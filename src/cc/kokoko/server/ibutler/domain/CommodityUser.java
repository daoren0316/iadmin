/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class CommodityUser
/*    */ {
/*    */   private Long applyId;
/*    */   private Long uid;
/*    */   private String houseTitle;
/*    */   private Date applyTime;
/*    */   private Integer amount;
/*    */   private Long commodityId;
/*    */   private String phoneNumber;
/*    */ 
/*    */   public Long getApplyId()
/*    */   {
/* 34 */     return this.applyId;
/*    */   }
/*    */   public void setApplyId(Long applyId) {
/* 37 */     this.applyId = applyId;
/*    */   }
/*    */   public Long getUid() {
/* 40 */     return this.uid;
/*    */   }
/*    */   public void setUid(Long uid) {
/* 43 */     this.uid = uid;
/*    */   }
/*    */   public String getHouseTitle() {
/* 46 */     return this.houseTitle;
/*    */   }
/*    */   public void setHouseTitle(String houseTitle) {
/* 49 */     this.houseTitle = houseTitle;
/*    */   }
/*    */   public Date getApplyTime() {
/* 52 */     return this.applyTime;
/*    */   }
/*    */   public void setApplyTime(Date applyTime) {
/* 55 */     this.applyTime = applyTime;
/*    */   }
/*    */   public Integer getAmount() {
/* 58 */     return this.amount;
/*    */   }
/*    */   public void setAmount(Integer amount) {
/* 61 */     this.amount = amount;
/*    */   }
/*    */   public Long getCommodityId() {
/* 64 */     return this.commodityId;
/*    */   }
/*    */   public void setCommodityId(Long commodityId) {
/* 67 */     this.commodityId = commodityId;
/*    */   }
/*    */   public String getPhoneNumber() {
/* 70 */     return this.phoneNumber;
/*    */   }
/*    */   public void setPhoneNumber(String phoneNumber) {
/* 73 */     this.phoneNumber = phoneNumber;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.CommodityUser
 * JD-Core Version:    0.6.0
 */