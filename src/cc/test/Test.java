package cc.test;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cc.kokoko.server.ibutler.domain.kdt.WeixinOauth2Token;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeixinOauth2Token wot = null;
		String respJSON = "{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200,\"refresh_token\":\"REFRESH_TOKEN\",\"openid\":\"OPENID\",\"scope\":\"SCOPE\"}";
		
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
	}

}
