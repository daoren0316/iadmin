package cc.kokoko.server.ibutler.persistence;

import cc.kokoko.server.ibutler.domain.kdt.Praise;

public interface PraiseMapper {

	public Long selectPraiseById(Long id);
	
	public void insertPraise(Praise praise);
	
	public void updatePraise(Long id);
}
