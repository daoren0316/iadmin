/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.UserRole;
import cc.kokoko.server.ibutler.domain.UserType;
import cc.kokoko.server.ibutler.persistence.UserTypeMapper;
/*     */ 
/*     */ @Service("userTypeService")
/*     */ public class UserTypeService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private UserTypeMapper userTypeMapper;
/*     */ 
/*     */   public Map<String, Object> getUserTypeRecord(Integer pagesize, Integer curPage)
/*     */   {
/*  28 */     Map paramMap = new HashMap();
/*  29 */     paramMap.put("pagesize", pagesize);
/*  30 */     paramMap.put("start", curPage);
/*  31 */     List list = this.userTypeMapper.getUserTypeRecord(paramMap);
/*  32 */     Long count = this.userTypeMapper.getUserTypeCount(paramMap);
/*     */ 
/*  34 */     count = Long.valueOf(count == null ? 0L : count.longValue());
/*  35 */     paramMap = new HashMap();
/*  36 */     paramMap.put("list", list);
/*  37 */     paramMap.put("count", count);
/*  38 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public UserType getUserTypeById(Long typeId)
/*     */   {
/*  48 */     Map paramMap = new HashMap();
/*  49 */     paramMap.put("typeId", typeId);
/*  50 */     return this.userTypeMapper.getUserTypeById(paramMap);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insert(UserType userType, Long[] role)
/*     */   {
/*  62 */     this.userTypeMapper.insert(userType);
/*     */ 
/*  64 */     if ((role != null) && (role.length > 0))
/*  65 */       for (Long roleId : role) {
/*  66 */         UserRole userRole = new UserRole();
/*  67 */         userRole.setRoleId(roleId);
/*  68 */         userRole.setTypeId(userType.getTypeId());
/*  69 */         this.userTypeMapper.insertUserRole(userRole);
/*     */       }
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void update(UserType userType, Long[] role)
/*     */   {
/*  83 */     this.userTypeMapper.update(userType);
/*     */ 
/*  85 */     Map paramMap = new HashMap();
/*  86 */     paramMap.put("typeId", userType.getTypeId());
/*  87 */     List<UserRole> userRoleList = this.userTypeMapper.getUserRoleByParam(paramMap);
/*  88 */     Object[] hasRole = null;
/*     */     int i;
/*  90 */     if (userRoleList.size() > 0) {
/*  91 */       hasRole = new Object[userRoleList.size()];
/*  92 */       i = 0;
/*  93 */       for (UserRole userRole : userRoleList) {
/*  94 */         hasRole[i] = userRole.getRoleId();
/*  95 */         i++;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 100 */     String[] repeatData = repeatNotDoubleObj(hasRole, role);
/*     */ 
/* 102 */     String delete = repeatData[1];
/* 103 */     String[] deleteObj = !StringUtil.isEmpty(delete) ? delete.split(",") : null;
/*     */ 
/* 105 */     if ((deleteObj != null) && (deleteObj.length > 0)) {
/* 106 */       for (String roleId : deleteObj) {
/* 107 */         paramMap = new HashMap();
/* 108 */         paramMap.put("roleId", roleId);
/* 109 */         paramMap.put("typeId", userType.getTypeId());
/*     */ 
/* 111 */         this.userTypeMapper.deleteUserRole(paramMap);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 116 */     String insert = repeatData[0];
/* 117 */     String[] insertObj = !StringUtil.isEmpty(insert) ? insert.split(",") : null;
/*     */ 
/* 119 */     if ((insertObj != null) && (insertObj.length > 0))
/* 120 */       for (String roleId : insertObj) {
/* 121 */         UserRole userRole = new UserRole();
/* 122 */         userRole.setRoleId(Long.valueOf(roleId));
/* 123 */         userRole.setTypeId(userType.getTypeId());
/* 124 */         this.userTypeMapper.insertUserRole(userRole);
/*     */       }
/*     */   }
/*     */ 
/*     */   public Boolean checkTypeName(String typeName, Long typeId)
/*     */   {
/* 138 */     Boolean bool = Boolean.valueOf(false);
/* 139 */     Map paramMap = new HashMap();
/* 140 */     paramMap.put("typeName", typeName);
/* 141 */     paramMap.put("typeId", typeId);
/* 142 */     UserType userType = this.userTypeMapper.checkTypeName(paramMap);
/* 143 */     if (userType == null)
/* 144 */       bool = Boolean.valueOf(true);
/* 145 */     return bool;
/*     */   }
/*     */ 
/*     */   public Boolean checkTypeFlag(Long typeFlag, Long typeId)
/*     */   {
/* 156 */     Boolean bool = Boolean.valueOf(false);
/* 157 */     Map paramMap = new HashMap();
/* 158 */     paramMap.put("typeFlag", typeFlag);
/* 159 */     paramMap.put("typeId", typeId);
/* 160 */     UserType userType = this.userTypeMapper.checkTypeFlag(paramMap);
/* 161 */     if (userType == null)
/* 162 */       bool = Boolean.valueOf(true);
/* 163 */     return bool;
/*     */   }
/*     */ 
/*     */   public List<UserRole> getUserRoleByParam(Long typeId)
/*     */   {
/* 173 */     Map paramMap = new HashMap();
/* 174 */     paramMap.put("typeId", typeId);
/* 175 */     return this.userTypeMapper.getUserRoleByParam(paramMap);
/*     */   }
/*     */ 
/*     */   public String[] repeatNotDoubleObj(Object[] objOne, Object[] objTwo)
/*     */   {
/* 187 */     String[] reMsg = new String[2];
/* 188 */     String insert = "";
/* 189 */     String delete = "";
/*     */ 
/* 192 */     Map repeat = new HashMap();
/*     */ 
/* 194 */     if ((objOne != null) && (objOne.length > 0)) {
/* 195 */       for (int i = 0; i < objOne.length; i++) {
/* 196 */         repeat.put("key_" + objOne[i], objOne[i]);
/*     */       }
/*     */     }
/* 199 */     Map repeat_s = new HashMap();
/*     */ 
/* 202 */     if ((objTwo != null) && (objTwo.length > 0)) {
/* 203 */       for (int k = 0; k < objTwo.length; k++) {
/* 204 */         if (!repeat.containsKey("key_" + objTwo[k])) {
/* 205 */           repeat_s.put("key_" + objTwo[k], objTwo[k]);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 210 */     Set some = repeat_s.keySet();
/*     */ 
/* 212 */     Iterator it = some.iterator();
/*     */ 
/* 214 */     while (it.hasNext()) {
/* 215 */       insert = insert + "," + repeat_s.get(it.next());
/*     */     }
/*     */ 
/* 220 */     repeat = new HashMap();
/*     */ 
/* 222 */     if ((objTwo != null) && (objTwo.length > 0)) {
/* 223 */       for (int i = 0; i < objTwo.length; i++) {
/* 224 */         repeat.put("key_" + objTwo[i], objTwo[i]);
/*     */       }
/*     */     }
/* 227 */     repeat_s = new HashMap();
/*     */ 
/* 230 */     if ((objOne != null) && (objOne.length > 0)) {
/* 231 */       for (int k = 0; k < objOne.length; k++) {
/* 232 */         if (!repeat.containsKey("key_" + objOne[k])) {
/* 233 */           repeat_s.put("key_" + objOne[k], objOne[k]);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 238 */     some = repeat_s.keySet();
/*     */ 
/* 240 */     it = some.iterator();
/*     */ 
/* 242 */     while (it.hasNext()) {
/* 243 */       delete = delete + "," + repeat_s.get(it.next());
/*     */     }
/* 245 */     reMsg[0] = insert;
/* 246 */     reMsg[1] = delete;
/* 247 */     return reMsg;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.UserTypeService
 * JD-Core Version:    0.6.0
 */