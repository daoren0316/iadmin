/*     */ package cc.kokoko.server.ibutler.domain.dto;
/*     */ 
/*     */ import cc.kokoko.server.ibutler.domain.Shop;
/*     */ import cc.kokoko.server.ibutler.domain.Site;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class CommodityDTO
/*     */ {
/*     */   private Long commodityId;
/*     */   private String commodityTitle;
/*     */   private BigDecimal price;
/*     */   private BigDecimal marketPrice;
/*     */   private Integer applyCount;
/*     */   private Date postTime;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private CommodityDescDTO commodityDesc;
/*     */   private List<AttachmentDTO> attachmentList;
/*     */   private String titlePicUrl;
/*     */   private Shop shop;
/*     */   private Site site;
/*     */   private int amountLimit;
/*     */   private Boolean isAnytimeDawback;
/*     */   private Boolean isTimeoutDawback;
/*     */   private Boolean isFav;
/*     */   private int availableAmount;
/*     */ 
/*     */   public int getAmountLimit()
/*     */   {
/*  89 */     return this.amountLimit;
/*     */   }
/*     */ 
/*     */   public void setAmountLimit(int amountLimit) {
/*  93 */     this.amountLimit = amountLimit;
/*     */   }
/*     */ 
/*     */   public Boolean getIsAnytimeDawback() {
/*  97 */     return this.isAnytimeDawback;
/*     */   }
/*     */ 
/*     */   public void setIsAnytimeDawback(Boolean isAnytimeDawback) {
/* 101 */     this.isAnytimeDawback = isAnytimeDawback;
/*     */   }
/*     */ 
/*     */   public Boolean getIsTimeoutDawback() {
/* 105 */     return this.isTimeoutDawback;
/*     */   }
/*     */ 
/*     */   public void setIsTimeoutDawback(Boolean isTimeoutDawback) {
/* 109 */     this.isTimeoutDawback = isTimeoutDawback;
/*     */   }
/*     */ 
/*     */   public Long getCommodityId()
/*     */   {
/* 115 */     return this.commodityId;
/*     */   }
/*     */ 
/*     */   public void setCommodityId(Long commodityId) {
/* 119 */     this.commodityId = commodityId;
/*     */   }
/*     */ 
/*     */   public String getCommodityTitle() {
/* 123 */     return this.commodityTitle;
/*     */   }
/*     */ 
/*     */   public void setCommodityTitle(String commodityTitle) {
/* 127 */     this.commodityTitle = commodityTitle;
/*     */   }
/*     */ 
/*     */   public BigDecimal getPrice() {
/* 131 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(BigDecimal price) {
/* 135 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public BigDecimal getMarketPrice() {
/* 139 */     return this.marketPrice;
/*     */   }
/*     */ 
/*     */   public void setMarketPrice(BigDecimal marketPrice) {
/* 143 */     this.marketPrice = marketPrice;
/*     */   }
/*     */ 
/*     */   public Integer getApplyCount() {
/* 147 */     return this.applyCount;
/*     */   }
/*     */ 
/*     */   public void setApplyCount(Integer applyCount) {
/* 151 */     this.applyCount = applyCount;
/*     */   }
/*     */ 
/*     */   public Date getPostTime() {
/* 155 */     return this.postTime;
/*     */   }
/*     */ 
/*     */   public void setPostTime(Date postTime) {
/* 159 */     this.postTime = postTime;
/*     */   }
/*     */ 
/*     */   public Date getStartTime() {
/* 163 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime) {
/* 167 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public Date getEndTime() {
/* 171 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime) {
/* 175 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public CommodityDescDTO getCommodityDesc() {
/* 179 */     return this.commodityDesc;
/*     */   }
/*     */ 
/*     */   public void setCommodityDesc(CommodityDescDTO commodityDesc) {
/* 183 */     this.commodityDesc = commodityDesc;
/*     */   }
/*     */ 
/*     */   public List<AttachmentDTO> getAttachmentList() {
/* 187 */     return this.attachmentList;
/*     */   }
/*     */ 
/*     */   public void setAttachmentList(List<AttachmentDTO> attachmentList) {
/* 191 */     this.attachmentList = attachmentList;
/*     */   }
/*     */ 
/*     */   public String getTitlePicUrl() {
/* 195 */     return this.titlePicUrl;
/*     */   }
/*     */ 
/*     */   public void setTitlePicUrl(String titlePicUrl) {
/* 199 */     this.titlePicUrl = titlePicUrl;
/*     */   }
/*     */ 
/*     */   public Shop getShop() {
/* 203 */     return this.shop;
/*     */   }
/*     */ 
/*     */   public void setShop(Shop shop) {
/* 207 */     this.shop = shop;
/*     */   }
/*     */ 
/*     */   public Boolean getIsFav() {
/* 211 */     return this.isFav;
/*     */   }
/*     */ 
/*     */   public void setIsFav(Boolean isFav) {
/* 215 */     this.isFav = isFav;
/*     */   }
/*     */ 
/*     */   public Site getSite() {
/* 219 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 223 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public int getAvailableAmount() {
/* 227 */     return this.availableAmount;
/*     */   }
/*     */ 
/*     */   public void setAvailableAmount(int availableAmount) {
/* 231 */     this.availableAmount = availableAmount;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.dto.CommodityDTO
 * JD-Core Version:    0.6.0
 */