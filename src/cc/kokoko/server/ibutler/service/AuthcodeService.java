/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.commons.util.AuthcodeGen;
/*    */ import cc.kokoko.server.commons.util.DateUtil;
/*    */ import cc.kokoko.server.commons.util.MaxMD5Util;
/*    */ import cc.kokoko.server.commons.util.MaxString;
/*    */ import cc.kokoko.server.ibutler.domain.Authcode;
/*    */ import cc.kokoko.server.ibutler.domain.SmsMT;
/*    */ import cc.kokoko.server.ibutler.persistence.AuthcodeMapper;
/*    */ import cc.kokoko.server.ibutler.persistence.SmsMapper;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Propagation;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("authcodeService")
/*    */ public class AuthcodeService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private AuthcodeMapper authcodeMapper;
/*    */ 
/*    */   @Autowired
/*    */   private SmsMapper smsMapper;
/*    */ 
/*    */   public String createAuthcode(Long uid, String userToken)
/*    */   {
/* 26 */     String str = MaxString.getUUID() + "@@" + uid + "_" + DateUtil.getNowTime();
/* 27 */     String authcodeStr = MaxMD5Util.toMD5(str);
/* 28 */     Authcode authcode = new Authcode();
/* 29 */     authcode.setAuthcodeStr(authcodeStr);
/* 30 */     authcode.setUid(uid);
/* 31 */     authcode.setUserToken(userToken);
/* 32 */     this.authcodeMapper.createAuthcode(authcode);
/* 33 */     return authcodeStr;
/*    */   }
/*    */ 
/*    */   public String createAuthcode(String phoneNumber)
/*    */   {
/* 39 */     String authcodeStr = AuthcodeGen.genPassword(6, 0);
/* 40 */     Authcode authcode = new Authcode();
/* 41 */     authcode.setPhoneNumber(phoneNumber);
/* 42 */     authcode.setAuthcodeStr(authcodeStr);
/* 43 */     this.authcodeMapper.createAuthcode(authcode);
/*    */ 
/* 45 */     SmsMT smsMT = new SmsMT();
/* 46 */     smsMT.setContent(authcodeStr + "（微管家验证码，请勿泄露），需要你进行身份校验。如非本人操作，请致电0571-86999823【微管家一卡通】");
/* 47 */     smsMT.setMobile(phoneNumber);
/* 48 */     this.smsMapper.createSmsMT(smsMT);
/* 49 */     this.smsMapper.createSmsMTBuffer(smsMT);
/* 50 */     return authcodeStr;
/*    */   }
/*    */ 
/*    */   public Boolean verrifyAuthcode(String phoneNumber, String authcodeStr) {
/* 54 */     Boolean ret = Boolean.valueOf(false);
/* 55 */     Authcode authcode = new Authcode();
/* 56 */     authcode.setPhoneNumber(phoneNumber);
/* 57 */     authcode.setAuthcodeStr(authcodeStr);
/* 58 */     Authcode ac = this.authcodeMapper.getAuthcodeByPhoneNumber(authcode);
/* 59 */     if (ac != null) {
/* 60 */       ret = Boolean.valueOf(true);
/*    */     }
/* 62 */     return ret;
/*    */   }
/*    */ 
/*    */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*    */   public void sendSMS(String phoneNumber, String content)
/*    */   {
/* 73 */     SmsMT smsMT = new SmsMT();
/* 74 */     smsMT.setContent(content);
/* 75 */     smsMT.setMobile(phoneNumber);
/* 76 */     this.smsMapper.createSmsMT(smsMT);
/* 77 */     this.smsMapper.createSmsMTBuffer(smsMT);
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.AuthcodeService
 * JD-Core Version:    0.6.0
 */