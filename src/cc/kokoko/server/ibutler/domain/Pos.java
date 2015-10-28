/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Pos
/*    */ {
/*    */   private Long shopId;
/*    */   private String shopName;
/*    */   private String posNo;
/*    */   private String shopNo;
/*    */   private Date createTime;
/*    */   private String communityName;
/*    */ 
/*    */   public Long getShopId()
/*    */   {
/* 30 */     return this.shopId;
/*    */   }
/*    */ 
/*    */   public void setShopId(Long shopId) {
/* 34 */     this.shopId = shopId;
/*    */   }
/*    */ 
/*    */   public String getShopName() {
/* 38 */     return this.shopName;
/*    */   }
/*    */ 
/*    */   public void setShopName(String shopName) {
/* 42 */     this.shopName = shopName;
/*    */   }
/*    */ 
/*    */   public String getShopNo() {
/* 46 */     return this.shopNo;
/*    */   }
/*    */ 
/*    */   public void setShopNo(String shopNo) {
/* 50 */     this.shopNo = shopNo;
/*    */   }
/*    */ 
/*    */   public String getPosNo() {
/* 54 */     return this.posNo;
/*    */   }
/*    */ 
/*    */   public void setPosNo(String posNo) {
/* 58 */     this.posNo = posNo;
/*    */   }
/*    */ 
/*    */   public Date getCreateTime() {
/* 62 */     return this.createTime;
/*    */   }
/*    */ 
/*    */   public void setCreateTime(Date createTime) {
/* 66 */     this.createTime = createTime;
/*    */   }
/*    */ 
/*    */   public String getCommunityName() {
/* 70 */     return this.communityName;
/*    */   }
/*    */ 
/*    */   public void setCommunityName(String communityName) {
/* 74 */     this.communityName = communityName;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 79 */     return "Pos{shopId=" + this.shopId + ", shopName='" + this.shopName + '\'' + ", posNo='" + this.posNo + '\'' + ", shopNo='" + this.shopNo + '\'' + ", createTime=" + this.createTime + '}';
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Pos
 * JD-Core Version:    0.6.0
 */