/*    */ package cc.kokoko.server.ibutler.domain.xmpp;
/*    */ 
/*    */ public class XMPPOnlineUser
/*    */ {
/*    */   private String username;
/*    */   private String resource;
/*    */   private Integer online;
/*    */   private String presence;
/*    */ 
/*    */   public String getUsername()
/*    */   {
/* 10 */     return this.username;
/*    */   }
/*    */ 
/*    */   public void setUsername(String username) {
/* 14 */     this.username = username;
/*    */   }
/*    */ 
/*    */   public String getResource() {
/* 18 */     return this.resource;
/*    */   }
/*    */ 
/*    */   public void setResource(String resource) {
/* 22 */     this.resource = resource;
/*    */   }
/*    */ 
/*    */   public Integer getOnline() {
/* 26 */     return this.online;
/*    */   }
/*    */ 
/*    */   public void setOnline(Integer online) {
/* 30 */     this.online = online;
/*    */   }
/*    */ 
/*    */   public String getPresence() {
/* 34 */     return this.presence;
/*    */   }
/*    */ 
/*    */   public void setPresence(String presence) {
/* 38 */     this.presence = presence;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.xmpp.XMPPOnlineUser
 * JD-Core Version:    0.6.0
 */