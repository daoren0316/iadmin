/*    */ package cc.kokoko.server.ibutler.domain.kdt.resp;
/*    */ 
/*    */ public class BuyerMessages
/*    */ {
/*    */   private String title;
/*    */   private String content;
/*    */ 
/*    */   public String getTitle()
/*    */   {
/* 14 */     return this.title;
/*    */   }
/*    */ 
/*    */   public void setTitle(String title) {
/* 18 */     this.title = title;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 22 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 26 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 31 */     return "BuyerMessages{title='" + this.title + '\'' + ", content='" + this.content + '\'' + '}';
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.kdt.resp.BuyerMessages
 * JD-Core Version:    0.6.0
 */