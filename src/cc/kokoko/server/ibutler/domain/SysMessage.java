/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class SysMessage
/*     */ {
/*     */   private Long sysMessageId;
/*     */   private Byte sysMessageType;
/*     */   private String sysMessageTitle;
/*     */   private Date postTime;
/*     */   private String sysMessageContent;
/*     */   private Long viewCount;
/*     */   private Long referId;
/*     */   private Long uid;
/*     */   private String username;
/*     */   private String userLogoUrl;
/*     */   private String titlePicUrl;
/*     */   private Long communityId;
/*     */   private Byte userType;
/*     */ 
/*     */   public String getUsername()
/*     */   {
/*  70 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/*  74 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getUserLogoUrl() {
/*  78 */     return this.userLogoUrl;
/*     */   }
/*     */ 
/*     */   public void setUserLogoUrl(String userLogoUrl) {
/*  82 */     this.userLogoUrl = userLogoUrl;
/*     */   }
/*     */ 
/*     */   public String getTitlePicUrl() {
/*  86 */     return this.titlePicUrl;
/*     */   }
/*     */ 
/*     */   public void setTitlePicUrl(String titlePicUrl) {
/*  90 */     this.titlePicUrl = titlePicUrl;
/*     */   }
/*     */ 
/*     */   public Long getSysMessageId() {
/*  94 */     return this.sysMessageId;
/*     */   }
/*     */ 
/*     */   public void setSysMessageId(Long sysMessageId) {
/*  98 */     this.sysMessageId = sysMessageId;
/*     */   }
/*     */ 
/*     */   public String getSysMessageTitle() {
/* 102 */     return this.sysMessageTitle;
/*     */   }
/*     */ 
/*     */   public void setSysMessageTitle(String sysMessageTitle) {
/* 106 */     this.sysMessageTitle = sysMessageTitle;
/*     */   }
/*     */ 
/*     */   public Date getPostTime() {
/* 110 */     return this.postTime;
/*     */   }
/*     */ 
/*     */   public void setPostTime(Date postTime) {
/* 114 */     this.postTime = postTime;
/*     */   }
/*     */ 
/*     */   public String getSysMessageContent() {
/* 118 */     return this.sysMessageContent;
/*     */   }
/*     */ 
/*     */   public void setSysMessageContent(String sysMessageContent) {
/* 122 */     this.sysMessageContent = sysMessageContent;
/*     */   }
/*     */ 
/*     */   public Long getUid() {
/* 126 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/* 130 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Byte getSysMessageType() {
/* 134 */     return this.sysMessageType;
/*     */   }
/*     */ 
/*     */   public void setSysMessageType(Byte sysMessageType) {
/* 138 */     this.sysMessageType = sysMessageType;
/*     */   }
/*     */ 
/*     */   public Long getReferId() {
/* 142 */     return this.referId;
/*     */   }
/*     */ 
/*     */   public void setReferId(Long referId) {
/* 146 */     this.referId = referId;
/*     */   }
/*     */ 
/*     */   public Long getViewCount() {
/* 150 */     return this.viewCount;
/*     */   }
/*     */ 
/*     */   public void setViewCount(Long viewCount) {
/* 154 */     this.viewCount = viewCount;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 158 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 162 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public Byte getUserType() {
/* 166 */     return this.userType;
/*     */   }
/*     */ 
/*     */   public void setUserType(Byte userType) {
/* 170 */     this.userType = userType;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.SysMessage
 * JD-Core Version:    0.6.0
 */