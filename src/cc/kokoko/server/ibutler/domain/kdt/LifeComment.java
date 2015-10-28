package cc.kokoko.server.ibutler.domain.kdt;

import java.util.Date;

public class LifeComment {

	private Long id;
	private Long lid;//datlifetime id
	private String content;
	private Date cdate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLid() {
		return lid;
	}
	public void setLid(Long lid) {
		this.lid = lid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
}
