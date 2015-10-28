/*     */ package cc.kokoko.server.ibutler.domain.bdpush;
/*     */ 
/*     */ public class BDPushMessage
/*     */ {
/*     */   private String title;
/*     */   private String description;
/*     */   private Byte notification_builder_id;
/*     */   private Byte notification_basic_style;
/*     */   private Byte open_type;
/*     */   private Byte net_support;
/*     */   private Byte user_confirm;
/*     */   private String url;
/*     */   private String pkg_content;
/*     */   private String pkg_name;
/*     */   private String pkg_version;
/*     */   private BDPushCustomContent custom_content;
/*     */ 
/*     */   public String getTitle()
/*     */   {
/*  24 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  28 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getDescription() {
/*  32 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/*  36 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public Byte getNotification_builder_id() {
/*  40 */     return this.notification_builder_id;
/*     */   }
/*     */ 
/*     */   public void setNotification_builder_id(Byte notification_builder_id) {
/*  44 */     this.notification_builder_id = notification_builder_id;
/*     */   }
/*     */ 
/*     */   public Byte getNotification_basic_style() {
/*  48 */     return this.notification_basic_style;
/*     */   }
/*     */ 
/*     */   public void setNotification_basic_style(Byte notification_basic_style) {
/*  52 */     this.notification_basic_style = notification_basic_style;
/*     */   }
/*     */ 
/*     */   public Byte getOpen_type() {
/*  56 */     return this.open_type;
/*     */   }
/*     */ 
/*     */   public void setOpen_type(Byte open_type) {
/*  60 */     this.open_type = open_type;
/*     */   }
/*     */ 
/*     */   public Byte getNet_support() {
/*  64 */     return this.net_support;
/*     */   }
/*     */ 
/*     */   public void setNet_support(Byte net_support) {
/*  68 */     this.net_support = net_support;
/*     */   }
/*     */ 
/*     */   public Byte getUser_confirm() {
/*  72 */     return this.user_confirm;
/*     */   }
/*     */ 
/*     */   public void setUser_confirm(Byte user_confirm) {
/*  76 */     this.user_confirm = user_confirm;
/*     */   }
/*     */ 
/*     */   public String getUrl() {
/*  80 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/*  84 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getPkg_content() {
/*  88 */     return this.pkg_content;
/*     */   }
/*     */ 
/*     */   public void setPkg_content(String pkg_content) {
/*  92 */     this.pkg_content = pkg_content;
/*     */   }
/*     */ 
/*     */   public String getPkg_name() {
/*  96 */     return this.pkg_name;
/*     */   }
/*     */ 
/*     */   public void setPkg_name(String pkg_name) {
/* 100 */     this.pkg_name = pkg_name;
/*     */   }
/*     */ 
/*     */   public String getPkg_version() {
/* 104 */     return this.pkg_version;
/*     */   }
/*     */ 
/*     */   public void setPkg_version(String pkg_version) {
/* 108 */     this.pkg_version = pkg_version;
/*     */   }
/*     */ 
/*     */   public BDPushCustomContent getCustom_content() {
/* 112 */     return this.custom_content;
/*     */   }
/*     */ 
/*     */   public void setCustom_content(BDPushCustomContent custom_content) {
/* 116 */     this.custom_content = custom_content;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.bdpush.BDPushMessage
 * JD-Core Version:    0.6.0
 */