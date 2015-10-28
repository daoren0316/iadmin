package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Remittance;
import cc.kokoko.server.ibutler.service.RemittanceService;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 财务打款
 *
 * @author HHang
 * @version V1.0.1
 */
public class RemittanceAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private RemittanceService remittanceService;

    private Remittance remittance;

    /**
     * 到达打款信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取留言记录
        Map<String, Object> map = this.remittanceService.getRemittanceRecord(remittance.getShopId(),
                remittance.getStartTime(), remittance.getEndTime(), page.getLineSize(), page.getStartRecord());
        List<Remittance> list = null;
        int count = 0;
        if (map != null) {
            list = (List<Remittance>) map.get("list");
            count = Integer.valueOf(map.get("count").toString());
        }
        page.setAllRecorders(count);
        request.setAttribute("riteList", list);
        return SUCCESS;
    }

    /**
     * 到达添加打款信息页面
     *
     * @return
     */
    public String toInsert() {
        return "toInsert";
    }

    /**
     * 添加打款记录
     *
     * @return
     */
    public String insert() {
        try {
            if (remittance == null)
                throw new RuntimeException("remittance 为空");
            if (remittance.getShopId() <= 0)
                throw new RuntimeException("shopId 不合法");
            if (remittance.getStartTime() == null)
                throw new RuntimeException("开始时间为空");
            if (remittance.getEndTime() == null)
                throw new RuntimeException("结束时间为空");
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            // 添加打款记录
            this.remittanceService.insert(remittance);
            this.dwz = new DwzUtil("200", "打款成功！", "rite", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil("300", "打款失败！", "rite", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 根据查询条件获取需打款的总金额
     *
     * @return
     */
    public void query() {
        try {
            if (remittance == null)
                throw new RuntimeException("remittance 为空");
            if (remittance.getShopId() <= 0)
                throw new RuntimeException("shopId 不合法");
            if (remittance.getStartTime() == null)
                throw new RuntimeException("开始时间为空");
            if (remittance.getEndTime() == null)
                throw new RuntimeException("结束时间为空");
            // 获取需打款金额
            Double money = this.remittanceService.queryReceiptMoney(remittance.getShopId(),
                    remittance.getStartTime(), remittance.getEndTime());
            this.convertMessage(money);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("query error" + errMsg);
        }
    }

    public Object getModel() {
        if (remittance == null)
            remittance = new Remittance();
        return remittance;
    }
}
