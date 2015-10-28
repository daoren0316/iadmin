package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Role;
import cc.kokoko.server.ibutler.domain.RolePower;
import java.util.List;
import java.util.Map;

public abstract interface RoleMapper
{
  public abstract List<Role> getAllRole();

  public abstract void insertRole(Role paramRole);

  public abstract void updateRole(Role paramRole);

  public abstract Role getRoleById(Long paramLong);

  public abstract List<Role> getRoleRecord(Map<String, Object> paramMap);

  public abstract Long getRoleCount(Map<String, Object> paramMap);

  public abstract Role checkRole(Map<String, Object> paramMap);

  public abstract void insertRolePower(RolePower paramRolePower);

  public abstract List<RolePower> getRolePowerByRoleId(Long paramLong);

  public abstract void deleteRolePower(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.RoleMapper
 * JD-Core Version:    0.6.0
 */