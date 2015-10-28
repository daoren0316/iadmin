/*    */ package cc.kokoko.server.ibutler.domain.kdt.resp;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Response
/*    */ {
/*    */   public Long total_results;
/* 10 */   public List<Trade> trades = new ArrayList();
/*    */ 
/*    */   public Long getTotal_results() {
/* 13 */     return this.total_results;
/*    */   }
/*    */ 
/*    */   public void setTotal_results(Long total_results) {
/* 17 */     this.total_results = total_results;
/*    */   }
/*    */ 
/*    */   public List<Trade> getTrades() {
/* 21 */     return this.trades;
/*    */   }
/*    */ 
/*    */   public void setTrades(List<Trade> trades) {
/* 25 */     this.trades = trades;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 30 */     return "Response{total_results=" + this.total_results + ", trades=" + this.trades + '}';
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.kdt.resp.Response
 * JD-Core Version:    0.6.0
 */