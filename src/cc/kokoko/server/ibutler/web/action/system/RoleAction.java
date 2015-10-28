package cc.kokoko.server.ibutler.web.action.system;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Power;
import cc.kokoko.server.ibutler.domain.Role;
import cc.kokoko.server.ibutler.service.PowerService;
import cc.kokoko.server.ibutler.service.RoleService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 角色信息管理
 *
 * @author HHang
 * @version V1.0.1
 */
public class RoleAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PowerService powerServic;

    private Role role;

    private String[] power;

    /**
     * 到达显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        Map<String, Object> map = this.roleService.getRoleRecord(role.getRoleName(), page.getLineSize(), page.getStartRecord());
        List<Role> list = null;
        int count = 0;
        if (map != null) {
            list = (List<Role>) map.get("list");
            count = Integer.valueOf(map.get("count").toString());
        }
        page.setAllRecorders(count);
        request.setAttribute("roleList", list);
        return SUCCESS;
    }

    /**
     * 到达数据添加页面
     *
     * @return
     */
    public String toInsert() {
        // 获取所有顶级权限
        List<Power> powerList = this.powerServic.getAllPower();
        // 构建权限的树形结构
        String tree = this.powerServic.buildTree(true, powerList, 0L);
        // 将树形信息放到request中
        request.setAttribute("tree", tree);
        return "toInsert";
    }

    /**
     * 添加角色信息
     *
     * @return
     */
    public String insert() {
        try {
            if (role == null)
                throw new RuntimeException("role 为空");
            // 添加角色信息
            this.roleService.insert(role, power);
            this.dwz = new DwzUtil("200", "角色添加成功", "role", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil("300", "角色添加失败", "role", "closeCurrent");
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
            if (role.getRoleId() < 0)
                throw new RuntimeException("角色编号 为空");
            // 根据编号获取角色信息
            request.setAttribute("role", this.roleService.getRoleById(role.getRoleId()));
            // 获取所有顶级权限
            List<Power> powerList = this.powerServic.getAllPower();
            // 构建权限的树形结构
            String tree = this.powerServic.buildTree(true, powerList, role.getRoleId());
            // 将树形信息放到request中
            request.setAttribute("tree", tree);
            return "toUpdate";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpdate error " + errMsg);
            this.dwz = new DwzUtil("300", errMsg, "role", "closeCurrent");
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 修改角色信息
     *
     * @return
     */
    public String update() {
        try {
            if (role == null)
                throw new RuntimeException("role 为空");
            if (role.getRoleId() < 1)
                throw new RuntimeException("roleId 不合法");
            // 添加角色信息
            this.roleService.update(role, power);
            this.dwz = new DwzUtil("200", "角色修改成功", "role", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil("300", "角色修改失败", "role", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 检测角色名是否存在
     *
     * @throws UnsupportedEncodingException
     */
    public void check() throws UnsupportedEncodingException {
        Boolean bool = false;
        try {
            if (StringUtil.isEmpty(role.getRoleName()))
                throw new RuntimeException("角色名为空");
            String name = role.getRoleName();
            // 转换字符类型
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            bool = this.roleService.checkRole(name,role.getRoleId());
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("check error " + errMsg);
        }
        this.convertMessage(bool);
    }

    public Object getModel() {
        if (role == null)
            role = new Role();
        return role;
    }

    public String[] getPower() {
        return power;
    }

    public void setPower(String[] power) {
        this.power = power;
    }
}
