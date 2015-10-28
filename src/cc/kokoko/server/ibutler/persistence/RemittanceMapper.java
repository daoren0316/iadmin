package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Remittance;
import cc.kokoko.server.ibutler.domain.dto.RemittanceDTO;
import java.util.List;
import java.util.Map;

public abstract interface RemittanceMapper
{
  public abstract void insert(Remittance paramRemittance);

  public abstract List<Remittance> getRemittanceRecord(Map<String, Object> paramMap);

  public abstract Long getRemittanceCount(Map<String, Object> paramMap);

  public abstract List<RemittanceDTO> getRemittanceRecordDTO(Map<String, Object> paramMap);

  public abstract Double getRemittanceTotal(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.RemittanceMapper
 * JD-Core Version:    0.6.0
 */