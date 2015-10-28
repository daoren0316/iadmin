/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class GuestBook
/*    */ {
/*    */   private Long gid;
/*    */   private Long uid;
/*    */   private String content;
/*    */   private Date postTime;
/*    */   private String ip;
/*    */   private String username;
/*    */ 
/*    */   public Long getGid()
/*    */   {
/* 33 */     return this.gid;
/*    */   }
/*    */ 
/*    */   public void setGid(Long gid) {
/* 37 */     this.gid = gid;
/*    */   }
/*    */ 
/*    */   public Long getUid() {
/* 41 */     return this.uid;
/*    */   }
/*    */ 
/*    */   public void setUid(Long uid) {
/* 45 */     this.uid = uid;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 49 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 53 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public String getIp() {
/* 57 */     return this.ip;
/*    */   }
/*    */ 
/*    */   public void setIp(String ip) {
/* 61 */     this.ip = ip;
/*    */   }
/*    */ 
/*    */   public Date getPostTime() {
/* 65 */     return this.postTime;
/*    */   }
/*    */ 
/*    */   public void setPostTime(Date postTime) {
/* 69 */     this.postTime = postTime;
/*    */   }
/*    */ 
/*    */   public String getUsername() {
/* 73 */     return this.username;
/*    */   }
/*    */ 
/*    */   public void setUsername(String username) {
/* 77 */     this.username = username;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.GuestBook
 * JD-Core Version:    0.6.0
 */