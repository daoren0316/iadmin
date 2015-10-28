/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Car
/*    */ {
/*    */   private Long carId;
/*    */   private Long uid;
/*    */   private String carNumber;
/*    */   private Date createTime;
/*    */ 
/*    */   public Long getCarId()
/*    */   {
/* 17 */     return this.carId;
/*    */   }
/*    */   public void setCarId(Long carId) {
/* 20 */     this.carId = carId;
/*    */   }
/*    */   public Long getUid() {
/* 23 */     return this.uid;
/*    */   }
/*    */   public void setUid(Long uid) {
/* 26 */     this.uid = uid;
/*    */   }
/*    */   public String getCarNumber() {
/* 29 */     return this.carNumber;
/*    */   }
/*    */   public void setCarNumber(String carNumber) {
/* 32 */     this.carNumber = carNumber;
/*    */   }
/*    */   public Date getCreateTime() {
/* 35 */     return this.createTime;
/*    */   }
/*    */   public void setCreateTime(Date createTime) {
/* 38 */     this.createTime = createTime;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Car
 * JD-Core Version:    0.6.0
 */