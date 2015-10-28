package cc.kokoko.server.ibutler.service.kdt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.kokoko.server.ibutler.domain.kdt.Praise;
import cc.kokoko.server.ibutler.persistence.PraiseMapper;

@Service("praiseService")
public class PraiseService {

	@Autowired
	PraiseMapper praiseMapper;
	
	public Long getPraiseCount(Long id){
		Long count = praiseMapper.selectPraiseById(id);
		if(count == null){
			Praise praise = new Praise();
			count = 0l;
			praise.setId(id);
			praise.setPcount(count);
			praiseMapper.insertPraise(praise);
		}
		return count;
	}
	
	
	@Transactional
	public void praise(Long id){
		praiseMapper.updatePraise(id);
	}
	
	
}
