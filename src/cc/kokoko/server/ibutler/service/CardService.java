/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.util.CipherUtil;
import cc.kokoko.server.commons.util.HttpClientUtils;
import cc.kokoko.server.commons.util.MD5Util;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Card;
import cc.kokoko.server.ibutler.domain.MoneyAccount;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.domain.dto.UserDTO;
import cc.kokoko.server.ibutler.persistence.CardMapper;
import cc.kokoko.server.ibutler.persistence.CommunityMapper;
import cc.kokoko.server.ibutler.persistence.MoneyLogMapper;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
/*     */ 
/*     */ @Service("cardService")
/*     */ public class CardService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private CardMapper cardMapper;
/*     */ 
/*     */   @Autowired
/*     */   private MoneyLogMapper moneyLogMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @Autowired
/*     */   private CommunityMapper communityMapper;
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void grantCard(String phoneNumber, Long communityId, Long houseId, String cardNo, Long operatorId, String operatorName)
/*     */     throws Exception
/*     */   {
/*  52 */     UserDTO userDTO = this.userService.getHouseByPhoneNumber(phoneNumber);
/*     */ 
/*  54 */     Long uid = Long.valueOf(0L);
/*     */ 
/*  56 */     if (userDTO == null)
/*     */     {
/*  58 */       User user = new User();
/*  59 */       user.setUsername("");
/*  60 */       user.setPhoneNumber(phoneNumber);
/*     */ 
/*  62 */       user.setPassword(MD5Util.getMD5String(phoneNumber.substring(phoneNumber.length() - 6, phoneNumber.length())));
/*  63 */       user.setToken(MD5Util.getMD5String(phoneNumber));
/*  64 */       user.setNickname("");
/*  65 */       user.setHouseId(houseId);
/*  66 */       user.setUserType(new Byte("0"));
/*  67 */       user.setAppStatus(AppConst.UserStatus.APP_NO_DOWN);
/*  68 */       user.setCardStatus(AppConst.UserStatus.CARD_HAS);
/*  69 */       user.setCommunityId(communityId);
/*  70 */       user.setPublicAddress(this.communityMapper.getUserAddrByHouseId(houseId));
/*  71 */       this.userService.addUser(user);
/*     */ 
/*  73 */       uid = user.getUid();
/*     */     } else {
/*  75 */       uid = userDTO.getUid();
/*     */     }
/*     */ 
/*  78 */     MoneyAccount moneyAccount = this.moneyLogMapper.getMoneyAccount(houseId);
/*  79 */     if (moneyAccount == null) {
/*  80 */       moneyAccount = new MoneyAccount();
/*  81 */       moneyAccount.setBalance(new BigDecimal(0));
/*  82 */       moneyAccount.setHouseId(houseId);
/*  83 */       moneyAccount.setOwnerUid(uid);
/*  84 */       moneyAccount.setRoomNo("");
/*  85 */       moneyAccount.setCiphertext(CipherUtil.encodePassword("111111"));
/*  86 */       this.moneyLogMapper.createMoneyAccount(moneyAccount);
/*     */     }
/*     */ 
/*  90 */     String password = "111111";
/*  91 */     if (!StringUtil.isEmpty(moneyAccount.getCiphertext()))
/*     */     {
/*  93 */       password = CipherUtil.decodePassword(moneyAccount.getCiphertext());
/*     */     }
/*     */ 
/*  97 */     Card card = new Card();
/*  98 */     card.setUid(uid);
/*  99 */     card.setCardNo(cardNo);
/* 100 */     card.setHouseId(houseId);
/* 101 */     card.setStatus(new Byte("0"));
/* 102 */     card.setOperatorId(operatorId);
/* 103 */     card.setOperatorName(operatorName);
/* 104 */     this.cardMapper.insert(card);
/*     */ 
/* 107 */     String resultMsg = HttpClientUtils.createCard(uid, cardNo, password);
/*     */ 
/* 109 */     Map map = ObjectUtil.getObjectMapFromJson(resultMsg);
/* 110 */     String code = map.get("code").toString();
/* 111 */     if (!code.equals("0"))
/* 112 */       throw new Exception("CCard失败 " + map.get("errorMsg"));
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getCardByCardNo(String cardNo)
/*     */   {
/* 123 */     return this.cardMapper.getCardByCardNo(cardNo);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void cancelCard(Long uid, String cardNo)
/*     */     throws Exception
/*     */   {
/* 135 */     Map paramMap = new HashMap();
/* 136 */     paramMap.put("uid", uid);
/* 137 */     paramMap.put("cardNo", cardNo);
/*     */ 
/* 139 */     this.cardMapper.insertCardDel(paramMap);
/*     */ 
/* 141 */     this.cardMapper.delete(paramMap);
/*     */ 
/* 144 */     String resultMsg = HttpClientUtils.delRdCard(cardNo);
/*     */ 
/* 146 */     Map map = ObjectUtil.getObjectMapFromJson(resultMsg);
/* 147 */     String code = map.get("code").toString();
/* 148 */     if (!code.equals("0"))
/* 149 */       throw new Exception("接口调用失败 " + map.get("errorMsg"));
/*     */   }
/*     */ 
/*     */   public Card getCardByPhoneNumber(String phoneNumber)
/*     */   {
/* 159 */     return this.cardMapper.getCardByPhoneNumber(phoneNumber);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getAllCardRecord(Long communityId, String cardNo, Long houseId, String phoneNumber, Integer pagesize, Integer curPage)
/*     */   {
/* 172 */     Map paramMap = new HashMap();
/* 173 */     paramMap.put("communityId", communityId);
/* 174 */     paramMap.put("cardNo", cardNo);
/* 175 */     paramMap.put("houseId", houseId);
/* 176 */     paramMap.put("phoneNumber", phoneNumber);
/* 177 */     paramMap.put("pagesize", pagesize);
/* 178 */     paramMap.put("start", curPage);
/* 179 */     List list = this.cardMapper.getCardRecordByParam(paramMap);
/* 180 */     Long count = this.cardMapper.getCardCountByParam(paramMap);
/* 181 */     paramMap = new HashMap();
/* 182 */     paramMap.put("list", list);
/* 183 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 184 */     return paramMap;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.CardService
 * JD-Core Version:    0.6.0
 */