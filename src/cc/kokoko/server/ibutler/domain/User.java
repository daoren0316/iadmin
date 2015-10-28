/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import cc.kokoko.server.json.CustomDateDeSerializerMySQLDateTime;
/*     */ import java.util.Date;
/*     */ import org.codehaus.jackson.map.annotate.JsonDeserialize;
/*     */ 
/*     */ public class User
/*     */ {
/*     */   private Long uid;
/*     */   private String username;
/*     */   private String nickname;
/*     */   private Byte gender;
/*     */   private String profileImage;
/*     */   private String email;
/*     */   private String password;
/*     */ 
/*     */   @JsonDeserialize(using=CustomDateDeSerializerMySQLDateTime.class)
/*     */   private Date regTime;
/*     */   private String token;
/*     */   private String phoneNumber;
/*     */   private Long houseId;
/*     */   private String memo;
/*     */   private String publicAddress;
/*     */   private Double money;
/*     */   private String roomNo;
/*     */   private Long communityId;
/*     */   private Byte userType;
/*     */   private Byte appStatus;
/*     */   private Byte cardStatus;
/*     */   private String communityName;
/*     */ 
/*     */   public Long getUid()
/*     */   {
/*  78 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/*  82 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public String getNickname() {
/*  86 */     return this.nickname;
/*     */   }
/*     */ 
/*     */   public void setNickname(String nickname) {
/*  90 */     this.nickname = nickname;
/*     */   }
/*     */ 
/*     */   public String getProfileImage() {
/*  94 */     return this.profileImage;
/*     */   }
/*     */ 
/*     */   public void setProfileImage(String profileImage) {
/*  98 */     this.profileImage = profileImage;
/*     */   }
/*     */ 
/*     */   public String getEmail() {
/* 102 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email) {
/* 106 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getPassword() {
/* 110 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password) {
/* 114 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public Date getRegTime() {
/* 118 */     return this.regTime;
/*     */   }
/*     */ 
/*     */   public void setRegTime(Date regTime) {
/* 122 */     this.regTime = regTime;
/*     */   }
/*     */ 
/*     */   public String getToken() {
/* 126 */     return this.token;
/*     */   }
/*     */ 
/*     */   public void setToken(String token) {
/* 130 */     this.token = token;
/*     */   }
/*     */ 
/*     */   public Byte getGender() {
/* 134 */     return this.gender;
/*     */   }
/*     */ 
/*     */   public void setGender(Byte gender) {
/* 138 */     this.gender = gender;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/* 142 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/* 146 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public Long getHouseId() {
/* 150 */     return this.houseId;
/*     */   }
/*     */ 
/*     */   public void setHouseId(Long houseId) {
/* 154 */     this.houseId = houseId;
/*     */   }
/*     */ 
/*     */   public String getMemo() {
/* 158 */     return this.memo;
/*     */   }
/*     */ 
/*     */   public void setMemo(String memo) {
/* 162 */     this.memo = memo;
/*     */   }
/*     */ 
/*     */   public String getPublicAddress() {
/* 166 */     return this.publicAddress;
/*     */   }
/*     */ 
/*     */   public void setPublicAddress(String publicAddress) {
/* 170 */     this.publicAddress = publicAddress;
/*     */   }
/*     */ 
/*     */   public String getRoomNo() {
/* 174 */     return this.roomNo;
/*     */   }
/*     */ 
/*     */   public void setRoomNo(String roomNo) {
/* 178 */     this.roomNo = roomNo;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 182 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 186 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public Byte getUserType() {
/* 190 */     return this.userType;
/*     */   }
/*     */ 
/*     */   public void setUserType(Byte userType) {
/* 194 */     this.userType = userType;
/*     */   }
/*     */ 
/*     */   public String getUsername() {
/* 198 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/* 202 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public Double getMoney() {
/* 206 */     return this.money;
/*     */   }
/*     */ 
/*     */   public void setMoney(Double money) {
/* 210 */     this.money = money;
/*     */   }
/*     */ 
/*     */   public String getCommunityName() {
/* 214 */     return this.communityName;
/*     */   }
/*     */ 
/*     */   public void setCommunityName(String communityName) {
/* 218 */     this.communityName = communityName;
/*     */   }
/*     */ 
/*     */   public Byte getAppStatus() {
/* 222 */     return this.appStatus;
/*     */   }
/*     */ 
/*     */   public void setAppStatus(Byte appStatus) {
/* 226 */     this.appStatus = appStatus;
/*     */   }
/*     */ 
/*     */   public Byte getCardStatus() {
/* 230 */     return this.cardStatus;
/*     */   }
/*     */ 
/*     */   public void setCardStatus(Byte cardStatus) {
/* 234 */     this.cardStatus = cardStatus;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.User
 * JD-Core Version:    0.6.0
 */