/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class CarFee
/*    */ {
/*    */   private Long carFeeId;
/*    */   private Double amount;
/*    */   private Date beginDate;
/*    */   private Date endDate;
/*    */   private Byte status;
/*    */   private String note;
/*    */ 
/*    */   public Double getAmount()
/*    */   {
/* 41 */     return this.amount;
/*    */   }
/*    */ 
/*    */   public void setAmount(Double amount) {
/* 45 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */   public Date getBeginDate() {
/* 49 */     return this.beginDate;
/*    */   }
/*    */ 
/*    */   public void setBeginDate(Date beginDate) {
/* 53 */     this.beginDate = beginDate;
/*    */   }
/*    */ 
/*    */   public Date getEndDate() {
/* 57 */     return this.endDate;
/*    */   }
/*    */ 
/*    */   public void setEndDate(Date endDate) {
/* 61 */     this.endDate = endDate;
/*    */   }
/*    */ 
/*    */   public String getNote() {
/* 65 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note) {
/* 69 */     this.note = note;
/*    */   }
/*    */ 
/*    */   public Byte getStatus() {
/* 73 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(Byte status) {
/* 77 */     this.status = status;
/*    */   }
/*    */ 
/*    */   public Long getCarFeeId() {
/* 81 */     return this.carFeeId;
/*    */   }
/*    */ 
/*    */   public void setCarFeeId(Long carFeeId) {
/* 85 */     this.carFeeId = carFeeId;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.CarFee
 * JD-Core Version:    0.6.0
 */