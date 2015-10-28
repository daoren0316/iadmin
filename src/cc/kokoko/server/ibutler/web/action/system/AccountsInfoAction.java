package cc.kokoko.server.ibutler.web.action.system;

import cc.kokoko.server.commons.util.DateUtil;
import cc.kokoko.server.commons.util.MaxMathUtil;
import cc.kokoko.server.ibutler.domain.SysDataAnalysis;
import cc.kokoko.server.ibutler.domain.SysInfoLog;
import cc.kokoko.server.ibutler.service.SysInfoService;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 账目统计
 */
public class AccountsInfoAction extends AdminBaseAction implements ModelDriven {

    @Autowired
    private SysInfoService sysInfoService;
    private SysInfoLog sysInfoLog;

    /**
     * 到达系统账目统计页面
     *
     * @return
     */
    public String toAccounts() {
        String[] categories = null;
        //Double[] activity = null;
        Double[] sys = null;
        Double[] charge = null;
        Double[] consume = null;
        //Double[] commodity = null;
        //Double[] pos = null;

        // 获取最后一条系统账目记录
        sysInfoLog = this.sysInfoService.getLastSysInfoLog();
        if (sysInfoLog != null) {
            // 获取前10条记录
            List<SysInfoLog> sysInfoLogList = this.sysInfoService.getTop10SysInfoLog();
            // 获取数据条数
            int size = sysInfoLogList.size();
            // xAxis数据
            categories = new String[size];
            //将数据设置到数组中，便于后面的格式化
            //activity = new Double[size];
            charge = new Double[size];
            consume = new Double[size];
            sys = new Double[size];
            //commodity = new Double[size];
            //pos = new Double[size];
            int i = 1;
            for (SysInfoLog sysInfoLog1 : sysInfoLogList) {
                //activity[i] = MaxMathUtil.getFormatedValue(sysInfoLog1.getActivityMoney(), 2);
                sys[size - i] = MaxMathUtil.getFormatedValue(sysInfoLog1.getSysMoney(), 2);
                charge[size - i] = MaxMathUtil.getFormatedValue(sysInfoLog1.getChargeMoney(), 2);
                consume[size - i] = MaxMathUtil.getFormatedValue(sysInfoLog1.getComsumeMoney(), 2);
                //commodity[i] = MaxMathUtil.getFormatedValue(sysInfoLog1.getCommodityMoney(), 2);
                //pos[i] = MaxMathUtil.getFormatedValue(sysInfoLog1.getPosMoney(), 2);
                categories[size - i] = DateUtil.getTime(sysInfoLog1.getCreatedTime(), "yyyy/MM/dd");
                i++;
            }
        }

        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        LinkedHashMap<String, Object> sysLinkedMap = new LinkedHashMap<String, Object>();
        sysLinkedMap.put("name", "监管账户");
        sysLinkedMap.put("data", sys);
        // 业主充值
        LinkedHashMap<String, Object> chargeLinkedMap = new LinkedHashMap<String, Object>();
        chargeLinkedMap.put("name", "业主充值");
        chargeLinkedMap.put("data", charge);
        // 活动返还
        LinkedHashMap<String, Object> consumeLinkedMap = new LinkedHashMap<String, Object>();
        consumeLinkedMap.put("name", "业主消费");
        consumeLinkedMap.put("data", consume);
        // 活动返还
        //LinkedHashMap<String, Object> activityLinkedMap = new LinkedHashMap<String, Object>();
        //activityLinkedMap.put("name", "活动返还");
        //activityLinkedMap.put("data", activity);
        // 团购
        //LinkedHashMap<String, Object> commodityLinkedMap = new LinkedHashMap<String, Object>();
        //commodityLinkedMap.put("name", "团购");
        //commodityLinkedMap.put("data", commodity);
        // POS刷卡
        //LinkedHashMap<String, Object> posLinkedMap = new LinkedHashMap<String, Object>();
        //posLinkedMap.put("name", "POS刷卡");
        //posLinkedMap.put("data", pos);
        // 将设置好的数据存入list中
        list.add(sysLinkedMap);
        list.add(chargeLinkedMap);
        list.add(consumeLinkedMap);
        //list.add(activityLinkedMap);
        //list.add(commodityLinkedMap);
        //list.add(posLinkedMap);

        request.setAttribute("categories", ObjectUtil.getJsonStr(categories));
        request.setAttribute("series", ObjectUtil.getJsonStr(list));
        return SUCCESS;
    }

