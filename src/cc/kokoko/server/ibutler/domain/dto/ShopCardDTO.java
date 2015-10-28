/*     */ package cc.kokoko.server.ibutler.domain.dto;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ShopCardDTO
/*     */ {
/*     */   private String cardTitle;
/*     */   private Long shopCardId;
/*     */   private String picUrl;
/*     */   private Integer chargeAmount;
/*     */   private BigDecimal discountRate;
/*     */   private Long shopId;
/*     */   private Map<String, String> cardDetail;
/*     */ 
/*     */   public String getCardTitle()
/*     */   {
/*  50 */     return this.cardTitle;
/*     */   }
/*     */ 
/*     */   public void setCardTitle(String cardTitle) {
/*  54 */     this.cardTitle = cardTitle;
/*     */   }
/*     */ 
/*     */   public Long getShopCardId() {
/*  58 */     return this.shopCardId;
/*     */   }
/*     */ 
/*     */   public void setShopCardId(Long shopCardId) {
/*  62 */     this.shopCardId = shopCardId;
/*     */   }
/*     */ 
/*     */   public Integer getChargeAmount() {
/*  66 */     return this.chargeAmount;
/*     */   }
/*     */ 
/*     */   public void setChargeAmount(Integer chargeAmount) {
/*  70 */     this.chargeAmount = chargeAmount;
/*     */   }
/*     */ 
/*     */   public BigDecimal getDiscountRate() {
/*  74 */     return this.discountRate;
/*     */   }
/*     */ 
/*     */   public void setDiscountRate(BigDecimal discountRate) {
/*  78 */     this.discountRate = discountRate;
/*     */   }
/*     */ 
/*     */   public Long getShopId() {
/*  82 */     return this.shopId;
/*     */   }
/*     */ 
/*     */   public void setShopId(Long shopId) {
/*  86 */     this.shopId = shopId;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getCardDetail() {
/*  90 */     return this.cardDetail;
/*     */   }
/*     */ 
/*     */   public void setCardDetail(Map<String, String> cardDetail) {
/*  94 */     this.cardDetail = cardDetail;
/*     */   }
/*     */ 
/*     */   public String getPicUrl() {
/*  98 */     return this.picUrl;
/*     */   }
/*     */ 
/*     */   public void setPicUrl(String picUrl) {
/* 102 */     this.picUrl = picUrl;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.ShopCardDTO
 * JD-Core Version:    0.6.0
 */