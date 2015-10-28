/*     */ package cc.kokoko.server.ibutler.domain.dto;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ActivityDTO
/*     */ {
/*     */   private Long activityId;
/*     */   private String activityTitle;
/*     */   private Integer viewCount;
/*     */   private Date postTime;
/*     */   private String activityDesc;
/*     */   private List<AttachmentDTO> attachmentList;
/*     */   private Long uid;
/*     */   private String username;
/*     */   private String userLogoUrl;
/*     */   private String titlePicUrl;
/*     */   private Boolean isFav;
/*     */ 
/*     */   public String getUsername()
/*     */   {
/*  56 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/*  60 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getUserLogoUrl() {
/*  64 */     return this.userLogoUrl;
/*     */   }
/*     */ 
/*     */   public void setUserLogoUrl(String userLogoUrl) {
/*  68 */     this.userLogoUrl = userLogoUrl;
/*     */   }
/*     */ 
/*     */   public String getTitlePicUrl() {
/*  72 */     return this.titlePicUrl;
/*     */   }
/*     */ 
/*     */   public void setTitlePicUrl(String titlePicUrl) {
/*  76 */     this.titlePicUrl = titlePicUrl;
/*     */   }
/*     */ 
/*     */   public Long getActivityId() {
/*  80 */     return this.activityId;
/*     */   }
/*     */ 
/*     */   public void setActivityId(Long activityId) {
/*  84 */     this.activityId = activityId;
/*     */   }
/*     */ 
/*     */   public String getActivityTitle() {
/*  88 */     return this.activityTitle;
/*     */   }
/*     */ 
/*     */   public void setActivityTitle(String activityTitle) {
/*  92 */     this.activityTitle = activityTitle;
/*     */   }
/*     */ 
/*     */   public Integer getViewCount() {
/*  96 */     return this.viewCount;
/*     */   }
/*     */ 
/*     */   public void setViewCount(Integer viewCount) {
/* 100 */     this.viewCount = viewCount;
/*     */   }
/*     */ 
/*     */   public Date getPostTime() {
/* 104 */     return this.postTime;
/*     */   }
/*     */ 
/*     */   public void setPostTime(Date postTime) {
/* 108 */     this.postTime = postTime;
/*     */   }
/*     */ 
/*     */   public String getActivityDesc() {
/* 112 */     return this.activityDesc;
/*     */   }
/*     */ 
/*     */   public void setActivityDesc(String activityDesc) {
/* 116 */     this.activityDesc = activityDesc;
/*     */   }
/*     */ 
/*     */   public Long getUid() {
/* 120 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/* 124 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public List<AttachmentDTO> getAttachmentList() {
/* 128 */     return this.attachmentList;
/*     */   }
/*     */ 
/*     */   public void setAttachmentList(List<AttachmentDTO> attachmentList) {
/* 132 */     this.attachmentList = attachmentList;
/*     */   }
/*     */ 
/*     */   public Boolean getIsFav() {
/* 136 */     return this.isFav;
/*     */   }
/*     */ 
/*     */   public void setIsFav(Boolean isFav) {
/* 140 */     this.isFav = isFav;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.ActivityDTO
 * JD-Core Version:    0.6.0
 */