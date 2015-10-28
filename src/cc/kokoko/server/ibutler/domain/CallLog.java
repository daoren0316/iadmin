/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class CallLog
/*    */ {
/*    */   private Long callId;
/*    */   private String nickname;
/*    */   private Date callTime;
/*    */   private Byte status;
/*    */ 
/*    */   public Long getCallId()
/*    */   {
/* 23 */     return this.callId;
/*    */   }
/*    */   public void setCallId(Long callId) {
/* 26 */     this.callId = callId;
/*    */   }
/*    */   public String getNickname() {
/* 29 */     return this.nickname;
/*    */   }
/*    */   public void setNickname(String nickname) {
/* 32 */     this.nickname = nickname;
/*    */   }
/*    */   public Date getCallTime() {
/* 35 */     return this.callTime;
/*    */   }
/*    */   public void setCallTime(Date callTime) {
/* 38 */     this.callTime = callTime;
/*    */   }
/*    */   public Byte getStatus() {
/* 41 */     return this.status;
/*    */   }
/*    */   public void setStatus(Byte status) {
/* 44 */     this.status = status;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.CallLog
 * JD-Core Version:    0.6.0
 */