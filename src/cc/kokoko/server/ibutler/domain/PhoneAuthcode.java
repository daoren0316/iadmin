/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class PhoneAuthcode
/*    */ {
/*    */   private String authcodeStr;
/*    */   private String phoneNumber;
/*    */   private Date createTime;
/*    */ 
/*    */   public String getAuthcodeStr()
/*    */   {
/* 11 */     return this.authcodeStr;
/*    */   }
/*    */ 
/*    */   public void setAuthcodeStr(String authcodeStr) {
/* 15 */     this.authcodeStr = authcodeStr;
/*    */   }
/*    */ 
/*    */   public Date getCreateTime() {
/* 19 */     return this.createTime;
/*    */   }
/*    */ 
/*    */   public void setCreateTime(Date createTime) {
/* 23 */     this.createTime = createTime;
/*    */   }
/*    */ 
/*    */   public String getPhoneNumber() {
/* 27 */     return this.phoneNumber;
/*    */   }
/*    */ 
/*    */   public void setPhoneNumber(String phoneNumber) {
/* 31 */     this.phoneNumber = phoneNumber;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.PhoneAuthcode
 * JD-Core Version:    0.6.0
 */