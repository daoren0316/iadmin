package cc.kokoko.server.ibutler.web.interceptor;

import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.exception.ServiceException;
import cc.kokoko.server.ibutler.domain.admin.AdminUser;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LogonInterceptor implements Interceptor {
    private static final long serialVersionUID = -8351874512604014838L;

    public void destroy() {

    }

    public void init() {
    }

    public String intercept(ActionInvocation ai) throws Exception {
        Map session = ai.getInvocationContext().getSession();
        AdminUser user = (AdminUser) session.get("adminUser");
        if (null != user) {
            //模块访问权限验证
            ActionSupport action = (ActionSupport) ai.getAction();
            HttpServletRequest req = ServletActionContext.getRequest();
            String url = req.getRequestURL().toString();
            url = url.substring(url.lastIndexOf("/") + 1);
            if (!check(url, user)) {
                throw new ServiceException(action.getText("NO_RIGHT", "Forbidden Operation!"));
            }
            return ai.invoke();
        } else {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/Json;charset=utf-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires", 0L);
            response.setCharacterEncoding("UTF-8");
            DwzUtil dwz = new DwzUtil("301", "", "");
            response.getWriter().write(ObjectUtil.getJsonStr(dwz));
            return null;
        }
    }

    private boolean check(String url, AdminUser user) {

        if (user.isSysFlag()) {
            return true;
        } else {
            //return user.getAuthPattern().matcher(url).matches();
            //@TODO 根据权限判断
            return true;
        }


    }
}
