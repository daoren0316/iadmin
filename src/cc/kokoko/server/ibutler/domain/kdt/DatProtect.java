/*    */ package cc.kokoko.server.ibutler.domain.kdt;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DatProtect
/*    */ {
/*    */   private Long id;
/*    */   private String telephone;
/*    */   private String protectName;
/*    */   private String protectOrder;
/*    */   private String time;
/*    */   private String note;
/*    */   private Date createdTime;
/*    */   private Date updateTime;
/*    */   private Boolean flag;
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 17 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 21 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getTelephone() {
/* 25 */     return this.telephone;
/*    */   }
/*    */ 
/*    */   public void setTelephone(String telephone) {
/* 29 */     this.telephone = telephone;
/*    */   }
/*    */ 
/*    */   public String getProtectName() {
/* 33 */     return this.protectName;
/*    */   }
/*    */ 
/*    */   public void setProtectName(String protectName) {
/* 37 */     this.protectName = protectName;
/*    */   }
/*    */ 
/*    */   public String getProtectOrder() {
/* 41 */     return this.protectOrder;
/*    */   }
/*    */ 
/*    */   public void setProtectOrder(String protectOrder) {
/* 45 */     this.protectOrder = protectOrder;
/*    */   }
/*    */ 
/*    */   public String getTime() {
/* 49 */     return this.time;
/*    */   }
/*    */ 
/*    */   public void setTime(String time) {
/* 53 */     this.time = time;
/*    */   }
/*    */ 
/*    */   public String getNote() {
/* 57 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note) {
/* 61 */     this.note = note;
/*    */   }
/*    */ 
/*    */   public Date getCreatedTime() {
/* 65 */     return this.createdTime;
/*    */   }
/*    */ 
/*    */   public void setCreatedTime(Date createdTime) {
/* 69 */     this.createdTime = createdTime;
/*    */   }
/*    */ 
/*    */   public Date getUpdateTime() {
/* 73 */     return this.updateTime;
/*    */   }
/*    */ 
/*    */   public void setUpdateTime(Date updateTime) {
/* 77 */     this.updateTime = updateTime;
/*    */   }
/*    */ 
/*    */   public Boolean getFlag() {
/* 81 */     return this.flag;
/*    */   }
/*    */ 
/*    */   public void setFlag(Boolean flag) {
/* 85 */     this.flag = flag;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 90 */     return "DatProtect{id=" + this.id + ", telephone='" + this.telephone + '\'' + ", protectName='" + this.protectName + '\'' + ", protectOrder='" + this.protectOrder + '\'' + ", time='" + this.time + '\'' + ", note='" + this.note + '\'' + ", createdTime=" + this.createdTime + ", updateTime=" + this.updateTime + '}';
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.kdt.DatProtect
 * JD-Core Version:    0.6.0
 */