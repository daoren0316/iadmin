/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class UserDevice
/*    */ {
/*    */   private Long uid;
/*    */   private String deviceId;
/*    */   private Date postTime;
/*    */ 
/*    */   public Long getUid()
/*    */   {
/* 11 */     return this.uid;
/*    */   }
/*    */ 
/*    */   public void setUid(Long uid) {
/* 15 */     this.uid = uid;
/*    */   }
/*    */ 
/*    */   public String getDeviceId() {
/* 19 */     return this.deviceId;
/*    */   }
/*    */ 
/*    */   public void setDeviceId(String deviceId) {
/* 23 */     this.deviceId = deviceId;
/*    */   }
/*    */ 
/*    */   public Date getPostTime() {
/* 27 */     return this.postTime;
/*    */   }
/*    */ 
/*    */   public void setPostTime(Date postTime) {
/* 31 */     this.postTime = postTime;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.UserDevice
 * JD-Core Version:    0.6.0
 */