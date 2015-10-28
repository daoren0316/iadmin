/*    */ package cc.kokoko.server.ibutler.domain.weixin.req;
/*    */ 
/*    */ public class LinkMessage extends BaseMessage
/*    */ {
/*    */   private String Title;
/*    */   private String Description;
/*    */   private String Url;
/*    */ 
/*    */   public String getTitle()
/*    */   {
/* 15 */     return this.Title;
/*    */   }
/*    */ 
/*    */   public void setTitle(String title) {
/* 19 */     this.Title = title;
/*    */   }
/*    */ 
/*    */   public String getDescription() {
/* 23 */     return this.Description;
/*    */   }
/*    */ 
/*    */   public void setDescription(String description) {
/* 27 */     this.Description = description;
/*    */   }
/*    */ 
/*    */   public String getUrl() {
/* 31 */     return this.Url;
/*    */   }
/*    */ 
/*    */   public void setUrl(String url) {
/* 35 */     this.Url = url;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.weixin.req.LinkMessage
 * JD-Core Version:    0.6.0
 */