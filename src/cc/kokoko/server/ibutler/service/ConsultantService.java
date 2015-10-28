/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.util.MaxMD5Util;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Attachment;
import cc.kokoko.server.ibutler.domain.Consultant;
import cc.kokoko.server.ibutler.domain.Operator;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.persistence.ConsultantMapper;
import cc.kokoko.server.ibutler.persistence.OperatorMapper;
import cc.kokoko.server.ibutler.persistence.UserMapper;
import cc.kokoko.server.ibutler.service.util.AttachmentUtil;
/*     */ 
/*     */ @Service("consultantService")
/*     */ public class ConsultantService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ConsultantMapper consultantMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserMapper userMapper;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentService attachmentService;
/*     */ 
/*     */   @Autowired
/*     */   private OperatorMapper operatorMapper;
/*     */ 
/*     */   public Consultant getConsultantById(Long id)
/*     */   {
/*  42 */     return this.consultantMapper.getConsultantById(id);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void update(Long uid, File image, String imageFileName, Consultant consultant)
/*     */   {
/*  57 */     String thumbnailUrl = consultant.getAvatarUrl();
/*     */ 
/*  59 */     if (!StringUtil.isEmpty(imageFileName))
/*     */     {
/*  61 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, image, imageFileName);
/*     */ 
/*  63 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/*  64 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/*  66 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*     */ 
/*  69 */     consultant.setAvatarUrl(thumbnailUrl);
/*     */ 
/*  71 */     this.consultantMapper.update(consultant);
/*     */ 
/*  73 */     User user = new User();
/*  74 */     user.setUid(consultant.getConsultantId());
/*  75 */     user.setProfileImage(consultant.getAvatarUrl());
/*  76 */     user.setPhoneNumber(consultant.getPhoneNumber());
/*  77 */     user.setPublicAddress(consultant.getPublicAddress());
/*     */ 
/*  79 */     this.userMapper.updateUser(user);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getConsultantRecord(Long communityId, String name, Integer pagesize, Integer curPage)
/*     */   {
/*  92 */     Map paramMap = new HashMap();
/*  93 */     paramMap.put("communityId", communityId);
/*  94 */     paramMap.put("communityName", name);
/*  95 */     paramMap.put("pagesize", pagesize);
/*  96 */     paramMap.put("start", curPage);
/*     */ 
/*  98 */     List list = this.consultantMapper.getConsultantRecord(paramMap);
/*  99 */     Long count = this.consultantMapper.getConsultantCount(paramMap);
/* 100 */     paramMap = new HashMap();
/* 101 */     paramMap.put("list", list);
/* 102 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 103 */     return paramMap;
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insert(Long uid, Long communityId, File image, String imageFileName, Consultant consultant, String name, String password, Long gender)
/*     */   {
/* 121 */     String thumbnailUrl = consultant.getAvatarUrl();
/*     */ 
/* 123 */     if (!StringUtil.isEmpty(imageFileName))
/*     */     {
/* 125 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, image, imageFileName);
/*     */ 
/* 127 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 128 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/* 130 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*     */ 
/* 133 */     String token = MaxMD5Util.createToken(name);
/* 134 */     String passwordMd5 = MaxMD5Util.toMD5(password);
/*     */ 
/* 137 */     User user = new User();
/* 138 */     user.setUsername(name);
/* 139 */     user.setToken(token);
/* 140 */     user.setPassword(passwordMd5);
/* 141 */     user.setPhoneNumber(consultant.getPhoneNumber());
/* 142 */     user.setNickname(consultant.getConsultantName());
/* 143 */     user.setCommunityId(communityId);
/* 144 */     user.setProfileImage(thumbnailUrl);
/* 145 */     user.setUserType(new Byte("3"));
/* 146 */     user.setGender(new Byte(gender.toString()));
/* 147 */     user.setAppStatus(AppConst.UserStatus.APP_NO_DOWN);
/* 148 */     user.setCardStatus(AppConst.UserStatus.CARD_NO_HAS);
/* 149 */     this.userMapper.addUser(user);
/*     */ 
/* 152 */     Operator operator = new Operator();
/* 153 */     operator.setUid(user.getUid());
/* 154 */     operator.setUsername(name);
/* 155 */     operator.setPassword(passwordMd5);
/* 156 */     operator.setToken(token);
/* 157 */     operator.setGender(new Byte("1"));
/* 158 */     operator.setNickname(consultant.getConsultantName());
/* 159 */     operator.setPhoneNumber(consultant.getPhoneNumber());
/* 160 */     operator.setUserType(new Byte("3"));
/* 161 */     operator.setUserStatus(new Byte("0"));
/* 162 */     operator.setUserFlag(new Byte("1"));
/* 163 */     operator.setCommunityId(communityId);
/* 164 */     this.operatorMapper.insert(operator);
/*     */ 
/* 167 */     consultant.setConsultantId(user.getUid());
/* 168 */     consultant.setCommunityId(communityId);
/* 169 */     consultant.setAvatarUrl(thumbnailUrl);
/* 170 */     this.consultantMapper.insert(consultant);
/*     */   }
/*     */ 
/*     */   public void delete(Long consultantId)
/*     */   {
/* 180 */     this.consultantMapper.delete(consultantId);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.ConsultantService
 * JD-Core Version:    0.6.0
 */