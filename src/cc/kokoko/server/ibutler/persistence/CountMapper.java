package cc.kokoko.server.ibutler.persistence;

import java.util.List;
import java.util.Map;

public abstract interface CountMapper
{
  public abstract List<Long> getAllAccedUserList();

  public abstract List<Long> getAllAccedUserListByWeek();

  public abstract Integer getUserAccedCount(Map<String, Object> paramMap);

  public abstract List<Integer> getUserAccedCountByWeek(Map<String, Object> paramMap);

  public abstract void updateUserLevel(Long paramLong, int paramInt);

  public abstract String getYesterdayFromMySQL();

  public abstract void createUserAccedCount(Map<String, Object> paramMap);

  public abstract void addUserAccedCount(Map<String, Object> paramMap);

  public abstract List<Map<String, Object>> getAccedList(Map<String, String> paramMap);

  public abstract void deleteUserLastWeek(Long paramLong);

  public abstract void createUserLastWeek(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.CountMapper
 * JD-Core Version:    0.6.0
 */