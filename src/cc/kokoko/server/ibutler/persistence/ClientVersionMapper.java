package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.ClientVersion;

public abstract interface ClientVersionMapper
{
  public abstract ClientVersion getClientVersion(Integer paramInteger);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.ClientVersionMapper
 * JD-Core Version:    0.6.0
 */