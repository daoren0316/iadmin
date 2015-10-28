package cc.kokoko.server.ibutler.web.weixin;

import cc.kokoko.server.ibutler.service.weixin.CoreService;
import cc.kokoko.server.ibutler.web.weixin.util.SignUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class CoreServlet extends HttpServlet
{
  protected Logger log = Logger.getLogger(getClass());

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
	    String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		WeixinMessageDigest wxDigest = WeixinMessageDigest.getInstance();
		boolean bValid = wxDigest.validate(signature, timestamp, nonce);
		PrintWriter out = response.getWriter();
		if (bValid) {
			out.write(echostr);
			out.close();
		}
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException
  {
    try
    {
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");

      String respMessage = CoreService.processRequest(request);

      response.setContentType("text/xml");
      PrintWriter out = response.getWriter();
      out.print(respMessage);
      out.close();
    } catch (Exception e) {
      this.log.error("POST ERROR : " + e.getMessage());
      e.printStackTrace();
    }
  }

}