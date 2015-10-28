package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Shop;
import cc.kokoko.server.ibutler.domain.ShopCard;
import java.util.List;
import java.util.Map;

public abstract interface ShopMapper
{
  public abstract List<Shop> getShopList(Long paramLong);

  public abstract List<Shop> getLocalShopList(Long paramLong);

  public abstract Shop getShopById(Long paramLong);

  public abstract List<ShopCard> getShopCardList(Long paramLong);

  public abstract void updateShop(Shop paramShop);

  public abstract List<Shop> getAllShopByParam(Map<String, Object> paramMap);

  public abstract Long getShopCountByParam(Map<String, Object> paramMap);

  public abstract void insert(Shop paramShop);

  public abstract void delete(Long paramLong);

  public abstract Shop getShopByCommunityId(Long paramLong);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.ShopMapper
 * JD-Core Version:    0.6.0
 */