/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class ShopCard
/*    */ {
/*    */   private Long shopCardId;
/*    */   private String cardTitle;
/*    */   private Integer chargeAmount;
/*    */   private Long shopId;
/*    */   private BigDecimal discountRate;
/*    */   private String content;
/*    */   private String picUrl;
/*    */ 
/*    */   public Long getShopCardId()
/*    */   {
/* 19 */     return this.shopCardId;
/*    */   }
/*    */   public void setShopCardId(Long shopCardId) {
/* 22 */     this.shopCardId = shopCardId;
/*    */   }
/*    */   public String getCardTitle() {
/* 25 */     return this.cardTitle;
/*    */   }
/*    */   public void setCardTitle(String cardTitle) {
/* 28 */     this.cardTitle = cardTitle;
/*    */   }
/*    */   public Integer getChargeAmount() {
/* 31 */     return this.chargeAmount;
/*    */   }
/*    */   public void setChargeAmount(Integer chargeAmount) {
/* 34 */     this.chargeAmount = chargeAmount;
/*    */   }
/*    */   public Long getShopId() {
/* 37 */     return this.shopId;
/*    */   }
/*    */   public void setShopId(Long shopId) {
/* 40 */     this.shopId = shopId;
/*    */   }
/*    */   public BigDecimal getDiscountRate() {
/* 43 */     return this.discountRate;
/*    */   }
/*    */   public void setDiscountRate(BigDecimal discountRate) {
/* 46 */     this.discountRate = discountRate;
/*    */   }
/*    */   public String getContent() {
/* 49 */     return this.content;
/*    */   }
/*    */   public void setContent(String content) {
/* 52 */     this.content = content;
/*    */   }
/*    */   public String getPicUrl() {
/* 55 */     return this.picUrl;
/*    */   }
/*    */   public void setPicUrl(String picUrl) {
/* 58 */     this.picUrl = picUrl;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.ShopCard
 * JD-Core Version:    0.6.0
 */