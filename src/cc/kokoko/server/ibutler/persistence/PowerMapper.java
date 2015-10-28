package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Power;
import cc.kokoko.server.ibutler.domain.RolePower;
import java.util.List;
import java.util.Map;

public abstract interface PowerMapper
{
  public abstract List<Power> getAllLeftPower(Map<String, Object> paramMap);

  public abstract List<Power> getAllPower(Map<String, Object> paramMap);

  public abstract List<Power> getPowerRecord(Map<String, Object> paramMap);

  public abstract Long getPowerCount(Map<String, Object> paramMap);

  public abstract RolePower getRolePowerByParam(Map<String, Object> paramMap);

  public abstract void insert(Power paramPower);

  public abstract void update(Power paramPower);

  public abstract Power getPowerById(Long paramLong);

  public abstract List<Power> loadTodoPower(Map<String, Object> paramMap);

  public abstract void delete(Long paramLong);

  public abstract void deleteRolePower(Long paramLong);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.PowerMapper
 * JD-Core Version:    0.6.0
 */