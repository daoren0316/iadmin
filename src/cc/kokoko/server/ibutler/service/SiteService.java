/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import cc.kokoko.server.commons.util.StringUtil;
/*     */ import cc.kokoko.server.ibutler.domain.Advertise;
/*     */ import cc.kokoko.server.ibutler.domain.Attachment;
/*     */ import cc.kokoko.server.ibutler.domain.Consultant;
/*     */ import cc.kokoko.server.ibutler.domain.Site;
/*     */ import cc.kokoko.server.ibutler.domain.User;
/*     */ import cc.kokoko.server.ibutler.persistence.SiteMapper;
/*     */ import cc.kokoko.server.ibutler.persistence.UserMapper;
/*     */ import cc.kokoko.server.ibutler.service.util.AttachmentUtil;
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Propagation;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("siteService")
/*     */ public class SiteService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SiteMapper siteMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserMapper userMapper;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentService attachmentService;
/*     */ 
/*     */   public Site getSiteDetail(Long siteId)
/*     */   {
/*  28 */     return this.siteMapper.getSiteDetailById(siteId);
/*     */   }
/*     */ 
/*     */   public Site getSiteDetailByCommunityId(Long ommunityId)
/*     */   {
/*  38 */     return this.siteMapper.getSiteDetailByCommunityId(ommunityId);
/*     */   }
/*     */ 
/*     */   public List<Consultant> getConsultantList(Long communityId) {
/*  42 */     return this.siteMapper.getConsultantList(communityId);
/*     */   }
/*     */ 
/*     */   public Consultant getConsultantById(Long consultantId) {
/*  46 */     return this.siteMapper.getConsultantById(consultantId);
/*     */   }
/*     */ 
/*     */   public List<Advertise> getAdvertisementList(Long communityId) {
/*  50 */     return this.siteMapper.getAdvertisementList(communityId);
/*     */   }
/*     */ 
/*     */   public void updateSite(Site site) {
/*  54 */     this.siteMapper.updateSiteById(site);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getAllSiteRecord(String siteName, Integer pagesize, Integer curPage)
/*     */   {
/*  66 */     Map paramMap = new HashMap();
/*  67 */     paramMap.put("siteName", siteName);
/*  68 */     paramMap.put("pagesize", pagesize);
/*  69 */     paramMap.put("start", curPage);
/*  70 */     List list = this.siteMapper.getAllSiteRecord(paramMap);
/*  71 */     Long count = this.siteMapper.getAllSiteCount(paramMap);
/*  72 */     paramMap = new HashMap();
/*  73 */     paramMap.put("list", list);
/*  74 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/*  75 */     return paramMap;
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void update(Long uid, File image, String imageFileName, Site site, String phoneNumber)
/*     */   {
/*  86 */     String thumbnailUrl = site.getLogoUrl();
/*     */ 
/*  88 */     if (!StringUtil.isEmpty(imageFileName))
/*     */     {
/*  90 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, image, imageFileName);
/*     */ 
/*  92 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/*  93 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/*  95 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*     */ 
/*  98 */     site.setLogoUrl(thumbnailUrl);
/*     */ 
/* 100 */     this.siteMapper.updateSiteById(site);
/*     */ 
/* 102 */     User user = new User();
/* 103 */     user.setUid(uid);
/* 104 */     user.setProfileImage(site.getLogoUrl());
/* 105 */     user.setPhoneNumber(phoneNumber);
/* 106 */     user.setPublicAddress(site.getSiteAddress());
/*     */ 
/* 108 */     this.userMapper.updateUser(user);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.SiteService
 * JD-Core Version:    0.6.0
 */