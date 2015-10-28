/*    */ package cc.kokoko.server.ibutler.domain;
/*    */ 
/*    */ public class ClientVersion
/*    */ {
/*    */   private Integer versionCode;
/*    */   private String versionName;
/*    */   private String url;
/*    */   private String desc;
/*    */   private Boolean forced;
/*    */ 
/*    */   public Integer getVersionCode()
/*    */   {
/* 26 */     return this.versionCode;
/*    */   }
/*    */ 
/*    */   public void setVersionCode(Integer versionCode) {
/* 30 */     this.versionCode = versionCode;
/*    */   }
/*    */ 
/*    */   public String getVersionName() {
/* 34 */     return this.versionName;
/*    */   }
/*    */ 
/*    */   public void setVersionName(String versionName) {
/* 38 */     this.versionName = versionName;
/*    */   }
/*    */ 
/*    */   public String getUrl() {
/* 42 */     return this.url;
/*    */   }
/*    */ 
/*    */   public void setUrl(String url) {
/* 46 */     this.url = url;
/*    */   }
/*    */ 
/*    */   public String getDesc() {
/* 50 */     return this.desc;
/*    */   }
/*    */ 
/*    */   public void setDesc(String desc) {
/* 54 */     this.desc = desc;
/*    */   }
/*    */ 
/*    */   public Boolean getForced() {
/* 58 */     return this.forced;
/*    */   }
/*    */ 
/*    */   public void setForced(Boolean forced) {
/* 62 */     this.forced = forced;
/*    */   }
/*    */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.ClientVersion
 * JD-Core Version:    0.6.0
 */