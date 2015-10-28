/*     */ package cc.kokoko.server.ibutler.domain.kdt;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class DatLifeTime
/*     */ {
/*     */   private Long id;
/*     */   private String tid;
/*     */   private Long num_iid;
/*     */   private String buyer_nick;
/*     */   private Byte status;
/*     */   private Date created;
/*     */   private Date pay_time;
/*     */   private Date consign_time;
/*     */   private Date sign_time;
/*     */   private Date createdTime;
/*     */   private Date updateTime;
/*     */   private Date effectiveTime;
/*     */   private String bbrName;
/*     */   private String bbrPhone;
/*     */   private String bbrCard;
/*     */   private String bbrImg;
/*     */   private String bbrMessage;
/*     */   private String bbrOccupation;
/*     */   private Boolean flag;
/*     */   private String dataSources;
/*     */   private Boolean sign;
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  32 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Long id) {
/*  36 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getTid() {
/*  40 */     return this.tid;
/*     */   }
/*     */ 
/*     */   public void setTid(String tid) {
/*  44 */     this.tid = tid;
/*     */   }
/*     */ 
/*     */   public Long getNum_iid() {
/*  48 */     return this.num_iid;
/*     */   }
/*     */ 
/*     */   public void setNum_iid(Long num_iid) {
/*  52 */     this.num_iid = num_iid;
/*     */   }
/*     */ 
/*     */   public String getBuyer_nick() {
/*  56 */     return this.buyer_nick;
/*     */   }
/*     */ 
/*     */   public void setBuyer_nick(String buyer_nick) {
/*  60 */     this.buyer_nick = buyer_nick;
/*     */   }
/*     */ 
/*     */   public Byte getStatus() {
/*  64 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status) {
/*  68 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Date getCreated() {
/*  72 */     return this.created;
/*     */   }
/*     */ 
/*     */   public void setCreated(Date created) {
/*  76 */     this.created = created;
/*     */   }
/*     */ 
/*     */   public Date getPay_time() {
/*  80 */     return this.pay_time;
/*     */   }
/*     */ 
/*     */   public void setPay_time(Date pay_time) {
/*  84 */     this.pay_time = pay_time;
/*     */   }
/*     */ 
/*     */   public Date getConsign_time() {
/*  88 */     return this.consign_time;
/*     */   }
/*     */ 
/*     */   public void setConsign_time(Date consign_time) {
/*  92 */     this.consign_time = consign_time;
/*     */   }
/*     */ 
/*     */   public Date getSign_time() {
/*  96 */     return this.sign_time;
/*     */   }
/*     */ 
/*     */   public void setSign_time(Date sign_time) {
/* 100 */     this.sign_time = sign_time;
/*     */   }
/*     */ 
/*     */   public Date getCreatedTime() {
/* 104 */     return this.createdTime;
/*     */   }
/*     */ 
/*     */   public void setCreatedTime(Date createdTime) {
/* 108 */     this.createdTime = createdTime;
/*     */   }
/*     */ 
/*     */   public Date getUpdateTime() {
/* 112 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {
/* 116 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   public Date getEffectiveTime() {
/* 120 */     return this.effectiveTime;
/*     */   }
/*     */ 
/*     */   public void setEffectiveTime(Date effectiveTime) {
/* 124 */     this.effectiveTime = effectiveTime;
/*     */   }
/*     */ 
/*     */   public String getBbrName() {
/* 128 */     return this.bbrName;
/*     */   }
/*     */ 
/*     */   public void setBbrName(String bbrName) {
/* 132 */     this.bbrName = bbrName;
/*     */   }
/*     */ 
/*     */   public String getBbrPhone() {
/* 136 */     return this.bbrPhone;
/*     */   }
/*     */ 
/*     */   public void setBbrPhone(String bbrPhone) {
/* 140 */     this.bbrPhone = bbrPhone;
/*     */   }
/*     */ 
/*     */   public String getBbrCard() {
/* 144 */     return this.bbrCard;
/*     */   }
/*     */ 
/*     */   public void setBbrCard(String bbrCard) {
/* 148 */     this.bbrCard = bbrCard;
/*     */   }
/*     */ 
/*     */   public String getBbrImg() {
/* 152 */     return this.bbrImg;
/*     */   }
/*     */ 
/*     */   public void setBbrImg(String bbrImg) {
/* 156 */     this.bbrImg = bbrImg;
/*     */   }
/*     */ 
/*     */   public String getBbrMessage() {
/* 160 */     return this.bbrMessage;
/*     */   }
/*     */ 
/*     */   public void setBbrMessage(String bbrMessage) {
/* 164 */     this.bbrMessage = bbrMessage;
/*     */   }
/*     */ 
/*     */   public String getBbrOccupation() {
/* 168 */     return this.bbrOccupation;
/*     */   }
/*     */ 
/*     */   public void setBbrOccupation(String bbrOccupation) {
/* 172 */     this.bbrOccupation = bbrOccupation;
/*     */   }
/*     */ 
/*     */   public Boolean getFlag() {
/* 176 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public void setFlag(Boolean flag) {
/* 180 */     this.flag = flag;
/*     */   }
/*     */ 
/*     */   public String getDataSources() {
/* 184 */     return this.dataSources;
/*     */   }
/*     */ 
/*     */   public void setDataSources(String dataSources) {
/* 188 */     this.dataSources = dataSources;
/*     */   }
/*     */ 
/*     */   public Boolean getSign() {
/* 192 */     return this.sign;
/*     */   }
/*     */ 
/*     */   public void setSign(Boolean sign) {
/* 196 */     this.sign = sign;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 201 */     return "DatLifeTime{id=" + this.id + ", tid='" + this.tid + '\'' + ", num_iid=" + this.num_iid + ", buyer_nick='" + this.buyer_nick + '\'' + ", status=" + this.status + ", created=" + this.created + ", pay_time=" + this.pay_time + ", consign_time=" + this.consign_time + ", sign_time=" + this.sign_time + ", createdTime=" + this.createdTime + ", updateTime=" + this.updateTime + ", effectiveTime=" + this.effectiveTime + ", bbrName='" + this.bbrName + '\'' + ", bbrPhone='" + this.bbrPhone + '\'' + ", bbrCard='" + this.bbrCard + '\'' + ", bbrImg='" + this.bbrImg + '\'' + ", bbrMessage='" + this.bbrMessage + '\'' + ", bbrOccupation='" + this.bbrOccupation + '\'' + ", flag=" + this.flag + ", dataSources='" + this.dataSources + '\'' + ", sign=" + this.sign + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.kdt.DatLifeTime
 * JD-Core Version:    0.6.0
 */