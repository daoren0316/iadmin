package cc.kokoko.server.ibutler.web.action.site;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.Activity;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.dto.AttachmentDTO;
import cc.kokoko.server.ibutler.service.ActivityService;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 活动信息管理
 *
 * @author HHang
 * @version V1.0.1
 */
public class ActivityAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private ActivityService activityService;

    private Activity activity;
    private String startTime;
    private String endTime;

    // 封装上传文件域的属性
    private File titleImage;
    // 封装上传文件类型的属性
    private String titleImageContentType;
    // 封装上传文件名的属性
    private String titleImageFileName;

    // 封装上传文件域的属性
    private File[] image;
    // 封装上传文件类型的属性
    private String[] imageContentType;
    // 封装上传文件名的属性
    private String[] imageFileName;


    /**
     * 到达信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        // 获取当前登录用户的编号
        Long uid = this.getLoginUser().getUid();
        // 获取活动信息
        Map<String, Object> map = this.activityService.getActivityRecord(uid, startTime, endTime, page.getLineSize(), page.getStartRecord());
        List<Activity> list = (List<Activity>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("activityList", list);
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
     * 添加活动信息
     *
     * @return
     */
    public String insert() {
        try {
            // 获取当前登录用户的编号
            Long uid = this.getLoginUser().getUid();
            // 发布活动
            this.activityService.insert(uid, titleImage, titleImageFileName, image, imageFileName, activity);
            log.debug(activity.getActivityDesc());
            this.dwz = new DwzUtil("200", "活动发布成功", "activity", "closeCurrent");
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil("300", "活动发布失败", "activity", "closeCurrent");
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
            Long activityId = activity.getActivityId();
            if (activityId == null || activityId <= 0)
                throw new RuntimeException("编号不合法");
            // 根据编号获取活动信息
            Activity activity1 = this.activityService.getActivityById(activityId);
            request.setAttribute("activity", activity1);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpdate error " + errMsg);
        }
        return "toUpdate";
    }

    /**
     * 修改活动信息
     *
     * @return
     */
    public String update() {
        try {
            if (activity.getActivityId() == null || activity.getActivityId() <= 0)
                throw new RuntimeException("编号不合法");
            // 获取当前登录用户的编号
            Long uid = this.getLoginUser().getUid();
            // 编辑活动
            this.activityService.update(uid, titleImage, titleImageFileName, image, imageFileName, activity);
            this.dwz = new DwzUtil("200", "活动编辑成功", "activity", "closeCurrent");
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil("300", "活动编辑失败", "activity", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 查询活动详细
     *
     * @return
     */
    public String look() {
        try {
            Long activityId = activity.getActivityId();
            if (activityId == null || activityId <= 0)
                throw new RuntimeException("编号不合法");
            // 根据编号获取活动信息
            Activity activity1 = this.activityService.getActivityById(activityId);
            String attach = activity1.getAttachmentList();
            // 验证是否有活动图片
            List attachList = null;
            if (!StringUtil.isEmpty(attach)) {
                // 解析JSON数据
                attachList = ObjectUtil.getObjectListFromJson(activity1.getAttachmentList(), Map.class);
            }
            request.setAttribute("attachList", attachList);
            request.setAttribute("activity", activity1);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("look error " + errMsg);
        }
        return "toLook";
    }

    /**
     * 删除活动信息
     *
     * @return
     */
    public String delete() {
        try {
            Long activityId = activity.getActivityId();
            if (activityId == null || activityId <= 0)
                throw new RuntimeException("编号不合法");
            // 删除活动
            this.activityService.delete(activityId);
            this.dwz = new DwzUtil("200", "活动删除成功", "activity", "");
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("delete error " + errMsg);
            this.dwz = new DwzUtil("300", "活动删除失败", "activity", "");
        }
        return AppConst.DwzCode.SUCCESS;
    }


    public Object getModel() {
        if (activity == null)
            activity = new Activity();
        return activity;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public File[] getImage() {
        return image;
    }

    public void setImage(File[] image) {
        this.image = image;
    }

    public String[] getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String[] imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String[] getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String[] imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getTitleImageFileName() {
        return titleImageFileName;
    }

    public void setTitleImageFileName(String titleImageFileName) {
        this.titleImageFileName = titleImageFileName;
    }

    public String getTitleImageContentType() {
        return titleImageContentType;
    }

    public void setTitleImageContentType(String titleImageContentType) {
        this.titleImageContentType = titleImageContentType;
    }

    public File getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(File titleImage) {
        this.titleImage = titleImage;
    }
}
