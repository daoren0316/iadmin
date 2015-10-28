/*     */ package cc.kokoko.server.ibutler.service.util;
/*     */ 
/*     */ import cc.kokoko.server.ibutler.domain.Message;
/*     */ import cc.kokoko.server.ibutler.domain.bdpush.BDPushCustomContent;
/*     */ import cc.kokoko.server.ibutler.domain.bdpush.BDPushMessage;
/*     */ import com.baidu.yun.channel.auth.ChannelKeyPair;
/*     */ import com.baidu.yun.channel.client.BaiduChannelClient;
/*     */ import com.baidu.yun.channel.exception.ChannelClientException;
/*     */ import com.baidu.yun.channel.exception.ChannelServerException;
/*     */ import com.baidu.yun.channel.model.PushTagMessageRequest;
/*     */ import com.baidu.yun.channel.model.PushTagMessageResponse;
/*     */ import com.baidu.yun.channel.model.PushUnicastMessageRequest;
/*     */ import com.baidu.yun.channel.model.PushUnicastMessageResponse;
/*     */ import com.baidu.yun.core.log.YunLogEvent;
/*     */ import com.baidu.yun.core.log.YunLogHandler;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class BDPushUtil
/*     */ {
/*     */   public static final int APPID = 2719611;
/*     */   public static final String API_KEY = "PlcMVDFLhYpSc8tbfXzYkEBS";
/*     */   public static final String SECRET_KEY = "UpaCW9e2ZXGIGxIj5oOOHgQuu1jiUvPG";
/*     */   public static final String PKG_NAME = "com.baidu.push.example";
/*     */   public static final int MAX_TITLE_LEN = 128;
/*     */ 
/*     */   public static BDPushMessage createBDPushMessage(String title, Byte msgType, Object contentObj)
/*     */   {
/*  25 */     BDPushMessage bdPushMessage = new BDPushMessage();
/*  26 */     bdPushMessage.setTitle(title);
/*  27 */     bdPushMessage.setDescription(title);
/*  28 */     bdPushMessage.setPkg_name("com.baidu.push.example");
/*  29 */     BDPushCustomContent customContent = new BDPushCustomContent();
/*  30 */     customContent.setContent(contentObj);
/*  31 */     customContent.setMsgType(msgType);
/*  32 */     bdPushMessage.setCustom_content(customContent);
/*  33 */     return bdPushMessage;
/*     */   }
/*     */ 
/*     */   public static void pushByUserId(String[] args) {
/*  37 */     Message message = new Message();
/*  38 */     String title = "消息测试";
/*  39 */     message.setContent(title);
/*  40 */     BDPushMessage bdPushMessage = createBDPushMessage(title, new Byte("1"), message);
/*  41 */     System.out.println(ObjectUtil.getJsonStr(bdPushMessage));
/*     */ 
/*  48 */     String apiKey = "PlcMVDFLhYpSc8tbfXzYkEBS";
/*  49 */     String secretKey = "UpaCW9e2ZXGIGxIj5oOOHgQuu1jiUvPG";
/*  50 */     ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
/*     */ 
/*  53 */     BaiduChannelClient channelClient = new BaiduChannelClient(pair);
/*     */ 
/*  56 */     channelClient.setChannelLogHandler(new YunLogHandler()
/*     */     {
/*     */       public void onHandle(YunLogEvent event) {
/*  59 */         System.out.println(event.getMessage());
/*     */       }
/*     */ 
/*     */     });
/*     */     try
/*     */     {
/*  67 */       PushUnicastMessageRequest request = new PushUnicastMessageRequest();
/*  68 */       request.setDeviceType(Integer.valueOf(3));
/*     */ 
/*  70 */       request.setChannelId(Long.valueOf(3518743698747461182L));
/*  71 */       request.setUserId("1059768153557836397");
/*     */ 
/*  73 */       request.setMessage(ObjectUtil.getJsonStr(bdPushMessage));
/*     */ 
/*  75 */       request.setMessageType(Integer.valueOf(0));
/*     */ 
/*  77 */       PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
/*     */ 
/*  81 */       System.out.println("push amount : " + response.getSuccessAmount());
/*     */     }
/*     */     catch (ChannelClientException e)
/*     */     {
/*  85 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelServerException e) {
/*  88 */       System.out.println(String.format("request_id: %d, error_code: %d, error_message: %s", new Object[] { Long.valueOf(e.getRequestId()), Integer.valueOf(e.getErrorCode()), e.getErrorMsg() }));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void pushByTag(String tag, String msg, int deviceType)
/*     */   {
/*  96 */     ChannelKeyPair pair = new ChannelKeyPair("PlcMVDFLhYpSc8tbfXzYkEBS", "UpaCW9e2ZXGIGxIj5oOOHgQuu1jiUvPG");
/*     */ 
/*  99 */     BaiduChannelClient channelClient = new BaiduChannelClient(pair);
/*     */ 
/* 102 */     channelClient.setChannelLogHandler(new YunLogHandler()
/*     */     {
/*     */       public void onHandle(YunLogEvent event) {
/* 105 */         System.out.println(event.getMessage());
/*     */       }
/*     */ 
/*     */     });
/*     */     try
/*     */     {
/* 112 */       PushTagMessageRequest request = new PushTagMessageRequest();
/* 113 */       request.setDeviceType(Integer.valueOf(deviceType));
/*     */ 
/* 115 */       request.setMessageType(Integer.valueOf(0));
/* 116 */       request.setTagName(tag);
/* 117 */       request.setDeployStatus(Integer.valueOf(1));
/* 118 */       request.setMessage(msg);
/*     */ 
/* 124 */       PushTagMessageResponse response = channelClient.pushTagMessage(request);
/*     */ 
/* 128 */       System.out.println("[pushByTag]push amount : " + response.getSuccessAmount());
/*     */     }
/*     */     catch (ChannelClientException e)
/*     */     {
/* 132 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelServerException e) {
/* 135 */       System.out.println("[pushByTag]" + String.format("request_id: %d, error_code: %d, error_message: %s", new Object[] { Long.valueOf(e.getRequestId()), Integer.valueOf(e.getErrorCode()), e.getErrorMsg() }));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 142 */     Message message = new Message();
/* 143 */     String title = "消息测试推送";
/* 144 */     for (int i = 0; i < 1; i++) {
/* 145 */       String ret = title + "[" + i + " ]";
/* 146 */       message.setContent(title);
/* 147 */       BDPushMessage bdPushMessage = createBDPushMessage(ret, new Byte("1"), message);
/*     */ 
/* 149 */       pushByTag("104", ObjectUtil.getJsonStr(bdPushMessage), 4);
/*     */     }
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.BDPushUtil
 * JD-Core Version:    0.6.0
 */