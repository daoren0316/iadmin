/*     */ package cc.kokoko.server.ibutler.domain.kdt.req;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class KdtTradesSoldGet
/*     */ {
/*     */   private String fields;
/*     */   private String status;
/*     */   private Date start_created;
/*     */   private Date end_created;
/*     */   private Date start_update;
/*     */   private Date end_update;
/*     */   private Long weixin_user_id;
/*     */   private String buyer_nick;
/*     */   private Integer page_no;
/*     */   private Integer page_size;
/*     */ 
/*     */   public String getFields()
/*     */   {
/*  64 */     return this.fields;
/*     */   }
/*     */ 
/*     */   public void setFields(String fields) {
/*  68 */     this.fields = fields;
/*     */   }
/*     */ 
/*     */   public String getStatus() {
/*  72 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status) {
/*  76 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Date getStart_created() {
/*  80 */     return this.start_created;
/*     */   }
/*     */ 
/*     */   public void setStart_created(Date start_created) {
/*  84 */     this.start_created = start_created;
/*     */   }
/*     */ 
/*     */   public Date getEnd_created() {
/*  88 */     return this.end_created;
/*     */   }
/*     */ 
/*     */   public void setEnd_created(Date end_created) {
/*  92 */     this.end_created = end_created;
/*     */   }
/*     */ 
/*     */   public Date getStart_update() {
/*  96 */     return this.start_update;
/*     */   }
/*     */ 
/*     */   public void setStart_update(Date start_update) {
/* 100 */     this.start_update = start_update;
/*     */   }
/*     */ 
/*     */   public Date getEnd_update() {
/* 104 */     return this.end_update;
/*     */   }
/*     */ 
/*     */   public void setEnd_update(Date end_update) {
/* 108 */     this.end_update = end_update;
/*     */   }
/*     */ 
/*     */   public Long getWeixin_user_id() {
/* 112 */     return this.weixin_user_id;
/*     */   }
/*     */ 
/*     */   public void setWeixin_user_id(Long weixin_user_id) {
/* 116 */     this.weixin_user_id = weixin_user_id;
/*     */   }
/*     */ 
/*     */   public String getBuyer_nick() {
/* 120 */     return this.buyer_nick;
/*     */   }
/*     */ 
/*     */   public void setBuyer_nick(String buyer_nick) {
/* 124 */     this.buyer_nick = buyer_nick;
/*     */   }
/*     */ 
/*     */   public Integer getPage_no() {
/* 128 */     return this.page_no;
/*     */   }
/*     */ 
/*     */   public void setPage_no(Integer page_no) {
/* 132 */     this.page_no = page_no;
/*     */   }
/*     */ 
/*     */   public Integer getPage_size() {
/* 136 */     return this.page_size;
/*     */   }
/*     */ 
/*     */   public void setPage_size(Integer page_size) {
/* 140 */     this.page_size = page_size;
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.kdt.req.KdtTradesSoldGet
 * JD-Core Version:    0.6.0
 */