package cc.kokoko.server.ibutler.web.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cc.kokoko.server.commons.util.MD5Util;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Operator;
import cc.kokoko.server.ibutler.domain.Power;
import cc.kokoko.server.ibutler.domain.admin.AdminUser;
import cc.kokoko.server.ibutler.service.OperatorService;
import cc.kokoko.server.ibutler.service.UserService;

import com.opensymphony.xwork2.ModelDriven;

public class AdminAction extends AdminBaseAction implements ModelDriven {

    private static final long serialVersionUID = -9200546650186490062L;

    @Autowired
    private UserService userService;
    @Autowired
    private OperatorService operatorService;

    private AdminUser user;

    // 进入后端管理首页
    public String doIndex() {
        return "index";
    }

    /**
     * 登录
     *
     * @return
     */
    public String doLogin() {
        try {
            if (this.paramMap == null) {
                paramMap = new HashMap<String, Object>();
            }
            paramMap.put("resultCode", AppConst.ResultCode.SUCCESS);
            if (user == null || StringUtil.isEmpty(user.getUsername())
                    || StringUtil.isEmpty(user.getPassword()))
                throw new RuntimeException("请填写用户名、密码");
            //校验验证码的合法性
            if (StringUtil.isEmpty(seccode)) {
                throw new RuntimeException("请填写验证码");
            } else {
                String rand = this.getSession().getAttribute("rand").toString();
                if (!seccode.equals(rand))
                    throw new RuntimeException("验证码输入不正确");
            }

            String username = user.getUsername();
            String password = MD5Util.getMD5String(user.getPassword());
            //根据用户名密码获取信息
            Operator operator = operatorService.getOperatorByUnamePassword(username, password);
            if (operator == null)
                throw new RuntimeException("用户名或密码错误");

            if (!operator.getUserType().equals(AppConst.UserType.SHOP)
                    && !operator.getUserType().equals(AppConst.UserType.SITE)
                    && !operator.getUserType().equals(AppConst.UserType.CONSULTANT)
                    && !operator.getUserType().equals(AppConst.UserType.OPERATOR)
                    && !operator.getUserType().equals(AppConst.UserType.ADMIN)
                    && !operator.getUserType().equals(AppConst.UserType.SYS_ADMIN))
                throw new RuntimeException("没有后台访问权限");

            if (!operator.getUserFlag().equals(new Byte("1")))
                throw new RuntimeException("用户状态不合法");

            // 验证当前登录的用户是否为管理员
            boolean isAdmin = false;
            if (operator.getUserType().equals(new Byte("9")) || operator.getUserType().equals(new Byte("99")))
                isAdmin = true;

            //设置user数据到session中
            user.setUid(operator.getUid());
            user.setOperatorId(operator.getId());
            user.setUserType(operator.getUserType());
            user.setCommunityId(operator.getCommunityId());
            user.setNickname(operator.getNickname());
            user.setPhoneNumber(operator.getPhoneNumber());
            user.setSysFlag(isAdmin);
            this.getSession().setAttribute("adminUser", user);

            // 修改操作员状态
            //this.operatorService.updateOperator(new Byte("1"),null,operator.getId());

            // 获取所有左侧菜单权限信息
            List<Power> leftPower = this.userService.getAllLeftPower(user.getUid());
            if (leftPower.size() < 0)
                throw new RuntimeException("没有iButler后台操作权限");

            this.getSession().setAttribute("leftPower", leftPower);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error(" doLogin error: " + errMsg);
            paramMap.put("resultCode", AppConst.ResultCode.FAILURE);
            paramMap.put("resultMsg", errMsg);
        }
        //将信息格式化成gson数据并返回页面
        this.convertMessage(paramMap);
        return null;
    }



    /**
     * 注销登录
     *
     * @return
     */
    public String doLogout() {
        this.getSession().invalidate();
        return "login";
    }

    private String seccode;  // 验证码
    private String msg;      // 记录需要反馈的错误信息

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSeccode() {
        return seccode;
    }

    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }

    public Object getModel() {
        if (user == null) {
            user = new AdminUser();
        }
        return user;
    }

    public AdminUser getUser() {
        return user;
    }

    public void setUser(AdminUser user) {
        this.user = user;
    }
}
