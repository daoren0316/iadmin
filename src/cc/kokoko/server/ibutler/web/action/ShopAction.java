package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Shop;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.service.*;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 商户数据处理
 *
 * @author HHang
 * @version V1.0.1
 */
public class ShopAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private ConsultantService consultantService;
    @Autowired
    private CommunityService communityService;

    private Shop shop;

    public String oldPassword;
    public String passwrod;

    // 封装上传文件域的属性
    private File image;
    // 封装上传文件类型的属性
    private String imageContentType;
    // 封装上传文件名的属性
    private String imageFileName;

    /**
     * 到达数据显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }

        // 如果为管理员，则获取小区信息用于查询
        if (this.getLoginUser().isSysFlag()) {
            request.setAttribute("communityList", this.communityService.getAllCommunity());
        } else
            shop.setCommunityId(this.getLoginUser().getCommunityId());

        // 获取所有商户信息
        Map<String, Object> map = this.shopService.getAllShopByParam(shop.getCommunityId(), shop.getShopName(), page.getLineSize(), page.getStartRecord());
        List<Shop> list = (List<Shop>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("shopList", list);
        return SUCCESS;
    }

    /**
     * 到达添加页面
     *
     * @return
     */
    public String toInsert() {
        return "toInsert";
    }

    /**
     * 添加商户信息
     *
     * @return
     */
    public String insert() {
        try {
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            // 获取所在小区编号
            Long commmunityId = this.getLoginUser().getCommunityId();
            // 商户类型
            // 1、美食餐饮
            // 2、商品超市
            // 3、美容健康
            // 4、休闲娱乐
            // 5、旅游酒店
            // 添加商户信息
            this.shopService.insert(uid, commmunityId, image, imageFileName, shop.getUsername(), passwrod, shop);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息添加成功", ActionResult.Dwz.SHOP, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "信息添加失败", ActionResult.Dwz.SHOP, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 到达修改登陆密码页面
     *
     * @return
     */
    public String toChangePwd() {
        return "toChangePwd";
    }

    /**
     * 修改商户密码
     *
     * @return
     */
    public String changePwd() {
        try {
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            // 操作员编号
            Long operatorId = this.getLoginUser().getOperatorId();
            // 获取当前登录用户的密码
            String pwd = this.getLoginUser().getPassword();
            // 验证旧密码是否输入正确
            if (!pwd.equals(oldPassword))
                throw new RuntimeException("旧密码输入不正确");
            // 修改新密码
            this.userService.changePassword(uid, passwrod, operatorId);
            //设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "登陆密码修改成功！", true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("changePwd error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "登陆密码修改失败！", true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 到达修改商户信息页面
     *
     * @return
     */
    public String toUpdate() {
        // 获取当前登录的用户编号
        Long uid = this.getLoginUser().getUid();
        // 获取用户详细信息
        User user = this.userService.getUserByUid(uid);
        request.setAttribute("phoneNumber", user.getPhoneNumber());
        // 用户类型 1商户,2服务小站,3,生活顾问,9管理员,99超级管理员
        Byte userType = user.getUserType();
        // 记录跳转路径
        String forword = "";
        switch (userType) {
            case 1:
                // 获取商户信息 （由商户编号和用户编号相同则直接通过用户编号取值）
                request.setAttribute("shop", this.shopService.getShopById(uid));
                forword = "toShop";
                break;
            case 2:
                // 获取服务小站信息
                request.setAttribute("site", this.siteService.getSiteDetailByCommunityId(user.getCommunityId()));
                forword = "toSite";
                break;
            case 3:
                //1 小区律师 2 社区医生 3 旅游向导 4 理财专家 5 置业顾问
                // 获取生活顾问信息
                request.setAttribute("consultant", this.consultantService.getConsultantById(uid));
                forword = "toConsultant";
                break;
        }
        return forword;
    }

    /**
     * 到达数据修改页面
     *
     * @return
     */
    public String toUpp() {
        try {
            Long shopId = shop.getShopId();
            if (shopId == null || shopId <= 0)
                throw new RuntimeException("编号不合法");
            // 获取商户信息 （由商户编号和用户编号相同则直接通过用户编号取值）
            request.setAttribute("shop", this.shopService.getShopById(shopId));
            return "toShop";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpp error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.SHOP, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 修改商户信息
     *
     * @return
     */
    public String update() {
        try {
            if (shop == null)
                throw new RuntimeException("Shop 数据为空");
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            // 修改商户信息
            this.shopService.updateShop(uid, image, imageFileName, shop);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "商户信息修改成功", true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "商户信息修改失败", true);
        }
        return AppConst.DwzCode.SUCCESS;
    }


    /**
     * 检查输入的旧密码是否正确
     */
    public void checkPwd() {
        boolean bool = false;
        try {
            if (StringUtil.isEmpty(oldPassword))
                throw new RuntimeException("输入密码不能为空");
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            // 获取当前登录用户的密码
            String pwd = this.getLoginUser().getPassword();
            // 验证旧密码是否输入正确
            if (!pwd.equals(oldPassword))
                throw new RuntimeException("旧密码输入不正确");
            bool = true;
        } catch (RuntimeException e) {
            log.error("checkPwd error " + e.getMessage());
        }
        //将信息格式化成gson数据并返回页面
        this.convertMessage(bool);
    }

    /**
     * 删除商户信息
     *
     * @return
     */
    public String delete() {
        try {
            if (shop.getShopId() == null || shop.getShopId() <= 0)
                throw new RuntimeException("商户编号不合法");
            // 修改商户信息
            this.shopService.delete(shop.getShopId());
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息删除成功", ActionResult.Dwz.SHOP, false);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("delete error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.SHOP, false);
        }
        return AppConst.DwzCode.SUCCESS;
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Object getModel() {
        if (shop == null)
            shop = new Shop();
        return shop;
    }
}
