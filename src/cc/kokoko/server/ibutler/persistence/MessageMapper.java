package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Message;
import cc.kokoko.server.ibutler.domain.SysMessage;
import java.util.List;
import java.util.Map;

public abstract interface MessageMapper
{
  public abstract void createMessage(Message paramMessage);

  public abstract void addSysMessage(SysMessage paramSysMessage);

  public abstract List<Message> getMessageListByUid(Long paramLong);

  public abstract List<Message> getMessageList(Map<String, Object> paramMap);

  public abstract List<Message> getMessageList2(Map<String, Object> paramMap);

  public abstract Integer getMessageCount(Long paramLong);

  public abstract List<SysMessage> getLocalSysMessageList(Map<String, Object> paramMap);

  public abstract int getMessageCountByUid(Long paramLong);

  public abstract List<Message> getLatestMessage(Long paramLong);

  public abstract List<Message> getMessageRecordByParam(Map<String, Object> paramMap);

  public abstract Long getMessageCountByParam(Map<String, Object> paramMap);

  public abstract Message getMessageById(Long paramLong);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.MessageMapper
 * JD-Core Version:    0.6.0
 */