/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ public class MoneyAccountMember
/*    */ {
/*    */   private String roomNo;
/*    */   private Long uid;
/*    */   private Byte permissions;
/*    */   private String nickname;
/*    */   private String profileImage;
/*    */ 
/*    */   public String getRoomNo()
/*    */   {
/* 41 */     return this.roomNo;
/*    */   }
/*    */ 
/*    */   public void setRoomNo(String roomNo)
/*    */   {
/* 53 */     this.roomNo = roomNo;
/*    */   }
/*    */ 
/*    */   public Long getUid()
/*    */   {
/* 65 */     return this.uid;
/*    */   }
/*    */ 
/*    */   public void setUid(Long uid)
/*    */   {
/* 77 */     this.uid = uid;
/*    */   }
/*    */ 
/*    */   public Byte getPermissions() {
/* 81 */     return this.permissions;
/*    */   }
/*    */ 
/*    */   public void setPermissions(Byte permissions) {
/* 85 */     this.permissions = permissions;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.MoneyAccountMember
 * JD-Core Version:    0.6.0
 */