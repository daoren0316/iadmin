/*    */ package cc.kokoko.server.ibutler.domain.dto;
/*    */ 
/*    */ public class MessageDTO
/*    */ {
/*    */   private Long seqId;
/*    */   private String messageId;
/*    */   private int messageType;
/*    */   private Long timestamp;
/*    */   private Long uid;
/*    */   private Object contentObject;
/*    */ 
/*    */   public String getMessageId()
/*    */   {
/* 12 */     return this.messageId;
/*    */   }
/*    */ 
/*    */   public void setMessageId(String messageId) {
/* 16 */     this.messageId = messageId;
/*    */   }
/*    */ 
/*    */   public Long getUid() {
/* 20 */     return this.uid;
/*    */   }
/*    */ 
/*    */   public void setUid(Long uid) {
/* 24 */     this.uid = uid;
/*    */   }
/*    */ 
/*    */   public Long getTimestamp() {
/* 28 */     return this.timestamp;
/*    */   }
/*    */ 
/*    */   public void setTimestamp(Long timestamp) {
/* 32 */     this.timestamp = timestamp;
/*    */   }
/*    */ 
/*    */   public int getMessageType() {
/* 36 */     return this.messageType;
/*    */   }
/*    */ 
/*    */   public void setMessageType(int messageType) {
/* 40 */     this.messageType = messageType;
/*    */   }
/*    */ 
/*    */   public Object getContentObject() {
/* 44 */     return this.contentObject;
/*    */   }
/*    */ 
/*    */   public void setContentObject(Object contentObject) {
/* 48 */     this.contentObject = contentObject;
/*    */   }
/*    */ 
/*    */   public Long getSeqId() {
/* 52 */     return this.seqId;
/*    */   }
/*    */ 
/*    */   public void setSeqId(Long seqId) {
/* 56 */     this.seqId = seqId;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.MessageDTO
 * JD-Core Version:    0.6.0
 */