/*     */ package cc.kokoko.server.ibutler.service.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ class FileEveryDaySerialNumber extends EveryDaySerialNumber
/*     */ {
/*  88 */   private File file = null;
/*     */   private static final String FIELD_SEPARATOR = ",";
/*     */ 
/*     */   public FileEveryDaySerialNumber(int width, String filename)
/*     */   {
/*  96 */     super(width);
/*  97 */     this.file = new File(filename);
/*     */   }
/*     */ 
/*     */   protected int getOrUpdateNumber(Date current, int start)
/*     */   {
/* 102 */     String date = format(current);
/* 103 */     int num = start;
/* 104 */     if (this.file.exists()) {
/* 105 */       List list = FileUtil.readList(this.file);
/* 106 */       String[] data = ((String)list.get(0)).split(",");
/* 107 */       if (date.equals(data[0])) {
/* 108 */         num = Integer.parseInt(data[1]);
/*     */       }
/*     */     }
/* 111 */     FileUtil.rewrite(this.file, date + "," + (num + 1));
/* 112 */     return num;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.FileEveryDaySerialNumber
 * JD-Core Version:    0.6.0
 */