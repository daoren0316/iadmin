package cc.kokoko.server.ibutler.web.timer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cc.kokoko.server.ibutler.domain.kdt.resp.KtoDTO;
import cc.kokoko.server.ibutler.service.SysInfoService;
import cc.kokoko.server.ibutler.service.kdt.KdtApiService;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import cc.kokoko.server.ibutler.web.lifetime.domain.commons.KdtCommons;
import cc.kokoko.server.ibutler.web.lifetime.kdt.KdtApiClient;

/**
 * 定时统计系统账目数据
 */
public class SysInfoTimer {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private SysInfoService sysInfoService;
    @Autowired
    private KdtApiService kdtApiService;

    private static final String APP_ID = "db0915ebc908a6c498";
    private static final String APP_SECRET = "3e6b2f375ad1943ee52c86146aad04d1";

    /**
     * 统计系统账目
     */
    public void statistics() {
        this.sysInfoService.statistics();
    }

    /**
     * 统计口袋通数据
     *
     * @return
     */
    public void KdtDataStatistics() {
        try {
            // 设置数据用于获取口袋通信息
            HashMap<String, String> params = new HashMap<String, String>();
            // 获取返回信息
            String result = this.loadKdtMsg(KdtCommons.Methods.KAT_TRADES_SOLD_GET, params);
            // 解析json数据
            KtoDTO ktoDTO = ObjectUtil.getObjectFromJson(result, KtoDTO.class);
            // 如果获取到数据，则进行添加
            if (ktoDTO != null) {
                // 添加数据
                this.kdtApiService.insert(ktoDTO, result);
            }
        } catch (Exception e) {
            log.error("KdtDataStatistics error : " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 获取口袋通接口信息
     *
     * @param method
     * @param params
     * @return
     */
    public String loadKdtMsg(String method, HashMap<String, String> params) {
        String returnMsg = "";
        try {
            KdtApiClient kdtApiClient = new KdtApiClient(APP_ID, APP_SECRET);
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
