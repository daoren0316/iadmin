package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Car;
import java.util.List;

public abstract interface CarMapper
{
  public abstract Car getCarByCarId(Long paramLong);

  public abstract List<Car> getCarList(Long paramLong);

  public abstract void createCar(Car paramCar);

  public abstract void deleteCar(Long paramLong);

  public abstract void updateCar(Car paramCar);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.CarMapper
 * JD-Core Version:    0.6.0
 */