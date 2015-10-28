/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ public class RDOrder
/*     */ {
/*     */   private String tradeId;
/*     */   private Long uid;
/*     */   private Long houseId;
/*     */   private String cardNo;
/*     */   private Byte status;
/*     */   private Long postTime;
/*     */   private String shopId;
/*     */   private String posNo;
/*     */   private String posTradeNo;
/*     */   private String tradeTime;
/*     */   private int amount;
/*     */   private Byte tradeType;
/*     */   private int rawAmout;
/*     */ 
/*     */   public String getTradeId()
/*     */   {
/*  61 */     return this.tradeId;
/*     */   }
/*     */ 
/*     */   public void setTradeId(String tradeId) {
/*  65 */     this.tradeId = tradeId;
/*     */   }
/*     */ 
/*     */   public Long getUid() {
/*  69 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/*  73 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Long getHouseId() {
/*  77 */     return this.houseId;
/*     */   }
/*     */ 
/*     */   public void setHouseId(Long houseId) {
/*  81 */     this.houseId = houseId;
/*     */   }
/*     */ 
/*     */   public String getCardNo() {
/*  85 */     return this.cardNo;
/*     */   }
/*     */ 
/*     */   public void setCardNo(String cardNo) {
/*  89 */     this.cardNo = cardNo;
/*     */   }
/*     */ 
/*     */   public String getShopId() {
/*  93 */     return this.shopId;
/*     */   }
/*     */ 
/*     */   public void setShopId(String shopId) {
/*  97 */     this.shopId = shopId;
/*     */   }
/*     */ 
/*     */   public String getPosNo() {
/* 101 */     return this.posNo;
/*     */   }
/*     */ 
/*     */   public void setPosNo(String posNo) {
/* 105 */     this.posNo = posNo;
/*     */   }
/*     */ 
/*     */   public String getPosTradeNo() {
/* 109 */     return this.posTradeNo;
/*     */   }
/*     */ 
/*     */   public void setPosTradeNo(String posTradeNo) {
/* 113 */     this.posTradeNo = posTradeNo;
/*     */   }
/*     */ 
/*     */   public String getTradeTime() {
/* 117 */     return this.tradeTime;
/*     */   }
/*     */ 
/*     */   public void setTradeTime(String tradeTime) {
/* 121 */     this.tradeTime = tradeTime;
/*     */   }
/*     */ 
/*     */   public int getAmount() {
/* 125 */     return this.amount;
/*     */   }
/*     */ 
/*     */   public void setAmount(int amount) {
/* 129 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   public Long getPostTime() {
/* 133 */     return this.postTime;
/*     */   }
/*     */ 
/*     */   public void setPostTime(Long postTime) {
/* 137 */     this.postTime = postTime;
/*     */   }
/*     */ 
/*     */   public Byte getStatus() {
/* 141 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status) {
/* 145 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Byte getTradeType() {
/* 149 */     return this.tradeType;
/*     */   }
/*     */ 
/*     */   public void setTradeType(Byte tradeType) {
/* 153 */     this.tradeType = tradeType;
/*     */   }
/*     */ 
/*     */   public int getRawAmout() {
/* 157 */     return this.rawAmout;
/*     */   }
/*     */ 
/*     */   public void setRawAmout(int rawAmout) {
/* 161 */     this.rawAmout = rawAmout;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.RDOrder
 * JD-Core Version:    0.6.0
 */