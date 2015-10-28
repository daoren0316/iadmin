/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class MoneyLog
/*     */ {
/*     */   private Long id;
/*     */   private String orderId;
/*     */   private Double amount;
/*     */   private Date tradeTime;
/*     */   private Byte tradeType;
/*     */   private Long uid;
/*     */   private Long houseId;
/*     */   private Long shopId;
/*     */   private String publicAddress;
/*     */   private String note;
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  48 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/*  52 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getOrderId() {
/*  56 */     return this.orderId;
/*     */   }
/*     */ 
/*     */   public void setOrderId(String orderId) {
/*  60 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   public Double getAmount() {
/*  64 */     return this.amount;
/*     */   }
/*     */ 
/*     */   public void setAmount(Double amount) {
/*  68 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   public Date getTradeTime() {
/*  72 */     return this.tradeTime;
/*     */   }
/*     */ 
/*     */   public void setTradeTime(Date tradeTime) {
/*  76 */     this.tradeTime = tradeTime;
/*     */   }
/*     */ 
/*     */   public Byte getTradeType() {
/*  80 */     return this.tradeType;
/*     */   }
/*     */ 
/*     */   public void setTradeType(Byte tradeType) {
/*  84 */     this.tradeType = tradeType;
/*     */   }
/*     */ 
/*     */   public Long getUid() {
/*  88 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/*  92 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Long getHouseId() {
/*  96 */     return this.houseId;
/*     */   }
/*     */ 
/*     */   public void setHouseId(Long houseId) {
/* 100 */     this.houseId = houseId;
/*     */   }
/*     */ 
/*     */   public Long getShopId() {
/* 104 */     return this.shopId;
/*     */   }
/*     */ 
/*     */   public void setShopId(Long shopId) {
/* 108 */     this.shopId = shopId;
/*     */   }
/*     */ 
/*     */   public String getPublicAddress() {
/* 112 */     return this.publicAddress;
/*     */   }
/*     */ 
/*     */   public void setPublicAddress(String publicAddress) {
/* 116 */     this.publicAddress = publicAddress;
/*     */   }
/*     */ 
/*     */   public String getNote() {
/* 120 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/* 124 */     this.note = note;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.MoneyLog
 * JD-Core Version:    0.6.0
 */