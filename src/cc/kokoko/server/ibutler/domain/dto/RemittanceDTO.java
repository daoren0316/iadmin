/*    */ package cc.kokoko.server.ibutler.domain.dto;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class RemittanceDTO
/*    */ {
/*    */   private Long id;
/*    */   private Double money;
/*    */   private Date time;
/*    */   private Date startTime;
/*    */   private Date endTime;
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 31 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 35 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Double getMoney() {
/* 39 */     return this.money;
/*    */   }
/*    */ 
/*    */   public void setMoney(Double money) {
/* 43 */     this.money = money;
/*    */   }
/*    */ 
/*    */   public Date getTime() {
/* 47 */     return this.time;
/*    */   }
/*    */ 
/*    */   public void setTime(Date time) {
/* 51 */     this.time = time;
/*    */   }
/*    */ 
/*    */   public Date getStartTime() {
/* 55 */     return this.startTime;
/*    */   }
/*    */ 
/*    */   public void setStartTime(Date startTime) {
/* 59 */     this.startTime = startTime;
/*    */   }
/*    */ 
/*    */   public Date getEndTime() {
/* 63 */     return this.endTime;
/*    */   }
/*    */ 
/*    */   public void setEndTime(Date endTime) {
/* 67 */     this.endTime = endTime;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.RemittanceDTO
 * JD-Core Version:    0.6.0
 */