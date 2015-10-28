/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ShopLocation
/*     */ {
/*     */   private Long id;
/*     */   private Long shopId;
/*     */   private Long communityId;
/*     */   private Date createdTime;
/*     */   private String shopName;
/*     */   private String shopAddress;
/*     */   private String phoneNumber;
/*     */   private String communityName;
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  41 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/*  45 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public Long getShopId() {
/*  49 */     return this.shopId;
/*     */   }
/*     */ 
/*     */   public void setShopId(Long shopId) {
/*  53 */     this.shopId = shopId;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/*  57 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/*  61 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public Date getCreatedTime() {
/*  65 */     return this.createdTime;
/*     */   }
/*     */ 
/*     */   public void setCreatedTime(Date createdTime) {
/*  69 */     this.createdTime = createdTime;
/*     */   }
/*     */ 
/*     */   public String getShopName() {
/*  73 */     return this.shopName;
/*     */   }
/*     */ 
/*     */   public void setShopName(String shopName) {
/*  77 */     this.shopName = shopName;
/*     */   }
/*     */ 
/*     */   public String getShopAddress() {
/*  81 */     return this.shopAddress;
/*     */   }
/*     */ 
/*     */   public void setShopAddress(String shopAddress) {
/*  85 */     this.shopAddress = shopAddress;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/*  89 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/*  93 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public String getCommunityName() {
/*  97 */     return this.communityName;
/*     */   }
/*     */ 
/*     */   public void setCommunityName(String communityName) {
/* 101 */     this.communityName = communityName;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.ShopLocation
 * JD-Core Version:    0.6.0
 */