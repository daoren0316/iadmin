package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Commodity;
import cc.kokoko.server.ibutler.domain.CommodityUser;
import java.util.List;
import java.util.Map;

public abstract interface CommodityMapper
{
  public abstract List<Commodity> getCommodityList(Long paramLong);

  public abstract Commodity getCommodityById(Long paramLong);

  public abstract List<CommodityUser> getCommodityUserList(Long paramLong);

  public abstract void addCommodityUser(CommodityUser paramCommodityUser);

  public abstract void updateCommodityCount(Map<String, Object> paramMap);

  public abstract List<Commodity> getLocalCommodityList(Long paramLong);

  public abstract void createCommodity(Commodity paramCommodity);

  public abstract void delCommodityById(Long paramLong);

  public abstract List<Commodity> getCommodityRecord(Map<String, Object> paramMap);

  public abstract Long getCommodityCount(Map<String, Object> paramMap);

  public abstract void update(Commodity paramCommodity);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.CommodityMapper
 * JD-Core Version:    0.6.0
 */