/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Role
/*    */ {
/*    */   private Long roleId;
/*    */   private String roleName;
/*    */   private Date createTime;
/*    */   private Long flag;
/*    */ 
/*    */   public Long getRoleId()
/*    */   {
/* 25 */     return this.roleId;
/*    */   }
/*    */ 
/*    */   public void setRoleId(Long roleId) {
/* 29 */     this.roleId = roleId;
/*    */   }
/*    */ 
/*    */   public Date getCreateTime() {
/* 33 */     return this.createTime;
/*    */   }
/*    */ 
/*    */   public void setCreateTime(Date createTime) {
/* 37 */     this.createTime = createTime;
/*    */   }
/*    */ 
/*    */   public String getRoleName() {
/* 41 */     return this.roleName;
/*    */   }
/*    */ 
/*    */   public void setRoleName(String roleName) {
/* 45 */     this.roleName = roleName;
/*    */   }
/*    */ 
/*    */   public Long getFlag() {
/* 49 */     return this.flag;
/*    */   }
/*    */ 
/*    */   public void setFlag(Long flag) {
/* 53 */     this.flag = flag;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 58 */     return "Role{roleId=" + this.roleId + ", roleName='" + this.roleName + '\'' + ", createTime=" + this.createTime + ", flag=" + this.flag + '}';
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Role
 * JD-Core Version:    0.6.0
 */