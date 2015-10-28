/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ import cc.kokoko.server.commons.util.MMGlobals;
/*    */ 
/*    */ public class GiftUtil
/*    */ {
/*    */   public static final String GIFT_IMG_PREFIXC = "gift";
/*    */ 
/*    */   public static String getImgUrlPrefix()
/*    */   {
/*  9 */     String prefix = MMGlobals.getProperty("FILE_HTTP_PREFIX");
/* 10 */     prefix = prefix + "/" + MMGlobals.getProperty("APP_NAME");
/* 11 */     prefix = prefix + "/gift";
/* 12 */     return prefix;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.GiftUtil
 * JD-Core Version:    0.6.0
 */