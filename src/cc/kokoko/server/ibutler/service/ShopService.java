/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.util.MaxMD5Util;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Attachment;
import cc.kokoko.server.ibutler.domain.Favorite;
import cc.kokoko.server.ibutler.domain.Operator;
import cc.kokoko.server.ibutler.domain.Shop;
import cc.kokoko.server.ibutler.domain.ShopCard;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.domain.dto.ShopCardDTO;
import cc.kokoko.server.ibutler.domain.dto.ShopDTO;
import cc.kokoko.server.ibutler.persistence.OperatorMapper;
import cc.kokoko.server.ibutler.persistence.ShopMapper;
import cc.kokoko.server.ibutler.persistence.UserMapper;
import cc.kokoko.server.ibutler.service.util.AttachmentUtil;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
/*     */ 
/*     */ @Service("shopService")
/*     */ public class ShopService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ShopMapper shopMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserMapper userMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentService attachmentService;
/*     */ 
/*     */   @Autowired
/*     */   private OperatorMapper operatorMapper;
/*     */ 
/*     */   public List<Shop> getLocalShopList(Long communityId, Byte type)
/*     */   {
/*  39 */     return this.shopMapper.getLocalShopList(communityId);
/*     */   }
/*     */ 
/*     */   public List<Shop> getShopList(Long uid, Byte type) {
/*  43 */     return this.shopMapper.getShopList(uid);
/*     */   }
/*     */ 
/*     */   public Shop getShopById(Long shopId) {
/*  47 */     Shop shop = this.shopMapper.getShopById(shopId);
/*  48 */     return shop;
/*     */   }
/*     */ 
/*     */   public Shop getShopById(Long shopId, Long invokeUid) {
/*  52 */     Shop shop = getShopById(shopId);
/*  53 */     shop.setIsFav(this.userService.getFavStatus(shopId, invokeUid, AppConst.FavType.SHOP));
/*     */ 
/*  55 */     return shop;
/*     */   }
/*     */ 
/*     */   public void addShopFav(Long uid, Long shopId) {
/*  59 */     Shop shop = getShopById(shopId);
/*  60 */     if (shop == null) {
/*  61 */       return;
/*     */     }
/*  63 */     if (isFav(uid, shopId, AppConst.FavType.SHOP) == true) {
/*  64 */       return;
/*     */     }
/*  66 */     Favorite favorite = new Favorite();
/*  67 */     favorite.setFavPicUrl(shop.getLogoUrl());
/*  68 */     favorite.setFavTitle(shop.getShopName());
/*  69 */     favorite.setFavType(AppConst.FavType.SHOP);
/*  70 */     favorite.setReferId(shop.getShopId());
/*  71 */     favorite.setUid(uid);
/*  72 */     this.userService.addFavorite(favorite);
/*     */   }
/*     */ 
/*     */   public boolean isFav(Long uid, Long referId, Byte favType) {
/*  76 */     boolean ret = false;
/*  77 */     Favorite pramFavorite = new Favorite();
/*  78 */     pramFavorite.setFavoriteId(referId);
/*  79 */     pramFavorite.setUid(uid);
/*  80 */     pramFavorite.setFavType(favType);
/*  81 */     pramFavorite.setReferId(referId);
/*  82 */     if (this.userService.getFavorite(pramFavorite) != null) {
/*  83 */       ret = true;
/*     */     }
/*  85 */     return ret;
/*     */   }
/*     */ 
/*     */   public void setCardPassword(Long uid)
/*     */   {
/*     */   }
/*     */ 
/*     */   public List<ShopCard> getShopCardList(Long shopId)
/*     */   {
/*  94 */     return this.shopMapper.getShopCardList(shopId);
/*     */   }
/*     */ 
/*     */   public List<ShopCardDTO> getShopCardDTOList(List<ShopCard> shopCardList) {
/*  98 */     List list = new ArrayList();
/*  99 */     for (ShopCard shopCard : shopCardList) {
/* 100 */       list.add(getShopCardDTO(shopCard));
/*     */     }
/*     */ 
/* 103 */     return list;
/*     */   }
/*     */ 
/*     */   public ShopCardDTO getShopCardDTO(ShopCard shopCard) {
/* 107 */     ShopCardDTO dto = new ShopCardDTO();
/* 108 */     dto.setCardTitle(shopCard.getCardTitle());
/* 109 */     dto.setChargeAmount(shopCard.getChargeAmount());
/* 110 */     dto.setDiscountRate(shopCard.getDiscountRate());
/* 111 */     dto.setShopCardId(shopCard.getShopCardId());
/* 112 */     dto.setShopId(shopCard.getShopId());
/* 113 */     dto.setPicUrl(shopCard.getPicUrl());
/*     */ 
/* 115 */     Map map = new HashMap();
/* 116 */     map.put("content", shopCard.getContent());
/*     */ 
/* 118 */     dto.setCardDetail(map);
/* 119 */     return dto;
/*     */   }
/*     */ 
/*     */   public ShopDTO shop2DTO(Shop shop, Long invokingUid)
/*     */   {
/* 124 */     ShopDTO dto = new ShopDTO();
/* 125 */     if (shop == null) {
/* 126 */       return dto;
/*     */     }
/* 128 */     dto.setCreatedTime(shop.getCreatedTime());
/* 129 */     dto.setIsFav(Boolean.valueOf(isFav(invokingUid, shop.getShopId(), AppConst.FavType.SHOP)));
/*     */ 
/* 131 */     dto.setLogoUrl(shop.getLogoUrl());
/* 132 */     dto.setPhoneNumber(shop.getPhoneNumber());
/* 133 */     dto.setShopAddress(shop.getShopAddress());
/* 134 */     dto.setShopDesc(shop.getShopDesc());
/* 135 */     dto.setShopDistance(shop.getShopDistance());
/* 136 */     dto.setShopId(shop.getShopId());
/* 137 */     dto.setShopName(shop.getShopName());
/* 138 */     dto.setShopType(shop.getShopType());
/* 139 */     dto.setSignature(shop.getSignature());
/* 140 */     dto.setDiscount(shop.getDiscount());
/* 141 */     if (!StringUtil.isEmpty(shop.getPicList())) {
/* 142 */       List picUrlList = ObjectUtil.getObjectListFromJson(shop.getPicList(), String.class);
/*     */ 
/* 144 */       dto.setPicUrlList(picUrlList);
/*     */     }
/* 146 */     return dto;
/*     */   }
/*     */ 
/*     */   public List<ShopDTO> shopList2DTOList(List<Shop> shopList, Long invokingUid) {
/* 150 */     List dtoList = new ArrayList();
/* 151 */     if ((shopList == null) || (shopList.size() < 1)) {
/* 152 */       return dtoList;
/*     */     }
/* 154 */     for (Shop shop : shopList) {
/* 155 */       if (shop != null) {
/* 156 */         dtoList.add(shop2DTO(shop, invokingUid));
/*     */       }
/*     */     }
/* 159 */     return dtoList;
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void updateShop(Shop shop)
/*     */   {
/* 170 */     this.shopMapper.updateShop(shop);
/*     */ 
/* 172 */     User user = new User();
/* 173 */     user.setPhoneNumber(shop.getPhoneNumber());
/* 174 */     user.setPublicAddress(shop.getShopAddress());
/* 175 */     user.setProfileImage(shop.getLogoUrl());
/* 176 */     user.setUid(shop.getShopId());
/*     */ 
/* 178 */     this.userService.updateUser(user);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void updateShop(Long uid, File image, String imageFileName, Shop shop)
/*     */   {
/* 192 */     String thumbnailUrl = shop.getLogoUrl();
/*     */ 
/* 194 */     if (!StringUtil.isEmpty(imageFileName))
/*     */     {
/* 196 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, image, imageFileName);
/*     */ 
/* 198 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 199 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/* 201 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*     */ 
/* 204 */     shop.setLogoUrl(thumbnailUrl);
/* 205 */     this.shopMapper.updateShop(shop);
/*     */ 
/* 207 */     User user = new User();
/* 208 */     user.setPhoneNumber(shop.getPhoneNumber());
/* 209 */     user.setPublicAddress(shop.getShopAddress());
/* 210 */     user.setProfileImage(thumbnailUrl);
/*     */ 
/* 212 */     this.userService.updateUser(user);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insert(Long uid, Long communityId, File image, String imageFileName, String username, String password, Shop shop)
/*     */   {
/* 229 */     String thumbnailUrl = shop.getLogoUrl();
/*     */ 
/* 231 */     if (!StringUtil.isEmpty(imageFileName))
/*     */     {
/* 233 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, image, imageFileName);
/*     */ 
/* 235 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 236 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/* 238 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*     */ 
/* 241 */     String token = MaxMD5Util.createToken(shop.getShopName());
/* 242 */     String passwordMd5 = MaxMD5Util.toMD5(password);
/*     */ 
/* 245 */     User user = new User();
/* 246 */     user.setUsername(username);
/* 247 */     user.setToken(token);
/* 248 */     user.setPassword(passwordMd5);
/* 249 */     user.setPhoneNumber(shop.getPhoneNumber());
/* 250 */     user.setNickname(shop.getShopName());
/* 251 */     user.setCommunityId(communityId);
/* 252 */     user.setProfileImage(thumbnailUrl);
/* 253 */     user.setUserType(new Byte("1"));
/* 254 */     user.setGender(new Byte("1"));
/* 255 */     user.setAppStatus(AppConst.UserStatus.APP_NO_DOWN);
/* 256 */     user.setCardStatus(AppConst.UserStatus.CARD_NO_HAS);
/* 257 */     this.userMapper.addUser(user);
/*     */ 
/* 260 */     Operator operator = new Operator();
/* 261 */     operator.setUid(user.getUid());
/* 262 */     operator.setUsername(username);
/* 263 */     operator.setPassword(passwordMd5);
/* 264 */     operator.setToken(token);
/* 265 */     operator.setGender(new Byte("1"));
/* 266 */     operator.setNickname(shop.getShopName());
/* 267 */     operator.setPhoneNumber(shop.getPhoneNumber());
/* 268 */     operator.setUserType(new Byte("1"));
/* 269 */     operator.setUserStatus(new Byte("0"));
/* 270 */     operator.setUserFlag(new Byte("1"));
/* 271 */     operator.setCommunityId(communityId);
/* 272 */     this.operatorMapper.insert(operator);
/*     */ 
/* 275 */     shop.setShopId(user.getUid());
/* 276 */     shop.setLogoUrl(thumbnailUrl);
/* 277 */     shop.setCommunityId(communityId);
/* 278 */     shop.setFlag(new Byte("0"));
/* 279 */     this.shopMapper.insert(shop);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getAllShopByParam(Long communityId, String shopName, Integer pagesize, Integer curPage)
/*     */   {
/* 293 */     Map paramMap = new HashMap();
/* 294 */     paramMap.put("communityId", communityId);
/* 295 */     paramMap.put("shopName", shopName);
/* 296 */     paramMap.put("pagesize", pagesize);
/* 297 */     paramMap.put("start", curPage);
/* 298 */     List list = this.shopMapper.getAllShopByParam(paramMap);
/* 299 */     Long count = this.shopMapper.getShopCountByParam(paramMap);
/* 300 */     paramMap = new HashMap();
/* 301 */     paramMap.put("list", list);
/* 302 */     paramMap.put("count", count);
/* 303 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public void delete(Long shopId)
/*     */   {
/* 312 */     this.shopMapper.delete(shopId);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.ShopService
 * JD-Core Version:    0.6.0
 */