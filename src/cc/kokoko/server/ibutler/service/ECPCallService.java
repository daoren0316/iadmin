/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import cc.kokoko.server.commons.util.StringUtil;
/*     */ import cc.kokoko.server.ibutler.domain.CallLog;
/*     */ import cc.kokoko.server.ibutler.domain.ECPCall;
/*     */ import cc.kokoko.server.ibutler.persistence.ECPCallMapper;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("ecpCallService")
/*     */ public class ECPCallService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ECPCallMapper ecpCallMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*  29 */   private static Logger log = LoggerFactory.getLogger(ECPCallService.class);
/*     */ 
/*  33 */   @Transactional
/*     */   public void createCall(String callFrom, String callTo, String ip) { String macStr = "dfgh-khjg-ui78-890g";
/*  34 */     ECPCall ecpCall = new ECPCall();
/*  35 */     ecpCall.setCall_from(callFrom);
/*  36 */     ecpCall.setCall_to(callTo);
/*  37 */     ecpCall.setIp(ip);
/*  38 */     ecpCall.setMac_str(macStr);
/*  39 */     this.ecpCallMapper.addECPCallBuffer(ecpCall);
/*  40 */     this.ecpCallMapper.addECPCall(ecpCall); }
/*     */ 
/*     */   public void createCall(Long fromUid, String callFrom, Long shopId, String ip)
/*     */   {
/*  44 */     String callTo = this.userService.getPhoneNumberByUid(shopId);
/*  45 */     if ((!StringUtil.isNull(callTo)) && (!StringUtil.isNull(callFrom))) {
/*  46 */       callFrom = StringUtil.replaceSpecialChar(callFrom);
/*  47 */       callTo = StringUtil.replaceSpecialChar(callTo);
/*     */ 
/*  49 */       createCall(callFrom, callTo, ip);
/*  50 */       createCallLog(fromUid, shopId, callFrom, callTo);
/*     */     } else {
/*  52 */       log.error("[createCall] callFrom=" + callFrom + ", shopId=" + shopId + ", callTo=" + callTo);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void createCallLog(Long fromUid, Long toUid, String callFrom, String callTo)
/*     */   {
/*  59 */     Map map = new HashMap();
/*  60 */     map.put("fromUid", fromUid);
/*  61 */     map.put("toUid", toUid);
/*  62 */     map.put("callFrom", callFrom);
/*  63 */     map.put("callTo", callTo);
/*  64 */     this.ecpCallMapper.addCallLog(map);
/*     */   }
/*     */ 
/*     */   public int callBack(Long shopId, Long callId, String ip)
/*     */   {
/*  73 */     int code = 0;
/*  74 */     Map map = this.ecpCallMapper.getCallLogById(callId);
/*  75 */     if ((map == null) || (map.size() < 1)) {
/*  76 */       return -1029;
/*     */     }
/*  78 */     Long toUid = (Long)map.get("toUid");
/*  79 */     if (!shopId.equals(toUid)) {
/*  80 */       return -1030;
/*     */     }
/*  82 */     createCall("" + map.get("callTo"), "" + map.get("callFrom"), ip);
/*  83 */     this.ecpCallMapper.updateCallLogById(callId);
/*  84 */     return code;
/*     */   }
/*     */ 
/*     */   public List<CallLog> getCallLogList(Long uid, Integer pagesize, Integer curPage)
/*     */   {
/*  89 */     Map paramMap = new HashMap();
/*  90 */     if ((pagesize == null) || (pagesize.intValue() < 1)) {
/*  91 */       pagesize = Integer.valueOf(20);
/*     */     }
/*  93 */     if ((curPage == null) || (curPage.intValue() < 1)) {
/*  94 */       curPage = Integer.valueOf(1);
/*     */     }
/*  96 */     int start = pagesize.intValue() * (curPage.intValue() - 1);
/*  97 */     paramMap.put("pagesize", pagesize);
/*  98 */     paramMap.put("start", Integer.valueOf(start));
/*  99 */     paramMap.put("uid", uid);
/* 100 */     return this.ecpCallMapper.getCallLogList(paramMap);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.ECPCallService
 * JD-Core Version:    0.6.0
 */