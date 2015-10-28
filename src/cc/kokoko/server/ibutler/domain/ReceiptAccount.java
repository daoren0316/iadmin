/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ReceiptAccount
/*     */ {
/*     */   private Long id;
/*     */   private Long flag;
/*     */   private Date createTime;
/*     */   private Date receiptTime;
/*     */   private Double money;
/*     */   private Double receiptMoney;
/*     */   private Double surplusMoney;
/*     */   private String publicAddress;
/*     */   private Long shopId;
/*     */   private String tradeId;
/*     */   private String note;
/*     */   private String shopName;
/*     */   private String shopAddress;
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  57 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/*  61 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public Long getFlag() {
/*  65 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public void setFlag(Long flag) {
/*  69 */     this.flag = flag;
/*     */   }
/*     */ 
/*     */   public Date getCreateTime() {
/*  73 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/*  77 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public Date getReceiptTime() {
/*  81 */     return this.receiptTime;
/*     */   }
/*     */ 
/*     */   public void setReceiptTime(Date receiptTime) {
/*  85 */     this.receiptTime = receiptTime;
/*     */   }
/*     */ 
/*     */   public Double getMoney() {
/*  89 */     return this.money;
/*     */   }
/*     */ 
/*     */   public void setMoney(Double money) {
/*  93 */     this.money = money;
/*     */   }
/*     */ 
/*     */   public Double getReceiptMoney() {
/*  97 */     return this.receiptMoney;
/*     */   }
/*     */ 
/*     */   public void setReceiptMoney(Double receiptMoney) {
/* 101 */     this.receiptMoney = receiptMoney;
/*     */   }
/*     */ 
/*     */   public Double getSurplusMoney() {
/* 105 */     return this.surplusMoney;
/*     */   }
/*     */ 
/*     */   public void setSurplusMoney(Double surplusMoney) {
/* 109 */     this.surplusMoney = surplusMoney;
/*     */   }
/*     */ 
/*     */   public Long getShopId() {
/* 113 */     return this.shopId;
/*     */   }
/*     */ 
/*     */   public void setShopId(Long shopId) {
/* 117 */     this.shopId = shopId;
/*     */   }
/*     */ 
/*     */   public String getNote()
/*     */   {
/* 122 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/* 126 */     this.note = note;
/*     */   }
/*     */ 
/*     */   public String getPublicAddress() {
/* 130 */     return this.publicAddress;
/*     */   }
/*     */ 
/*     */   public void setPublicAddress(String publicAddress) {
/* 134 */     this.publicAddress = publicAddress;
/*     */   }
/*     */ 
/*     */   public String getTradeId() {
/* 138 */     return this.tradeId;
/*     */   }
/*     */ 
/*     */   public void setTradeId(String tradeId) {
/* 142 */     this.tradeId = tradeId;
/*     */   }
/*     */ 
/*     */   public String getShopName() {
/* 146 */     return this.shopName;
/*     */   }
/*     */ 
/*     */   public void setShopName(String shopName) {
/* 150 */     this.shopName = shopName;
/*     */   }
/*     */ 
/*     */   public String getShopAddress() {
/* 154 */     return this.shopAddress;
/*     */   }
/*     */ 
/*     */   public void setShopAddress(String shopAddress) {
/* 158 */     this.shopAddress = shopAddress;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.ReceiptAccount
 * JD-Core Version:    0.6.0
 */