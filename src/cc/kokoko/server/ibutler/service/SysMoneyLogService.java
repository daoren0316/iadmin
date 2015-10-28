/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.persistence.SysMoneyLogMapper;
/*    */ import cc.kokoko.server.ibutler.persistence.UserMapper;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Propagation;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("sysMoneyLogService")
/*    */ public class SysMoneyLogService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SysMoneyLogMapper sysMoneyLogMapper;
/*    */ 
/*    */   @Autowired
/*    */   private UserMapper userMapper;
/*    */ 
/*    */   public Map<String, Object> getSysMoneyRecord(String starttime, String endtime, Integer pagesize, Integer curPage)
/*    */   {
/* 39 */     Map paramMap = new HashMap();
/* 40 */     paramMap.put("starttime", starttime);
/* 41 */     paramMap.put("endtime", endtime);
/* 42 */     paramMap.put("pagesize", pagesize);
/* 43 */     paramMap.put("start", curPage);
/* 44 */     List list = this.sysMoneyLogMapper.getSysMoneyRecord(paramMap);
/* 45 */     Long count = this.sysMoneyLogMapper.getSysMoneyCount(paramMap);
/* 46 */     paramMap = new HashMap();
/* 47 */     paramMap.put("list", list);
/* 48 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/* 49 */     return paramMap;
/*    */   }
/*    */ 
/*    */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*    */   public void transferMoney(Double money, Long siteId, String note)
/*    */   {
/* 62 */     Map paramMap = new HashMap();
/* 63 */     paramMap.put("amount", money);
/* 64 */     paramMap.put("uid", siteId);
/* 65 */     this.userMapper.updateUserMoney(paramMap);
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.SysMoneyLogService
 * JD-Core Version:    0.6.0
 */