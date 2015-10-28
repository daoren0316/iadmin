/*    */ package cc.kokoko.server.ibutler.service;
/*    */ 
/*    */ import cc.kokoko.server.ibutler.domain.SysConfig;
/*    */ import cc.kokoko.server.ibutler.persistence.SysConfigMapper;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("sysConfigService")
/*    */ public class SysConfigService
/*    */ {
/* 21 */   private static Logger log = LoggerFactory.getLogger(SysConfigService.class);
/*    */ 
/*    */   @Autowired
/*    */   private SysConfigMapper sysConfigMapper;
/*    */ 
/*    */   public void loadConfig()
/*    */   {
/* 31 */     log.debug("start load sys config from db.");
/* 32 */     List<SysConfig> sysConfigList = this.sysConfigMapper.getSysConfigList();
/* 33 */     if (sysConfigList.size() < 1) {
/* 34 */       log.error("no config data");
/* 35 */       return;
/*    */     }
/* 37 */     Map configMap = new HashMap();
/* 38 */     for (SysConfig config : sysConfigList) {
/* 39 */       configMap.put(config.getCfgKey(), config.getCfgValue());
/*    */     }
/* 41 */     cc.kokoko.server.ibutler.domain.AppConst.sysConfigMap = configMap;
/* 42 */     log.debug("succ! " + configMap.size());
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.SysConfigService
 * JD-Core Version:    0.6.0
 */