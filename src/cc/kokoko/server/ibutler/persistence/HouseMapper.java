package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Building;
import cc.kokoko.server.ibutler.domain.City;
import cc.kokoko.server.ibutler.domain.Community;
import cc.kokoko.server.ibutler.domain.House;
import cc.kokoko.server.ibutler.domain.Unit;
import java.util.List;
import java.util.Map;

public abstract interface HouseMapper
{
  public abstract List<City> getCityList(String paramString);

  public abstract City getCityById(Integer paramInteger);

  public abstract List<Community> getCommunityList(Integer paramInteger);

  public abstract Community getCommunityById(Long paramLong);

  public abstract List<Building> getBuildingList(Long paramLong);

  public abstract Building getBuildingById(Long paramLong);

  public abstract List<Unit> getUnitList(Long paramLong);

  public abstract Unit getUnitById(Long paramLong);

  public abstract List<House> getHouseList(Long paramLong);

  public abstract House getHouseById(Long paramLong);

  public abstract Map<String, Object> getUserHouse(Map<String, Long> paramMap);

  public abstract void createUserHouse(Map<String, Object> paramMap);

  public abstract void delUserHouseByPkId(String paramString);

  public abstract List<House> getUserHouseList(Long paramLong);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.HouseMapper
 * JD-Core Version:    0.6.0
 */