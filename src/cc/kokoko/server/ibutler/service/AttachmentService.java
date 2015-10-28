/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.commons.util.MMGlobals;
/*    */ import cc.kokoko.server.ibutler.domain.Attachment;
/*    */ import cc.kokoko.server.ibutler.persistence.AttachmentMapper;
/*    */ import cc.kokoko.server.ibutler.service.util.AttachmentUtil;
/*    */ import cc.kokoko.server.ibutler.service.util.KeepDownloader;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.util.UUID;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("attachmentService")
/*    */ public class AttachmentService
/*    */ {
/* 20 */   private static Logger log = LoggerFactory.getLogger(AttachmentService.class);
/*    */ 
/*    */   @Autowired
/*    */   private AttachmentMapper attachmentMapper;
/*    */ 
/* 27 */   public void createAttachment(Attachment attachment) { this.attachmentMapper.createAttachment(attachment);
/*    */ 
/* 29 */     log.debug("create attachment, attId=" + attachment.getAttId());
/*    */   }
/*    */ 
/*    */   public Attachment createAttachment(Long uid, File uploadFile, String clientFilename)
/*    */   {
/* 34 */     Attachment attachment = null;
/*    */     try {
/* 36 */       String attachmentID = UUID.randomUUID().toString();
/*    */ 
/* 38 */       KeepDownloader keepDownloader = new KeepDownloader(MMGlobals.getProperty("uploadPath_ibutler"));
/*    */ 
/* 40 */       clientFilename = clientFilename.toLowerCase();
/* 41 */       keepDownloader.uploadFile(new FileInputStream(uploadFile), clientFilename, attachmentID, uid + "");
/*    */ 
/* 44 */       attachment = AttachmentUtil.createAttachment(attachmentID, keepDownloader.getfilePath(), keepDownloader.getFilesize(), uid, clientFilename);
/*    */ 
/* 47 */       createAttachment(attachment);
/*    */     } catch (Exception e) {
/* 49 */       e.printStackTrace();
/* 50 */       log.error("[createAttachment] " + e.getMessage() + " \n" + e.getCause());
/*    */     }
/*    */ 
/* 53 */     return attachment;
/*    */   }
/*    */ 
/*    */   public Attachment uploadAttachment(Long uid, File uploadFile, String clientFilename)
/*    */   {
/* 65 */     Attachment attachment = null;
/*    */     try
/*    */     {
/* 68 */       String attachmentID = UUID.randomUUID().toString();
/*    */ 
/* 70 */       KeepDownloader keepDownloader = new KeepDownloader(MMGlobals.getProperty("uploadPath_ibutler"));
/*    */ 
/* 73 */       keepDownloader.uploadFile(new FileInputStream(uploadFile), clientFilename, attachmentID, uid + "");
/*    */ 
/* 76 */       attachment = AttachmentUtil.createAttachment(attachmentID, keepDownloader.getfilePath(), keepDownloader.getFilesize(), uid, clientFilename);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 80 */       e.printStackTrace();
/* 81 */       log.error("[uploadAttachment] " + e.getMessage() + " \n" + e.getCause());
/*    */     }
/*    */ 
/* 84 */     return attachment;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.AttachmentService
 * JD-Core Version:    0.6.0
 */