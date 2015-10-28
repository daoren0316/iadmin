package cc.kokoko.server.ibutler.web.upmp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpmpCore
{
  public static final String QSTRING_EQUAL = "=";
  public static final String QSTRING_SPLIT = "&";

  public static Map<String, String> paraFilter(Map<String, String> para)
  {
    Map result = new HashMap();
    if ((para == null) || (para.size() <= 0)) {
      return result;
    }
    for (String key : para.keySet()) {
      String value = (String)para.get(key);
      if ((value == null) || (value.equals("")) || (key.equalsIgnoreCase("signature")) || (key.equalsIgnoreCase("signMethod")))
      {
        continue;
      }
      result.put(key, value);
    }
    return result;
  }

  public static String buildSignature(Map<String, String> req)
  {
    String prestr = createLinkString(req, true, false);
    prestr = prestr + "&" + UpmpMd5Encrypt.md5(UpmpConfig.SECURITY_KEY);
    return UpmpMd5Encrypt.md5(prestr);
  }

  public static String createLinkString(Map<String, String> para, boolean sort, boolean encode)
  {
    List keys = new ArrayList(para.keySet());
    if (sort)
      Collections.sort(keys);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < keys.size(); i++) {
      String key = (String)keys.get(i);
      String value = (String)para.get(key);
      if (encode)
        try {
          value = URLEncoder.encode(value, UpmpConfig.CHARSET);
        }
        catch (UnsupportedEncodingException e) {
        }
      if (i == keys.size() - 1)
        sb.append(key).append("=").append(value);
      else {
        sb.append(key).append("=").append(value).append("&");
      }
    }
    return sb.toString();
  }

  public static Map<String, String> parseQString(String str)
    throws UnsupportedEncodingException
  {
    Map map = new HashMap();
    int len = str.length();
    StringBuilder temp = new StringBuilder();

    String key = null;
    boolean isKey = true;
    for (int i = 0; i < len; i++) {
      char curChar = str.charAt(i);
      if (curChar == '&') {
        putKeyValueToMap(temp, isKey, key, map);
        temp.setLength(0);
        isKey = true;
      }
      else if (isKey) {
        if (curChar == '=') {
          key = temp.toString();
          temp.setLength(0);
          isKey = false;
        } else {
          temp.append(curChar);
        }
      } else {
        temp.append(curChar);
      }
    }

    putKeyValueToMap(temp, isKey, key, map);

    return map;
  }

  private static void putKeyValueToMap(StringBuilder temp, boolean isKey, String key, Map<String, String> map) throws UnsupportedEncodingException
  {
    if (isKey) {
      key = temp.toString();
      if (key.length() == 0) {
        throw new RuntimeException("QString format illegal");
      }
      map.put(key, "");
    } else {
      if (key.length() == 0) {
        throw new RuntimeException("QString format illegal");
      }
      map.put(key, URLDecoder.decode(temp.toString(), UpmpConfig.CHARSET));
    }
  }
}