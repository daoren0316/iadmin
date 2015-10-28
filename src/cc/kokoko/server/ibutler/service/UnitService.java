/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import cc.kokoko.server.ibutler.domain.Building;
/*     */ import cc.kokoko.server.ibutler.domain.Community;
/*     */ import cc.kokoko.server.ibutler.domain.House;
/*     */ import cc.kokoko.server.ibutler.domain.Unit;
/*     */ import cc.kokoko.server.ibutler.persistence.CommunityMapper;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("unitService")
/*     */ public class UnitService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private CommunityMapper communityMapper;
/*     */ 
/*     */   public Map<String, Object> getUnitReocrd(Long communityId, Long buildingId, String unitName, Integer pagesize, Integer curPage)
/*     */   {
/*  37 */     Map paramMap = new HashMap();
/*  38 */     paramMap.put("communityId", communityId);
/*  39 */     paramMap.put("buildingId", buildingId);
/*  40 */     paramMap.put("unitName", unitName);
/*  41 */     paramMap.put("pagesize", pagesize);
/*  42 */     paramMap.put("start", curPage);
/*  43 */     List list = this.communityMapper.getUnitReocrd(paramMap);
/*  44 */     Long count = this.communityMapper.getUnitCount(paramMap);
/*  45 */     paramMap = new HashMap();
/*  46 */     paramMap.put("list", list);
/*  47 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/*  48 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public void insert(Unit unit)
/*     */   {
/*  57 */     this.communityMapper.insertUnit(unit);
/*     */   }
/*     */ 
/*     */   public boolean checkUnit(Long buildingId, String unitName)
/*     */   {
/*  68 */     Boolean bool = Boolean.valueOf(false);
/*  69 */     Map paramMap = new HashMap();
/*  70 */     paramMap.put("buildingId", buildingId);
/*  71 */     paramMap.put("unitName", unitName);
/*  72 */     Unit unit = this.communityMapper.checkUnit(paramMap);
/*  73 */     if (unit == null)
/*  74 */       bool = Boolean.valueOf(true);
/*  75 */     return bool.booleanValue();
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getHouseReocrd(Long communityId, Long buildingId, Long unitId, String houseName, Integer pagesize, Integer curPage)
/*     */   {
/*  90 */     Map paramMap = new HashMap();
/*  91 */     paramMap.put("communityId", communityId);
/*  92 */     paramMap.put("buildingId", buildingId);
/*  93 */     paramMap.put("unitId", unitId);
/*  94 */     paramMap.put("houseName", houseName);
/*  95 */     paramMap.put("pagesize", pagesize);
/*  96 */     paramMap.put("start", curPage);
/*  97 */     List list = this.communityMapper.getHouseReocrd(paramMap);
/*  98 */     Long count = this.communityMapper.getHouseCount(paramMap);
/*  99 */     paramMap = new HashMap();
/* 100 */     paramMap.put("list", list);
/* 101 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 102 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public void insertHouse(House house)
/*     */   {
/* 112 */     Unit unit = this.communityMapper.getUnitById(house.getUnitId());
/* 113 */     house.setHouseTitle(unit.getBuildingTitle() + unit.getUnitName() + house.getHouseName());
/* 114 */     house.setCommunityId(unit.getCommunityId());
/* 115 */     this.communityMapper.insertHouse(house);
/*     */   }
/*     */ 
/*     */   public Boolean checkHouse(Long unitId, String houseName)
/*     */   {
/* 126 */     Boolean bool = Boolean.valueOf(false);
/* 127 */     Map paramMap = new HashMap();
/* 128 */     paramMap.put("unitId", unitId);
/* 129 */     paramMap.put("houseName", houseName);
/* 130 */     House house = this.communityMapper.checkHouse(paramMap);
/* 131 */     if (house == null)
/* 132 */       bool = Boolean.valueOf(true);
/* 133 */     return bool;
/*     */   }
/*     */ 
/*     */   public List<Community> getAllComunity()
/*     */   {
/* 142 */     return this.communityMapper.getAllCommunity();
/*     */   }
/*     */ 
/*     */   public List<Building> getBuildingById(Long communityId)
/*     */   {
/* 152 */     return this.communityMapper.getAllBuildingById(communityId);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.UnitService
 * JD-Core Version:    0.6.0
 */