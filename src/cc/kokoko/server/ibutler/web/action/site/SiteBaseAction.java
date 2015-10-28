package cc.kokoko.server.ibutler.web.action.site;

import cc.kokoko.server.ibutler.web.action.AdminBaseAction;


public class SiteBaseAction extends AdminBaseAction {
	protected String msg;
	protected String toUrl;
	protected int result;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getToUrl() {
		return toUrl;
	}
	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
