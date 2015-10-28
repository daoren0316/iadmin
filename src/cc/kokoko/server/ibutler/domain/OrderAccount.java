/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class OrderAccount
/*     */ {
/*     */   private Long id;
/*     */   private String orderId;
/*     */   private String tn;
/*     */   private Double amount;
/*     */   private Byte orderType;
/*     */   private Byte orderStatus;
/*     */   private Long uid;
/*     */   private Long houseId;
/*     */   private Date createdTime;
/*     */   private Date updateTime;
/*     */   private String note;
/*     */   private Long operatorId;
/*     */   private String operatorName;
/*     */   private String publicAddress;
/*     */   private String phoneNumber;
/*     */   private String communityName;
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  67 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/*  71 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getOrderId() {
/*  75 */     return this.orderId;
/*     */   }
/*     */ 
/*     */   public void setOrderId(String orderId) {
/*  79 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   public String getTn() {
/*  83 */     return this.tn;
/*     */   }
/*     */ 
/*     */   public void setTn(String tn) {
/*  87 */     this.tn = tn;
/*     */   }
/*     */ 
/*     */   public Double getAmount() {
/*  91 */     return this.amount;
/*     */   }
/*     */ 
/*     */   public void setAmount(Double amount) {
/*  95 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   public Byte getOrderType() {
/*  99 */     return this.orderType;
/*     */   }
/*     */ 
/*     */   public void setOrderType(Byte orderType) {
/* 103 */     this.orderType = orderType;
/*     */   }
/*     */ 
/*     */   public Byte getOrderStatus() {
/* 107 */     return this.orderStatus;
/*     */   }
/*     */ 
/*     */   public void setOrderStatus(Byte orderStatus) {
/* 111 */     this.orderStatus = orderStatus;
/*     */   }
/*     */ 
/*     */   public Date getCreatedTime() {
/* 115 */     return this.createdTime;
/*     */   }
/*     */ 
/*     */   public void setCreatedTime(Date createdTime) {
/* 119 */     this.createdTime = createdTime;
/*     */   }
/*     */ 
/*     */   public String getNote() {
/* 123 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/* 127 */     this.note = note;
/*     */   }
/*     */ 
/*     */   public Date getUpdateTime() {
/* 131 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {
/* 135 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public Long getHouseId() {
/* 139 */     return this.houseId;
/*     */   }
/*     */ 
/*     */   public void setHouseId(Long houseId) {
/* 143 */     this.houseId = houseId;
/*     */   }
/*     */ 
/*     */   public Long getUid() {
/* 147 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/* 151 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Long getOperatorId() {
/* 155 */     return this.operatorId;
/*     */   }
/*     */ 
/*     */   public void setOperatorId(Long operatorId) {
/* 159 */     this.operatorId = operatorId;
/*     */   }
/*     */ 
/*     */   public String getOperatorName() {
/* 163 */     return this.operatorName;
/*     */   }
/*     */ 
/*     */   public void setOperatorName(String operatorName) {
/* 167 */     this.operatorName = operatorName;
/*     */   }
/*     */ 
/*     */   public String getPublicAddress() {
/* 171 */     return this.publicAddress;
/*     */   }
/*     */ 
/*     */   public void setPublicAddress(String publicAddress) {
/* 175 */     this.publicAddress = publicAddress;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/* 179 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/* 183 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public String getCommunityName() {
/* 187 */     return this.communityName;
/*     */   }
/*     */ 
/*     */   public void setCommunityName(String communityName) {
/* 191 */     this.communityName = communityName;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.OrderAccount
 * JD-Core Version:    0.6.0
 */