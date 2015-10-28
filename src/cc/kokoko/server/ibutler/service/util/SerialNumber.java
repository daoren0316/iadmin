/*    */ package cc.kokoko.server.ibutler.service.util;
/*    */ 
/*    */ abstract class SerialNumber
/*    */ {
/*    */   public synchronized String getSerialNumber()
/*    */   {
/* 34 */     return process();
/*    */   }
/*    */ 
/*    */   protected abstract String process();
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.util.SerialNumber
 * JD-Core Version:    0.6.0
 */