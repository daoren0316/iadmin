/*     */ package cc.kokoko.server.ibutler.service.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ class FileUtil
/*     */ {
/*     */   public static void rewrite(File file, String data)
/*     */   {
/* 118 */     BufferedWriter bw = null;
/*     */     try {
/* 120 */       bw = new BufferedWriter(new FileWriter(file));
/* 121 */       bw.write(data);
/*     */     } catch (IOException e) {
/* 123 */       e.printStackTrace();
/*     */     } finally {
/* 125 */       if (bw != null)
/*     */         try {
/* 127 */           bw.close();
/*     */         } catch (IOException e) {
/* 129 */           e.printStackTrace();
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List<String> readList(File file)
/*     */   {
/* 136 */     BufferedReader br = null;
/* 137 */     List data = new ArrayList();
/*     */     try {
/* 139 */       br = new BufferedReader(new FileReader(file));
/* 140 */       for (String str = null; (str = br.readLine()) != null; )
/* 141 */         data.add(str);
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*     */       String str;
/* 144 */       e.printStackTrace();
/*     */     } finally {
/* 146 */       if (br != null) {
/*     */         try {
/* 148 */           br.close();
/*     */         } catch (IOException e) {
/* 150 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 154 */     return data;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.FileUtil
 * JD-Core Version:    0.6.0
 */