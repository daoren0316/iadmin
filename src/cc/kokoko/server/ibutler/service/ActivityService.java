/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.Activity;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Attachment;
import cc.kokoko.server.ibutler.domain.Favorite;
import cc.kokoko.server.ibutler.domain.SysMessage;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.domain.dto.ActivityDTO;
import cc.kokoko.server.ibutler.domain.dto.AttachmentDTO;
import cc.kokoko.server.ibutler.persistence.ActivityMapper;
import cc.kokoko.server.ibutler.persistence.MessageMapper;
import cc.kokoko.server.ibutler.service.util.AttachmentUtil;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
/*     */ 
/*     */ @Service("activityService")
/*     */ public class ActivityService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ActivityMapper activityMapper;
/*     */ 
/*     */   @Autowired
/*     */   private MessageMapper messageMapper;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @Autowired
/*     */   private ShopService shopService;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentService attachmentService;
/*     */ 
/*     */   public List<Activity> getActivityList(Long shopId)
/*     */   {
/*  34 */     return this.activityMapper.getActivityList(shopId);
/*     */   }
/*     */ 
/*     */   public List<Activity> getActivityListByUid(Long uid, Long communityId)
/*     */   {
/*  43 */     return this.activityMapper.getLocalActivityList(communityId);
/*     */   }
/*     */ 
/*     */   public List<ActivityDTO> getActivityDTOList(List<Activity> actList) {
/*  47 */     List dtoList = null;
/*  48 */     if (actList.size() > 0) {
/*  49 */       dtoList = new ArrayList();
/*  50 */       for (Activity act : actList) {
/*  51 */         dtoList.add(getActivityDTO(act));
/*     */       }
/*     */     }
/*     */ 
/*  55 */     return dtoList;
/*     */   }
/*     */ 
/*     */   public ActivityDTO getActivityDTO(Activity activity) {
/*  59 */     ActivityDTO dto = null;
/*  60 */     if (activity != null) {
/*  61 */       dto = new ActivityDTO();
/*  62 */       dto.setActivityId(activity.getActivityId());
/*  63 */       dto.setActivityDesc(activity.getActivityDesc());
/*  64 */       dto.setActivityTitle(activity.getActivityTitle());
/*  65 */       dto.setPostTime(activity.getPostTime());
/*  66 */       dto.setUid(activity.getUid());
/*  67 */       dto.setTitlePicUrl(activity.getTitlePicUrl());
/*  68 */       dto.setUserLogoUrl(activity.getUserLogoUrl());
/*  69 */       dto.setUsername(activity.getUsername());
/*  70 */       if ((!StringUtil.isEmpty(activity.getAttachmentList())) && (!"null".equals(activity.getAttachmentList().toLowerCase().trim())))
/*     */       {
/*  73 */         dto.setAttachmentList(ObjectUtil.getObjectListFromJson(activity.getAttachmentList(), AttachmentDTO.class));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  82 */     return dto;
/*     */   }
/*     */ 
/*     */   public Activity getActivityById(Long activityId) {
/*  86 */     return this.activityMapper.getActivityById(activityId);
/*     */   }
/*     */   @Transactional
/*     */   public ActivityDTO getActivityDTO(Long activityId, Long invokeUid) {
/*  91 */     Activity activity = getActivityById(activityId);
/*     */ 
/*  93 */     ActivityDTO dto = getActivityDTO(activity);
/*  94 */     dto.setIsFav(this.userService.getFavStatus(activityId, invokeUid, AppConst.FavType.ACTIVITY));
/*     */ 
/*  96 */     activity.setViewCount(Integer.valueOf(activity.getViewCount().intValue() + 1));
/*  97 */     this.activityMapper.updateActViewCount(activity);
/*  98 */     return dto;
/*     */   }
/*     */ 
/*     */   public void createActivity(Activity activity, Long uid) {
/* 102 */     activity.setPostTime(new Date());
/* 103 */     activity.setViewCount(Integer.valueOf(0));
/* 104 */     activity.setUid(uid);
/* 105 */     this.activityMapper.createActivity(activity);
/*     */   }
/*     */ 
/*     */   public void createActivity(User user, String title, String desc, String picUrl, String attachList)
/*     */   {
/* 118 */     Activity activity = new Activity();
/* 119 */     activity.setActivityTitle(title);
/* 120 */     activity.setActivityDesc(desc);
/* 121 */     activity.setAttachmentList(attachList);
/* 122 */     activity.setViewCount(Integer.valueOf(0));
/* 123 */     activity.setPostTime(new Date());
/* 124 */     activity.setUid(user.getUid());
/* 125 */     activity.setTitlePicUrl(picUrl);
/* 126 */     activity.setCommunityId(user.getCommunityId());
/* 127 */     activity.setUserType(user.getUserType());
/* 128 */     this.activityMapper.createActivity(activity);
/*     */   }
/*     */ 
/*     */   public void addFav(Long uid, Long activityId) {
/* 132 */     Activity act = getActivityById(activityId);
/* 133 */     if (act == null) {
/* 134 */       return;
/*     */     }
/* 136 */     if (this.shopService.isFav(uid, activityId, AppConst.FavType.ACTIVITY) == true) {
/* 137 */       return;
/*     */     }
/* 139 */     Favorite favorite = new Favorite();
/* 140 */     favorite.setFavPicUrl(act.getTitlePicUrl());
/* 141 */     favorite.setFavTitle(act.getActivityTitle());
/* 142 */     favorite.setFavType(AppConst.FavType.ACTIVITY);
/* 143 */     favorite.setReferId(activityId);
/* 144 */     favorite.setUid(uid);
/* 145 */     this.userService.addFavorite(favorite);
/*     */   }
/*     */ 
/*     */   public void delActivityById(Long activityId) {
/* 149 */     this.activityMapper.delActivityById(activityId);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getActivityRecord(Long uid, String starttime, String endtime, Integer pagesize, Integer curPage)
/*     */   {
/* 163 */     Map paramMap = new HashMap();
/* 164 */     paramMap.put("uid", uid);
/* 165 */     paramMap.put("starttime", starttime);
/* 166 */     paramMap.put("endtime", endtime);
/* 167 */     paramMap.put("pagesize", pagesize);
/* 168 */     paramMap.put("start", curPage);
/* 169 */     List list = this.activityMapper.getActivityRecordByParam(paramMap);
/* 170 */     Long count = this.activityMapper.getActivityCountByParam(paramMap);
/* 171 */     paramMap = new HashMap();
/* 172 */     paramMap.put("list", list);
/* 173 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 174 */     return paramMap;
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insert(Long uid, File titleImage, String titleImageFileName, File[] image, String[] imageFileName, Activity activity)
/*     */   {
/* 190 */     User user = this.userService.getUserByUid(uid);
/*     */ 
/* 193 */     String thumbnailUrl = "";
/*     */ 
/* 195 */     if (!StringUtil.isEmpty(titleImageFileName))
/*     */     {
/* 197 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, titleImage, titleImageFileName);
/*     */ 
/* 199 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 200 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/* 202 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*     */ 
/* 207 */     String attachmentList = "";
/*     */ 
/* 209 */     if ((imageFileName != null) && (imageFileName.length > 0)) {
/* 210 */       List list = new ArrayList();
/*     */ 
/* 212 */       for (int i = 0; i < imageFileName.length; i++) {
/* 213 */         File file = image[i];
/* 214 */         String fileName = imageFileName[i];
/*     */ 
/* 216 */         Attachment attachment = this.attachmentService.uploadAttachment(uid, file, fileName);
/*     */ 
/* 218 */         this.attachmentService.createAttachment(attachment);
/*     */ 
/* 220 */         AttachmentDTO attachmentDTO = AttachmentUtil.Attachment2AttachmentDTO(attachment);
/*     */ 
/* 222 */         list.add(attachmentDTO);
/*     */       }
/*     */ 
/* 225 */       attachmentList = ObjectUtil.getJsonStr(list);
/*     */     }
/*     */ 
/* 229 */     activity.setTitlePicUrl(thumbnailUrl);
/* 230 */     activity.setAttachmentList(attachmentList);
/* 231 */     activity.setViewCount(Integer.valueOf(0));
/* 232 */     activity.setCommunityId(user.getCommunityId());
/* 233 */     activity.setUserType(user.getUserType());
/* 234 */     activity.setUid(user.getUid());
/* 235 */     this.activityMapper.createActivity(activity);
/*     */ 
/* 238 */     SysMessage sysMessage = new SysMessage();
/* 239 */     sysMessage.setSysMessageTitle(activity.getActivityTitle());
/* 240 */     sysMessage.setSysMessageContent(activity.getActivityDesc());
/* 241 */     sysMessage.setViewCount(Long.valueOf(0L));
/* 242 */     sysMessage.setUid(user.getUid());
/* 243 */     sysMessage.setTitlePicUrl(thumbnailUrl);
/* 244 */     sysMessage.setCommunityId(user.getCommunityId());
/* 245 */     sysMessage.setUserType(user.getUserType());
/*     */ 
/* 247 */     Byte sysMessageType = null;
/* 248 */     switch (user.getUserType().byteValue()) {
/*     */     case 1:
/* 250 */       sysMessageType = new Byte("2");
/* 251 */       break;
/*     */     case 2:
/* 253 */       sysMessageType = new Byte("3");
/* 254 */       break;
/*     */     case 3:
/* 256 */       sysMessageType = new Byte("4");
/*     */     }
/*     */ 
/* 259 */     sysMessage.setSysMessageType(sysMessageType);
/* 260 */     sysMessage.setReferId(activity.getActivityId());
/* 261 */     this.messageMapper.addSysMessage(sysMessage);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void update(Long uid, File titleImage, String titleImageFileName, File[] image, String[] imageFileName, Activity activity)
/*     */   {
/* 277 */     Activity activity1 = this.activityMapper.getActivityById(activity.getActivityId());
/*     */ 
/* 281 */     String thumbnailUrl = activity1.getTitlePicUrl();
/*     */ 
/* 283 */     if (!StringUtil.isEmpty(titleImageFileName))
/*     */     {
/* 285 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, titleImage, titleImageFileName);
/*     */ 
/* 287 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 288 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/* 290 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/*     */ 
/* 295 */     String attachmentList = activity1.getAttachmentList();
/*     */ 
/* 297 */     if ((imageFileName != null) && (imageFileName.length > 0)) {
/* 298 */       List list = new ArrayList();
/*     */ 
/* 300 */       for (int i = 0; i < imageFileName.length; i++) {
/* 301 */         File file = image[i];
/* 302 */         String fileName = imageFileName[i];
/*     */ 
/* 304 */         Attachment attachment = this.attachmentService.uploadAttachment(uid, file, fileName);
/*     */ 
/* 306 */         this.attachmentService.createAttachment(attachment);
/*     */ 
/* 308 */         AttachmentDTO attachmentDTO = AttachmentUtil.Attachment2AttachmentDTO(attachment);
/*     */ 
/* 310 */         list.add(attachmentDTO);
/*     */       }
/*     */ 
/* 313 */       attachmentList = ObjectUtil.getJsonStr(list);
/*     */     }
/*     */ 
/* 317 */     activity.setTitlePicUrl(thumbnailUrl);
/* 318 */     activity.setAttachmentList(attachmentList);
/* 319 */     this.activityMapper.updateActivity(activity);
/*     */   }
/*     */ 
/*     */   public void delete(Long activityId)
/*     */   {
/* 328 */     this.activityMapper.delActivityById(activityId);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.ActivityService
 * JD-Core Version:    0.6.0
 */