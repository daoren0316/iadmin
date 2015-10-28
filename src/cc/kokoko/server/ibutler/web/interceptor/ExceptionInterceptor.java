package cc.kokoko.server.ibutler.web.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cc.kokoko.server.commons.exception.ServiceException;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * action通用异常处理类
 * @author lijunhui
 *
 */
public class ExceptionInterceptor implements Interceptor {
	
	protected static final Logger log = Logger.getLogger(ExceptionInterceptor.class);
	
	public void destroy() {

	}

	public void init() {

	}
    
	public String intercept(ActionInvocation ai) throws Exception {
		String result = null;
		Map session = ai.getInvocationContext().getSession();
		HttpSession sessionss=ServletActionContext.getRequest().getSession();
		ActionSupport action=(ActionSupport) ai.getAction();
		try {
            result = ai.invoke();
        } catch(ServiceException e){ //业务异常
        	result="serviceError";
        	int errorCode=e.getErrorCode();        	
        	if(errorCode>0){ //从资源文件中加载
        		action.addActionError(action.getText(errorCode+""));
        	}
        	else{
        		action.addActionError(e.getErrorDes()); 
        	}
        	
        	if(e.getOriginException()==null){ //纯业务,程序自己产生
        		log.info("业务异常："+e.getErrorDes());         		
        	}
        	else{ //一般由dao层（数据库错误引发)
        		log.info("业务执行中发生系统错误："+e.getErrorDes(),e.getOriginException());
        	}
        	
        } catch (Exception e) { //未捕获异常（未预定)
        	  result=Action.ERROR;
              log.error("系统错误："+e.getMessage(),e);
              action.addActionError("系统繁忙："+e.getMessage());
              throw e;
        }

        return result;
	}

}
