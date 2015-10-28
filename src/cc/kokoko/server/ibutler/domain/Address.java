/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Address
/*    */ {
/*    */   private Long addressId;
/*    */   private Long uid;
/*    */   private String addressDetail;
/*    */   private Date createTime;
/*    */ 
/*    */   public Long getAddressId()
/*    */   {
/* 17 */     return this.addressId;
/*    */   }
/*    */   public void setAddressId(Long addressId) {
/* 20 */     this.addressId = addressId;
/*    */   }
/*    */   public Long getUid() {
/* 23 */     return this.uid;
/*    */   }
/*    */   public void setUid(Long uid) {
/* 26 */     this.uid = uid;
/*    */   }
/*    */   public String getAddressDetail() {
/* 29 */     return this.addressDetail;
/*    */   }
/*    */   public void setAddressDetail(String addressDetail) {
/* 32 */     this.addressDetail = addressDetail;
/*    */   }
/*    */   public Date getCreateTime() {
/* 35 */     return this.createTime;
/*    */   }
/*    */   public void setCreateTime(Date createTime) {
/* 38 */     this.createTime = createTime;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Address
 * JD-Core Version:    0.6.0
 */