/*     */ package cc.kokoko.server.ibutler.domain.dto;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ConsumeLogDTO
/*     */ {
/*     */   private String orderId;
/*     */   private Double amount;
/*     */   private Long tradeType;
/*     */   private Date tradeTime;
/*     */   private String nickname;
/*     */   private String publicAddress;
/*     */   private String phoneNumber;
/*     */   private String note;
/*     */ 
/*     */   public String getOrderId()
/*     */   {
/*  41 */     return this.orderId;
/*     */   }
/*     */ 
/*     */   public void setOrderId(String orderId) {
/*  45 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   public Double getAmount() {
/*  49 */     return this.amount;
/*     */   }
/*     */ 
/*     */   public void setAmount(Double amount) {
/*  53 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   public Long getTradeType() {
/*  57 */     return this.tradeType;
/*     */   }
/*     */ 
/*     */   public void setTradeType(Long tradeType) {
/*  61 */     this.tradeType = tradeType;
/*     */   }
/*     */ 
/*     */   public Date getTradeTime() {
/*  65 */     return this.tradeTime;
/*     */   }
/*     */ 
/*     */   public void setTradeTime(Date tradeTime) {
/*  69 */     this.tradeTime = tradeTime;
/*     */   }
/*     */ 
/*     */   public String getNickname() {
/*  73 */     return this.nickname;
/*     */   }
/*     */ 
/*     */   public void setNickname(String nickname) {
/*  77 */     this.nickname = nickname;
/*     */   }
/*     */ 
/*     */   public String getPublicAddress() {
/*  81 */     return this.publicAddress;
/*     */   }
/*     */ 
/*     */   public void setPublicAddress(String publicAddress) {
/*  85 */     this.publicAddress = publicAddress;
/*     */   }
/*     */ 
/*     */   public String getPhoneNumber() {
/*  89 */     return this.phoneNumber;
/*     */   }
/*     */ 
/*     */   public void setPhoneNumber(String phoneNumber) {
/*  93 */     this.phoneNumber = phoneNumber;
/*     */   }
/*     */ 
/*     */   public String getNote() {
/*  97 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/* 101 */     this.note = note;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.ConsumeLogDTO
 * JD-Core Version:    0.6.0
 */