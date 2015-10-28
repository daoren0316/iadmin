package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.UserRole;
import cc.kokoko.server.ibutler.domain.UserType;
import java.util.List;
import java.util.Map;

public abstract interface UserTypeMapper
{
  public abstract void insert(UserType paramUserType);

  public abstract void update(UserType paramUserType);

  public abstract UserType getUserTypeById(Map<String, Object> paramMap);

  public abstract List<UserType> getUserTypeRecord(Map<String, Object> paramMap);

  public abstract Long getUserTypeCount(Map<String, Object> paramMap);

  public abstract void insertUserRole(UserRole paramUserRole);

  public abstract UserType checkTypeName(Map<String, Object> paramMap);

  public abstract UserType checkTypeFlag(Map<String, Object> paramMap);

  public abstract List<UserRole> getUserRoleByParam(Map<String, Object> paramMap);

  public abstract void deleteUserRole(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.UserTypeMapper
 * JD-Core Version:    0.6.0
 */