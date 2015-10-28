/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.SysDataAnalysis;
import cc.kokoko.server.ibutler.domain.SysInfoLog;
import cc.kokoko.server.ibutler.domain.SysMoney;
import cc.kokoko.server.ibutler.persistence.MoneyLogMapper;
import cc.kokoko.server.ibutler.persistence.ReceiptMapper;
import cc.kokoko.server.ibutler.persistence.SysActivityMapper;
import cc.kokoko.server.ibutler.persistence.SysDataAnalysisMapper;
import cc.kokoko.server.ibutler.persistence.SysInfoLogMapper;
import cc.kokoko.server.ibutler.persistence.SysMoneyLogMapper;
/*     */ 
/*     */ @Service("sysInfoService")
/*     */ public class SysInfoService
/*     */ {
/*  22 */   private Logger log = Logger.getLogger(getClass());
/*     */ 
/*     */   @Autowired
/*     */   private SysMoneyLogMapper sysMoneyLogMapper;
/*     */ 
/*     */   @Autowired
/*     */   private SysActivityMapper sysActivityMapper;
/*     */ 
/*     */   @Autowired
/*     */   private MoneyLogMapper moneyLogMapper;
/*     */ 
/*     */   @Autowired
/*     */   private ReceiptMapper receiptMapper;
/*     */ 
/*     */   @Autowired
/*     */   private SysInfoLogMapper sysInfoLogMapper;
/*     */ 
/*     */   @Autowired
/*     */   private SysDataAnalysisMapper sysDataAnalysisMapper;
/*     */ 
/*     */   @Autowired
/*     */   private AuthcodeService authcodeService;
/*  39 */   private int CHARGE = 1;
/*  40 */   private int CONSUME = 2;
/*  41 */   private int COMMODITY = 3;
/*  42 */   private int POS = 4;
/*  43 */   private int HAS_FLAG = 0;
/*  44 */   private int LOST_FLAG = 1;
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void statistics()
/*     */   {
/*     */     try
/*     */     {
/*  53 */       SysMoney sysMoney = this.sysMoneyLogMapper.loadSysMoney();
/*  54 */       if (sysMoney == null) {
/*  55 */         throw new Exception("监管账户金额获取失败");
/*     */       }
/*  57 */       Double activityMoney = this.sysActivityMapper.getBackMoneyTotal();
/*     */ 
/*  59 */       Double chargeMoney = getMoneyTotalByParam(this.CHARGE);
/*     */ 
/*  61 */       Double comsumeMoney = getMoneyTotalByParam(this.CONSUME);
/*     */ 
/*  63 */       Double commodityMoney = getMoneyTotalByParam(this.COMMODITY);
/*     */ 
/*  65 */       Double posMoney = getMoneyTotalByParam(this.POS);
/*     */ 
/*  67 */       Double shopMoney = getReceiptTotalByParam(null);
/*     */ 
/*  69 */       Double shopHasMoney = getReceiptTotalByParam(Integer.valueOf(this.HAS_FLAG));
/*     */ 
/*  71 */       Double shopLostMoney = getReceiptTotalByParam(Integer.valueOf(this.LOST_FLAG));
/*     */ 
/*  74 */       SysInfoLog sysInfoLog = new SysInfoLog();
/*  75 */       sysInfoLog.setSysMoney(Double.valueOf(sysMoney.getMoney() == null ? 0.0D : sysMoney.getMoney().doubleValue()));
/*  76 */       sysInfoLog.setActivityMoney(Double.valueOf(activityMoney == null ? 0.0D : activityMoney.doubleValue()));
/*  77 */       sysInfoLog.setChargeMoney(Double.valueOf(chargeMoney == null ? 0.0D : chargeMoney.doubleValue()));
/*  78 */       sysInfoLog.setComsumeMoney(Double.valueOf(comsumeMoney == null ? 0.0D : comsumeMoney.doubleValue()));
/*  79 */       sysInfoLog.setCommodityMoney(Double.valueOf(commodityMoney == null ? 0.0D : commodityMoney.doubleValue()));
/*  80 */       sysInfoLog.setPosMoney(Double.valueOf(posMoney == null ? 0.0D : posMoney.doubleValue()));
/*  81 */       sysInfoLog.setShopMoney(Double.valueOf(shopMoney == null ? 0.0D : shopMoney.doubleValue()));
/*  82 */       sysInfoLog.setShopHasMoney(Double.valueOf(shopHasMoney == null ? 0.0D : shopHasMoney.doubleValue()));
/*  83 */       sysInfoLog.setShopLostMoney(Double.valueOf(shopLostMoney == null ? 0.0D : shopLostMoney.doubleValue()));
/*     */ 
/*  85 */       this.sysInfoLogMapper.insert(sysInfoLog);
/*     */ 
/*  88 */       double money = add(sysMoney.getMoney().doubleValue(), shopHasMoney.doubleValue());
/*  89 */       if (money * 100.0D != chargeMoney.doubleValue() * 100.0D) {
/*  90 */         String content = "系统账目资金失衡，请速查找原因并予以解决【微管家一卡通】";
/*  91 */         this.authcodeService.sendSMS("15658072101", content);
/*     */       }
/*     */ 
/*  95 */       dataAnalysis();
/*     */     }
/*     */     catch (Exception e) {
/*  98 */       this.log.error("statistics error " + e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void dataAnalysis()
/*     */   {
/*     */     try
/*     */     {
/* 109 */       Long appDowns = this.sysDataAnalysisMapper.getAppDowns();
/*     */ 
/* 111 */       Long cardCount = this.sysDataAnalysisMapper.getCardCount();
/*     */ 
/* 113 */       Long onlineChargeCount = this.sysDataAnalysisMapper.getMoneyLogCountByType(AppConst.TradeType.TYPE_UMPM_CHARGE.byteValue());
/*     */ 
/* 115 */       Long offlineChargeCount = this.sysDataAnalysisMapper.getMoneyLogCountByType(AppConst.TradeType.TYPE_CHARGE.byteValue());
/*     */ 
/* 117 */       Long commodityCount = this.sysDataAnalysisMapper.getMoneyLogCountByType(AppConst.TradeType.TYPE_COMMODITY_PAY.byteValue());
/*     */ 
/* 119 */       Long posCount = this.sysDataAnalysisMapper.getMoneyLogCountByType(AppConst.TradeType.TYPE_POS_PAY.byteValue());
/*     */ 
/* 121 */       Long houseCardCount = this.sysDataAnalysisMapper.getHouseCardCount();
/*     */ 
/* 123 */       Map paramMap = new HashMap();
/* 124 */       paramMap.put("type", Integer.valueOf(1));
/*     */ 
/* 126 */       Long houseChargeCount = this.sysDataAnalysisMapper.getHouseTradeCountByParam(paramMap);
/* 127 */       paramMap = new HashMap();
/* 128 */       paramMap.put("type", Integer.valueOf(2));
/*     */ 
/* 130 */       Long houseConsumeCount = this.sysDataAnalysisMapper.getHouseTradeCountByParam(paramMap);
/*     */ 
/* 132 */       SysDataAnalysis sysDataAnalysis = new SysDataAnalysis();
/* 133 */       sysDataAnalysis.setAppDowns(appDowns);
/* 134 */       sysDataAnalysis.setCardCount(cardCount);
/* 135 */       sysDataAnalysis.setOnlineChargeCount(onlineChargeCount);
/* 136 */       sysDataAnalysis.setOfflineChargeCount(offlineChargeCount);
/* 137 */       sysDataAnalysis.setCommodityCount(commodityCount);
/* 138 */       sysDataAnalysis.setPosCount(posCount);
/* 139 */       sysDataAnalysis.setHouseCardCount(houseCardCount);
/* 140 */       sysDataAnalysis.setHouseChargeCount(houseChargeCount);
/* 141 */       sysDataAnalysis.setHouseConsumeCount(houseConsumeCount);
/*     */ 
/* 143 */       this.sysDataAnalysisMapper.insert(sysDataAnalysis);
/*     */     }
/*     */     catch (Exception e) {
/* 146 */       this.log.error("dataAnalysis error " + e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private double add(double v1, double v2)
/*     */   {
/* 158 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/* 159 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/* 160 */     return b1.add(b2).doubleValue();
/*     */   }
/*     */ 
/*     */   public SysInfoLog getLastSysInfoLog()
/*     */   {
/* 169 */     return this.sysInfoLogMapper.getLastSysInfoLog();
/*     */   }
/*     */ 
/*     */   public List<SysInfoLog> getTop10SysInfoLog()
/*     */   {
/* 178 */     return this.sysInfoLogMapper.getTop10SysInfoLog();
/*     */   }
/*     */ 
/*     */   public SysDataAnalysis getLastSysDataAnalysis()
/*     */   {
/* 187 */     return this.sysDataAnalysisMapper.getLastSysDataAnalysis();
/*     */   }
/*     */ 
/*     */   public List<SysDataAnalysis> getTop10SysDataAnalysis()
/*     */   {
/* 196 */     return this.sysDataAnalysisMapper.getTop10SysDataAnalysis();
/*     */   }
/*     */ 
/*     */   private Double getMoneyTotalByParam(int type)
/*     */   {
/* 206 */     Map paramMap = new HashMap();
/* 207 */     paramMap.put("type", Integer.valueOf(type));
/* 208 */     return this.moneyLogMapper.getMoneyTotalByParam(paramMap);
/*     */   }
/*     */ 
/*     */   private Double getReceiptTotalByParam(Integer flag)
/*     */   {
/* 218 */     Map paramMap = new HashMap();
/* 219 */     paramMap.put("flag", Integer.valueOf(flag == null ? -1 : flag.intValue()));
/* 220 */     return this.receiptMapper.getReceiptTotalByParam(paramMap);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.SysInfoService
 * JD-Core Version:    0.6.0
 */