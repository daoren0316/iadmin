/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import cc.kokoko.server.commons.util.StringUtil;
/*     */ import cc.kokoko.server.ibutler.domain.Role;
/*     */ import cc.kokoko.server.ibutler.domain.RolePower;
/*     */ import cc.kokoko.server.ibutler.persistence.RoleMapper;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Propagation;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("roleService")
/*     */ public class RoleService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private RoleMapper roleMapper;
/*     */ 
/*     */   public List<Role> getAllRole()
/*     */   {
/*  25 */     return this.roleMapper.getAllRole();
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getRoleRecord(String roleName, Integer pagesize, Integer curPage)
/*     */   {
/*  37 */     Map paramMap = new HashMap();
/*  38 */     paramMap.put("roleName", roleName);
/*  39 */     paramMap.put("pagesize", pagesize);
/*  40 */     paramMap.put("start", curPage);
/*  41 */     List list = this.roleMapper.getRoleRecord(paramMap);
/*  42 */     Long count = this.roleMapper.getRoleCount(paramMap);
/*  43 */     paramMap = new HashMap();
/*  44 */     paramMap.put("list", list);
/*  45 */     paramMap.put("count", count);
/*  46 */     return paramMap;
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insert(Role role, String[] power)
/*     */   {
/*  58 */     role.setFlag(Long.valueOf(0L));
/*  59 */     this.roleMapper.insertRole(role);
/*     */ 
/*  61 */     if ((power != null) && (power.length > 0))
/*     */     {
/*  63 */       for (String powerId : power)
/*     */       {
/*  65 */         RolePower rolePower = new RolePower();
/*     */ 
/*  67 */         rolePower.setRoleId(role.getRoleId());
/*  68 */         rolePower.setPowerId(Long.valueOf(powerId));
/*     */ 
/*  70 */         this.roleMapper.insertRolePower(rolePower);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void update(Role role, String[] power)
/*     */   {
/*  84 */     this.roleMapper.updateRole(role);
/*     */ 
/*  86 */     List<RolePower> rolePowerList = this.roleMapper.getRolePowerByRoleId(role.getRoleId());
/*  87 */     Object[] hasPower = null;
/*     */     int i;
/*  89 */     if (rolePowerList.size() > 0) {
/*  90 */       hasPower = new Object[rolePowerList.size()];
/*  91 */       i = 0;
/*  92 */       for (RolePower rolePower : rolePowerList) {
/*  93 */         hasPower[i] = rolePower.getPowerId();
/*  94 */         i++;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  99 */     String[] repeatData = repeatNotDoubleObj(hasPower, power);
/*     */ 
/* 101 */     String delete = repeatData[1];
/* 102 */     String[] deleteObj = !StringUtil.isEmpty(delete) ? delete.split(",") : null;
/*     */ 
/* 104 */     if ((deleteObj != null) && (deleteObj.length > 0)) {
/* 105 */       for (String powerId : deleteObj) {
/* 106 */         Map paramMap = new HashMap();
/* 107 */         paramMap.put("roleId", role.getRoleId());
/* 108 */         paramMap.put("powerId", powerId);
/*     */ 
/* 110 */         this.roleMapper.deleteRolePower(paramMap);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 115 */     String insert = repeatData[0];
/* 116 */     String[] insertObj = !StringUtil.isEmpty(insert) ? insert.split(",") : null;
/*     */ 
/* 118 */     if ((insertObj != null) && (insertObj.length > 0))
/*     */     {
/* 120 */       for (String powerId : insertObj)
/*     */       {
/* 122 */         RolePower rolePower = new RolePower();
/*     */ 
/* 124 */         rolePower.setRoleId(role.getRoleId());
/* 125 */         rolePower.setPowerId(Long.valueOf(powerId));
/*     */ 
/* 127 */         this.roleMapper.insertRolePower(rolePower);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Role getRoleById(Long roleId)
/*     */   {
/* 139 */     return this.roleMapper.getRoleById(roleId);
/*     */   }
/*     */ 
/*     */   public Boolean checkRole(String roleName, Long roleId)
/*     */   {
/* 150 */     Boolean bool = Boolean.valueOf(false);
/* 151 */     Map paramMap = new HashMap();
/* 152 */     paramMap.put("roleName", roleName);
/* 153 */     paramMap.put("roleId", roleId);
/* 154 */     Role role = this.roleMapper.checkRole(paramMap);
/* 155 */     if (role == null)
/* 156 */       bool = Boolean.valueOf(true);
/* 157 */     return bool;
/*     */   }
/*     */ 
/*     */   public String[] repeatNotDoubleObj(Object[] objOne, Object[] objTwo)
/*     */   {
/* 169 */     String[] reMsg = new String[2];
/* 170 */     String insert = "";
/* 171 */     String delete = "";
/*     */ 
/* 174 */     Map repeat = new HashMap();
/*     */ 
/* 176 */     if ((objOne != null) && (objOne.length > 0)) {
/* 177 */       for (int i = 0; i < objOne.length; i++) {
/* 178 */         repeat.put("key_" + objOne[i], objOne[i]);
/*     */       }
/*     */     }
/* 181 */     Map repeat_s = new HashMap();
/*     */ 
/* 184 */     if ((objTwo != null) && (objTwo.length > 0)) {
/* 185 */       for (int k = 0; k < objTwo.length; k++) {
/* 186 */         if (!repeat.containsKey("key_" + objTwo[k])) {
/* 187 */           repeat_s.put("key_" + objTwo[k], objTwo[k]);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 192 */     Set some = repeat_s.keySet();
/*     */ 
/* 194 */     Iterator it = some.iterator();
/*     */ 
/* 196 */     while (it.hasNext()) {
/* 197 */       insert = insert + "," + repeat_s.get(it.next());
/*     */     }
/*     */ 
/* 202 */     repeat = new HashMap();
/*     */ 
/* 204 */     if ((objTwo != null) && (objTwo.length > 0)) {
/* 205 */       for (int i = 0; i < objTwo.length; i++) {
/* 206 */         repeat.put("key_" + objTwo[i], objTwo[i]);
/*     */       }
/*     */     }
/* 209 */     repeat_s = new HashMap();
/*     */ 
/* 212 */     if ((objOne != null) && (objOne.length > 0)) {
/* 213 */       for (int k = 0; k < objOne.length; k++) {
/* 214 */         if (!repeat.containsKey("key_" + objOne[k])) {
/* 215 */           repeat_s.put("key_" + objOne[k], objOne[k]);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 220 */     some = repeat_s.keySet();
/*     */ 
/* 222 */     it = some.iterator();
/*     */ 
/* 224 */     while (it.hasNext()) {
/* 225 */       delete = delete + "," + repeat_s.get(it.next());
/*     */     }
/* 227 */     reMsg[0] = insert;
/* 228 */     reMsg[1] = delete;
/* 229 */     return reMsg;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.RoleService
 * JD-Core Version:    0.6.0
 */