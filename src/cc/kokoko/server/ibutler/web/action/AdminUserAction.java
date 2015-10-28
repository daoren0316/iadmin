package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Operator;
import cc.kokoko.server.ibutler.service.CommunityService;
import cc.kokoko.server.ibutler.service.OperatorService;
import cc.kokoko.server.ibutler.service.UserService;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
public class AdminUserAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private UserService userService;
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private CommunityService communityService;

    private Operator operator;

    /**
     * 到信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取操作员记录
        Map<String, Object> map = this.operatorService.getOperatorRecord(operator.getCommunityId(), operator.getPhoneNumber(), operator.getNickname(), page.getLineSize(), page.getStartRecord());
        List<Operator> list = (List<Operator>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("operatorList", list);
        request.setAttribute("communityList", this.communityService.getAllCommunity());
        return SUCCESS;
    }

    /**
     * 到达添加页面
     *
     * @return
     */
    public String toInsert() {
        // 获取所有小区信息
        request.setAttribute("communityList", this.communityService.getAllCommunity());
        return "toInsert";
    }

    /**
     * 添加数据
     *
     * @return
     */
    public String insert() {
        try {
            //
            this.operatorService.insert(operator);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息添加成功", ActionResult.Dwz.OPERATOR, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "信息添加失败", ActionResult.Dwz.OPERATOR, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 到修改页面
     *
     * @return
     */
    public String toUpdate() {
        try {
            Long id = operator.getId();
            if (id == null || id <= 0)
                throw new RuntimeException("编号不合法");
            //
            request.setAttribute("operator", this.operatorService.getOperatorById(id));
            // 获取所有小区信息
            request.setAttribute("communityList", this.communityService.getAllCommunity());

            return "toUpdate";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpdate error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.OPERATOR, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 修改信息
     *
     * @return
     */
    public String update() {
        try {
            Long id = operator.getId();
            if (id == null || id <= 0)
                throw new RuntimeException("编号不合法");
            //
            this.operatorService.update(operator);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息修改成功", ActionResult.Dwz.OPERATOR, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "信息修改失败", ActionResult.Dwz.OPERATOR, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 删除信息
     *
     * @return
     */
    public String delete() {
        try {
            Long id = operator.getId();
            if (id == null || id <= 0)
                throw new RuntimeException("编号不合法");
            // 修改商户信息
            this.operatorService.delete(id);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息删除成功", ActionResult.Dwz.OPERATOR, false);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("delete error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.OPERATOR, false);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 禁用操作员
     *
     * @return
     */
    public String forbidden() {
        try {
            Long id = operator.getId();
            if (id == null || id <= 0)
                throw new RuntimeException("编号不合法");
            Operator operator1 = this.operatorService.getOperatorById(id);
            Byte userFlag = new Byte("1");
            boolean isForbidden = false;
            if (operator1.getUserFlag().equals(new Byte("1"))) {
                userFlag = new Byte("2");
                isForbidden = true;
            }
            // 修改商户信息
            this.operatorService.updateOperator(null, userFlag, id);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, isForbidden ? "禁用成功" : "启用成功", ActionResult.Dwz.OPERATOR, false);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("forbidden error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.OPERATOR, false);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 检测用户名是否存在
     *
     * @return
     */
    public void checkOperator() {
        boolean bool = false;
        try {
            bool = this.operatorService.checkOperatorByName(operator.getUsername());
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("checkOperator error " + e.getMessage());
        }
        this.convertMessage(bool);
    }

    /**
     * 检测用户名是否存在
     *
     * @return
     */
    public void checkUser() {
        boolean bool = false;
        try {
            bool = this.userService.checkUserByName(operator.getUsername());
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("checkUser error " + e.getMessage());
        }
        this.convertMessage(bool);
    }

    public Object getModel() {
        if (operator == null)
            operator = new Operator();
        return operator;
    }
}
