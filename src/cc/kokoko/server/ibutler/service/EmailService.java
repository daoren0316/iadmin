/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.commons.MailSender;
/*    */ import cc.kokoko.server.commons.util.MMGlobals;
/*    */ import cc.kokoko.server.ibutler.domain.Email;
/*    */ import cc.kokoko.server.ibutler.persistence.EmailMapper;
/*    */ import org.apache.commons.mail.EmailException;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("emailService")
/*    */ public class EmailService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private EmailMapper emailMapper;
/* 22 */   private static Logger log = LoggerFactory.getLogger(EmailService.class);
/*    */ 
/*    */   public void saveEmailToDb(String mailId)
/*    */   {
/* 26 */     Email email = this.emailMapper.getEmailById(Integer.parseInt(mailId));
/* 27 */     if (email != null) {
/* 28 */       int smtpPort = Integer.parseInt(MMGlobals.getProperty("SMTP_PORT"));
/* 29 */       MailSender sender = new MailSender(MMGlobals.getProperty("SMTP_HOST"), MMGlobals.getProperty("SMTP_USER"), MMGlobals.getProperty("SMTP_PSW"), smtpPort);
/*    */       try
/*    */       {
/* 34 */         sender.sendHtmlEmail(email.getContent(), email.getSendTo(), "noreply@keep.im", email.getSubject());
/*    */       }
/*    */       catch (EmailException e) {
/* 37 */         log.error("[saveEmailToDb] send email err:" + e.getMessage());
/*    */ 
/* 39 */         e.printStackTrace();
/*    */       }
/* 41 */       this.emailMapper.createEmailLog(email);
/* 42 */       this.emailMapper.deleteEmailById(email.getMailId());
/* 43 */       log.debug("[saveEmailToDb] OK! to=" + email.getSendTo() + ", mailId=" + mailId);
/*    */     }
/*    */     else {
/* 46 */       log.error("[saveEmailToDb] (" + mailId + ") 不存在");
/*    */     }
/*    */   }
/*    */ 
/*    */   public void createEmail(Email email) {
/* 51 */     this.emailMapper.createEmail(email);
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.EmailService
 * JD-Core Version:    0.6.0
 */