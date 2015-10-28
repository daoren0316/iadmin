/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.util.MaxMathUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.OrderAccount;
import cc.kokoko.server.ibutler.domain.Pos;
import cc.kokoko.server.ibutler.domain.RDOrder;
import cc.kokoko.server.ibutler.domain.SysMoney;
import cc.kokoko.server.ibutler.domain.SysMoneyLog;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.persistence.PosMapper;
import cc.kokoko.server.ibutler.persistence.SysMoneyLogMapper;
import cc.kokoko.server.ibutler.persistence.TradeMapper;
/*     */ 
/*     */ @Service("tradeService")
/*     */ public class TradeService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private TradeMapper tradeMapper;
/*     */ 
/*     */   @Autowired
/*     */   private PosMapper posMapper;
/*     */ 
/*     */   @Autowired
/*     */   private SysMoneyLogMapper sysMoneyLogMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @Autowired
/*     */   private MoneyService moneyService;
/*     */ 
/*     */   @Autowired
/*     */   private ReceiptService receiptService;
/*  31 */   private static Logger log = LoggerFactory.getLogger(TradeService.class);
/*     */ 
/*     */   @Transactional
/*     */   public void createRDOrder(RDOrder rdOrder, String cardNo, double yAmount, double totalSavedAmount, Long shopId)
/*     */   {
/*  45 */     this.tradeMapper.createRDOrder(rdOrder);
/*     */ 
/*  47 */     this.moneyService.changeAccountBalance(rdOrder.getUid(), rdOrder.getHouseId(), yAmount, AppConst.TradeType.TYPE_POS_PAY, rdOrder.getTradeId(), "POS刷卡消费 ");
/*     */ 
/*  50 */     User posUser = getUserByCardNo(cardNo);
/*     */ 
/*  52 */     this.receiptService.createReceiptAccount(shopId, yAmount, posUser.getPublicAddress(), rdOrder.getTradeId());
/*     */ 
/*  54 */     this.moneyService.updateTotalSavedAmount(rdOrder.getHouseId(), totalSavedAmount);
/*     */   }
/*     */ 
/*     */   @Transactional
/*     */   public void flushes(RDOrder rdOrder)
/*     */   {
/*  65 */     this.tradeMapper.updateRDOrder(rdOrder);
/*     */ 
/*  67 */     double yAmount = MaxMathUtil.getYuanFromFen(rdOrder.getAmount());
/*     */ 
/*  69 */     String orderId = AppConst.OrderAccount.generateOrdrNo();
/*     */ 
/*  71 */     this.moneyService.changeAccountBalance(rdOrder.getUid(), rdOrder.getHouseId(), yAmount, AppConst.TradeType.TYPE_CHARGE, orderId, "POS交易退款 ");
/*     */ 
/*  75 */     SysMoney sysMoney = this.sysMoneyLogMapper.loadSysMoney();
/*  76 */     if (sysMoney != null) {
/*  77 */       sysMoney.setMoney(Double.valueOf(sysMoney.getMoney().doubleValue() + yAmount));
/*  78 */       this.sysMoneyLogMapper.update(sysMoney);
/*     */     } else {
/*  80 */       sysMoney = new SysMoney();
/*  81 */       sysMoney.setMoney(Double.valueOf(yAmount));
/*  82 */       this.sysMoneyLogMapper.insert(sysMoney);
/*     */     }
/*     */ 
/*  85 */     OrderAccount orderAccount = new OrderAccount();
/*  86 */     orderAccount.setOrderId(orderId);
/*  87 */     orderAccount.setTn(orderId);
/*  88 */     orderAccount.setAmount(Double.valueOf(yAmount));
/*  89 */     orderAccount.setOrderType(AppConst.OrderAccount.TYPE_OFFLINE_CHARGE);
/*  90 */     orderAccount.setOrderStatus(AppConst.OrderAccount.STATUS_SUC);
/*  91 */     orderAccount.setUid(rdOrder.getUid());
/*  92 */     orderAccount.setHouseId(rdOrder.getHouseId());
/*  93 */     orderAccount.setNote("POS交易退款, 户号： " + rdOrder.getHouseId() + "退款订单号：" + rdOrder.getTradeId());
/*  94 */     this.sysMoneyLogMapper.insertOrderAccount(orderAccount);
/*     */ 
/*  97 */     SysMoneyLog sysMoneyLog = new SysMoneyLog();
/*  98 */     sysMoneyLog.setMoney(Double.valueOf(yAmount));
/*  99 */     sysMoneyLog.setType(AppConst.SysMoneyLog.TYPE_CHARGE);
/* 100 */     sysMoneyLog.setOrderId(orderId);
/* 101 */     sysMoneyLog.setNote("POS交易退款, 户号：" + rdOrder.getHouseId() + "退款订单号：" + rdOrder.getTradeId());
/* 102 */     this.sysMoneyLogMapper.insertMoneyLog(sysMoneyLog);
/*     */ 
/* 105 */     this.receiptService.deleteReceiptAccountByTradeId(rdOrder.getTradeId());
/*     */   }
/*     */ 
/*     */   public RDOrder getRDOrder(String tradeId)
/*     */   {
/* 115 */     return this.tradeMapper.getRDOrderByTradeId(tradeId);
/*     */   }
/*     */ 
/*     */   public double getDisCount(String shopNo)
/*     */   {
/* 125 */     Long shopId = this.tradeMapper.getShopIdByShopNo(shopNo);
/* 126 */     int i = 100;
/* 127 */     if ((shopId != null) && (shopId.longValue() > 0L))
/* 128 */       i = this.tradeMapper.getShopDiscountByShopId(shopId).intValue();
/*     */     else {
/* 130 */       log.error("[getDisCount] err, shopNo=" + shopNo);
/*     */     }
/* 132 */     return i / 100.0D;
/*     */   }
/*     */ 
/*     */   public double getUserBalanceByCardNo(String cardNo)
/*     */   {
/* 142 */     User user = getUserByCardNo(cardNo);
/* 143 */     if (user == null) {
/* 144 */       log.error("[getUserBalanceByCardNo] cardNo=" + cardNo);
/* 145 */       return 0.0D;
/*     */     }
/* 147 */     return this.moneyService.getUserBalance(user.getUid(), user.getHouseId()).doubleValue();
/*     */   }
/*     */ 
/*     */   public User getUserByCardNo(String cardNo)
/*     */   {
/* 157 */     return this.userService.getUserByRdCardNo(cardNo);
/*     */   }
/*     */ 
/*     */   public Long getShopIdByPosNo(String posNo)
/*     */   {
/* 167 */     Long shopId = Long.valueOf(0L);
/* 168 */     Pos pos = this.posMapper.getOneByPosNo(posNo);
/* 169 */     if (pos != null) {
/* 170 */       shopId = pos.getShopId();
/*     */     }
/* 172 */     return shopId;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.TradeService
 * JD-Core Version:    0.6.0
 */