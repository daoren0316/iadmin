package cc.kokoko.server.ibutler.web.action.site;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.*;
import cc.kokoko.server.ibutler.domain.dto.UserDTO;
import cc.kokoko.server.ibutler.service.*;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 充值管理
 */
public class ChargeAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private UserService userService;
    @Autowired
    private MoneyService moneyService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private AuthcodeService authcodeService;
    @Autowired
    private CardService cardService;

    private Fee fee;

    private String phoneNumber;
    private UserDTO chargeUser;
    private Long buildingId;
    private Long unitId;
    private Long code;
    private String ref;
    public String startTime;
    public String endTime;
    private String publicAddress;
    private Long cityId;

    /**
     * 到达充值页面
     *
     * @return
     */
    public String toCharge() {
        // 如果为管理员，则获取所有小区
        if (this.getLoginUser().isSysFlag()) {
            // 获取所有小区编号
            request.setAttribute("communityList", this.communityService.getAllCommunity());
        } else {
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 根据小区编号获取小区的详细数据
            User user = this.userService.getSiteUserByCommunityId(communityId);
            // 获取用户的余额
            Double money = user.getMoney();
            // 获取所有幢信息
            List<Building> buildingList = this.communityService.getBuildingByCId(communityId);
            // 根据小区编号获取名称
            String communityName = this.communityService.getCommunityName(communityId);
            // 将数据放大request中
            request.setAttribute("money", money);
            request.setAttribute("communityName", communityName);
            request.setAttribute("buildingList", buildingList);
        }
        return "toCharge";
    }

    /**
     * 确认充值
     *
     * @return
     */
    public String confirm() {
        try {
            if (fee.getAmount() <= 0)
                throw new RuntimeException("充值金额不合法");
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 根据手机号验证该用户是否存在
            chargeUser = userService.getHouseByPhoneNumber(phoneNumber);
            if (chargeUser == null)
                throw new RuntimeException("该手机号尚未注册，请先注册");
            if (!this.getLoginUser().isSysFlag() && !communityId.equals(chargeUser.getCommunityId()))
                throw new RuntimeException("此业主不属于该小站");

            // 获取绑定的户号
            Long houseId = chargeUser.getHouseId();
            if (houseId == null || houseId < 1)
                throw new RuntimeException("该用户尚未设置户号");
            // 如果 PublicAddress为空，则重写
            if (StringUtil.isEmpty(chargeUser.getPublicAddress())) {
                String address = this.communityService.getUserAddrByHouseId(houseId);
                chargeUser.setPublicAddress(address);
            }

            // 如果为管理员，则通过户号获取所属小区进行发卡
            if (this.getLoginUser().isSysFlag()) {
                Community community = this.communityService.getCommunityByHouseId(houseId);
                if (community == null)
                    throw new RuntimeException("小站数据不存在");
                // 重新设置小区编号
                communityId = community.getCommunityId();
            }
            // 根据小区编号获取小区的详细数据
            User user = this.userService.getSiteUserByCommunityId(communityId);
            if (user == null)
                throw new RuntimeException("小站数据不存在");
            // 获取用户的余额
            Double money = user.getMoney() == null ? 0 : user.getMoney();
            if (money < fee.getAmount())
                throw new RuntimeException("小站余额不足");

            return "confirm";
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("confirm error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.CHARGE, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 检测手机号码是否合法
     */
    public void check() {
        boolean bool = false;
        try {
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 根据手机号验证该用户是否存在
            chargeUser = userService.getHouseByPhoneNumber(phoneNumber);
            if (chargeUser == null)
                throw new RuntimeException("该手机号尚未注册，请先注册");
            if (!this.getLoginUser().isSysFlag() && !communityId.equals(chargeUser.getCommunityId()))
                throw new RuntimeException("此业主不属于该小站");
            bool = true;
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("check error" + errMsg);
        }
        // 反馈查询结果
        this.convertMessage(bool);
    }

    /**
     * 充值
     *
     * @return
     */
    public String charge() {
        try {
            if (fee.getAmount() <= 0)
                throw new RuntimeException("充值金额不合法");
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 操作员编号
            Long operatorId = this.getLoginUser().getOperatorId();
            // 操作员名称
            String operatorName = this.getLoginUser().getNickname();

            // 根据手机号验证该用户是否存在
            chargeUser = userService.getHouseByPhoneNumber(phoneNumber);
            if (chargeUser == null)
                throw new RuntimeException("该手机号尚未注册，请先注册");
            if (!this.getLoginUser().isSysFlag() && !communityId.equals(chargeUser.getCommunityId()))
                throw new RuntimeException("此业主不属于该小站");
            // 获取绑定的户号
            Long houseId = chargeUser.getHouseId();
            if (houseId == null || houseId < 1)
                throw new RuntimeException("该用户尚未设置户号");
            // 如果为管理员，则通过户号获取所属小区进行发卡
            if (this.getLoginUser().isSysFlag()) {
                Community community = this.communityService.getCommunityByHouseId(houseId);
                if (community == null)
                    throw new RuntimeException("小站数据不存在");
                // 重新设置小区编号
                communityId = community.getCommunityId();
            }
            // 根据小区编号获取小区的详细数据
            User user = this.userService.getSiteUserByCommunityId(communityId);
            if (user == null)
                throw new RuntimeException("小站数据不存在");
            // 获取用户的余额
            Double money = user.getMoney() == null ? 0 : user.getMoney();
            if (money < fee.getAmount())
                throw new RuntimeException("小站余额不足");
            // 线下充值
            this.moneyService.offLineCharge(chargeUser.getUid(), chargeUser.getHouseId(), fee.getAmount(), user.getUid(), operatorId, operatorName);

            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "充值成功", ActionResult.Dwz.CHARGE, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("charge error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "充值失败：" + errMsg, ActionResult.Dwz.CHARGE, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 到达退款页面
     *
     * @return
     */
    public String toRefund() {
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
        return "toRefund";
    }

    /**
     * 确认退款
     *
     * @return
     */
    public String confirmRefund() {
        try {
            if (fee.getAmount() <= 0)
                throw new RuntimeException("退款金额不合法");
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 根据手机号验证该用户是否存在
            chargeUser = userService.getHouseByPhoneNumber(phoneNumber);
            if (chargeUser == null)
                throw new RuntimeException("该手机号尚未注册，请先注册");
            if (!this.getLoginUser().isSysFlag() && !communityId.equals(chargeUser.getCommunityId()))
                throw new RuntimeException("此业主不属于该小站");
            // 获取业主详细地址
            String address = this.communityService.getUserAddrByHouseId(fee.getHouseId());
            request.setAttribute("address", address);
            return "refund";
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("confirmRefund error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.REFUND, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 退款
     *
     * @return
     */
    public String refund() {
        try {
            if (fee.getAmount() <= 0)
                throw new RuntimeException("充值金额不合法");
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 操作员编号
            Long operatorId = this.getLoginUser().getOperatorId();
            // 操作员名称
            String operatorName = this.getLoginUser().getNickname();
            // 根据手机号验证该用户是否存在
            chargeUser = userService.getHouseByPhoneNumber(phoneNumber);
            if (chargeUser == null)
                throw new RuntimeException("该手机号尚未注册，请先注册");
            if (!this.getLoginUser().isSysFlag() && !communityId.equals(chargeUser.getCommunityId()))
                throw new RuntimeException("此业主不属于该小站");

            // 退款
            this.moneyService.Refund(chargeUser.getUid(), chargeUser.getHouseId(), chargeUser.getPublicAddress(), operatorId, operatorName);

            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "退款成功", ActionResult.Dwz.REFUND, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("refund error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "退款失败：" + errMsg, ActionResult.Dwz.REFUND, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 到达查询业主余额页面
     *
     * @return
     */
    public String toQueryAmount() {
        // 如果为管理员，则获取所有小区
        if (this.getLoginUser().isSysFlag()) {
            // 获取所有小区编号
            request.setAttribute("communityList", this.communityService.getAllCommunity());
        } else {
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 获取所有幢信息
            List<Building> buildingList = this.communityService.getBuildingByCId(communityId);
            request.setAttribute("buildingList", buildingList);
        }
        return "toQueryAmount";
    }

    /**
     * 到达充值信息的显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取小区编号
        Long communityId = this.getCommunityId();
        //根据参数获取交易记录
        Map<String, Object> map = this.moneyService.getOrderAccountRecord(communityId, phoneNumber, startTime, endTime, fee.getHouseId(), page.getLineSize(), page.getStartRecord());
        List<OrderAccount> list = (List<OrderAccount>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("orderAccountList", list);
        request.setAttribute("ref", "LookupHouseOrderAccount");
        return SUCCESS;
    }

    /**
     * 获取业主余额
     */
    public void queryAmount() throws IOException {
        // 获取业主余额
        double amount = this.moneyService.getMoneyAccountBalance(fee.getHouseId());
        this.getResponseWriter().write(ObjectUtil.getJsonStr(amount));
    }

    /**
     * 到交物业费页面
     *
     * @return
     */
    public String toWyFee() {
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
        return "toWyFee";
    }

    /**
     * 交物业费
     *
     * @return
     */
    public String wyFee() {
        try {
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 操作员编号
            Long operatorId = this.getLoginUser().getOperatorId();
            // 操作员名称
            String operatorName = this.getLoginUser().getNickname();
            // 根据户号获取用户编号
            User user = this.userService.getOwnerUserByHouseId(fee.getHouseId());
            if (user == null)
                throw new RuntimeException("该手机号尚未注册，请先注册");
            if (!this.getLoginUser().isSysFlag() && !communityId.equals(user.getCommunityId()))
                throw new RuntimeException("此业主不属于该小站");

            fee.setUid(user.getUid());
            fee.setCommunityId(user.getCommunityId());
            // 缴纳费用
            this.moneyService.insertFee(fee, operatorId, operatorName, user.getPublicAddress());
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "缴费成功", ActionResult.Dwz.FEE, false);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("wyFee error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "缴费失败：" + errMsg, ActionResult.Dwz.FEE, false);
        }
        return AppConst.DwzCode.SUCCESS;

    }

    /**
     * 查找带回业主地址信息
     *
     * @return
     */
    public String LookupHouse() {
        boolean isAdmin = this.getLoginUser().isSysFlag();
        if (isAdmin) {
            List<City> cityList = this.communityService.getAllCity();
            request.setAttribute("cityList", cityList);
        } else {
            // 所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            // 获取所有幢信息
            List<Building> buildingList = this.communityService.getBuildingByCId(communityId);
            // 将数据放大request中
            request.setAttribute("communityName", this.communityService.getCommunityName(communityId));
            request.setAttribute("buildingList", buildingList);
        }
        request.setAttribute("ref", ref);
        return "LookupHouse";
    }

    /**
     * 获取小区信息
     *
     * @throws IOException
     */
    public void LoadCommunity() throws IOException {
        List<Object> list = new ArrayList<Object>();
        Object[] obj = new Object[]{0, "请选择小区"};
        list.add(obj);
        if (cityId != null && cityId > 0) {
            List<Community> communityList = this.communityService.getAllCommunityById(cityId);
            for (Community community : communityList) {
                obj = new Object[2];
                obj[0] = community.getCommunityId();
                obj[1] = community.getCommunityName();
                list.add(obj);
            }
        }
        this.getResponseWriter().write(ObjectUtil.getJsonStr(list));
    }

    /**
     * 获取幢信息
     *
     * @throws IOException
     */
    public void loadBuilding() throws IOException {
        List<Object> list = new ArrayList<Object>();
        Object[] obj = new Object[]{0, "请选择幢"};
        list.add(obj);
        if (fee.getCommunityId() != null && fee.getCommunityId() > 0) {
            List<Building> buildingList = this.communityService.getBuildingByCId(fee.getCommunityId());
            for (Building building : buildingList) {
                obj = new Object[2];
                obj[0] = building.getBuildingId();
                obj[1] = building.getBuildingTitle();
                list.add(obj);
            }
        }
        this.getResponseWriter().write(ObjectUtil.getJsonStr(list));
    }

    /**
     * 获取单元信息
     */
    public void loadUnit() throws IOException {
        List<Object> list = new ArrayList<Object>();
        Object[] obj = new Object[]{0, "请选择单元"};
        list.add(obj);
        if (buildingId != null && buildingId > 0) {
            List<Unit> unitList = this.communityService.getUnitByBId(buildingId);
            for (Unit unit : unitList) {
                obj = new Object[2];
                obj[0] = unit.getUnitId();
                obj[1] = unit.getUnitName();
                list.add(obj);
            }
        }
        this.getResponseWriter().write(ObjectUtil.getJsonStr(list));
    }

    /**
     * 获取户号信息
     */
    public void loadHouse() throws IOException {
        List<Object> list = new ArrayList<Object>();
        Object[] obj = new Object[]{0, "请选择户号"};
        list.add(obj);
        if (unitId != null && unitId > 0) {
            List<House> houseList = this.communityService.getHouseByUId(unitId);
            for (House house : houseList) {
                obj = new Object[2];
                obj[0] = house.getHouseId();
                obj[1] = house.getHouseName();
                list.add(obj);
            }
        }
        this.getResponseWriter().write(ObjectUtil.getJsonStr(list));
    }

    /**
     * 获取手机号码
     */
    public void loadPhoneNumber() throws IOException {
        String phoneNumber = "";
        if (fee.getHouseId() != null && fee.getHouseId() > 0) {
            // 根据户号获取用户数据
            log.debug(fee.getHouseId());
            User user = this.userService.getOwnerUserByHouseId(fee.getHouseId());
            if (user != null)
                phoneNumber = user.getPhoneNumber();
        }
        this.convertMessage(phoneNumber);
    }

    /**
     * 获取手机号码
     */
    public void loadPhone() throws IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (fee.getHouseId() != null && fee.getHouseId() > 0) {
            // 根据户号获取用户数据
            List<User> userList = this.userService.getUserByHouseId(fee.getHouseId());
            // 获取户主信息
            User houseUser = this.userService.getOwnerUserByHouseId(fee.getHouseId());
            // 获取户主手机号码
            String housePhoneNumber = houseUser != null ? houseUser.getPhoneNumber() : "-1";
            if (userList.size() > 0) {
                for (User user : userList) {
                    // 手机号
                    String phoneNumber = user.getPhoneNumber();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("uid", user.getUid());
                    map.put("phone", phoneNumber);
                    map.put("HMain", housePhoneNumber.equals(phoneNumber) ? true : false);
                    list.add(map);
                }
            }
        }
        this.convertMessage(list);
    }

    /**
     * 获取户主手机号码
     */
    public void loadMainPhone() throws IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (fee.getHouseId() != null && fee.getHouseId() > 0) {
            // 根据户号获取用户数据
            List<User> userList = this.userService.getUserByHouseId(fee.getHouseId());
            // 获取户主信息
            User houseUser = this.userService.getOwnerUserByHouseId(fee.getHouseId());
            // 获取户主手机号码
            String housePhoneNumber = houseUser != null ? houseUser.getPhoneNumber() : "-1";
            if (userList.size() > 0) {
                for (User user : userList) {
                    // 手机号
                    String phoneNumber = user.getPhoneNumber();
                    if (housePhoneNumber.equals(phoneNumber)) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("uid", user.getUid());
                        map.put("phone", phoneNumber);
                        map.put("HMain", true);
                        list.add(map);
                    }
                }
            }
        }
        this.convertMessage(list);
    }

    /**
     * 发送验证码
     */
    public void sendCode() {
        boolean bool = false;
        try {
            if (StringUtil.isEmpty(phoneNumber))
                throw new RuntimeException("手机号码为空");
            // 发送验证码
            String authcodeStr = this.authcodeService.createAuthcode(phoneNumber);
            bool = !StringUtil.isEmpty(authcodeStr);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("sendRank error " + errMsg);
        }
        this.convertMessage(bool);
    }

    /**
     * 根据手机号获取绑定的卡号
     */
    public void loadCard() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (StringUtil.isEmpty(phoneNumber))
                throw new RuntimeException("手机号码为空");
            if (code == null || code <= 0)
                throw new RuntimeException("验证码不合法");
            // 校验验证码
            boolean bool = this.authcodeService.verrifyAuthcode(phoneNumber, code.toString());
            if (bool) {
                // 根据手机号码获取卡号
                Card card = this.cardService.getCardByPhoneNumber(phoneNumber);
                if (card != null) {
                    map.put("code", 0);
                    map.put("result", card.getCardNo());
                } else
                    throw new RuntimeException("卡数据不存在");
            } else
                throw new RuntimeException("验证码校验失败");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            map.put("code", 1);
            map.put("result", errMsg);
            log.error("checkCode error " + errMsg);
        }
        this.convertMessage(map);
    }

    /**
     * 校验手机验证码
     */
    public void checkPhoneNumberCode() {
        boolean bool = false;
        try {
            if (StringUtil.isEmpty(phoneNumber))
                throw new RuntimeException("手机号码为空");
            if (code == null || code <= 0)
                throw new RuntimeException("验证码不合法");
            // 校验验证码
            bool = this.authcodeService.verrifyAuthcode(phoneNumber, code.toString());
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("checkPhoneNumberCode error " + errMsg);
        }
        this.convertMessage(bool);
    }

    /**
     * 根据手机号获取业主账户的余额
     */
    public void loadAccount() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (StringUtil.isEmpty(phoneNumber))
                throw new RuntimeException("手机号码为空");
            if (code == null || code <= 0)
                throw new RuntimeException("验证码不合法");
            if (fee.getHouseId() <= 0)
                throw new RuntimeException("户号为空");
            // 校验验证码
            boolean bool = this.authcodeService.verrifyAuthcode(phoneNumber, code.toString());
            if (bool) {
                // 根据手机号码获取卡号
                Double account = this.moneyService.getMoneyAccountBalance(fee.getHouseId());
                map.put("code", 0);
                map.put("result", account == null ? 0l : account);
            } else
                throw new RuntimeException("验证码校验失败");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            map.put("code", 1);
            map.put("result", errMsg);
            log.error("checkCode error " + errMsg);
        }
        this.convertMessage(map);
    }

    /**
     * 获取小站可用余额
     */
    public void loadSiteAccount() {
        Double money = 0.0;
        try {
            if (fee.getCommunityId() <= 0)
                throw new RuntimeException("小区编号为空");
            // 根据小区编号获取小站详细信息
            User user = this.userService.getSiteUserByCommunityId(fee.getCommunityId());
            if (user == null)
                throw new RuntimeException("小站数据不存在");
            money = user.getMoney() == null ? 0 : user.getMoney();
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("loadCommunityAccount error " + errMsg);
        }
        this.convertMessage(money);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserDTO getChargeUser() {
        return chargeUser;
    }

    public void setChargeUser(UserDTO chargeUser) {
        this.chargeUser = chargeUser;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Object getModel() {
        if (fee == null)
            fee = new Fee();
        return fee;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
