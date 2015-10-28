package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.Feedback;
import java.util.List;
import java.util.Map;

public abstract interface FeedbackMapper
{
  public abstract void createFeedback(Feedback paramFeedback);

  public abstract List<Feedback> getFeedbackList(Map<String, Object> paramMap);

  public abstract int getFeedbackCountByCommunityId(Map<String, Object> paramMap);

  public abstract Feedback getFeedbackById(Long paramLong);

  public abstract void updateFeedback(Map<String, Object> paramMap);
}

/* Location:           H:\ibu\ibutler-persistence-1.0-SNAPSHOT\
 * Qualified Name:     cc.kokoko.server.ibutler.persistence.FeedbackMapper
 * JD-Core Version:    0.6.0
 */