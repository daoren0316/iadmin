package cc.kokoko.server.ibutler.persistence;

public abstract interface UserLogMapper
{
  public abstract String getLastCheckinDateByUid(Long paramLong);

  public abstract void deleteCheckinLog(Long paramLong);

  public abstract void saveCheckinLog(Long paramLong);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.UserLogMapper
 * JD-Core Version:    0.6.0
 */