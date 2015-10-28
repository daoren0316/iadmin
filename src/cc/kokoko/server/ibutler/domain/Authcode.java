/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Authcode
/*    */ {
/*    */   private String authcodeStr;
/*    */   private Long uid;
/*    */   private Date createdTime;
/*    */   private String userToken;
/*    */   private String phoneNumber;
/*    */ 
/*    */   public String getAuthcodeStr()
/*    */   {
/* 12 */     return this.authcodeStr;
/*    */   }
/*    */   public void setAuthcodeStr(String authcodeStr) {
/* 15 */     this.authcodeStr = authcodeStr;
/*    */   }
/*    */   public Long getUid() {
/* 18 */     return this.uid;
/*    */   }
/*    */   public void setUid(Long uid) {
/* 21 */     this.uid = uid;
/*    */   }
/*    */   public Date getCreatedTime() {
/* 24 */     return this.createdTime;
/*    */   }
/*    */   public void setCreatedTime(Date createdTime) {
/* 27 */     this.createdTime = createdTime;
/*    */   }
/*    */   public String getUserToken() {
/* 30 */     return this.userToken;
/*    */   }
/*    */   public void setUserToken(String userToken) {
/* 33 */     this.userToken = userToken;
/*    */   }
/*    */   public String getPhoneNumber() {
/* 36 */     return this.phoneNumber;
/*    */   }
/*    */   public void setPhoneNumber(String phoneNumber) {
/* 39 */     this.phoneNumber = phoneNumber;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Authcode
 * JD-Core Version:    0.6.0
 */