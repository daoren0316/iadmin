/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ public class FamilyMember
/*     */ {
/*     */   private Long houseId;
/*     */   private Long memberId;
/*     */   private String memberName;
/*     */   private String profileImage;
/*     */   private String phoneNumber;
/*     */   private Boolean isAccepted;
/*     */   private Boolean isPaymentAllowed;
/*     */   private Boolean isMaster;
/*     */ 
/*     */   public Long getHouseId()
/*     */   {
/*  40 */     return this.houseId;
/*     */   }
/*     */ 
/*     */   public void setHouseId(Long houseId) {
/*  44 */     this.houseId = houseId;
/*     */   }
/*     */ 
/*     */   public Long getMemberId() {
/*  48 */     return this.memberId;
/*     */   }
/*     */ 
/*     */   public void setMemberId(Long memberId) {
/*  52 */     this.memberId = memberId;
/*     */   }
/*     */ 
/*     */   public String getMemberName() {
/*  56 */     return this.memberName;
/*     */   }
/*     */ 
/*     */   public void setMemberName(String memberName) {
/*  60 */     this.memberName = memberName;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber()
/*     */   {
/*  66 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/*  70 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public Boolean getIsAccepted() {
/*  74 */     return this.isAccepted;
/*     */   }
/*     */ 
/*     */   public void setIsAccepted(Boolean isAccepted) {
/*  78 */     this.isAccepted = isAccepted;
/*     */   }
/*     */ 
/*     */   public Boolean getIsPaymentAllowed() {
/*  82 */     return this.isPaymentAllowed;
/*     */   }
/*     */ 
/*     */   public void setIsPaymentAllowed(Boolean isPaymentAllowed) {
/*  86 */     this.isPaymentAllowed = isPaymentAllowed;
/*     */   }
/*     */ 
/*     */   public Boolean getIsMaster() {
/*  90 */     return this.isMaster;
/*     */   }
/*     */ 
/*     */   public void setIsMaster(Boolean isMaster) {
/*  94 */     this.isMaster = isMaster;
/*     */   }
/*     */ 
/*     */   public String getProfileImage() {
/*  98 */     return this.profileImage;
/*     */   }
/*     */ 
/*     */   public void setProfileImage(String profileImage) {
/* 102 */     this.profileImage = profileImage;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.FamilyMember
 * JD-Core Version:    0.6.0
 */