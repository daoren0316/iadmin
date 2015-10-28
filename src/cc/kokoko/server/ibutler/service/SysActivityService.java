/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.MoneyLog;
import cc.kokoko.server.ibutler.domain.OrderAccount;
import cc.kokoko.server.ibutler.domain.SysActivityConfig;
import cc.kokoko.server.ibutler.domain.SysActivityLog;
import cc.kokoko.server.ibutler.domain.SysMoney;
import cc.kokoko.server.ibutler.domain.SysMoneyLog;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.domain.kdt.DatProtect;
import cc.kokoko.server.ibutler.persistence.MoneyLogMapper;
import cc.kokoko.server.ibutler.persistence.SysActivityMapper;
import cc.kokoko.server.ibutler.persistence.SysMoneyLogMapper;
import cc.kokoko.server.ibutler.persistence.UserMapper;
/*     */ 
/*     */ @Service("sysActivityService")
/*     */ public class SysActivityService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SysActivityMapper sysActivityMapper;
/*     */ 
/*     */   @Autowired
/*     */   private MoneyLogMapper moneyLogMapper;
/*     */ 
/*     */   @Autowired
/*     */   private SysMoneyLogMapper sysMoneyLogMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserMapper userMapper;
/*     */ 
/*     */   @Autowired
/*     */   private AuthcodeService authcodeService;
/*  37 */   private Logger log = Logger.getLogger(getClass());
/*     */ 
/*     */   public void backPayment(Long uid, Long houseId, Double account)
/*     */   {
/*  47 */     backMoneyActivity(uid, houseId, account);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void backMoneyActivity(Long uid, Long houseId, Double account)
/*     */   {
/*     */     try
/*     */     {
/*  60 */       Map paramMap = new HashMap();
/*  61 */       paramMap.put("flag", Integer.valueOf(1));
/*     */ 
/*  63 */       SysActivityConfig sysActivityConfig = this.sysActivityMapper.loadSysActivity(paramMap);
/*  64 */       if (sysActivityConfig != null)
/*     */       {
/*  66 */         Double money = sysActivityConfig.getMoney();
/*  67 */         if ((money == null) || (money.doubleValue() <= 0.0D)) {
/*  68 */           throw new Exception("参数获取失败");
/*     */         }
/*  70 */         Double amount = this.sysActivityMapper.getBackMoneyTotalByHouseId(houseId);
/*  71 */         amount = Double.valueOf(amount == null ? 0.0D : amount.doubleValue());
/*     */ 
/*  73 */         if (amount.doubleValue() < money.doubleValue())
/*     */         {
/*  75 */           double hasAmount = money.doubleValue() - amount.doubleValue();
/*     */ 
/*  77 */           account = Double.valueOf(account.doubleValue() >= hasAmount ? hasAmount : account.doubleValue());
/*     */ 
/*  79 */           SysActivityLog sysActivityLog = new SysActivityLog();
/*  80 */           sysActivityLog.setAmount(account);
/*  81 */           sysActivityLog.setBack(true);
/*  82 */           sysActivityLog.setHouseId(houseId);
/*  83 */           sysActivityLog.setUid(uid);
/*  84 */           this.sysActivityMapper.insertSysActivityLog(sysActivityLog);
/*     */ 
/*  87 */           String orderId = AppConst.OrderAccount.generateOrdrNo();
/*     */ 
/*  90 */           paramMap = new HashMap();
/*  91 */           paramMap.put("houseId", houseId);
/*  92 */           paramMap.put("amount", account);
/*  93 */           this.moneyLogMapper.changeAccountBalance(paramMap);
/*     */ 
/*  96 */           createMoneyLog(uid, houseId, AppConst.TradeType.TYPE_TRANS_IN, account.doubleValue(), orderId, "[官网活动]你消费，我买单活动");
/*     */ 
/*  99 */           SysMoney sysMoney = this.sysMoneyLogMapper.loadSysMoney();
/* 100 */           if (sysMoney != null) {
/* 101 */             sysMoney.setMoney(Double.valueOf(sysMoney.getMoney().doubleValue() + account.doubleValue()));
/* 102 */             this.sysMoneyLogMapper.update(sysMoney);
/*     */           } else {
/* 104 */             sysMoney = new SysMoney();
/* 105 */             sysMoney.setMoney(account);
/* 106 */             this.sysMoneyLogMapper.insert(sysMoney);
/*     */           }
/*     */ 
/* 109 */           OrderAccount orderAccount = new OrderAccount();
/* 110 */           orderAccount.setOrderId(orderId);
/* 111 */           orderAccount.setTn(orderId);
/* 112 */           orderAccount.setAmount(account);
/* 113 */           orderAccount.setOrderType(AppConst.OrderAccount.TYPE_OFFLINE_CHARGE);
/* 114 */           orderAccount.setOrderStatus(AppConst.OrderAccount.STATUS_SUC);
/* 115 */           orderAccount.setUid(uid);
/* 116 */           orderAccount.setHouseId(houseId);
/* 117 */           orderAccount.setNote("[官网活动]你消费，我买单活动，业主消费金额返还，户号： " + houseId);
/* 118 */           this.sysMoneyLogMapper.insertOrderAccount(orderAccount);
/*     */ 
/* 121 */           SysMoneyLog sysMoneyLog = new SysMoneyLog();
/* 122 */           sysMoneyLog.setMoney(account);
/* 123 */           sysMoneyLog.setType(AppConst.SysMoneyLog.TYPE_CHARGE);
/* 124 */           sysMoneyLog.setOrderId(orderId);
/* 125 */           sysMoneyLog.setNote("[官网活动]你消费，我买单活动，业主消费金额返还，户号： " + houseId);
/* 126 */           this.sysMoneyLogMapper.insertMoneyLog(sysMoneyLog);
/*     */ 
/* 129 */           User user = this.userMapper.getUserByUid(uid);
/* 130 */           if (user == null) {
/* 131 */             throw new Exception("用户信息获取失败");
/*     */           }
/* 133 */           String content = "感谢您参与你消费，我买单活动，本次返还金额：" + account + "元。如有疑问，请致电0571-86999823【微管家一卡通】";
/* 134 */           this.authcodeService.sendSMS(user.getPhoneNumber(), content);
/*     */         }
/*     */       } else {
/* 137 */         throw new Exception("未找到可用的活动配置");
/*     */       }
/*     */     } catch (Exception e) {
/* 139 */       this.log.error("backMoneyActivity error " + e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void createMoneyLog(Long uid, Long houseId, Byte tradeType, double amount, String orderId, String note)
/*     */   {
/* 155 */     MoneyLog moneyLog = new MoneyLog();
/* 156 */     moneyLog.setOrderId(orderId);
/* 157 */     moneyLog.setUid(uid);
/* 158 */     moneyLog.setTradeType(tradeType);
/* 159 */     moneyLog.setAmount(Double.valueOf(amount));
/* 160 */     moneyLog.setNote(note);
/* 161 */     moneyLog.setHouseId(houseId);
/* 162 */     this.moneyLogMapper.createMoneyLog(moneyLog);
/*     */   }
/*     */ 
/*     */   public void scanActivityIStart()
/*     */   {
/* 169 */     Map paramMap = new HashMap();
/* 170 */     paramMap.put("flag", Integer.valueOf(0));
/*     */ 
/* 172 */     SysActivityConfig sysActivityConfig = this.sysActivityMapper.loadSysActivity(paramMap);
/* 173 */     if (sysActivityConfig != null) {
/* 174 */       Date startTime = sysActivityConfig.getStartTime();
/*     */ 
/* 176 */       Calendar cal = Calendar.getInstance();
/* 177 */       cal.setTime(startTime);
/* 178 */       int result = cal.getTime().compareTo(new Date());
/* 179 */       if (result <= 0)
/*     */       {
/* 181 */         updateSysActivityFlag(Long.valueOf(1L), sysActivityConfig.getId());
/*     */ 
/* 184 */         this.log.info("[官网活动]你消费，我买单活动开始啦~~~ ");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void scanActivityIEnd()
/*     */   {
/* 195 */     Map paramMap = new HashMap();
/* 196 */     paramMap.put("flag", Integer.valueOf(1));
/*     */ 
/* 198 */     SysActivityConfig sysActivityConfig = this.sysActivityMapper.loadSysActivity(paramMap);
/* 199 */     if (sysActivityConfig != null) {
/* 200 */       Date endTime = sysActivityConfig.getEndTime();
/*     */ 
/* 202 */       Calendar cal = Calendar.getInstance();
/* 203 */       cal.setTime(endTime);
/* 204 */       int result = cal.getTime().compareTo(new Date());
/* 205 */       if (result <= 0)
/*     */       {
/* 207 */         updateSysActivityFlag(Long.valueOf(2L), sysActivityConfig.getId());
/*     */ 
/* 210 */         this.log.info("[官网活动]你消费，我买单活动圆满结束~~~ ");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getSysActivityRecord(String startTime, String endTime, Integer pagesize, Integer curPage)
/*     */   {
/* 225 */     Map paramMap = new HashMap();
/* 226 */     paramMap.put("starttime", startTime);
/* 227 */     paramMap.put("endtime", endTime);
/* 228 */     paramMap.put("pagesize", pagesize);
/* 229 */     paramMap.put("start", curPage);
/* 230 */     List list = this.sysActivityMapper.getSysActivityRecord(paramMap);
/* 231 */     Long count = this.sysActivityMapper.getSysActivityCount(paramMap);
/* 232 */     paramMap = new HashMap();
/* 233 */     paramMap.put("list", list);
/* 234 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 235 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public void insert(SysActivityConfig sysActivityConfig)
/*     */   {
/* 244 */     this.sysActivityMapper.insert(sysActivityConfig);
/*     */   }
/*     */ 
/*     */   public void updateSysActivityFlag(Long flag, Long id)
/*     */   {
/* 254 */     Map paramMap = new HashMap();
/* 255 */     paramMap.put("flag", flag);
/* 256 */     paramMap.put("id", id);
/* 257 */     this.sysActivityMapper.updateSysActivityFlag(paramMap);
/*     */   }
/*     */ 
/*     */   public SysActivityConfig getSysActivityConfigById(Long id)
/*     */   {
/* 267 */     return this.sysActivityMapper.getSysActivityConfigById(id);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void allotmentToSite()
/*     */   {
/* 276 */     List<User> userList = this.userMapper.getUserByType(Long.valueOf(AppConst.UserType.SITE.longValue()));
/* 277 */     if (userList.size() > 0)
/*     */     {
/* 279 */       for (User user : userList) {
/* 280 */         Double money = user.getMoney();
/* 281 */         money = Double.valueOf(money == null ? 30000.0D : 30000.0D - money.doubleValue());
/*     */ 
/* 283 */         Map paramMap = new HashMap();
/* 284 */         paramMap.put("amount", money);
/* 285 */         paramMap.put("uid", user.getUid());
/* 286 */         this.userMapper.updateUserMoney(paramMap);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getSysActivityLogRecord(String startTime, String endTime, Long houseId, Integer pagesize, Integer curPage)
/*     */   {
/* 301 */     Map paramMap = new HashMap();
/* 302 */     paramMap.put("starttime", startTime);
/* 303 */     paramMap.put("endtime", endTime);
/* 304 */     paramMap.put("houseId", houseId);
/* 305 */     paramMap.put("pagesize", pagesize);
/* 306 */     paramMap.put("start", curPage);
/* 307 */     List list = this.sysActivityMapper.getSysActivityLogRecord(paramMap);
/* 308 */     Long count = this.sysActivityMapper.getSysActivityLogCount(paramMap);
/* 309 */     paramMap = new HashMap();
/* 310 */     paramMap.put("list", list);
/* 311 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 312 */     return paramMap;
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void export(Sheet sheet)
/*     */   {
/* 323 */     int rows = sheet.getRows();
/*     */ 
/* 325 */     int columns = sheet.getColumns();
/*     */ 
/* 327 */     int startRows = 1;
/*     */ 
/* 329 */     while (startRows < rows)
/*     */     {
/* 331 */       Cell nameCell = sheet.getCell(0, startRows);
/* 332 */       String protectName = nameCell.getContents();
/*     */ 
/* 334 */       Cell telCell = sheet.getCell(1, startRows);
/* 335 */       String telephone = telCell.getContents();
/*     */ 
/* 337 */       Cell orderCell = sheet.getCell(2, startRows);
/* 338 */       String protectOrder = orderCell.getContents();
/*     */ 
/* 340 */       Cell timeCell = sheet.getCell(3, startRows);
/* 341 */       String time = timeCell.getContents();
/*     */ 
/* 343 */       if ((protectOrder != null) && (protectOrder != ""))
/*     */       {
/* 345 */         DatProtect datProtect = new DatProtect();
/* 346 */         datProtect.setProtectName(protectName);
/* 347 */         datProtect.setProtectOrder(protectOrder);
/* 348 */         datProtect.setTelephone(telephone);
/* 349 */         datProtect.setTime(time);
/* 350 */         datProtect.setFlag(Boolean.valueOf(false));
/*     */ 
/* 352 */         String msg = "亲，给爱情下个套首年保险保单已生效，保单号码为" + protectOrder + "，可登陆中国人寿官网http://www.gdapp1.e-chinalife.com/tby/查询。" + "后续保险ALIFETIME平台每年将自动为你续保，请于每年" + time + "登陆官网查看。如有疑问请致电：4008-520-787";
/*     */ 
/* 354 */         datProtect.setNote(msg);
/*     */ 
/* 356 */         this.sysActivityMapper.insertDatProtect(datProtect);
/*     */ 
/* 358 */         this.authcodeService.sendSMS(telephone, msg);
/*     */       }
/* 360 */       startRows++;
/*     */     }
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.SysActivityService
 * JD-Core Version:    0.6.0
 */