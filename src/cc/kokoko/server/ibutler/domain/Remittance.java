/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Remittance
/*     */ {
/*     */   private Long id;
/*     */   private Double money;
/*     */   private Date initTime;
/*     */   private Long shopId;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private String note;
/*     */   private String shopName;
/*     */   private String phoneNumber;
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  45 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/*  49 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public Double getMoney() {
/*  53 */     return this.money;
/*     */   }
/*     */ 
/*     */   public void setMoney(Double money) {
/*  57 */     this.money = money;
/*     */   }
/*     */ 
/*     */   public Date getInitTime() {
/*  61 */     return this.initTime;
/*     */   }
/*     */ 
/*     */   public void setInitTime(Date initTime) {
/*  65 */     this.initTime = initTime;
/*     */   }
/*     */ 
/*     */   public Long getShopId() {
/*  69 */     return this.shopId;
/*     */   }
/*     */ 
/*     */   public void setShopId(Long shopId) {
/*  73 */     this.shopId = shopId;
/*     */   }
/*     */ 
/*     */   public Date getStartTime() {
/*  77 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime) {
/*  81 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public Date getEndTime() {
/*  85 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime) {
/*  89 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public String getNote() {
/*  93 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/*  97 */     this.note = note;
/*     */   }
/*     */ 
/*     */   public String getShopName() {
/* 101 */     return this.shopName;
/*     */   }
/*     */ 
/*     */   public void setShopName(String shopName) {
/* 105 */     this.shopName = shopName;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/* 109 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/* 113 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 118 */     return "Remittance{id=" + this.id + ", money=" + this.money + ", initTime=" + this.initTime + ", shopId=" + this.shopId + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", note='" + this.note + '\'' + ", shopName='" + this.shopName + '\'' + ", phoneNumber='" + this.phoneNumber + '\'' + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Remittance
 * JD-Core Version:    0.6.0
 */