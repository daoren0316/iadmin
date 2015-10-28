/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.util.MaxMD5Util;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Building;
import cc.kokoko.server.ibutler.domain.City;
import cc.kokoko.server.ibutler.domain.Community;
import cc.kokoko.server.ibutler.domain.House;
import cc.kokoko.server.ibutler.domain.Operator;
import cc.kokoko.server.ibutler.domain.Site;
import cc.kokoko.server.ibutler.domain.Unit;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.persistence.CommunityMapper;
import cc.kokoko.server.ibutler.persistence.OperatorMapper;
import cc.kokoko.server.ibutler.persistence.SiteMapper;
import cc.kokoko.server.ibutler.persistence.UserMapper;
/*     */ 
/*     */ @Service("communityService")
/*     */ public class CommunityService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private CommunityMapper communityMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserMapper userMapper;
/*     */ 
/*     */   @Autowired
/*     */   private SiteMapper siteMapper;
/*     */ 
/*     */   @Autowired
/*     */   private OperatorMapper operatorMapper;
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insert(Community community)
/*     */   {
/*  45 */     this.communityMapper.insertCommunity(community);
/*     */ 
/*  47 */     User user = new User();
/*  48 */     String name = new StringBuilder().append(community.getCommunityName()).append("服务小站").toString();
/*  49 */     String token = MaxMD5Util.createToken(name);
/*  50 */     String passwordMd5 = MaxMD5Util.toMD5("5rdxcft6");
/*  51 */     user.setUsername(name);
/*  52 */     user.setPassword(passwordMd5);
/*  53 */     user.setToken(token);
/*  54 */     user.setNickname(name);
/*  55 */     user.setUserType(new Byte("2"));
/*  56 */     user.setCommunityId(community.getCommunityId());
/*  57 */     user.setAppStatus(AppConst.UserStatus.APP_NO_DOWN);
/*  58 */     user.setCardStatus(AppConst.UserStatus.CARD_NO_HAS);
/*  59 */     this.userMapper.addUser(user);
/*     */ 
/*  62 */     Operator operator = new Operator();
/*  63 */     operator.setUid(user.getUid());
/*  64 */     operator.setUsername(name);
/*  65 */     operator.setPassword(passwordMd5);
/*  66 */     operator.setToken(token);
/*  67 */     operator.setGender(new Byte("1"));
/*  68 */     operator.setNickname(name);
/*  69 */     operator.setUserType(new Byte("2"));
/*  70 */     operator.setUserStatus(new Byte("1"));
/*  71 */     operator.setUserFlag(new Byte("2"));
/*  72 */     operator.setCommunityId(community.getCommunityId());
/*  73 */     this.operatorMapper.insert(operator);
/*     */ 
/*  76 */     Site site = new Site();
/*  77 */     site.setSiteId(user.getUid());
/*  78 */     site.setSiteName(name);
/*  79 */     site.setCommunityId(community.getCommunityId());
/*  80 */     site.setSignature("贴心服务 幸福小区");
/*  81 */     site.setSiteDesc(new StringBuilder().append("欢迎您访问").append(name).toString());
/*  82 */     this.siteMapper.insertSite(site);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getCommunityRecord(int cityId, String communityName, Integer pagesize, Integer curPage)
/*     */   {
/*  95 */     Map paramMap = new HashMap();
/*  96 */     paramMap.put("cityId", Integer.valueOf(cityId));
/*  97 */     paramMap.put("communityName", communityName);
/*  98 */     paramMap.put("pagesize", pagesize);
/*  99 */     paramMap.put("start", curPage);
/* 100 */     List list = this.communityMapper.getCommunityRecord(paramMap);
/* 101 */     Long count = this.communityMapper.getCommunityCount(paramMap);
/* 102 */     paramMap = new HashMap();
/* 103 */     paramMap.put("list", list);
/* 104 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 105 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public boolean checkCommunity(int cityId, String communityName)
/*     */   {
/* 116 */     Boolean bool = Boolean.valueOf(false);
/* 117 */     Map paramMap = new HashMap();
/* 118 */     paramMap.put("cityId", Integer.valueOf(cityId));
/* 119 */     paramMap.put("communityName", communityName);
/* 120 */     Community community = this.communityMapper.checkCommunity(paramMap);
/* 121 */     if (community == null)
/* 122 */       bool = Boolean.valueOf(true);
/* 123 */     return bool.booleanValue();
/*     */   }
/*     */ 
/*     */   public List<Building> getBuildingByCId(Long communityId)
/*     */   {
/* 133 */     return this.communityMapper.getBuildingByCId(communityId);
/*     */   }
/*     */ 
/*     */   public List<Unit> getUnitByBId(Long buildingId)
/*     */   {
/* 143 */     return this.communityMapper.getUnitByBId(buildingId);
/*     */   }
/*     */ 
/*     */   public List<House> getHouseByUId(Long unitId)
/*     */   {
/* 153 */     return this.communityMapper.getHouseByUId(unitId);
/*     */   }
/*     */ 
/*     */   public List<City> getAllCity()
/*     */   {
/* 162 */     return this.communityMapper.getAllCity();
/*     */   }
/*     */ 
/*     */   public List<Community> getAllCommunity()
/*     */   {
/* 172 */     return this.communityMapper.getAllCommunity();
/*     */   }
/*     */ 
/*     */   public List<Community> getAllCommunityById(Long cityId)
/*     */   {
/* 182 */     return this.communityMapper.getAllCommunityById(cityId.intValue());
/*     */   }
/*     */ 
/*     */   public String getUserAddrByHouseId(Long houseId)
/*     */   {
/* 192 */     return this.communityMapper.getUserAddrByHouseId(houseId);
/*     */   }
/*     */ 
/*     */   public Community getCommunityByHouseId(Long houseId)
/*     */   {
/* 202 */     return this.communityMapper.getCommunityByHouseId(houseId);
/*     */   }
/*     */ 
/*     */   public String getCommunityName(Long communityId)
/*     */   {
/* 212 */     return this.communityMapper.getCommunityName(communityId);
/*     */   }
/*     */ 
/*     */   public String buildTree(String path)
/*     */   {
/* 222 */     StringBuilder sb = new StringBuilder();
/*     */ 
/* 224 */     List<City> cityList = this.communityMapper.getAllCity();
/*     */ 
/* 226 */     if (cityList.size() > 0) {
/* 227 */       sb.append("<ul class=\"tree treeFolder collapse\">");
/* 228 */       for (City city : cityList) {
/* 229 */         sb.append(new StringBuilder().append("<li><a href=\"").append(path).append("/admin/cty_ctyTree.do?cityId=").append(city.getCityId()).append("\" ").append("target=\"ajax\" rel=\"communityBox\">").append(city.getCityName()).append("</a>").toString());
/*     */ 
/* 232 */         List<Community> communityList = this.communityMapper.getAllCommunityById(city.getCityId());
/*     */ 
/* 234 */         if (communityList.size() > 0) {
/* 235 */           sb.append("<ul>");
/* 236 */           for (Community community : communityList) {
/* 237 */             sb.append(new StringBuilder().append("<li><a href=\"").append(path).append("/admin/building_buildingTree.do?communityId=").append(community.getCommunityId()).append("\" ").append("target=\"ajax\" rel=\"communityBox\">").append(community.getCommunityName()).append("</a>").toString());
/*     */ 
/* 240 */             List<Building> buildingList = this.communityMapper.getAllBuildingById(community.getCommunityId());
/*     */ 
/* 242 */             if (buildingList.size() > 0) {
/* 243 */               sb.append("<ul>");
/* 244 */               for (Building building : buildingList) {
/* 245 */                 sb.append(new StringBuilder().append("<li><a href=\"").append(path).append("/admin/unit_unitTree.do?buildingId=").append(building.getBuildingId()).append("\"").append("target=\"ajax\" rel=\"communityBox\">").append(building.getBuildingTitle()).append("</a>").toString());
/*     */ 
/* 248 */                 List<Unit> unitList = this.communityMapper.getAllUnitById(building.getBuildingId());
/*     */ 
/* 250 */                 if (unitList.size() > 0) {
/* 251 */                   sb.append("<ul>");
/* 252 */                   for (Unit unit : unitList) {
/* 253 */                     sb.append(new StringBuilder().append("<li><a href=\"").append(path).append("/admin/house_houseTree.do?unitId=").append(unit.getUnitId()).append("\"").append("target=\"ajax\" rel=\"communityBox\">").append(unit.getUnitName()).append("</a>").toString());
/*     */ 
/* 256 */                     List<House> houseList = this.communityMapper.getAllHouseById(unit.getUnitId());
/*     */ 
/* 258 */                     if (houseList.size() > 0) {
/* 259 */                       sb.append("<ul>");
/* 260 */                       for (House house : houseList) {
/* 261 */                         sb.append(new StringBuilder().append("<li><a href=\"javascript:;\">").append(house.getHouseName()).append("</a></li>").toString());
/*     */                       }
/* 263 */                       sb.append("</ul>");
/*     */                     }
/* 265 */                     sb.append("</li>");
/*     */                   }
/* 267 */                   sb.append("</ul>");
/*     */                 }
/* 269 */                 sb.append("</li>");
/*     */               }
/* 271 */               sb.append("</ul>");
/*     */             }
/* 273 */             sb.append("</li>");
/*     */           }
/* 275 */           sb.append("</ul>");
/*     */         }
/* 277 */         sb.append("</li>");
/*     */       }
/* 279 */       sb.append("<ul>");
/*     */     }
/* 281 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void export(Sheet sheet)
/*     */   {
/*     */     try
/*     */     {
/* 294 */       int rows = sheet.getRows();
/*     */ 
/* 296 */       LinkedHashMap communityMap = loadMess(1, rows, 0, sheet, "community");
/*     */ 
/* 298 */       Set set = communityMap.keySet();
/* 299 */       Iterator iterator = set.iterator();
/* 300 */       while (iterator.hasNext()) {
/* 301 */         String key = (String)iterator.next();
/* 302 */         doMain domain1 = (doMain)communityMap.get(key);
/*     */ 
/* 304 */         Community community = new Community();
/* 305 */         community.setCityId(1);
/* 306 */         community.setCommunityName(domain1.getName());
/*     */ 
/* 308 */         insert(community);
/*     */ 
/* 310 */         Long communityId = community.getCommunityId();
/*     */ 
/* 312 */         LinkedHashMap buildingMap = loadMess(domain1.getScopeStart(), domain1.getScopeEnd(), 1, sheet, new StringBuilder().append(key).append("building").toString());
/*     */ 
/* 314 */         Set set1 = buildingMap.keySet();
/* 315 */         Iterator iterator1 = set1.iterator();
/* 316 */         while (iterator1.hasNext()) {
/* 317 */           String key1 = (String)iterator1.next();
/* 318 */           doMain domain2 = (doMain)buildingMap.get(key1);
/*     */ 
/* 320 */           Building building = new Building();
/* 321 */           building.setBuildingTitle(new StringBuilder().append(domain2.getName()).append("幢").toString());
/* 322 */           building.setCommunityId(communityId);
/* 323 */           this.communityMapper.insertBuilding(building);
/*     */ 
/* 325 */           Long buildingId = building.getBuildingId();
/*     */ 
/* 327 */           LinkedHashMap unitMap = loadMess(domain2.getScopeStart(), domain2.getScopeEnd(), 2, sheet, new StringBuilder().append(key1).append("unit").toString());
/*     */ 
/* 329 */           Set set2 = unitMap.keySet();
/* 330 */           Iterator iterator2 = set2.iterator();
/* 331 */           while (iterator2.hasNext()) {
/* 332 */             String key2 = (String)iterator2.next();
/* 333 */             doMain domain3 = (doMain)unitMap.get(key2);
/*     */ 
/* 335 */             Unit unit = new Unit();
/* 336 */             unit.setUnitName(new StringBuilder().append(domain3.getName()).append("单元").toString());
/* 337 */             unit.setBuildingId(buildingId);
/* 338 */             this.communityMapper.insertUnit(unit);
/*     */ 
/* 340 */             Long unitId = unit.getUnitId();
/*     */ 
/* 342 */             LinkedHashMap houseMap = loadMess(domain3.getScopeStart(), domain3.getScopeEnd(), 3, sheet, new StringBuilder().append(key2).append("house").toString());
/*     */ 
/* 344 */             Set set3 = houseMap.keySet();
/* 345 */             Iterator iterator3 = set3.iterator();
/* 346 */             while (iterator3.hasNext()) {
/* 347 */               String key3 = (String)iterator3.next();
/* 348 */               doMain domain4 = (doMain)houseMap.get(key3);
/*     */ 
/* 350 */               House house = new House();
/* 351 */               house.setHouseTitle(new StringBuilder().append(domain2.getName()).append("幢").append(domain3.getName()).append("单元").append(domain4.getName()).toString());
/* 352 */               house.setHouseName(domain4.getName());
/* 353 */               house.setUnitId(unitId);
/* 354 */               house.setCommunityId(communityId);
/* 355 */               this.communityMapper.insertHouse(house);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 361 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public LinkedHashMap<String, Object> loadMess(int start, int end, int index, Sheet sheet, String key)
/*     */   {
/* 376 */     LinkedHashMap map = new LinkedHashMap();
/* 377 */     String name = null;
/* 378 */     int keySign_ = 1;
/* 379 */     int scope = start;
/*     */ 
/* 381 */     while (start < end)
/*     */     {
/* 383 */       Cell cell = sheet.getCell(index, start);
/* 384 */       if ((cell.getContents() != null) && (cell.getContents() != "")) {
/* 385 */         if (name == null)
/*     */         {
/* 387 */           name = cell.getContents();
/* 388 */         } else if ((name != null) && (!name.equals(cell.getContents()))) {
/* 389 */           doMain domain = new doMain();
/* 390 */           domain.setScopeStart(scope);
/*     */ 
/* 392 */           domain.setScopeEnd(start);
/* 393 */           domain.setName(name);
/* 394 */           map.put(new StringBuilder().append(key).append(keySign_).toString(), domain);
/*     */ 
/* 396 */           name = cell.getContents();
/* 397 */           keySign_++;
/* 398 */           scope = start;
/*     */         }
/*     */       }
/* 401 */       start++;
/*     */     }
/*     */ 
/* 404 */     if (start == end) {
/* 405 */       doMain domain = new doMain();
/* 406 */       domain.setScopeStart(scope);
/* 407 */       domain.setName(name);
/* 408 */       domain.setScopeEnd(end);
/* 409 */       map.put(new StringBuilder().append(key).append(keySign_).toString(), domain);
/*     */     }
/*     */ 
/* 412 */     return map; } 
/*     */   class doMain { private String name;
/* 417 */     private int scopeStart = 1;
/*     */     private int scopeEnd;
/*     */ 
/*     */     doMain() {  }
/*     */ 
/* 421 */     String getName() { return this.name; }
/*     */ 
/*     */     void setName(String name)
/*     */     {
/* 425 */       this.name = name;
/*     */     }
/*     */ 
/*     */     int getScopeEnd() {
/* 429 */       return this.scopeEnd;
/*     */     }
/*     */ 
/*     */     void setScopeEnd(int scopeEnd) {
/* 433 */       this.scopeEnd = scopeEnd;
/*     */     }
/*     */ 
/*     */     int getScopeStart() {
/* 437 */       return this.scopeStart;
/*     */     }
/*     */ 
/*     */     void setScopeStart(int scopeStart) {
/* 441 */       this.scopeStart = scopeStart;
/*     */     }
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.CommunityService
 * JD-Core Version:    0.6.0
 */