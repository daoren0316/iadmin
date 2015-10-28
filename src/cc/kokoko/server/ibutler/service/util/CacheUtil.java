/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ import cc.kokoko.server.commons.util.StringUtil;
/*    */ import cc.kokoko.server.ibutler.service.redis.YunWuJedisClient;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class CacheUtil
/*    */ {
/* 11 */   private static Logger log = LoggerFactory.getLogger(CacheUtil.class);
/*    */ 
/*    */   public static int getUserMsgCount(YunWuJedisClient jedisClient, Long uid, Long maxSeqId, String hashName)
/*    */   {
/* 24 */     int count = 0;
/* 25 */     String str = jedisClient.hgetKV(hashName, "" + uid);
/* 26 */     if (!StringUtil.isEmpty(str)) {
/*    */       try {
/* 28 */         count = Integer.parseInt(str);
/*    */       } catch (Exception e) {
/* 30 */         log.error("[getUserMsgCount] " + e.getMessage());
/* 31 */         e.printStackTrace();
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 36 */     if (count > 0) {
/* 37 */       jedisClient.hsetKV(hashName, "" + uid, "0");
/*    */     }
/*    */ 
/* 40 */     return count;
/*    */   }
/*    */ 
/*    */   public static void addUserMsgCount(YunWuJedisClient jedisClient, Long uid, Long maxSeqId, String hashName)
/*    */   {
/* 45 */     int count = 0;
/* 46 */     String str = jedisClient.hgetKV(hashName, "" + uid);
/* 47 */     if (!StringUtil.isEmpty(str)) {
/*    */       try {
/* 49 */         count = Integer.parseInt(str);
/*    */       } catch (Exception e) {
/* 51 */         log.error("[addUserMsgCount] " + e.getMessage());
/* 52 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */ 
/* 56 */     count += 1;
/* 57 */     jedisClient.hsetKV(hashName, "" + uid, count + "");
/*    */   }
/*    */ 
/*    */   public static Long getMaxQuestionSeqId(YunWuJedisClient jedisClient, String key)
/*    */   {
/* 62 */     Long maxQuestionSeqId = Long.valueOf(0L);
/*    */     try {
/* 64 */       maxQuestionSeqId = Long.valueOf(Long.parseLong(jedisClient.getKV("max_question_id")));
/*    */     }
/*    */     catch (Exception e) {
/* 67 */       log.error("[getMaxQuestionSeqId] " + e.getMessage());
/* 68 */       e.printStackTrace();
/*    */     }
/* 70 */     if (maxQuestionSeqId == null) {
/* 71 */       maxQuestionSeqId = Long.valueOf(0L);
/*    */     }
/* 73 */     return maxQuestionSeqId;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.CacheUtil
 * JD-Core Version:    0.6.0
 */