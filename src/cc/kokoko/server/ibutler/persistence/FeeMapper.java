package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.CarFee;
import cc.kokoko.server.ibutler.domain.Fee;
import cc.kokoko.server.ibutler.domain.PropertyFee;
import java.util.List;

public abstract interface FeeMapper
{
  public abstract Double getFeeAmountByFeeId(Long paramLong);

  public abstract List<CarFee> getCarFeeList(Long paramLong);

  public abstract List<PropertyFee> getPropertyFeeList(Long paramLong);

  public abstract void updateFeeStatus(Long paramLong);

  public abstract Fee getFeeByFeeId(Long paramLong);

  public abstract void insertFee(Fee paramFee);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.FeeMapper
 * JD-Core Version:    0.6.0
 */