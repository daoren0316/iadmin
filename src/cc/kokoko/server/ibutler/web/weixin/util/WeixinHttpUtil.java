package cc.kokoko.server.ibutler.web.weixin.util;

import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeixinHttpUtil
{
  private static Logger log = LoggerFactory.getLogger(WeixinHttpUtil.class);

  public static String httpRequest(String requestUrl, String requestMethod, String outputStr)
  {
    String resp = null;
    StringBuffer buffer = new StringBuffer();
    try
    {
      TrustManager[] tm = { new MyX509TrustManager() };
      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
      sslContext.init(null, tm, new SecureRandom());

      SSLSocketFactory ssf = sslContext.getSocketFactory();

      URL url = new URL(requestUrl);
      HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
      httpUrlConn.setSSLSocketFactory(ssf);

      httpUrlConn.setDoOutput(true);
      httpUrlConn.setDoInput(true);
      httpUrlConn.setUseCaches(false);

      httpUrlConn.setRequestMethod(requestMethod);

      if ("GET".equalsIgnoreCase(requestMethod)) {
        httpUrlConn.connect();
      }

      if (null != outputStr) {
        OutputStream outputStream = httpUrlConn.getOutputStream();

        outputStream.write(outputStr.getBytes("UTF-8"));
        outputStream.close();
      }

      InputStream inputStream = httpUrlConn.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      String str = null;
      while ((str = bufferedReader.readLine()) != null) {
        buffer.append(str);
      }
      bufferedReader.close();
      inputStreamReader.close();

      inputStream.close();
      inputStream = null;
      httpUrlConn.disconnect();
      resp = buffer.toString();
    } catch (ConnectException ce) {
      log.error("Weixin server connection timed out.");
    } catch (Exception e) {
      log.error("https request error:{}", e);
    }
    return resp;
  }

  public static void main(String[] args)
  {
    Map button = new HashMap();
    List list = new ArrayList();
    Map buttonMsg = new HashMap();
    buttonMsg.put("type", "click");
    buttonMsg.put("name", "通知");
    buttonMsg.put("key", "V1001_NOTICE");
    Map buttonMsg1 = new HashMap();
    buttonMsg1.put("name", "服务");

    List subList1 = new ArrayList();
    Map subButton1 = new HashMap();
    subButton1.put("type", "click");
    subButton1.put("name", "缴费充值");
    subButton1.put("key", "V1002_01_RECHARGE");
    Map subButton = new HashMap();
    subButton.put("type", "view");
    subButton.put("name", "绑定户号");
    subButton.put("url", "http://iadmin.ibutler.cn/iadmin/weixin/bind.do");
    subList1.add(subButton1);
    subList1.add(subButton);
    buttonMsg1.put("sub_button", subList1);

    Map buttonMsg2 = new HashMap();
    buttonMsg2.put("name", "我的");
    List subList2 = new ArrayList();
    Map subButton2 = new HashMap();
    subButton2.put("type", "click");
    subButton2.put("name", "家人管理");
    subButton2.put("key", "V1003_01_FAMILY");
    Map subButton3 = new HashMap();
    subButton3.put("type", "click");
    subButton3.put("name", "消费记录");
    subButton3.put("key", "V1003_02_RECORD");
    Map subButton4 = new HashMap();
    subButton4.put("type", "click");
    subButton4.put("name", "我的钱包");
    subButton4.put("key", "V1003_04_WALLET");
    subList2.add(subButton2);
    subList2.add(subButton3);
    subList2.add(subButton4);
    buttonMsg2.put("sub_button", subList2);

    list.add(buttonMsg);
    list.add(buttonMsg1);
    list.add(buttonMsg2);
    button.put("button", list);

    System.out.println(ObjectUtil.getJsonStr(button));
  }
}