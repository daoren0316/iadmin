/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SysActivityLog
/*    */ {
/*    */   private Long id;
/*    */   private Long uid;
/*    */   private Long houseId;
/*    */   private Double amount;
/*    */   private Date createdTime;
/*    */   private boolean isBack;
/*    */   private String publicAddress;
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 40 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 44 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Long getUid() {
/* 48 */     return this.uid;
/*    */   }
/*    */ 
/*    */   public void setUid(Long uid) {
/* 52 */     this.uid = uid;
/*    */   }
/*    */ 
/*    */   public Long getHouseId() {
/* 56 */     return this.houseId;
/*    */   }
/*    */ 
/*    */   public void setHouseId(Long houseId) {
/* 60 */     this.houseId = houseId;
/*    */   }
/*    */ 
/*    */   public Double getAmount() {
/* 64 */     return this.amount;
/*    */   }
/*    */ 
/*    */   public void setAmount(Double amount) {
/* 68 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */   public Date getCreatedTime() {
/* 72 */     return this.createdTime;
/*    */   }
/*    */ 
/*    */   public void setCreatedTime(Date createdTime) {
/* 76 */     this.createdTime = createdTime;
/*    */   }
/*    */ 
/*    */   public boolean isBack() {
/* 80 */     return this.isBack;
/*    */   }
/*    */ 
/*    */   public void setBack(boolean back) {
/* 84 */     this.isBack = back;
/*    */   }
/*    */ 
/*    */   public String getPublicAddress() {
/* 88 */     return this.publicAddress;
/*    */   }
/*    */ 
/*    */   public void setPublicAddress(String publicAddress) {
/* 92 */     this.publicAddress = publicAddress;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.SysActivityLog
 * JD-Core Version:    0.6.0
 */