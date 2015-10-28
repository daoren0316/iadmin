/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class SysActivityConfig
/*     */ {
/*     */   private Long id;
/*     */   private Double money;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private Date createdTime;
/*     */   private Date updateTime;
/*     */   private int flag;
/*     */   private String note;
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  43 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/*  47 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public Double getMoney() {
/*  51 */     return this.money;
/*     */   }
/*     */ 
/*     */   public void setMoney(Double money) {
/*  55 */     this.money = money;
/*     */   }
/*     */ 
/*     */   public Date getStartTime() {
/*  59 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime) {
/*  63 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public Date getEndTime() {
/*  67 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime) {
/*  71 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public Date getCreatedTime() {
/*  75 */     return this.createdTime;
/*     */   }
/*     */ 
/*     */   public void setCreatedTime(Date createdTime) {
/*  79 */     this.createdTime = createdTime;
/*     */   }
/*     */ 
/*     */   public Date getUpdateTime() {
/*  83 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {
/*  87 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public int getFlag() {
/*  91 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public void setFlag(int flag) {
/*  95 */     this.flag = flag;
/*     */   }
/*     */ 
/*     */   public String getNote() {
/*  99 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/* 103 */     this.note = note;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.SysActivityConfig
 * JD-Core Version:    0.6.0
 */