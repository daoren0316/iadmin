/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ public class Consultant
/*     */ {
/*     */   private Long consultantId;
/*     */   private String consultantName;
/*     */   private String avatarUrl;
/*     */   private Byte consultantType;
/*     */   private String publicAddress;
/*     */   private String signature;
/*     */   private Integer distance;
/*     */   private String duty;
/*     */   private String consultantDesc;
/*     */   private Long communityId;
/*     */   private String phoneNumber;
/*     */ 
/*     */   public Long getConsultantId()
/*     */   {
/*  55 */     return this.consultantId;
/*     */   }
/*     */ 
/*     */   public void setConsultantId(Long consultantId) {
/*  59 */     this.consultantId = consultantId;
/*     */   }
/*     */ 
/*     */   public String getConsultantName() {
/*  63 */     return this.consultantName;
/*     */   }
/*     */ 
/*     */   public void setConsultantName(String consultantName) {
/*  67 */     this.consultantName = consultantName;
/*     */   }
/*     */ 
/*     */   public String getAvatarUrl() {
/*  71 */     return this.avatarUrl;
/*     */   }
/*     */ 
/*     */   public void setAvatarUrl(String avatarUrl) {
/*  75 */     this.avatarUrl = avatarUrl;
/*     */   }
/*     */ 
/*     */   public Byte getConsultantType()
/*     */   {
/*  80 */     return this.consultantType;
/*     */   }
/*     */ 
/*     */   public void setConsultantType(Byte consultantType) {
/*  84 */     this.consultantType = consultantType;
/*     */   }
/*     */ 
/*     */   public String getPublicAddress() {
/*  88 */     return this.publicAddress;
/*     */   }
/*     */ 
/*     */   public void setPublicAddress(String publicAddress) {
/*  92 */     this.publicAddress = publicAddress;
/*     */   }
/*     */ 
/*     */   public String getSignature() {
/*  96 */     return this.signature;
/*     */   }
/*     */ 
/*     */   public void setSignature(String signature) {
/* 100 */     this.signature = signature;
/*     */   }
/*     */ 
/*     */   public Integer getDistance() {
/* 104 */     return this.distance;
/*     */   }
/*     */ 
/*     */   public void setDistance(Integer distance) {
/* 108 */     this.distance = distance;
/*     */   }
/*     */ 
/*     */   public String getDuty() {
/* 112 */     return this.duty;
/*     */   }
/*     */ 
/*     */   public void setDuty(String duty) {
/* 116 */     this.duty = duty;
/*     */   }
/*     */ 
/*     */   public String getConsultantDesc() {
/* 120 */     return this.consultantDesc;
/*     */   }
/*     */ 
/*     */   public void setConsultantDesc(String consultantDesc) {
/* 124 */     this.consultantDesc = consultantDesc;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 128 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 132 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/* 136 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/* 140 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 145 */     return "Consultant{consultantId=" + this.consultantId + ", consultantName='" + this.consultantName + '\'' + ", avatarUrl='" + this.avatarUrl + '\'' + ", consultantType=" + this.consultantType + ", publicAddress='" + this.publicAddress + '\'' + ", signature='" + this.signature + '\'' + ", distance=" + this.distance + ", duty='" + this.duty + '\'' + ", consultantDesc='" + this.consultantDesc + '\'' + ", communityId=" + this.communityId + ", phoneNumber='" + this.phoneNumber + '\'' + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Consultant
 * JD-Core Version:    0.6.0
 */