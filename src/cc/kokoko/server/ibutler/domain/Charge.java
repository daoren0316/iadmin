/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Charge
/*    */ {
/*    */   private Long chargeId;
/*    */   private Double amount;
/*    */   private Date chargeTime;
/*    */   private String note;
/*    */ 
/*    */   public Long getChargeId()
/*    */   {
/* 31 */     return this.chargeId;
/*    */   }
/*    */ 
/*    */   public void setChargeId(Long chargeId)
/*    */   {
/* 36 */     this.chargeId = chargeId;
/*    */   }
/*    */ 
/*    */   public Double getAmount()
/*    */   {
/* 41 */     return this.amount;
/*    */   }
/*    */ 
/*    */   public void setAmount(Double amount)
/*    */   {
/* 46 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */   public String getNote()
/*    */   {
/* 51 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note)
/*    */   {
/* 56 */     this.note = note;
/*    */   }
/*    */ 
/*    */   public Date getChargeTime()
/*    */   {
/* 61 */     return this.chargeTime;
/*    */   }
/*    */ 
/*    */   public void setChargeTime(Date chargeTime)
/*    */   {
/* 66 */     this.chargeTime = chargeTime;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Charge
 * JD-Core Version:    0.6.0
 */