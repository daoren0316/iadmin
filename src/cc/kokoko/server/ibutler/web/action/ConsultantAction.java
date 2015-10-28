package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Consultant;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.service.ConsultantService;
import cc.kokoko.server.ibutler.service.UserService;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 生活顾问信息管理
 *
 * @author HHang
 * @version V1.0.1
 */
public class ConsultantAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private ConsultantService consultantService;
    @Autowired
    private UserService userService;

    private Consultant consultant;
    // 封装上传文件域的属性
    private File image;
    // 封装上传文件类型的属性
    private String imageContentType;
    // 封装上传文件名的属性
    private String imageFileName;

    private String username; //登录名
    private String password; //登录密码
    private Long gender; //性别
    private String navTab;

    /**
     * 到达信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        // 获取小区编号
        Long communityId = this.getLoginUser().getCommunityId();
        // 获取生活顾问信息
        Map<String, Object> map = this.consultantService.getConsultantRecord(communityId, consultant.getConsultantName(), page.getLineSize(), page.getStartRecord());
        List<Consultant> list = (List<Consultant>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("consultantList", list);
        return SUCCESS;
    }

    /**
     * 到达信息添加页面
     *
     * @return
     */
    public String toInsert() {
        return "toInsert";
    }

    /**
     * 添加顾问信息
     *
     * @return
     */
    public String insert() {
        try {
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            // 获取当前登录的所属小区
            Long communityId = this.getLoginUser().getCommunityId();
            gender = gender == null ? 1 : gender;
            // 添加服务小站信息
            this.consultantService.insert(uid, communityId, image, imageFileName, consultant, username, password, gender);
            //设置页面提示信息
            this.dwz = new DwzUtil("200", "信息添加成功！", "consultant", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil("300", "信息添加失败！", "consultant", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 到达修改页面
     *
     * @return
     */
    public String toUpdate() {
        try {
            if (consultant.getConsultantId() <= 0)
                throw new RuntimeException("编号不合法");
            Long uid = consultant.getConsultantId();
            // 获取用户详细信息
            User user = this.userService.getUserByUid(uid);
            request.setAttribute("phoneNumber", user.getPhoneNumber());
            request.setAttribute("username", user.getUsername());
            request.setAttribute("consultant", this.consultantService.getConsultantById(uid));
            request.setAttribute("navTab", "consultant");
            return "toUpdate";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpdate error " + errMsg);
            this.dwz = new DwzUtil("300", errMsg, "closeCurrent");
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 修改生活顾问数据
     *
     * @return
     */
    public String update() {
        try {
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            if (consultant.getConsultantId() <= 0)
                throw new RuntimeException("编号不合法");
            // 修改服务小站信息
            this.consultantService.update(uid, image, imageFileName, consultant);
            navTab = StringUtil.isEmpty(navTab) ? "" : "consultant";
            //设置页面提示信息
            this.dwz = new DwzUtil("200", "信息修改成功！", navTab, "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil("300", "信息修改失败！", navTab, "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 删除顾问数据
     *
     * @return
     */
    public String delete() {
        try {
            if (consultant.getConsultantId() <= 0)
                throw new RuntimeException("编号不合法");
            Long uid = consultant.getConsultantId();
            // 修改服务小站信息
            this.consultantService.delete(uid);
            //设置页面提示信息
            this.dwz = new DwzUtil("200", "信息删除成功！", "consultant", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("delete error " + errMsg);
            this.dwz = new DwzUtil("300", "信息删除失败！", "consultant", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    public Object getModel() {
        if (consultant == null)
            consultant = new Consultant();
        return consultant;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }
}
