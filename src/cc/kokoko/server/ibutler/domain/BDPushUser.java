/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BDPushUser
/*    */ {
/*    */   private Long uid;
/*    */   private String bdUserId;
/*    */   private String bdChannelId;
/*    */   private Date updateTime;
/*    */ 
/*    */   public Long getUid()
/*    */   {
/* 12 */     return this.uid;
/*    */   }
/*    */ 
/*    */   public void setUid(Long uid) {
/* 16 */     this.uid = uid;
/*    */   }
/*    */ 
/*    */   public String getBdUserId() {
/* 20 */     return this.bdUserId;
/*    */   }
/*    */ 
/*    */   public void setBdUserId(String bdUserId) {
/* 24 */     this.bdUserId = bdUserId;
/*    */   }
/*    */ 
/*    */   public String getBdChannelId() {
/* 28 */     return this.bdChannelId;
/*    */   }
/*    */ 
/*    */   public void setBdChannelId(String bdChannelId) {
/* 32 */     this.bdChannelId = bdChannelId;
/*    */   }
/*    */ 
/*    */   public Date getUpdateTime() {
/* 36 */     return this.updateTime;
/*    */   }
/*    */ 
/*    */   public void setUpdateTime(Date updateTime) {
/* 40 */     this.updateTime = updateTime;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.BDPushUser
 * JD-Core Version:    0.6.0
 */