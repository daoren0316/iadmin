/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Feedback
/*     */ {
/*     */   private Long feedbackId;
/*     */   private Byte type;
/*     */   private Byte category;
/*     */   private String content;
/*     */   private String picList;
/*     */   private Long uid;
/*     */   private Long communityId;
/*     */   private Date postTime;
/*     */   private Byte flag;
/*     */   private String note;
/*     */   private Long operatorId;
/*     */   private String operatorName;
/*     */   private Date updateTime;
/*     */   private String nickname;
/*     */   private String publicAddress;
/*     */   private String phoneNumber;
/*     */   private String communityName;
/*     */ 
/*     */   public Long getFeedbackId()
/*     */   {
/*  64 */     return this.feedbackId;
/*     */   }
/*     */ 
/*     */   public void setFeedbackId(Long feedbackId) {
/*  68 */     this.feedbackId = feedbackId;
/*     */   }
/*     */ 
/*     */   public Byte getType() {
/*  72 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(Byte type) {
/*  76 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public Byte getCategory() {
/*  80 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(Byte category) {
/*  84 */     this.category = category;
/*     */   }
/*     */ 
/*     */   public String getPicList() {
/*  88 */     return this.picList;
/*     */   }
/*     */ 
/*     */   public void setPicList(String picList) {
/*  92 */     this.picList = picList;
/*     */   }
/*     */ 
/*     */   public Long getUid() {
/*  96 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/* 100 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 104 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 108 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public Date getPostTime() {
/* 112 */     return this.postTime;
/*     */   }
/*     */ 
/*     */   public void setPostTime(Date postTime) {
/* 116 */     this.postTime = postTime;
/*     */   }
/*     */ 
/*     */   public String getContent() {
/* 120 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/* 124 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/* 128 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/* 132 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public String getPublicAddress() {
/* 136 */     return this.publicAddress;
/*     */   }
/*     */ 
/*     */   public void setPublicAddress(String publicAddress) {
/* 140 */     this.publicAddress = publicAddress;
/*     */   }
/*     */ 
/*     */   public String getNickname() {
/* 144 */     return this.nickname;
/*     */   }
/*     */ 
/*     */   public void setNickname(String nickname) {
/* 148 */     this.nickname = nickname;
/*     */   }
/*     */ 
/*     */   public Byte getFlag() {
/* 152 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public void setFlag(Byte flag) {
/* 156 */     this.flag = flag;
/*     */   }
/*     */ 
/*     */   public String getNote() {
/* 160 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/* 164 */     this.note = note;
/*     */   }
/*     */ 
/*     */   public Long getOperatorId() {
/* 168 */     return this.operatorId;
/*     */   }
/*     */ 
/*     */   public void setOperatorId(Long operatorId) {
/* 172 */     this.operatorId = operatorId;
/*     */   }
/*     */ 
/*     */   public String getOperatorName() {
/* 176 */     return this.operatorName;
/*     */   }
/*     */ 
/*     */   public void setOperatorName(String operatorName) {
/* 180 */     this.operatorName = operatorName;
/*     */   }
/*     */ 
/*     */   public Date getUpdateTime() {
/* 184 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {
/* 188 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public String getCommunityName() {
/* 192 */     return this.communityName;
/*     */   }
/*     */ 
/*     */   public void setCommunityName(String communityName) {
/* 196 */     this.communityName = communityName;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Feedback
 * JD-Core Version:    0.6.0
 */