/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ public class Email
/*    */ {
/*    */   private int mailId;
/*    */   private String subject;
/*    */   private String sendTo;
/*    */   private String content;
/*    */ 
/*    */   public int getMailId()
/*    */   {
/* 10 */     return this.mailId;
/*    */   }
/*    */ 
/*    */   public void setMailId(int mailId) {
/* 14 */     this.mailId = mailId;
/*    */   }
/*    */ 
/*    */   public String getSubject() {
/* 18 */     return this.subject;
/*    */   }
/*    */ 
/*    */   public void setSubject(String subject) {
/* 22 */     this.subject = subject;
/*    */   }
/*    */ 
/*    */   public String getSendTo() {
/* 26 */     return this.sendTo;
/*    */   }
/*    */ 
/*    */   public void setSendTo(String sendTo) {
/* 30 */     this.sendTo = sendTo;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 34 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 38 */     this.content = content;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Email
 * JD-Core Version:    0.6.0
 */