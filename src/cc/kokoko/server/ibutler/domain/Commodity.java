/*     */ package cc.kokoko.server.ibutler.domain;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Commodity
/*     */ {
/*     */   private Long commodityId;
/*     */   private String commodityTitle;
/*     */   private BigDecimal price;
/*     */   private BigDecimal marketPrice;
/*     */   private Integer applyCount;
/*     */   private Date postTime;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private String commodityDesc;
/*     */   private String attachmentList;
/*     */   private String titlePicUrl;
/*     */   private String notice;
/*     */   private Long uid;
/*     */   private int amountLimit;
/*     */   private Boolean isAnytimeDawback;
/*     */   private Boolean isTimeoutDawback;
/*     */   private Integer saledAmount;
/*     */   private Integer totalAmount;
/*     */   private Long communityId;
/*     */ 
/*     */   public int getAmountLimit()
/*     */   {
/*  85 */     return this.amountLimit;
/*     */   }
/*     */ 
/*     */   public void setAmountLimit(int amountLimit) {
/*  89 */     this.amountLimit = amountLimit;
/*     */   }
/*     */ 
/*     */   public Boolean getIsAnytimeDawback() {
/*  93 */     return this.isAnytimeDawback;
/*     */   }
/*     */ 
/*     */   public void setIsAnytimeDawback(Boolean isAnytimeDawback) {
/*  97 */     this.isAnytimeDawback = isAnytimeDawback;
/*     */   }
/*     */ 
/*     */   public Boolean getIsTimeoutDawback() {
/* 101 */     return this.isTimeoutDawback;
/*     */   }
/*     */ 
/*     */   public void setIsTimeoutDawback(Boolean isTimeoutDawback) {
/* 105 */     this.isTimeoutDawback = isTimeoutDawback;
/*     */   }
/*     */ 
/*     */   public Long getCommodityId()
/*     */   {
/* 114 */     return this.commodityId;
/*     */   }
/*     */ 
/*     */   public void setCommodityId(Long commodityId)
/*     */   {
/* 123 */     this.commodityId = commodityId;
/*     */   }
/*     */ 
/*     */   public String getCommodityTitle()
/*     */   {
/* 132 */     return this.commodityTitle;
/*     */   }
/*     */ 
/*     */   public void setCommodityTitle(String commodityTitle)
/*     */   {
/* 141 */     this.commodityTitle = commodityTitle;
/*     */   }
/*     */ 
/*     */   public BigDecimal getPrice()
/*     */   {
/* 150 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(BigDecimal price)
/*     */   {
/* 159 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public BigDecimal getMarketPrice()
/*     */   {
/* 168 */     return this.marketPrice;
/*     */   }
/*     */ 
/*     */   public void setMarketPrice(BigDecimal marketPrice)
/*     */   {
/* 177 */     this.marketPrice = marketPrice;
/*     */   }
/*     */ 
/*     */   public Integer getApplyCount()
/*     */   {
/* 186 */     return this.applyCount;
/*     */   }
/*     */ 
/*     */   public void setApplyCount(Integer applyCount)
/*     */   {
/* 195 */     this.applyCount = applyCount;
/*     */   }
/*     */ 
/*     */   public Date getPostTime()
/*     */   {
/* 204 */     return this.postTime;
/*     */   }
/*     */ 
/*     */   public void setPostTime(Date postTime)
/*     */   {
/* 213 */     this.postTime = postTime;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 222 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 231 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 240 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 249 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public String getCommodityDesc()
/*     */   {
/* 263 */     return this.commodityDesc;
/*     */   }
/*     */ 
/*     */   public void setCommodityDesc(String commodityDesc)
/*     */   {
/* 275 */     this.commodityDesc = commodityDesc;
/*     */   }
/*     */ 
/*     */   public String getAttachmentList()
/*     */   {
/* 287 */     return this.attachmentList;
/*     */   }
/*     */ 
/*     */   public void setAttachmentList(String attachmentList)
/*     */   {
/* 299 */     this.attachmentList = attachmentList;
/*     */   }
/*     */ 
/*     */   public String getTitlePicUrl() {
/* 303 */     return this.titlePicUrl;
/*     */   }
/*     */ 
/*     */   public void setTitlePicUrl(String titlePicUrl) {
/* 307 */     this.titlePicUrl = titlePicUrl;
/*     */   }
/*     */ 
/*     */   public String getNotice() {
/* 311 */     return this.notice;
/*     */   }
/*     */ 
/*     */   public void setNotice(String notice) {
/* 315 */     this.notice = notice;
/*     */   }
/*     */ 
/*     */   public Long getUid() {
/* 319 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(Long uid) {
/* 323 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   public Integer getSaledAmount() {
/* 327 */     return this.saledAmount;
/*     */   }
/*     */ 
/*     */   public void setSaledAmount(Integer saledAmount) {
/* 331 */     this.saledAmount = saledAmount;
/*     */   }
/*     */ 
/*     */   public Integer getTotalAmount() {
/* 335 */     return this.totalAmount;
/*     */   }
/*     */ 
/*     */   public void setTotalAmount(Integer totalAmount) {
/* 339 */     this.totalAmount = totalAmount;
/*     */   }
/*     */ 
/*     */   public Long getCommunityId() {
/* 343 */     return this.communityId;
/*     */   }
/*     */ 
/*     */   public void setCommunityId(Long communityId) {
/* 347 */     this.communityId = communityId;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 352 */     return "Commodity{commodityId=" + this.commodityId + ", commodityTitle='" + this.commodityTitle + '\'' + ", price=" + this.price + ", marketPrice=" + this.marketPrice + ", applyCount=" + this.applyCount + ", postTime=" + this.postTime + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", commodityDesc='" + this.commodityDesc + '\'' + ", attachmentList='" + this.attachmentList + '\'' + ", titlePicUrl='" + this.titlePicUrl + '\'' + ", notice='" + this.notice + '\'' + ", uid=" + this.uid + ", amountLimit=" + this.amountLimit + ", isAnytimeDawback=" + this.isAnytimeDawback + ", isTimeoutDawback=" + this.isTimeoutDawback + ", saledAmount=" + this.saledAmount + ", totalAmount=" + this.totalAmount + ", communityId=" + this.communityId + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.Commodity
 * JD-Core Version:    0.6.0
 */