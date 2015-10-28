/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Message
/*     */ {
/*     */   private Long messageId;
/*     */   private Long toUid;
/*     */   private Long fromUid;
/*     */   private String fromUsername;
/*     */   private String fromUserAvatar;
/*     */   private Byte messageType;
/*     */   private Date postTime;
/*     */   private String content;
/*     */   private Long communityId;
/*     */ 
/*     */   public Long getMessageId()
/*     */   {
/*  52 */     return this.messageId;
/*     */   }
/*     */ 
/*     */   public void setMessageId(Long messageId) {
/*  56 */     this.messageId = messageId;
/*     */   }
/*     */ 
/*     */   public Byte getMessageType() {
/*  60 */     return this.messageType;
/*     */   }
/*     */ 
/*     */   public void setMessageType(Byte messageType)
/*     */   {
/*  65 */     this.messageType = messageType;
/*     */   }
/*     */ 
/*     */   public Date getPostTime()
/*     */   {
/*  70 */     return this.postTime;
/*     */   }
/*     */ 
/*     */   public void setPostTime(Date postTime)
/*     */   {
/*  75 */     this.postTime = postTime;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/*  80 */     return this.content;
/*     */   }
/*     */ 
/*     */   public Long getToUid()
/*     */   {
/*  85 */     return this.toUid;
/*     */   }
/*     */ 
/*     */   public void setToUid(Long toUid)
/*     */   {
/*  90 */     this.toUid = toUid;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/*  95 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public Long getFromUid()
/*     */   {
/* 100 */     return this.fromUid;
/*     */   }
/*     */ 
/*     */   public void setFromUid(Long fromUid)
/*     */   {
/* 105 */     this.fromUid = fromUid;
/*     */   }
/*     */ 
/*     */   public String getFromUsername()
/*     */   {
/* 110 */     return this.fromUsername;
/*     */   }
/*     */ 
/*     */   public void setFromUsername(String fromUsername)
/*     */   {
/* 115 */     this.fromUsername = fromUsername;
/*     */   }
/*     */ 
/*     */   public String getFromUserAvatar()
/*     */   {
/* 120 */     return this.fromUserAvatar;
/*     */   }
/*     */ 
/*     */   public void setFromUserAvatar(String fromUserAvatar)
/*     */   {
/* 125 */     this.fromUserAvatar = fromUserAvatar;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 129 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 133 */     this.communityId = communityId;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Message
 * JD-Core Version:    0.6.0
 */