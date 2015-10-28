package cc.kokoko.server.ibutler.web.action.site;


import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Feedback;
import cc.kokoko.server.ibutler.service.FeedbackService;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 投诉保修
 */
public class FeedbackAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private FeedbackService feedbackService;

    private Feedback feedback;

    private String startTime;
    private String endTime;
    private Long houseId;
    private String publicAddress;

    /**
     * 到咨询数据显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        // 获取当前登录用户所在小区的编号
        Long communityId = this.getCommunityId();
        // 获取活动信息
        Map<String, Object> map = this.feedbackService.getFeedbackRecord(communityId, 2L, null, startTime, endTime, houseId,
                page.getLineSize(), page.getStartRecord());
        List<Feedback> list = (List<Feedback>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("feedbackList", list);
        request.setAttribute("ref", "LookupFeedBack");
        return SUCCESS;
    }

    /**
     * 到达投诉数据显示页面
     *
     * @return
     */
    public String toTSIndex() {
        if (page == null)
            page = new PageUtil();
        // 获取当前登录用户所在小区的编号
        Long communityId = this.getCommunityId();
        // 获取活动信息
        Map<String, Object> map = this.feedbackService.getFeedbackRecord(communityId, 3L, feedback.getType(), startTime, endTime, houseId,
                page.getLineSize(), page.getStartRecord());
        List<Feedback> list = (List<Feedback>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("feedbackList", list);
        request.setAttribute("ref", "LookupFeedBackTS");
        return "toTSIndex";
    }

    /**
     * 到达保修数据显示页面
     *
     * @return
     */
    public String toBxIndex() {
        if (page == null)
            page = new PageUtil();
        // 获取当前登录用户所在小区的编号
        Long communityId = this.getCommunityId();
        // 获取活动信息
        Map<String, Object> map = this.feedbackService.getFeedbackRecord(communityId, 1L, feedback.getType(), startTime, endTime, houseId,
                page.getLineSize(), page.getStartRecord());
        List<Feedback> list = (List<Feedback>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("feedbackList", list);
        request.setAttribute("ref", "LookupFeedBackBx");
        return "toBxIndex";
    }

    /**
     * 到达回访页面
     *
     * @return
     */
    public String toUpdate() {
        String navTabId = "";
        try {
            Long id = feedback.getFeedbackId();
            if (id == null || id <= 0)
                throw new RuntimeException("编号不合法");
            // 获取信息
            Feedback feedback1 = this.feedbackService.getFeedbackById(id);
            if (feedback1 == null)
                throw new RuntimeException("信息获取失败");
            // 判断信息的类型
            if (feedback1.getCategory().equals(new Byte("1"))) {
                navTabId = ActionResult.Dwz.FEEDBACK_BX;
            } else if (feedback1.getCategory().equals(new Byte("2"))) {
                navTabId = ActionResult.Dwz.FEEDBACK_ZX;
            } else if (feedback1.getCategory().equals(new Byte("3"))) {
                navTabId = ActionResult.Dwz.FEEDBACK_TS;
            } else
                throw new RuntimeException("数据类型错误");

            request.setAttribute("feedback", feedback1);
            return "toUpdate";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpdate error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, navTabId, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 修改数据
     *
     * @return
     */
    public String update() {
        String navTabId = "";
        try {
            Long id = feedback.getFeedbackId();
            if (id == null || id <= 0)
                throw new RuntimeException("编号不合法");
            Feedback feedback1 = this.feedbackService.getFeedbackById(id);
            if (feedback1 == null)
                throw new RuntimeException("信息获取失败");
            // 操作员编号
            Long operatorId = this.getLoginUser().getOperatorId();
            // 操作员名称
            String operatorName = this.getLoginUser().getNickname();
            // 判断信息的类型
            if (feedback1.getCategory().equals(new Byte("1"))) {
                navTabId = ActionResult.Dwz.FEEDBACK_BX;
            } else if (feedback1.getCategory().equals(new Byte("2"))) {
                navTabId = ActionResult.Dwz.FEEDBACK_ZX;
            } else if (feedback1.getCategory().equals(new Byte("3"))) {
                navTabId = ActionResult.Dwz.FEEDBACK_TS;
            } else
                throw new RuntimeException("数据类型错误");
            // 修改信息
            this.feedbackService.updateFeedback(id, operatorId, operatorName, feedback.getNote());
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "操作成功", navTabId, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "操作失败", navTabId, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 展示图片
     *
     * @return
     */
    public String showImg() {
        String navTabId = "";
        try {
            Long id = feedback.getFeedbackId();
            if (id == null || id <= 0)
                throw new RuntimeException("编号不合法");
            // 获取信息
            Feedback feedback1 = this.feedbackService.getFeedbackById(id);
            if (feedback1 == null)
                throw new RuntimeException("信息获取失败");
            // 判断信息的类型
            if (feedback1.getCategory().equals(new Byte("1"))) {
                navTabId = ActionResult.Dwz.FEEDBACK_BX;
            } else if (feedback1.getCategory().equals(new Byte("2"))) {
                navTabId = ActionResult.Dwz.FEEDBACK_ZX;
            } else if (feedback1.getCategory().equals(new Byte("3"))) {
                navTabId = ActionResult.Dwz.FEEDBACK_TS;
            } else
                throw new RuntimeException("数据类型错误");
            // 获取图片数据
            String attach = feedback1.getPicList();
            // 验证是否有活动图片
            List attachList = null;
            if (!StringUtil.isEmpty(attach)) {
                // 解析JSON数据
                attachList = ObjectUtil.getObjectListFromJson(attach, Map.class);
            }
            request.setAttribute("attachList", attachList);
            return "toShowImg";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("showImg error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, navTabId, true);
            return AppConst.DwzCode.SUCCESS;
        }
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

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public Object getModel() {
        if (feedback == null)
            feedback = new Feedback();
        return feedback;
    }
}
