/*     */ package cc.kokoko.server.ibutler.service.util;
/*     */ 
/*     */ import cc.kokoko.server.commons.util.DateUtil;
/*     */ import cc.kokoko.server.commons.util.MMGlobals;
/*     */ import cc.kokoko.server.commons.util.MaxFileUtil;
/*     */ import cc.kokoko.server.commons.util.MaxImageUtil;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Date;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class KeepDownloader
/*     */ {
/*  25 */   private static Logger log = LoggerFactory.getLogger(KeepDownloader.class);
/*     */   private String baseDir;
/*     */   private String thumbnailUrl;
/*     */   private Long filesize;
/*     */   private String clientFileName;
/*     */   private String url;
/*     */   private String filePath;
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  34 */     KeepDownloader keepDownloader = new KeepDownloader("/data1/uploads/yunwu/");
/*     */     try
/*     */     {
/*  37 */       String str = keepDownloader.downloadFile("http://img1.gtimg.com/news/pics/hv1/132/208/1169/76067397.jpg", "ttt-iii", "3");
/*     */ 
/*  41 */       log.debug(str);
/*     */     } catch (IOException e) {
/*  43 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public KeepDownloader()
/*     */   {
/*     */   }
/*     */ 
/*     */   public KeepDownloader(String baseDir) {
/*  52 */     this.baseDir = baseDir;
/*     */   }
/*     */ 
/*     */   public String downloadFile(String urlString, String attachmentID, String uid) throws IOException
/*     */   {
/*  57 */     URL url = null;
/*  58 */     URLConnection con = null;
/*  59 */     InputStream is = null;
/*     */     try
/*     */     {
/*  62 */       url = new URL(urlString);
/*     */ 
/*  64 */       con = url.openConnection();
/*  65 */       con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5");
/*  66 */       int index = urlString.indexOf("/", 10);
/*     */ 
/*  68 */       con.setRequestProperty("Referer", "http://user.qzone.qq.com/8152911");
/*  69 */       String lContentType = con.getContentType();
/*  70 */       String imagename = "";
/*  71 */       if ((lContentType.indexOf("jpg") != -1) || (lContentType.indexOf("jpeg") != -1))
/*     */       {
/*  73 */         imagename = attachmentID + ".jpg";
/*  74 */       } else if (lContentType.indexOf("png") != -1)
/*  75 */         imagename = attachmentID + ".png";
/*  76 */       else if (lContentType.indexOf("gif") != -1)
/*  77 */         imagename = attachmentID + ".gif";
/*  78 */       else if (lContentType.indexOf("mp4") != -1)
/*  79 */         imagename = attachmentID + ".mp4";
/*     */       else {
/*  81 */         return null;
/*     */       }
/*  83 */       FileOutputStream fos = null;
/*  84 */       OutputStream os = null;
/*     */       try {
/*  86 */         is = con.getInputStream();
/*  87 */         createDir(this.baseDir + getPath(uid));
/*  88 */         File lFile = new File(this.baseDir + getPath(uid) + imagename);
/*  89 */         fos = new FileOutputStream(lFile);
/*  90 */         os = new BufferedOutputStream(fos);
/*  91 */         byte[] buffer = new byte[1024];
/*  92 */         int byteRead = 0;
/*  93 */         while ((byteRead = is.read(buffer)) != -1) {
/*  94 */           os.write(buffer, 0, byteRead);
/*     */         }
/*  96 */         os.flush();
/*  97 */         String imgFile = this.baseDir + getPath(uid) + imagename;
/*  98 */         makeThumbnai(imgFile);
/*     */ 
/* 100 */         String str1 = imgFile;
/*     */         return str1;
/*     */       }
/*     */       catch (Exception e2)
/*     */       {
/* 102 */         e2.printStackTrace();
/* 103 */         log.error("save File failed: uid=" + uid + " &attid=" + attachmentID + e2.getMessage());
/*     */       }
/*     */       finally {
/* 106 */         if (is != null)
/* 107 */           is.close();
/* 108 */         if (os != null)
/* 109 */           os.close();
/* 110 */         if (fos != null)
/* 111 */           fos.close();
/*     */       }
/*     */     }
/*     */     catch (Exception e1)
/*     */     {
/*     */     }
/* 117 */     return null;
/*     */   }
/*     */ 
/*     */   public String uploadFile(InputStream is, String filename, String attachmentID, String uid)
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 127 */       log.debug("filename=" + filename);
/* 128 */       this.clientFileName = filename;
/* 129 */       String imagename = "";
/* 130 */       boolean isImg = true;
/* 131 */       if ((filename.indexOf("jpg") != -1) || (filename.indexOf("jpeg") != -1)) {
/* 132 */         imagename = attachmentID + ".jpg";
/* 133 */       } else if (filename.indexOf("png") != -1) {
/* 134 */         imagename = attachmentID + ".png";
/* 135 */       } else if (filename.indexOf("gif") != -1) {
/* 136 */         imagename = attachmentID + ".gif";
/* 137 */       } else if (filename.indexOf("mp4") != -1) {
/* 138 */         isImg = false;
/* 139 */         imagename = attachmentID + ".mp4";
/*     */       } else {
/* 141 */         isImg = false;
/* 142 */         imagename = attachmentID + "." + MaxFileUtil.getExtension(filename);
/*     */       }
/*     */ 
/* 146 */       FileOutputStream fos = null;
/* 147 */       OutputStream os = null;
/*     */       try {
/* 149 */         createDir(this.baseDir + getPath(uid));
/* 150 */         File lFile = new File(this.baseDir + getPath(uid) + imagename);
/* 151 */         fos = new FileOutputStream(lFile);
/* 152 */         os = new BufferedOutputStream(fos);
/* 153 */         byte[] buffer = new byte[1024];
/* 154 */         int byteRead = 0;
/* 155 */         while ((byteRead = is.read(buffer)) != -1) {
/* 156 */           os.write(buffer, 0, byteRead);
/*     */         }
/* 158 */         os.flush();
/* 159 */         String imgFile = this.baseDir + getPath(uid) + imagename;
/* 160 */         if (isImg == true) {
/* 161 */           log.debug("上传图片文件 -- " + imgFile + " --，生成缩略图 ");
/* 162 */           makeThumbnai(imgFile);
/*     */         }
/*     */ 
/* 165 */         this.url = (MMGlobals.getProperty("FILE_HTTP_PREFIX") + imgFile.replace(MMGlobals.getProperty("uploadPath"), "/"));
/*     */ 
/* 168 */         this.filesize = Long.valueOf(lFile.length());
/* 169 */         this.filePath = (getPath(uid) + imagename);
/* 170 */         String str1 = imgFile;
/*     */         return str1;
/*     */       }
/*     */       catch (Exception e2)
/*     */       {
/* 172 */         e2.printStackTrace();
/* 173 */         log.error("save File failed: uid=" + uid + " &attid=" + attachmentID + e2.getMessage());
/*     */       }
/*     */       finally {
/* 176 */         if (is != null)
/* 177 */           is.close();
/* 178 */         if (os != null)
/* 179 */           os.close();
/* 180 */         if (fos != null)
/* 181 */           fos.close();
/*     */       }
/*     */     }
/*     */     catch (Exception e1)
/*     */     {
/*     */     }
/* 187 */     return null;
/*     */   }
/*     */ 
/*     */   private String getPath(String uid)
/*     */   {
/* 192 */     DecimalFormat df = new DecimalFormat("00000000");
/* 193 */     String str = df.format(Long.parseLong(uid));
/* 194 */     StringBuffer sb = new StringBuffer();
/* 195 */     sb.append(str.substring(0, 2));
/* 196 */     sb.append("/");
/* 197 */     sb.append(str.substring(2, 4));
/* 198 */     sb.append("/");
/* 199 */     sb.append(str.substring(4, 6));
/* 200 */     sb.append("/");
/* 201 */     sb.append(str.substring(6, 8));
/* 202 */     sb.append("/");
/* 203 */     String dateStr = DateUtil.getDayFromTimeStamp(Long.valueOf(new Date().getTime()));
/* 204 */     sb.append(dateStr.replace("-", ""));
/* 205 */     sb.append("/");
/*     */ 
/* 207 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private void createDir(String dirPath) {
/*     */     try {
/* 212 */       String[] paths = dirPath.split("/");
/* 213 */       String lPath = "/";
/* 214 */       for (int i = 0; i < paths.length; i++)
/* 215 */         if (!paths[i].trim().equals("")) {
/* 216 */           lPath = lPath + paths[i] + "/";
/* 217 */           File lFile = new File(lPath);
/* 218 */           if (!lFile.exists())
/* 219 */             lFile.mkdir();
/*     */         }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 224 */       log.error("create dir failed: dirPath=" + dirPath);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void makeThumbnai(String imgFilePath) {
/* 229 */     MaxImageUtil.thumbImageByImagePHP(imgFilePath);
/* 230 */     String[] arr = imgFilePath.split("\\.");
/* 231 */     String str = "";
/* 232 */     if (arr.length > 1) {
/* 233 */       str = arr[0] + "_small" + "." + arr[1];
/*     */     }
/* 235 */     this.thumbnailUrl = (MMGlobals.getProperty("FILE_HTTP_PREFIX") + str.replace(MMGlobals.getProperty("uploadPath"), "/"));
/*     */ 
/* 237 */     log.debug("thumbnailUrl=" + this.thumbnailUrl + ", imgFilePath=" + imgFilePath);
/*     */   }
/*     */ 
/*     */   public String getBaseDir()
/*     */   {
/* 242 */     return this.baseDir;
/*     */   }
/*     */ 
/*     */   public void setBaseDir(String baseDir) {
/* 246 */     this.baseDir = baseDir;
/*     */   }
/*     */ 
/*     */   public String getThumbnailUrl() {
/* 250 */     return this.thumbnailUrl;
/*     */   }
/*     */ 
/*     */   public void setThumbnailUrl(String thumbnailUrl) {
/* 254 */     this.thumbnailUrl = thumbnailUrl;
/*     */   }
/*     */ 
/*     */   public Long getFilesize() {
/* 258 */     return this.filesize;
/*     */   }
/*     */ 
/*     */   public void setFilesize(Long filesize) {
/* 262 */     this.filesize = filesize;
/*     */   }
/*     */ 
/*     */   public String getClientFileName() {
/* 266 */     return this.clientFileName;
/*     */   }
/*     */ 
/*     */   public void setClientFileName(String clientFileName) {
/* 270 */     this.clientFileName = clientFileName;
/*     */   }
/*     */ 
/*     */   public String getUrl() {
/* 274 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 278 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getfilePath() {
/* 282 */     return this.filePath;
/*     */   }
/*     */ 
/*     */   public void setfilePath(String filePath) {
/* 286 */     this.filePath = filePath;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.KeepDownloader
 * JD-Core Version:    0.6.0
 */