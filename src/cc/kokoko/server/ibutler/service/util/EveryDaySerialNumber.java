/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ abstract class EveryDaySerialNumber extends SerialNumber
/*    */ {
/* 42 */   protected static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*    */ 
/* 44 */   protected DecimalFormat df = null;
/*    */ 
/*    */   public EveryDaySerialNumber(int width) {
/* 47 */     if (width < 1) {
/* 48 */       throw new IllegalArgumentException("Parameter length must be great than 1!");
/*    */     }
/*    */ 
/* 51 */     char[] chs = new char[width];
/* 52 */     for (int i = 0; i < width; i++) {
/* 53 */       chs[i] = '0';
/*    */     }
/* 55 */     this.df = new DecimalFormat(new String(chs));
/*    */   }
/*    */ 
/*    */   protected String process() {
/* 59 */     Date date = new Date();
/* 60 */     int n = getOrUpdateNumber(date, 1);
/* 61 */     return format(date) + format(n);
/*    */   }
/*    */ 
/*    */   protected String format(Date date) {
/* 65 */     return sdf.format(date);
/*    */   }
/*    */ 
/*    */   protected String format(int num) {
/* 69 */     return this.df.format(num);
/*    */   }
/*    */ 
/*    */   protected abstract int getOrUpdateNumber(Date paramDate, int paramInt);
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.EveryDaySerialNumber
 * JD-Core Version:    0.6.0
 */