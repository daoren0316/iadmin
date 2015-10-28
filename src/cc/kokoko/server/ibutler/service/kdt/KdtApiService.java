/*     */ package cc.kokoko.server.ibutler.service.kdt;
/*     */ 
/*     */ import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jxl.Image;
import jxl.Sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.commons.util.DateUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.Attachment;
import cc.kokoko.server.ibutler.domain.kdt.DatLifeTime;
import cc.kokoko.server.ibutler.domain.kdt.dto.KdtShowDTO;
import cc.kokoko.server.ibutler.domain.kdt.resp.BuyerMessages;
import cc.kokoko.server.ibutler.domain.kdt.resp.KtoDTO;
import cc.kokoko.server.ibutler.domain.kdt.resp.Orders;
import cc.kokoko.server.ibutler.domain.kdt.resp.Trade;
import cc.kokoko.server.ibutler.persistence.KdtApiMapper;
import cc.kokoko.server.ibutler.service.AttachmentService;
import cc.kokoko.server.ibutler.service.util.AttachmentUtil;
/*     */ 
/*     */ @Service("kdtApiService")
/*     */ public class KdtApiService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private KdtApiMapper kdtApiMapper;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentService attachmentService;
/*     */ 
/*     */   public Map<String, Object> getKdtShowRecord(String bbrCard, Integer pagesize, Integer curPage)
/*     */   {
/*  48 */     Map paramMap = new HashMap();
/*  49 */     paramMap.put("bbrCard", bbrCard);
/*  50 */     paramMap.put("pagesize", pagesize);
/*  51 */     paramMap.put("start", curPage);
/*  52 */     List<KdtShowDTO> list = this.kdtApiMapper.getKdtShowRecord(paramMap);
/*  53 */     List list1 = new ArrayList();
/*  54 */     for (KdtShowDTO kdtShowDTO : list) {
/*  55 */       kdtShowDTO.setBuyer_nick(unicode2String(kdtShowDTO.getBuyer_nick()));
/*  56 */       kdtShowDTO.setBbrName(unicode2String(kdtShowDTO.getBbrName()));
/*  57 */       kdtShowDTO.setBbrMessage(unicode2String(kdtShowDTO.getBbrMessage()));
/*  58 */       list1.add(kdtShowDTO);
/*     */     }
/*  60 */     Long count = this.kdtApiMapper.getKdtShowCount(paramMap);
/*  61 */     paramMap = new HashMap();
/*  62 */     paramMap.put("list", list1);
/*  63 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/*  64 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public DatLifeTime getDatLifeTimeById(Long id)
/*     */   {
/*  74 */     Map paramMap = new HashMap();
/*  75 */     paramMap.put("id", id);
/*  76 */     DatLifeTime datLifeTime = this.kdtApiMapper.getDatLifeTimeById(paramMap);
/*  77 */     if (datLifeTime != null) {
/*  78 */       datLifeTime.setBuyer_nick(unicode2String(datLifeTime.getBuyer_nick()));
/*  79 */       datLifeTime.setBbrName(unicode2String(datLifeTime.getBbrName()));
/*  80 */       datLifeTime.setBbrMessage(unicode2String(datLifeTime.getBbrMessage()));
/*     */     }
/*  82 */     return datLifeTime;
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insert(KtoDTO ktoDTO, String kdtResult)
/*     */   {
/*  93 */     List<Trade> tradeList = ktoDTO.getResponse().getTrades();
/*  94 */     if (tradeList.size() > 0) {
/*  95 */       for (Trade trade : tradeList)
/*     */       {
/*  97 */         DatLifeTime lifeTime = new DatLifeTime();
/*     */ 
/*  99 */         Orders orders = (Orders)trade.getOrders().get(0);
/* 100 */         String bbrName = "";
/* 101 */         String bbrPhone = "";
/* 102 */         String bbrCard = "";
/* 103 */         String bbrImg = "";
/* 104 */         String bbrMessage = "";
/* 105 */         String bbrOccupation = "";
/*     */ 
/* 107 */         List<BuyerMessages> buyerMessagesList = orders.getBuyer_messages();
/* 108 */         for (BuyerMessages buyerMessages : buyerMessagesList) {
/* 109 */           String title = buyerMessages.getTitle().trim();
/* 110 */           if ("被保人姓名".equals(title))
/* 111 */             bbrName = buyerMessages.getContent();
/* 112 */           else if ("被保人手机".equals(title))
/* 113 */             bbrPhone = buyerMessages.getContent();
/* 114 */           else if ("被保身份证".equals(title))
/* 115 */             bbrCard = buyerMessages.getContent();
/* 116 */           else if ("爱情照片".equals(title))
/* 117 */             bbrImg = buyerMessages.getContent();
/* 118 */           else if ("爱情宣言".equals(title))
/* 119 */             bbrMessage = buyerMessages.getContent();
/* 120 */           else if ("被保人职业".equals(title)) {
/* 121 */             bbrOccupation = buyerMessages.getContent();
/*     */           }
/*     */         }
/*     */ 
/* 125 */         lifeTime.setTid(trade.getTid());
/* 126 */         lifeTime.setNum_iid(Long.valueOf(trade.getNum_iid()));
/* 127 */         lifeTime.setBuyer_nick(string2Unicode(trade.getBuyer_nick()));
/* 128 */         lifeTime.setStatus(checkStatus(trade.getStatus()));
/* 129 */         lifeTime.setCreated(StringUtil.isEmpty(trade.getCreated()) ? null : DateUtil.getDateFromStr(trade.getCreated(), "yyyy-MM-dd HH:mm:ss"));
/* 130 */         lifeTime.setPay_time(StringUtil.isEmpty(trade.getPay_time()) ? null : DateUtil.getDateFromStr(trade.getPay_time(), "yyyy-MM-dd HH:mm:ss"));
/* 131 */         lifeTime.setConsign_time(StringUtil.isEmpty(trade.getConsign_time()) ? null : DateUtil.getDateFromStr(trade.getConsign_time(), "yyyy-MM-dd HH:mm:ss"));
/* 132 */         lifeTime.setSign_time(StringUtil.isEmpty(trade.getSign_time()) ? null : DateUtil.getDateFromStr(trade.getSign_time(), "yyyy-MM-dd HH:mm:ss"));
/*     */ 
/* 134 */         String effectiveTime = StringUtil.isEmpty(trade.getPay_time()) ? null : trade.getPay_time();
/*     */ 
/* 136 */         lifeTime.setEffectiveTime(StringUtil.isEmpty(effectiveTime) ? null : DateUtil.addTenYear(effectiveTime, "yyyy-MM-dd HH:mm:ss"));
/* 137 */         lifeTime.setBbrName(string2Unicode(bbrName));
/* 138 */         lifeTime.setBbrPhone(bbrPhone);
/* 139 */         lifeTime.setBbrCard(bbrCard);
/* 140 */         lifeTime.setBbrImg(bbrImg);
/* 141 */         lifeTime.setBbrMessage(string2Unicode(bbrMessage));
/* 142 */         lifeTime.setBbrOccupation(bbrOccupation);
/* 143 */         lifeTime.setFlag(Boolean.valueOf(false));
/* 144 */         lifeTime.setDataSources("kdt");
/* 145 */         lifeTime.setSign(Boolean.valueOf(false));
/* 146 */         addDatLifeTime(lifeTime);
/*     */       }
/*     */     }
/*     */ 
/* 150 */     this.kdtApiMapper.deleteData();
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void insertDatLifeTime(Long uid, File titleImage, String titleImageFileName, DatLifeTime datLifeTime)
/*     */   {
/* 161 */     String thumbnailUrl = "";
/*     */ 
/* 163 */     if (!StringUtil.isEmpty(titleImageFileName))
/*     */     {
/* 165 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, titleImage, titleImageFileName);
/*     */ 
/* 167 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 168 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/* 170 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/* 172 */     datLifeTime.setStatus(new Byte("5"));
/* 173 */     Date date = DateUtil.getDateFromStr(DateUtil.getTimeStr(), "yyyy-MM-dd HH:mm:ss");
/* 174 */     datLifeTime.setCreated(date);
/* 175 */     datLifeTime.setPay_time(date);
/* 176 */     datLifeTime.setConsign_time(date);
/* 177 */     datLifeTime.setSign_time(date);
/* 178 */     datLifeTime.setEffectiveTime(DateUtil.addTenYear(DateUtil.getTimeStr(), "yyyy-MM-dd HH:mm:ss"));
/* 179 */     datLifeTime.setBbrImg(thumbnailUrl);
/* 180 */     datLifeTime.setFlag(Boolean.valueOf(false));
/* 181 */     datLifeTime.setDataSources("taobao");
/* 182 */     datLifeTime.setSign(Boolean.valueOf(true));
/* 183 */     datLifeTime.setBbrName(string2Unicode(datLifeTime.getBbrName()));
/* 184 */     datLifeTime.setBbrMessage(string2Unicode(datLifeTime.getBbrMessage()));
/* 185 */     datLifeTime.setBuyer_nick(string2Unicode(datLifeTime.getBuyer_nick()));
/* 186 */     datLifeTime.setTid(getRandom());
/* 187 */     this.kdtApiMapper.addDatLifeTime(datLifeTime);
/*     */   }
/*     */ 
/*     */   @Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
/*     */   public void updateDatLifeTime(Long uid, File titleImage, String titleImageFileName, DatLifeTime datLifeTime)
/*     */     throws Exception
/*     */   {
/* 197 */     DatLifeTime datLifeTime1 = getDatLifeTimeById(datLifeTime.getId());
/* 198 */     if (datLifeTime1 == null) {
/* 199 */       throw new Exception("数据不存在");
/*     */     }
/* 201 */     String thumbnailUrl = datLifeTime1.getBbrImg();
/*     */ 
/* 203 */     if (!StringUtil.isEmpty(titleImageFileName))
/*     */     {
/* 205 */       Attachment attachment = this.attachmentService.uploadAttachment(uid, titleImage, titleImageFileName);
/*     */ 
/* 207 */       if (!StringUtil.isEmpty(attachment.getFilePath())) {
/* 208 */         thumbnailUrl = AttachmentUtil.getUrlPrefix() + "/" + attachment.getFilePath();
/*     */       }
/* 210 */       this.attachmentService.createAttachment(attachment);
/*     */     }
/* 212 */     datLifeTime1.setBbrPhone(datLifeTime.getBbrPhone());
/* 213 */     datLifeTime1.setBbrCard(datLifeTime.getBbrCard());
/* 214 */     datLifeTime1.setBbrImg(thumbnailUrl);
/* 215 */     datLifeTime1.setSign(Boolean.valueOf(true));
/* 216 */     datLifeTime1.setBbrName(string2Unicode(datLifeTime.getBbrName()));
/* 217 */     datLifeTime1.setBbrMessage(string2Unicode(datLifeTime.getBbrMessage()));
/* 218 */     datLifeTime1.setBuyer_nick(string2Unicode(datLifeTime.getBuyer_nick()));
/* 219 */     this.kdtApiMapper.updateDatLifeTime(datLifeTime1);
/*     */   }
/*     */ 
/*     */   public void addDatLifeTime(DatLifeTime datLifeTime)
/*     */   {
/* 229 */     String tid = datLifeTime.getTid();
/*     */ 
/* 231 */     Map paramMap = new HashMap();
/* 232 */     paramMap.put("tid", tid);
/* 233 */     DatLifeTime datLifeTime1 = this.kdtApiMapper.getDatLifeTimeById(paramMap);
/*     */ 
/* 235 */     if (datLifeTime1 != null)
/*     */     {
/* 237 */       if (!datLifeTime1.getSign().booleanValue()) {
/* 238 */         datLifeTime.setId(datLifeTime1.getId());
/* 239 */         this.kdtApiMapper.updateDatLifeTime(datLifeTime);
/*     */       }
/*     */     }
/* 242 */     else this.kdtApiMapper.addDatLifeTime(datLifeTime);
/*     */   }
/*     */ 
/*     */   private Byte checkStatus(String status)
/*     */   {
/* 252 */     Byte result = null;
/*     */ 
/* 262 */     if ("TRADE_NO_CREATE_PAY".equals(status))
/* 263 */       result = new Byte("1");
/* 264 */     else if ("WAIT_BUYER_PAY".equals(status))
/* 265 */       result = new Byte("2");
/* 266 */     else if ("WAIT_SELLER_SEND_GOODS".equals(status))
/* 267 */       result = new Byte("3");
/* 268 */     else if ("WAIT_BUYER_CONFIRM_GOODS".equals(status))
/* 269 */       result = new Byte("4");
/* 270 */     else if ("TRADE_BUYER_SIGNED".equals(status))
/* 271 */       result = new Byte("5");
/* 272 */     else if ("TRADE_CLOSED".equals(status))
/* 273 */       result = new Byte("6");
/* 274 */     else if ("TRADE_CLOSED_BY_USER".equals(status)) {
/* 275 */       result = new Byte("7");
/*     */     }
/* 277 */     return result;
/*     */   }
/*     */ 
/*     */   public static String string2Unicode(String string)
/*     */   {
/* 287 */     StringBuffer unicode = new StringBuffer();
/* 288 */     for (int i = 0; i < string.length(); i++)
/*     */     {
/* 290 */       char c = string.charAt(i);
/*     */ 
/* 292 */       unicode.append("\\u" + Integer.toHexString(c));
/*     */     }
/* 294 */     return unicode.toString();
/*     */   }
/*     */ 
/*     */   public static String unicode2String(String unicode)
/*     */   {
/* 304 */     StringBuffer string = new StringBuffer();
/* 305 */     String[] hex = unicode.split("\\\\u");
/* 306 */     for (int i = 1; i < hex.length; i++)
/*     */     {
/* 308 */       int data = Integer.parseInt(hex[i], 16);
/*     */ 
/* 310 */       string.append((char)data);
/*     */     }
/* 312 */     return string.toString();
/*     */   }
/*     */ 
/*     */   public void delete(Long id)
/*     */   {
/* 321 */     this.kdtApiMapper.delete(id);
/*     */   }
/*     */ 
/*     */   public String getRandom()
/*     */   {
/* 330 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
/* 331 */     String name = format.format(new Date());
/* 332 */     Random random = new Random();
/* 333 */     for (int i = 0; i < 3; i++) {
/* 334 */       name = name + random.nextInt(9);
/*     */     }
/* 336 */     return "TB" + name;
/*     */   }
/*     */ 
/*     */   public void export(Sheet sheet)
/*     */   {
/* 346 */     int imageNumber = sheet.getNumberOfImages();
/* 347 */     for (int i = 0; i < imageNumber; i++)
/*     */     {
/* 349 */       Image image = sheet.getDrawing(i);
/* 350 */       int imageRow = (int)(image.getRow() + 0.5D);
/* 351 */       File imageFile = image.getImageFile();
/* 352 */       System.out.println(imageFile.getName() + " path : " + imageFile.getPath());
/*     */     }
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.kdt.KdtApiService
 * JD-Core Version:    0.6.0
 */