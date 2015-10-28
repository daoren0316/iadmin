/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Shop
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
/*     */   private String picList;
/*     */   private String signature;
/*     */   private int discount;
/*     */   private String username;
/*     */   private Long communityId;
/*     */   private String communityName;
/*     */   private Byte flag;
/*     */ 
/*     */   public Long getShopId()
/*     */   {
/*  92 */     return this.shopId;
/*     */   }
/*     */ 
/*     */   public void setShopId(Long shopId) {
/*  96 */     this.shopId = shopId;
/*     */   }
/*     */ 
/*     */   public String getShopName() {
/* 100 */     return this.shopName;
/*     */   }
/*     */ 
/*     */   public void setShopName(String shopName) {
/* 104 */     this.shopName = shopName;
/*     */   }
/*     */ 
/*     */   public String getShopAddress() {
/* 108 */     return this.shopAddress;
/*     */   }
/*     */ 
/*     */   public void setShopAddress(String shopAddress) {
/* 112 */     this.shopAddress = shopAddress;
/*     */   }
/*     */ 
/*     */   public String getLogoUrl() {
/* 116 */     return this.logoUrl;
/*     */   }
/*     */ 
/*     */   public void setLogoUrl(String logoUrl) {
/* 120 */     this.logoUrl = logoUrl;
/*     */   }
/*     */ 
/*     */   public Byte getShopType() {
/* 124 */     return this.shopType;
/*     */   }
/*     */ 
/*     */   public void setShopType(Byte shopType) {
/* 128 */     this.shopType = shopType;
/*     */   }
/*     */ 
/*     */   public Date getCreatedTime() {
/* 132 */     return this.createdTime;
/*     */   }
/*     */ 
/*     */   public void setCreatedTime(Date createdTime) {
/* 136 */     this.createdTime = createdTime;
/*     */   }
/*     */ 
/*     */   public String getShopDesc() {
/* 140 */     return this.shopDesc;
/*     */   }
/*     */ 
/*     */   public void setShopDesc(String shopDesc) {
/* 144 */     this.shopDesc = shopDesc;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/* 148 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/* 152 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public Integer getShopDistance() {
/* 156 */     return this.shopDistance;
/*     */   }
/*     */ 
/*     */   public void setShopDistance(Integer shopDistance) {
/* 160 */     this.shopDistance = shopDistance;
/*     */   }
/*     */ 
/*     */   public Boolean getIsFav() {
/* 164 */     return this.isFav;
/*     */   }
/*     */ 
/*     */   public void setIsFav(Boolean isFav) {
/* 168 */     this.isFav = isFav;
/*     */   }
/*     */ 
/*     */   public String getPicList() {
/* 172 */     return this.picList;
/*     */   }
/*     */ 
/*     */   public void setPicList(String picList) {
/* 176 */     this.picList = picList;
/*     */   }
/*     */ 
/*     */   public String getSignature()
/*     */   {
/* 181 */     return this.signature;
/*     */   }
/*     */ 
/*     */   public void setSignature(String signature) {
/* 185 */     this.signature = signature;
/*     */   }
/*     */ 
/*     */   public int getDiscount() {
/* 189 */     return this.discount;
/*     */   }
/*     */ 
/*     */   public void setDiscount(int discount) {
/* 193 */     this.discount = discount;
/*     */   }
/*     */ 
/*     */   public String getUsername() {
/* 197 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/* 201 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 205 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 209 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public String getCommunityName() {
/* 213 */     return this.communityName;
/*     */   }
/*     */ 
/*     */   public void setCommunityName(String communityName) {
/* 217 */     this.communityName = communityName;
/*     */   }
/*     */ 
/*     */   public Byte getFlag() {
/* 221 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public void setFlag(Byte flag) {
/* 225 */     this.flag = flag;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 230 */     return "Shop{shopId=" + this.shopId + ", shopName='" + this.shopName + '\'' + ", shopAddress='" + this.shopAddress + '\'' + ", logoUrl='" + this.logoUrl + '\'' + ", shopType=" + this.shopType + ", createdTime=" + this.createdTime + ", shopDesc='" + this.shopDesc + '\'' + ", phoneNumber='" + this.phoneNumber + '\'' + ", shopDistance=" + this.shopDistance + ", isFav=" + this.isFav + ", picList='" + this.picList + '\'' + ", signature='" + this.signature + '\'' + ", discount=" + this.discount + ", username='" + this.username + '\'' + ", communityId='" + this.communityId + '\'' + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Shop
 * JD-Core Version:    0.6.0
 */