/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.kokoko.server.commons.util.MaxMD5Util;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Operator;
import cc.kokoko.server.ibutler.domain.Site;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.persistence.OperatorMapper;
import cc.kokoko.server.ibutler.persistence.SiteMapper;
/*     */ 
/*     */ @Service("operatorService")
/*     */ public class OperatorService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private OperatorMapper operatorMapper;
/*     */ 
/*     */   @Autowired
/*     */   private SiteMapper siteMapper;
/*     */ 
/*     */   public Map<String, Object> getOperatorRecord(Long communityId, String phoneNumber, String nickname, Integer pagesize, Integer curPage)
/*     */   {
/*  37 */     Map paramMap = new HashMap();
/*  38 */     paramMap.put("pagesize", pagesize);
/*  39 */     paramMap.put("start", curPage);
/*  40 */     paramMap.put("phoneNumber", phoneNumber);
/*  41 */     paramMap.put("nickname", nickname);
/*  42 */     paramMap.put("communityId", communityId);
/*     */ 
/*  44 */     List list = this.operatorMapper.getOperatorRecord(paramMap);
/*  45 */     Long count = this.operatorMapper.getOperatorCount(paramMap);
/*  46 */     paramMap = new HashMap();
/*  47 */     paramMap.put("list", list);
/*  48 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/*  49 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public void insert(Operator operator)
/*     */     throws Exception
/*     */   {
/*  58 */     String token = MaxMD5Util.createToken(operator.getNickname());
/*  59 */     String passwordMd5 = MaxMD5Util.toMD5(operator.getPassword());
/*     */ 
/*  61 */     Site site = this.siteMapper.getSiteDetailByCommunityId(operator.getCommunityId());
/*  62 */     if (site == null)
/*  63 */       throw new Exception("服务小站不存在");
/*  64 */     operator.setUid(site.getSiteId());
/*  65 */     operator.setPassword(passwordMd5);
/*  66 */     operator.setToken(token);
/*  67 */     operator.setUserType(new Byte("5"));
/*  68 */     operator.setUserStatus(new Byte("0"));
/*  69 */     operator.setUserFlag(new Byte("1"));
/*  70 */     this.operatorMapper.insert(operator);
/*     */   }
/*     */ 
/*     */   public Operator getOperatorById(Long id)
/*     */   {
/*  80 */     return this.operatorMapper.getOperatorById(id);
/*     */   }
/*     */ 
/*     */   public void update(Operator operator)
/*     */     throws Exception
/*     */   {
/*  90 */     Operator op = getOperatorById(operator.getId());
/*     */ 
/*  92 */     if (operator.getPassword().contains("111111"))
/*  93 */       operator.setPassword(op.getPassword());
/*     */     else {
/*  95 */       operator.setPassword(MaxMD5Util.toMD5(operator.getPassword()));
/*     */     }
/*  97 */     long uid = op.getUid().longValue();
/*  98 */     if (op.getUserType().equals(AppConst.UserType.OPERATOR))
/*     */     {
/* 100 */       Site site = this.siteMapper.getSiteDetailByCommunityId(operator.getCommunityId());
/* 101 */       if (site == null)
/* 102 */         throw new Exception("服务小站不存在");
/* 103 */       uid = site.getSiteId().longValue();
/*     */     }
/* 105 */     operator.setUid(Long.valueOf(uid));
/* 106 */     operator.setUserType(op.getUserType());
/* 107 */     operator.setUserStatus(op.getUserStatus());
/* 108 */     this.operatorMapper.update(operator);
/*     */   }
/*     */ 
/*     */   public void delete(Long id)
/*     */   {
/* 117 */     this.operatorMapper.delete(id);
/*     */   }
/*     */ 
/*     */   public boolean checkOperatorByName(String username)
/*     */   {
/* 127 */     boolean bool = true;
/* 128 */     User user = this.operatorMapper.checkOperatorByName(username);
/* 129 */     if (user != null)
/* 130 */       bool = false;
/* 131 */     return bool;
/*     */   }
/*     */ 
/*     */   public Operator getOperatorByUnamePassword(String username, String password)
/*     */   {
/* 142 */     Map paramMap = new HashMap();
/* 143 */     paramMap.put("username", username);
/* 144 */     paramMap.put("password", password);
/* 145 */     return this.operatorMapper.getOperatorByUnamePassword(paramMap);
/*     */   }
/*     */ 
/*     */   public void updateOperator(Byte userStatus, Byte userFlag, Long id)
/*     */   {
/* 154 */     Map paramMap = new HashMap();
/* 155 */     paramMap.put("userStatus", userStatus);
/* 156 */     paramMap.put("userFlag", userFlag);
/* 157 */     paramMap.put("id", id);
/* 158 */     this.operatorMapper.updateOperator(paramMap);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.OperatorService
 * JD-Core Version:    0.6.0
 */