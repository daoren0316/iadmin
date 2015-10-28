package cc.kokoko.server.ibutler.web.action.site;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.service.OrderService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


/**
 * 订单管理
 */
public class OrderAction extends AdminBaseAction {
    @Autowired
    private OrderService orderService;

    private Long tradeStatus;
    private Long orderId;
    private String startTime;
    private String endTime;
    private String[] ids;

    /**
     * 到达信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取所在小区编号
        Long communityId = this.getLoginUser().getCommunityId();
        // 如果状态为空则查询未完成的订单
        tradeStatus = tradeStatus == null ? 0 : tradeStatus;
        // 获取订单信息
        Map<String, Object> map = this.orderService.getOrderRecord(communityId, tradeStatus, orderId, startTime, endTime,
                page.getLineSize(), page.getStartRecord());
        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("orderList", list);
        return SUCCESS;
    }

    /**
     * 设置为已完成
     *
     * @return
     */
    public String set() {
        try {
            // 获取当前登录用户的编号
            Long uid = this.getLoginUser().getUid();
            if (ids == null || ids.length <= 0)
                throw new RuntimeException("选择的数据为空");
            // 修改订单信息
            this.orderService.changeOrderStatus(ids);
            // 发布活动
            this.dwz = new DwzUtil("200", "设置成功", "order", "");
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("set error " + errMsg);
            this.dwz = new DwzUtil("300", "设置失败", "order", "");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    public Long getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Long tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
