/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Order
/*     */ {
/*     */   private String orderId;
/*     */   private String content;
/*     */   private Date createedTime;
/*     */   private Byte paymentStatus;
/*     */   private Byte tradeStatus;
/*     */   private Long uid;
/*     */   private Long shopId;
/*     */   private Double amount;
/*     */   private Byte orderType;
/*     */   private String commodityTitle;
/*     */   private Long commodityId;
/*     */   private String picUrl;
/*     */   private Double savingsAmount;
/*     */ 
/*     */   public String getCommodityTitle()
/*     */   {
/*  54 */     return this.commodityTitle;
/*     */   }
/*     */ 
/*     */   public void setCommodityTitle(String commodityTitle) {
/*  58 */     this.commodityTitle = commodityTitle;
/*     */   }
/*     */ 
/*     */   public Long getCommodityId() {
/*  62 */     return this.commodityId;
/*     */   }
/*     */ 
/*     */   public void setCommodityId(Long commodityId) {
/*  66 */     this.commodityId = commodityId;
/*     */   }
/*     */ 
/*     */   public String getOrderId()
/*     */   {
/*  80 */     return this.orderId;
/*     */   }
/*     */ 
/*     */   public void setOrderId(String orderId) {
/*  84 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   public String getContent() {
/*  88 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/*  92 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public Date getCreateedTime() {
/*  96 */     return this.createedTime;
/*     */   }
/*     */ 
/*     */   public void setCreateedTime(Date createedTime) {
/* 100 */     this.createedTime = createedTime;
/*     */   }
/*     */ 
/*     */   public Byte getPaymentStatus() {
/* 104 */     return this.paymentStatus;
/*     */   }
/*     */ 
/*     */   public void setPaymentStatus(Byte paymentStatus) {
/* 108 */     this.paymentStatus = paymentStatus;
/*     */   }
/*     */ 
/*     */   public Byte getTradeStatus() {
/* 112 */     return this.tradeStatus;
/*     */   }
/*     */ 
/*     */   public void setTradeStatus(Byte tradeStatus) {
/* 116 */     this.tradeStatus = tradeStatus;
/*     */   }
/*     */ 
/*     */   public Long getUid() {
/* 120 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/* 124 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Long getShopId() {
/* 128 */     return this.shopId;
/*     */   }
/*     */ 
/*     */   public void setShopId(Long shopId) {
/* 132 */     this.shopId = shopId;
/*     */   }
/*     */ 
/*     */   public Double getAmount() {
/* 136 */     return this.amount;
/*     */   }
/*     */ 
/*     */   public void setAmount(Double amount) {
/* 140 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   public Byte getOrderType() {
/* 144 */     return this.orderType;
/*     */   }
/*     */ 
/*     */   public void setOrderType(Byte orderType) {
/* 148 */     this.orderType = orderType;
/*     */   }
/*     */ 
/*     */   public String getPicUrl() {
/* 152 */     return this.picUrl;
/*     */   }
/*     */ 
/*     */   public void setPicUrl(String picUrl) {
/* 156 */     this.picUrl = picUrl;
/*     */   }
/*     */ 
/*     */   public Double getSavingsAmount() {
/* 160 */     return this.savingsAmount;
/*     */   }
/*     */ 
/*     */   public void setSavingsAmount(Double savingsAmount) {
/* 164 */     this.savingsAmount = savingsAmount;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Order
 * JD-Core Version:    0.6.0
 */