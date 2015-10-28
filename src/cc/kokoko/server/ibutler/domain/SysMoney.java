/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SysMoney
/*    */ {
/*    */   private Long id;
/*    */   private Double money;
/*    */   private Date updateTime;
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 20 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 24 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Double getMoney() {
/* 28 */     return this.money;
/*    */   }
/*    */ 
/*    */   public void setMoney(Double money) {
/* 32 */     this.money = money;
/*    */   }
/*    */ 
/*    */   public Date getUpdateTime() {
/* 36 */     return this.updateTime;
/*    */   }
/*    */ 
/*    */   public void setUpdateTime(Date updateTime) {
/* 40 */     this.updateTime = updateTime;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.SysMoney
 * JD-Core Version:    0.6.0
 */