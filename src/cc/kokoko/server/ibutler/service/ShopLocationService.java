/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.domain.ShopLocation;
/*    */ import cc.kokoko.server.ibutler.persistence.ShopLocationMapper;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("shopLocationService")
/*    */ public class ShopLocationService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private ShopLocationMapper shopLocationMapper;
/*    */ 
/*    */   public Map<String, Object> getAllShopLocationByParam(Long communityId, String startTime, String endTime, Integer pagesize, Integer curPage)
/*    */   {
/* 28 */     Map paramMap = new HashMap();
/* 29 */     paramMap.put("communityId", communityId);
/* 30 */     paramMap.put("starttime", startTime);
/* 31 */     paramMap.put("endtime", endTime);
/* 32 */     paramMap.put("pagesize", pagesize);
/* 33 */     paramMap.put("start", curPage);
/* 34 */     List list = this.shopLocationMapper.getShopLocationList(paramMap);
/* 35 */     Long count = this.shopLocationMapper.getShopLocationCount(paramMap);
/* 36 */     paramMap = new HashMap();
/* 37 */     paramMap.put("list", list);
/* 38 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 39 */     return paramMap;
/*    */   }
/*    */ 
/*    */   public ShopLocation getShopLocationByParam(Long shopId, Long communityId)
/*    */   {
/* 50 */     Map paramMap = new HashMap();
/* 51 */     paramMap.put("shopId", shopId);
/* 52 */     paramMap.put("communityId", communityId);
/* 53 */     return this.shopLocationMapper.getShopLocationByParam(paramMap);
/*    */   }
/*    */ 
/*    */   public void insert(Long commmunityId, ShopLocation shopLocation)
/*    */   {
/* 62 */     shopLocation.setCommunityId(commmunityId);
/* 63 */     this.shopLocationMapper.insert(shopLocation);
/*    */   }
/*    */ 
/*    */   public void delete(Long id)
/*    */   {
/* 72 */     this.shopLocationMapper.delete(id);
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.ShopLocationService
 * JD-Core Version:    0.6.0
 */