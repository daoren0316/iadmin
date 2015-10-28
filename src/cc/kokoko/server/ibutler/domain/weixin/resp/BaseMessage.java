/*    */ package cc.kokoko.server.ibutler.domain.weixin.resp;
/*    */ 
/*    */ public class BaseMessage
/*    */ {
/*    */   private String ToUserName;
/*    */   private String FromUserName;
/*    */   private long CreateTime;
/*    */   private String MsgType;
/*    */ 
/*    */   public String getToUserName()
/*    */   {
/* 17 */     return this.ToUserName;
/*    */   }
/*    */ 
/*    */   public void setToUserName(String toUserName) {
/* 21 */     this.ToUserName = toUserName;
/*    */   }
/*    */ 
/*    */   public String getFromUserName() {
/* 25 */     return this.FromUserName;
/*    */   }
/*    */ 
/*    */   public void setFromUserName(String fromUserName) {
/* 29 */     this.FromUserName = fromUserName;
/*    */   }
/*    */ 
/*    */   public long getCreateTime() {
/* 33 */     return this.CreateTime;
/*    */   }
/*    */ 
/*    */   public void setCreateTime(long createTime) {
/* 37 */     this.CreateTime = createTime;
/*    */   }
/*    */ 
/*    */   public String getMsgType() {
/* 41 */     return this.MsgType;
/*    */   }
/*    */ 
/*    */   public void setMsgType(String msgType) {
/* 45 */     this.MsgType = msgType;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.weixin.resp.BaseMessage
 * JD-Core Version:    0.6.0
 */