package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Building;
import cc.kokoko.server.ibutler.service.BuildingService;
import cc.kokoko.server.ibutler.service.CommunityService;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 幢信息管理
 *
 * @author HHang
 * @version V1.0.1
 */
public class BuildingAction extends AdminBaseAction implements ModelDriven {

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private CommunityService communityService;

    private Building building;

    /**
     * 到达幢信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        // 获取小区编号
        Long communityId = building.getCommunityId();
        // 如果为管理员，则获取小区信息用于查询
        if (this.getLoginUser().isSysFlag())
            request.setAttribute("communityList", this.buildingService.getAllComunity());
        else
            communityId = this.getLoginUser().getCommunityId();

        Map<String, Object> map = this.buildingService.getBuildingRecord(communityId, building.getBuildingTitle(), page.getLineSize(), page.getStartRecord());
        List<Building> list = (List<Building>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("buildingList", list);
        return SUCCESS;
    }

    /**
     * 到达幢信息显示页面
     *
     * @return
     */
    public String buildingTree() {
        if (page == null)
            page = new PageUtil();
        Map<String, Object> map = this.buildingService.getBuildingRecord(building.getCommunityId(), building.getBuildingTitle(), page.getLineSize(), page.getStartRecord());
        List<Building> list = (List<Building>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("buildingList", list);
        request.setAttribute("communityId", building.getCommunityId());
        return "toBuildingTree";
    }

    /**
     * 到达添加页面
     *
     * @return
     */
    public String toInsert() {
        try {
            request.setAttribute("communityList", this.buildingService.getAllComunity());
            return "toInsert";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toInsert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, errMsg, ActionResult.Dwz.BUILDING, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 添加幢信息
     *
     * @return
     */
    public String insert() {
        try {
            if (building == null)
                throw new RuntimeException("building 为空");

            // 如果不是管理员，则设置为当前登录人的小区编号
            if (this.getLoginUser().isSysFlag())
                building.setCommunityId(this.getLoginUser().getCommunityId());

            if (building.getCommunityId() <= 0)
                throw new RuntimeException("communityId 参数错误");

            // 添加小区信息
            this.buildingService.insert(building);
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息添加成功", ActionResult.Dwz.BUILDING, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toInsert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息添加失败：" + errMsg, ActionResult.Dwz.BUILDING, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 检测幢名是否已存在
     *
     * @throws UnsupportedEncodingException
     */
    public void check() throws UnsupportedEncodingException {
        Boolean bool = false;
        try {
            if (building.getCommunityId() < 0)
                throw new RuntimeException("communityId 参数错误");
            if (StringUtil.isEmpty(building.getBuildingTitle()))
                throw new RuntimeException("幢名为空");
            String name = building.getBuildingTitle();
            // 转换字符类型
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            // 检测幢名是否存在
            bool = this.buildingService.checkBuilding(building.getCommunityId(), name);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("check error " + errMsg);
        }
        this.convertMessage(bool);
    }

    public Object getModel() {
        if (building == null)
            building = new Building();
        return building;
    }
}
