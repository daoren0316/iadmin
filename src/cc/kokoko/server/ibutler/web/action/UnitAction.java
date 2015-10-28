package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Unit;
import cc.kokoko.server.ibutler.service.CommunityService;
import cc.kokoko.server.ibutler.service.UnitService;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 单元信息
 *
 * @author HHang
 * @version V1.0.1
 */
public class UnitAction extends AdminBaseAction implements ModelDriven {

    @Autowired
    private UnitService unitService;
    @Autowired
    private CommunityService communityService;

    private Unit unit;

    /**
     * 到达单元信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        // 获取小区编号
        Long communityId = unit.getCommunityId();
        // 如果为管理员，则获取小区信息用于查询
        if (this.getLoginUser().isSysFlag()) {
            request.setAttribute("communityList", this.unitService.getAllComunity());
        } else
            communityId = this.getLoginUser().getCommunityId();

        Map<String, Object> map = this.unitService.getUnitReocrd(communityId, unit.getBuildingId(), unit.getUnitName(), page.getLineSize(), page.getStartRecord());
        List<Unit> list = (List<Unit>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("unitList", list);
        // 如果communitId不为空，则获取幢信息
        if (communityId != null && communityId > 0) {
            request.setAttribute("buildingList", this.communityService.getBuildingByCId(communityId));
        }
        return SUCCESS;
    }

    /**
     * 到达单元信息显示页面
     *
     * @return
     */
    public String unitTree() {
        if (page == null)
            page = new PageUtil();
        Map<String, Object> map = this.unitService.getUnitReocrd(unit.getCommunityId(), unit.getBuildingId(), unit.getUnitName(), page.getLineSize(), page.getStartRecord());
        List<Unit> list = (List<Unit>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("unitList", list);
        request.setAttribute("buildingId", unit.getBuildingId());
        return "toUnitTree";
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
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.UNIT, true);
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
            if (unit == null)
                throw new RuntimeException("unit 为空");
            if (unit.getBuildingId() < 0)
                throw new RuntimeException("buildingId 参数错误");
            // 添加小区信息
            this.unitService.insert(unit);
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息添加成功", ActionResult.Dwz.UNIT, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toInsert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "信息添加失败：" + errMsg, ActionResult.Dwz.UNIT, true);
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
            if (unit.getBuildingId() < 0)
                throw new RuntimeException("buildingId 参数错误");
            if (StringUtil.isEmpty(unit.getUnitName()))
                throw new RuntimeException("单元名为空");
            String name = unit.getUnitName();
            // 转换字符类型
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            // 检测幢名是否存在
            bool = this.unitService.checkUnit(unit.getBuildingId(), name);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("check error " + errMsg);
        }
        this.convertMessage(bool);
    }

    public Object getModel() {
        if (unit == null)
            unit = new Unit();
        return unit;
    }
}
