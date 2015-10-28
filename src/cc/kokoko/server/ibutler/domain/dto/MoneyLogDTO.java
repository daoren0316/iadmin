/*    */ package cc.kokoko.server.ibutler.domain.dto;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class MoneyLogDTO
/*    */ {
/*    */   private Long id;
/*    */   private Date tradeTime;
/*    */   private Double amount;
/*    */   private String tradeType;
/*    */   private String publicAddress;
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
/*    */   public String getPublicAddress() {
/* 41 */     return this.publicAddress;
/*    */   }
/*    */ 
/*    */   public void setPublicAddress(String publicAddress) {
/* 45 */     this.publicAddress = publicAddress;
/*    */   }
/*    */ 
/*    */   public String getTradeType() {
/* 49 */     return this.tradeType;
/*    */   }
/*    */ 
/*    */   public void setTradeType(String tradeType) {
/* 53 */     this.tradeType = tradeType;
/*    */   }
/*    */ 
/*    */   public Double getAmount() {
/* 57 */     return this.amount;
/*    */   }
/*    */ 
/*    */   public void setAmount(Double amount) {
/* 61 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */   public Date getTradeTime() {
/* 65 */     return this.tradeTime;
/*    */   }
/*    */ 
/*    */   public void setTradeTime(Date tradeTime) {
/* 69 */     this.tradeTime = tradeTime;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.MoneyLogDTO
 * JD-Core Version:    0.6.0
 */