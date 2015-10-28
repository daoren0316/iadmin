package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.BDPushUser;
import cc.kokoko.server.ibutler.domain.Favorite;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.domain.UserDevice;
import cc.kokoko.server.ibutler.domain.dto.UserDTO;
import cc.kokoko.server.ibutler.domain.xmpp.OpenFireUser;
import cc.kokoko.server.ibutler.domain.xmpp.XMPPOnlineUser;
import java.util.List;
import java.util.Map;

public abstract interface UserMapper
{
  public abstract void addUser(User paramUser);

  public abstract int registerUser(User paramUser);

  public abstract User getUserByUid(Long paramLong);

  public abstract User getUserByEmail(String paramString);

  public abstract void updateUser(User paramUser);

  public abstract void updateUserToken(User paramUser);

  public abstract void updateUserCoins(Map<String, Object> paramMap);

  public abstract void updateAnswerCount(Long paramLong);

  public abstract void updateQuestionCount(Long paramLong);

  public abstract void updateAppreciationCount(Long paramLong);

  public abstract void changePassword(User paramUser);

  public abstract void createXMPPUser(OpenFireUser paramOpenFireUser);

  public abstract List<User> getAllUserList();

  public abstract List<User> getIOSUserList();

  public abstract OpenFireUser getOpenFireUser(String paramString);

  public abstract List<User> getUserList(Map<String, Object> paramMap);

  public abstract List<User> getUserListOrderByYesterday(Map<String, Object> paramMap);

  public abstract List<XMPPOnlineUser> getXMPPOnlineUserList();

  public abstract List<User> getCounsellorList(Map<String, Object> paramMap);

  public abstract void addReport(Map<String, Object> paramMap);

  public abstract List<UserDevice> getUserDeviceList(Long paramLong);

  public abstract UserDevice getUserDevice(UserDevice paramUserDevice);

  public abstract void addUserDevice(UserDevice paramUserDevice);

  public abstract void deleteUserDevice(UserDevice paramUserDevice);

  public abstract void addToDeviceBlacklist(String paramString);

  public abstract void deleteFromDeviceBlacklist(String paramString);

  public abstract String getDeviceIdFromDeviceBlacklist(String paramString);

  public abstract User getUserByPhoneNumber(String paramString);

  public abstract User getUserByNickname(String paramString);

  public abstract void addFavorite(Favorite paramFavorite);

  public abstract void delFavorite(Favorite paramFavorite);

  public abstract Favorite getFavorite(Favorite paramFavorite);

  public abstract List<Favorite> getFavoritelist(Long paramLong);

  public abstract Long getUserCommunityId(Long paramLong);

  public abstract void createBDPushUser(BDPushUser paramBDPushUser);

  public abstract void updateBDPushUser(BDPushUser paramBDPushUser);

  public abstract BDPushUser getBDPushUserByUid(Long paramLong);

  public abstract Integer getUserScore(Long paramLong);

  public abstract Byte getUserType(Long paramLong);

  public abstract String getShopPhoneNumber(Long paramLong);

  public abstract String getUserPhoneNumber(Long paramLong);

  public abstract Long getUidByRdCardNo(String paramString);

  public abstract User getUserByUsername(String paramString);

  public abstract User getUserByUnamePassword(Map<String, Object> paramMap);

  public abstract Double getUserMoney(Long paramLong);

  public abstract void updateUserMoney(Map<String, Object> paramMap);

  public abstract List<User> getUserByHouseId(Long paramLong);

  public abstract User getOwnerUserByHouseId(Long paramLong);

  public abstract UserDTO getHouseByPhoneNumber(Map<String, Object> paramMap);

  public abstract User checkUserByName(String paramString);

  public abstract User getUserByRdCardNo(String paramString);

  public abstract List<User> getUserByType(Long paramLong);

  public abstract List<User> getOwnerRecordByParam(Map<String, Object> paramMap);

  public abstract Long getOwnerCountByParam(Map<String, Object> paramMap);

  public abstract User getSiteUserByCommunityId(Long paramLong);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.UserMapper
 * JD-Core Version:    0.6.0
 */