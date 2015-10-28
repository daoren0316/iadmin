package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.OrderAccount;
import cc.kokoko.server.ibutler.domain.SysMoney;
import cc.kokoko.server.ibutler.domain.SysMoneyLog;
import java.util.List;
import java.util.Map;

public abstract interface SysMoneyLogMapper
{
  public abstract void insert(SysMoney paramSysMoney);

  public abstract void update(SysMoney paramSysMoney);

  public abstract SysMoney loadSysMoney();

  public abstract void insertMoneyLog(SysMoneyLog paramSysMoneyLog);

  public abstract List<SysMoneyLog> getSysMoneyRecord(Map<String, Object> paramMap);

  public abstract Long getSysMoneyCount(Map<String, Object> paramMap);

  public abstract void insertOrderAccount(OrderAccount paramOrderAccount);

  public abstract OrderAccount getOrderAccountByOrderId(String paramString);

  public abstract void updateOrderStatus(Map<String, Object> paramMap);

  public abstract List<OrderAccount> getOrderAccountRecord(Map<String, Object> paramMap);

  public abstract Long getOrderAccountCount(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.SysMoneyLogMapper
 * JD-Core Version:    0.6.0
 */