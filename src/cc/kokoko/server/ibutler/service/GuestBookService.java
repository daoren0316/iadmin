/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.domain.GuestBook;
/*    */ import cc.kokoko.server.ibutler.persistence.GuestBookMapper;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("guestBookService")
/*    */ public class GuestBookService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private GuestBookMapper guestBookMapper;
/*    */ 
/*    */   public Map<String, Object> getGuestBookRecord(String starttime, String endtime, Integer pagesize, Integer curPage)
/*    */   {
/* 37 */     Map paramMap = new HashMap();
/* 38 */     paramMap.put("pagesize", pagesize);
/* 39 */     paramMap.put("start", curPage);
/* 40 */     paramMap.put("starttime", starttime);
/* 41 */     paramMap.put("endtime", endtime);
/*    */ 
/* 43 */     List list = this.guestBookMapper.getGuestBookRecord(paramMap);
/* 44 */     Long count = this.guestBookMapper.getGuestBookCount(paramMap);
/* 45 */     paramMap = new HashMap();
/* 46 */     paramMap.put("list", list);
/* 47 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 48 */     return paramMap;
/*    */   }
/*    */ 
/*    */   public void createGuestBook(Long uid, String ip, String content) {
/* 52 */     GuestBook gb = new GuestBook();
/* 53 */     gb.setContent(content);
/* 54 */     gb.setIp(ip);
/* 55 */     gb.setUid(uid);
/* 56 */     this.guestBookMapper.saveGustbook(gb);
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.GuestBookService
 * JD-Core Version:    0.6.0
 */