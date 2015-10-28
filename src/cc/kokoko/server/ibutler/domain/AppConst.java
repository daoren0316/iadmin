/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class AppConst
/*     */ {
/*  11 */   public static Map<String, String> sysConfigMap = new HashMap();
/*  12 */   public static final Long REFUND_SHOP = Long.valueOf(3L);
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 221 */     System.out.println(OrderAccount.generateOrdrNo());
/*     */   }
/*     */ 
/*     */   public static class DwzCode
/*     */   {
/*     */     public static final String SUCCESS = "outer";
/*     */   }
/*     */ 
/*     */   public static class ResultCode
/*     */   {
/* 212 */     public static final byte SUCCESS = new Byte("0").byteValue();
/* 213 */     public static final byte FAILURE = new Byte("1").byteValue();
/*     */   }
/*     */ 
/*     */   public static class RDOrderType
/*     */   {
/* 207 */     public static final Byte PAY = new Byte("1");
/* 208 */     public static final Byte FLUSHES = new Byte("2");
/*     */   }
/*     */ 
/*     */   public static class RDOrderStatus
/*     */   {
/* 202 */     public static final Byte NORMAL = new Byte("0");
/* 203 */     public static final Byte FLUSHED = new Byte("1");
/*     */   }
/*     */ 
/*     */   public static class ActionType
/*     */   {
/* 198 */     public static final Byte REG = new Byte("1");
/*     */   }
/*     */ 
/*     */   public static class OrderAccount
/*     */   {
/* 178 */     public static final Byte TYPE_ONLINE_CHARGE = new Byte("1");
/* 179 */     public static final Byte TYPE_OFFLINE_CHARGE = new Byte("2");
/* 180 */     public static final Byte TYPE_REFUND = new Byte("3");
/* 181 */     public static final Byte TYPE_FEE = new Byte("4");
/*     */ 
/* 183 */     public static final Byte STATUS_ING = new Byte("0");
/* 184 */     public static final Byte STATUS_SUC = new Byte("1");
/* 185 */     public static final Byte STATUS_LOSE = new Byte("2");
/* 186 */     public static final Byte STATUS_FAIL = new Byte("3");
/* 187 */     public static final Byte STATUS_OTHER = new Byte("9");
/*     */ 
/*     */     public static final String generateOrdrNo()
/*     */     {
/* 191 */       DateFormat formater = new SimpleDateFormat("yyyyMMddHHmmssSS");
/* 192 */       StringBuilder sb = new StringBuilder(formater.format(new Date()));
/* 193 */       return sb.toString();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class SysMoneyLog
/*     */   {
/* 172 */     public static final Byte TYPE_CHARGE = new Byte("1");
/* 173 */     public static final Byte TYPE_SHOP_CLEAR = new Byte("2");
/* 174 */     public static final Byte TYPE_PAY = new Byte("3");
/*     */   }
/*     */ 
/*     */   public static class UserType
/*     */   {
/* 162 */     public static final Byte COMMON = new Byte("0");
/* 163 */     public static final Byte SHOP = new Byte("1");
/* 164 */     public static final Byte SITE = new Byte("2");
/* 165 */     public static final Byte CONSULTANT = new Byte("3");
/* 166 */     public static final Byte OPERATOR = new Byte("5");
/* 167 */     public static final Byte ADMIN = new Byte("9");
/* 168 */     public static final Byte SYS_ADMIN = new Byte("99");
/*     */   }
/*     */ 
/*     */   public static class FavType
/*     */   {
/* 156 */     public static final Byte SHOP = new Byte("1");
/* 157 */     public static final Byte ACTIVITY = new Byte("2");
/* 158 */     public static final Byte COMMODITY = new Byte("3");
/*     */   }
/*     */ 
/*     */   public static class MySQLTime
/*     */   {
/*     */     public static final String defaultDay = "0000-00-00";
/*     */     public static final String timeOfDayStart = "00:00:00";
/*     */     public static final String timeOfDayEnd = "23:59:59";
/*     */   }
/*     */ 
/*     */   public static class Quartz
/*     */   {
/*     */     public static final String QUESTION_GROUP_NAME = "question";
/*     */     public static final String QUESTION_JOB_PREFIX = "job_q_";
/*     */     public static final String QUESTION_TRIGGER_PREFIX = "trigger_q_";
/*     */   }
/*     */ 
/*     */   public static class AnswerIsBest
/*     */   {
/* 139 */     public static final Byte FALSE = new Byte("0");
/* 140 */     public static final Byte TRUE = new Byte("1");
/*     */   }
/*     */ 
/*     */   public static class UserStatus
/*     */   {
/* 125 */     public static final Byte APP_NO_DOWN = new Byte("0");
/* 126 */     public static final Byte APP_DOWN = new Byte("1");
/* 127 */     public static final Byte APP_NO_BIND = new Byte("2");
/*     */ 
/* 129 */     public static final Byte CARD_NO_HAS = new Byte("0");
/* 130 */     public static final Byte CARD_HAS = new Byte("1");
/*     */ 
/* 132 */     public static final Byte NORMAL = new Byte("0");
/* 133 */     public static final Byte FORBIDDEN = new Byte("1");
/*     */     public static final int LEVEL_DEFAULT = 0;
/*     */     public static final int GESTATION_DAYS = 280;
/*     */   }
/*     */ 
/*     */   public static class CoinsChangeAmount
/*     */   {
/*     */     public static final int ANSWER_FIRST = 1;
/*     */     public static final int ANSWER_OTHER = 1;
/*     */     public static final int APPRECIATION = 0;
/*     */     public static final int ANSWER_ACCEPTED = 10;
/*     */     public static final int QUESTION_OWNER_ACCEPT = 1;
/*     */     public static final int INVITE_SUCCESS = 30;
/*     */   }
/*     */ 
/*     */   public static class CoinsChangeType
/*     */   {
/* 100 */     public static final Byte NONE = new Byte("0");
/* 101 */     public static final Byte GIFT_PAY = new Byte("1");
/* 102 */     public static final Byte ANSWER_FIRST = new Byte("2");
/* 103 */     public static final Byte ANSWER_OTHER = new Byte("3");
/* 104 */     public static final Byte APPRECIATION_BY_OWNER = new Byte("4");
/* 105 */     public static final Byte ACCEPT_BY_OWNER = new Byte("5");
/* 106 */     public static final Byte QUESTION_OWNER_ACCEPT = new Byte("6");
/* 107 */     public static final Byte DAILY_CHECKIN = new Byte("7");
/* 108 */     public static final Byte DAILY_SHARE = new Byte("8");
/* 109 */     public static final Byte INVITE_SUCCESS = new Byte("9");
/*     */ 
/* 111 */     public static final Byte COUNSELLOR = new Byte("12");
/*     */   }
/*     */ 
/*     */   public static class Page
/*     */   {
/*     */     public static final int DEFAULT_PAGE_SIZE = 20;
/*     */     public static final int ARTICLE_DEFAULT_PAGE_SIZE = 10;
/*     */   }
/*     */ 
/*     */   public static class Sex
/*     */   {
/*  90 */     public static final Byte MALE = new Byte("1");
/*  91 */     public static final Byte FEMALE = new Byte("2");
/*     */   }
/*     */ 
/*     */   public static class SysUser
/*     */   {
/*  86 */     public static final Long SYS_USER = Long.valueOf(10000L);
/*     */   }
/*     */ 
/*     */   public static class ThumbnailTAG
/*     */   {
/*     */     public static final String BIG = "_big";
/*     */     public static final String SMALL = "_small";
/*     */     public static final String NORMAL = "";
/*     */   }
/*     */ 
/*     */   public static class TradeType
/*     */   {
/*  68 */     public static final Byte TYPE_CHARGE = new Byte("1");
/*  69 */     public static final Byte TYPE_UMPM_CHARGE = new Byte("8");
/*  70 */     public static final Byte TYPE_COMMODITY_PAY = new Byte("2");
/*  71 */     public static final Byte TYPE_POS_PAY = new Byte("5");
/*  72 */     public static final Byte TYPE_FEE_PAY = new Byte("6");
/*  73 */     public static final Byte TYPE_PACK_PAY = new Byte("7");
/*  74 */     public static final Byte TYPE_TRANS = new Byte("3");
/*  75 */     public static final Byte TYPE_TRANS_IN = new Byte("4");
/*  76 */     public static final Byte TYPE_REFUND = new Byte("9");
/*     */   }
/*     */ 
/*     */   public static class TradeStatus
/*     */   {
/*  63 */     public static final Byte TYPE_ING = new Byte("0");
/*  64 */     public static final Byte TYPE_DONE = new Byte("1");
/*     */   }
/*     */ 
/*     */   public static class PaymentStatus
/*     */   {
/*  58 */     public static final Byte WAITING_FOR_PAY = new Byte("0");
/*  59 */     public static final Byte PAY_SUCC = new Byte("1");
/*     */   }
/*     */ 
/*     */   public static class OrderStatus
/*     */   {
/*  54 */     public static final Byte NORMAL = new Byte("0");
/*     */   }
/*     */ 
/*     */   public static class RecurrenceType
/*     */   {
/*  42 */     public static final Byte ONE_TIME = new Byte("0");
/*  43 */     public static final Byte EVERY_DAY = new Byte("1");
/*  44 */     public static final Byte EVERY_WEEK = new Byte("2");
/*  45 */     public static final Byte EVERY_MONTH = new Byte("3");
/*     */   }
/*     */ 
/*     */   public static class NoticeFor
/*     */   {
/*  30 */     public static final Byte BOTH = new Byte("0");
/*  31 */     public static final Byte MALE = new Byte("1");
/*  32 */     public static final Byte FEMALE = new Byte("2");
/*     */   }
/*     */ 
/*     */   public static class FeedbackCategory
/*     */   {
/*  22 */     public static final Byte REPAIR = Byte.valueOf("1");
/*  23 */     public static final Byte CONSULT = Byte.valueOf("2");
/*  24 */     public static final Byte COMPLAIN = Byte.valueOf("3");
/*     */ 
/*  26 */     public static final Byte DEFAULT_TYPE = Byte.valueOf("0");
/*     */   }
/*     */ 
/*     */   public static class MessageType
/*     */   {
/*  15 */     public static final Byte SYS_NOTICE = new Byte("100");
/*  16 */     public static final Byte NEW_VERSION = new Byte("101");
/*  17 */     public static final Byte P2P_MESSAGE = new Byte("102");
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.AppConst
 * JD-Core Version:    0.6.0
 */