package cc.kokoko.server.ibutler.persistence;

import java.util.List;

import cc.kokoko.server.ibutler.domain.kdt.LifeComment;

public interface CommentMapper {
	
	public List<LifeComment> selectByLid(long lid);
	
	public void insertComment(LifeComment lifeComment);
	
}
