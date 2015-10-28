package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.ShopLocation;
import java.util.List;
import java.util.Map;

public abstract interface ShopLocationMapper
{
  public abstract List<ShopLocation> getShopLocationList(Map<String, Object> paramMap);

  public abstract Long getShopLocationCount(Map<String, Object> paramMap);

  public abstract ShopLocation getShopLocationByParam(Map<String, Object> paramMap);

  public abstract void insert(ShopLocation paramShopLocation);

  public abstract void delete(Long paramLong);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.ShopLocationMapper
 * JD-Core Version:    0.6.0
 */