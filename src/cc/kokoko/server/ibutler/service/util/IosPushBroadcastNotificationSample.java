/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ import com.baidu.yun.channel.auth.ChannelKeyPair;
/*    */ import com.baidu.yun.channel.client.BaiduChannelClient;
/*    */ import com.baidu.yun.channel.exception.ChannelClientException;
/*    */ import com.baidu.yun.channel.exception.ChannelServerException;
/*    */ import com.baidu.yun.channel.model.PushTagMessageRequest;
/*    */ import com.baidu.yun.channel.model.PushTagMessageResponse;
/*    */ import com.baidu.yun.core.log.YunLogEvent;
/*    */ import com.baidu.yun.core.log.YunLogHandler;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class IosPushBroadcastNotificationSample
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 65 */     String apiKey = "PlcMVDFLhYpSc8tbfXzYkEBS";
/* 66 */     String secretKey = "UpaCW9e2ZXGIGxIj5oOOHgQuu1jiUvPG";
/* 67 */     ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
/* 68 */     BaiduChannelClient channelClient = new BaiduChannelClient(pair);
/* 69 */     channelClient.setChannelLogHandler(new YunLogHandler()
/*    */     {
/*    */       public void onHandle(YunLogEvent event) {
/* 72 */         System.out.println(event.getMessage());
/*    */       } } );
/*    */     try {
/* 76 */       PushTagMessageRequest tagRequest = new PushTagMessageRequest();
/* 77 */       tagRequest.setDeviceType(Integer.valueOf(4));
/* 78 */       tagRequest.setDeployStatus(Integer.valueOf(2));
/* 79 */       tagRequest.setMessageType(Integer.valueOf(1));
/* 80 */       tagRequest.setTagName("104");
/* 81 */       tagRequest.setMessage("{\"aps\":{\"alert\":\"iButlet推送测试\"}}");
/* 82 */       PushTagMessageResponse response = channelClient.pushTagMessage(tagRequest);
/* 83 */       System.out.println("push amount : " + response.getSuccessAmount());
/*    */     } catch (ChannelClientException e) {
/* 85 */       e.printStackTrace();
/*    */     } catch (ChannelServerException e) {
/* 87 */       System.out.println(String.format("request_id: %d, error_code: %d, error_message: %s", new Object[] { Long.valueOf(e.getRequestId()), Integer.valueOf(e.getErrorCode()), e.getErrorMsg() }));
/*    */     }
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.IosPushBroadcastNotificationSample
 * JD-Core Version:    0.6.0
 */