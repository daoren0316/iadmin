/*     */ package cc.kokoko.server.ibutler.service;
/*     */ 
/*     */ import cc.kokoko.server.ibutler.domain.ReceiptAccount;
/*     */ import cc.kokoko.server.ibutler.domain.dto.ReceiptAccountDTO;
/*     */ import cc.kokoko.server.ibutler.domain.dto.RemittanceDTO;
/*     */ import cc.kokoko.server.ibutler.persistence.ReceiptMapper;
/*     */ import cc.kokoko.server.ibutler.persistence.RemittanceMapper;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("receiptService")
/*     */ public class ReceiptService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ReceiptMapper receiptMapper;
/*     */ 
/*     */   @Autowired
/*     */   private RemittanceMapper remittanceMapper;
/*     */ 
/*     */   public List<ReceiptAccountDTO> getReceiptList(Long shopId, Long flag, String startTime, String endTime, Integer pagesize, Integer curPage)
/*     */   {
/*  38 */     Map paramMap = new HashMap();
/*  39 */     if ((pagesize == null) || (pagesize.intValue() < 1)) {
/*  40 */       pagesize = Integer.valueOf(20);
/*     */     }
/*  42 */     if ((curPage == null) || (curPage.intValue() < 1)) {
/*  43 */       curPage = Integer.valueOf(1);
/*     */     }
/*  45 */     int start = pagesize.intValue() * (curPage.intValue() - 1);
/*  46 */     paramMap.put("pagesize", pagesize);
/*  47 */     paramMap.put("start", Integer.valueOf(start));
/*  48 */     paramMap.put("shopId", shopId);
/*  49 */     paramMap.put("flag", flag);
/*     */ 
/*  51 */     paramMap.put("startTime", startTime);
/*  52 */     paramMap.put("endTime", endTime);
/*  53 */     return this.receiptMapper.getReceiptList(paramMap);
/*     */   }
/*     */ 
/*     */   public List<RemittanceDTO> getRemittanceRecord(Long shopId, Integer pagesize, Integer curPage)
/*     */   {
/*  65 */     Map paramMap = new HashMap();
/*  66 */     if ((pagesize == null) || (pagesize.intValue() < 1)) {
/*  67 */       pagesize = Integer.valueOf(20);
/*     */     }
/*  69 */     if ((curPage == null) || (curPage.intValue() < 1)) {
/*  70 */       curPage = Integer.valueOf(1);
/*     */     }
/*  72 */     int start = pagesize.intValue() * (curPage.intValue() - 1);
/*  73 */     paramMap.put("shopId", shopId);
/*  74 */     paramMap.put("pagesize", pagesize);
/*  75 */     paramMap.put("start", Integer.valueOf(start));
/*     */ 
/*  77 */     return this.remittanceMapper.getRemittanceRecordDTO(paramMap);
/*     */   }
/*     */ 
/*     */   public ReceiptAccount getReceiptDetail(Long id)
/*     */   {
/*  87 */     return this.receiptMapper.getReceiptDetail(id);
/*     */   }
/*     */ 
/*     */   public void createReceiptAccount(Long shopId, double money, String publicAddress, String tradeId)
/*     */   {
/*  99 */     ReceiptAccount receiptAccount = new ReceiptAccount();
/* 100 */     receiptAccount.setTradeId(tradeId);
/* 101 */     receiptAccount.setFlag(Long.valueOf(1L));
/* 102 */     receiptAccount.setMoney(Double.valueOf(money));
/* 103 */     receiptAccount.setPublicAddress(publicAddress);
/* 104 */     receiptAccount.setShopId(shopId);
/* 105 */     receiptAccount.setSurplusMoney(Double.valueOf(money));
/* 106 */     receiptAccount.setReceiptMoney(Double.valueOf(0.0D));
/* 107 */     receiptAccount.setNote("");
/* 108 */     this.receiptMapper.createReceiptAccount(receiptAccount);
/*     */   }
/*     */ 
/*     */   public void deleteReceiptAccountByTradeId(String tradeId)
/*     */   {
/* 117 */     this.receiptMapper.deleteReceiptAccountByTradeId(tradeId);
/*     */   }
/*     */ 
/*     */   public List<ReceiptAccount> getRecepitAccountRecord(Long communityId, Long flag, Long shopId, String starttime, String endtime)
/*     */   {
/* 131 */     Map paramMap = new HashMap();
/* 132 */     paramMap.put("communityId", communityId);
/* 133 */     paramMap.put("flag", flag);
/* 134 */     paramMap.put("shopId", shopId);
/* 135 */     paramMap.put("starttime", starttime);
/* 136 */     paramMap.put("endtime", endtime);
/*     */ 
/* 138 */     return this.receiptMapper.getRecepitAccountRecord(paramMap);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getRecepitAccountRecord(Long communityId, Long flag, Long shopId, String starttime, String endtime, Long houseId, Integer pagesize, Integer curPage)
/*     */   {
/* 155 */     Map paramMap = new HashMap();
/* 156 */     paramMap.put("communityId", communityId);
/* 157 */     paramMap.put("flag", flag);
/* 158 */     paramMap.put("shopId", shopId);
/* 159 */     paramMap.put("houseId", houseId);
/* 160 */     paramMap.put("pagesize", pagesize);
/* 161 */     paramMap.put("start", curPage);
/* 162 */     paramMap.put("starttime", starttime);
/* 163 */     paramMap.put("endtime", endtime);
/*     */ 
/* 165 */     List list = this.receiptMapper.getRecepitAccountRecord(paramMap);
/* 166 */     Long count = this.receiptMapper.getRecepitAccountCount(paramMap);
/*     */ 
/* 168 */     paramMap = new HashMap();
/* 169 */     paramMap.put("list", list);
/* 170 */     paramMap.put("count", Long.valueOf(count == null ? 0L : count.longValue()));
/*     */ 
/* 172 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public Double getRecepitTotal(Long shopId, Long flag, String starttime, String endtime)
/*     */   {
/* 185 */     Map paramMap = new HashMap();
/* 186 */     paramMap.put("shopId", shopId);
/* 187 */     paramMap.put("flag", flag);
/* 188 */     paramMap.put("starttime", starttime);
/* 189 */     paramMap.put("endtime", endtime);
/* 190 */     Double total = this.receiptMapper.getRecepitTotal(paramMap);
/* 191 */     return Double.valueOf(total != null ? total.doubleValue() : 0.0D);
/*     */   }
/*     */ 
/*     */   public Double getRemittanceTotal(Long shopId)
/*     */   {
/* 201 */     Map paramMap = new HashMap();
/* 202 */     paramMap.put("shopId", shopId);
/* 203 */     Double total = this.remittanceMapper.getRemittanceTotal(paramMap);
/* 204 */     return Double.valueOf(total != null ? total.doubleValue() : 0.0D);
/*     */   }
/*     */ }

/* Location:           H:\ibu\ibutler-service-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.service.ReceiptService
 * JD-Core Version:    0.6.0
 */