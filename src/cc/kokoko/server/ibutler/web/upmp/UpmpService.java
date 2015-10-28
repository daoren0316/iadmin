package cc.kokoko.server.ibutler.web.upmp;

import cc.kokoko.server.common.util.HttpUtil;
import java.util.Map;

public class UpmpService
{
  public static boolean trade(Map<String, String> req, Map<String, String> resp)
  {
    String nvp = buildReq(req);
    String respString = HttpUtil.post(UpmpConfig.TRADE_URL, nvp);
    return verifyResponse(respString, resp);
  }

  public static boolean query(Map<String, String> req, Map<String, String> resp)
  {
    String nvp = buildReq(req);
    String respString = HttpUtil.post(UpmpConfig.QUERY_URL, nvp);
    return verifyResponse(respString, resp);
  }

  public static String buildReserved(Map<String, String> req)
  {
    StringBuilder merReserved = new StringBuilder();
    merReserved.append("{");
    merReserved.append(UpmpCore.createLinkString(req, false, true));
    merReserved.append("}");
    return merReserved.toString();
  }

  public static String buildReq(Map<String, String> req)
  {
    Map filteredReq = UpmpCore.paraFilter(req);

    String signature = UpmpCore.buildSignature(filteredReq);

    filteredReq.put("signature", signature);
    filteredReq.put("signMethod", UpmpConfig.SIGN_TYPE);

    return UpmpCore.createLinkString(filteredReq, false, true);
  }

  public static boolean verifySignature(Map<String, String> para)
  {
    String respSignature = (String)para.get("signature");

    Map filteredReq = UpmpCore.paraFilter(para);
    String signature = UpmpCore.buildSignature(filteredReq);

    return (null != respSignature) && (respSignature.equals(signature));
  }

  private static boolean verifyResponse(String respString, Map<String, String> resp)
  {
    if ((respString != null) && (!"".equals(respString))) {
      Map para;
      try {
        para = UpmpCore.parseQString(respString);
      } catch (Exception e) {
        return false;
      }
      boolean signIsValid = verifySignature(para);

      resp.putAll(para);

      return signIsValid;
    }

    return false;
  }
}