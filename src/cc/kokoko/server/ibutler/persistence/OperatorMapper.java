package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Operator;
import cc.kokoko.server.ibutler.domain.User;
import java.util.List;
import java.util.Map;

public abstract interface OperatorMapper
{
  public abstract List<Operator> getOperatorRecord(Map<String, Object> paramMap);

  public abstract Long getOperatorCount(Map<String, Object> paramMap);

  public abstract void insert(Operator paramOperator);

  public abstract Operator getOperatorById(Long paramLong);

  public abstract void update(Operator paramOperator);

  public abstract void delete(Long paramLong);

  public abstract User checkOperatorByName(String paramString);

  public abstract Operator getOperatorByUnamePassword(Map<String, Object> paramMap);

  public abstract void updateOperator(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.OperatorMapper
 * JD-Core Version:    0.6.0
 */