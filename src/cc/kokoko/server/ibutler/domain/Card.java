/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Card
/*     */ {
/*     */   private Long id;
/*     */   private Long uid;
/*     */   private Long houseId;
/*     */   private String cardNo;
/*     */   private Byte status;
/*     */   private Date createdTime;
/*     */   private Date updateTime;
/*     */   private Long operatorId;
/*     */   private String operatorName;
/*     */   public String publicAddress;
/*     */   public String phoneNumber;
/*     */ 
/*     */   public Long getUid()
/*     */   {
/*  48 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/*  52 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public String getCardNo() {
/*  56 */     return this.cardNo;
/*     */   }
/*     */ 
/*     */   public void setCardNo(String cardNo) {
/*  60 */     this.cardNo = cardNo;
/*     */   }
/*     */ 
/*     */   public Date getCreatedTime() {
/*  64 */     return this.createdTime;
/*     */   }
/*     */ 
/*     */   public void setCreatedTime(Date createdTime) {
/*  68 */     this.createdTime = createdTime;
/*     */   }
/*     */ 
/*     */   public Long getId() {
/*  72 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/*  76 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public Long getHouseId() {
/*  80 */     return this.houseId;
/*     */   }
/*     */ 
/*     */   public void setHouseId(Long houseId) {
/*  84 */     this.houseId = houseId;
/*     */   }
/*     */ 
/*     */   public Byte getStatus() {
/*  88 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status) {
/*  92 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Date getUpdateTime() {
/*  96 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {
/* 100 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public String getPublicAddress() {
/* 104 */     return this.publicAddress;
/*     */   }
/*     */ 
/*     */   public void setPublicAddress(String publicAddress) {
/* 108 */     this.publicAddress = publicAddress;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/* 112 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/* 116 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public Long getOperatorId() {
/* 120 */     return this.operatorId;
/*     */   }
/*     */ 
/*     */   public void setOperatorId(Long operatorId) {
/* 124 */     this.operatorId = operatorId;
/*     */   }
/*     */ 
/*     */   public String getOperatorName() {
/* 128 */     return this.operatorName;
/*     */   }
/*     */ 
/*     */   public void setOperatorName(String operatorName) {
/* 132 */     this.operatorName = operatorName;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Card
 * JD-Core Version:    0.6.0
 */