package cc.kokoko.server.ibutler.web.action.system;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.UserType;
import cc.kokoko.server.ibutler.service.RoleService;
import cc.kokoko.server.ibutler.service.UserTypeService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 用户类型信息管理
 *
 * @author HHang
 * @version V1.0.1
 */
public class UserTypeAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private UserTypeService userTypeService;
    @Autowired
    private RoleService roleService;

    private UserType userType;

    private Long[] role;

    /**
     * 到达信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        Map<String, Object> map = this.userTypeService.getUserTypeRecord(page.getLineSize(), page.getStartRecord());
        List<UserType> list = null;
        int count = 0;
        if (map != null) {
            list = (List<UserType>) map.get("list");
            count = Integer.valueOf(map.get("count").toString());
        }
        page.setAllRecorders(count);
        request.setAttribute("userTypeList", list);
        return SUCCESS;
    }

    /**
     * 到时数据添加页面
     *
     * @return
     */
    public String toInsert() {
        // 获取启用的角色信息
        request.setAttribute("roleList", this.roleService.getAllRole());
        return "toInsert";
    }

    /**
     * 添加数据
     *
     * @return
     */
    public String insert() {
        try {
            if (userType == null)
                throw new RuntimeException("userType 为空");
            // 添加角色信息
            this.userTypeService.insert(userType, role);
            this.dwz = new DwzUtil("200", "添加成功", "type", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil("300", "添加失败", "type", "closeCurrent");
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
            if (userType.getTypeId() < 1)
                throw new RuntimeException("类型编号不合法");
            // 根据编号获取用户类型数据
            request.setAttribute("type", this.userTypeService.getUserTypeById(userType.getTypeId()));
            // 获取启用的角色信息
            request.setAttribute("roleList", this.roleService.getAllRole());
            // 获取已有的角色信息
            request.setAttribute("hasRole", this.userTypeService.getUserRoleByParam(userType.getTypeId()));
            return "toUpdate";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpdate error " + errMsg);
            this.dwz = new DwzUtil("300", errMsg, "role", "closeCurrent");
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 修改用户类型信息
     *
     * @return
     */
    public String update() {
        try {
            if (userType == null)
                throw new RuntimeException("userType 为空");
            if (userType.getTypeId() < 1)
                throw new RuntimeException("typeId 不合法");
            // 添加角色信息
            this.userTypeService.update(userType, role);
            this.dwz = new DwzUtil("200", "修改成功", "type", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil("300", "修改失败", "type", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 检测用户类型名称是否存在
     *
     * @throws UnsupportedEncodingException
     */
    public void check() throws UnsupportedEncodingException {
        Boolean bool = false;
        try {
            if (StringUtil.isEmpty(userType.getTypeName()))
                throw new RuntimeException("类型名称为空");
            String name = userType.getTypeName();
            // 转换字符类型
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            bool = this.userTypeService.checkTypeName(name, userType.getTypeId());
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("check error " + errMsg);
        }
        this.convertMessage(bool);
    }

    /**
     * 检测用户类型标识是否存在
     *
     * @throws UnsupportedEncodingException
     */
    public void checkFlag() throws UnsupportedEncodingException {
        Boolean bool = false;
        try {
            if (userType.getTypeFlag() < 1)
                throw new RuntimeException("类型标识不合法");
            // 检测用户类型标识是否存在
            bool = this.userTypeService.checkTypeFlag(userType.getTypeFlag(), userType.getTypeId());
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("checkFlag error " + errMsg);
        }
        this.convertMessage(bool);
    }

    public Object getModel() {
        if (userType == null)
            userType = new UserType();
        return userType;
    }

    public Long[] getRole() {
        return role;
    }

    public void setRole(Long[] role) {
        this.role = role;
    }
}
