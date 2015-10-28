/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class MoneyAccount
/*    */ {
/*    */   private Long houseId;
/*    */   private String roomNo;
/*    */   private BigDecimal balance;
/*    */   private Long ownerUid;
/*    */   private Date createdTime;
/*    */   private Date updateTime;
/*    */   private String password;
/*    */   private String ciphertext;
/*    */ 
/*    */   public String getRoomNo()
/*    */   {
/* 17 */     return this.roomNo;
/*    */   }
/*    */ 
/*    */   public void setRoomNo(String roomNo) {
/* 21 */     this.roomNo = roomNo;
/*    */   }
/*    */ 
/*    */   public BigDecimal getBalance() {
/* 25 */     return this.balance;
/*    */   }
/*    */ 
/*    */   public void setBalance(BigDecimal balance) {
/* 29 */     this.balance = balance;
/*    */   }
/*    */ 
/*    */   public Long getOwnerUid() {
/* 33 */     return this.ownerUid;
/*    */   }
/*    */ 
/*    */   public void setOwnerUid(Long ownerUid) {
/* 37 */     this.ownerUid = ownerUid;
/*    */   }
/*    */ 
/*    */   public Date getCreatedTime() {
/* 41 */     return this.createdTime;
/*    */   }
/*    */ 
/*    */   public void setCreatedTime(Date createdTime) {
/* 45 */     this.createdTime = createdTime;
/*    */   }
/*    */ 
/*    */   public Date getUpdateTime() {
/* 49 */     return this.updateTime;
/*    */   }
/*    */ 
/*    */   public void setUpdateTime(Date updateTime) {
/* 53 */     this.updateTime = updateTime;
/*    */   }
/*    */ 
/*    */   public Long getHouseId() {
/* 57 */     return this.houseId;
/*    */   }
/*    */ 
/*    */   public void setHouseId(Long houseId) {
/* 61 */     this.houseId = houseId;
/*    */   }
/*    */ 
/*    */   public String getPassword() {
/* 65 */     return this.password;
/*    */   }
/*    */ 
/*    */   public void setPassword(String password) {
/* 69 */     this.password = password;
/*    */   }
/*    */ 
/*    */   public String getCiphertext() {
/* 73 */     return this.ciphertext;
/*    */   }
/*    */ 
/*    */   public void setCiphertext(String ciphertext) {
/* 77 */     this.ciphertext = ciphertext;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.MoneyAccount
 * JD-Core Version:    0.6.0
 */