/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Attachment
/*     */ {
/*     */   private String attId;
/*     */   private String filePath;
/*     */   private Long attSize;
/*     */   private Long uid;
/*     */   private String relationId;
/*     */   private String relationTable;
/*     */   private Date postTime;
/*     */   private String fileName;
/*     */ 
/*     */   public String getAttId()
/*     */   {
/*  79 */     return this.attId;
/*     */   }
/*     */ 
/*     */   public void setAttId(String attId)
/*     */   {
/*  91 */     this.attId = attId;
/*     */   }
/*     */ 
/*     */   public String getFilePath()
/*     */   {
/* 103 */     return this.filePath;
/*     */   }
/*     */ 
/*     */   public void setFilePath(String filePath)
/*     */   {
/* 115 */     this.filePath = filePath;
/*     */   }
/*     */ 
/*     */   public Long getAttSize()
/*     */   {
/* 127 */     return this.attSize;
/*     */   }
/*     */ 
/*     */   public void setAttSize(Long attSize)
/*     */   {
/* 139 */     this.attSize = attSize;
/*     */   }
/*     */ 
/*     */   public Long getUid()
/*     */   {
/* 151 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid)
/*     */   {
/* 163 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public String getRelationId()
/*     */   {
/* 175 */     return this.relationId;
/*     */   }
/*     */ 
/*     */   public void setRelationId(String relationId)
/*     */   {
/* 187 */     this.relationId = relationId;
/*     */   }
/*     */ 
/*     */   public String getRelationTable()
/*     */   {
/* 199 */     return this.relationTable;
/*     */   }
/*     */ 
/*     */   public void setRelationTable(String relationTable)
/*     */   {
/* 211 */     this.relationTable = relationTable;
/*     */   }
/*     */ 
/*     */   public Date getPostTime()
/*     */   {
/* 223 */     return this.postTime;
/*     */   }
/*     */ 
/*     */   public void setPostTime(Date postTime)
/*     */   {
/* 235 */     this.postTime = postTime;
/*     */   }
/*     */ 
/*     */   public String getFileName()
/*     */   {
/* 247 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public void setFileName(String fileName)
/*     */   {
/* 259 */     this.fileName = fileName;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Attachment
 * JD-Core Version:    0.6.0
 */