/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Site
/*     */ {
/*     */   private Long siteId;
/*     */   private String siteName;
/*     */   private String logoUrl;
/*     */   private Date createdTime;
/*     */   private String siteDesc;
/*     */   private String signature;
/*     */   private String siteAddress;
/*     */   private Long communityId;
/*     */ 
/*     */   public Long getSiteId()
/*     */   {
/*  47 */     return this.siteId;
/*     */   }
/*     */ 
/*     */   public void setSiteId(Long siteId) {
/*  51 */     this.siteId = siteId;
/*     */   }
/*     */ 
/*     */   public String getSiteName() {
/*  55 */     return this.siteName;
/*     */   }
/*     */ 
/*     */   public void setSiteName(String siteName) {
/*  59 */     this.siteName = siteName;
/*     */   }
/*     */ 
/*     */   public String getLogoUrl() {
/*  63 */     return this.logoUrl;
/*     */   }
/*     */ 
/*     */   public void setLogoUrl(String logoUrl) {
/*  67 */     this.logoUrl = logoUrl;
/*     */   }
/*     */ 
/*     */   public Date getCreatedTime() {
/*  71 */     return this.createdTime;
/*     */   }
/*     */ 
/*     */   public void setCreatedTime(Date createdTime) {
/*  75 */     this.createdTime = createdTime;
/*     */   }
/*     */ 
/*     */   public String getSiteDesc() {
/*  79 */     return this.siteDesc;
/*     */   }
/*     */ 
/*     */   public void setSiteDesc(String siteDesc) {
/*  83 */     this.siteDesc = siteDesc;
/*     */   }
/*     */ 
/*     */   public String getSignature() {
/*  87 */     return this.signature;
/*     */   }
/*     */ 
/*     */   public void setSignature(String signature) {
/*  91 */     this.signature = signature;
/*     */   }
/*     */ 
/*     */   public String getSiteAddress() {
/*  95 */     return this.siteAddress;
/*     */   }
/*     */ 
/*     */   public void setSiteAddress(String siteAddress) {
/*  99 */     this.siteAddress = siteAddress;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 103 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 107 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 112 */     return "Site{siteId=" + this.siteId + ", siteName='" + this.siteName + '\'' + ", logoUrl='" + this.logoUrl + '\'' + ", createdTime=" + this.createdTime + ", siteDesc='" + this.siteDesc + '\'' + ", signature='" + this.signature + '\'' + ", siteAddress='" + this.siteAddress + '\'' + ", communityId=" + this.communityId + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Site
 * JD-Core Version:    0.6.0
 */