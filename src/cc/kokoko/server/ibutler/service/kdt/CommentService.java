package cc.kokoko.server.ibutler.service.kdt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.kokoko.server.ibutler.domain.kdt.LifeComment;
import cc.kokoko.server.ibutler.persistence.CommentMapper;

@Service("commentService")
public class CommentService {

	@Autowired
	CommentMapper commentMapper;
	
	public void addComment(LifeComment lifeComment){
		try{
			commentMapper.insertComment(lifeComment);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public List<LifeComment> findAllCommentsByLid(Long lid){
		return commentMapper.selectByLid(lid);
	}
	
	
}
