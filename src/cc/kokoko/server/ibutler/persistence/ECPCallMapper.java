package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.CallLog;
import cc.kokoko.server.ibutler.domain.ECPCall;
import java.util.List;
import java.util.Map;

public abstract interface ECPCallMapper
{
  public abstract void addECPCall(ECPCall paramECPCall);

  public abstract void addECPCallBuffer(ECPCall paramECPCall);

  public abstract void addCallLog(Map<String, Object> paramMap);

  public abstract Map<String, Object> getCallLogById(Long paramLong);

  public abstract void updateCallLogById(Long paramLong);

  public abstract List<CallLog> getCallLogList(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.ECPCallMapper
 * JD-Core Version:    0.6.0
 */