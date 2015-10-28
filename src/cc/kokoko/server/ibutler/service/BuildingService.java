/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.domain.Building;
/*    */ import cc.kokoko.server.ibutler.domain.Community;
/*    */ import cc.kokoko.server.ibutler.persistence.CommunityMapper;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("buildingService")
/*    */ public class BuildingService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CommunityMapper communityMapper;
/*    */ 
/*    */   public Map<String, Object> getBuildingRecord(Long communityId, String buildingTitle, Integer pagesize, Integer curPage)
/*    */   {
/* 34 */     Map paramMap = new HashMap();
/* 35 */     paramMap.put("communityId", communityId);
/* 36 */     paramMap.put("buildingTitle", buildingTitle);
/* 37 */     paramMap.put("pagesize", pagesize);
/* 38 */     paramMap.put("start", curPage);
/* 39 */     List list = this.communityMapper.getBuildingRecord(paramMap);
/* 40 */     Long count = this.communityMapper.getBuildingCount(paramMap);
/* 41 */     paramMap = new HashMap();
/* 42 */     paramMap.put("list", list);
/* 43 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 44 */     return paramMap;
/*    */   }
/*    */ 
/*    */   public void insert(Building building)
/*    */   {
/* 53 */     this.communityMapper.insertBuilding(building);
/*    */   }
/*    */ 
/*    */   public Boolean checkBuilding(Long communityId, String buildingTitle)
/*    */   {
/* 64 */     Boolean bool = Boolean.valueOf(false);
/* 65 */     Map paramMap = new HashMap();
/* 66 */     paramMap.put("communityId", communityId);
/* 67 */     paramMap.put("buildingTitle", buildingTitle);
/* 68 */     Building building = this.communityMapper.checkBuilding(paramMap);
/* 69 */     if (building == null)
/* 70 */       bool = Boolean.valueOf(true);
/* 71 */     return bool;
/*    */   }
/*    */ 
/*    */   public List<Community> getAllComunity()
/*    */   {
/* 80 */     return this.communityMapper.getAllCommunity();
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.BuildingService
 * JD-Core Version:    0.6.0
 */