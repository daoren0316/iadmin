/*     */ package cc.kokoko.server.ibutler.domain.kdt.resp;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class Trade
/*     */ {
/*     */   private String tid;
/*     */   private String num;
/*     */   private String num_iid;
/*     */   private String price;
/*     */   private String pic_path;
/*     */   private String pic_thumb_path;
/*     */   private String title;
/*     */   private String type;
/*     */   private String weixin_user_id;
/*     */   private String buyer_type;
/*     */   private String buyer_id;
/*     */   private String buyer_nick;
/*     */   private String buyer_message;
/*     */   private String seller_flag;
/*     */   private String trade_memo;
/*     */   private String receiver_city;
/*     */   private String receiver_district;
/*     */   private String receiver_name;
/*     */   private String receiver_state;
/*     */   private String receiver_address;
/*     */   private String receiver_zip;
/*     */   private String receiver_mobile;
/*     */   private String feedback;
/*     */   private String outer_tid;
/*     */   private String status;
/*     */   private String shipping_type;
/*     */   private String post_fee;
/*     */   private String total_fee;
/*     */   private String discount_fee;
/*     */   private String payment;
/*     */   private String created;
/*     */   private String update_time;
/*     */   private String pay_time;
/*     */   private String pay_type;
/*     */   private String consign_time;
/*     */   private String sign_time;
/*     */   private String buyer_area;
/*     */   private List<Orders> orders;
/*  44 */   private FetchDetail fetch_detail = new FetchDetail();
/*     */   private List<CouponDetails> coupon_details;
/*     */   private Object[] sub_trades;
/*     */ 
/*     */   public String getTid()
/*     */   {
/*  49 */     return this.tid;
/*     */   }
/*     */ 
/*     */   public void setTid(String tid) {
/*  53 */     this.tid = tid;
/*     */   }
/*     */ 
/*     */   public String getNum() {
/*  57 */     return this.num;
/*     */   }
/*     */ 
/*     */   public void setNum(String num) {
/*  61 */     this.num = num;
/*     */   }
/*     */ 
/*     */   public String getNum_iid() {
/*  65 */     return this.num_iid;
/*     */   }
/*     */ 
/*     */   public void setNum_iid(String num_iid) {
/*  69 */     this.num_iid = num_iid;
/*     */   }
/*     */ 
/*     */   public String getPrice() {
/*  73 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(String price) {
/*  77 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public String getPic_path() {
/*  81 */     return this.pic_path;
/*     */   }
/*     */ 
/*     */   public void setPic_path(String pic_path) {
/*  85 */     this.pic_path = pic_path;
/*     */   }
/*     */ 
/*     */   public String getPic_thumb_path() {
/*  89 */     return this.pic_thumb_path;
/*     */   }
/*     */ 
/*     */   public void setPic_thumb_path(String pic_thumb_path) {
/*  93 */     this.pic_thumb_path = pic_thumb_path;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/*  97 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 101 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getType() {
/* 105 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type) {
/* 109 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getWeixin_user_id() {
/* 113 */     return this.weixin_user_id;
/*     */   }
/*     */ 
/*     */   public void setWeixin_user_id(String weixin_user_id) {
/* 117 */     this.weixin_user_id = weixin_user_id;
/*     */   }
/*     */ 
/*     */   public String getBuyer_type() {
/* 121 */     return this.buyer_type;
/*     */   }
/*     */ 
/*     */   public void setBuyer_type(String buyer_type) {
/* 125 */     this.buyer_type = buyer_type;
/*     */   }
/*     */ 
/*     */   public String getBuyer_id() {
/* 129 */     return this.buyer_id;
/*     */   }
/*     */ 
/*     */   public void setBuyer_id(String buyer_id) {
/* 133 */     this.buyer_id = buyer_id;
/*     */   }
/*     */ 
/*     */   public String getBuyer_nick() {
/* 137 */     return this.buyer_nick;
/*     */   }
/*     */ 
/*     */   public void setBuyer_nick(String buyer_nick) {
/* 141 */     this.buyer_nick = buyer_nick;
/*     */   }
/*     */ 
/*     */   public String getBuyer_message() {
/* 145 */     return this.buyer_message;
/*     */   }
/*     */ 
/*     */   public void setBuyer_message(String buyer_message) {
/* 149 */     this.buyer_message = buyer_message;
/*     */   }
/*     */ 
/*     */   public String getSeller_flag() {
/* 153 */     return this.seller_flag;
/*     */   }
/*     */ 
/*     */   public void setSeller_flag(String seller_flag) {
/* 157 */     this.seller_flag = seller_flag;
/*     */   }
/*     */ 
/*     */   public String getTrade_memo() {
/* 161 */     return this.trade_memo;
/*     */   }
/*     */ 
/*     */   public void setTrade_memo(String trade_memo) {
/* 165 */     this.trade_memo = trade_memo;
/*     */   }
/*     */ 
/*     */   public String getReceiver_city() {
/* 169 */     return this.receiver_city;
/*     */   }
/*     */ 
/*     */   public void setReceiver_city(String receiver_city) {
/* 173 */     this.receiver_city = receiver_city;
/*     */   }
/*     */ 
/*     */   public String getReceiver_district() {
/* 177 */     return this.receiver_district;
/*     */   }
/*     */ 
/*     */   public void setReceiver_district(String receiver_district) {
/* 181 */     this.receiver_district = receiver_district;
/*     */   }
/*     */ 
/*     */   public String getReceiver_name() {
/* 185 */     return this.receiver_name;
/*     */   }
/*     */ 
/*     */   public void setReceiver_name(String receiver_name) {
/* 189 */     this.receiver_name = receiver_name;
/*     */   }
/*     */ 
/*     */   public String getReceiver_state() {
/* 193 */     return this.receiver_state;
/*     */   }
/*     */ 
/*     */   public void setReceiver_state(String receiver_state) {
/* 197 */     this.receiver_state = receiver_state;
/*     */   }
/*     */ 
/*     */   public String getReceiver_address() {
/* 201 */     return this.receiver_address;
/*     */   }
/*     */ 
/*     */   public void setReceiver_address(String receiver_address) {
/* 205 */     this.receiver_address = receiver_address;
/*     */   }
/*     */ 
/*     */   public String getReceiver_zip() {
/* 209 */     return this.receiver_zip;
/*     */   }
/*     */ 
/*     */   public void setReceiver_zip(String receiver_zip) {
/* 213 */     this.receiver_zip = receiver_zip;
/*     */   }
/*     */ 
/*     */   public String getReceiver_mobile() {
/* 217 */     return this.receiver_mobile;
/*     */   }
/*     */ 
/*     */   public void setReceiver_mobile(String receiver_mobile) {
/* 221 */     this.receiver_mobile = receiver_mobile;
/*     */   }
/*     */ 
/*     */   public String getFeedback() {
/* 225 */     return this.feedback;
/*     */   }
/*     */ 
/*     */   public void setFeedback(String feedback) {
/* 229 */     this.feedback = feedback;
/*     */   }
/*     */ 
/*     */   public String getOuter_tid() {
/* 233 */     return this.outer_tid;
/*     */   }
/*     */ 
/*     */   public void setOuter_tid(String outer_tid) {
/* 237 */     this.outer_tid = outer_tid;
/*     */   }
/*     */ 
/*     */   public String getStatus() {
/* 241 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status) {
/* 245 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getShipping_type() {
/* 249 */     return this.shipping_type;
/*     */   }
/*     */ 
/*     */   public void setShipping_type(String shipping_type) {
/* 253 */     this.shipping_type = shipping_type;
/*     */   }
/*     */ 
/*     */   public String getPost_fee() {
/* 257 */     return this.post_fee;
/*     */   }
/*     */ 
/*     */   public void setPost_fee(String post_fee) {
/* 261 */     this.post_fee = post_fee;
/*     */   }
/*     */ 
/*     */   public String getTotal_fee() {
/* 265 */     return this.total_fee;
/*     */   }
/*     */ 
/*     */   public void setTotal_fee(String total_fee) {
/* 269 */     this.total_fee = total_fee;
/*     */   }
/*     */ 
/*     */   public String getDiscount_fee() {
/* 273 */     return this.discount_fee;
/*     */   }
/*     */ 
/*     */   public void setDiscount_fee(String discount_fee) {
/* 277 */     this.discount_fee = discount_fee;
/*     */   }
/*     */ 
/*     */   public String getPayment() {
/* 281 */     return this.payment;
/*     */   }
/*     */ 
/*     */   public void setPayment(String payment) {
/* 285 */     this.payment = payment;
/*     */   }
/*     */ 
/*     */   public String getCreated() {
/* 289 */     return this.created;
/*     */   }
/*     */ 
/*     */   public void setCreated(String created) {
/* 293 */     this.created = created;
/*     */   }
/*     */ 
/*     */   public String getUpdate_time() {
/* 297 */     return this.update_time;
/*     */   }
/*     */ 
/*     */   public void setUpdate_time(String update_time) {
/* 301 */     this.update_time = update_time;
/*     */   }
/*     */ 
/*     */   public String getPay_time() {
/* 305 */     return this.pay_time;
/*     */   }
/*     */ 
/*     */   public void setPay_time(String pay_time) {
/* 309 */     this.pay_time = pay_time;
/*     */   }
/*     */ 
/*     */   public String getPay_type() {
/* 313 */     return this.pay_type;
/*     */   }
/*     */ 
/*     */   public void setPay_type(String pay_type) {
/* 317 */     this.pay_type = pay_type;
/*     */   }
/*     */ 
/*     */   public String getConsign_time() {
/* 321 */     return this.consign_time;
/*     */   }
/*     */ 
/*     */   public void setConsign_time(String consign_time) {
/* 325 */     this.consign_time = consign_time;
/*     */   }
/*     */ 
/*     */   public String getSign_time() {
/* 329 */     return this.sign_time;
/*     */   }
/*     */ 
/*     */   public void setSign_time(String sign_time) {
/* 333 */     this.sign_time = sign_time;
/*     */   }
/*     */ 
/*     */   public String getBuyer_area() {
/* 337 */     return this.buyer_area;
/*     */   }
/*     */ 
/*     */   public void setBuyer_area(String buyer_area) {
/* 341 */     this.buyer_area = buyer_area;
/*     */   }
/*     */ 
/*     */   public List<Orders> getOrders() {
/* 345 */     return this.orders;
/*     */   }
/*     */ 
/*     */   public void setOrders(List<Orders> orders) {
/* 349 */     this.orders = orders;
/*     */   }
/*     */ 
/*     */   public FetchDetail getFetch_detail() {
/* 353 */     return this.fetch_detail;
/*     */   }
/*     */ 
/*     */   public void setFetch_detail(FetchDetail fetch_detail) {
/* 357 */     this.fetch_detail = fetch_detail;
/*     */   }
/*     */ 
/*     */   public List<CouponDetails> getCoupon_details() {
/* 361 */     return this.coupon_details;
/*     */   }
/*     */ 
/*     */   public void setCoupon_details(List<CouponDetails> coupon_details) {
/* 365 */     this.coupon_details = coupon_details;
/*     */   }
/*     */ 
/*     */   public Object[] getSub_trades() {
/* 369 */     return this.sub_trades;
/*     */   }
/*     */ 
/*     */   public void setSub_trades(Object[] sub_trades) {
/* 373 */     this.sub_trades = sub_trades;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 378 */     return "Trade{tid='" + this.tid + '\'' + ", num='" + this.num + '\'' + ", num_iid='" + this.num_iid + '\'' + ", price='" + this.price + '\'' + ", pic_path='" + this.pic_path + '\'' + ", pic_thumb_path='" + this.pic_thumb_path + '\'' + ", title='" + this.title + '\'' + ", type='" + this.type + '\'' + ", weixin_user_id='" + this.weixin_user_id + '\'' + ", buyer_type='" + this.buyer_type + '\'' + ", buyer_id='" + this.buyer_id + '\'' + ", buyer_nick='" + this.buyer_nick + '\'' + ", buyer_message='" + this.buyer_message + '\'' + ", seller_flag='" + this.seller_flag + '\'' + ", trade_memo='" + this.trade_memo + '\'' + ", receiver_city='" + this.receiver_city + '\'' + ", receiver_district='" + this.receiver_district + '\'' + ", receiver_name='" + this.receiver_name + '\'' + ", receiver_state='" + this.receiver_state + '\'' + ", receiver_address='" + this.receiver_address + '\'' + ", receiver_zip='" + this.receiver_zip + '\'' + ", receiver_mobile='" + this.receiver_mobile + '\'' + ", feedback='" + this.feedback + '\'' + ", outer_tid='" + this.outer_tid + '\'' + ", status='" + this.status + '\'' + ", shipping_type='" + this.shipping_type + '\'' + ", post_fee='" + this.post_fee + '\'' + ", total_fee='" + this.total_fee + '\'' + ", discount_fee='" + this.discount_fee + '\'' + ", payment='" + this.payment + '\'' + ", created='" + this.created + '\'' + ", update_time='" + this.update_time + '\'' + ", pay_time='" + this.pay_time + '\'' + ", pay_type='" + this.pay_type + '\'' + ", consign_time='" + this.consign_time + '\'' + ", sign_time='" + this.sign_time + '\'' + ", buyer_area='" + this.buyer_area + '\'' + ", orders=" + this.orders + ", fetch_detail=" + this.fetch_detail + ", coupon_details=" + this.coupon_details + ", sub_trades='" + this.sub_trades + '\'' + '}';
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-domain-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.domain.kdt.resp.Trade
 * JD-Core Version:    0.6.0
 */