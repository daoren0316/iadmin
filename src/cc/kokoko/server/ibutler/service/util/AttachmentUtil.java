/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ import cc.kokoko.server.commons.util.MMGlobals;
/*    */ import cc.kokoko.server.commons.util.StringUtil;
/*    */ import cc.kokoko.server.ibutler.domain.Attachment;
/*    */ import cc.kokoko.server.ibutler.domain.dto.AttachmentDTO;
/*    */ 
/*    */ public class AttachmentUtil
/*    */ {
/*    */   public static final String ArticlePrefix = "article";
/*    */ 
/*    */   public static Attachment createAttachment(String attId, String filePath, Long attSize, Long uid, String fileName)
/*    */   {
/* 14 */     Attachment attachment = new Attachment();
/* 15 */     attachment.setUid(uid);
/* 16 */     attachment.setAttSize(attSize);
/* 17 */     attachment.setFilePath(filePath);
/* 18 */     attachment.setAttId(attId);
/* 19 */     attachment.setFileName(fileName);
/* 20 */     return attachment;
/*    */   }
/*    */ 
/*    */   public static AttachmentDTO Attachment2AttachmentDTO(Attachment attachment) {
/* 24 */     AttachmentDTO attachmentDTO = new AttachmentDTO();
/* 25 */     attachmentDTO.setAttId(attachment.getAttId());
/* 26 */     attachmentDTO.setFileName(attachment.getFileName());
/* 27 */     attachmentDTO.setAttSize(attachment.getAttSize());
/* 28 */     if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 29 */       attachmentDTO.setFileUrl(getUrlPrefix() + "/" + attachment.getFilePath());
/*    */     }
/*    */ 
/* 32 */     return attachmentDTO;
/*    */   }
/*    */ 
/*    */   public static String getUrlPrefix() {
/* 36 */     String prefix = MMGlobals.getProperty("FILE_HTTP_PREFIX");
/* 37 */     prefix = prefix + "/" + MMGlobals.getProperty("APP_NAME");
/*    */ 
/* 39 */     return prefix;
/*    */   }
/*    */ 
/*    */   public static String getArticlePicUrlPrefix()
/*    */   {
/* 44 */     String prefix = getUrlPrefix();
/* 45 */     prefix = prefix + "/";
/* 46 */     return prefix;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.AttachmentUtil
 * JD-Core Version:    0.6.0
 */