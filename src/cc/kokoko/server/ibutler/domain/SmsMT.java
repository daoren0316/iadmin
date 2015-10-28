/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SmsMT
/*    */ {
/*    */   private Long id;
/*    */   private String mobile;
/*    */   private String content;
/*    */   private Date sendtime;
/*    */   private Byte status;
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 13 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 17 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getMobile() {
/* 21 */     return this.mobile;
/*    */   }
/*    */ 
/*    */   public void setMobile(String mobile) {
/* 25 */     this.mobile = mobile;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 29 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 33 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public Date getSendtime() {
/* 37 */     return this.sendtime;
/*    */   }
/*    */ 
/*    */   public void setSendtime(Date sendtime) {
/* 41 */     this.sendtime = sendtime;
/*    */   }
/*    */ 
/*    */   public Byte getStatus() {
/* 45 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(Byte status) {
/* 49 */     this.status = status;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.SmsMT
 * JD-Core Version:    0.6.0
 */