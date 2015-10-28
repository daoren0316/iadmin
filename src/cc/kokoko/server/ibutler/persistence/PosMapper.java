package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Pos;
import java.util.List;
import java.util.Map;

public abstract interface PosMapper
{
  public abstract List<Pos> getPosList(Map<String, Object> paramMap);

  public abstract Long getPosCount(Map<String, Object> paramMap);

  public abstract void insertPos(Pos paramPos);

  public abstract void updatePos(Pos paramPos);

  public abstract Pos getOnePos(Long paramLong);

  public abstract Pos getOneByPosNo(String paramString);

  public abstract void deletePos(Pos paramPos);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.PosMapper
 * JD-Core Version:    0.6.0
 */