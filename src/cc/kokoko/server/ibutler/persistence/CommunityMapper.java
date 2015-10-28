package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Building;
import cc.kokoko.server.ibutler.domain.City;
import cc.kokoko.server.ibutler.domain.Community;
import cc.kokoko.server.ibutler.domain.House;
import cc.kokoko.server.ibutler.domain.Unit;
import java.util.List;
import java.util.Map;

public abstract interface CommunityMapper
{
  public abstract List<City> getAllCity();

  public abstract List<Community> getAllCommunity();

  public abstract List<Community> getAllCommunityById(int paramInt);

  public abstract List<Building> getAllBuildingById(Long paramLong);

  public abstract List<Unit> getAllUnitById(Long paramLong);

  public abstract List<House> getAllHouseById(Long paramLong);

  public abstract void insertCommunity(Community paramCommunity);

  public abstract void insertBuilding(Building paramBuilding);

  public abstract void insertUnit(Unit paramUnit);

  public abstract void insertHouse(House paramHouse);

  public abstract List<Community> getCommunityRecord(Map<String, Object> paramMap);

  public abstract Long getCommunityCount(Map<String, Object> paramMap);

  public abstract List<Building> getBuildingRecord(Map<String, Object> paramMap);

  public abstract Long getBuildingCount(Map<String, Object> paramMap);

  public abstract List<Unit> getUnitReocrd(Map<String, Object> paramMap);

  public abstract Long getUnitCount(Map<String, Object> paramMap);

  public abstract List<House> getHouseReocrd(Map<String, Object> paramMap);

  public abstract Long getHouseCount(Map<String, Object> paramMap);

  public abstract Community checkCommunity(Map<String, Object> paramMap);

  public abstract Building checkBuilding(Map<String, Object> paramMap);

  public abstract Unit checkUnit(Map<String, Object> paramMap);

  public abstract House checkHouse(Map<String, Object> paramMap);

  public abstract Unit getUnitById(Long paramLong);

  public abstract List<Building> getBuildingByCId(Long paramLong);

  public abstract List<Unit> getUnitByBId(Long paramLong);

  public abstract List<House> getHouseByUId(Long paramLong);

  public abstract String getUserAddrByHouseId(Long paramLong);

  public abstract String getCommunityName(Long paramLong);

  public abstract Community getCommunityByHouseId(Long paramLong);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.CommunityMapper
 * JD-Core Version:    0.6.0
 */