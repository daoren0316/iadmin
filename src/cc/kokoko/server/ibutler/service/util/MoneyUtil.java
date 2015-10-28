/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.domain.Charge;
/*    */ import cc.kokoko.server.ibutler.domain.MoneyLog;
/*    */ import cc.kokoko.server.ibutler.domain.Trade;
/*    */ 
/*    */ public class MoneyUtil
/*    */ {
/*    */   public static Trade MoneyLog2Trade(MoneyLog moneyLog)
/*    */   {
/*  9 */     Trade trade = null;
/*    */ 
/* 11 */     if (moneyLog != null) {
/* 12 */       trade = new Trade();
/* 13 */       trade.setTradeId(moneyLog.getId());
/* 14 */       trade.setAmount(moneyLog.getAmount());
/* 15 */       trade.setTradeTime(moneyLog.getTradeTime());
/* 16 */       trade.setNote(moneyLog.getNote());
/*    */     }
/* 18 */     return trade;
/*    */   }
/*    */ 
/*    */   public static Charge MoneyLog2Charge(MoneyLog moneyLog) {
/* 22 */     Charge charge = null;
/*    */ 
/* 24 */     if (moneyLog != null) {
/* 25 */       charge = new Charge();
/* 26 */       charge.setChargeId(moneyLog.getId());
/* 27 */       charge.setAmount(moneyLog.getAmount());
/* 28 */       charge.setChargeTime(moneyLog.getTradeTime());
/* 29 */       charge.setNote(moneyLog.getNote());
/*    */     }
/* 31 */     return charge;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.MoneyUtil
 * JD-Core Version:    0.6.0
 */