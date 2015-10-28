package cc.kokoko.server.ibutler.web.action.site;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Building;
import cc.kokoko.server.ibutler.domain.Card;
import cc.kokoko.server.ibutler.domain.Community;
import cc.kokoko.server.ibutler.domain.dto.UserDTO;
import cc.kokoko.server.ibutler.service.CardService;
import cc.kokoko.server.ibutler.service.CommunityService;
import cc.kokoko.server.ibutler.service.UserService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 卡片管理
 */
public class CardAction extends AdminBaseAction {
    @Autowired
    private CardService cardService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommunityService communityService;

    private UserDTO chargeUser;
    private String phoneNumber;
    private String cardNo;
    private Long buildingId;
    private Long unitId;
    private Long houseId;
    private String publicAddress;

    /**
     * 到达发卡页面
     *
     * @return
     */
    public String toGrant() {
        // 如果为管理员，则获取所有小区
        if (this.getLoginUser().isSysFlag()) {
            // 获取所有小区编号
            request.setAttribute("communityList", this.communityService.getAllCommunity());
        } else {
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 根据小区编号获取名称
            String communityName = this.communityService.getCommunityName(communityId);
            request.setAttribute("communityName", communityName);
            // 获取所有幢信息
            List<Building> buildingList = this.communityService.getBuildingByCId(communityId);
            request.setAttribute("buildingList", buildingList);
        }
        return "toGrant";
    }

    /**
     * 确认发卡
     *
     * @return
     */
    public String confirm() {
        try {
            // 获取当前登录用户的编号
            Long uid = this.getLoginUser().getUid();
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 根据手机号获取绑定数据，验证是否已绑定卡片
            Card card = this.cardService.getCardByPhoneNumber(phoneNumber);
            if (card != null)
                throw new RuntimeException("该用户已绑定卡片");
            // 根据手机号码获取用户信息
            chargeUser = userService.getHouseByPhoneNumber(phoneNumber);
            // 如果用户数据存在，则验证是否为同一个小站
            if (chargeUser != null) {
                if (!this.getLoginUser().isSysFlag() && !communityId.equals(chargeUser.getCommunityId()))
                    throw new RuntimeException("此业主不属于该小站");
            }
            // 根据户号获取业主地址
            String address = this.communityService.getUserAddrByHouseId(houseId);
            request.setAttribute("address", address);
            return "confirm";
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("confirm error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.CARD_GRANT, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 发放卡片
     *
     * @return
     */
    public String grant() {
        try {
            // 获取当前登录用户的编号
            Long uid = this.getLoginUser().getUid();
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 操作员编号
            Long operatorId = this.getLoginUser().getOperatorId();
            // 操作员名称
            String operatorName = this.getLoginUser().getNickname();

            if (StringUtil.isEmpty(phoneNumber))
                throw new RuntimeException("手机号码为空");
            if (houseId == null || houseId <= 0)
                throw new RuntimeException("请选择对应的户号");
            if (StringUtil.isEmpty(cardNo))
                throw new RuntimeException("卡号为空");
            // 根据手机号获取绑定数据，验证是否已绑定卡片
            Card card = this.cardService.getCardByPhoneNumber(phoneNumber);
            if (card != null)
                throw new RuntimeException("该用户已绑定卡片");
            // 根据手机号码获取用户信息
            chargeUser = userService.getHouseByPhoneNumber(phoneNumber);
            // 如果用户数据存在，则验证是否为同一个小站
            if (chargeUser != null) {
                if (!this.getLoginUser().isSysFlag() && !communityId.equals(chargeUser.getCommunityId()))
                    throw new RuntimeException("此业主不属于该小站");
            }

            // 如果为管理员，则通过户号获取所属小区进行发卡
            if (this.getLoginUser().isSysFlag()) {
                Community community = this.communityService.getCommunityByHouseId(houseId);
                if (community == null)
                    throw new RuntimeException("小站数据不存在");
                // 重新设置小区编号
                communityId = community.getCommunityId();
            }

            // 添加发卡记录
            this.cardService.grantCard(phoneNumber, communityId, houseId, cardNo, operatorId, operatorName);

            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "发卡成功", ActionResult.Dwz.CARD_GRANT, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("grant error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "发卡失败："+errMsg, ActionResult.Dwz.CARD_GRANT, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 到达取消卡片绑定页面
     *
     * @return
     */
    public String toCancel() {
        // 如果为管理员，则获取所有小区
        if (this.getLoginUser().isSysFlag()) {
            // 获取所有小区编号
            request.setAttribute("communityList", this.communityService.getAllCommunity());
        } else {
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 根据小区编号获取名称
            String communityName = this.communityService.getCommunityName(communityId);
            request.setAttribute("communityName", communityName);
            // 获取所有幢信息
            List<Building> buildingList = this.communityService.getBuildingByCId(communityId);
            request.setAttribute("buildingList", buildingList);
        }
        return "toCancel";
    }

    /**
     * 确认注销卡信息
     *
     * @return
     */
    public String confirmCancel() {
        try {
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();

            if (StringUtil.isEmpty(cardNo))
                throw new RuntimeException("卡号为空");
            // 根据卡号获取卡绑定信息
            Map<String, Object> card = this.cardService.getCardByCardNo(cardNo);
            if (card == null)
                throw new RuntimeException("卡数据不存在");
            // 根据手机号验证用户是否存在
            chargeUser = userService.getHouseByPhoneNumber(phoneNumber);
            if (chargeUser == null)
                throw new RuntimeException("该手机号尚未注册，请先注册");
            // 当前操作员不为管理员时验证业主所属小站
            if (!this.getLoginUser().isSysFlag() && !communityId.equals(chargeUser.getCommunityId()))
                throw new RuntimeException("此业主不属于该小站");

            String address = this.communityService.getUserAddrByHouseId(houseId);
            request.setAttribute("address", address);
            request.setAttribute("card", card);
            return "confirmCancel";
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("confirmCancel error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.CARD_CANCEL, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 注销卡片
     *
     * @return
     */
    public String cancel() {
        try {
            if (StringUtil.isEmpty(cardNo))
                throw new RuntimeException("卡号为空");
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 根据卡号获取卡绑定信息
            Map<String, Object> card = this.cardService.getCardByCardNo(cardNo);
            if (card == null)
                throw new RuntimeException("卡数据不存在");
            // 根据手机号验证用户是否存在
            chargeUser = userService.getHouseByPhoneNumber(phoneNumber);
            if (chargeUser == null)
                throw new RuntimeException("该手机号尚未注册，请先注册");
            // 当前操作员不为管理员时验证业主所属小站
            if (!this.getLoginUser().isSysFlag() && !communityId.equals(chargeUser.getCommunityId()))
                throw new RuntimeException("此业主不属于该小站");
            // 获取持卡人的编号
            Long uid = Long.valueOf(card.get("uid").toString());
            // 注销卡片
            this.cardService.cancelCard(uid, cardNo);
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "注销成功", ActionResult.Dwz.CARD_CANCEL, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("cancel error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.CARD_CANCEL, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 发卡信息查询
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 如果是管理员类型则设置为空
        Long communityId = this.getCommunityId();

        Map<String, Object> map = this.cardService.getAllCardRecord(communityId, cardNo, houseId, phoneNumber, page.getLineSize(), page.getStartRecord());
        List<Card> list = (List<Card>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("cardList", list);
        request.setAttribute("ref", "LookupHouseCard");
        return SUCCESS;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public UserDTO getChargeUser() {
        return chargeUser;
    }

    public void setChargeUser(UserDTO chargeUser) {
        this.chargeUser = chargeUser;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }
}
