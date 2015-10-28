package cc.kokoko.server.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import cc.kokoko.server.common.model.PageInfo;
import cc.kokoko.server.common.util.StdLib;
import cc.kokoko.server.commons.util.MMGlobals;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware {

	private Map	session;
	
	private int pageCacheFlag =1;
	
	protected Logger log;
	
	protected PageInfo pageInfo;
	
	public BaseAction(){
		log=Logger.getLogger(this.getClass());
	}
	
	/**
	 * @return Returns the session.
	 */
	public Map getSession()
	{
		return session;
	}
	
	
	/**
	 * @param session
	 *            The session to set.
	 */
	public void setSession(Map session)
	{
		this.session = session;

	}

	/**
	 * Returns cookie associating with specified name
	 * 
	 * @param name
	 *            specifies which value to get from cookie
	 * @return value of a cookie associating with specified name
	 */
	protected final String getCookie(String name)
	{
		Cookie c = (Cookie) getCookies().get(name);
		String v = c != null ? c.getValue() : null;

		try
		{
			return v != null ? URLDecoder.decode(v, "iso-8859-1") : null;
		} catch (UnsupportedEncodingException e)
		{
			return v;
		}
	}

	protected final Map getCookies()
	{
		return StdLib.parseCookies(ServletActionContext.getRequest()
				.getCookies());

	}

	/**
	 * set cookie using given name and value
	 * 
	 * @param name
	 *            specifies dookie's name
	 * @param value
	 *            specifies dookie's value
	 */
	protected final void setCookie(String name, String value)
	{
		Cookie c = new Cookie(name, (value == null) ? "" : value.toString());
		c.setPath("/");
		ServletActionContext.getResponse().addCookie(c);

		getCookies().put(name, c);
	}

	/**
	 * set cookie using given name and value
	 * 
	 * @param name
	 *            specifies cookie's name
	 * @param value
	 *            specifies cookie's value
	 * @param expiry
	 *            specifies cookie's maximum age
	 */
	protected final void setCookie(String name, String value, int expiry)
	{
		Cookie c = new Cookie(name, (value == null) ? "" : value);
		c.setMaxAge(expiry);
		c.setPath("/");
		ServletActionContext.getResponse().addCookie(c);

		if (value == null)
			getCookies().remove(name);
		else
			getCookies().put(name, c);
	}

	public int getPageCacheFlag() {
		return pageCacheFlag;
	}

	public void setPageCacheFlag(int pageCacheFlag) {
		this.pageCacheFlag = pageCacheFlag;
		if(this.pageCacheFlag==0){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.addHeader("pragma", "no-cache");
			response.addHeader("cache-control", "no-cache,must-revalidate");
			response.addHeader("expires", "0");
		}
	}
	
	
	/**
	 * 运用j2ee api将数据发给客户端
	 * 
	 * @param content
	 * @throws IOException
	 */
	protected void sendClient(String content) throws IOException {
		PrintWriter out = getResponseWriter();
		out.print(content);
	//	out.flush();
	//	out.close();
	}
	
	
	protected PrintWriter getResponseWriter() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		return response.getWriter();
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}	
	
	
	
	/**
	 * 合法校验字段
	 */ 
	protected String sig;
	public String getSig() {
		return sig;
	}
	public void setSig(String sig) {
		this.sig = sig;
	}
	
	/**
	 * 判断前端传递进来的ID是否合法，主要为了防止用户模拟URL进行恶意操作
	 * 
	 * @param id
	 * @return 
	 */
	protected boolean isLegitimate(int id){
		if(sig==null || "".equals(sig)) {
			return false;
		}
		else {
			String userMd5 = MMGlobals.getProperty("server.md5.apikey");
			if(StdLib.md5(id+userMd5).equals(sig)){
				return true;
			}
			else{
				return false;
			}
		}
	}
	/**
	 * 清除浏览器端缓存
	 */
	protected void clearCache(){
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setHeader("Pragma","No-cache"); 
        response.setHeader("Cache-Control","no-cache"); 
        response.setDateHeader("Expires", 0);
	}




}
