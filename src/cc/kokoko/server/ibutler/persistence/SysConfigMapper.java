package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.SysConfig;
import java.util.List;

public abstract interface SysConfigMapper
{
  public abstract List<SysConfig> getSysConfigList();
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.SysConfigMapper
 * JD-Core Version:    0.6.0
 */