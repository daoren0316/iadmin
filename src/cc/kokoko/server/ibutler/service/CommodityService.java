/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Attachment;
import cc.kokoko.server.ibutler.domain.Commodity;
import cc.kokoko.server.ibutler.domain.CommodityUser;
import cc.kokoko.server.ibutler.domain.Favorite;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.domain.dto.AttachmentDTO;
import cc.kokoko.server.ibutler.domain.dto.CommodityDTO;
import cc.kokoko.server.ibutler.domain.dto.CommodityDescDTO;
import cc.kokoko.server.ibutler.persistence.CommodityMapper;
import cc.kokoko.server.ibutler.service.util.AttachmentUtil;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
/*     */ 
/*     */ @Service("commodityService")
/*     */ public class CommodityService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private CommodityMapper commodityMapper;
/*     */ 
/*     */   @Autowired
/*     */   private ShopService shopService;
/*     */ 
/*     */   @Autowired
/*     */   private SiteService siteService;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentService attachmentService;
/*     */ 
/*     */   @Autowired
/*     */   private MoneyService moneyService;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*  41 */   private static Logger log = LoggerFactory.getLogger(CommodityService.class);
/*     */ 
/*     */   public List<Commodity> getCommodityList(Long siteId) {
/*  44 */     return this.commodityMapper.getCommodityList(siteId);
/*     */   }
/*     */ 
/*     */   public List<Commodity> getLocalCommodityList(Long communityId) {
/*  48 */     return this.commodityMapper.getLocalCommodityList(communityId);
/*     */   }
/*     */ 
/*     */   public void addCommodityFav(Long uid, Long commodityId)
/*     */   {
/*  58 */     Commodity commodity = getCommodityById(commodityId);
/*  59 */     if (commodity == null) {
/*  60 */       return;
/*     */     }
/*  62 */     if (this.shopService.isFav(uid, commodityId, AppConst.FavType.COMMODITY) == true) {
/*  63 */       return;
/*     */     }
/*  65 */     Favorite favorite = new Favorite();
/*  66 */     favorite.setFavPicUrl(commodity.getTitlePicUrl());
/*  67 */     favorite.setFavTitle(commodity.getCommodityTitle());
/*  68 */     favorite.setFavType(AppConst.FavType.COMMODITY);
/*  69 */     favorite.setReferId(commodityId);
/*  70 */     favorite.setUid(uid);
/*  71 */     this.userService.addFavorite(favorite);
/*     */   }
/*     */ 
/*     */   public List<CommodityUser> getCommodityUserList(Long commodityId)
/*     */   {
/*  76 */     List<CommodityUser>  commodityUserList = this.commodityMapper.getCommodityUserList(commodityId);
/*  77 */     List commodityUserList1 = null;
/*  78 */     if (commodityUserList.size() > 0) {
/*  79 */       commodityUserList1 = new ArrayList();
/*  80 */       for (CommodityUser commodityUser : commodityUserList) {
/*  81 */         String houseTitle = commodityUser.getHouseTitle();
/*  82 */         houseTitle = !StringUtil.isEmpty(houseTitle) ? houseTitle.substring(0, houseTitle.indexOf("元") + 1) : "";
/*  83 */         commodityUser.setHouseTitle(houseTitle);
/*  84 */         commodityUserList1.add(commodityUser);
/*     */       }
/*     */     }
/*  87 */     return commodityUserList1;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> join(Long uid, Long commodityId, Integer amount, String phoneNumber, Long addressId, String houseTitle)
/*     */   {
/*  92 */     Map map = new HashMap();
/*  93 */     String msg = "";
/*  94 */     int code = 0;
/*  95 */     map.put("msg", msg);
/*  96 */     map.put("code", Integer.valueOf(code));
/*  97 */     Commodity commodity = this.commodityMapper.getCommodityById(commodityId);
/*  98 */     if (commodity == null) {
/*  99 */       log.error("[join]commodity is null, commodityId=" + commodityId);
/* 100 */       return map;
/*     */     }
/* 102 */     int remainAMount = commodity.getTotalAmount().intValue() - commodity.getSaledAmount().intValue();
/*     */ 
/* 104 */     if (remainAMount < amount.intValue()) {
/* 105 */       map.put("msg", "购买数量有限制，剩余购买数量" + remainAMount + ", 本次购买=" + amount);
/* 106 */       map.put("code", Integer.valueOf(-1025));
/* 107 */       log.error("[join] 购买数量有限制，剩余购买数量" + remainAMount + ", 本次购买=" + amount);
/*     */ 
/* 109 */       return map;
/*     */     }
/* 111 */     CommodityUser commodityUser = new CommodityUser();
/* 112 */     commodityUser.setApplyTime(new Date());
/* 113 */     commodityUser.setCommodityId(commodityId);
/* 114 */     commodityUser.setAmount(amount);
/* 115 */     commodityUser.setPhoneNumber(phoneNumber);
/* 116 */     commodityUser.setUid(uid);
/* 117 */     commodityUser.setHouseTitle(houseTitle);
/* 118 */     this.commodityMapper.addCommodityUser(commodityUser);
/* 119 */     updateCommodityCount(commodityId, amount.intValue());
/* 120 */     double totalAmount = commodity.getPrice().doubleValue() * amount.intValue();
/* 121 */     double savingsAmount = commodity.getMarketPrice().doubleValue() - commodity.getPrice().doubleValue();
/*     */ 
/* 123 */     msg = this.moneyService.createOrder(uid, commodity.getUid(), new Byte("1"), commodity.getCommodityTitle(), totalAmount, commodity.getTitlePicUrl(), commodityId, commodity.getCommodityTitle(), Double.valueOf(savingsAmount));
/*     */ 
/* 127 */     map.put("msg", msg);
/* 128 */     map.put("code", Integer.valueOf(code));
/* 129 */     return map;
/*     */   }
/*     */ 
/*     */   public void updateCommodityCount(Long commodityId, int amount) {
/* 133 */     Map map = new HashMap();
/* 134 */     map.put("commodityId", commodityId);
/* 135 */     map.put("amount", Integer.valueOf(amount));
/* 136 */     map.put("applyCount", Integer.valueOf(1));
/* 137 */     this.commodityMapper.updateCommodityCount(map);
/*     */   }
/*     */ 
/*     */   public List<CommodityDTO> commodityList2DTOList(List<Commodity> commodityList, Long invokingUid)
/*     */   {
/* 142 */     List dtoList = new ArrayList();
/* 143 */     if ((commodityList == null) || (commodityList.size() < 1)) {
/* 144 */       return dtoList;
/*     */     }
/* 146 */     for (Commodity commodity : commodityList) {
/* 147 */       if (commodity != null) {
/* 148 */         dtoList.add(commodity2DTO(commodity, false, invokingUid));
/*     */       }
/*     */     }
/*     */ 
/* 152 */     return dtoList;
/*     */   }
/*     */ 
/*     */   public CommodityDTO commodity2DTO(Commodity commodity, boolean getDetail, Long invokingUid)
/*     */   {
/* 157 */     if (commodity == null) {
/* 158 */       return null;
/*     */     }
/*     */ 
/* 161 */     CommodityDTO dto = new CommodityDTO();
/* 162 */     dto.setApplyCount(commodity.getApplyCount());
/*     */ 
/* 164 */     dto.setCommodityId(commodity.getCommodityId());
/* 165 */     dto.setCommodityTitle(commodity.getCommodityTitle());
/* 166 */     dto.setEndTime(commodity.getEndTime());
/* 167 */     dto.setMarketPrice(commodity.getMarketPrice());
/* 168 */     dto.setPostTime(commodity.getPostTime());
/* 169 */     dto.setPrice(commodity.getPrice());
/* 170 */     dto.setStartTime(commodity.getStartTime());
/* 171 */     dto.setTitlePicUrl(commodity.getTitlePicUrl());
/* 172 */     dto.setAmountLimit(commodity.getAmountLimit());
/* 173 */     dto.setIsAnytimeDawback(commodity.getIsAnytimeDawback());
/* 174 */     dto.setIsTimeoutDawback(commodity.getIsTimeoutDawback());
/* 175 */     if (getDetail == true) {
/* 176 */       CommodityDescDTO commodityDesc = new CommodityDescDTO();
/* 177 */       commodityDesc.setDetail(commodity.getCommodityDesc());
/* 178 */       commodityDesc.setNotice(commodity.getNotice());
/* 179 */       dto.setCommodityDesc(commodityDesc);
/*     */ 
/* 181 */       dto.setSite(this.siteService.getSiteDetail(commodity.getUid()));
/*     */     }
/* 183 */     dto.setIsFav(this.userService.getFavStatus(commodity.getCommodityId(), invokingUid, AppConst.FavType.COMMODITY));
/*     */ 
/* 185 */     if (!StringUtil.isEmpty(commodity.getAttachmentList())) {
/* 186 */       dto.setAttachmentList(ObjectUtil.getObjectListFromJson(commodity.getAttachmentList(), AttachmentDTO.class));
/*     */     }
/*     */ 
/* 189 */     int availableAmount = commodity.getTotalAmount().intValue() - commodity.getSaledAmount().intValue();
/*     */ 
/* 191 */     dto.setAvailableAmount(availableAmount);
/* 192 */     return dto;
/*     */   }
/*     */ 
/*     */   public void createCommodity(User user, Commodity commodity) {
/* 196 */     commodity.setUid(user.getUid());
/* 197 */     commodity.setCommunityId(user.getCommunityId());
/* 198 */     commodity.setApplyCount(Integer.valueOf(0));
/* 199 */     commodity.setSaledAmount(Integer.valueOf(0));
/* 200 */     this.commodityMapper.createCommodity(commodity);
/*     */   }
/*     */ 
/*     */   public void delCommodityById(Long commodityId)
/*     */   {
/* 209 */     this.commodityMapper.delCommodityById(commodityId);
/*     */   }
/*     */ 
/*     */   public Commodity getCommodityById(Long commodityId)
/*     */   {
/* 219 */     return this.commodityMapper.getCommodityById(commodityId);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getCommodityRecord(Long uid, Long communityId, String starttime, String endtime, Integer pagesize, Integer curPage)
/*     */   {
/* 235 */     Map paramMap = new HashMap();
/* 236 */     paramMap.put("uid", uid);
/* 237 */     paramMap.put("communityId", communityId);
/* 238 */     paramMap.put("pagesize", pagesize);
/* 239 */     paramMap.put("start", curPage);
/* 240 */     paramMap.put("starttime", starttime);
/* 241 */     paramMap.put("endtime", endtime);
/*     */ 
/* 243 */     List list = this.commodityMapper.getCommodityRecord(paramMap);
/* 244 */     Long count = this.commodityMapper.getCommodityCount(paramMap);
/* 245 */     paramMap = new HashMap();
/* 246 */     paramMap.put("list", list);
/* 247 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 248 */     return paramMap;
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insert(Long uid, Long communityId, File titleImage, String titleImageFileName, File[] image, String[] imageFileName, Commodity commodity)
/*     */   {
/* 265 */     String thumbnailUrl = commodity.getTitlePicUrl();
/*     */ 
/* 267 */     if (!StringUtil.isEmpty(titleImageFileName))
/*     */     {
/* 269 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, titleImage, titleImageFileName);
/*     */ 
/* 271 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 272 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/* 274 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*     */ 
/* 277 */     String attachmentList = "";
/*     */ 
/* 279 */     if ((imageFileName != null) && (imageFileName.length > 0)) {
/* 280 */       List list = new ArrayList();
/*     */ 
/* 282 */       for (int i = 0; i < imageFileName.length; i++) {
/* 283 */         File file = image[i];
/* 284 */         String fileName = imageFileName[i];
/*     */ 
/* 286 */         Attachment attachment = this.attachmentService.uploadAttachment(uid, file, fileName);
/*     */ 
/* 288 */         this.attachmentService.createAttachment(attachment);
/*     */ 
/* 290 */         AttachmentDTO attachmentDTO = AttachmentUtil.Attachment2AttachmentDTO(attachment);
/*     */ 
/* 292 */         list.add(attachmentDTO);
/*     */       }
/*     */ 
/* 295 */       attachmentList = ObjectUtil.getJsonStr(list);
/*     */     }
/*     */ 
/* 299 */     commodity.setUid(uid);
/* 300 */     commodity.setCommunityId(communityId);
/* 301 */     commodity.setTitlePicUrl(thumbnailUrl);
/* 302 */     commodity.setAttachmentList(attachmentList);
/* 303 */     commodity.setApplyCount(Integer.valueOf(0));
/* 304 */     commodity.setSaledAmount(Integer.valueOf(0));
/*     */ 
/* 306 */     this.commodityMapper.createCommodity(commodity);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void update(Long uid, Long commodityId, File titleImage, String titleImageFileName, File[] image, String[] imageFileName, Commodity commodity)
/*     */   {
/* 323 */     Commodity commodity1 = this.commodityMapper.getCommodityById(commodityId);
/*     */ 
/* 326 */     String thumbnailUrl = commodity1.getTitlePicUrl();
/*     */ 
/* 328 */     if (!StringUtil.isEmpty(titleImageFileName))
/*     */     {
/* 330 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, titleImage, titleImageFileName);
/*     */ 
/* 332 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 333 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/* 335 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*     */ 
/* 338 */     String attachmentList = commodity1.getAttachmentList();
/*     */ 
/* 340 */     if ((imageFileName != null) && (imageFileName.length > 0)) {
/* 341 */       List list = new ArrayList();
/*     */ 
/* 343 */       for (int i = 0; i < imageFileName.length; i++) {
/* 344 */         File file = image[i];
/* 345 */         String fileName = imageFileName[i];
/*     */ 
/* 347 */         Attachment attachment = this.attachmentService.uploadAttachment(uid, file, fileName);
/*     */ 
/* 349 */         this.attachmentService.createAttachment(attachment);
/*     */ 
/* 351 */         AttachmentDTO attachmentDTO = AttachmentUtil.Attachment2AttachmentDTO(attachment);
/*     */ 
/* 353 */         list.add(attachmentDTO);
/*     */       }
/*     */ 
/* 356 */       attachmentList = ObjectUtil.getJsonStr(list);
/*     */     }
/*     */ 
/* 359 */     commodity.setTitlePicUrl(thumbnailUrl);
/* 360 */     commodity.setAttachmentList(attachmentList);
/* 361 */     commodity.setCommodityId(commodityId);
/*     */ 
/* 363 */     this.commodityMapper.update(commodity);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.CommodityService
 * JD-Core Version:    0.6.0
 */