package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Authcode;

public abstract interface AuthcodeMapper
{
  public abstract void createAuthcode(Authcode paramAuthcode);

  public abstract Authcode getAuthcodeByPhoneNumber(Authcode paramAuthcode);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.AuthcodeMapper
 * JD-Core Version:    0.6.0
 */