/*     */ package cc.kokoko.server.ibutler.service.weixin;
/*     */ 
/*     */ import cc.kokoko.server.ibutler.domain.weixin.resp.TextMessage;
/*     */ import cc.kokoko.server.ibutler.service.util.WeixinMessageUtil;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("coreService")
/*     */ public class CoreService
/*     */ {
/*     */   public String parseOpenId(HttpServletRequest request)
/*     */   {
/*  26 */     Map requestMap = WeixinMessageUtil.parseXml(request);
/*     */ 
/*  28 */     return (String)requestMap.get("FromUserName");
/*     */   }
/*     */ 
/*     */   public static String processRequest(HttpServletRequest request)
/*     */   {
/*  39 */     String respMessage = null;
/*     */     try
/*     */     {
/*  42 */       StringBuffer respContent = new StringBuffer();
/*     */ 
/*  44 */       Map requestMap = WeixinMessageUtil.parseXml(request);
/*     */ 
/*  46 */       String fromUserName = (String)requestMap.get("FromUserName");
/*     */ 
/*  48 */       String toUserName = (String)requestMap.get("ToUserName");
/*     */ 
/*  50 */       String msgType = (String)requestMap.get("MsgType");
/*     */ 
/*  52 */       String content = (String)requestMap.get("Content");
/*     */ 
/*  56 */       if (msgType.equals("text"))
/*     */       {
/*  58 */         respContent.append(" /:rose/:rose/:rose/:rose/:rose/:rose/:rose/:rose/:rose/:rose ").append("\n");
/*  60 */       } else if (msgType.equals("event"))
/*     */       {
/*  62 */         String event = (String)requestMap.get("Event");
/*  63 */         if (event.equals("subscribe")) {
/*  64 */           respContent.append(" /:rose/:rose/:rose/:rose/:rose/:rose/:rose/:rose/:rose/:rose ").append("\n");
/*  65 */           respContent.append(" 感谢您关注微管家一卡通 ").append("\n\n");
/*  66 */           respContent.append(" 通过 服务->绑定户号 功能完成户号的绑定可使您获得更丰富的可操作功能/:jump/:jump").append("\n");
/*  67 */         } else if (!event.equals("unsubscribe"))
/*     */         {
/*  69 */           if (event.equals("CLICK"))
/*     */           {
/*  71 */             String eventKey = (String)requestMap.get("EventKey");
/*  72 */             if (eventKey.equals("V1001_NOTICE"))
/*     */             {
/*  74 */               respContent.append("您点击的“通知”菜单还在建设中... /:8*/:8*").append("\n");
/*  75 */             } else if (eventKey.equals("V1002_01_RECHARGE"))
/*     */             {
/*  77 */               respContent.append("您点击的“缴费充值”菜单还在建设中... /:8*/:8*").append("\n");
/*  78 */             } else if (eventKey.equals("V1003_02_RECORD"))
/*     */             {
/*  80 */               respContent.append("您点击的“消费记录”菜单还在建设中... /:8*/:8*").append("\n");
/*  81 */             } else if (eventKey.equals("V1003_01_FAMILY"))
/*     */             {
/*  83 */               respContent.append("您点击的“家人管理”菜单还在建设中... /:8*/:8*").append("\n");
/*  84 */             } else if (eventKey.equals("V1003_04_WALLET"))
/*     */             {
/*  86 */               respContent.append("您点击的“我的钱包”菜单还在建设中... /:8*/:8*").append("\n");
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*  92 */       TextMessage textMessage = new TextMessage();
/*  93 */       textMessage.setToUserName(fromUserName);
/*  94 */       textMessage.setFromUserName(toUserName);
/*  95 */       textMessage.setCreateTime(new Date().getTime());
/*  96 */       textMessage.setMsgType("text");
/*  97 */       textMessage.setContent(respContent.toString());
/*     */ 
/* 100 */       respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
/*     */     }
/*     */     catch (Exception e) {
/* 103 */       e.printStackTrace();
/*     */     }
/* 105 */     return respMessage;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.weixin.CoreService
 * JD-Core Version:    0.6.0
 */