package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.domain.dto.OwnerWalletDTO;
import cc.kokoko.server.ibutler.service.AuthcodeService;
import cc.kokoko.server.ibutler.service.MoneyService;
import cc.kokoko.server.ibutler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 业主信息管理
 */
public class HomeOwnerAction extends AdminBaseAction {
    @Autowired
    private UserService userService;
    @Autowired
    private MoneyService moneyService;
    @Autowired
    private AuthcodeService authcodeService;

    private String phoneNumber;
    private Long userStatus;
    private String startTime;
    private String endTime;
    private Long houseId;
    private String publicAddress;
    private Long uid;
    private String rank;

    /**
     * 到达业主信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 如果是管理员类型则设置为空
        Long communityId = this.getCommunityId();
        // 获取留言记录
        Map<String, Object> map = this.userService.getHomeOwnerRecord(communityId, phoneNumber, userStatus, houseId, page.getLineSize(), page.getStartRecord());
        List<User> list = (List<User>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("ownerList", list);
        request.setAttribute("ref", "LookupHouseOwner");
        return SUCCESS;
    }

    /**
     * 到业主钱包信息显示页面
     *
     * @return
     */
    public String toWallet() {
        if (page == null) {
            page = new PageUtil();
        }
        // 如果是管理员类型则设置为空
        Long communityId = this.getCommunityId();

        // 获取留言记录
        Map<String, Object> map = this.moneyService.getOwnerWalletRecord(communityId, phoneNumber, startTime, endTime, houseId, page.getLineSize(), page.getStartRecord());
        List<OwnerWalletDTO> list = (List<OwnerWalletDTO>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("ownerWalletList", list);
        request.setAttribute("ref", "LookupHouseWallet");
        return "toWallet";
    }

    /**
     * 到修改手机号码页面
     *
     * @return
     */
    public String toUppPhoneNumber() {
        try {
            if (uid == null || uid <= 0)
                throw new RuntimeException("用户编号不合法");
            // 获取用户信息
            User user = this.userService.getUserByUid(uid);
            if (user == null)
                throw new RuntimeException("业主信息不存在");
            request.setAttribute("phoneNumber", user.getPhoneNumber());
            return "toUppPhoneNumber";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUppPhoneNumber error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.OWNER, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 修改手机号码
     *
     * @return
     */
    public String uppPhoneNumber() {
        try {
            if (uid == null || uid <= 0)
                throw new RuntimeException("用户编号不合法");
            if (StringUtil.isEmpty(rank))
                throw new RuntimeException("验证码不合法");
            if (StringUtil.isEmpty(phoneNumber))
                throw new RuntimeException("手机号码为空");

            // 验证新手机号码是否已被使用
            User user = this.userService.getUserByPhoneNumber(phoneNumber);
            if (user != null)
                throw new RuntimeException("新手机号已存在");
            // 根据用户编号获取用户信息
            user = this.userService.getUserByUid(uid);
            if (user == null)
                throw new RuntimeException("业主信息不存在");
            // 修改用户信息
            user.setPhoneNumber(phoneNumber);
            user.setUid(uid);
            this.userService.updateUser(user);

            // 发送提示短信
            String content = "感谢您使用微管家一卡通，您的手机号码已变更成功，如有疑问请致电0571-86999823【微管家一卡通】";
            this.authcodeService.sendSMS(phoneNumber, content);

            //设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "修改成功", ActionResult.Dwz.OWNER, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("uppPhoneNumber error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "修改失败：" + errMsg, ActionResult.Dwz.OWNER, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Long userStatus) {
        this.userStatus = userStatus;
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

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
