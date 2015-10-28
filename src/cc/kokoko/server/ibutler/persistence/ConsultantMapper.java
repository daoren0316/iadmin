package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Consultant;
import java.util.List;
import java.util.Map;

public abstract interface ConsultantMapper
{
  public abstract void update(Consultant paramConsultant);

  public abstract Consultant getConsultantById(Long paramLong);

  public abstract List<Consultant> getConsultantRecord(Map<String, Object> paramMap);

  public abstract Long getConsultantCount(Map<String, Object> paramMap);

  public abstract void insert(Consultant paramConsultant);

  public abstract void delete(Long paramLong);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.ConsultantMapper
 * JD-Core Version:    0.6.0
 */