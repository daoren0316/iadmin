package cc.kokoko.server.ibutler.web.lifetime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cc.kokoko.server.ibutler.domain.kdt.WeixinOauth2Token;
import cc.kokoko.server.ibutler.web.weixin.util.MyX509TrustManager;

public class AdvancedUtil {
	
	/**
	 * 处理https GET/POST请求
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方法（GET/POST）
	 * @param outputStr 参数
	 * @return
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = null;
		try {
			// 创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			TrustManager[] tm = { new MyX509TrustManager() };
			// 初始化
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 获取SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);
			// 设置当前实例使用的SSLSocketFactory
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();

			// 往服务器端写内容
			if (null != outputStr) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}

			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);

			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toString();
	}

	/**
	 * 通过code换取access_token
	 * @param appID
	 * @param appSecret
	 * @param code 授权码
	 * @return
	 */
	public static WeixinOauth2Token getOAuth2AceessToken(String appID, String appSecret, String code) {
		WeixinOauth2Token wot = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appID);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 接口调用
		String respJSON = httpsRequest(requestUrl, "GET", null);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			Map<String, Object> map = mapper.readValue(respJSON, Map.class);
			wot = new WeixinOauth2Token();
			wot.setAccessToken((String)map.get("access_token"));
			wot.setExpiresIn((Integer)map.get("expires_in"));
			wot.setRefreshToken((String)map.get("refresh_token"));
			wot.setOpenId((String)map.get("openid"));
			wot.setScope((String)map.get("scope"));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wot;
	}
	
	/**
	 * 网页授权获取用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	/*
	public static FromUser getSNSUserInfo(String accessToken, String openId) {
		FromUser fromUser = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		// 接口调用
		String respJSON = httpsRequest(requestUrl, "GET", null);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			Map<String, Object> map = mapper.readValue(respJSON, Map.class);
			fromUser = new FromUser();
			fromUser.setOpenid((String)map.get("openid"));
			fromUser.setNickname((String)map.get("nickname"));
			fromUser.setHeadimgurl((String)map.get("headimgurl"));
			fromUser.setSex((Integer)map.get("sex"));
			fromUser.setProvince((String)map.get("openid"));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fromUser;
	}
	*/
}
