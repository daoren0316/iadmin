/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Favorite
/*    */ {
/*    */   private Long favoriteId;
/*    */   private Byte favType;
/*    */   private String favTitle;
/*    */   private String favPicUrl;
/*    */   private Long referId;
/*    */   private Date favTime;
/*    */   private Long uid;
/*    */ 
/*    */   public Long getFavoriteId()
/*    */   {
/* 39 */     return this.favoriteId;
/*    */   }
/*    */   public void setFavoriteId(Long favoriteId) {
/* 42 */     this.favoriteId = favoriteId;
/*    */   }
/*    */   public Byte getFavType() {
/* 45 */     return this.favType;
/*    */   }
/*    */   public void setFavType(Byte favType) {
/* 48 */     this.favType = favType;
/*    */   }
/*    */   public String getFavTitle() {
/* 51 */     return this.favTitle;
/*    */   }
/*    */   public void setFavTitle(String favTitle) {
/* 54 */     this.favTitle = favTitle;
/*    */   }
/*    */   public String getFavPicUrl() {
/* 57 */     return this.favPicUrl;
/*    */   }
/*    */   public void setFavPicUrl(String favPicUrl) {
/* 60 */     this.favPicUrl = favPicUrl;
/*    */   }
/*    */   public Long getReferId() {
/* 63 */     return this.referId;
/*    */   }
/*    */   public void setReferId(Long referId) {
/* 66 */     this.referId = referId;
/*    */   }
/*    */   public Date getFavTime() {
/* 69 */     return this.favTime;
/*    */   }
/*    */   public void setFavTime(Date favTime) {
/* 72 */     this.favTime = favTime;
/*    */   }
/*    */   public Long getUid() {
/* 75 */     return this.uid;
/*    */   }
/*    */   public void setUid(Long uid) {
/* 78 */     this.uid = uid;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Favorite
 * JD-Core Version:    0.6.0
 */