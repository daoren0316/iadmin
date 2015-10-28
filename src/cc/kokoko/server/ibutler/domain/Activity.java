/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Activity
/*     */ {
/*     */   private Long activityId;
/*     */   private String activityTitle;
/*     */   private Integer viewCount;
/*     */   private Date postTime;
/*     */   private String activityDesc;
/*     */   private String attachmentList;
/*     */   private Long uid;
/*     */   private String username;
/*     */   private String userLogoUrl;
/*     */   private String titlePicUrl;
/*     */   private Boolean isFav;
/*     */   private Long communityId;
/*     */   private Byte userType;
/*     */ 
/*     */   public Long getActivityId()
/*     */   {
/*  72 */     return this.activityId;
/*     */   }
/*     */ 
/*     */   public void setActivityId(Long activityId) {
/*  76 */     this.activityId = activityId;
/*     */   }
/*     */ 
/*     */   public String getActivityTitle() {
/*  80 */     return this.activityTitle;
/*     */   }
/*     */ 
/*     */   public void setActivityTitle(String activityTitle) {
/*  84 */     this.activityTitle = activityTitle;
/*     */   }
/*     */ 
/*     */   public Integer getViewCount() {
/*  88 */     return this.viewCount;
/*     */   }
/*     */ 
/*     */   public void setViewCount(Integer viewCount) {
/*  92 */     this.viewCount = viewCount;
/*     */   }
/*     */ 
/*     */   public Date getPostTime() {
/*  96 */     return this.postTime;
/*     */   }
/*     */ 
/*     */   public void setPostTime(Date postTime) {
/* 100 */     this.postTime = postTime;
/*     */   }
/*     */ 
/*     */   public String getActivityDesc() {
/* 104 */     return this.activityDesc;
/*     */   }
/*     */ 
/*     */   public void setActivityDesc(String activityDesc) {
/* 108 */     this.activityDesc = activityDesc;
/*     */   }
/*     */ 
/*     */   public String getAttachmentList() {
/* 112 */     return this.attachmentList;
/*     */   }
/*     */ 
/*     */   public void setAttachmentList(String attachmentList) {
/* 116 */     this.attachmentList = attachmentList;
/*     */   }
/*     */ 
/*     */   public String getUsername() {
/* 120 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/* 124 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getUserLogoUrl() {
/* 128 */     return this.userLogoUrl;
/*     */   }
/*     */ 
/*     */   public void setUserLogoUrl(String userLogoUrl) {
/* 132 */     this.userLogoUrl = userLogoUrl;
/*     */   }
/*     */ 
/*     */   public String getTitlePicUrl() {
/* 136 */     return this.titlePicUrl;
/*     */   }
/*     */ 
/*     */   public void setTitlePicUrl(String titlePicUrl) {
/* 140 */     this.titlePicUrl = titlePicUrl;
/*     */   }
/*     */ 
/*     */   public Long getUid() {
/* 144 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/* 148 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Boolean getIsFav() {
/* 152 */     return this.isFav;
/*     */   }
/*     */ 
/*     */   public void setIsFav(Boolean isFav) {
/* 156 */     this.isFav = isFav;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 160 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 164 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public Byte getUserType() {
/* 168 */     return this.userType;
/*     */   }
/*     */ 
/*     */   public void setUserType(Byte userType) {
/* 172 */     this.userType = userType;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Activity
 * JD-Core Version:    0.6.0
 */