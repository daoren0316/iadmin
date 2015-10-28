/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Trade
/*    */ {
/*    */   private Long tradeId;
/*    */   private Double amount;
/*    */   private Date tradeTime;
/*    */   private String note;
/*    */ 
/*    */   public Long getTradeId()
/*    */   {
/* 31 */     return this.tradeId;
/*    */   }
/*    */ 
/*    */   public void setTradeId(Long tradeId)
/*    */   {
/* 36 */     this.tradeId = tradeId;
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
/*    */   public Date getTradeTime()
/*    */   {
/* 51 */     return this.tradeTime;
/*    */   }
/*    */ 
/*    */   public void setTradeTime(Date tradeTime)
/*    */   {
/* 56 */     this.tradeTime = tradeTime;
/*    */   }
/*    */ 
/*    */   public String getNote()
/*    */   {
/* 61 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note)
/*    */   {
/* 66 */     this.note = note;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Trade
 * JD-Core Version:    0.6.0
 */