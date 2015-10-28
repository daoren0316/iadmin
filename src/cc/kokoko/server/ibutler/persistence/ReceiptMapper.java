package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.ReceiptAccount;
import cc.kokoko.server.ibutler.domain.dto.ReceiptAccountDTO;
import java.util.List;
import java.util.Map;

public abstract interface ReceiptMapper
{
  public abstract List<ReceiptAccountDTO> getReceiptList(Map<String, Object> paramMap);

  public abstract ReceiptAccount getReceiptDetail(Long paramLong);

  public abstract void createReceiptAccount(ReceiptAccount paramReceiptAccount);

  public abstract void deleteReceiptAccountByTradeId(String paramString);

  public abstract void update(Map<String, Object> paramMap);

  public abstract Double getReceiptMoneyByParam(Map<String, Object> paramMap);

  public abstract List<ReceiptAccount> getRecepitAccountRecord(Map<String, Object> paramMap);

  public abstract Long getRecepitAccountCount(Map<String, Object> paramMap);

  public abstract Double getRecepitAccountTotal(Map<String, Object> paramMap);

  public abstract Double getRecepitTotal(Map<String, Object> paramMap);

  public abstract Double getReceiptTotalByParam(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.ReceiptMapper
 * JD-Core Version:    0.6.0
 */