/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Message;
import cc.kokoko.server.ibutler.domain.SysMessage;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.domain.bdpush.BDPushMessage;
import cc.kokoko.server.ibutler.persistence.MessageMapper;
import cc.kokoko.server.ibutler.service.util.BDPushUtil;
import cc.kokoko.server.ibutler.service.util.MessageUtil;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import cc.kokoko.server.ibutler.service.util.UserUtil;
/*     */ 
/*     */ @Service("messageService")
/*     */ public class MessageService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private MessageMapper messageMapper;
/*  29 */   private static Logger log = LoggerFactory.getLogger(MessageService.class);
/*     */ 
/*     */   public List<Message> getMessageListByUid(Long uid) {
/*  32 */     log.debug("[getMessageListByUid] uid=" + uid);
/*  33 */     return this.messageMapper.getMessageListByUid(uid);
/*     */   }
/*     */ 
/*     */   public void writeToDb(Message message)
/*     */   {
/*  40 */     this.messageMapper.createMessage(message);
/*     */ 
/*  43 */     String title = message.getContent();
/*  44 */     if (title.length() > 128) {
/*  45 */       title = title.substring(0, 128);
/*     */     }
/*  47 */     BDPushMessage bdPushMessage = BDPushUtil.createBDPushMessage(title, message.getMessageType(), message);
/*  48 */     BDPushUtil.pushByTag("" + message.getToUid(), ObjectUtil.getJsonStr(bdPushMessage), 3);
/*  49 */     BDPushUtil.pushByTag("" + message.getToUid(), ObjectUtil.getJsonStr(bdPushMessage), 4);
/*     */   }
/*     */ 
/*     */   public Message sendMessage(User user, Long toUid, String content)
/*     */   {
/*  54 */     String avatar = UserUtil.getUserAvatar(user.getProfileImage());
/*  55 */     Message message = MessageUtil.createMessage(AppConst.MessageType.P2P_MESSAGE, toUid, content, user.getUid(), user.getNickname(), avatar);
/*     */ 
/*  57 */     writeToDb(message);
/*  58 */     return message;
/*     */   }
/*     */ 
/*     */   public List<Message> getMessageList(Long uid, Integer pagesize, Integer curPage, Long toUid)
/*     */   {
/*  64 */     Map paramMap = new HashMap();
/*  65 */     if ((pagesize == null) || (pagesize.intValue() < 1)) {
/*  66 */       pagesize = Integer.valueOf(20);
/*     */     }
/*  68 */     if ((curPage == null) || (curPage.intValue() < 1)) {
/*  69 */       curPage = Integer.valueOf(1);
/*     */     }
/*  71 */     int start = pagesize.intValue() * (curPage.intValue() - 1);
/*  72 */     paramMap.put("pagesize", pagesize);
/*  73 */     paramMap.put("start", Integer.valueOf(start));
/*  74 */     paramMap.put("uid", uid);
/*  75 */     if ((toUid != null) && (toUid.longValue() > 0L)) {
/*  76 */       paramMap.put("toUid", toUid);
/*  77 */       return this.messageMapper.getMessageList2(paramMap);
/*     */     }
/*  79 */     return this.messageMapper.getMessageList(paramMap);
/*     */   }
/*     */ 
/*     */   public List<SysMessage> getSysMessageList(Long communityId, Integer pagesize, Integer curPage)
/*     */   {
/*  85 */     Map paramMap = new HashMap();
/*  86 */     if ((pagesize == null) || (pagesize.intValue() < 1)) {
/*  87 */       pagesize = Integer.valueOf(20);
/*     */     }
/*  89 */     if ((curPage == null) || (curPage.intValue() < 1)) {
/*  90 */       curPage = Integer.valueOf(1);
/*     */     }
/*  92 */     int start = pagesize.intValue() * (curPage.intValue() - 1);
/*  93 */     paramMap.put("pagesize", pagesize);
/*  94 */     paramMap.put("start", Integer.valueOf(start));
/*  95 */     paramMap.put("communityId", communityId);
/*  96 */     return this.messageMapper.getLocalSysMessageList(paramMap);
/*     */   }
/*     */ 
/*     */   public int getMessageCountByUid(Long uid)
/*     */   {
/* 101 */     return this.messageMapper.getMessageCountByUid(uid);
/*     */   }
/*     */ 
/*     */   public List<Message> getLatestMessage(Long uid)
/*     */   {
/* 106 */     return this.messageMapper.getLatestMessage(uid);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getMessageRecord(Long uid, String starttime, String endtime, Integer pagesize, Integer curPage)
/*     */   {
/* 120 */     Map paramMap = new HashMap();
/* 121 */     paramMap.put("uid", uid);
/* 122 */     paramMap.put("starttime", starttime);
/* 123 */     paramMap.put("endtime", endtime);
/* 124 */     paramMap.put("pagesize", pagesize);
/* 125 */     paramMap.put("start", curPage);
/* 126 */     List list = this.messageMapper.getMessageRecordByParam(paramMap);
/* 127 */     Long count = this.messageMapper.getMessageCountByParam(paramMap);
/* 128 */     paramMap = new HashMap();
/* 129 */     paramMap.put("list", list);
/* 130 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 131 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public void insert(Message message)
/*     */   {
/* 140 */     this.messageMapper.createMessage(message);
/*     */   }
/*     */ 
/*     */   public Message getMessageById(Long messageId)
/*     */   {
/* 150 */     return this.messageMapper.getMessageById(messageId);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.MessageService
 * JD-Core Version:    0.6.0
 */