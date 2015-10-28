package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.House;
import cc.kokoko.server.ibutler.service.CommunityService;
import cc.kokoko.server.ibutler.service.UnitService;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 户信息
 *
 * @author HHang
 * @version V1.0.1
 */
public class HouseAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private UnitService unitService;
    @Autowired
    private CommunityService communityService;

    private House house;
    private Long buildingId;

    /**
     * 到达单元信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        Map<String, Object> map = this.unitService.getHouseReocrd(house.getCommunityId(), buildingId, house.getUnitId(), house.getHouseName(), page.getLineSize(), page.getStartRecord());
        List<House> list = (List<House>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("communityList", this.unitService.getAllComunity());
        // 如果communitId不为空，则获取幢信息
        if (house.getCommunityId() != null && house.getCommunityId() > 0) {
            request.setAttribute("buildingList", this.communityService.getBuildingByCId(house.getCommunityId()));
        }
        // 如果buildingId不为空，则获取单元信息
        if (buildingId != null && buildingId > 0) {
            request.setAttribute("unitList", this.communityService.getUnitByBId(buildingId));
        }
        request.setAttribute("houseList", list);
        return SUCCESS;
    }

    /**
     * 到达单元信息显示页面
     *
     * @return
     */
    public String houseTree() {
        if (page == null)
            page = new PageUtil();
        Map<String, Object> map = this.unitService.getHouseReocrd(house.getCommunityId(), buildingId, house.getUnitId(), house.getHouseName(), page.getLineSize(), page.getStartRecord());
        List<House> list = (List<House>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("houseList", list);
        request.setAttribute("unitId", house.getUnitId());
        return "toHouseTree";
    }

    /**
     * 到达添加页面
     *
     * @return
     */
    public String toInsert() {
        try {
            request.setAttribute("communityList", this.unitService.getAllComunity());
            return "toInsert";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toInsert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.HOUSE, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 添加单元信息
     *
     * @return
     */
    public String insert() {
        try {
            if (house == null)
                throw new RuntimeException("unit 为空");
            if (house.getUnitId() < 0)
                throw new RuntimeException("unitId 参数错误");
            // 添加小区信息
            this.unitService.insertHouse(house);
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息添加成功", ActionResult.Dwz.HOUSE, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "信息添加失败：" + errMsg, ActionResult.Dwz.HOUSE, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 检测单元名是否已存在
     *
     * @throws UnsupportedEncodingException
     */
    public void check() throws UnsupportedEncodingException {
        Boolean bool = false;
        try {
            if (house.getUnitId() < 0)
                throw new RuntimeException("unitId 参数错误");
            if (StringUtil.isEmpty(house.getHouseName()))
                throw new RuntimeException("单元名为空");
            String name = house.getHouseName();
            // 转换字符类型
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            // 检测幢名是否存在
            bool = this.unitService.checkHouse(house.getUnitId(), name);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("check error " + errMsg);
        }
        this.convertMessage(bool);
    }

    public Object getModel() {
        if (house == null)
            house = new House();
        return house;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
