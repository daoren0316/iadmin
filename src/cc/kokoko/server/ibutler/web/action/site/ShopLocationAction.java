package cc.kokoko.server.ibutler.web.action.site;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.ShopLocation;
import cc.kokoko.server.ibutler.service.CommunityService;
import cc.kokoko.server.ibutler.service.ShopLocationService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 商户，小区关系管理
 */
public class ShopLocationAction extends AdminBaseAction implements ModelDriven {

    @Autowired
    private ShopLocationService shopLocationService;
    @Autowired
    private CommunityService communityService;

    private ShopLocation shopLocation;

    private String startTime;
    private String endTime;

    /**
     * 到达数据显示页
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取所在小区编号
        // 如果为管理员，则获取小区信息用于查询
        if (this.getLoginUser().isSysFlag()) {
            request.setAttribute("communityList", this.communityService.getAllCommunity());
        } else
            shopLocation.setCommunityId(this.getLoginUser().getCommunityId());

        Map<String, Object> map = this.shopLocationService.getAllShopLocationByParam(shopLocation.getCommunityId(), startTime, endTime, page.getLineSize(), page.getStartRecord());
        List<ShopLocation> list = (List<ShopLocation>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("shopLocationList", list);
        return SUCCESS;
    }

    /**
     * 到达添加页面
     *
     * @return
     */
    public String toInsert() {
        request.setAttribute("communityList", this.communityService.getAllCommunity());
        return "toInsert";
    }

    /**
     * 关联商户，小区信息
     *
     * @return
     */
    public String insert() {
        try {
            // 获取所在小区编号
            Long communityId = this.getLoginUser().getCommunityId();
            if (this.getLoginUser().isSysFlag())
                communityId = shopLocation.getCommunityId();

            // 验证商户是否已接入
            ShopLocation shopLocation1 = this.shopLocationService.getShopLocationByParam(shopLocation.getShopId(), communityId);
            if (shopLocation1 != null)
                throw new RuntimeException("该商户已接入");
            // 添加商户信息
            this.shopLocationService.insert(communityId, shopLocation);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息添加成功", ActionResult.Dwz.SHOP_LOCATION, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.SHOP_LOCATION, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 删除商户，小区关联数据
     *
     * @return
     */
    public String delete() {
        try {
            if (shopLocation.getId() == null || shopLocation.getId() <= 0)
                throw new RuntimeException("传入参数不合法");
            // 删除信息
            this.shopLocationService.delete(shopLocation.getId());
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息删除成功", ActionResult.Dwz.SHOP_LOCATION, false);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("delete error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.SHOP_LOCATION, false);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    public Object getModel() {
        if (shopLocation == null)
            shopLocation = new ShopLocation();
        return shopLocation;
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
}
