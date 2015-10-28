package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.SysDataAnalysis;
import java.util.List;
import java.util.Map;

public abstract interface SysDataAnalysisMapper
{
  public abstract void insert(SysDataAnalysis paramSysDataAnalysis);

  public abstract Long getAppDowns();

  public abstract Long getCardCount();

  public abstract Long getHouseCardCount();

  public abstract Long getHouseTradeCountByParam(Map<String, Object> paramMap);

  public abstract Long getMoneyLogCountByType(int paramInt);

  public abstract SysDataAnalysis getLastSysDataAnalysis();

  public abstract List<SysDataAnalysis> getTop10SysDataAnalysis();
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.SysDataAnalysisMapper
 * JD-Core Version:    0.6.0
 */