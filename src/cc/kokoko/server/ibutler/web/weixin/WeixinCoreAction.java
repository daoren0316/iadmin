package cc.kokoko.server.ibutler.web.weixin;

import cc.kokoko.server.ibutler.service.weixin.CoreService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class WeixinCoreAction extends AdminBaseAction
{

  @Autowired
  private CoreService coreService;
  private String phoneNumber;
  private String house;

  public String toBindHouse()
  {
    String openId = null;
    this.log.debug(" openId : " + openId + "　phoneNumber : " + this.phoneNumber + " house : " + this.house);
    return "success";
  }
}