/*     */ package cc.kokoko.server.ibutler.domain.dto;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ShopDTO
/*     */ {
/*     */   private Long shopId;
/*     */   private String shopName;
/*     */   private String shopAddress;
/*     */   private String logoUrl;
/*     */   private Byte shopType;
/*     */   private Date createdTime;
/*     */   private String shopDesc;
/*     */   private String phoneNumber;
/*     */   private Integer shopDistance;
/*     */   private Boolean isFav;
/*     */   private List<String> picUrlList;
/*     */   private String signature;
/*     */   private int discount;
/*     */ 
/*     */   public Long getShopId()
/*     */   {
/*  76 */     return this.shopId;
/*     */   }
/*     */ 
/*     */   public void setShopId(Long shopId) {
/*  80 */     this.shopId = shopId;
/*     */   }
/*     */ 
/*     */   public String getShopName() {
/*  84 */     return this.shopName;
/*     */   }
/*     */ 
/*     */   public void setShopName(String shopName) {
/*  88 */     this.shopName = shopName;
/*     */   }
/*     */ 
/*     */   public String getShopAddress() {
/*  92 */     return this.shopAddress;
/*     */   }
/*     */ 
/*     */   public void setShopAddress(String shopAddress) {
/*  96 */     this.shopAddress = shopAddress;
/*     */   }
/*     */ 
/*     */   public String getLogoUrl() {
/* 100 */     return this.logoUrl;
/*     */   }
/*     */ 
/*     */   public void setLogoUrl(String logoUrl) {
/* 104 */     this.logoUrl = logoUrl;
/*     */   }
/*     */ 
/*     */   public Byte getShopType() {
/* 108 */     return this.shopType;
/*     */   }
/*     */ 
/*     */   public void setShopType(Byte shopType) {
/* 112 */     this.shopType = shopType;
/*     */   }
/*     */ 
/*     */   public Date getCreatedTime() {
/* 116 */     return this.createdTime;
/*     */   }
/*     */ 
/*     */   public void setCreatedTime(Date createdTime) {
/* 120 */     this.createdTime = createdTime;
/*     */   }
/*     */ 
/*     */   public String getShopDesc() {
/* 124 */     return this.shopDesc;
/*     */   }
/*     */ 
/*     */   public void setShopDesc(String shopDesc) {
/* 128 */     this.shopDesc = shopDesc;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/* 132 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/* 136 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public Integer getShopDistance() {
/* 140 */     return this.shopDistance;
/*     */   }
/*     */ 
/*     */   public void setShopDistance(Integer shopDistance) {
/* 144 */     this.shopDistance = shopDistance;
/*     */   }
/*     */ 
/*     */   public Boolean getIsFav() {
/* 148 */     return this.isFav;
/*     */   }
/*     */ 
/*     */   public void setIsFav(Boolean isFav) {
/* 152 */     this.isFav = isFav;
/*     */   }
/*     */ 
/*     */   public List<String> getPicUrlList()
/*     */   {
/* 157 */     return this.picUrlList;
/*     */   }
/*     */ 
/*     */   public void setPicUrlList(List<String> picUrlList) {
/* 161 */     this.picUrlList = picUrlList;
/*     */   }
/*     */ 
/*     */   public String getSignature() {
/* 165 */     return this.signature;
/*     */   }
/*     */ 
/*     */   public void setSignature(String signature) {
/* 169 */     this.signature = signature;
/*     */   }
/*     */ 
/*     */   public int getDiscount() {
/* 173 */     return this.discount;
/*     */   }
/*     */ 
/*     */   public void setDiscount(int discount) {
/* 177 */     this.discount = discount;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.ShopDTO
 * JD-Core Version:    0.6.0
 */