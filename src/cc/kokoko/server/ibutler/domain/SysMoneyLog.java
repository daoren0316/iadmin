/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SysMoneyLog
/*    */ {
/*    */   private Long id;
/*    */   private Double money;
/*    */   private Byte type;
/*    */   private String orderId;
/*    */   private Date createdTime;
/*    */   private String note;
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 33 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 37 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Double getMoney() {
/* 41 */     return this.money;
/*    */   }
/*    */ 
/*    */   public void setMoney(Double money) {
/* 45 */     this.money = money;
/*    */   }
/*    */ 
/*    */   public Byte getType() {
/* 49 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(Byte type) {
/* 53 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public String getOrderId() {
/* 57 */     return this.orderId;
/*    */   }
/*    */ 
/*    */   public void setOrderId(String orderId) {
/* 61 */     this.orderId = orderId;
/*    */   }
/*    */ 
/*    */   public Date getCreatedTime() {
/* 65 */     return this.createdTime;
/*    */   }
/*    */ 
/*    */   public void setCreatedTime(Date createdTime) {
/* 69 */     this.createdTime = createdTime;
/*    */   }
/*    */ 
/*    */   public String getNote() {
/* 73 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note) {
/* 77 */     this.note = note;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 82 */     return "SysMoneyLog{id=" + this.id + ", money=" + this.money + ", type=" + this.type + ", orderId=" + this.orderId + ", createdTime=" + this.createdTime + ", note='" + this.note + '\'' + '}';
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.SysMoneyLog
 * JD-Core Version:    0.6.0
 */