/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ import com.baidu.yun.channel.auth.ChannelKeyPair;
/*    */ import com.baidu.yun.channel.client.BaiduChannelClient;
/*    */ import com.baidu.yun.channel.exception.ChannelClientException;
/*    */ import com.baidu.yun.channel.exception.ChannelServerException;
/*    */ import com.baidu.yun.channel.model.PushUnicastMessageRequest;
/*    */ import com.baidu.yun.channel.model.PushUnicastMessageResponse;
/*    */ import com.baidu.yun.core.log.YunLogEvent;
/*    */ import com.baidu.yun.core.log.YunLogHandler;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class IosPushNotificationSample
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 20 */     String apiKey = "PlcMVDFLhYpSc8tbfXzYkEBS";
/* 21 */     String secretKey = "UpaCW9e2ZXGIGxIj5oOOHgQuu1jiUvPG";
/* 22 */     ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
/*    */ 
/* 25 */     BaiduChannelClient channelClient = new BaiduChannelClient(pair);
/*    */ 
/* 28 */     channelClient.setChannelLogHandler(new YunLogHandler()
/*    */     {
/*    */       public void onHandle(YunLogEvent event) {
/* 31 */         System.out.println(event.getMessage());
/*    */       }
/*    */ 
/*    */     });
/*    */     try
/*    */     {
/* 39 */       PushUnicastMessageRequest request = new PushUnicastMessageRequest();
/* 40 */       request.setDeviceType(Integer.valueOf(4));
/*    */ 
/* 42 */       request.setDeployStatus(Integer.valueOf(1));
/*    */ 
/* 47 */       request.setUserId("104");
/*    */ 
/* 49 */       request.setMessageType(Integer.valueOf(1));
/* 50 */       request.setMessage("{\"aps\":{\"alert\":\"Hello Baidu Channel\"}}");
/*    */ 
/* 53 */       PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
/*    */ 
/* 57 */       System.out.println("push amount : " + response.getSuccessAmount());
/*    */     }
/*    */     catch (ChannelClientException e)
/*    */     {
/* 61 */       e.printStackTrace();
/*    */     }
/*    */     catch (ChannelServerException e) {
/* 64 */       System.out.println(String.format("request_id: %d, error_code: %d, error_message: %s", new Object[] { Long.valueOf(e.getRequestId()), Integer.valueOf(e.getErrorCode()), e.getErrorMsg() }));
/*    */     }
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.IosPushNotificationSample
 * JD-Core Version:    0.6.0
 */