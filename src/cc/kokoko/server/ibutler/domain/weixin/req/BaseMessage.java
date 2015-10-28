/*    */ package cc.kokoko.server.ibutler.domain.weixin.req;
/*    */ 
/*    */ public class BaseMessage
/*    */ {
/*    */   private String ToUserName;
/*    */   private String FromUserName;
/*    */   private long CreateTime;
/*    */   private String MsgType;
/*    */   private long MsgId;
/*    */ 
/*    */   public String getToUserName()
/*    */   {
/* 19 */     return this.ToUserName;
/*    */   }
/*    */ 
/*    */   public void setToUserName(String toUserName) {
/* 23 */     this.ToUserName = toUserName;
/*    */   }
/*    */ 
/*    */   public String getFromUserName() {
/* 27 */     return this.FromUserName;
/*    */   }
/*    */ 
/*    */   public void setFromUserName(String fromUserName) {
/* 31 */     this.FromUserName = fromUserName;
/*    */   }
/*    */ 
/*    */   public long getCreateTime() {
/* 35 */     return this.CreateTime;
/*    */   }
/*    */ 
/*    */   public void setCreateTime(long createTime) {
/* 39 */     this.CreateTime = createTime;
/*    */   }
/*    */ 
/*    */   public String getMsgType() {
/* 43 */     return this.MsgType;
/*    */   }
/*    */ 
/*    */   public void setMsgType(String msgType) {
/* 47 */     this.MsgType = msgType;
/*    */   }
/*    */ 
/*    */   public long getMsgId() {
/* 51 */     return this.MsgId;
/*    */   }
/*    */ 
/*    */   public void setMsgId(long msgId) {
/* 55 */     this.MsgId = msgId;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.weixin.req.BaseMessage
 * JD-Core Version:    0.6.0
 */