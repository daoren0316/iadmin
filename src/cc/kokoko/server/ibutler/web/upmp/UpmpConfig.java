package cc.kokoko.server.ibutler.web.upmp;

import java.io.InputStream;
import java.util.PropertyResourceBundle;

public class UpmpConfig
{
  public static String VERSION;
  public static String CHARSET;
  public static String TRADE_URL;
  public static String QUERY_URL;
  public static String MER_ID;
  public static String MER_BACK_END_URL;
  public static String MER_FRONT_END_URL;
  public static String MER_FRONT_RETURN_URL;
  public static String SIGN_TYPE;
  public static String SECURITY_KEY;
  private static final String KEY_VERSION = "version";
  private static final String KEY_CHARSET = "charset";
  private static final String KEY_TRADE_URL = "upmp.trade.url";
  private static final String KEY_QUERY_URL = "upmp.query.url";
  private static final String KEY_MER_ID = "mer.id";
  private static final String KEY_MER_BACK_END_URL = "mer.back.end.url";
  private static final String KEY_MER_FRONT_END_URL = "mer.front.end.url";
  private static final String KEY_SIGN_METHOD = "sign.method";
  private static final String KEY_SECURITY_KEY = "security.key";
  public static final String RESPONSE_CODE_SUCCESS = "00";
  public static final String SIGNATURE = "signature";
  public static final String SIGN_METHOD = "signMethod";
  public static final String RESPONSE_CODE = "respCode";
  public static final String RESPONSE_MSG = "respMsg";
  private static final String CONF_FILE_NAME = "upmp.properties";

  static
  {
    try
    {
      InputStream fis = UpmpConfig.class.getClassLoader().getResourceAsStream("upmp.properties");
      PropertyResourceBundle props = new PropertyResourceBundle(fis);
      VERSION = props.getString("version");
      CHARSET = props.getString("charset");
      TRADE_URL = props.getString("upmp.trade.url");
      QUERY_URL = props.getString("upmp.query.url");
      MER_ID = props.getString("mer.id");
      MER_BACK_END_URL = props.getString("mer.back.end.url");
      MER_FRONT_END_URL = props.getString("mer.front.end.url");
      SIGN_TYPE = props.getString("sign.method");
      SECURITY_KEY = props.getString("security.key");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}