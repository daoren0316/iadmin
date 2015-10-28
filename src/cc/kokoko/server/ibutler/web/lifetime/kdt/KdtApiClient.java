package cc.kokoko.server.ibutler.web.lifetime.kdt;

import java.io.File;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class KdtApiClient
{
  private static final String Version = "1.0";
  private static final String apiEntry = "http://open.koudaitong.com/api/entry?";
  private static final String format = "json";
  private static final String signMethod = "md5";
  private static final String DefaultUserAgent = "KdtApiSdk Client v0.1";
  private String appId;
  private String appSecret;

  public KdtApiClient(String appId, String appSecret)
    throws Exception
  {
    if (("".equals(appId)) || ("".equals(appSecret))) {
      throw new Exception("appId 和 appSecret 不能为空");
    }

    this.appId = appId;
    this.appSecret = appSecret;
  }

  public HttpResponse get(String method, HashMap<String, String> params) throws Exception {
    String url = "http://open.koudaitong.com/api/entry?" + getParamStr(method, params);

    HttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet(url);
    request.addHeader("User-Agent", "KdtApiSdk Client v0.1");

    HttpResponse response = client.execute(request);
    return response;
  }

  public HttpResponse post(String method, HashMap<String, String> params, List<String> filePaths, String fileKey) throws Exception {
    String url = "http://open.koudaitong.com/api/entry?" + getParamStr(method, params);

    HttpClient client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url);
    httppost.addHeader("User-Agent", "KdtApiSdk Client v0.1");

    if ((null != filePaths) && (filePaths.size() > 0) && (null != fileKey) && (!"".equals(fileKey))) {
      MultipartEntity mpEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
      for (int i = 0; i < filePaths.size(); i++) {
        File file = new File((String)filePaths.get(i));
        ContentBody cbFile = new FileBody(file);
        mpEntity.addPart(fileKey, cbFile);
      }

      httppost.setEntity(mpEntity);
    }

    System.out.println("executing request " + httppost.getRequestLine());
    HttpResponse response = client.execute(httppost);

    return response;
  }

  public String getParamStr(String method, HashMap<String, String> params) {
    String str = "";
    try {
      str = URLEncoder.encode(buildParamStr(buildCompleteParams(method, params)), "UTF-8").replace("%3A", ":").replace("%2F", "/").replace("%26", "&").replace("%3D", "=").replace("%3F", "?");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return str;
  }

  private String buildParamStr(HashMap<String, String> param) {
    String paramStr = "";
    Object[] keyArray = param.keySet().toArray();
    for (int i = 0; i < keyArray.length; i++) {
      String key = (String)keyArray[i];

      if (0 == i)
        paramStr = paramStr + key + "=" + (String)param.get(key);
      else {
        paramStr = paramStr + "&" + key + "=" + (String)param.get(key);
      }
    }

    return paramStr;
  }

  private HashMap<String, String> buildCompleteParams(String method, HashMap<String, String> parames) throws Exception
  {
    HashMap commonParams = getCommonParams(method);
    for (String key : parames.keySet()) {
      if (commonParams.containsKey(key)) {
        throw new Exception("参数名冲突");
      }
      commonParams.put(key, parames.get(key));
    }

    commonParams.put("sign", KdtApiProtocol.sign(this.appSecret, commonParams));
    return commonParams;
  }

  private HashMap<String, String> getCommonParams(String method) {
    HashMap params = new HashMap();
    params.put("app_id", this.appId);
    params.put("method", method);
    params.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    params.put("format", "json");
    params.put("sign_method", "md5");
    params.put("v", "1.0");
    return params;
  }
}