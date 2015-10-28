/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import cc.kokoko.server.commons.util.MD5Util;
/*     */ import cc.kokoko.server.commons.util.StringUtil;
/*     */ import cc.kokoko.server.ibutler.domain.Building;
/*     */ import cc.kokoko.server.ibutler.domain.City;
/*     */ import cc.kokoko.server.ibutler.domain.Community;
/*     */ import cc.kokoko.server.ibutler.domain.House;
/*     */ import cc.kokoko.server.ibutler.domain.Unit;
/*     */ import cc.kokoko.server.ibutler.domain.dto.HouseDTO;
/*     */ import cc.kokoko.server.ibutler.persistence.HouseMapper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("houseService")
/*     */ public class HouseService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private HouseMapper houseMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*  31 */   private static Logger log = LoggerFactory.getLogger(HouseService.class);
/*     */ 
/*     */   public List<City> getCityList(String provinceAbbre)
/*     */   {
/*  35 */     return this.houseMapper.getCityList(provinceAbbre);
/*     */   }
/*     */ 
/*     */   public List<Community> getCommunityList(Integer cityId) {
/*  39 */     return this.houseMapper.getCommunityList(cityId);
/*     */   }
/*     */ 
/*     */   public List<Building> getBuildingList(Long communityId) {
/*  43 */     return this.houseMapper.getBuildingList(communityId);
/*     */   }
/*     */ 
/*     */   public List<Unit> getUnitList(Long buildingId) {
/*  47 */     return this.houseMapper.getUnitList(buildingId);
/*     */   }
/*     */ 
/*     */   public List<House> getHouseList(Long unitId) {
/*  51 */     return this.houseMapper.getHouseList(unitId);
/*     */   }
/*     */ 
/*     */   public List<House> getUserHouseList(Long uid) {
/*  55 */     return this.houseMapper.getUserHouseList(uid);
/*     */   }
/*     */ 
/*     */   public House getHouseById(Long houseId) {
/*  59 */     return this.houseMapper.getHouseById(houseId);
/*     */   }
/*     */ 
/*     */   public String getHouseTitle(Long houseId) {
/*  63 */     House house = getHouseById(houseId);
/*     */ 
/*  65 */     String houseTitle = house.getHouseTitle();
/*     */ 
/*  67 */     houseTitle = !StringUtil.isEmpty(houseTitle) ? houseTitle.substring(0, houseTitle.indexOf("元") + 1) : "";
/*  68 */     return houseTitle;
/*     */   }
/*     */ 
/*     */   public List<HouseDTO> getHouseDTOList(List<House> houseList) {
/*  72 */     List dtoList = new ArrayList();
/*  73 */     for (House house : houseList) {
/*  74 */       dtoList.add(getHouseDTO(house));
/*     */     }
/*     */ 
/*  77 */     return dtoList;
/*     */   }
/*     */ 
/*     */   public HouseDTO getHouseDTO(House house) {
/*  81 */     HouseDTO dto = new HouseDTO();
/*  82 */     dto.setHouseName(house.getHouseName());
/*  83 */     dto.setHouseId(house.getHouseId());
/*  84 */     dto.setHouseTitle(house.getHouseTitle());
/*  85 */     Unit unit = this.houseMapper.getUnitById(house.getUnitId());
/*  86 */     if (unit != null) {
/*  87 */       dto.setUnit(unit);
/*  88 */       Building building = this.houseMapper.getBuildingById(unit.getBuildingId());
/*  89 */       if (building != null) {
/*  90 */         dto.setBuilding(building);
/*  91 */         Community community = this.houseMapper.getCommunityById(building.getCommunityId());
/*  92 */         if (community != null) {
/*  93 */           dto.setCommunity(community);
/*  94 */           City city = this.houseMapper.getCityById(Integer.valueOf(community.getCityId()));
/*  95 */           dto.setCity(city);
/*     */         }
/*     */       }
/*     */     }
/*  99 */     return dto;
/*     */   }
/*     */   @Transactional
/*     */   public void createUserHouse(Long uid, Long houseId) {
/* 104 */     Map paramMap = new HashMap();
/* 105 */     paramMap.put("pkId", MD5Util.getMD5String("uid=" + uid + "_houseId=" + houseId));
/* 106 */     paramMap.put("uid", uid);
/* 107 */     paramMap.put("houseId", houseId);
/*     */     try {
/* 109 */       this.houseMapper.createUserHouse(paramMap);
/* 110 */       Map info = getHouseInfo(houseId);
/* 111 */       String address = (String)info.get("title");
/* 112 */       if ((!StringUtil.isEmpty(address)) && 
/* 113 */         (this.userService.getUserAddressByHouseId(uid, houseId) == null)) {
/* 114 */         this.userService.addUserAddress(uid, address, houseId);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 119 */       log.error("[createUserHouse] uid=" + uid + ", houseId=" + houseId + "\n" + e.getCause());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void delUserHouse(Long uid, Long houseId) {
/* 124 */     String pkId = MD5Util.getMD5String("uid=" + uid + "_houseId=" + houseId);
/* 125 */     this.houseMapper.delUserHouseByPkId(pkId);
/*     */   }
/*     */ 
/*     */   public Map<String, String> getHouseInfo(Long houseId)
/*     */   {
/* 136 */     Map map = new HashMap();
/* 137 */     HouseDTO dto = getHouseDTO(this.houseMapper.getHouseById(houseId));
/* 138 */     StringBuffer sb = new StringBuffer();
/* 139 */     StringBuffer sb2 = new StringBuffer();
/* 140 */     if (dto.getCity() != null) {
/* 141 */       sb.append(" ");
/* 142 */       sb.append(dto.getCity().getCityName());
/*     */     }
/* 144 */     if (dto.getCommunity() != null) {
/* 145 */       sb.append(dto.getCommunity().getCommunityName());
/* 146 */       sb.append(" ");
/* 147 */       sb2.append(dto.getCommunity().getCommunityId());
/* 148 */       map.put("communityId", dto.getCommunity().getCommunityId() + "");
/*     */     }
/* 150 */     if (dto.getBuilding() != null)
/*     */     {
/* 153 */       sb2.append("_");
/* 154 */       sb2.append(dto.getBuilding().getBuildingId());
/*     */     }
/* 156 */     if (dto.getUnit() != null) {
/* 157 */       sb2.append("_");
/* 158 */       sb2.append(dto.getUnit().getUnitId());
/*     */     }
/*     */ 
/* 161 */     sb.append(dto.getHouseTitle());
/* 162 */     sb2.append("_");
/* 163 */     sb2.append(dto.getHouseId());
/* 164 */     map.put("roomNo", sb2.toString());
/* 165 */     map.put("title", sb.toString());
/* 166 */     return map;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.HouseService
 * JD-Core Version:    0.6.0
 */