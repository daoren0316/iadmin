package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.GuestBook;
import java.util.List;
import java.util.Map;

public abstract interface GuestBookMapper
{
  public abstract void saveGustbook(GuestBook paramGuestBook);

  public abstract List<GuestBook> getGuestBookRecord(Map<String, Object> paramMap);

  public abstract Long getGuestBookCount(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.GuestBookMapper
 * JD-Core Version:    0.6.0
 */