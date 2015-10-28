/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.MoneyAccount;
import cc.kokoko.server.ibutler.domain.MoneyLog;
import cc.kokoko.server.ibutler.domain.OrderAccount;
import cc.kokoko.server.ibutler.domain.SysMoney;
import cc.kokoko.server.ibutler.domain.SysMoneyLog;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.persistence.MoneyLogMapper;
import cc.kokoko.server.ibutler.persistence.SysMoneyLogMapper;
import cc.kokoko.server.ibutler.persistence.UserMapper;
/*     */ 
/*     */ @Service("upmpChargeService")
/*     */ public class UpmpChargeService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private UserMapper userMapper;
/*     */ 
/*     */   @Autowired
/*     */   private SysMoneyLogMapper sysMoneyLogMapper;
/*     */ 
/*     */   @Autowired
/*     */   private MoneyLogMapper moneyLogMapper;
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void completeOrder(String orderId, String note)
/*     */     throws Exception
/*     */   {
/*  37 */     OrderAccount orderAccount = this.sysMoneyLogMapper.getOrderAccountByOrderId(orderId);
/*  38 */     if (orderAccount == null) {
/*  39 */       throw new Exception("订单信息不存在");
/*     */     }
/*  41 */     Double amount = orderAccount.getAmount();
/*     */ 
/*  43 */     Long uid = orderAccount.getUid();
/*     */ 
/*  45 */     Date createdTime = orderAccount.getCreatedTime();
/*     */ 
/*  47 */     if ((uid == null) || (uid.longValue() <= 0L)) {
/*  48 */       throw new Exception("用户编号为空");
/*     */     }
/*  50 */     User user = this.userMapper.getUserByUid(uid);
/*  51 */     if (user == null) {
/*  52 */       throw new Exception("用户不存在");
/*     */     }
/*     */ 
/*  55 */     MoneyAccount moneyAccount = this.moneyLogMapper.getMoneyAccount(user.getHouseId());
/*  56 */     if (moneyAccount == null) {
/*  57 */       moneyAccount = new MoneyAccount();
/*  58 */       moneyAccount.setBalance(new BigDecimal(0));
/*  59 */       moneyAccount.setHouseId(user.getHouseId());
/*  60 */       moneyAccount.setOwnerUid(uid);
/*  61 */       moneyAccount.setRoomNo("");
/*  62 */       this.moneyLogMapper.createMoneyAccount(moneyAccount);
/*     */     }
/*     */ 
/*  65 */     Map paramMap = new HashMap();
/*  66 */     paramMap.put("houseId", user.getHouseId());
/*  67 */     paramMap.put("amount", amount);
/*  68 */     this.moneyLogMapper.changeAccountBalance(paramMap);
/*     */ 
/*  71 */     MoneyLog moneyLog = new MoneyLog();
/*  72 */     moneyLog.setOrderId(orderId);
/*  73 */     moneyLog.setAmount(amount);
/*  74 */     moneyLog.setTradeTime(createdTime);
/*  75 */     moneyLog.setTradeType(AppConst.TradeType.TYPE_UMPM_CHARGE);
/*  76 */     moneyLog.setUid(uid);
/*  77 */     moneyLog.setHouseId(user.getHouseId());
/*  78 */     moneyLog.setNote("银联充值记录");
/*  79 */     this.moneyLogMapper.createMoneyLog(moneyLog);
/*     */ 
/*  82 */     SysMoney sysMoney = this.sysMoneyLogMapper.loadSysMoney();
/*  83 */     if (sysMoney != null) {
/*  84 */       sysMoney.setMoney(Double.valueOf(sysMoney.getMoney().doubleValue() + amount.doubleValue()));
/*  85 */       this.sysMoneyLogMapper.update(sysMoney);
/*     */     } else {
/*  87 */       sysMoney = new SysMoney();
/*  88 */       sysMoney.setMoney(amount);
/*  89 */       this.sysMoneyLogMapper.insert(sysMoney);
/*     */     }
/*     */ 
/*  93 */     SysMoneyLog sysMoneyLog = new SysMoneyLog();
/*  94 */     sysMoneyLog.setMoney(amount);
/*  95 */     sysMoneyLog.setType(AppConst.SysMoneyLog.TYPE_CHARGE);
/*  96 */     sysMoneyLog.setOrderId(orderId);
/*  97 */     sysMoneyLog.setNote("银联充值记录, 户号：" + user.getHouseId() + " " + note);
/*  98 */     this.sysMoneyLogMapper.insertMoneyLog(sysMoneyLog);
/*     */ 
/* 101 */     paramMap = new HashMap();
/* 102 */     paramMap.put("orderStatus", Integer.valueOf(1));
/* 103 */     paramMap.put("note", note);
/* 104 */     paramMap.put("orderId", orderId);
/* 105 */     this.sysMoneyLogMapper.updateOrderStatus(paramMap);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.UpmpChargeService
 * JD-Core Version:    0.6.0
 */