/*    */ package cc.kokoko.server.ibutler.domain.dto;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class ReceiptAccountDTO
/*    */ {
/*    */   private Long id;
/*    */   private Date time;
/*    */   private Double money;
/*    */   private String publicAddress;
/*    */   private String note;
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 29 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 33 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNote() {
/* 37 */     return this.note;
/*    */   }
/*    */ 
/*    */   public void setNote(String note) {
/* 41 */     this.note = note;
/*    */   }
/*    */ 
/*    */   public Date getTime() {
/* 45 */     return this.time;
/*    */   }
/*    */ 
/*    */   public void setTime(Date time) {
/* 49 */     this.time = time;
/*    */   }
/*    */ 
/*    */   public Double getMoney() {
/* 53 */     return this.money;
/*    */   }
/*    */ 
/*    */   public void setMoney(Double money) {
/* 57 */     this.money = money;
/*    */   }
/*    */ 
/*    */   public String getPublicAddress() {
/* 61 */     return this.publicAddress;
/*    */   }
/*    */ 
/*    */   public void setPublicAddress(String publicAddress) {
/* 65 */     this.publicAddress = publicAddress;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.ReceiptAccountDTO
 * JD-Core Version:    0.6.0
 */