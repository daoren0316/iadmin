package cc.kokoko.server.ibutler.web.action.site;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Pos;
import cc.kokoko.server.ibutler.domain.Shop;
import cc.kokoko.server.ibutler.service.CommunityService;
import cc.kokoko.server.ibutler.service.PosService;
import cc.kokoko.server.ibutler.service.ShopService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 商户绑定Pos机
 *
 * @author HHang
 * @version V1.0.1
 */
@SuppressWarnings("ALL")
public class PosAction extends AdminBaseAction implements ModelDriven {

    @Autowired
    private PosService posService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CommunityService communityService;

    private Pos pos;
    private Long communityId;

    /**
     * 查询pos机绑定信息
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
            communityId = this.getLoginUser().getCommunityId();
        // 获取pos机绑定信息
        Map<String, Object> map = this.posService.getPosRecord(communityId, pos.getPosNo(), page.getLineSize(), page.getStartRecord());
        List<Pos> list = (List<Pos>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("posList", list);
        return SUCCESS;
    }

    /**
     * 到达数据添加页面
     *
     * @return
     */
    public String toInsert() {
        return "toInsert";
    }

    /**
     * 绑定商户POS机
     *
     * @return
     */
    public String insert() {
        try {
            if (pos == null)
                throw new RuntimeException("Pos 数据为空");
            // 绑定商户POS机
            this.posService.insertPos(pos);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "POS机绑定成功", ActionResult.Dwz.POS, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "POS机绑定失败：" + errMsg, ActionResult.Dwz.POS, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 到达数据修改页面
     *
     * @return
     */
    public String toUpdate() {
        try {
            if (StringUtil.isEmpty(pos.getPosNo()))
                throw new RuntimeException("POS机编号为空");
            // 根据商户编号获取pos绑定信息
            pos = this.posService.getOneByPosNo(pos.getPosNo());
            return "toUpdate";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpdate error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.POS, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 修改商户绑定Pos信息
     *
     * @return
     */
    public String update() {
        try {
            if (pos == null)
                throw new RuntimeException("POS 数据为空");
            if (StringUtil.isEmpty(pos.getPosNo()))
                throw new RuntimeException("POS机编号为空");
            // 修改绑定POS机信息
            this.posService.updatePos(pos);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息修改成功", ActionResult.Dwz.POS, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "信息修改失败：" + errMsg, ActionResult.Dwz.POS, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 删除POS机绑定信息
     *
     * @return
     */
    public String delete() {
        try {
            if (pos == null)
                throw new RuntimeException("POS 数据为空");
            if (StringUtil.isEmpty(pos.getPosNo()))
                throw new RuntimeException("POS 机编号为空");
            // 删除POS机绑定信息
            this.posService.deletePos(pos);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息删除成功", ActionResult.Dwz.POS);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "信息删除失败", ActionResult.Dwz.POS);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 查找带回商户信息
     *
     * @return
     */
    public String lookupShop() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取所在小区编号 ,如果是管理员则不做小区限制
        Long communityId = this.getCommunityId();
        // 获取所有商户信息
        Map<String, Object> map = this.shopService.getAllShopByParam(null, pos.getShopName(), page.getLineSize(), page.getStartRecord());
        List<Shop> list = (List<Shop>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("shopList", list);
        return "lookupShop";
    }

    /**
     * 检查POS机是否已绑定商户
     */
    public void checkPosNo() {
        boolean bool = false;
        try {
            if (StringUtil.isEmpty(pos.getPosNo()))
                throw new RuntimeException("POS机编号为空");
            Pos pos1 = this.posService.getOneByPosNo(pos.getPosNo());
            if (pos1 == null)
                bool = true;
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("checkPosNo error " + errMsg);
        }
        this.convertMessage(bool);
    }

    public Object getModel() {
        if (pos == null)
            pos = new Pos();
        return pos;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }
}
