/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class ECPCall
/*    */ {
/*    */   private Long id;
/*    */   private String call_from;
/*    */   private String call_to;
/*    */   private Date sendtime;
/*    */   private String mac_str;
/*    */   private String ip;
/*    */   private Byte status;
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 15 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 19 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getCall_from() {
/* 23 */     return this.call_from;
/*    */   }
/*    */ 
/*    */   public void setCall_from(String call_from) {
/* 27 */     this.call_from = call_from;
/*    */   }
/*    */ 
/*    */   public String getCall_to() {
/* 31 */     return this.call_to;
/*    */   }
/*    */ 
/*    */   public void setCall_to(String call_to) {
/* 35 */     this.call_to = call_to;
/*    */   }
/*    */ 
/*    */   public Date getSendtime() {
/* 39 */     return this.sendtime;
/*    */   }
/*    */ 
/*    */   public void setSendtime(Date sendtime) {
/* 43 */     this.sendtime = sendtime;
/*    */   }
/*    */ 
/*    */   public String getMac_str() {
/* 47 */     return this.mac_str;
/*    */   }
/*    */ 
/*    */   public void setMac_str(String mac_str) {
/* 51 */     this.mac_str = mac_str;
/*    */   }
/*    */ 
/*    */   public String getIp() {
/* 55 */     return this.ip;
/*    */   }
/*    */ 
/*    */   public void setIp(String ip) {
/* 59 */     this.ip = ip;
/*    */   }
/*    */ 
/*    */   public Byte getStatus() {
/* 63 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(Byte status) {
/* 67 */     this.status = status;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.ECPCall
 * JD-Core Version:    0.6.0
 */