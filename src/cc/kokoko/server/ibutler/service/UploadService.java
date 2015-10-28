/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.commons.util.StringUtil;
/*    */ import cc.kokoko.server.ibutler.domain.Attachment;
/*    */ import cc.kokoko.server.ibutler.service.util.AttachmentUtil;
/*    */ import java.io.File;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("uploadService")
/*    */ public class UploadService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private AttachmentService attachmentService;
/*    */ 
/*    */   public String upload(Long uid, File image, String imageFileName)
/*    */   {
/* 27 */     String thumbnailUrl = "";
/*    */ 
/* 29 */     if (!StringUtil.isEmpty(imageFileName))
/*    */     {
/* 31 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, image, imageFileName);
/*    */ 
/* 33 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 34 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*    */       }
/* 36 */       this.attachmentService.createAttachment(attachment);
/*    */     }
/* 38 */     return thumbnailUrl;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.UploadService
 * JD-Core Version:    0.6.0
 */