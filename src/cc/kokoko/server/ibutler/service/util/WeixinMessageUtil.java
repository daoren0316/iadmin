/*     */ package cc.kokoko.server.ibutler.service.util;
/*     */ 
/*     */ import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cc.kokoko.server.ibutler.domain.weixin.resp.TextMessage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
/*     */ 
/*     */ public class WeixinMessageUtil
/*     */ {
/*     */   public static final String APP_ID = "wxdbb1a4bf5216234e";
/*     */   public static final String APP_SECRET = "4a800213f8f1fcdb45263fc5045d2cb0";
/*     */   public static final String REQ_URL = "http://iadmin.ibutler.cn/iadmin/";
/*     */   public static final String RESP_MESSAGE_TYPE_TEXT = "text";
/*     */   public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
/*     */   public static final String RESP_MESSAGE_TYPE_NEWS = "news";
/*     */   public static final String REQ_MESSAGE_TYPE_TEXT = "text";
/*     */   public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
/*     */   public static final String REQ_MESSAGE_TYPE_LINK = "link";
/*     */   public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
/*     */   public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
/*     */   public static final String REQ_MESSAGE_TYPE_EVENT = "event";
/*     */   public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
/*     */   public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
/*     */   public static final String EVENT_TYPE_CLICK = "CLICK";
/*     */   public static final String EVENT_TYPE_CLICK_NOTICE = "V1001_NOTICE";
/*     */   public static final String EVENT_TYPE_CLICK_RECHARGE = "V1002_01_RECHARGE";
/*     */   public static final String EVENT_TYPE_CLICK_FAMILY = "V1003_01_FAMILY";
/*     */   public static final String EVENT_TYPE_CLICK_RECORD = "V1003_02_RECORD";
/*     */   public static final String EVENT_TYPE_CLICK_WALLET = "V1003_04_WALLET";
/* 162 */   private static XStream xstream = new XStream(new XppDriver() {
/*     */     public HierarchicalStreamWriter createWriter(Writer out) {
/* 164 */       return new PrettyPrintWriter(out)
/*     */       {
/* 166 */         boolean cdata = true;
/*     */ 
/*     */         public void startNode(String name, Class clazz)
/*     */         {
/* 170 */           super.startNode(name, clazz);
/*     */         }
/*     */ 
/*     */         protected void writeText(QuickWriter writer, String text) {
/* 174 */           if (this.cdata) {
/* 175 */             writer.write("<![CDATA[");
/* 176 */             writer.write(text);
/* 177 */             writer.write("]]>");
/*     */           } else {
/* 179 */             writer.write(text);
/*     */           }
/*     */         }
/*     */       };
/*     */     }
/*     */   });
/*     */ 
/*     */   public static Map<String, String> parseXml(HttpServletRequest request)
/*     */   {
/* 119 */     Map map = new HashMap();
/*     */     try
/*     */     {
/* 122 */       StringBuffer sb = new StringBuffer();
/* 123 */       InputStream is = request.getInputStream();
/* 124 */       InputStreamReader isr = new InputStreamReader(is, "UTF-8");
/* 125 */       BufferedReader br = new BufferedReader(isr);
/* 126 */       String s = "";
/* 127 */       while ((s = br.readLine()) != null) {
/* 128 */         sb.append(s);
/*     */       }
/*     */ 
/* 131 */       Document document = DocumentHelper.parseText(sb.toString());
/*     */ 
/* 133 */       Element root = document.getRootElement();
/*     */ 
/* 135 */       List<Element> elementList = root.elements();
/*     */ 
/* 137 */       for (Element e : elementList)
/* 138 */         map.put(e.getName(), e.getText());
/*     */     }
/*     */     catch (Exception e) {
/* 141 */       e.printStackTrace();
/*     */     }
/* 143 */     return map;
/*     */   }
/*     */ 
/*     */   public static String textMessageToXml(TextMessage textMessage)
/*     */   {
/* 153 */     xstream.alias("xml", textMessage.getClass());
/* 154 */     return xstream.toXML(textMessage);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.WeixinMessageUtil
 * JD-Core Version:    0.6.0
 */