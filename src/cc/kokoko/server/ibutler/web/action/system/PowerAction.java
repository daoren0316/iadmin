package cc.kokoko.server.ibutler.web.action.system;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Power;
import cc.kokoko.server.ibutler.service.PowerService;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 权限信息管理
 *
 * @author HHang
 * @version V1.0.1
 */
public class PowerAction extends AdminBaseAction implements ModelDriven {

    @Autowired
    private PowerService powerService;

    private Power power;

    private Long rank; //等级
    private Long maxable; //最大化
    private Long minable; //最小化
    private Long mask; //遮盖
    private Long resizable;//可变大小
    private Long drawable; //拖动
    private String term;
    private Long oldParent;

    /**
     * 到达显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        Map<String, Object> map = this.powerService.getPowerRecord(power.getPowerName(), page.getLineSize(), page.getStartRecord());
        List<Power> list = null;
        int count = 0;
        if (map != null) {
            list = (List<Power>) map.get("list");
            count = Integer.valueOf(map.get("count").toString());
        }
        page.setAllRecorders(count);
        request.setAttribute("powerList", list);
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
     * 添加权限信息
     *
     * @return
     */
    public String insert() {
        try {
            if (rank == null || (rank != 0 && rank != 5))
                throw new RuntimeException("等级参数不合法");
            if (rank == 5) {
                // 如果是子权限，则把权限等级加 1
                power.setPowerLevel(power.getPowerLevel() + 1);
            }
            // 如果target不为ajaxTodo 和 selectedTodo，则设置启用设置
            if (!"ajaxTodo".equals(power.getTarget()) && !"selectedTodo".equals(power.getTarget()) && !"dwzExport".equals(power.getTarget())) {
                power.setTitle(power.getPowerName());
            }
            // 如果target不为dialog，则设置启用设置
            if (!"dialog".equals(power.getTarget())) {
                power.setWidth(0L);
                power.setHeight(0L);
                power.setAttribute("");
            } else {
                maxable = 1L;
                minable = 1L;
                mask = mask != null ? 1 : 0L;
                resizable = resizable != null ? 1 : 0L;
                drawable = drawable != null ? 1 : 0L;
                String attr = maxable + "," + minable.toString() + "," + mask.toString() + "," + drawable.toString() + "," + resizable.toString();
                // 拼接属性值
                power.setAttribute(attr);
            }
            // 添加权限信息
            this.powerService.insert(power);
            this.dwz = new DwzUtil("200", "权限添加成功！", "power", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil("300", "权限添加失败！", "power", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 到达信息修改页面
     *
     * @return
     */
    public String toUpdate() {
        try {
            if (power.getPowerId() < 1)
                throw new RuntimeException("编号不合法");
            // 根据编号获取权限信息
            Power power1 = this.powerService.getPowerById(power.getPowerId());
            String attr = StringUtil.isEmpty(power1.getAttribute()) ? "0,0,0,0,0" : power1.getAttribute();
            String[] param = attr.split(",");
            maxable = Long.valueOf(param[0]);
            minable = Long.valueOf(param[1]);
            mask = Long.valueOf(param[2]);
            drawable = Long.valueOf(param[3]);
            resizable = Long.valueOf(param[4]);
            request.setAttribute("power", power1);
            return "toUpdate";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpdate error " + errMsg);
            this.dwz = new DwzUtil("300", errMsg, "power", "closeCurrent");
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 修改权限信息
     *
     * @return
     */
    public String update() {
        try {
            if (rank == null || (rank != 0 && rank != 5))
                throw new RuntimeException("等级参数不合法");

            if (!oldParent.equals(power.getParent()) && rank == 5) {
                Long level = power.getPowerLevel() + 1;
                // 如果是子权限，则把权限等级加 1
                power.setPowerLevel(level > 2 ? 2 : level);
            }
            // 如果target不为ajaxTodo，则设置启用设置
            if (!"ajaxTodo".equals(power.getTarget()) && !"selectedTodo".equals(power.getTarget()) && !"dwzExport".equals(power.getTarget())) {
                power.setTitle(power.getPowerName());
            }
            // 如果target不为dialog，则设置启用设置
            if (!"dialog".equals(power.getTarget())) {
                power.setWidth(0L);
                power.setHeight(0L);
                power.setAttribute("");
            } else {
                maxable = 1L;
                minable = 1L;
                mask = mask != null ? 1 : 0L;
                resizable = resizable != null ? 1 : 0L;
                drawable = drawable != null ? 1 : 0L;
                String attr = maxable + "," + minable.toString() + "," + mask.toString() + "," + drawable.toString() + "," + resizable.toString();
                // 拼接属性值
                power.setAttribute(attr);
            }
            // 添加权限信息
            this.powerService.update(power);
            this.dwz = new DwzUtil("200", "权限修改成功！", "power", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil("300", "权限修改失败！", "power", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 获取权限的树形数据
     *
     * @return
     */
    public String load() {
        // 获取所有顶级权限
        List<Power> powerList = this.powerService.getAllPower();
        // 设置树形数据到request中
        request.setAttribute("tree", this.powerService.buildTree(true, powerList));
        return "load";
    }

    /**
     * 获取操作权限
     */
    public void todo() throws IOException {
        // 获取当前登录用户的编号
        Long uid = this.getLoginUser().getUid();
        // 获取项目绝对路径
        String path = request.getContextPath();

        // 根据编号获取操作权限
        String str = this.powerService.loadTodoPower(path, term, uid);
        // JSONObject jsonObject;
        this.getResponseWriter().write(ObjectUtil.getJsonStr(str));
    }

    /**
     * 删除权限信息
     *
     * @return
     */
    public String delete() {
        try {
            if (power.getPowerId() < 1)
                throw new RuntimeException("编号不合法");
            // 删除活动
            this.powerService.delete(power.getPowerId());
            this.dwz = new DwzUtil("200", "信息删除成功", "power", "");
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("delete error " + errMsg);
            this.dwz = new DwzUtil("300", "信息删除失败", "power", "");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    public Object getModel() {
        if (power == null)
            power = new Power();
        return power;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getMaxable() {
        return maxable;
    }

    public void setMaxable(Long maxable) {
        this.maxable = maxable;
    }

    public Long getMinable() {
        return minable;
    }

    public void setMinable(Long minable) {
        this.minable = minable;
    }

    public Long getMask() {
        return mask;
    }

    public void setMask(Long mask) {
        this.mask = mask;
    }

    public Long getResizable() {
        return resizable;
    }

    public void setResizable(Long resizable) {
        this.resizable = resizable;
    }

    public Long getDrawable() {
        return drawable;
    }

    public void setDrawable(Long drawable) {
        this.drawable = drawable;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Long getOldParent() {
        return oldParent;
    }

    public void setOldParent(Long oldParent) {
        this.oldParent = oldParent;
    }
}