    /**
     * 到数据分析统计页面
     *
     * @return
     */
    public String toDataAnalysis() {
        String[] categories = null;
        Long[] appDowns = null;
        Long[] onlineChargeCount = null;
        Long[] offlineChargeCount = null;
        Long[] cardCount = null;
        // 获取数据
        SysDataAnalysis sysDataAnalysis = this.sysInfoService.getLastSysDataAnalysis();
        if (sysDataAnalysis != null) {
            List<SysDataAnalysis> sysDataAnalysisList = this.sysInfoService.getTop10SysDataAnalysis();
            // 获取数据条数
            int size = sysDataAnalysisList.size();
            categories = new String[size];
            appDowns = new Long[size];
            onlineChargeCount = new Long[size];
            offlineChargeCount = new Long[size];
            cardCount = new Long[size];

            int i = 1;
            for (SysDataAnalysis sysDataAnalysis1 : sysDataAnalysisList) {
                categories[size - i] = DateUtil.getTime(sysDataAnalysis1.getCreatedTime(), "yyyy/MM/dd");
                // App下载次数
                appDowns[size - i] = sysDataAnalysis1.getAppDowns();
                // 在线充值
                onlineChargeCount[size - i] = sysDataAnalysis1.getOnlineChargeCount();
                // 线下充值
                offlineChargeCount[size - i] = sysDataAnalysis1.getOfflineChargeCount();
                // 共发卡次数
                cardCount[size - i] = sysDataAnalysis1.getCardCount();
                i++;
            }
            request.setAttribute("sysDataAnalysis", sysDataAnalysis);
        }

        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();

        LinkedHashMap<String, Object> appDownsLinkedMap = new LinkedHashMap<String, Object>();
        appDownsLinkedMap.put("name", "App下载次数");
        appDownsLinkedMap.put("data", appDowns);

        LinkedHashMap<String, Object> onlineChargeCountLinkedMap = new LinkedHashMap<String, Object>();
        onlineChargeCountLinkedMap.put("name", "在线充值次数");
        onlineChargeCountLinkedMap.put("data", onlineChargeCount);

        LinkedHashMap<String, Object> offlineChargeCountLinkedMap = new LinkedHashMap<String, Object>();
        offlineChargeCountLinkedMap.put("name", "线下充值次数");
        offlineChargeCountLinkedMap.put("data", offlineChargeCount);

        LinkedHashMap<String, Object> cardCountLinkedMap = new LinkedHashMap<String, Object>();
        cardCountLinkedMap.put("name", "发卡次数");
        cardCountLinkedMap.put("data", cardCount);

        list.add(cardCountLinkedMap);
        list.add(appDownsLinkedMap);
        list.add(onlineChargeCountLinkedMap);
        list.add(offlineChargeCountLinkedMap);

        request.setAttribute("categories", ObjectUtil.getJsonStr(categories));
        request.setAttribute("series", ObjectUtil.getJsonStr(list));

        return "toDataAnalysis";
    }

    public SysInfoLog getSysInfoLog() {
        return sysInfoLog;
    }

    public void setSysInfoLog(SysInfoLog sysInfoLog) {
        this.sysInfoLog = sysInfoLog;
    }

    public Object getModel() {
        if (sysInfoLog == null)
            sysInfoLog = new SysInfoLog();
        return sysInfoLog;
    }
}
