package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Activity;
import java.util.List;
import java.util.Map;

public abstract interface ActivityMapper
{
  public abstract List<Activity> getActivityList(Long paramLong);

  public abstract List<Activity> getLocalActivityList(Long paramLong);

  public abstract Activity getActivityById(Long paramLong);

  public abstract void createActivity(Activity paramActivity);

  public abstract void delActivityById(Long paramLong);

  public abstract List<Activity> getActivityRecordByParam(Map<String, Object> paramMap);

  public abstract Long getActivityCountByParam(Map<String, Object> paramMap);

  public abstract void updateActivity(Activity paramActivity);

  public abstract void updateActViewCount(Activity paramActivity);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.ActivityMapper
 * JD-Core Version:    0.6.0
 */