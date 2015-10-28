/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.domain.Feedback;
/*    */ import cc.kokoko.server.ibutler.persistence.FeedbackMapper;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("feedbackService")
/*    */ public class FeedbackService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private FeedbackMapper feedbackMapper;
/*    */ 
/*    */   public void createFeedback(Long uid, Byte category, Byte type, String content, String picList, Long communityId)
/*    */   {
/* 22 */     Feedback feedback = new Feedback();
/* 23 */     feedback.setUid(uid);
/* 24 */     feedback.setCategory(category);
/* 25 */     feedback.setType(type);
/* 26 */     feedback.setPicList(picList);
/* 27 */     feedback.setContent(content);
/* 28 */     feedback.setCommunityId(communityId);
/* 29 */     feedback.setFlag(new Byte("0"));
/* 30 */     this.feedbackMapper.createFeedback(feedback);
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getFeedbackRecord(Long communityId, Long category, Byte type, String startTime, String endTime, Long houseId, Integer pagesize, Integer curPage)
/*    */   {
/* 47 */     Map paramMap = new HashMap();
/* 48 */     paramMap.put("communityId", communityId);
/* 49 */     paramMap.put("category", category);
/* 50 */     paramMap.put("type", type);
/* 51 */     paramMap.put("starttime", startTime);
/* 52 */     paramMap.put("endtime", endTime);
/* 53 */     paramMap.put("houseId", houseId);
/* 54 */     paramMap.put("pagesize", pagesize);
/* 55 */     paramMap.put("start", curPage);
/* 56 */     List list = this.feedbackMapper.getFeedbackList(paramMap);
/* 57 */     int count = this.feedbackMapper.getFeedbackCountByCommunityId(paramMap);
/* 58 */     paramMap = new HashMap();
/* 59 */     paramMap.put("list", list);
/* 60 */     paramMap.put("count", Integer.valueOf(count));
/* 61 */     return paramMap;
/*    */   }
/*    */ 
/*    */   public Feedback getFeedbackById(Long feedbackId)
/*    */   {
/* 71 */     return this.feedbackMapper.getFeedbackById(feedbackId);
/*    */   }
/*    */ 
/*    */   public void updateFeedback(Long feedbackId, Long operatorId, String operatorName, String note)
/*    */   {
/* 83 */     Map paramMap = new HashMap();
/* 84 */     paramMap.put("feedbackId", feedbackId);
/* 85 */     paramMap.put("operatorId", operatorId);
/* 86 */     paramMap.put("operatorName", operatorName);
/* 87 */     paramMap.put("note", note);
/* 88 */     this.feedbackMapper.updateFeedback(paramMap);
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.FeedbackService
 * JD-Core Version:    0.6.0
 */