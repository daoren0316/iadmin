/*    */ package cc.kokoko.server.ibutler.domain.dto;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.domain.MoneyAccountMember;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MoneyAccountDTO
/*    */ {
/*    */   private String roomNo;
/*    */   private BigDecimal balance;
/*    */   private Long ownerUid;
/*    */   private Date createdTime;
/*    */   private Date updateTime;
/*    */   private List<MoneyAccountMember> members;
/*    */ 
/*    */   public String getRoomNo()
/*    */   {
/* 41 */     return this.roomNo;
/*    */   }
/*    */ 
/*    */   public void setRoomNo(String roomNo) {
/* 45 */     this.roomNo = roomNo;
/*    */   }
/*    */ 
/*    */   public BigDecimal getBalance() {
/* 49 */     return this.balance;
/*    */   }
/*    */ 
/*    */   public void setBalance(BigDecimal balance) {
/* 53 */     this.balance = balance;
/*    */   }
/*    */ 
/*    */   public Long getOwnerUid() {
/* 57 */     return this.ownerUid;
/*    */   }
/*    */ 
/*    */   public void setOwnerUid(Long ownerUid) {
/* 61 */     this.ownerUid = ownerUid;
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
/*    */   public List<MoneyAccountMember> getMembers() {
/* 81 */     return this.members;
/*    */   }
/*    */ 
/*    */   public void setMembers(List<MoneyAccountMember> members) {
/* 85 */     this.members = members;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.MoneyAccountDTO
 * JD-Core Version:    0.6.0
 */