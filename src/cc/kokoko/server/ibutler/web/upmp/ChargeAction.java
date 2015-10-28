package cc.kokoko.server.ibutler.web.upmp;

import cc.kokoko.server.ibutler.service.UpmpChargeService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ChargeAction extends AdminBaseAction
{
  protected Logger log = Logger.getLogger(getClass());

  @Autowired
  private UpmpChargeService upmpChargeService;

  public String verifyOrder() {
    try {
      String uid = this.request.getParameter("uid");

      Map params = new HashMap();
      Map requestParams = this.request.getParameterMap();
      for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
        String name = (String)iter.next();
        String[] values = (String[])(String[])requestParams.get(name);
        String valueStr = "";
        for (int i = 0; i < values.length; i++) {
          valueStr = valueStr + values[i] + ",";
        }
        params.put(name, valueStr);
      }

      if (UpmpService.verifySignature(params))
      {
        String transStatus = this.request.getParameter("transStatus");
        String orderId = (String)params.get("orderNumber");
        this.log.info("交易状态： " + transStatus + " 订单号：" + orderId);
        if ((null != transStatus) && (transStatus.equals("00")))
        {
          String qn = (String)params.get("qn");
          String note = "查询流水号：" + qn;

          this.upmpChargeService.completeOrder(orderId, note);
          this.log.debug("orderId：" + orderId + " qn：" + qn);
        } else {
          this.log.debug("error orderId：" + orderId);
        }
      }
      else {
        this.log.error("服务器签名验证失败");
      }
    } catch (Exception e) {
      String errMsg = e.getMessage();
      this.log.error("verifyOrder error: " + e.getMessage());
    }
    return null;
  }
}