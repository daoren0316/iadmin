package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageInfo;
import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.ibutler.domain.admin.AdminUser;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


public class AdminBaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {

    private static final long serialVersionUID = -7050887876342141566L;
    protected Logger log = Logger.getLogger(this.getClass());
    protected PageInfo pageInfo;
    public Map<String, Object> paramMap;
    /**
     * 分页工具类
     */
    protected PageUtil page = new PageUtil();
    /**
     * DWZ交互提示数据的工具类
     */
    protected DwzUtil dwz;

    /**
     * 当前页码
     */
    protected int pageNum = 1;
    /**
     * 每页显示的条数
     */
    protected int numPerPage = 20;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        //同步工具类中的参数
        page.setCurrentPage(pageNum);
        this.pageNum = pageNum;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        page.setLineSize(numPerPage);
        this.numPerPage = numPerPage;
    }

    /**
     * 初始化REQUEST
     */
    protected HttpServletRequest request;
    /**
     * 初始化RESPONSE
     */
    protected HttpServletResponse response;
    /**
     * 初始化SESSION
     */
    protected HttpSession session;
    /**
     * 初始化SESSION-MAP
     */
    protected Map<String, Object> sessionMap;

    /**
     * 获取小区编号
     *
     * @return
     */
    public Long getCommunityId() {
        // 如果为管理员则返回null
        return this.getLoginUser().isSysFlag() ? null : this.getLoginUser().getCommunityId();
    }

    /**
     * 获取当前登录用户
     */
    public AdminUser getLoginUser() {
        return (AdminUser) this.getSession().getAttribute("adminUser");
    }

    /**
     * 描述：为request对象赋值
     */
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 描述：为response对象赋值
     */
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public void setSession(Map<String, Object> session) {
        this.sessionMap = session;
    }

    protected PrintWriter getResponseWriter() throws IOException {
        response.setContentType("application/Json;charset=utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0L);
        response.setCharacterEncoding("UTF-8");
        return response.getWriter();
    }

    /**
     * 功能：将信息格式化成gson数据并返回页面
     *
     * @param obj
     */
    protected void convertMessage(Object obj) {
        try {
            // 初始化gson对象
            Gson gson = new Gson();
            // 格式化数据
            String data = gson.toJson(obj);
            // 返回gson数据
            this.getResponseWriter().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能：将信息格式化成gson数据并返回页面
     *
     * @param paramMap
     */
    protected void convertMessage(Map<String, Object> paramMap) {
        try {
            // 初始化gson对象
            Gson gson = new Gson();
            // 格式化数据
            String data = gson.toJson(paramMap);
            // 返回gson数据
            this.getResponseWriter().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public PageUtil getPage() {
        return page;
    }

    public void setPage(PageUtil page) {
        this.page = page;
    }

    public DwzUtil getDwz() {
        return dwz;
    }

    public void setDwz(DwzUtil dwz) {
        this.dwz = dwz;
    }
}
