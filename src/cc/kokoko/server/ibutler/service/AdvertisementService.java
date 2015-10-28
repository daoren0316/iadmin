/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import cc.kokoko.server.commons.util.StringUtil;
/*     */ import cc.kokoko.server.ibutler.domain.Advertise;
/*     */ import cc.kokoko.server.ibutler.domain.Attachment;
/*     */ import cc.kokoko.server.ibutler.persistence.AdvertisementMapper;
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
/*     */ @Service("advertisementService")
/*     */ public class AdvertisementService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private AdvertisementMapper advertisementMapper;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentService attachmentService;
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insert(Long uid, Long communityId, File image, String imageFileName, Advertise advertise)
/*     */   {
/*  40 */     String thumbnailUrl = advertise.getPicUrl();
/*     */ 
/*  42 */     if (!StringUtil.isEmpty(imageFileName))
/*     */     {
/*  44 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, image, imageFileName);
/*     */ 
/*  46 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/*  47 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/*  49 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*  51 */     advertise.setPicUrl(thumbnailUrl);
/*  52 */     advertise.setCommunityId(communityId);
/*  53 */     this.advertisementMapper.insert(advertise);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void update(Long uid, Long communityId, Long advId, File image, String imageFileName, Advertise advertise)
/*     */   {
/*  67 */     Advertise advertise1 = this.advertisementMapper.getAdvertiseById(advId);
/*     */ 
/*  69 */     String thumbnailUrl = advertise1.getPicUrl();
/*     */ 
/*  71 */     if (!StringUtil.isEmpty(imageFileName))
/*     */     {
/*  73 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, image, imageFileName);
/*     */ 
/*  75 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/*  76 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/*  78 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*  80 */     advertise.setPicUrl(thumbnailUrl);
/*  81 */     advertise.setAdvId(advId);
/*  82 */     advertise.setCommunityId(communityId);
/*  83 */     this.advertisementMapper.update(advertise);
/*     */   }
/*     */ 
/*     */   public Advertise getAdvertiseById(Long advId)
/*     */   {
/*  93 */     return this.advertisementMapper.getAdvertiseById(advId);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getAdvertiseRecord(Long communityId, String picUrl, Integer pagesize, Integer curPage)
/*     */   {
/* 106 */     Map paramMap = new HashMap();
/* 107 */     paramMap.put("communityId", communityId);
/* 108 */     paramMap.put("picUrl", picUrl);
/* 109 */     paramMap.put("pagesize", pagesize);
/* 110 */     paramMap.put("start", curPage);
/*     */ 
/* 112 */     List list = this.advertisementMapper.getAdvertiseRecord(paramMap);
/* 113 */     Long count = this.advertisementMapper.getAdvertiseCount(paramMap);
/* 114 */     paramMap = new HashMap();
/* 115 */     paramMap.put("list", list);
/* 116 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 117 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public void delete(Long advId)
/*     */   {
/* 126 */     this.advertisementMapper.delete(advId);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.AdvertisementService
 * JD-Core Version:    0.6.0
 */