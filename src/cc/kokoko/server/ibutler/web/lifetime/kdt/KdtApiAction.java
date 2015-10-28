package cc.kokoko.server.ibutler.web.lifetime.kdt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;

import cc.kokoko.server.ibutler.domain.kdt.resp.KtoDTO;
import cc.kokoko.server.ibutler.service.kdt.KdtApiService;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import cc.kokoko.server.ibutler.web.lifetime.domain.commons.KdtCommons;

public class KdtApiAction extends AdminBaseAction
{

  @Autowired
  private KdtApiService kdtApiService;
  private static final String APP_ID = "db0915ebc908a6c498";
  private static final String APP_SECRET = "3e6b2f375ad1943ee52c86146aad04d1";

  public String Statistics()
  {
    try
    {
      HashMap params = new HashMap();

      String result = loadKdtMsg(KdtCommons.Methods.KAT_TRADES_SOLD_GET, params);
      this.log.debug(result);

      KtoDTO ktoDTO = (KtoDTO)ObjectUtil.getObjectFromJson(result, KtoDTO.class);
      if (ktoDTO == null) {
        throw new Exception(" kdt data error ");
      }
      this.kdtApiService.insert(ktoDTO, result);
    } catch (Exception e) {
      this.log.error("Statistics error : " + e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  public String loadKdtMsg(String method, HashMap<String, String> params)
  {
    String returnMsg = "";
    try {
      KdtApiClient kdtApiClient = new KdtApiClient("db0915ebc908a6c498", "3e6b2f375ad1943ee52c86146aad04d1");
      HttpResponse response = kdtApiClient.get(method, params);
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      StringBuffer result = new StringBuffer();
      String line = "";
      while ((line = bufferedReader.readLine()) != null) {
        result.append(line);
      }
      returnMsg = result.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnMsg;
  }
}