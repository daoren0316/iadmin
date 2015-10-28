/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Operator
/*     */ {
/*     */   private Long id;
/*     */   private Long uid;
/*     */   private String username;
/*     */   private String password;
/*     */   private String token;
/*     */   private Byte gender;
/*     */   private String nickname;
/*     */   private String phoneNumber;
/*     */   private Byte userType;
/*     */   private Byte userStatus;
/*     */   private Byte userFlag;
/*     */   private Long communityId;
/*     */   private Date createdTime;
/*     */   private Date updateTime;
/*     */   private String userTypeName;
/*     */   private String communityName;
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  68 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/*  72 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getUsername() {
/*  76 */     return this.username;
/*     */   }
/*     */ 
/*     */   public Long getUid() {
/*  80 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/*  84 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/*  88 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getPassword() {
/*  92 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password) {
/*  96 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public String getToken() {
/* 100 */     return this.token;
/*     */   }
/*     */ 
/*     */   public void setToken(String token) {
/* 104 */     this.token = token;
/*     */   }
/*     */ 
/*     */   public Byte getGender() {
/* 108 */     return this.gender;
/*     */   }
/*     */ 
/*     */   public void setGender(Byte gender) {
/* 112 */     this.gender = gender;
/*     */   }
/*     */ 
/*     */   public String getNickname() {
/* 116 */     return this.nickname;
/*     */   }
/*     */ 
/*     */   public void setNickname(String nickname) {
/* 120 */     this.nickname = nickname;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/* 124 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/* 128 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public Byte getUserType() {
/* 132 */     return this.userType;
/*     */   }
/*     */ 
/*     */   public void setUserType(Byte userType) {
/* 136 */     this.userType = userType;
/*     */   }
/*     */ 
/*     */   public Byte getUserStatus() {
/* 140 */     return this.userStatus;
/*     */   }
/*     */ 
/*     */   public void setUserStatus(Byte userStatus) {
/* 144 */     this.userStatus = userStatus;
/*     */   }
/*     */ 
/*     */   public Byte getUserFlag() {
/* 148 */     return this.userFlag;
/*     */   }
/*     */ 
/*     */   public void setUserFlag(Byte userFlag) {
/* 152 */     this.userFlag = userFlag;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 156 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 160 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public Date getCreatedTime() {
/* 164 */     return this.createdTime;
/*     */   }
/*     */ 
/*     */   public void setCreatedTime(Date createdTime) {
/* 168 */     this.createdTime = createdTime;
/*     */   }
/*     */ 
/*     */   public Date getUpdateTime() {
/* 172 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {
/* 176 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public String getUserTypeName() {
/* 180 */     return this.userTypeName;
/*     */   }
/*     */ 
/*     */   public void setUserTypeName(String userTypeName) {
/* 184 */     this.userTypeName = userTypeName;
/*     */   }
/*     */ 
/*     */   public String getCommunityName() {
/* 188 */     return this.communityName;
/*     */   }
/*     */ 
/*     */   public void setCommunityName(String communityName) {
/* 192 */     this.communityName = communityName;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 197 */     return "Operator{id=" + this.id + ", uid=" + this.uid + ", username='" + this.username + '\'' + ", password='" + this.password + '\'' + ", token='" + this.token + '\'' + ", gender=" + this.gender + ", nickname='" + this.nickname + '\'' + ", phoneNumber='" + this.phoneNumber + '\'' + ", userType=" + this.userType + ", userStatus=" + this.userStatus + ", userFlag=" + this.userFlag + ", communityId=" + this.communityId + ", createdTime=" + this.createdTime + ", updateTime=" + this.updateTime + ", userTypeName='" + this.userTypeName + '\'' + ", communityName='" + this.communityName + '\'' + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Operator
 * JD-Core Version:    0.6.0
 */