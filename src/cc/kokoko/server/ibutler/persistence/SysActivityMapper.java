package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.SysActivityConfig;
import cc.kokoko.server.ibutler.domain.SysActivityLog;
import cc.kokoko.server.ibutler.domain.kdt.DatProtect;
import java.util.List;
import java.util.Map;

public abstract interface SysActivityMapper
{
  public abstract SysActivityConfig loadSysActivity(Map<String, Object> paramMap);

  public abstract Double getBackMoneyTotal();

  public abstract Double getBackMoneyTotalByHouseId(Long paramLong);

  public abstract void insertSysActivityLog(SysActivityLog paramSysActivityLog);

  public abstract void updateSysActivityFlag(Map<String, Object> paramMap);

  public abstract List<SysActivityConfig> getSysActivityRecord(Map<String, Object> paramMap);

  public abstract Long getSysActivityCount(Map<String, Object> paramMap);

  public abstract void insert(SysActivityConfig paramSysActivityConfig);

  public abstract SysActivityConfig getSysActivityConfigById(Long paramLong);

  public abstract List<SysActivityLog> getSysActivityLogRecord(Map<String, Object> paramMap);

  public abstract Long getSysActivityLogCount(Map<String, Object> paramMap);

  public abstract void insertDatProtect(DatProtect paramDatProtect);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.SysActivityMapper
 * JD-Core Version:    0.6.0
 */