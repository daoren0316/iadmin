/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.domain.Pos;
/*    */ import cc.kokoko.server.ibutler.persistence.PosMapper;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("posService")
/*    */ public class PosService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private PosMapper posMapper;
/*    */ 
/*    */   public Map<String, Object> getPosRecord(Long communityId, String posNo, Integer pagesize, Integer curPage)
/*    */   {
/* 33 */     Map paramMap = new HashMap();
/* 34 */     paramMap.put("communityId", communityId);
/* 35 */     paramMap.put("posNo", posNo);
/* 36 */     paramMap.put("pagesize", pagesize);
/* 37 */     paramMap.put("start", curPage);
/*    */ 
/* 39 */     List list = this.posMapper.getPosList(paramMap);
/*    */ 
/* 41 */     Long count = this.posMapper.getPosCount(paramMap);
/* 42 */     paramMap = new HashMap();
/* 43 */     paramMap.put("list", list);
/* 44 */     paramMap.put("count", count);
/* 45 */     return paramMap;
/*    */   }
/*    */ 
/*    */   public void insertPos(Pos pos)
/*    */   {
/* 54 */     this.posMapper.insertPos(pos);
/*    */   }
/*    */ 
/*    */   public void updatePos(Pos pos)
/*    */   {
/* 63 */     this.posMapper.updatePos(pos);
/*    */   }
/*    */ 
/*    */   public Pos getOnePos(Long shopId)
/*    */   {
/* 73 */     return this.posMapper.getOnePos(shopId);
/*    */   }
/*    */ 
/*    */   public Pos getOneByPosNo(String posNo)
/*    */   {
/* 83 */     return this.posMapper.getOneByPosNo(posNo);
/*    */   }
/*    */ 
/*    */   public void deletePos(Pos pos)
/*    */   {
/* 92 */     this.posMapper.deletePos(pos);
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.PosService
 * JD-Core Version:    0.6.0
 */