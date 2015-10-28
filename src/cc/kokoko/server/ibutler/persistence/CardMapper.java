package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Card;
import java.util.List;
import java.util.Map;

public abstract interface CardMapper
{
  public abstract Map<String, Object> getCardByCardNo(String paramString);

  public abstract Map<String, Object> getCardByUid(Long paramLong);

  public abstract void insert(Card paramCard);

  public abstract void insertCardDel(Map<String, Object> paramMap);

  public abstract void delete(Map<String, Object> paramMap);

  public abstract Card getCardByPhoneNumber(String paramString);

  public abstract List<Card> getCardRecordByParam(Map<String, Object> paramMap);

  public abstract Long getCardCountByParam(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.CardMapper
 * JD-Core Version:    0.6.0
 */