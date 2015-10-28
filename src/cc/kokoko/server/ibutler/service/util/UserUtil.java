/*     */ package cc.kokoko.server.ibutler.service.util;
/*     */ 
/*     */ import cc.kokoko.server.commons.exception.KeepDataException;
/*     */ import cc.kokoko.server.commons.util.DateUtil;
/*     */ import cc.kokoko.server.commons.util.MD5Util;
/*     */ import cc.kokoko.server.commons.util.MMGlobals;
/*     */ import cc.kokoko.server.commons.util.MaxMD5Util;
/*     */ import cc.kokoko.server.commons.util.MaxMathUtil;
/*     */ import cc.kokoko.server.commons.util.StringUtil;
/*     */ import cc.kokoko.server.ibutler.domain.AppConst;
/*     */ import cc.kokoko.server.ibutler.domain.AppConst.UserStatus;
/*     */ import cc.kokoko.server.ibutler.domain.User;
/*     */ import cc.kokoko.server.ibutler.persistence.UserMapper;
/*     */ import java.io.PrintStream;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class UserUtil
/*     */ {
/*     */   public static final String XMPP_USER_PREFIX = "ibutler_";
/*     */   public static final String XMPP_RESOURCE_PREFIX = "r";
/*  18 */   private static Logger log = LoggerFactory.getLogger(UserUtil.class);
/*     */ 
/*     */   public static User createUser(String nickname, String email, String password)
/*     */   {
/*  22 */     if (nickname == null) {
/*  23 */       nickname = "";
/*     */     }
/*  25 */     User u = new User();
/*  26 */     u.setEmail(email);
/*  27 */     u.setPassword(MaxMD5Util.toMD5(password));
/*  28 */     String token = MaxMD5Util.toMD5(UUID.randomUUID() + "@@_" + nickname + "_" + new Date().toString());
/*     */ 
/*  30 */     u.setToken(token);
/*  31 */     u.setNickname(nickname);
/*  32 */     return u;
/*     */   }
/*     */ 
/*     */   public static User createUser(String phoneNumber, String password) {
/*  36 */     String nickname = "";
/*  37 */     User u = new User();
/*  38 */     String token = MaxMD5Util.createToken(nickname);
/*  39 */     u.setToken(token);
/*  40 */     String passwordMd5 = MaxMD5Util.toMD5(password);
/*  41 */     u.setNickname(nickname);
/*  42 */     u.setPhoneNumber(phoneNumber);
/*  43 */     u.setPassword(passwordMd5);
/*  44 */     u.setAppStatus(AppConst.UserStatus.APP_NO_DOWN);
/*  45 */     u.setCardStatus(AppConst.UserStatus.CARD_NO_HAS);
/*  46 */     return u;
/*     */   }
/*     */ 
/*     */   public static User createUser(String nickname, Byte gender, String profileImage, String token)
/*     */   {
/*  52 */     User u = new User();
/*  53 */     u.setToken(token);
/*  54 */     u.setGender(gender);
/*  55 */     u.setNickname(nickname);
/*  56 */     return u;
/*     */   }
/*     */ 
/*     */   public static boolean checkToken(Long uid, String token, UserMapper userMapper)
/*     */   {
/*  62 */     String LOG_TAG = "[checkToken]";
/*  63 */     log.debug(LOG_TAG + " uid=" + uid + " ,token=" + token);
/*  64 */     boolean ret = false;
/*  65 */     if (!StringUtil.isEmpty(token)) {
/*  66 */       User user = userMapper.getUserByUid(uid);
/*  67 */       ret = checkToken(user, token);
/*     */     }
/*  69 */     return ret;
/*     */   }
/*     */ 
/*     */   public static boolean checkToken(User user, String token) {
/*  73 */     if ((!StringUtil.isEmpty(token)) && (((String)AppConst.sysConfigMap.get("admin_token")).equals(token)))
/*     */     {
/*  75 */       log.debug("系统管理员调用 token=" + token);
/*  76 */       return true;
/*     */     }
/*  78 */     boolean ret = false;
/*  79 */     if ((user != null) && (token != null) && (token.equals(user.getToken())))
/*     */     {
/*  82 */       ret = true;
/*     */     }
/*  84 */     if (!ret) {
/*  85 */       log.error("[checkToken] err: token=" + token + ", user=" + ToStringBuilder.reflectionToString(user));
/*     */     }
/*     */ 
/*  88 */     return ret;
/*     */   }
/*     */ 
/*     */   public static boolean checkPassword(User user, String password) {
/*  92 */     boolean ret = false;
/*  93 */     String str = MD5Util.getMD5String(password);
/*  94 */     if ((user != null) && (password != null) && (str.equals(user.getPassword()))) {
/*  95 */       ret = true;
/*     */     }
/*  97 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String getUserAvatarPrefix() {
/* 101 */     String prefix = MMGlobals.getProperty("FILE_HTTP_PREFIX");
/* 102 */     prefix = prefix + "/" + MMGlobals.getProperty("APP_NAME");
/*     */ 
/* 104 */     return prefix;
/*     */   }
/*     */ 
/*     */   public static String getUserAvatar(String profileImage) {
/* 108 */     String ret = null;
/* 109 */     String prefix = getUserAvatarPrefix();
/* 110 */     if (!StringUtil.isEmpty(profileImage)) {
/* 111 */       if ("http".equals(profileImage.substring(0, 4).toLowerCase())) {
/* 112 */         ret = profileImage;
/*     */       } else {
/* 114 */         if (!"/".equals(profileImage.substring(0, 1))) {
/* 115 */           profileImage = "/" + profileImage;
/*     */         }
/* 117 */         ret = prefix + profileImage;
/*     */       }
/*     */     }
/*     */ 
/* 121 */     log.debug("prefix=" + prefix + ", profileImage=" + profileImage);
/*     */ 
/* 123 */     return ret;
/*     */   }
/*     */ 
/*     */   public static User getUserProfile(User u, Long invokerUid)
/*     */   {
/* 135 */     if (u == null) {
/* 136 */       log.error("[getUserProfile] 未找到用户");
/* 137 */       return new User();
/*     */     }
/* 139 */     u.setPassword("");
/* 140 */     if (!invokerUid.equals(u.getUid())) {
/* 141 */       u.setToken("");
/*     */     }
/*     */ 
/* 144 */     u.setProfileImage(getUserAvatar(u.getProfileImage()));
/* 145 */     return u;
/*     */   }
/*     */ 
/*     */   public static List<User> getProfileList(List<User> userList, Long invokerUid)
/*     */   {
/* 154 */     List upList = new ArrayList();
/*     */ 
/* 156 */     for (User u : userList) {
/* 157 */       upList.add(getUserProfile(u, invokerUid));
/*     */     }
/*     */ 
/* 160 */     return upList;
/*     */   }
/*     */ 
/*     */   public static Long getUidFromOfUsername(String username) {
/* 164 */     if (StringUtil.isEmpty(username)) {
/* 165 */       return null;
/*     */     }
/* 167 */     String str = username.replace("ibutler_", "");
/* 168 */     if ((username.length() < 5) || (!"ibutler_".equals(username.substring(0, 4)))) {
/* 169 */       return Long.valueOf(0L);
/*     */     }
/* 171 */     return Long.valueOf(Long.parseLong(str));
/*     */   }
/*     */ 
/*     */   public static int getAgeOfDay(Date birthday)
/*     */   {
/* 181 */     int num = 0;
/* 182 */     if (birthday == null) {
/* 183 */       log.debug("[getAgeOfDay] birthday is null");
/* 184 */       return num;
/*     */     }
/*     */     try {
/* 187 */       num = DateUtil.daysBetween(birthday, new Date());
/*     */     } catch (ParseException e) {
/* 189 */       log.error("[getAgeOfDay] err " + e.getMessage() + "\r\n" + e.getStackTrace());
/*     */     }
/*     */     catch (Exception e1) {
/* 192 */       log.error("[getAgeOfDay]err1: " + e1.getStackTrace());
/*     */     }
/* 194 */     System.out.println("num=" + num);
/* 195 */     return num;
/*     */   }
/*     */ 
/*     */   public static int getSameAgeDaysDiff(Date birthday)
/*     */   {
/* 214 */     int ret = 0;
/* 215 */     int days = getAgeOfDay(birthday);
/* 216 */     if (days < 1)
/* 217 */       ret = 18;
/* 218 */     else if ((days > 0) && (days < 365))
/* 219 */       ret = 46;
/* 220 */     else if ((days > 364) && (days < 1095))
/* 221 */       ret = 76;
/*     */     else {
/* 223 */       ret = 107;
/*     */     }
/* 225 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String getXMPPUsername(Long uid) {
/* 229 */     return "ibutler_" + uid;
/*     */   }
/*     */ 
/*     */   public static String getXMPPUsername(String uid) {
/* 233 */     return "ibutler_" + uid;
/*     */   }
/*     */ 
/*     */   public static String getXMPPResource(Long uid) {
/* 237 */     return "r";
/*     */   }
/*     */ 
/*     */   public static Map<String, Integer> getArticleDaysMap(Date birthday, int groupIdOfAge1)
/*     */   {
/* 248 */     Map retMap = new HashMap();
/* 249 */     int groupId = 1;
/* 250 */     int dayType = 1;
/* 251 */     int dayCount = 1;
/* 252 */     if (birthday == null) {
/* 253 */       retMap.put("dayType", Integer.valueOf(groupId));
/* 254 */       retMap.put("dayCount", Integer.valueOf(1));
/* 255 */       return retMap;
/*     */     }
/* 257 */     int ageOfDay = getAgeOfDay(birthday);
/* 258 */     if (ageOfDay < -280) {
/* 259 */       groupId = 1;
/* 260 */     } else if ((ageOfDay > -281) && (ageOfDay < 365))
/*     */     {
/* 262 */       int days = 280 + ageOfDay;
/* 263 */       if (ageOfDay > 0) {
/* 264 */         dayCount = ageOfDay;
/* 265 */         dayType = 3;
/*     */       } else {
/* 267 */         dayType = 2;
/* 268 */         dayCount = days;
/*     */       }
/*     */ 
/* 271 */       int gestationWeek = (int)Math.floor(days / 7) + 1;
/* 272 */       System.out.println("days=" + days + ", gestationWeek=" + gestationWeek);
/* 273 */       groupId = 1 + gestationWeek;
/*     */     } else {
/* 275 */       int age = (int)Math.ceil(Math.abs(ageOfDay) / 365);
/* 276 */       dayCount = ageOfDay;
/* 277 */       dayType = 3;
/* 278 */       groupId = groupIdOfAge1 + age;
/*     */     }
/* 280 */     retMap.put("dayType", Integer.valueOf(dayType));
/* 281 */     retMap.put("dayCount", Integer.valueOf(dayCount));
/* 282 */     return retMap;
/*     */   }
/*     */ 
/*     */   public static int getRandConis(Long uid) {
/* 286 */     if (AppConst.sysConfigMap == null)
/*     */     {
/* 288 */       throw new KeepDataException("AppConst.sysConfigMap is null");
/*     */     }
/* 290 */     String cfgDailyCheckinCoinsMin = (String)AppConst.sysConfigMap.get("dailyCheckinCoinsMin");
/* 291 */     String cfgDailyCheckinCoinsMax = (String)AppConst.sysConfigMap.get("dailyCheckinCoinsMax");
/* 292 */     if (StringUtil.isEmpty(cfgDailyCheckinCoinsMin)) {
/* 293 */       cfgDailyCheckinCoinsMin = "0";
/*     */     }
/* 295 */     if (StringUtil.isEmpty(cfgDailyCheckinCoinsMax)) {
/* 296 */       cfgDailyCheckinCoinsMax = "10";
/*     */     }
/* 298 */     long min = Long.parseLong(cfgDailyCheckinCoinsMin);
/* 299 */     long max = Long.parseLong(cfgDailyCheckinCoinsMax);
/* 300 */     long rand = MaxMathUtil.getRand(min, max);
/* 301 */     return Integer.parseInt(rand + "");
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 305 */     Date birthday = DateUtil.getDateFromStr("2011-11-29", "yyyy-MM-dd");
/* 306 */     System.out.println(getArticleDaysMap(birthday, 90));
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.UserUtil
 * JD-Core Version:    0.6.0
 */