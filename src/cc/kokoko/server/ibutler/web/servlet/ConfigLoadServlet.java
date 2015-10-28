package cc.kokoko.server.ibutler.web.servlet;

import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.service.SysConfigService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 加载配置文件
 */
public class ConfigLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(ConfigLoadServlet.class);

	private WebApplicationContext webApplicationContext;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		SysConfigService sysConfigService = (SysConfigService) webApplicationContext.getBean(SysConfigService.class);
		if (sysConfigService != null) {
			log.debug("成功加载spring bean sysConfigService");
			sysConfigService.loadConfig();
		} else {
			log.error("加载sysConfigService失败");
		}

	}

	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(AppConst.sysConfigMap.size());
	}

}
