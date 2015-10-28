package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Attachment;

public abstract interface AttachmentMapper
{
  public abstract int createAttachment(Attachment paramAttachment);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.AttachmentMapper
 * JD-Core Version:    0.6.0
 */