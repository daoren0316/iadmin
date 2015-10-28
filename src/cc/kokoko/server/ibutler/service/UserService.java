/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.util.MD5Util;
import cc.kokoko.server.commons.util.MaxMD5Util;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.Address;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.BDPushUser;
import cc.kokoko.server.ibutler.domain.Car;
import cc.kokoko.server.ibutler.domain.Favorite;
import cc.kokoko.server.ibutler.domain.Power;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.domain.UserDevice;
import cc.kokoko.server.ibutler.domain.dto.UserDTO;
import cc.kokoko.server.ibutler.domain.xmpp.OpenFireUser;
import cc.kokoko.server.ibutler.domain.xmpp.XMPPOnlineUser;
import cc.kokoko.server.ibutler.persistence.AddressMapper;
import cc.kokoko.server.ibutler.persistence.CarMapper;
import cc.kokoko.server.ibutler.persistence.MessageMapper;
import cc.kokoko.server.ibutler.persistence.OperatorMapper;
import cc.kokoko.server.ibutler.persistence.PowerMapper;
import cc.kokoko.server.ibutler.persistence.UserMapper;
import cc.kokoko.server.ibutler.service.util.UserUtil;
/*     */ 
/*     */ @Service("userService")
/*     */ public class UserService
/*     */ {
/*  27 */   private static Logger log = LoggerFactory.getLogger(UserService.class);
/*     */ 
/*     */   @Autowired
/*     */   private UserMapper userMapper;
/*     */ 
/*     */   @Autowired
/*     */   private MessageMapper messageMapper;
/*     */ 
/*     */   @Autowired
/*     */   private AddressMapper addressMapper;
/*     */ 
/*     */   @Autowired
/*     */   private CarMapper carMapper;
/*     */ 
/*     */   @Autowired
/*     */   private PowerMapper powerMapper;
/*     */ 
/*     */   @Autowired
/*     */   private OperatorMapper operatorMapper;
/*     */ 
/*  46 */   public void addUser(User user) { this.userMapper.addUser(user); }
/*     */ 
/*     */   public User regUser(String phoneNumber, String password)
/*     */   {
/*  50 */     User user = getUserByPhoneNumber(phoneNumber);
/*     */ 
/*  52 */     if (user == null) {
/*  53 */       user = UserUtil.createUser(phoneNumber, password);
/*  54 */       this.userMapper.registerUser(user);
/*     */     } else {
/*  56 */       user.setPassword(MD5Util.getMD5String(password));
/*  57 */       updateUser(user);
/*     */     }
/*  59 */     return user;
/*     */   }
/*     */ 
/*     */   public OpenFireUser getOpenFireUserByUid(String xmppUserPrefix, Long uid)
/*     */   {
/*  64 */     String username = xmppUserPrefix + "" + uid;
/*  65 */     return this.userMapper.getOpenFireUser(username);
/*     */   }
/*     */ 
/*     */   public void changePassword(User user) {
/*  69 */     this.userMapper.changePassword(user);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void changePassword(Long uid, String password, Long operatorId)
/*     */   {
/*  79 */     User user = new User();
/*  80 */     user.setUid(uid);
/*  81 */     password = MaxMD5Util.toMD5(password);
/*  82 */     user.setPassword(password);
/*  83 */     changePassword(user);
/*     */ 
/*  85 */     if ((operatorId != null) && (operatorId.longValue() > 0L)) {
/*  86 */       Map paramMap = new HashMap();
/*  87 */       paramMap.put("password", password);
/*  88 */       paramMap.put("id", operatorId);
/*  89 */       this.operatorMapper.updateOperator(paramMap);
/*     */     }
/*     */   }
/*     */ 
/*     */   public User getUserByUid(Long uid)
/*     */   {
/*  95 */     return this.userMapper.getUserByUid(uid);
/*     */   }
/*     */ 
/*     */   public User getUserByEmail(String email)
/*     */   {
/* 100 */     return this.userMapper.getUserByEmail(email);
/*     */   }
/*     */ 
/*     */   public User getUserByPhoneNumber(String phoneNumber) {
/* 104 */     return this.userMapper.getUserByPhoneNumber(phoneNumber);
/*     */   }
/*     */ 
/*     */   public User getUserByNickname(String nickname) {
/* 108 */     return this.userMapper.getUserByNickname(nickname);
/*     */   }
/*     */ 
/*     */   public User getUserByUsername(String username) {
/* 112 */     return this.userMapper.getUserByUsername(username);
/*     */   }
/*     */ 
/*     */   public User getUserByUnamePassword(String username, String password)
/*     */   {
/* 123 */     Map paramMap = new HashMap();
/* 124 */     paramMap.put("username", username);
/* 125 */     paramMap.put("password", password);
/* 126 */     return this.userMapper.getUserByUnamePassword(paramMap);
/*     */   }
/*     */ 
/*     */   public User getUserProfileByUid(Long uid, Long invokerUid)
/*     */   {
/* 136 */     User u = getUserByUid(uid);
/* 137 */     return UserUtil.getUserProfile(u, invokerUid);
/*     */   }
/*     */ 
/*     */   public List<User> getUserList(Integer pagesize, Integer curPage, Byte type)
/*     */   {
/* 142 */     Map paramMap = new HashMap();
/* 143 */     if ((pagesize == null) || (pagesize.intValue() < 1)) {
/* 144 */       pagesize = Integer.valueOf(20);
/*     */     }
/* 146 */     if ((curPage == null) || (curPage.intValue() < 1)) {
/* 147 */       curPage = Integer.valueOf(1);
/*     */     }
/* 149 */     int start = pagesize.intValue() * (curPage.intValue() - 1);
/* 150 */     paramMap.put("pagesize", pagesize);
/* 151 */     paramMap.put("start", Integer.valueOf(start));
/*     */ 
/* 153 */     List userList = new ArrayList();
/* 154 */     if (new Byte("1").equals(type))
/* 155 */       userList = this.userMapper.getUserListOrderByYesterday(paramMap);
/*     */     else {
/* 157 */       userList = this.userMapper.getUserList(paramMap);
/*     */     }
/* 159 */     return userList;
/*     */   }
/*     */ 
/*     */   public int registerUser(User user)
/*     */   {
/* 164 */     if ((!StringUtil.isEmpty(user.getEmail())) && 
/* 165 */       (getUserByEmail(user.getEmail()) != null)) {
/* 166 */       return -1010;
/*     */     }
/*     */ 
/* 169 */     int uid = this.userMapper.registerUser(user);
/* 170 */     return uid;
/*     */   }
/*     */ 
/*     */   public void createOpenFireUser(OpenFireUser xmppUser) {
/* 174 */     this.userMapper.createXMPPUser(xmppUser);
/*     */   }
/*     */ 
/*     */   public void updateUser(User user) {
/* 178 */     this.userMapper.updateUser(user);
/*     */   }
/*     */ 
/*     */   public OpenFireUser getOpenFireUser(Long uid) {
/* 182 */     String ofUsername = "ibutler_" + uid;
/* 183 */     return this.userMapper.getOpenFireUser(ofUsername);
/*     */   }
/*     */ 
/*     */   public List<XMPPOnlineUser> getXMPPOnlineUserList() {
/* 187 */     return this.userMapper.getXMPPOnlineUserList();
/*     */   }
/*     */ 
/*     */   public void addReport(Long uid, Long toUid, String content)
/*     */   {
/* 192 */     Map paramMap = new HashMap();
/* 193 */     paramMap.put("uid", uid);
/* 194 */     paramMap.put("toUid", toUid);
/* 195 */     paramMap.put("content", content);
/* 196 */     this.userMapper.addReport(paramMap);
/*     */   }
/*     */ 
/*     */   public void banDevice(Long uid)
/*     */   {
/* 205 */     List<UserDevice> udList = this.userMapper.getUserDeviceList(uid);
/* 206 */     for (UserDevice ud : udList)
/* 207 */       if ((ud != null) && (!StringUtil.isEmpty(ud.getDeviceId()))) {
/* 208 */         this.userMapper.deleteFromDeviceBlacklist(ud.getDeviceId());
/* 209 */         this.userMapper.addToDeviceBlacklist(ud.getDeviceId());
/*     */       }
/*     */   }
/*     */ 
/*     */   public boolean checkUsername(String username)
/*     */   {
/* 222 */     boolean ret = true;
/*     */ 
/* 224 */     return ret;
/*     */   }
/*     */ 
/*     */   public boolean checkUserByName(String username)
/*     */   {
/* 234 */     boolean bool = true;
/* 235 */     User user = this.userMapper.checkUserByName(username);
/* 236 */     if (user != null)
/* 237 */       bool = false;
/* 238 */     return bool;
/*     */   }
/*     */ 
/*     */   public void addUserDevice(Long uid, String deviceId) {
/* 242 */     if (StringUtil.isEmpty(deviceId)) {
/* 243 */       log.info("deviceId is null , uid=" + uid);
/* 244 */       return;
/*     */     }
/* 246 */     UserDevice ud = new UserDevice();
/* 247 */     ud.setDeviceId(deviceId);
/* 248 */     ud.setUid(uid);
/*     */ 
/* 250 */     UserDevice ud2 = this.userMapper.getUserDevice(ud);
/* 251 */     if (ud2 == null) {
/* 252 */       this.userMapper.addUserDevice(ud);
/* 253 */       log.debug("[addUserDevice] deviceId=" + deviceId + ", uid=" + uid);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addUserAddress(Long uid, String addressDetail, Long houseId)
/*     */   {
/* 259 */     if ((houseId == null) || (houseId.longValue() < 0L)) {
/* 260 */       houseId = Long.valueOf(0L);
/*     */     }
/* 262 */     Map paramMap = new HashMap();
/* 263 */     paramMap.put("uid", uid);
/* 264 */     paramMap.put("addressDetail", addressDetail);
/* 265 */     paramMap.put("houseId", houseId);
/* 266 */     this.addressMapper.createAddress(paramMap);
/*     */   }
/*     */ 
/*     */   public Address getUserAddressByHouseId(Long uid, Long houseId)
/*     */   {
/* 277 */     Map paramMap = new HashMap();
/* 278 */     paramMap.put("uid", uid);
/* 279 */     paramMap.put("houseId", houseId);
/* 280 */     return this.addressMapper.getUserAddressByHouseId(paramMap);
/*     */   }
/*     */ 
/*     */   public void updateUserAddress(Long uid, Long addressId, String addressDetail)
/*     */   {
/* 285 */     Address address = new Address();
/* 286 */     address.setUid(uid);
/* 287 */     address.setAddressId(addressId);
/* 288 */     address.setAddressDetail(addressDetail);
/* 289 */     this.addressMapper.updateAddress(address);
/*     */   }
/*     */ 
/*     */   public void deleteUserAddress(Long uid, Long addressId) {
/* 293 */     Address address = this.addressMapper.getAddressByAddressId(addressId);
/* 294 */     if ((address != null) && (uid.equals(address.getUid())))
/* 295 */       this.addressMapper.deleteAddress(addressId);
/*     */   }
/*     */ 
/*     */   public List<Address> getUserAddressList(Long uid)
/*     */   {
/* 300 */     return this.addressMapper.getAddressList(uid);
/*     */   }
/*     */ 
/*     */   public void addUserCar(Long uid, String carNumber)
/*     */   {
/* 305 */     Car car = new Car();
/* 306 */     car.setUid(uid);
/* 307 */     car.setCarNumber(carNumber);
/* 308 */     this.carMapper.createCar(car);
/*     */   }
/*     */ 
/*     */   public void editUserCar(Long uid, Long carId, String carNumber) {
/* 312 */     Car car = this.carMapper.getCarByCarId(carId);
/* 313 */     if ((car != null) && (uid.equals(car.getUid()))) {
/* 314 */       car.setCarNumber(carNumber);
/* 315 */       this.carMapper.updateCar(car);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void deleteUserCar(Long uid, Long carId) {
/* 320 */     Car car = this.carMapper.getCarByCarId(carId);
/* 321 */     if ((car != null) && (uid.equals(car.getUid())))
/* 322 */       this.carMapper.deleteCar(carId);
/*     */   }
/*     */ 
/*     */   public List<Car> getUserCarList(Long uid)
/*     */   {
/* 327 */     return this.carMapper.getCarList(uid);
/*     */   }
/*     */ 
/*     */   public List<Favorite> getFavoritelist(Long uid) {
/* 331 */     return this.userMapper.getFavoritelist(uid);
/*     */   }
/*     */ 
/*     */   public void updateUserToken(User user)
/*     */   {
/* 336 */     this.userMapper.updateUserToken(user);
/*     */   }
/*     */ 
/*     */   public void updateUserMoney(Long uid, double amount) {
/* 340 */     Map map = new HashMap();
/* 341 */     map.put("uid", uid);
/* 342 */     map.put("amount", Double.valueOf(amount));
/* 343 */     this.userMapper.updateUserMoney(map);
/*     */   }
/*     */ 
/*     */   public void updateBDPushUser(Long uid, String bdUserId, String bdChannelId) {
/* 347 */     BDPushUser bdPushUser = this.userMapper.getBDPushUserByUid(uid);
/* 348 */     if (bdPushUser == null) {
/* 349 */       bdPushUser = new BDPushUser();
/* 350 */       bdPushUser.setBdChannelId(bdChannelId);
/* 351 */       bdPushUser.setBdChannelId(bdChannelId);
/* 352 */       bdPushUser.setUid(uid);
/* 353 */       this.userMapper.createBDPushUser(bdPushUser);
/*     */     } else {
/* 355 */       bdPushUser.setBdChannelId(bdChannelId);
/* 356 */       bdPushUser.setBdChannelId(bdChannelId);
/* 357 */       this.userMapper.updateBDPushUser(bdPushUser);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addFavorite(Favorite favorite)
/*     */   {
/* 364 */     this.userMapper.addFavorite(favorite);
/*     */   }
/*     */ 
/*     */   public Favorite getFavorite(Favorite pramFavorite)
/*     */   {
/* 370 */     return this.userMapper.getFavorite(pramFavorite);
/*     */   }
/*     */ 
/*     */   public Boolean getFavStatus(Long referId, Long uid, Byte favType) {
/* 374 */     boolean ret = false;
/* 375 */     Favorite pramFavorite = new Favorite();
/* 376 */     pramFavorite.setFavType(favType);
/* 377 */     pramFavorite.setReferId(referId);
/* 378 */     pramFavorite.setUid(uid);
/* 379 */     Favorite fav = getFavorite(pramFavorite);
/* 380 */     if ((fav != null) && (fav.getFavoriteId().longValue() > 0L)) {
/* 381 */       ret = true;
/*     */     }
/* 383 */     return Boolean.valueOf(ret);
/*     */   }
/*     */ 
/*     */   public int getMessageCount(Long uid) {
/* 387 */     return this.messageMapper.getMessageCount(uid).intValue();
/*     */   }
/*     */ 
/*     */   public int getUserScore(Long uid) {
/* 391 */     return this.userMapper.getUserScore(uid).intValue();
/*     */   }
/*     */ 
/*     */   public Double getUserMoney(Long uid)
/*     */   {
/* 400 */     return this.userMapper.getUserMoney(uid);
/*     */   }
/*     */ 
/*     */   public void delFavorite(Long uid, Long referId, Byte favType) {
/* 404 */     Favorite pramFavorite = new Favorite();
/* 405 */     pramFavorite.setFavType(favType);
/* 406 */     pramFavorite.setReferId(referId);
/* 407 */     pramFavorite.setUid(uid);
/* 408 */     this.userMapper.delFavorite(pramFavorite);
/*     */   }
/*     */ 
/*     */   public Byte getUserType(Long uid) {
/* 412 */     return this.userMapper.getUserType(uid);
/*     */   }
/*     */ 
/*     */   public String getPhoneNumberByUid(Long uid) {
/* 416 */     String callTo = null;
/* 417 */     Byte uType = getUserType(uid);
/* 418 */     if (AppConst.UserType.COMMON.equals(uType))
/*     */     {
/* 420 */       return null;
/*     */     }
/* 422 */     if (AppConst.UserType.SHOP.equals(uType))
/* 423 */       callTo = this.userMapper.getShopPhoneNumber(uid);
/*     */     else {
/* 425 */       callTo = this.userMapper.getUserPhoneNumber(uid);
/*     */     }
/*     */ 
/* 429 */     return callTo;
/*     */   }
/*     */ 
/*     */   public Long getUidByRdCardNo(String cardNo) {
/* 433 */     return this.userMapper.getUidByRdCardNo(cardNo);
/*     */   }
/*     */ 
/*     */   public User getUserByRdCardNo(String cardNo)
/*     */   {
/* 443 */     return this.userMapper.getUserByRdCardNo(cardNo);
/*     */   }
/*     */ 
/*     */   public List<Power> getAllLeftPower(Long userId)
/*     */   {
/* 452 */     Map paramMap = new HashMap();
/* 453 */     paramMap.put("userId", userId);
/* 454 */     return this.powerMapper.getAllLeftPower(paramMap);
/*     */   }
/*     */ 
/*     */   public List<User> getUserByHouseId(Long houseId)
/*     */   {
/* 464 */     return this.userMapper.getUserByHouseId(houseId);
/*     */   }
/*     */ 
/*     */   public User getSiteUserByCommunityId(Long communityId)
/*     */   {
/* 474 */     return this.userMapper.getSiteUserByCommunityId(communityId);
/*     */   }
/*     */ 
/*     */   public User getOwnerUserByHouseId(Long houseId)
/*     */   {
/* 484 */     return this.userMapper.getOwnerUserByHouseId(houseId);
/*     */   }
/*     */ 
/*     */   public UserDTO getHouseByPhoneNumber(String phoneNumber)
/*     */   {
/* 494 */     Map paramMap = new HashMap();
/* 495 */     paramMap.put("phoneNumber", phoneNumber);
/* 496 */     return this.userMapper.getHouseByPhoneNumber(paramMap);
/*     */   }
/*     */ 
/*     */   public List<User> getUserByType(Long userType)
/*     */   {
/* 506 */     return this.userMapper.getUserByType(userType);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getHomeOwnerRecord(Long communityId, String phoneNumber, Long status, Long houseId, Integer pagesize, Integer curPage)
/*     */   {
/* 520 */     Map paramMap = new HashMap();
/* 521 */     paramMap.put("userType", Integer.valueOf(0));
/* 522 */     paramMap.put("communityId", communityId);
/* 523 */     paramMap.put("phoneNumber", phoneNumber);
/* 524 */     paramMap.put("status", status);
/* 525 */     paramMap.put("houseId", houseId);
/* 526 */     paramMap.put("pagesize", pagesize);
/* 527 */     paramMap.put("start", curPage);
/* 528 */     List list = this.userMapper.getOwnerRecordByParam(paramMap);
/* 529 */     Long count = this.userMapper.getOwnerCountByParam(paramMap);
/* 530 */     paramMap = new HashMap();
/* 531 */     paramMap.put("list", list);
/* 532 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 533 */     return paramMap;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.UserService
 * JD-Core Version:    0.6.0
 */