package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.SmsMT;

public abstract interface SmsMapper
{
  public abstract void createSmsMT(SmsMT paramSmsMT);

  public abstract void createSmsMTBuffer(SmsMT paramSmsMT);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.SmsMapper
 * JD-Core Version:    0.6.0
 */