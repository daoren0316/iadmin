/*     */ package cc.kokoko.server.ibutler.domain.kdt.resp;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class Orders
/*     */ {
/*     */   private String num_iid;
/*     */   private String sku_id;
/*     */   private String num;
/*     */   private String outer_sku_id;
/*     */   private String outer_item_id;
/*     */   private String title;
/*     */   private String seller_nick;
/*     */   private String price;
/*     */   private String total_fee;
/*     */   private String discount_fee;
/*     */   private String payment;
/*     */   private String sku_properties_name;
/*     */   private String pic_path;
/*     */   private String pic_thumb_path;
/*     */   private List<BuyerMessages> buyer_messages;
/*     */ 
/*     */   public String getNum_iid()
/*     */   {
/*  24 */     return this.num_iid;
/*     */   }
/*     */ 
/*     */   public void setNum_iid(String num_iid) {
/*  28 */     this.num_iid = num_iid;
/*     */   }
/*     */ 
/*     */   public String getSku_id() {
/*  32 */     return this.sku_id;
/*     */   }
/*     */ 
/*     */   public void setSku_id(String sku_id) {
/*  36 */     this.sku_id = sku_id;
/*     */   }
/*     */ 
/*     */   public String getNum() {
/*  40 */     return this.num;
/*     */   }
/*     */ 
/*     */   public void setNum(String num) {
/*  44 */     this.num = num;
/*     */   }
/*     */ 
/*     */   public String getOuter_sku_id() {
/*  48 */     return this.outer_sku_id;
/*     */   }
/*     */ 
/*     */   public void setOuter_sku_id(String outer_sku_id) {
/*  52 */     this.outer_sku_id = outer_sku_id;
/*     */   }
/*     */ 
/*     */   public String getOuter_item_id() {
/*  56 */     return this.outer_item_id;
/*     */   }
/*     */ 
/*     */   public void setOuter_item_id(String outer_item_id) {
/*  60 */     this.outer_item_id = outer_item_id;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/*  64 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  68 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getSeller_nick() {
/*  72 */     return this.seller_nick;
/*     */   }
/*     */ 
/*     */   public void setSeller_nick(String seller_nick) {
/*  76 */     this.seller_nick = seller_nick;
/*     */   }
/*     */ 
/*     */   public String getPrice() {
/*  80 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(String price) {
/*  84 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public String getTotal_fee() {
/*  88 */     return this.total_fee;
/*     */   }
/*     */ 
/*     */   public void setTotal_fee(String total_fee) {
/*  92 */     this.total_fee = total_fee;
/*     */   }
/*     */ 
/*     */   public String getDiscount_fee() {
/*  96 */     return this.discount_fee;
/*     */   }
/*     */ 
/*     */   public void setDiscount_fee(String discount_fee) {
/* 100 */     this.discount_fee = discount_fee;
/*     */   }
/*     */ 
/*     */   public String getPayment() {
/* 104 */     return this.payment;
/*     */   }
/*     */ 
/*     */   public void setPayment(String payment) {
/* 108 */     this.payment = payment;
/*     */   }
/*     */ 
/*     */   public String getSku_properties_name() {
/* 112 */     return this.sku_properties_name;
/*     */   }
/*     */ 
/*     */   public void setSku_properties_name(String sku_properties_name) {
/* 116 */     this.sku_properties_name = sku_properties_name;
/*     */   }
/*     */ 
/*     */   public String getPic_path() {
/* 120 */     return this.pic_path;
/*     */   }
/*     */ 
/*     */   public void setPic_path(String pic_path) {
/* 124 */     this.pic_path = pic_path;
/*     */   }
/*     */ 
/*     */   public String getPic_thumb_path() {
/* 128 */     return this.pic_thumb_path;
/*     */   }
/*     */ 
/*     */   public void setPic_thumb_path(String pic_thumb_path) {
/* 132 */     this.pic_thumb_path = pic_thumb_path;
/*     */   }
/*     */ 
/*     */   public List<BuyerMessages> getBuyer_messages() {
/* 136 */     return this.buyer_messages;
/*     */   }
/*     */ 
/*     */   public void setBuyer_messages(List<BuyerMessages> buyer_messages) {
/* 140 */     this.buyer_messages = buyer_messages;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 145 */     return "Orders{num_iid='" + this.num_iid + '\'' + ", sku_id='" + this.sku_id + '\'' + ", num='" + this.num + '\'' + ", outer_sku_id='" + this.outer_sku_id + '\'' + ", outer_item_id='" + this.outer_item_id + '\'' + ", title='" + this.title + '\'' + ", seller_nick='" + this.seller_nick + '\'' + ", price='" + this.price + '\'' + ", total_fee='" + this.total_fee + '\'' + ", discount_fee='" + this.discount_fee + '\'' + ", payment='" + this.payment + '\'' + ", sku_properties_name='" + this.sku_properties_name + '\'' + ", pic_path='" + this.pic_path + '\'' + ", pic_thumb_path='" + this.pic_thumb_path + '\'' + ", buyer_messages=" + this.buyer_messages + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.kdt.resp.Orders
 * JD-Core Version:    0.6.0
 */