/*     */ package cc.kokoko.server.ibutler.domain.dto;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class OwnerWalletDTO
/*     */ {
/*     */   private Long houseId;
/*     */   private Double balance;
/*     */   private Double totalSavedAmount;
/*     */   private Date createdTime;
/*     */   private Date updateTime;
/*     */   private String phoneNumber;
/*     */   private String publicAddress;
/*     */   private Double chargeAmount;
/*     */   private Double consumeAmount;
/*     */   private String communityName;
/*     */ 
/*     */   public Long getHouseId()
/*     */   {
/*  49 */     return this.houseId;
/*     */   }
/*     */ 
/*     */   public void setHouseId(Long houseId) {
/*  53 */     this.houseId = houseId;
/*     */   }
/*     */ 
/*     */   public Double getBalance() {
/*  57 */     return this.balance;
/*     */   }
/*     */ 
/*     */   public void setBalance(Double balance) {
/*  61 */     this.balance = balance;
/*     */   }
/*     */ 
/*     */   public Double getTotalSavedAmount() {
/*  65 */     return this.totalSavedAmount;
/*     */   }
/*     */ 
/*     */   public void setTotalSavedAmount(Double totalSavedAmount) {
/*  69 */     this.totalSavedAmount = totalSavedAmount;
/*     */   }
/*     */ 
/*     */   public Date getCreatedTime() {
/*  73 */     return this.createdTime;
/*     */   }
/*     */ 
/*     */   public void setCreatedTime(Date createdTime) {
/*  77 */     this.createdTime = createdTime;
/*     */   }
/*     */ 
/*     */   public Date getUpdateTime() {
/*  81 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {
/*  85 */     this.updateTime = updateTime;
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
/*     */   public String getPublicAddress() {
/*  97 */     return this.publicAddress;
/*     */   }
/*     */ 
/*     */   public void setPublicAddress(String publicAddress) {
/* 101 */     this.publicAddress = publicAddress;
/*     */   }
/*     */ 
/*     */   public Double getChargeAmount() {
/* 105 */     return this.chargeAmount;
/*     */   }
/*     */ 
/*     */   public void setChargeAmount(Double chargeAmount) {
/* 109 */     this.chargeAmount = chargeAmount;
/*     */   }
/*     */ 
/*     */   public Double getConsumeAmount() {
/* 113 */     return this.consumeAmount;
/*     */   }
/*     */ 
/*     */   public void setConsumeAmount(Double consumeAmount) {
/* 117 */     this.consumeAmount = consumeAmount;
/*     */   }
/*     */ 
/*     */   public String getCommunityName() {
/* 121 */     return this.communityName;
/*     */   }
/*     */ 
/*     */   public void setCommunityName(String communityName) {
/* 125 */     this.communityName = communityName;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.OwnerWalletDTO
 * JD-Core Version:    0.6.0
 */