/*     */ package cc.kokoko.server.ibutler.domain.admin;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class AdminUser
/*     */ {
/*     */   private Long uid;
/*     */   private Long operatorId;
/*     */   private String username;
/*     */   private String nickname;
/*     */   private String phoneNumber;
/*     */   private String password;
/*     */   private Date lastLoginDate;
/*     */   private Byte accountStatus;
/*     */   private String lastLoginIp;
/*     */   private Byte userType;
/*     */   private boolean sysFlag;
/*     */   private Long communityId;
/*     */ 
/*     */   public Long getUid()
/*     */   {
/*  21 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/*  25 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Long getOperatorId() {
/*  29 */     return this.operatorId;
/*     */   }
/*     */ 
/*     */   public void setOperatorId(Long operatorId) {
/*  33 */     this.operatorId = operatorId;
/*     */   }
/*     */ 
/*     */   public Date getLastLoginDate() {
/*  37 */     return this.lastLoginDate;
/*     */   }
/*     */ 
/*     */   public void setLastLoginDate(Date lastLoginDate) {
/*  41 */     this.lastLoginDate = lastLoginDate;
/*     */   }
/*     */ 
/*     */   public Byte getAccountStatus() {
/*  45 */     return this.accountStatus;
/*     */   }
/*     */ 
/*     */   public void setAccountStatus(Byte accountStatus) {
/*  49 */     this.accountStatus = accountStatus;
/*     */   }
/*     */ 
/*     */   public String getLastLoginIp() {
/*  53 */     return this.lastLoginIp;
/*     */   }
/*     */ 
/*     */   public void setLastLoginIp(String lastLoginIp) {
/*  57 */     this.lastLoginIp = lastLoginIp;
/*     */   }
/*     */ 
/*     */   public String getPassword() {
/*  61 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password) {
/*  65 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public String getUsername() {
/*  69 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/*  73 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public Byte getUserType() {
/*  77 */     return this.userType;
/*     */   }
/*     */ 
/*     */   public void setUserType(Byte userType) {
/*  81 */     this.userType = userType;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/*  85 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/*  89 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public String getNickname() {
/*  93 */     return this.nickname;
/*     */   }
/*     */ 
/*     */   public void setNickname(String nickname) {
/*  97 */     this.nickname = nickname;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/* 101 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/* 105 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public boolean isSysFlag() {
/* 109 */     return this.sysFlag;
/*     */   }
/*     */ 
/*     */   public void setSysFlag(boolean sysFlag) {
/* 113 */     this.sysFlag = sysFlag;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.admin.AdminUser
 * JD-Core Version:    0.6.0
 */