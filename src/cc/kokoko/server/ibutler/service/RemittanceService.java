/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Remittance;
import cc.kokoko.server.ibutler.domain.SysMoney;
import cc.kokoko.server.ibutler.domain.SysMoneyLog;
import cc.kokoko.server.ibutler.persistence.ReceiptMapper;
import cc.kokoko.server.ibutler.persistence.RemittanceMapper;
import cc.kokoko.server.ibutler.persistence.SysMoneyLogMapper;
/*     */ 
/*     */ @Service("remittanceService")
/*     */ public class RemittanceService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ReceiptMapper receiptMapper;
/*     */ 
/*     */   @Autowired
/*     */   private RemittanceMapper remittanceMapper;
/*     */ 
/*     */   @Autowired
/*     */   private SysMoneyLogMapper sysMoneyLogMapper;
/*     */ 
/*     */   public Map<String, Object> getRemittanceRecord(Long shopId, Date startTime, Date endTime, Integer pagesize, Integer curPage)
/*     */   {
/*  48 */     Map paramMap = new HashMap();
/*  49 */     paramMap.put("shopId", shopId);
/*  50 */     paramMap.put("startTime", startTime);
/*  51 */     paramMap.put("endTime", endTime);
/*  52 */     paramMap.put("pagesize", pagesize);
/*  53 */     paramMap.put("start", curPage);
/*     */ 
/*  55 */     List list = this.remittanceMapper.getRemittanceRecord(paramMap);
/*  56 */     Long count = this.remittanceMapper.getRemittanceCount(paramMap);
/*  57 */     paramMap = new HashMap();
/*  58 */     paramMap.put("list", list);
/*  59 */     paramMap.put("count", count);
/*  60 */     return paramMap;
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insert(Remittance remittance)
/*     */   {
/*  71 */     this.remittanceMapper.insert(remittance);
/*     */ 
/*  74 */     Map paramMap = new HashMap();
/*  75 */     paramMap.put("flag", Integer.valueOf(0));
/*  76 */     paramMap.put("shopId", remittance.getShopId());
/*  77 */     paramMap.put("startTime", remittance.getStartTime());
/*  78 */     paramMap.put("endTime", remittance.getEndTime());
/*  79 */     this.receiptMapper.update(paramMap);
/*     */ 
/*  82 */     SysMoney sysMoney = this.sysMoneyLogMapper.loadSysMoney();
/*  83 */     sysMoney.setMoney(Double.valueOf(sysMoney.getMoney().doubleValue() - remittance.getMoney().doubleValue()));
/*  84 */     this.sysMoneyLogMapper.update(sysMoney);
/*     */ 
/*  87 */     SysMoneyLog sysMoneyLog = new SysMoneyLog();
/*  88 */     sysMoneyLog.setMoney(remittance.getMoney());
/*  89 */     sysMoneyLog.setType(AppConst.SysMoneyLog.TYPE_SHOP_CLEAR);
/*  90 */     sysMoneyLog.setOrderId(remittance.getId().toString());
/*  91 */     sysMoneyLog.setNote("商家定点结算, 商家编号：" + remittance.getShopId());
/*  92 */     this.sysMoneyLogMapper.insertMoneyLog(sysMoneyLog);
/*     */   }
/*     */ 
/*     */   public Double queryReceiptMoney(Long shopId, Date startTime, Date endTime)
/*     */   {
/* 105 */     Map paramMap = new HashMap();
/* 106 */     paramMap.put("shopId", shopId);
/* 107 */     paramMap.put("startTime", startTime);
/* 108 */     paramMap.put("endTime", endTime);
/*     */ 
/* 110 */     return this.receiptMapper.getReceiptMoneyByParam(paramMap);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.RemittanceService
 * JD-Core Version:    0.6.0
 */