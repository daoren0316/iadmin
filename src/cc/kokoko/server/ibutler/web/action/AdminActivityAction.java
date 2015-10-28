package cc.kokoko.server.ibutler.web.action;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cc.kokoko.server.common.model.PageInfo;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.ibutler.domain.Activity;
import cc.kokoko.server.ibutler.domain.Attachment;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.service.ActivityService;
import cc.kokoko.server.ibutler.service.AttachmentService;
import cc.kokoko.server.ibutler.service.UserService;
import cc.kokoko.server.ibutler.service.util.AttachmentUtil;
import cc.kokoko.server.ibutler.service.util.ObjectUtil;
import cc.kokoko.server.ibutler.web.action.site.SiteBaseAction;


public class AdminActivityAction extends SiteBaseAction {
	
	private static final long serialVersionUID = -9200546650186490062L;
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private UserService userService;
	
	private List<Activity> activityList;
	private Activity activity;
	private Long activityId;
	
	private File titlePic;
	private File pic;
	private String picFileName;
	private String titlePicFileName;
	private String newFileContentType;
	
	
	public String addNew(){
		return INPUT;
	}
	
	public String doAddNew(){
		if(pic != null){
			Attachment att = attachmentService.createAttachment(this.getLoginUser().getUid(), pic, picFileName);
			activity.setAttachmentList(ObjectUtil.getJsonStr(att));
		}
		if(titlePic != null){
			Attachment att2 = attachmentService.createAttachment(this.getLoginUser().getUid(), titlePic, titlePicFileName);
			activity.setTitlePicUrl(AttachmentUtil.getArticlePicUrlPrefix() + att2.getFilePath());
		}
		User user = userService.getUserByUid(this.getLoginUser().getUid());
		activityService.createActivity(user , activity.getActivityTitle(), activity.getActivityDesc(), activity.getTitlePicUrl(), activity.getAttachmentList());
		msg = "发布成功";
		result = 0;
		toUrl = "/admin/activity_doActivityList.do";
		return ActionResult.COMMON_MSG;
	}
	
	public String doDel(){
		activity = activityService.getActivityById(activityId);
		if(activity == null){
			msg = "删除失败：该活动已经不存在";
			result = -1;
			toUrl = "javascript:history.back();";
		}
		if(!activity.getUid().equals(this.getLoginUser().getUid())){
			msg = "删除失败：没有权限删除该活动";
			result = -1;
			toUrl = "javascript:history.back();";
		}
		activityService.delActivityById(activityId);
		msg = "删除成功";
		result = 0;
		toUrl = "/admin/activity_doActivityList.do";
		return ActionResult.COMMON_MSG;
	}

	public String doActivityList() {
		boolean isFirst = false;
		if (pageInfo == null) {
			pageInfo = new PageInfo();
			pageInfo.setComputeTotal(true);
			isFirst = true;
		}
		Long shopId = this.getLoginUser().getUid();
		activityList=activityService.getActivityList(shopId);
		return ActionResult.LIST;
	}


	public String getNewFileContentType() {
		return newFileContentType;
	}

	public void setNewFileContentType(String newFileContentType) {
		this.newFileContentType = newFileContentType;
	}

	

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public File getTitlePic() {
		return titlePic;
	}

	public void setTitlePic(File titlePic) {
		this.titlePic = titlePic;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getTitlePicFileName() {
		return titlePicFileName;
	}

	public void setTitlePicFileName(String titlePicFileName) {
		this.titlePicFileName = titlePicFileName;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	

	
}
