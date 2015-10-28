/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ import cc.kokoko.server.commons.util.StringUtil;
/*    */ import cc.kokoko.server.ibutler.domain.Message;
/*    */ import cc.kokoko.server.ibutler.domain.User;
/*    */ import cc.kokoko.server.ibutler.domain.dto.MessageDTO;
/*    */ import java.util.Date;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class MessageUtil
/*    */ {
/* 28 */   private static Logger log = LoggerFactory.getLogger(MessageUtil.class);
/*    */ 
/*    */   public static Message getMessageFromJson(String jsonStr) {
/* 31 */     Message message = (Message)ObjectUtil.getObjectFromJson(jsonStr, Message.class);
/* 32 */     if (message == null) {
/* 33 */       log.error("[getMessageFromJson] messageID is empty [" + message + "]");
/*    */     }
/*    */ 
/* 36 */     return message;
/*    */   }
/*    */ 
/*    */   public static MessageDTO getMessageDTOFromJson(String jsonStr)
/*    */   {
/* 45 */     MessageDTO messageDTO = (MessageDTO)ObjectUtil.getObjectFromJson(jsonStr, MessageDTO.class);
/*    */ 
/* 47 */     String jsonContent = ObjectUtil.getJsonStr(messageDTO.getContentObject());
/*    */ 
/* 49 */     switch (messageDTO.getMessageType()) {
/*    */     case 2:
/* 51 */       User user = (User)ObjectUtil.getObjectFromJson(jsonContent, User.class);
/* 52 */       messageDTO.setContentObject(user);
/*    */     }
/*    */ 
/* 55 */     if ((messageDTO == null) || (StringUtil.isEmpty(messageDTO.getMessageId()))) {
/* 56 */       log.error("[getMessageFromJson] messageID is empty [" + messageDTO + "]");
/*    */     }
/*    */ 
/* 59 */     return messageDTO;
/*    */   }
/*    */ 
/*    */   public static String getJsonStr() {
/* 63 */     String str = null;
/*    */ 
/* 65 */     return str;
/*    */   }
/*    */ 
/*    */   public static Message createMessage(Byte messageType, Long toUid, String content, Long fromUid, String fromUsername, String avatar)
/*    */   {
/* 84 */     Message message = new Message();
/* 85 */     message.setContent(content);
/* 86 */     message.setMessageType(messageType);
/* 87 */     message.setFromUid(fromUid);
/* 88 */     message.setToUid(toUid);
/* 89 */     if (StringUtil.isEmpty(fromUsername)) {
/* 90 */       fromUsername = "匿名";
/*    */     }
/* 92 */     message.setFromUsername(fromUsername);
/* 93 */     message.setFromUserAvatar(avatar);
/* 94 */     message.setPostTime(new Date());
/* 95 */     return message;
/*    */   }
/*    */ 
/*    */   public static class MessageType
/*    */   {
/*    */     private static final int TASK = 2;
/*    */     private static final int PAIRRING = 2;
/*    */     private static final int COMMODITY = 3;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.MessageUtil
 * JD-Core Version:    0.6.0
 */