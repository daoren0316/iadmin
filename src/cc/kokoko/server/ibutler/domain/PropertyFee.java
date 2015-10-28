/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class PropertyFee
/*    */ {
/*    */   private Long propertyFeeId;
/*    */   private Double amount;
/*    */   private Date beginDate;
/*    */   private Date endDate;
/*    */   private Byte status;
/*    */   private String note;
/*    */ 
/*    */   public Long getPropertyFeeId()
/*    */   {
/* 37 */     return this.propertyFeeId;
/*    */   }
/*    */ 
/*    */   public void setPropertyFeeId(Long propertyFeeId) {
/* 41 */     this.propertyFeeId = propertyFeeId;
/*    */   }
/*    */ 
/*    */   public Double getAmount()
/*    */   {
/* 47 */     return this.amount;
/*    */   }
/*    */ 
/*    */   public void setAmount(Double amount) {
/* 51 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */   public Date getBeginDate() {
/* 55 */     return this.beginDate;
/*    */   }
/*    */ 
/*    */   public void setBeginDate(Date beginDate) {
/* 59 */     this.beginDate = beginDate;
/*    */   }
/*    */ 
/*    */   public Date getEndDate() {
/* 63 */     return this.endDate;
/*    */   }
/*    */ 
/*    */   public void setEndDate(Date endDate) {
/* 67 */     this.endDate = endDate;
/*    */   }
/*    */ 
/*    */   public String getNote() {
/* 71 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note) {
/* 75 */     this.note = note;
/*    */   }
/*    */ 
/*    */   public Byte getStatus() {
/* 79 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(Byte status) {
/* 83 */     this.status = status;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.PropertyFee
 * JD-Core Version:    0.6.0
 */