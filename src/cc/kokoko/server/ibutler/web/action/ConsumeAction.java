package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ExportExeclUtil;
import cc.kokoko.server.commons.util.DateUtil;
import cc.kokoko.server.ibutler.domain.ReceiptAccount;
import cc.kokoko.server.ibutler.domain.dto.ConsumeLogDTO;
import cc.kokoko.server.ibutler.service.MoneyService;
import cc.kokoko.server.ibutler.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 消费记录统计
 *
 * @author HHang
 * @version V1.0.1
 */
public class ConsumeAction extends AdminBaseAction {
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private MoneyService moneyService;

    public String startTime;
    public String endTime;
    public Long flag;
    public String phoneNumber;
    public Long shopId;
    public String shopName;
    public Long tradeType;
    private Long houseId;
    private String publicAddress;

    /**
     * 获取商户收款记录
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取小区编号
        Long communityId = this.getCommunityId();
        // 如果flag的值不合法，则设置为1（未打款）
        flag = (flag == null || flag > 1 || flag < 0) ? 1 : flag;
        //根据参数获取交易记录
        Map<String, Object> map = this.receiptService.getRecepitAccountRecord(communityId, flag, shopId, startTime, endTime, houseId, page.getLineSize(), page.getStartRecord());
        List<ReceiptAccount> list = (List<ReceiptAccount>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("receiptAccountList", list);
        request.setAttribute("ref", "LookupHouseShopReceipt");
        return SUCCESS;
    }

    /**
     * 到达交易数据显示页面
     *
     * @return
     */
    public String toConsumeIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        Long communityId = this.getCommunityId();
        // 类型
        String type = tradeType == null ? null : tradeType.toString();
        // 根据参数获取交易记录
        Map<String, Object> map = this.moneyService.getConsumeRecord(communityId, phoneNumber, type, startTime, endTime, houseId, page.getLineSize(), page.getStartRecord());
        List<ConsumeLogDTO> list = (List<ConsumeLogDTO>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("ConsumeLogDTOList", list);
        request.setAttribute("ref", "LookupHouseConsume");
        return "toConsume";
    }

    /**
     * 开放给商户使用的，消费记录查询功能
     *
     * @return
     */
    public String toShopConsumeIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取小区编号
        Long communityId = this.getLoginUser().getCommunityId();
        // 获取登录用户编号
        Long uid = this.getLoginUser().getUid();
        //根据参数获取交易记录
        Map<String, Object> map = this.receiptService.getRecepitAccountRecord(communityId, null, uid, startTime, endTime, houseId, page.getLineSize(), page.getStartRecord());
        List<ReceiptAccount> list = (List<ReceiptAccount>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("receiptAccountList", list);
        request.setAttribute("ref", "LookupHouseShopConsume");
        return "toShopConsumeIndex";
    }

    /**
     * @return
     */
    public String toChargeIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        Long communityId = this.getLoginUser().getCommunityId();
        // 类型
        String type = tradeType == null ? "1,8,4" : tradeType.toString();
        if (tradeType == null || (tradeType != 1 && tradeType != 8 && tradeType != 4)) {
            type = "1,8,4";
        }
        //根据参数获取交易记录
        Map<String, Object> map = this.moneyService.getConsumeRecord(null, phoneNumber, type, startTime, endTime, houseId, page.getLineSize(), page.getStartRecord());
        List<ConsumeLogDTO> list = (List<ConsumeLogDTO>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("ConsumeLogDTOList", list);
        request.setAttribute("ref", "LookupHouseCharge");
        return "toChargeIndex";
    }

    /**
     * 导出交易记录的Execl
     */
    public void toExportTrade() {
        Long communityId = this.getCommunityId();
        //根据参数获取交易记录
        List<ConsumeLogDTO> consumeLogDTOList = this.moneyService.getConsumeRecord(communityId, phoneNumber, tradeType, startTime, endTime);
        List<Object> list = new ArrayList<Object>();
        for (ConsumeLogDTO consumeLogDTO : consumeLogDTOList) {
            String tradeName = "";
            boolean isConsume = false; //是否为消费类型
            switch (consumeLogDTO.getTradeType().intValue()) {
                case 1:
                    tradeName = "线下充值";
                    break;
                case 8:
                    tradeName = "在线充值";
                    break;
                case 2:
                    isConsume = true;
                    tradeName = "团购";
                    break;
                case 5:
                    isConsume = true;
                    tradeName = "POS机刷卡";
                    break;
                case 6:
                    isConsume = true;
                    tradeName = "缴费物业费";
                    break;
                case 7:
                    isConsume = true;
                    tradeName = "缴费停车费";
                    break;
                case 3:
                    isConsume = true;
                    tradeName = "转账-转出";
                    break;
                case 4:
                    tradeName = "转账-转入";
                    break;
                case 9:
                    isConsume = true;
                    tradeName = "退款";
                    break;
            }

            Object[] obj = new Object[8];
            obj[0] = consumeLogDTO.getOrderId();
            obj[1] = tradeName;
            // 如果为消费类型，则将金额转换为负数
            obj[2] = isConsume ? -consumeLogDTO.getAmount() : consumeLogDTO.getAmount();
            obj[3] = consumeLogDTO.getPublicAddress();
            obj[4] = consumeLogDTO.getNickname();
            obj[5] = consumeLogDTO.getPhoneNumber();
            obj[6] = DateUtil.getTime(consumeLogDTO.getTradeTime(), DateUtil.DATE_FORMAT_TIME);
            obj[7] = consumeLogDTO.getNote();
            list.add(obj);
        }
        String header = "业主交易记录";
        String[] title = new String[]{"订单号", "交易类型", "交易金额", "业主地址", "业主昵称", "业主电话号码", "交易时间", "备注"};
        // 导出execl
        ExportExeclUtil.export(header, header, title, list, this.request, this.response);
    }

    /**
     * 导出商户收款记录的Execl
     */
    public void toExport() {
        // 获取小区编号
        Long communityId = this.getCommunityId();
        // 如果flag的值不合法，则设置为1（未打款）
        flag = (flag == null || flag > 1 || flag < 0) ? 1 : flag;
        //根据参数获取交易记录
        List<ReceiptAccount> receiptAccountList = this.receiptService.getRecepitAccountRecord(communityId, flag, shopId, startTime, endTime);
        List<Object> list = new ArrayList<Object>();
        for (ReceiptAccount receiptAccount : receiptAccountList) {
            Object[] obj = new Object[6];
            obj[0] = receiptAccount.getTradeId();
            obj[1] = DateUtil.getTime(receiptAccount.getCreateTime(), DateUtil.DATE_FORMAT_TIME);
            obj[2] = receiptAccount.getMoney();
            obj[3] = receiptAccount.getShopName();
            obj[4] = receiptAccount.getShopAddress();
            obj[5] = receiptAccount.getFlag() == 0 ? "已打款" : "未打款";
            list.add(obj);
        }
        String header = "商户收款数据";
        String[] title = new String[]{"交易号", "交易时间", "交易金额", "商户名称", "商户地址", "是否已打款"};
        // 导出execl
        ExportExeclUtil.export(header, header, title, list, this.request, this.response);
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getFlag() {
        return flag;
    }

    public void setFlag(Long flag) {
        this.flag = flag;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getTradeType() {
        return tradeType;
    }

    public void setTradeType(Long tradeType) {
        this.tradeType = tradeType;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
}
