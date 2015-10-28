/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class LevelUtil
/*    */ {
/*    */   public static int getLevel(int accCount)
/*    */   {
/* 11 */     int level = 1;
/* 12 */     int maxLevel = 16;
/* 13 */     int maxLevelCount = 32827;
/* 14 */     if (accCount > maxLevelCount) {
/* 15 */       return maxLevel;
/*    */     }
/* 17 */     for (long n = 1L; n < maxLevel; n += 1L) {
/* 18 */       double nextLevel = Math.pow(2.0D, n) + 4L * n;
/*    */ 
/* 20 */       if (nextLevel > accCount) {
/* 21 */         level = Integer.parseInt("" + n);
/*    */ 
/* 23 */         break;
/*    */       }
/*    */     }
/* 26 */     return level;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 30 */     System.out.println(getLevel(32826));
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.LevelUtil
 * JD-Core Version:    0.6.0
 */