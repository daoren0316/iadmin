package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.RDOrder;

public abstract interface TradeMapper
{
  public abstract Long getShopIdByPosNo(String paramString);

  public abstract Long getShopIdByShopNo(String paramString);

  public abstract void createRDOrder(RDOrder paramRDOrder);

  public abstract Integer getShopDiscountByShopId(Long paramLong);

  public abstract RDOrder getRDOrderByTradeId(String paramString);

  public abstract void updateRDOrder(RDOrder paramRDOrder);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.TradeMapper
 * JD-Core Version:    0.6.0
 */