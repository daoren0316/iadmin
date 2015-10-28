package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Order;
import java.util.List;
import java.util.Map;

public abstract interface OrderMapper
{
  public abstract List<Map<String, Object>> getOrderRecord(Map<String, Object> paramMap);

  public abstract Long getOrderCount(Map<String, Object> paramMap);

  public abstract void changeOrderStatus(String paramString);

  public abstract Order getOrderById(String paramString);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.OrderMapper
 * JD-Core Version:    0.6.0
 */