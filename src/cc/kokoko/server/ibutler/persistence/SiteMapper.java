package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Advertise;
import cc.kokoko.server.ibutler.domain.Consultant;
import cc.kokoko.server.ibutler.domain.Site;
import java.util.List;
import java.util.Map;

public abstract interface SiteMapper
{
  public abstract void insertSite(Site paramSite);

  public abstract Site getSiteDetailById(Long paramLong);

  public abstract List<Consultant> getConsultantList(Long paramLong);

  public abstract Consultant getConsultantById(Long paramLong);

  public abstract List<Advertise> getAdvertisementList(Long paramLong);

  public abstract Site getSiteDetailByCommunityId(Long paramLong);

  public abstract void updateSiteById(Site paramSite);

  public abstract List<Site> getAllSiteRecord(Map<String, Object> paramMap);

  public abstract Long getAllSiteCount(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.SiteMapper
 * JD-Core Version:    0.6.0
 */