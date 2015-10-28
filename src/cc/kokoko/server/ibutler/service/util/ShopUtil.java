/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ShopUtil
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 13 */     List picList = new ArrayList();
/* 14 */     picList.add("http://file.kokoko.cc/1.jpg");
/* 15 */     picList.add("http://file.kokoko.cc/2.jpg");
/*    */ 
/* 17 */     System.out.println(ObjectUtil.getJsonStr(picList));
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.ShopUtil
 * JD-Core Version:    0.6.0
 */