/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Fee
/*     */ {
/*     */   private Long feeId;
/*     */   private Long uid;
/*     */   private Double amount;
/*     */   private Date beginDate;
/*     */   private Date endDate;
/*     */   private Byte status;
/*     */   private String note;
/*     */   private Long feeType;
/*     */   private Long houseId;
/*     */   private Long communityId;
/*     */ 
/*     */   public Long getUid()
/*     */   {
/*  50 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/*  54 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Double getAmount() {
/*  58 */     return this.amount;
/*     */   }
/*     */ 
/*     */   public void setAmount(Double amount) {
/*  62 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   public Date getBeginDate() {
/*  66 */     return this.beginDate;
/*     */   }
/*     */ 
/*     */   public void setBeginDate(Date beginDate) {
/*  70 */     this.beginDate = beginDate;
/*     */   }
/*     */ 
/*     */   public Date getEndDate() {
/*  74 */     return this.endDate;
/*     */   }
/*     */ 
/*     */   public void setEndDate(Date endDate) {
/*  78 */     this.endDate = endDate;
/*     */   }
/*     */ 
/*     */   public String getNote() {
/*  82 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/*  86 */     this.note = note;
/*     */   }
/*     */ 
/*     */   public Byte getStatus() {
/*  90 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status) {
/*  94 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Long getFeeId() {
/*  98 */     return this.feeId;
/*     */   }
/*     */ 
/*     */   public void setFeeId(Long feeId) {
/* 102 */     this.feeId = feeId;
/*     */   }
/*     */ 
/*     */   public Long getHouseId() {
/* 106 */     return this.houseId;
/*     */   }
/*     */ 
/*     */   public void setHouseId(Long houseId) {
/* 110 */     this.houseId = houseId;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 114 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 118 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public Long getFeeType() {
/* 122 */     return this.feeType;
/*     */   }
/*     */ 
/*     */   public void setFeeType(Long feeType) {
/* 126 */     this.feeType = feeType;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 131 */     return "Fee{feeId=" + this.feeId + ", uid=" + this.uid + ", amount=" + this.amount + ", beginDate=" + this.beginDate + ", endDate=" + this.endDate + ", status=" + this.status + ", note='" + this.note + '\'' + ", feeType=" + this.feeType + ", houseId=" + this.houseId + ", communityId=" + this.communityId + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Fee
 * JD-Core Version:    0.6.0
 */