/*     */ package cc.kokoko.server.ibutler.service.redis;
/*     */ 
/*     */ import cc.kokoko.server.commons.util.MaxMD5Util;
/*     */ import cc.kokoko.server.commons.util.StringUtil;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.data.redis.connection.jedis.JedisConnection;
/*     */ 
/*     */ public class AskRedisClient
/*     */ {
/*  16 */   private static Logger log = LoggerFactory.getLogger(AskRedisClient.class);
/*     */   private static final String REDIS_USER_HASH = "UserStatusSet";
/*     */   private static final String DEVICE_TOKEN = "DeviceToken";
/*     */   private JedisConnection jedisConn;
/*     */ 
/*     */   public AskRedisClient(JedisConnection jedisConnection)
/*     */   {
/*  25 */     this.jedisConn = jedisConnection;
/*     */   }
/*     */ 
/*     */   public AskRedisClient() {
/*  29 */     log.error("[AskRedisClient] no RedisConnection ");
/*     */   }
/*     */ 
/*     */   public boolean isOnline(String uid, String resource) {
/*  33 */     String value = uid + ":" + resource;
/*  34 */     return this.jedisConn.sIsMember("UserStatusSet".getBytes(), value.getBytes()).booleanValue();
/*     */   }
/*     */ 
/*     */   public String createToken(String uid, String randKey)
/*     */   {
/*  45 */     if ((uid == null) || ("".equals(uid))) {
/*  46 */       return null;
/*     */     }
/*  48 */     String token = null;
/*  49 */     String key = uid + "." + "Token";
/*  50 */     String v = getKV(key);
/*  51 */     if (v != null) {
/*  52 */       log.debug("get token from redis " + v);
/*  53 */       token = v;
/*     */     } else {
/*  55 */       Date date = new Date();
/*  56 */       Long timestamp = Long.valueOf(date.getTime());
/*  57 */       token = MaxMD5Util.toMD5(randKey + uid + timestamp);
/*  58 */       setKV(key, token);
/*     */     }
/*     */ 
/*  62 */     return token;
/*     */   }
/*     */ 
/*     */   public String getDeviceToken(String uid)
/*     */   {
/*  72 */     String key = uid + "." + "DeviceToken";
/*  73 */     return getKV(key);
/*     */   }
/*     */ 
/*     */   public void setDeviceToken(String uid, String deviceToken) {
/*  77 */     if (StringUtil.isEmpty(deviceToken)) {
/*  78 */       log.error("[setDeviceToken] err: deviceToken is null");
/*  79 */       return;
/*     */     }
/*  81 */     String key = uid + "." + "DeviceToken";
/*  82 */     setKV(key, deviceToken);
/*     */   }
/*     */ 
/*     */   public void setKV(String key, String value) {
/*  86 */     this.jedisConn.set(key.getBytes(), value.getBytes());
/*     */   }
/*     */ 
/*     */   public String getKV(String key) {
/*  90 */     byte[] ret = this.jedisConn.get(key.getBytes());
/*  91 */     String str = null;
/*  92 */     if (ret != null) {
/*  93 */       str = new String(ret);
/*     */     }
/*  95 */     return str;
/*     */   }
/*     */ 
/*     */   public Boolean hsetKV(String hashName, String key, String value) {
/*  99 */     log.debug("[hsetKV] debug:hashName=" + hashName + " , key=" + key + " &value=" + value);
/*     */ 
/* 101 */     return this.jedisConn.hSet(hashName.getBytes(), key.getBytes(), value.getBytes());
/*     */   }
/*     */ 
/*     */   public String hgetKV(String hashName, String key)
/*     */   {
/* 106 */     String str = null;
/* 107 */     byte[] ret = this.jedisConn.hGet(hashName.getBytes(), key.getBytes());
/* 108 */     if (ret != null) {
/* 109 */       str = new String(ret);
/*     */     }
/* 111 */     return str;
/*     */   }
/*     */ 
/*     */   public List<byte[]> blPop(String queueName) {
/* 115 */     return this.jedisConn.bLPop(0, new byte[][] { queueName.getBytes() });
/*     */   }
/*     */ 
/*     */   public void hdel(String hashName, String key) {
/* 119 */     this.jedisConn.hDel(hashName.getBytes(), new byte[][] { key.getBytes() });
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.redis.AskRedisClient
 * JD-Core Version:    0.6.0
 */