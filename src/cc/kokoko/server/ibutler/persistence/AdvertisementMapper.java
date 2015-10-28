package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Advertise;
import java.util.List;
import java.util.Map;

public abstract interface AdvertisementMapper
{
  public abstract List<Advertise> getAdvertiseRecord(Map<String, Object> paramMap);

  public abstract Long getAdvertiseCount(Map<String, Object> paramMap);

  public abstract void insert(Advertise paramAdvertise);

  public abstract void update(Advertise paramAdvertise);

  public abstract Advertise getAdvertiseById(Long paramLong);

  public abstract void delete(Long paramLong);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.AdvertisementMapper
 * JD-Core Version:    0.6.0
 */