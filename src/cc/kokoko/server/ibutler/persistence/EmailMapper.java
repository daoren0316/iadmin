package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Email;

public abstract interface EmailMapper
{
  public abstract void createEmail(Email paramEmail);

  public abstract Email getEmailById(int paramInt);

  public abstract void createEmailLog(Email paramEmail);

  public abstract void deleteEmailById(int paramInt);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.EmailMapper
 * JD-Core Version:    0.6.0
 */