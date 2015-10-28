package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.SysInfoLog;
import java.util.List;

public abstract interface SysInfoLogMapper
{
  public abstract void insert(SysInfoLog paramSysInfoLog);

  public abstract SysInfoLog getLastSysInfoLog();

  public abstract List<SysInfoLog> getTop10SysInfoLog();
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.SysInfoLogMapper
 * JD-Core Version:    0.6.0
 */