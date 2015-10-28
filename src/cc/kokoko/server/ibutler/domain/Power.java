/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ public class Power
/*     */ {
/*     */   private Long powerId;
/*     */   private Long parent;
/*     */   private String powerName;
/*     */   private String powerUrl;
/*     */   private Long powerLevel;
/*     */   private String rel;
/*     */   private String target;
/*     */   private String title;
/*     */   private Long width;
/*     */   private Long height;
/*     */   private String attribute;
/*     */   private Long sort;
/*     */   private String parentName;
/*     */ 
/*     */   public Long getPowerId()
/*     */   {
/*  61 */     return this.powerId;
/*     */   }
/*     */ 
/*     */   public void setPowerId(Long powerId) {
/*  65 */     this.powerId = powerId;
/*     */   }
/*     */ 
/*     */   public Long getParent() {
/*  69 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(Long parent) {
/*  73 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public String getPowerName() {
/*  77 */     return this.powerName;
/*     */   }
/*     */ 
/*     */   public void setPowerName(String powerName) {
/*  81 */     this.powerName = powerName;
/*     */   }
/*     */ 
/*     */   public String getPowerUrl() {
/*  85 */     return this.powerUrl;
/*     */   }
/*     */ 
/*     */   public void setPowerUrl(String powerUrl) {
/*  89 */     this.powerUrl = powerUrl;
/*     */   }
/*     */ 
/*     */   public Long getPowerLevel() {
/*  93 */     return this.powerLevel;
/*     */   }
/*     */ 
/*     */   public void setPowerLevel(Long powerLevel) {
/*  97 */     this.powerLevel = powerLevel;
/*     */   }
/*     */ 
/*     */   public String getRel() {
/* 101 */     return this.rel;
/*     */   }
/*     */ 
/*     */   public void setRel(String rel) {
/* 105 */     this.rel = rel;
/*     */   }
/*     */ 
/*     */   public String getTarget() {
/* 109 */     return this.target;
/*     */   }
/*     */ 
/*     */   public void setTarget(String target) {
/* 113 */     this.target = target;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/* 117 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 121 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public Long getWidth() {
/* 125 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void setWidth(Long width) {
/* 129 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public Long getHeight() {
/* 133 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void setHeight(Long height) {
/* 137 */     this.height = height;
/*     */   }
/*     */ 
/*     */   public String getAttribute() {
/* 141 */     return this.attribute;
/*     */   }
/*     */ 
/*     */   public void setAttribute(String attribute) {
/* 145 */     this.attribute = attribute;
/*     */   }
/*     */ 
/*     */   public Long getSort() {
/* 149 */     return this.sort;
/*     */   }
/*     */ 
/*     */   public void setSort(Long sort) {
/* 153 */     this.sort = sort;
/*     */   }
/*     */ 
/*     */   public String getParentName() {
/* 157 */     return this.parentName;
/*     */   }
/*     */ 
/*     */   public void setParentName(String parentName) {
/* 161 */     this.parentName = parentName;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 166 */     return "Power{powerId=" + this.powerId + ", parent=" + this.parent + ", powerName='" + this.powerName + '\'' + ", powerUrl='" + this.powerUrl + '\'' + ", powerLevel=" + this.powerLevel + ", rel='" + this.rel + '\'' + ", target='" + this.target + '\'' + ", title='" + this.title + '\'' + ", width=" + this.width + ", height=" + this.height + ", attribute='" + this.attribute + '\'' + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Power
 * JD-Core Version:    0.6.0
 */