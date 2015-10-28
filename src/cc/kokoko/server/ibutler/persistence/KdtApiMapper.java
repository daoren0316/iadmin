package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.kdt.DatLifeTime;
import cc.kokoko.server.ibutler.domain.kdt.dto.KdtShowDTO;
import java.util.List;
import java.util.Map;

public abstract interface KdtApiMapper
{
  public abstract void addDatLifeTime(DatLifeTime paramDatLifeTime);

  public abstract DatLifeTime getDatLifeTimeById(Map<String, Object> paramMap);

  public abstract void updateDatLifeTime(DatLifeTime paramDatLifeTime);

  public abstract List<KdtShowDTO> getKdtShowRecord(Map<String, Object> paramMap);

  public abstract Long getKdtShowCount(Map<String, Object> paramMap);

  public abstract void delete(Long paramLong);

  public abstract void deleteData();
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.KdtApiMapper
 * JD-Core Version:    0.6.0
 */