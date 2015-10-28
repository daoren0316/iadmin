/*     */ package cc.kokoko.server.ibutler.service.redis;
/*     */ 
/*     */ import cc.kokoko.server.commons.util.MaxMD5Util;
/*     */ import cc.kokoko.server.commons.util.StringUtil;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import redis.clients.jedis.Jedis;
/*     */ import redis.clients.jedis.JedisPool;
/*     */ 
/*     */ public class YunWuJedisClient
/*     */ {
/*  17 */   private static Logger log = LoggerFactory.getLogger(YunWuJedisClient.class);
/*     */   private static final String REDIS_USER_HASH = "UserStatusSet";
/*     */   private static final String DEVICE_TOKEN = "DeviceToken";
/*  23 */   private JedisPool jedisPool = null;
/*     */   private Jedis jedis;
/*  25 */   int connectCount = 0;
/*     */ 
/*     */   public YunWuJedisClient(JedisPool jedisPool) {
/*  28 */     this.jedisPool = jedisPool;
/*     */   }
/*     */ 
/*     */   public boolean isOnline(String uid, String resource) {
/*  32 */     boolean res = false;
/*  33 */     boolean isBroken = false;
/*     */     try {
/*  35 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/*  36 */       res = this.jedis.sismember("UserStatusSet", uid + ":" + resource).booleanValue();
/*     */     }
/*     */     catch (Exception e) {
/*  39 */       isBroken = true;
/*  40 */       log.error("[isOnline] err: " + e.getMessage());
/*     */     } finally {
/*  42 */       release(this.jedis, isBroken);
/*     */     }
/*  44 */     return res;
/*     */   }
/*     */ 
/*     */   public String createToken(String uid, String randKey)
/*     */   {
/*  54 */     if ((uid == null) || ("".equals(uid))) {
/*  55 */       return null;
/*     */     }
/*  57 */     String token = null;
/*  58 */     String key = uid + "." + "Token";
/*  59 */     boolean isBroken = false;
/*     */     try {
/*  61 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/*  62 */       String v = this.jedis.get(key);
/*  63 */       if (v != null) {
/*  64 */         log.debug("get token from redis " + v);
/*  65 */         token = v;
/*     */       } else {
/*  67 */         Date date = new Date();
/*  68 */         Long timestamp = Long.valueOf(date.getTime());
/*  69 */         token = MaxMD5Util.toMD5(randKey + uid + timestamp);
/*     */         try {
/*  71 */           String res = this.jedis.set(key, token);
/*     */         }
/*     */         catch (Exception e)
/*     */         {
///*     */           String res;
/*  77 */           log.error("createToken err: " + e.getMessage());
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/*  81 */       isBroken = true;
/*  82 */       log.error("[updateMessageStatus] err: " + e.getMessage());
/*     */     } finally {
/*  84 */       release(this.jedis, isBroken);
/*     */     }
/*     */ 
/*  87 */     return token;
/*     */   }
/*     */ 
/*     */   public String getDeviceToken(String uid)
/*     */   {
/*  97 */     String deviceToken = null;
/*  98 */     boolean isBroken = false;
/*     */     try {
/* 100 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/* 101 */       String key = uid + "." + "DeviceToken";
/* 102 */       deviceToken = this.jedis.get(key);
/*     */     } catch (Exception e) {
/* 104 */       isBroken = true;
/* 105 */       log.error("[getDeviceToken] err: " + e.getMessage());
/*     */     } finally {
/* 107 */       release(this.jedis, isBroken);
/*     */     }
/* 109 */     return deviceToken;
/*     */   }
/*     */ 
/*     */   public void setDeviceToken(String uid, String deviceToken) {
/* 113 */     if (StringUtil.isEmpty(deviceToken)) {
/* 114 */       log.error("[setDeviceToken] err: deviceToken is null");
/* 115 */       return;
/*     */     }
/* 117 */     boolean isBroken = false;
/*     */     try {
/* 119 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/* 120 */       String key = uid + "." + "DeviceToken";
/* 121 */       this.jedis.set(key, deviceToken);
/*     */     } catch (Exception e) {
/* 123 */       log.error("[setDeviceToken] err: " + e.getMessage());
/*     */     } finally {
/* 125 */       release(this.jedis, isBroken);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setKV(String key, String value) {
/* 130 */     boolean isBroken = false;
/*     */     try {
/* 132 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/* 133 */       this.jedis.set(key, value);
/*     */     } catch (Exception e) {
/* 135 */       isBroken = true;
/* 136 */       log.error("[setKV] err: " + e.getMessage());
/*     */     } finally {
/* 138 */       release(this.jedis, isBroken);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getKV(String key) {
/* 143 */     String value = null;
/* 144 */     boolean isBroken = false;
/*     */     try {
/* 146 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/* 147 */       value = this.jedis.get(key);
/*     */     } catch (Exception e) {
/* 149 */       log.error("[getKV] err: " + e.getMessage());
/*     */     } finally {
/* 151 */       release(this.jedis, isBroken);
/*     */     }
/*     */ 
/* 154 */     return value;
/*     */   }
/*     */ 
/*     */   public Long hsetKV(String hashName, String key, String value) {
/* 158 */     log.debug("[hsetKV] debug:hashName=" + hashName + " , key=" + key + " &value=" + value);
/*     */ 
/* 160 */     Long res = null;
/* 161 */     boolean isBroken = false;
/*     */     try {
/* 163 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/* 164 */       res = this.jedis.hset(hashName, key, value);
/*     */     }
/*     */     catch (Exception e) {
/* 167 */       log.error("[hsetKV] err: " + e.getMessage());
/*     */     } finally {
/* 169 */       isBroken = true;
/* 170 */       release(this.jedis, isBroken);
/*     */     }
/*     */ 
/* 173 */     return res;
/*     */   }
/*     */ 
/*     */   public String hgetKV(String hashName, String key) {
/* 177 */     String res = null;
/* 178 */     boolean isBroken = false;
/*     */     try {
/* 180 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/* 181 */       res = this.jedis.hget(hashName, key);
/*     */     } catch (Exception e) {
/* 183 */       isBroken = true;
/* 184 */       e.printStackTrace();
/* 185 */       log.error("[hgetKV] err: " + e.getMessage());
/*     */     } finally {
/* 187 */       release(this.jedis, isBroken);
/*     */     }
/* 189 */     return res;
/*     */   }
/*     */ 
/*     */   public List<String> blPop(String queueName) {
/* 193 */     List pushMessages = null;
/* 194 */     boolean isBroken = false;
/*     */     try {
/* 196 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/* 197 */       pushMessages = this.jedis.blpop(0, new String[] { queueName });
/*     */     } catch (Exception e) {
/* 199 */       isBroken = true;
/* 200 */       log.error("[blPop] err: " + e.getMessage());
/*     */     } finally {
/* 202 */       release(this.jedis, isBroken);
/*     */     }
/* 204 */     return pushMessages;
/*     */   }
/*     */ 
/*     */   public Long hdel(String hashName, String key) {
/* 208 */     Long res = null;
/* 209 */     boolean isBroken = false;
/*     */     try {
/* 211 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/* 212 */       res = this.jedis.hdel(hashName, key);
/*     */     } catch (Exception e) {
/* 214 */       isBroken = true;
/* 215 */       e.printStackTrace();
/* 216 */       log.error("[hdel] err: " + e.getMessage());
/*     */     } finally {
/* 218 */       release(this.jedis, isBroken);
/*     */     }
/* 220 */     return res;
/*     */   }
/*     */ 
/*     */   public String info() {
/* 224 */     String res = null;
/* 225 */     boolean isBroken = false;
/*     */     try {
/* 227 */       this.jedis = ((Jedis)this.jedisPool.getResource());
/* 228 */       res = this.jedis.info();
/*     */     } catch (Exception e) {
/* 230 */       isBroken = true;
/* 231 */       e.printStackTrace();
/* 232 */       log.error("[info] err: " + e.getMessage());
/*     */     } finally {
/* 234 */       release(this.jedis, isBroken);
/*     */     }
/* 236 */     return res;
/*     */   }
/*     */ 
/*     */   protected void release(Jedis jedis, boolean isBroken)
/*     */   {
/* 241 */     if (jedis != null)
/*     */     {
/* 243 */       if (isBroken)
/*     */       {
/* 245 */         this.jedisPool.returnBrokenResource(jedis);
/*     */       }
/*     */       else
/*     */       {
/* 249 */         this.jedisPool.returnResource(jedis);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.redis.YunWuJedisClient
 * JD-Core Version:    0.6.0
 */