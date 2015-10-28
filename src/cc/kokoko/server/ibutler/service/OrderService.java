/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.persistence.OrderMapper;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Propagation;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("orderService")
/*    */ public class OrderService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private OrderMapper orderMapper;
/*    */ 
/*    */   public Map<String, Object> getOrderRecord(Long communityId, Long tradeStatus, Long orderId, String startTime, String endTime, Integer pagesize, Integer curPage)
/*    */   {
/* 35 */     Map paramMap = new HashMap();
/* 36 */     paramMap.put("communityId", communityId);
/* 37 */     paramMap.put("tradeStatus", tradeStatus);
/* 38 */     paramMap.put("orderId", orderId);
/* 39 */     paramMap.put("starttime", startTime);
/* 40 */     paramMap.put("endtime", endTime);
/* 41 */     paramMap.put("pagesize", pagesize);
/* 42 */     paramMap.put("start", curPage);
/*    */ 
/* 44 */     List list = this.orderMapper.getOrderRecord(paramMap);
/* 45 */     Long count = this.orderMapper.getOrderCount(paramMap);
/* 46 */     paramMap = new HashMap();
/* 47 */     paramMap.put("list", list);
/* 48 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 49 */     return paramMap;
/*    */   }
/*    */ 
/*    */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*    */   public void changeOrderStatus(String[] ids)
/*    */   {
/* 59 */     for (String str : ids) {
/* 60 */       String orderId = str;
/* 61 */       this.orderMapper.changeOrderStatus(orderId);
/*    */     }
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.OrderService
 * JD-Core Version:    0.6.0
 */