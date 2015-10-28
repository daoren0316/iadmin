package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Charge;
import cc.kokoko.server.ibutler.domain.FamilyMember;
import cc.kokoko.server.ibutler.domain.MoneyAccount;
import cc.kokoko.server.ibutler.domain.MoneyLog;
import cc.kokoko.server.ibutler.domain.Order;
import cc.kokoko.server.ibutler.domain.Trade;
import cc.kokoko.server.ibutler.domain.dto.ConsumeLogDTO;
import cc.kokoko.server.ibutler.domain.dto.MoneyLogDTO;
import cc.kokoko.server.ibutler.domain.dto.OwnerWalletDTO;
import java.util.List;
import java.util.Map;

public abstract interface MoneyLogMapper
{
  public abstract MoneyLog getMoneyLogById(Long paramLong);

  public abstract Trade getTradeById(Long paramLong);

  public abstract Charge getChargeById(Long paramLong);

  public abstract List<Trade> getTradeList(Long paramLong);

  public abstract List<Charge> getChargeList(Long paramLong);

  public abstract List<Charge> getChargeListByHouseId(Long paramLong);

  public abstract void createMoneyLog(MoneyLog paramMoneyLog);

  public abstract Double getUserBalance(Long paramLong);

  public abstract List<FamilyMember> getFamilyMemberList(Map<String, Object> paramMap);

  public abstract List<FamilyMember> getSearchFamilyMemberList(Map<String, Object> paramMap);

  public abstract void updateFamilyMember(FamilyMember paramFamilyMember);

  public abstract FamilyMember getFamilyMember(Map<String, Object> paramMap);

  public abstract List<Order> getOrderList(Map<String, Object> paramMap);

  public abstract Order getOrderById(String paramString);

  public abstract void updateOrder(Order paramOrder);

  public abstract void createOrder(Order paramOrder);

  public abstract MoneyAccount getMoneyAccount(Long paramLong);

  public abstract void createMoneyAccount(MoneyAccount paramMoneyAccount);

  public abstract void changeAccountBalance(Map<String, Object> paramMap);

  public abstract void changeAccountPassword(Map<String, Object> paramMap);

  public abstract List<MoneyLogDTO> getMoneyRecord(Map<String, Object> paramMap);

  public abstract List<MoneyLogDTO> getHistoryRecord(Map<String, Object> paramMap);

  public abstract Double getMoneyTotal(Map<String, Object> paramMap);

  public abstract List<MoneyLogDTO> getMoneyRecordByParam(Map<String, Object> paramMap);

  public abstract Long getMoneyCountByParam(Map<String, Object> paramMap);

  public abstract void updateTotalSavedAmount(Map<String, Object> paramMap);

  public abstract Double getTotalSavedAmount(Long paramLong);

  public abstract List<ConsumeLogDTO> getConsumeRecordByParam(Map<String, Object> paramMap);

  public abstract Long getConsumeCountByParam(Map<String, Object> paramMap);

  public abstract Double getMoneyTotalByParam(Map<String, Object> paramMap);

  public abstract List<OwnerWalletDTO> getOwnerWalletRecord(Map<String, Object> paramMap);

  public abstract Long getOwnerWalletCount(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.MoneyLogMapper
 * JD-Core Version:    0.6.0
 */