/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import cc.kokoko.server.commons.util.StringUtil;
/*     */ import cc.kokoko.server.ibutler.domain.Power;
/*     */ import cc.kokoko.server.ibutler.domain.RolePower;
/*     */ import cc.kokoko.server.ibutler.persistence.PowerMapper;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Propagation;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("powerService")
/*     */ public class PowerService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private PowerMapper powerMapper;
/*     */ 
/*     */   public Map<String, Object> getPowerRecord(String powerName, Integer pagesize, Integer curPage)
/*     */   {
/*  31 */     Map paramMap = new HashMap();
/*  32 */     paramMap.put("powerName", powerName);
/*  33 */     paramMap.put("pagesize", pagesize);
/*  34 */     paramMap.put("start", curPage);
/*  35 */     List list = this.powerMapper.getPowerRecord(paramMap);
/*  36 */     Long count = this.powerMapper.getPowerCount(paramMap);
/*  37 */     paramMap = new HashMap();
/*  38 */     paramMap.put("list", list);
/*  39 */     paramMap.put("count", count);
/*  40 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public void insert(Power power)
/*     */   {
/*  49 */     this.powerMapper.insert(power);
/*     */   }
/*     */ 
/*     */   public void update(Power power)
/*     */   {
/*  58 */     this.powerMapper.update(power);
/*     */   }
/*     */ 
/*     */   public Power getPowerById(Long powerId)
/*     */   {
/*  68 */     return this.powerMapper.getPowerById(powerId);
/*     */   }
/*     */ 
/*     */   public List<Power> getAllPower()
/*     */   {
/*  77 */     Map paramMap = new HashMap();
/*  78 */     paramMap.put("powerId", null);
/*  79 */     return this.powerMapper.getAllPower(paramMap);
/*     */   }
/*     */ 
/*     */   public String buildTree(boolean isTop, List<Power> powerList)
/*     */   {
/*  88 */     StringBuffer treeCode = new StringBuffer();
/*  89 */     String param = isTop ? "class=\"tree\"" : "";
/*  90 */     if (powerList.size() > 0) {
/*  91 */       treeCode.append(new StringBuilder().append("<ul ").append(param).append(">").toString());
/*  92 */       for (Power power : powerList) {
/*  93 */         treeCode.append("<li>");
/*  94 */         treeCode.append(new StringBuilder().append("<a href=\"javascript:void(0);\" onclick=\"selectedEle(").append(power.getPowerId()).append(",'").append(power.getPowerName()).append("',").append(power.getPowerLevel()).append(");\">").append(power.getPowerName()).append("</a>").toString());
/*     */ 
/*  97 */         Map paramMap = new HashMap();
/*  98 */         paramMap.put("powerId", power.getPowerId());
/*  99 */         List childPower = this.powerMapper.getAllPower(paramMap);
/* 100 */         treeCode.append(buildTree(false, childPower));
/* 101 */         treeCode.append("</li>");
/*     */       }
/* 103 */       treeCode.append("</ul>");
/*     */     }
/* 105 */     return treeCode.toString();
/*     */   }
/*     */ 
/*     */   public String buildTree(boolean isTop, List<Power> powerList, Long roleId)
/*     */   {
/* 116 */     StringBuffer sb = new StringBuffer();
/*     */ 
/* 118 */     String param = isTop ? "class=\"tree treeFolder treeCheck expand\"" : "";
/* 119 */     if (powerList.size() > 0) {
/* 120 */       sb.append(new StringBuilder().append("<ul ").append(param).append(">").toString());
/* 121 */       for (Power power : powerList)
/*     */       {
/* 123 */         boolean isInclude = roleId.longValue() > 0L ? isInclude(power.getPowerId(), roleId) : false;
/* 124 */         String isChecked = isInclude ? "checked=\"true\"" : "";
/* 125 */         sb.append(new StringBuilder().append("<li><a tname=\"power\" tvalue=\"").append(power.getPowerId()).append("\" ").append(isChecked).append(">").append(power.getPowerName()).append("</a>").toString());
/*     */ 
/* 127 */         Map paramMap = new HashMap();
/* 128 */         paramMap.put("powerId", power.getPowerId());
/* 129 */         List childPower = this.powerMapper.getAllPower(paramMap);
/* 130 */         sb.append(buildTree(false, childPower, roleId));
/* 131 */         sb.append("</li>");
/*     */       }
/* 133 */       sb.append("</ul>");
/*     */     }
/* 135 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private boolean isInclude(Long powerId, Long roleId)
/*     */   {
/* 146 */     boolean bool = false;
/*     */ 
/* 148 */     Map paramMap = new HashMap();
/* 149 */     paramMap.put("powerId", powerId);
/* 150 */     paramMap.put("roleId", roleId);
/* 151 */     RolePower rolePower = this.powerMapper.getRolePowerByParam(paramMap);
/* 152 */     if (rolePower != null)
/* 153 */       bool = true;
/* 154 */     return bool;
/*     */   }
/*     */ 
/*     */   public String loadTodoPower(String path, String term, Long uid)
/*     */   {
/* 164 */     Map paramMap = new HashMap();
/* 165 */     paramMap.put("term", term);
/* 166 */     paramMap.put("uid", uid);
/*     */ 
/* 168 */     List<Power> powerList = this.powerMapper.loadTodoPower(paramMap);
/* 169 */     StringBuffer sb = new StringBuffer();
/* 170 */     if (powerList.size() > 0) {
/* 171 */       for (Power power : powerList)
/*     */       {
/* 173 */         String target = power.getTarget();
/* 174 */         String url = new StringBuilder().append(path).append("/").append(power.getPowerUrl()).toString();
/* 175 */         String name = power.getPowerName();
/* 176 */         String icon = "icon";
/*     */ 
/* 178 */         if ((name.contains("添加")) || (name.contains("发布")))
/* 179 */           icon = "add";
/* 180 */         else if ((name.contains("修改")) || (name.contains("编辑")) || (name.contains("变更")))
/* 181 */           icon = "edit";
/* 182 */         else if (name.contains("删除")) {
/* 183 */           icon = "delete";
/*     */         }
/*     */ 
/* 186 */         if ("navTab".equals(target)) {
/* 187 */           sb.append(new StringBuilder().append("<li><a class=\"").append(icon).append("\" href=\"").append(url).append("\" rel=\"").append(power.getRel()).append("\" target=\"").append(target).append("\"><span>").append(name).append("</span></a></li>").toString());
/*     */         }
/* 189 */         else if (("ajaxTodo".equals(target)) || ("selectedTodo".equals(target)) || ("dwzExport".equals(target))) {
/* 190 */           sb.append(new StringBuilder().append("<li><a class=\"").append(icon).append("\" href=\"").append(url).append("\" rel=\"").append(power.getRel()).append("\" target=\"").append(target).append("\" title=\"").append(power.getTitle()).append("\"><span>").append(name).append("</span></a></li>").toString());
/*     */         }
/* 192 */         else if ("dialog".equals(target)) {
/* 193 */           String attr = power.getAttribute();
/*     */ 
/* 195 */           String[] param = !StringUtil.isEmpty(attr) ? attr.split(",") : null;
/* 196 */           Long mask = Long.valueOf(param[2]);
/* 197 */           Long drawable = Long.valueOf(param[3]);
/* 198 */           Long resizabl = Long.valueOf(param[4]);
/*     */ 
/* 200 */           String maskStr = new StringBuilder().append("mask=").append(mask.longValue() == 1L ? "\"true\"" : "\"false\"").toString();
/* 201 */           String drawableStr = new StringBuilder().append("drawable=").append(drawable.longValue() == 1L ? "\"true\"" : "\"false\"").toString();
/* 202 */           String resizablStr = new StringBuilder().append("resizabl=").append(resizabl.longValue() == 1L ? "\"true\"" : "\"false\"").toString();
/*     */ 
/* 204 */           sb.append(new StringBuilder().append("<li><a class=\"").append(icon).append("\" href=\"").append(url).append("\" rel=\"").append(power.getRel()).append("\"target=\"").append(target).append("\" width=\"").append(power.getWidth()).append("\" height=\"").append(power.getHeight()).append("\" ").append(maskStr).append(" ").append(drawableStr).append(" ").append(resizablStr).append("><span>").append(name).append("</span></a></li>").toString());
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 210 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void delete(Long powerId)
/*     */   {
/* 221 */     this.powerMapper.delete(powerId);
/*     */ 
/* 223 */     this.powerMapper.deleteRolePower(powerId);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.PowerService
 * JD-Core Version:    0.6.0
 */