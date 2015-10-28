package cc.kokoko.server.ibutler.web.action.system;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.SysMoneyLog;
import cc.kokoko.server.ibutler.service.SysMoneyLogService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 系统资金信息管理
 */
public class MoneyAction extends AdminBaseAction implements ModelDriven {

    @Autowired
    private SysMoneyLogService sysMoneyLogService;

    private SysMoneyLog sysMoneyLog;

    private String startTime;
    private String endTime;
    private Long siteId;

    /**
     * 到达信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        Map<String, Object> map = this.sysMoneyLogService.getSysMoneyRecord(startTime, endTime, page.getLineSize(), page.getStartRecord());
        List<SysMoneyLog> list = (List<SysMoneyLog>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("sysMoneyLogList", list);
        return SUCCESS;
    }

    /**
     * 到达数据添加页面
     *
     * @return
     */
    public String toInsert() {
        return "toInsert";
    }

    /**
     * 财务划款
     *
     * @return
     */
    public String insert() {
        try {
            // 验证数据是否合法
            if (sysMoneyLog.getMoney() <= 0)
                throw new RuntimeException("金额不合法");

            // 添加数据
            this.sysMoneyLogService.transferMoney(sysMoneyLog.getMoney(), siteId, sysMoneyLog.getNote());
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "划款成功", ActionResult.Dwz.SYS_MONEY, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.SYS_MONEY, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Object getModel() {
        if (sysMoneyLog == null)
            sysMoneyLog = new SysMoneyLog();
        return sysMoneyLog;
    }
}
