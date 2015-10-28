/*    */ package cc.kokoko.server.ibutler.domain.weixin.resp;
/*    */ 
/*    */ public class TextMessage extends BaseMessage
/*    */ {
/*    */   private String Content;
/*    */ 
/*    */   public String getContent()
/*    */   {
/* 11 */     return this.Content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 15 */     this.Content = content;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 20 */     return "TextMessage{Content='" + this.Content + '\'' + '}';
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.weixin.resp.TextMessage
 * JD-Core Version:    0.6.0
 */