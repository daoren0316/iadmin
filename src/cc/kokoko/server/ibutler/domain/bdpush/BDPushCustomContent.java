/*    */ package cc.kokoko.server.ibutler.domain.bdpush;
/*    */ 
/*    */ public class BDPushCustomContent
/*    */ {
/*    */   private Byte msgType;
/*    */   private Object content;
/*    */ 
/*    */   public Byte getMsgType()
/*    */   {
/* 14 */     return this.msgType;
/*    */   }
/*    */ 
/*    */   public void setMsgType(Byte msgType) {
/* 18 */     this.msgType = msgType;
/*    */   }
/*    */ 
/*    */   public Object getContent() {
/* 22 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(Object content) {
/* 26 */     this.content = content;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.bdpush.BDPushCustomContent
 * JD-Core Version:    0.6.0
 */