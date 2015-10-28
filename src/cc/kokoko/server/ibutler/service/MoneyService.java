/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.exception.KeepDataException;
import cc.kokoko.server.commons.util.CipherUtil;
import cc.kokoko.server.commons.util.HttpClientUtils;
import cc.kokoko.server.commons.util.MaxMD5Util;
import cc.kokoko.server.commons.util.MaxMathUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.CarFee;
import cc.kokoko.server.ibutler.domain.Charge;
import cc.kokoko.server.ibutler.domain.FamilyMember;
import cc.kokoko.server.ibutler.domain.Fee;
import cc.kokoko.server.ibutler.domain.MoneyAccount;
import cc.kokoko.server.ibutler.domain.MoneyLog;
import cc.kokoko.server.ibutler.domain.Order;
import cc.kokoko.server.ibutler.domain.OrderAccount;
import cc.kokoko.server.ibutler.domain.PropertyFee;
import cc.kokoko.server.ibutler.domain.Shop;
import cc.kokoko.server.ibutler.domain.SysMoney;
import cc.kokoko.server.ibutler.domain.SysMoneyLog;
import cc.kokoko.server.ibutler.domain.Trade;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.domain.dto.ConsumeLogDTO;
import cc.kokoko.server.ibutler.domain.dto.MoneyAccountDTO;
import cc.kokoko.server.ibutler.domain.dto.MoneyLogDTO;
import cc.kokoko.server.ibutler.persistence.FeeMapper;
import cc.kokoko.server.ibutler.persistence.MoneyLogMapper;
import cc.kokoko.server.ibutler.persistence.ShopMapper;
import cc.kokoko.server.ibutler.persistence.SysMoneyLogMapper;
import cc.kokoko.server.ibutler.persistence.UserMapper;
import cc.kokoko.server.ibutler.service.util.MoneyUtil;
import cc.kokoko.server.ibutler.service.util.UserUtil;
/*     */ 
/*     */ @Service("moneyService")
/*     */ public class MoneyService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private FeeMapper feeMapper;
/*     */ 
/*     */   @Autowired
/*     */   private MoneyLogMapper moneyLogMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserMapper userMapper;
/*     */ 
/*     */   @Autowired
/*     */   private SysActivityService sysActivityService;
/*     */ 
/*     */   @Autowired
/*     */   private SysMoneyLogMapper sysMoneyLogMapper;
/*     */ 
/*     */   @Autowired
/*     */   private ReceiptService receiptService;
/*     */ 
/*     */   @Autowired
/*     */   private AuthcodeService authcodeService;
/*     */ 
/*     */   @Autowired
/*     */   private ShopMapper shopMapper;
/*  43 */   private static Logger log = LoggerFactory.getLogger(MoneyService.class);
/*     */ 
/*     */   public Double getUserBalance(Long uid, Long houseId) throws KeepDataException
/*     */   {
/*  47 */     double ret = 0.0D;
/*  48 */     if ((houseId != null) && (houseId.longValue() > 0L)) {
/*  49 */       MoneyAccount account = getMoneyAccount(houseId);
/*  50 */       if (account == null) {
/*  51 */         throw new KeepDataException("account not found , houseId=" + houseId);
/*     */       }
/*     */ 
/*  54 */       if ((account.getOwnerUid().equals(uid)) || (isFamilyMember(uid, houseId)))
/*     */       {
/*  56 */         ret = getMoneyAccountBalance(account);
/*     */       }
/*     */     }
/*     */     else {
/*  60 */       return Double.valueOf(ret);
/*     */     }
/*  62 */     return Double.valueOf(ret);
/*     */   }
/*     */ 
/*     */   public boolean isPayAllowed(Long uid, Long houseId) {
/*  66 */     boolean ret = false;
/*  67 */     if ((houseId != null) && (houseId.longValue() > 0L)) {
/*  68 */       MoneyAccount account = getMoneyAccount(houseId);
/*  69 */       if (account == null) {
/*  70 */         return false;
/*     */       }
/*  72 */       if ((account.getOwnerUid().equals(uid)) || (isFamilyMember(uid, houseId)))
/*     */       {
/*  74 */         ret = true;
/*     */       }
/*     */     }
/*  77 */     return ret;
/*     */   }
/*     */ 
/*     */   public Double getFeeAmountByFeeId(Long feeId) {
/*  81 */     Double feeAmount = this.feeMapper.getFeeAmountByFeeId(feeId);
/*  82 */     return Double.valueOf(feeAmount == null ? 0.0D : feeAmount.doubleValue());
/*     */   }
/*     */ 
/*     */   public List<CarFee> getCarFeeList(Long houseId) {
/*  86 */     return this.feeMapper.getCarFeeList(houseId);
/*     */   }
/*     */ 
/*     */   public List<PropertyFee> getPropertyFeeList(Long houseId) {
/*  90 */     return this.feeMapper.getPropertyFeeList(houseId);
/*     */   }
/*     */ 
/*     */   public Trade getTradeById(Long uid, Long tradeId) {
/*  94 */     Trade trade = null;
/*  95 */     MoneyLog moneyLog = this.moneyLogMapper.getMoneyLogById(tradeId);
/*  96 */     if (moneyLog == null) {
/*  97 */       return null;
/*     */     }
/*  99 */     if (tradeId.equals(moneyLog.getId())) {
/* 100 */       trade = MoneyUtil.MoneyLog2Trade(moneyLog);
/*     */     }
/* 102 */     return trade;
/*     */   }
/*     */ 
/*     */   public Charge getChargeById(Long uid, Long chargeId) {
/* 106 */     Charge charge = null;
/* 107 */     MoneyLog moneyLog = this.moneyLogMapper.getMoneyLogById(chargeId);
/* 108 */     if (moneyLog == null) {
/* 109 */       return null;
/*     */     }
/* 111 */     if (chargeId.equals(moneyLog.getId())) {
/* 112 */       charge = MoneyUtil.MoneyLog2Charge(moneyLog);
/*     */     }
/* 114 */     return charge;
/*     */   }
/*     */ 
/*     */   public List<Trade> getTradeList(Long uid) {
/* 118 */     return this.moneyLogMapper.getTradeList(uid);
/*     */   }
/*     */ 
/*     */   public List<Charge> getChargeList(Long uid) {
/* 122 */     return this.moneyLogMapper.getChargeList(uid);
/*     */   }
/*     */ 
/*     */   public List<Charge> getChargeListByHouseId(Long houseId) {
/* 126 */     return this.moneyLogMapper.getChargeListByHouseId(houseId);
/*     */   }
/*     */ 
/*     */   public void createMoneyLog(Long uid, Long houseId, Byte tradeType, double amount, String orderId, String note)
/*     */   {
/* 141 */     MoneyLog moneyLog = new MoneyLog();
/* 142 */     moneyLog.setOrderId(orderId);
/* 143 */     moneyLog.setAmount(Double.valueOf(amount));
/* 144 */     moneyLog.setTradeTime(new Date());
/* 145 */     moneyLog.setTradeType(tradeType);
/* 146 */     moneyLog.setUid(uid);
/* 147 */     moneyLog.setHouseId(houseId);
/* 148 */     moneyLog.setNote(note);
/* 149 */     this.moneyLogMapper.createMoneyLog(moneyLog);
/*     */   }
/*     */ 
/*     */   public void createMoneyLog(MoneyLog moneyLog) {
/* 153 */     this.moneyLogMapper.createMoneyLog(moneyLog);
/*     */   }
/*     */ 
/*     */   public MoneyAccountDTO getMoneyAccountDTOByUid(Long uid) {
/* 157 */     return null;
/*     */   }
/*     */ 
/*     */   public List<FamilyMember> getFamilyMemberList(Long uid, Long houseId, String phoneNumber)
/*     */   {
/* 162 */     Map map = new HashMap();
/* 163 */     map.put("memberId", uid);
/* 164 */     map.put("houseId", houseId);
/* 165 */     List list = new ArrayList();
/* 166 */     if (!StringUtil.isEmpty(phoneNumber)) {
/* 167 */       map.put("phoneNumber", phoneNumber);
/* 168 */       list = this.moneyLogMapper.getSearchFamilyMemberList(map);
/*     */     } else {
/* 170 */       list = this.moneyLogMapper.getFamilyMemberList(map);
/*     */     }
/*     */ 
/* 173 */     addUserProfileImagePrefix(list);
/* 174 */     return list;
/*     */   }
/*     */ 
/*     */   public boolean isFamilyMember(Long uid, Long houseId) {
/* 178 */     boolean ret = false;
/* 179 */     FamilyMember member = getFamilyMember(uid, houseId);
/* 180 */     if ((member != null) && (member.getIsAccepted().booleanValue() == true) && (member.getIsPaymentAllowed().booleanValue() == true))
/*     */     {
/* 182 */       ret = true;
/*     */     }
/*     */ 
/* 185 */     return ret;
/*     */   }
/*     */ 
/*     */   public FamilyMember getFamilyMember(Long memberId, Long houseId) {
/* 189 */     Map map = new HashMap();
/* 190 */     map.put("memberId", memberId);
/* 191 */     map.put("houseId", houseId);
/* 192 */     return this.moneyLogMapper.getFamilyMember(map);
/*     */   }
/*     */ 
/*     */   public void addUserProfileImagePrefix(List<FamilyMember> list) {
/* 196 */     for (FamilyMember member : list)
/* 197 */       if ((member != null) && (!StringUtil.isEmpty(member.getProfileImage())))
/* 198 */         member.setProfileImage(UserUtil.getUserAvatar(member.getProfileImage()));
/*     */   }
/*     */ 
/*     */   public void updateFamilyMember(FamilyMember familyMember)
/*     */   {
/* 205 */     this.moneyLogMapper.updateFamilyMember(familyMember);
/*     */   }
/*     */ 
/*     */   public List<Order> getOrderList(Long uid, Byte tradeStatus) {
/* 209 */     Map map = new HashMap();
/* 210 */     map.put("uid", uid);
/* 211 */     map.put("tradeStatus", tradeStatus);
/* 212 */     return this.moneyLogMapper.getOrderList(map);
/*     */   }
/*     */ 
/*     */   public String createOrder(Long uid, Long shopId, Byte orderType, String content, double amount, String picUrl, Long commodityId, String commodityTitle, Double savingsAmount)
/*     */   {
/* 223 */     String ret = "";
/* 224 */     Order order = new Order();
/* 225 */     order.setUid(uid);
/* 226 */     order.setShopId(shopId);
/* 227 */     order.setPaymentStatus(AppConst.PaymentStatus.WAITING_FOR_PAY);
/* 228 */     order.setTradeStatus(AppConst.OrderStatus.NORMAL);
/* 229 */     order.setCreateedTime(new Date());
/* 230 */     order.setContent(content);
/* 231 */     order.setAmount(Double.valueOf(amount));
/* 232 */     order.setPicUrl(picUrl);
/* 233 */     order.setOrderType(orderType);
/* 234 */     order.setCommodityId(commodityId);
/* 235 */     order.setCommodityTitle(commodityTitle);
/* 236 */     order.setSavingsAmount(savingsAmount);
/* 237 */     this.moneyLogMapper.createOrder(order);
/* 238 */     ret = order.getOrderId();
/* 239 */     return ret;
/*     */   }
/*     */ 
/*     */   public Order getOrderById(String orderId)
/*     */   {
/* 244 */     return this.moneyLogMapper.getOrderById(orderId);
/*     */   }
/*     */ 
/*     */   public void updateOrder(Order order) {
/* 248 */     this.moneyLogMapper.updateOrder(order);
/*     */   }
/*     */ 
/*     */   public MoneyAccount getMoneyAccount(Long houseId) {
/* 252 */     return this.moneyLogMapper.getMoneyAccount(houseId);
/*     */   }
/*     */ 
/*     */   public void createMoneyAccount(Long houseId, Long ownerUid, BigDecimal balance)
/*     */   {
/* 264 */     if (balance == null) {
/* 265 */       balance = new BigDecimal("0");
/*     */     }
/* 267 */     MoneyAccount moneyAccount = new MoneyAccount();
/* 268 */     moneyAccount.setBalance(balance);
/* 269 */     moneyAccount.setHouseId(houseId);
/* 270 */     moneyAccount.setOwnerUid(ownerUid);
/* 271 */     moneyAccount.setRoomNo("");
/* 272 */     this.moneyLogMapper.createMoneyAccount(moneyAccount);
/*     */   }
/*     */ 
/*     */   public double getMoneyAccountBalance(Long houseId)
/*     */   {
/* 282 */     MoneyAccount account = getMoneyAccount(houseId);
/* 283 */     return getMoneyAccountBalance(account);
/*     */   }
/*     */ 
/*     */   public double getMoneyAccountBalance(MoneyAccount account) {
/* 287 */     double ret = 0.0D;
/* 288 */     if ((account != null) && (account.getBalance() != null)) {
/* 289 */       ret = account.getBalance().doubleValue();
/*     */     }
/*     */ 
/* 292 */     return ret;
/*     */   }
/*     */ 
/*     */   @Transactional
/*     */   public Map<String, Object> pay(Long houseId, String orderId)
/*     */   {
/* 303 */     int resultCode = 0;
/* 304 */     String msg = "";
/* 305 */     Map map = new HashMap();
/*     */ 
/* 307 */     double balance = getMoneyAccountBalance(houseId);
/* 308 */     double newBalance = balance;
/* 309 */     double savings = 0.0D;
/*     */ 
/* 311 */     Order order = getOrderById(orderId);
/* 312 */     if (order == null) {
/* 313 */       resultCode = -10120;
/* 314 */       msg = "未找到对应的订单";
/*     */     }
/* 316 */     else if (balance < order.getAmount().doubleValue()) {
/* 317 */       resultCode = -1021;
/* 318 */       msg = "余额不足";
/*     */     } else {
/* 320 */       order.setPaymentStatus(AppConst.PaymentStatus.PAY_SUCC);
/* 321 */       updateOrder(order);
/*     */ 
/* 323 */       changeAccountBalance(order.getUid(), houseId, order.getAmount().doubleValue(), AppConst.TradeType.TYPE_COMMODITY_PAY, orderId, order.getContent());
/*     */ 
/* 327 */       this.receiptService.createReceiptAccount(AppConst.REFUND_SHOP, balance, "", orderId);
/*     */ 
/* 329 */       double payAmount = MaxMathUtil.getFormatedValue(order.getAmount().doubleValue(), 2);
/* 330 */       savings = order.getSavingsAmount().doubleValue();
/* 331 */       newBalance = balance - payAmount;
/*     */     }
/*     */ 
/* 334 */     map.put("code", Integer.valueOf(resultCode));
/* 335 */     map.put("msg", msg);
/* 336 */     map.put("newBalance", Double.valueOf(MaxMathUtil.getFormatedValue(newBalance, 2)));
/* 337 */     map.put("savings", Double.valueOf(MaxMathUtil.getFormatedValue(savings, 2)));
/* 338 */     return map;
/*     */   }
/*     */ 
/*     */   @Transactional
/*     */   public void changeAccountBalance(Long uid, Long houseId, double amount, Byte tradeType, String orderId, String note)
/*     */   {
/* 353 */     Map paramMap = new HashMap();
/* 354 */     paramMap.put("houseId", houseId);
/* 355 */     boolean isPay = false;
/*     */ 
/* 357 */     if ((tradeType.equals(AppConst.TradeType.TYPE_POS_PAY)) || (tradeType.equals(AppConst.TradeType.TYPE_COMMODITY_PAY)) || (tradeType.equals(AppConst.TradeType.TYPE_FEE_PAY)) || (tradeType.equals(AppConst.TradeType.TYPE_PACK_PAY)))
/*     */     {
/* 359 */       paramMap.put("amount", Double.valueOf(0.0D - amount));
/* 360 */       isPay = true;
/*     */     } else {
/* 362 */       paramMap.put("amount", Double.valueOf(amount));
/*     */     }
/*     */ 
/* 365 */     this.moneyLogMapper.changeAccountBalance(paramMap);
/*     */ 
/* 367 */     createMoneyLog(uid, houseId, tradeType, amount, orderId, note);
/*     */ 
/* 369 */     User user = this.userMapper.getUserByUid(uid);
/* 370 */     Double balance = Double.valueOf(getMoneyAccountBalance(houseId));
/* 371 */     String sign = isPay ? "消费" : "充值";
/* 372 */     String content = "感谢您使用微管家一卡通，本次" + sign + "金额为：" + amount + "元，当前账户余额为：" + balance + "如有疑问，请致电0571-86999823";
/* 373 */     this.authcodeService.sendSMS(user.getPhoneNumber(), content);
/*     */ 
/* 376 */     if ((tradeType.equals(AppConst.TradeType.TYPE_POS_PAY)) || (tradeType.equals(AppConst.TradeType.TYPE_COMMODITY_PAY)) || (tradeType.equals(AppConst.TradeType.TYPE_FEE_PAY)) || (tradeType.equals(AppConst.TradeType.TYPE_PACK_PAY)))
/*     */     {
/* 378 */       this.sysActivityService.backPayment(uid, houseId, Double.valueOf(amount));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateTotalSavedAmount(Long houseId, double totalSavedAmount) {
/* 383 */     Map paramMap = new HashMap();
/* 384 */     paramMap.put("houseId", houseId);
/* 385 */     paramMap.put("totalSavedAmount", Double.valueOf(totalSavedAmount));
/* 386 */     this.moneyLogMapper.updateTotalSavedAmount(paramMap);
/*     */   }
/*     */ 
/*     */   public double getTotalSavedAmount(Long houseId) {
/* 390 */     Double totalSavedAmount = this.moneyLogMapper.getTotalSavedAmount(houseId);
/* 391 */     return totalSavedAmount == null ? 0.0D : totalSavedAmount.doubleValue();
/*     */   }
/*     */ 
/*     */   public Map<String, Object> changeAccountPassword(Long uid, Long houseId, String newPassword)
/*     */     throws KeepDataException
/*     */   {
/* 397 */     MoneyAccount account = getMoneyAccount(houseId);
/* 398 */     Map map = changeAccountPassword(uid, account, newPassword);
/*     */ 
/* 400 */     map.remove("md5");
/* 401 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> initAccountPassword(Long uid, Long houseId, String password)
/*     */     throws KeepDataException
/*     */   {
/* 417 */     MoneyAccount account = getMoneyAccount(houseId);
/* 418 */     if (!StringUtil.isNull(account.getPassword())) {
/* 419 */       Map retMap = new HashMap();
/* 420 */       retMap.put("code", Integer.valueOf(-1028));
/* 421 */       retMap.put("msg", "old payment password not empty, can not init");
/* 422 */       return retMap;
/*     */     }
/* 424 */     Map map = changeAccountPassword(uid, account, password);
/*     */ 
/* 426 */     map.remove("md5");
/* 427 */     return map;
/*     */   }
/*     */ 
/*     */   public boolean isHouseOwner(Long uid, Long houseId)
/*     */   {
/* 432 */     boolean ret = false;
/* 433 */     MoneyAccount account = getMoneyAccount(houseId);
/* 434 */     if ((account != null) && (uid.equals(account.getOwnerUid()))) {
/* 435 */       ret = true;
/*     */     }
/*     */ 
/* 438 */     return ret;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> changeAccountPassword(Long uid, MoneyAccount account, String newPassword)
/*     */     throws KeepDataException
/*     */   {
/* 444 */     Map map = new HashMap();
/* 445 */     int code = 0;
/* 446 */     String msg = "";
/* 447 */     if (account == null) {
/* 448 */       throw new KeepDataException("money account is null");
/*     */     }
/*     */ 
/* 451 */     if (!uid.equals(account.getOwnerUid())) {
/* 452 */       code = -1023;
/* 453 */       log.error("[changeAccountPassword] uid=" + uid + ", houseId=" + account.getHouseId());
/*     */ 
/* 455 */       msg = "只有户主才能修改支付密码";
/*     */     }
/* 457 */     Map paramMap = new HashMap();
/* 458 */     paramMap.put("houseId", account.getHouseId());
/* 459 */     paramMap.put("password", MaxMD5Util.toMD5(newPassword));
/* 460 */     paramMap.put("ciphertext", CipherUtil.encodePassword(newPassword));
/* 461 */     this.moneyLogMapper.changeAccountPassword(paramMap);
/*     */     try {
/* 463 */       HttpClientUtils.changeCardPassword(uid, newPassword);
/*     */     } catch (Exception e) {
/* 465 */       log.error("[changeAccountPassword] 调用融鼎接口出错 uid=" + uid + ", houseId=" + account.getHouseId() + e.getMessage());
/*     */ 
/* 467 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 470 */     map.put("md5", account.getPassword());
/* 471 */     map.put("msg", msg);
/* 472 */     map.put("code", Integer.valueOf(code));
/* 473 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> changeAccountPassword(Long uid, Long houseId, String oldPassword, String newPassword)
/*     */   {
/* 478 */     MoneyAccount account = getMoneyAccount(houseId);
/* 479 */     Map map = new HashMap();
/* 480 */     boolean ret = false;
/* 481 */     if ((StringUtil.isEmpty(oldPassword)) && (StringUtil.isEmpty(account.getPassword())))
/*     */     {
/* 483 */       ret = true;
/*     */     } else {
/* 485 */       oldPassword = null == oldPassword ? "" : oldPassword;
/* 486 */       if (MaxMD5Util.toMD5(oldPassword).equals(account.getPassword())) {
/* 487 */         ret = true;
/*     */       }
/*     */     }
/* 490 */     if (!ret) {
/* 491 */       map.put("code", Integer.valueOf(-1024));
/* 492 */       map.put("msg", "老支付密码错误");
/*     */     } else {
/*     */       try {
/* 495 */         map = changeAccountPassword(uid, account, newPassword);
/*     */       }
/*     */       catch (Exception e) {
/* 498 */         log.error("[changeAccountPassword] " + e.getStackTrace());
/*     */       }
/*     */     }
/*     */ 
/* 502 */     map.remove("md5");
/*     */ 
/* 504 */     return map;
/*     */   }
/*     */ 
/*     */   public boolean isPayPasswordSet(Long houseId) {
/* 508 */     boolean ret = true;
/* 509 */     MoneyAccount account = getMoneyAccount(houseId);
/* 510 */     if ((account == null) || (StringUtil.isNull(account.getPassword()))) {
/* 511 */       ret = false;
/*     */     }
/*     */ 
/* 514 */     return ret;
/*     */   }
/*     */ 
/*     */   public boolean isPayPasswordRight(Long houseId, String md5Password) {
/* 518 */     if (md5Password == null) {
/* 519 */       md5Password = "";
/*     */     }
/* 521 */     boolean ret = false;
/* 522 */     MoneyAccount account = getMoneyAccount(houseId);
/* 523 */     if (account != null)
/*     */     {
/* 525 */       if ((StringUtil.isNull(account.getPassword())) || (md5Password.equals(account.getPassword())))
/*     */       {
/* 527 */         ret = true;
/*     */       }
/*     */     }
/*     */ 
/* 531 */     return ret;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> checkPaymentPrivilege(Long uid, Long houseId, String md5Password)
/*     */   {
/* 541 */     int code = 0;
/* 542 */     String msg = "";
/* 543 */     Map map = new HashMap();
/* 544 */     if (!isPayPasswordRight(houseId, md5Password)) {
/* 545 */       code = -1022;
/* 546 */       msg = "payment password err";
/*     */     }
/* 548 */     else if (!isPayAllowed(uid, houseId)) {
/* 549 */       code = -1027;
/* 550 */       msg = "payment not allowed";
/*     */     }
/*     */ 
/* 553 */     map.put("code", Integer.valueOf(code));
/* 554 */     map.put("msg", msg);
/* 555 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> payFee(Long uid, Long houseId, Long feeId)
/*     */   {
/* 567 */     double balance = getMoneyAccountBalance(houseId);
/* 568 */     double newBalance = balance;
/* 569 */     double savings = 0.0D;
/* 570 */     int resultCode = 0;
/* 571 */     String msg = "";
/* 572 */     Map map = new HashMap();
/* 573 */     Fee fee = this.feeMapper.getFeeByFeeId(feeId);
/* 574 */     if (fee == null) {
/* 575 */       resultCode = -10120;
/* 576 */       msg = "fee not found";
/*     */     }
/* 578 */     else if (balance < fee.getAmount().doubleValue()) {
/* 579 */       resultCode = -1021;
/* 580 */       msg = "余额不足";
/*     */     }
/*     */     else {
/* 583 */       Byte tradeType = fee.getFeeType().longValue() == 1L ? AppConst.TradeType.TYPE_FEE_PAY : AppConst.TradeType.TYPE_PACK_PAY;
/*     */ 
/* 585 */       changeAccountBalance(uid, houseId, fee.getAmount().doubleValue(), tradeType, fee.getFeeId().toString(), fee.getNote());
/*     */ 
/* 587 */       this.feeMapper.updateFeeStatus(feeId);
/* 588 */       newBalance = balance - fee.getAmount().doubleValue();
/*     */     }
/*     */ 
/* 591 */     map.put("code", Integer.valueOf(resultCode));
/* 592 */     map.put("msg", msg);
/* 593 */     map.put("newBalance", Double.valueOf(MaxMathUtil.getFormatedValue(newBalance, 2)));
/* 594 */     return map;
/*     */   }
/*     */ 
/*     */   public List<MoneyLogDTO> getMoneyRecord(Long uid, String recordType, Integer pagesize, Integer curPage)
/*     */   {
/* 608 */     Map paramMap = new HashMap();
/* 609 */     if ((pagesize == null) || (pagesize.intValue() < 1)) {
/* 610 */       pagesize = Integer.valueOf(20);
/*     */     }
/* 612 */     if ((curPage == null) || (curPage.intValue() < 1)) {
/* 613 */       curPage = Integer.valueOf(1);
/*     */     }
/* 615 */     int start = pagesize.intValue() * (curPage.intValue() - 1);
/* 616 */     paramMap.put("pagesize", pagesize);
/* 617 */     paramMap.put("start", Integer.valueOf(start));
/* 618 */     paramMap.put("uid", uid);
/* 619 */     paramMap.put("recordType", recordType);
/*     */ 
/* 621 */     return this.moneyLogMapper.getMoneyRecord(paramMap);
/*     */   }
/*     */ 
/*     */   public List<MoneyLogDTO> getHistoryRecord(Long uid, String recordType, String starttime, String endtime, Integer pagesize, Integer curPage)
/*     */   {
/* 637 */     Map paramMap = new HashMap();
/* 638 */     if ((pagesize == null) || (pagesize.intValue() < 1)) {
/* 639 */       pagesize = Integer.valueOf(20);
/*     */     }
/* 641 */     if ((curPage == null) || (curPage.intValue() < 1)) {
/* 642 */       curPage = Integer.valueOf(1);
/*     */     }
/* 644 */     int start = pagesize.intValue() * (curPage.intValue() - 1);
/* 645 */     paramMap.put("pagesize", pagesize);
/* 646 */     paramMap.put("start", Integer.valueOf(start));
/* 647 */     paramMap.put("uid", uid);
/* 648 */     paramMap.put("recordType", recordType);
/* 649 */     paramMap.put("starttime", starttime);
/* 650 */     paramMap.put("endtime", endtime);
/*     */ 
/* 652 */     return this.moneyLogMapper.getHistoryRecord(paramMap);
/*     */   }
/*     */ 
/*     */   public Double getMoneyTotal(Long uid, String recordType, String starttime, String endtime)
/*     */   {
/* 665 */     Map paramMap = new HashMap();
/* 666 */     paramMap.put("uid", uid);
/* 667 */     paramMap.put("recordType", recordType);
/* 668 */     paramMap.put("starttime", starttime);
/* 669 */     paramMap.put("endtime", endtime);
/* 670 */     Double total = this.moneyLogMapper.getMoneyTotal(paramMap);
/* 671 */     return Double.valueOf(total == null ? 0.0D : total.doubleValue());
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getMoneyRecordByParam(Long uid, String starttime, String endtime, Integer pagesize, Integer curPage)
/*     */   {
/* 685 */     Map paramMap = new HashMap();
/* 686 */     paramMap.put("pagesize", pagesize);
/* 687 */     paramMap.put("start", curPage);
/* 688 */     paramMap.put("uid", uid);
/* 689 */     paramMap.put("starttime", starttime);
/* 690 */     paramMap.put("endtime", endtime);
/*     */ 
/* 692 */     List list = this.moneyLogMapper.getMoneyRecordByParam(paramMap);
/* 693 */     Long count = this.moneyLogMapper.getMoneyCountByParam(paramMap);
/* 694 */     paramMap = new HashMap();
/* 695 */     paramMap.put("list", list);
/* 696 */     paramMap.put("count", count);
/* 697 */     return paramMap;
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void offLineCharge(Long uid, Long houseId, Double amount, Long siteId, Long operatorId, String operatorName)
/*     */   {
/* 711 */     MoneyAccount moneyAccount = getMoneyAccount(houseId);
/* 712 */     if (moneyAccount == null) {
/* 713 */       moneyAccount = new MoneyAccount();
/* 714 */       moneyAccount.setBalance(new BigDecimal(0));
/* 715 */       moneyAccount.setHouseId(houseId);
/* 716 */       moneyAccount.setOwnerUid(uid);
/* 717 */       moneyAccount.setRoomNo("");
/* 718 */       this.moneyLogMapper.createMoneyAccount(moneyAccount);
/*     */     }
/*     */ 
/* 721 */     String orderId = AppConst.OrderAccount.generateOrdrNo();
/*     */ 
/* 724 */     Map paramMap = new HashMap();
/* 725 */     paramMap.put("houseId", houseId);
/* 726 */     paramMap.put("amount", amount);
/* 727 */     this.moneyLogMapper.changeAccountBalance(paramMap);
/*     */ 
/* 729 */     createMoneyLog(uid, houseId, AppConst.TradeType.TYPE_CHARGE, amount.doubleValue(), orderId, "服务小站线下充值");
/*     */ 
/* 732 */     paramMap = new HashMap();
/* 733 */     paramMap.put("uid", siteId);
/* 734 */     paramMap.put("amount", Double.valueOf(0.0D - amount.doubleValue()));
/* 735 */     this.userMapper.updateUserMoney(paramMap);
/*     */ 
/* 737 */     createMoneyLog(siteId, Long.valueOf(0L), AppConst.TradeType.TYPE_TRANS, amount.doubleValue(), orderId, "服务小站线下充值 [" + uid + "]");
/*     */ 
/* 740 */     SysMoney sysMoney = this.sysMoneyLogMapper.loadSysMoney();
/* 741 */     if (sysMoney != null) {
/* 742 */       sysMoney.setMoney(Double.valueOf(sysMoney.getMoney().doubleValue() + amount.doubleValue()));
/* 743 */       this.sysMoneyLogMapper.update(sysMoney);
/*     */     } else {
/* 745 */       sysMoney = new SysMoney();
/* 746 */       sysMoney.setMoney(amount);
/* 747 */       this.sysMoneyLogMapper.insert(sysMoney);
/*     */     }
/*     */ 
/* 750 */     OrderAccount orderAccount = new OrderAccount();
/* 751 */     orderAccount.setOrderId(orderId);
/* 752 */     orderAccount.setTn(orderId);
/* 753 */     orderAccount.setAmount(amount);
/* 754 */     orderAccount.setOrderType(AppConst.OrderAccount.TYPE_OFFLINE_CHARGE);
/* 755 */     orderAccount.setOrderStatus(AppConst.OrderAccount.STATUS_SUC);
/* 756 */     orderAccount.setUid(uid);
/* 757 */     orderAccount.setHouseId(houseId);
/* 758 */     orderAccount.setOperatorId(operatorId);
/* 759 */     orderAccount.setOperatorName(operatorName);
/* 760 */     orderAccount.setNote("业主线下充值, 户号： " + houseId);
/* 761 */     this.sysMoneyLogMapper.insertOrderAccount(orderAccount);
/*     */ 
/* 764 */     SysMoneyLog sysMoneyLog = new SysMoneyLog();
/* 765 */     sysMoneyLog.setMoney(amount);
/* 766 */     sysMoneyLog.setType(AppConst.SysMoneyLog.TYPE_CHARGE);
/* 767 */     sysMoneyLog.setOrderId(orderId);
/* 768 */     sysMoneyLog.setNote("业主线下充值, 户号：" + houseId);
/* 769 */     this.sysMoneyLogMapper.insertMoneyLog(sysMoneyLog);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void Refund(Long uid, Long houseId, String publicAddress, Long operatorId, String operatorName)
/*     */     throws Exception
/*     */   {
/* 783 */     MoneyAccount moneyAccount = getMoneyAccount(houseId);
/*     */ 
/* 785 */     if (moneyAccount == null) {
/* 786 */       throw new Exception("业主资金账户不存在");
/*     */     }
/* 788 */     Double balance = Double.valueOf(moneyAccount.getBalance().doubleValue());
/*     */ 
/* 790 */     Map paramMap = new HashMap();
/* 791 */     paramMap.put("houseId", houseId);
/* 792 */     paramMap.put("amount", Double.valueOf(-balance.doubleValue()));
/* 793 */     this.moneyLogMapper.changeAccountBalance(paramMap);
/*     */ 
/* 796 */     String orderId = AppConst.OrderAccount.generateOrdrNo();
/* 797 */     OrderAccount orderAccount = new OrderAccount();
/* 798 */     orderAccount.setOrderId(orderId);
/* 799 */     orderAccount.setTn(orderId);
/* 800 */     orderAccount.setAmount(balance);
/* 801 */     orderAccount.setOrderType(AppConst.OrderAccount.TYPE_REFUND);
/* 802 */     orderAccount.setOrderStatus(AppConst.OrderAccount.STATUS_SUC);
/* 803 */     orderAccount.setUid(uid);
/* 804 */     orderAccount.setHouseId(houseId);
/* 805 */     orderAccount.setOperatorId(operatorId);
/* 806 */     orderAccount.setOperatorName(operatorName);
/* 807 */     orderAccount.setNote("业主线下退款, 户号：" + houseId);
/* 808 */     this.sysMoneyLogMapper.insertOrderAccount(orderAccount);
/*     */ 
/* 811 */     createMoneyLog(uid, houseId, AppConst.TradeType.TYPE_REFUND, balance.doubleValue(), orderId, "业主线下退款, 户号：" + houseId);
/*     */ 
/* 814 */     this.receiptService.createReceiptAccount(AppConst.REFUND_SHOP, balance.doubleValue(), publicAddress, orderId);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insertFee(Fee fee, Long operatorId, String operatorName, String publicAddress)
/*     */     throws Exception
/*     */   {
/* 830 */     Long houseId = fee.getHouseId();
/*     */ 
/* 832 */     String feeTypeName = "";
/* 833 */     Byte tradeType = null;
/* 834 */     if (fee.getFeeType().longValue() == 1L) {
/* 835 */       feeTypeName = "物业费";
/* 836 */       tradeType = AppConst.TradeType.TYPE_FEE_PAY;
/*     */     } else {
/* 838 */       feeTypeName = "停车费";
/* 839 */       tradeType = AppConst.TradeType.TYPE_PACK_PAY;
/*     */     }
/*     */ 
/* 842 */     MoneyAccount moneyAccount = getMoneyAccount(houseId);
/*     */ 
/* 844 */     if (moneyAccount == null) {
/* 845 */       throw new Exception("业主资金账户不存在");
/*     */     }
/* 847 */     if (moneyAccount.getBalance().doubleValue() * 100.0D < fee.getAmount().doubleValue() * 100.0D) {
/* 848 */       throw new Exception("业主余额不足");
/*     */     }
/* 850 */     Map paramMap = new HashMap();
/* 851 */     paramMap.put("houseId", houseId);
/* 852 */     paramMap.put("amount", Double.valueOf(-fee.getAmount().doubleValue()));
/* 853 */     this.moneyLogMapper.changeAccountBalance(paramMap);
/*     */ 
/* 855 */     String orderId = AppConst.OrderAccount.generateOrdrNo();
/* 856 */     OrderAccount orderAccount = new OrderAccount();
/* 857 */     orderAccount.setOrderId(orderId);
/* 858 */     orderAccount.setTn(orderId);
/* 859 */     orderAccount.setAmount(fee.getAmount());
/* 860 */     orderAccount.setOrderType(AppConst.OrderAccount.TYPE_FEE);
/* 861 */     orderAccount.setOrderStatus(AppConst.OrderAccount.STATUS_SUC);
/* 862 */     orderAccount.setUid(fee.getUid());
/* 863 */     orderAccount.setHouseId(houseId);
/* 864 */     orderAccount.setOperatorId(operatorId);
/* 865 */     orderAccount.setOperatorName(operatorName);
/* 866 */     orderAccount.setNote("缴纳" + feeTypeName + "，户号：" + houseId);
/* 867 */     this.sysMoneyLogMapper.insertOrderAccount(orderAccount);
/*     */ 
/* 870 */     createMoneyLog(fee.getUid(), houseId, tradeType, fee.getAmount().doubleValue(), orderId, "缴纳" + feeTypeName + "，户号：" + houseId);
/*     */ 
/* 872 */     Shop shop = this.shopMapper.getShopByCommunityId(fee.getCommunityId());
/* 873 */     if (shop == null) {
/* 874 */       throw new Exception("物业账户不存在");
/*     */     }
/* 876 */     this.receiptService.createReceiptAccount(shop.getShopId(), fee.getAmount().doubleValue(), publicAddress, orderId);
/*     */ 
/* 878 */     fee.setStatus(new Byte("0"));
/* 879 */     fee.setNote("缴纳" + feeTypeName + "，金额：" + fee.getAmount());
/* 880 */     this.feeMapper.insertFee(fee);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getConsumeRecord(Long communityId, String phoneNumber, String tradeType, String starttime, String endtime, Long houseId, Integer pagesize, Integer curPage)
/*     */   {
/* 897 */     Map paramMap = new HashMap();
/* 898 */     paramMap.put("communityId", communityId);
/* 899 */     paramMap.put("phoneNumber", phoneNumber);
/* 900 */     paramMap.put("tradeType", tradeType);
/* 901 */     paramMap.put("pagesize", pagesize);
/* 902 */     paramMap.put("start", curPage);
/* 903 */     paramMap.put("starttime", starttime);
/* 904 */     paramMap.put("endtime", endtime);
/* 905 */     paramMap.put("houseId", houseId);
/* 906 */     List list = this.moneyLogMapper.getConsumeRecordByParam(paramMap);
/* 907 */     Long count = this.moneyLogMapper.getConsumeCountByParam(paramMap);
/* 908 */     paramMap = new HashMap();
/* 909 */     paramMap.put("list", list);
/* 910 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 911 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public List<ConsumeLogDTO> getConsumeRecord(Long communityId, String phoneNumber, Long tradeType, String starttime, String endtime)
/*     */   {
/* 925 */     Map paramMap = new HashMap();
/* 926 */     paramMap.put("communityId", communityId);
/* 927 */     paramMap.put("phoneNumber", phoneNumber);
/* 928 */     paramMap.put("tradeType", tradeType);
/* 929 */     paramMap.put("starttime", starttime);
/* 930 */     paramMap.put("endtime", endtime);
/* 931 */     return this.moneyLogMapper.getConsumeRecordByParam(paramMap);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getOwnerWalletRecord(Long communityId, String phoneNumber, String starttime, String endtime, Long houseId, Integer pagesize, Integer curPage)
/*     */   {
/* 946 */     Map paramMap = new HashMap();
/* 947 */     paramMap.put("communityId", communityId);
/* 948 */     paramMap.put("phoneNumber", phoneNumber);
/* 949 */     paramMap.put("starttime", starttime);
/* 950 */     paramMap.put("endtime", endtime);
/* 951 */     paramMap.put("houseId", houseId);
/* 952 */     paramMap.put("pagesize", pagesize);
/* 953 */     paramMap.put("start", curPage);
/* 954 */     List list = this.moneyLogMapper.getOwnerWalletRecord(paramMap);
/* 955 */     Long count = this.moneyLogMapper.getOwnerWalletCount(paramMap);
/* 956 */     paramMap = new HashMap();
/* 957 */     paramMap.put("list", list);
/* 958 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 959 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getOrderAccountRecord(Long communityId, String phoneNumber, String starttime, String endtime, Long houseId, Integer pagesize, Integer curPage)
/*     */   {
/* 975 */     Map paramMap = new HashMap();
/* 976 */     paramMap.put("communityId", communityId);
/* 977 */     paramMap.put("phoneNumber", phoneNumber);
/* 978 */     paramMap.put("starttime", starttime);
/* 979 */     paramMap.put("endtime", endtime);
/* 980 */     paramMap.put("houseId", houseId);
/* 981 */     paramMap.put("pagesize", pagesize);
/* 982 */     paramMap.put("start", curPage);
/* 983 */     List list = this.sysMoneyLogMapper.getOrderAccountRecord(paramMap);
/* 984 */     Long count = this.sysMoneyLogMapper.getOrderAccountCount(paramMap);
/* 985 */     paramMap = new HashMap();
/* 986 */     paramMap.put("list", list);
/* 987 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 988 */     return paramMap;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.MoneyService
 * JD-Core Version:    0.6.0
 */