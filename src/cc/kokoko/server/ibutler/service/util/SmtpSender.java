/*     */ package cc.kokoko.server.ibutler.service.util;
/*     */ 
/*     */ import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.kokoko.server.commons.util.Base64;
/*     */ 
/*     */ public class SmtpSender
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(SmtpSender.class);
/*     */   private static final String DATE_TEMPLET = "d MMM yyyy HH:mm:ss Z";
/*     */   private static final String BOUNDARY_PREFIX = "--";
/*     */   private static final String CRLF = "\r\n";
/*     */   private Socket socket;
/*     */   private BufferedReader input;
/*  41 */   private PrintStream output = null;
/*     */   private SimpleDateFormat format;
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/*  50 */     SmtpSender send = new SmtpSender();
/*  51 */     send.send("junhui81@qq.com,junhui81@163.com", "取回密码", "<a href=\"http://www.keep.im\">点击链接取回密码</a>", "", "utf8", "", "", "孕期助手客服");
/*     */   }
/*     */ 
/*     */   public void send(String to, String subject, String content, String attachment, String charset, String user, String pass, String fromName)
/*     */   {
/*  57 */     String[] toList = to.split("[,|;]+");
/*  58 */     for (String tostr : toList)
/*     */       try {
/*  60 */         String[] addressList = getSMTPServerByJNDI(tostr);
/*  61 */         for (int i = 0; i < addressList.length; i++) {
/*  62 */           Boolean result = Boolean.valueOf(false);
/*  63 */           String[] fromlist = { "fmaster@mail.test.keep.im", "fmaster@keep.im", "fmaster@qq.com", "fmaster@gmail.com" };
/*     */ 
/*  65 */           for (String from : fromlist) {
/*  66 */             result = Boolean.valueOf(send(from, fromName, tostr, to, subject, content, attachment, charset, addressList[i], addressList[i], user, pass, 25));
/*     */ 
/*  69 */             if (result.booleanValue()) {
/*     */               break;
/*     */             }
/*     */           }
/*  73 */           if (result.booleanValue())
/*     */             break;
/*     */         }
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */       }
/*     */   }
/*     */ 
/*     */   public void send(String from, String formName, String to, String subject, String content, String attachment, String charset, String user, String pass)
/*     */   {
/*  85 */     String[] toList = to.split("[,|;]+");
/*  86 */     for (String tostr : toList)
/*     */       try {
/*  88 */         String[] addressList = getSMTPServerByJNDI(tostr);
/*  89 */         for (int i = 0; i < addressList.length; i++) {
/*  90 */           Boolean result = Boolean.valueOf(send(from, formName, tostr, to, subject, content, attachment, charset, addressList[i], addressList[i], user, pass, 25));
/*     */ 
/*  93 */           if (result.booleanValue())
/*     */             break;
/*     */         }
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */       }
/*     */   }
/*     */ 
/*     */   private String getDomainFromAddress(String EmailAddress) {
/* 103 */     StringTokenizer tokenizer = new StringTokenizer(EmailAddress, "@>;");
/* 104 */     String DomainOnly = tokenizer.nextToken();
/*     */ 
/* 106 */     DomainOnly = tokenizer.nextToken();
/*     */ 
/* 108 */     return DomainOnly;
/*     */   }
/*     */ 
/*     */   private String[] getSMTPServerByJNDI(String to) throws Exception {
/* 112 */     String host = getDomainFromAddress(to);
/* 113 */     Properties jndiEnvironmentProperties = new Properties();
/* 114 */     jndiEnvironmentProperties.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
/*     */ 
/* 116 */     InitialDirContext initialDirContext = new InitialDirContext(jndiEnvironmentProperties);
/*     */ 
/* 118 */     Attributes attributes = initialDirContext.getAttributes(host, new String[] { "MX" });
/*     */ 
/* 121 */     Attribute attribute = attributes.get("MX");
/* 122 */     String[] servers = new String[attribute.size()];
/* 123 */     for (int i = 0; i < attribute.size(); i++) {
/* 124 */       servers[i] = attribute.get(i).toString();
/* 125 */       servers[i] = servers[i].substring(servers[i].indexOf(" ") + 1, servers[i].length() - 1);
/*     */     }
/*     */ 
/* 129 */     return servers;
/*     */   }
/*     */ 
/*     */   public boolean send(String from, String formName, String to, String replyTO, String subject, String content, String attachment, String charset, String smtpAddress, String smtpHost, String user, String pass, int port)
/*     */   {
/*     */     try
/*     */     {
/* 137 */       String boundary = "=======ThisIsBoundary=======";
/* 138 */       this.socket = new Socket(smtpAddress, port);
/* 139 */       this.socket.setSoTimeout(10000);
/* 140 */       this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
/*     */ 
/* 142 */       this.output = new PrintStream(this.socket.getOutputStream());
/* 143 */       getResponse("220", "Failed to connect to: " + smtpHost, true);
/* 144 */       sendCommand("HELO " + smtpHost + "\r\n");
/* 145 */       getResponse("250", "Failed to get HELO response from server.", true);
/* 146 */       if ((user != null) && (!user.isEmpty()) && (pass != null) && (!pass.isEmpty()))
/*     */       {
/* 148 */         sendCommand("AUTH LOGIN\r\n");
/* 149 */         getResponse("334", "Failed to get USERNAME request from server.", true);
/*     */ 
/* 151 */         sendCommand(getBase64String(user) + "\r\n");
/* 152 */         getResponse("334", "Failed to get PASSWORD request from server.", true);
/*     */ 
/* 154 */         sendCommand(getBase64String(pass) + "\r\n");
/* 155 */         getResponse("235", "Failed to send AUTH LOGIN username and password to server.", true);
/*     */       }
/*     */ 
/* 161 */       sendCommand("MAIL FROM: <" + from + ">" + "\r\n");
/* 162 */       getResponse("250", "Failed to get MAIL FROM response from server.", true);
/*     */ 
/* 165 */       String[] toList = to.split("[,|;]+");
/* 166 */       for (String tostr : toList) {
/* 167 */         sendCommand("RCPT TO: <" + tostr + ">" + "\r\n");
/* 168 */         getResponse("250", "Failed to get RCPT TO response from server.", false);
/*     */       }
/*     */ 
/* 172 */       if ((replyTO == null) || (replyTO.isEmpty())) {
/* 173 */         replyTO = to;
/*     */       }
/*     */ 
/* 176 */       sendCommand("DATA\r\n");
/* 177 */       getResponse("354", "Failed to get DATA response from server.", true);
/* 178 */       sendCommand("Subject: " + getBase64Subject(subject, charset) + "\r\n");
/* 179 */       sendCommand("Date: " + getDateString() + "\r\n");
/* 180 */       sendCommand("From: " + formName + "<" + from + ">" + "\r\n");
/* 181 */       sendCommand("To: " + replyTO + "\r\n");
/* 182 */       sendCommand("MIME-Version: 1.0\r\n");
/*     */ 
/* 184 */       sendCommand("Content-Type: multipart/mixed; boundary=\"" + boundary + "\"" + "\r\n");
/*     */ 
/* 186 */       sendCommand("Content-Transfer-Encoding: 7bit\r\n\r\n");
/* 187 */       sendCommand("This is a multi-part message in MIME format.\r\n\r\n");
/*     */ 
/* 190 */       sendCommand("--" + boundary + "\r\n");
/* 191 */       sendCommand("Content-Type: text/plain;\r\n");
/* 192 */       sendCommand("Content-Transfer-Encoding: base64\r\n\r\n");
/* 193 */       sendCommand(getBase64String(content) + "\r\n" + "\r\n");
/* 194 */       String[] fileList = attachment.split("[ |\t|,]+");
/*     */ 
/* 196 */       for (String eachFile : fileList) {
/* 197 */         if (eachFile.trim().isEmpty()) {
/*     */           continue;
/*     */         }
/* 200 */         sendCommand("--" + boundary + "\r\n");
/* 201 */         String[] filenamesplit = eachFile.split("[\\\\|/]+");
/* 202 */         String filename = filenamesplit[(filenamesplit.length - 1)];
/*     */ 
/* 204 */         if ((eachFile.startsWith("hdfs@")) && (filename.indexOf(".") < 0)) {
/* 205 */           filename = filename + ".txt";
/*     */         }
/*     */ 
/* 208 */         sendCommand("Content-Type: application/octet-stream; name=\"" + filename + "\"" + "\r\n");
/*     */ 
/* 210 */         sendCommand("Content-Transfer-Encoding: base64\r\n");
/* 211 */         sendCommand("Content-Disposition: attachment; filename=\"" + filename + "\"" + "\r\n" + "\r\n");
/*     */       }
/*     */ 
/* 216 */       sendCommand("\r\n.\r\n");
/*     */ 
/* 218 */       getResponse("250", "Failed to send DATA content to server.", true);
/* 219 */       sendCommand("QUIT\r\n");
/* 220 */       getResponse("221", "Failed to get QUIT response from server.", true);
/* 221 */       System.out.println("succ " + from + " to " + to + "    " + smtpAddress);
/*     */ 
///* 224 */       ??? = 1;
/*     */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 226 */       System.out.println("fail " + from + " to " + to + "    " + smtpAddress);
/*     */ 
///* 228 */       String[] toList = 0;
/*     */       return false;
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 233 */         this.output.close();
/* 234 */         this.input.close();
/* 235 */         this.socket.close(); } catch (Exception e) {
/*     */       }
/*     */     }
///* 237 */     throw localObject;
/*     */   }
/*     */ 
/*     */   private void sendCommand(String command)
/*     */     throws Exception
/*     */   {
/* 283 */     if ((this.output == null) || (command == null) || (command.length() < 1)) {
/* 284 */       return;
/*     */     }
/* 286 */     this.output.print(command);
/*     */   }
/*     */ 
/*     */   private void getResponse(String code, String message, boolean shouldQuit) throws Exception
/*     */   {
/* 291 */     if ((this.input == null) || (code == null) || (code.length() < 1)) {
/* 292 */       return;
/*     */     }
/*     */ 
/* 295 */     String line = this.input.readLine();
/* 296 */     if ((!line.startsWith(code)) && 
/* 297 */       (shouldQuit))
/* 298 */       throw new Exception(message);
/*     */   }
/*     */ 
/*     */   private String getBase64String(String message)
/*     */   {
/* 303 */     if (message == null) {
/* 304 */       return null;
/*     */     }
/*     */ 
/* 307 */     String ret = null;
/*     */     try {
/* 309 */       ret = Base64.encode(message);
/*     */     } catch (Exception e) {
/* 311 */       e.printStackTrace();
/*     */     }
/* 313 */     return ret;
/*     */   }
/*     */ 
/*     */   private String getBase64Subject(String subject, String charset) {
/* 317 */     if (subject == null) {
/* 318 */       return null;
/*     */     }
/* 320 */     String ret = null;
/* 321 */     byte[] bytes = null;
/*     */     try
/*     */     {
/* 324 */       bytes = charset == null ? subject.getBytes() : subject.getBytes(charset);
/*     */ 
/* 326 */       ret = "=?" + charset + "?B?" + Base64.encode(subject, charset) + "?=";
/*     */     }
/*     */     catch (Exception e) {
/* 329 */       log.error("libtools", e);
/*     */     }
/*     */ 
/* 332 */     return ret;
/*     */   }
/*     */ 
/*     */   private String getDateString() {
/* 336 */     if (this.format == null) {
/*     */       try {
/* 338 */         this.format = new SimpleDateFormat("d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
/*     */       } catch (Exception e) {
/* 340 */         log.error("libtools", e);
/*     */       }
/*     */     }
/*     */ 
/* 344 */     return this.format == null ? new Date().toString() : this.format.format(new Date());
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.SmtpSender
 * JD-Core Version:    0.6.0
 */