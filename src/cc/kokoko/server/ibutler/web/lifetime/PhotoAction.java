package cc.kokoko.server.ibutler.web.lifetime;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.kdt.DatLifeTime;
import cc.kokoko.server.ibutler.domain.kdt.LifeComment;
import cc.kokoko.server.ibutler.domain.weixin.resp.TextMessage;
import cc.kokoko.server.ibutler.service.kdt.CommentService;
import cc.kokoko.server.ibutler.service.kdt.KdtApiService;
import cc.kokoko.server.ibutler.service.kdt.PraiseService;
import cc.kokoko.server.ibutler.service.util.WeixinMessageUtil;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class PhotoAction extends AdminBaseAction implements ModelDriven {

	@Autowired
	private KdtApiService kdtApiService;
	@Autowired
	private PraiseService praiseService;
	@Autowired
	private CommentService commentService;
//	@Autowired
//	private FromUserService fromUserService;
	private DatLifeTime datLifeTime;
	private String k;
	private final String keyword = "f56d2ea3d85aa26fdce5f0b33348d2bb";
	private String card;
	private File image;
	private String imageContentType;
	private String imageFileName;
	private Long lid;
	private String content;
	

	public String toMain() {
		return "success";
	}

	public String toLoad() {
		if (this.page == null) {
			this.page = new PageUtil();
		}

		Map map = this.kdtApiService.getKdtShowRecord(this.card, Integer
				.valueOf(this.page.getLineSize()), Integer.valueOf(this.page
				.getStartRecord()));
		int count = Integer.valueOf(map.get("count").toString()).intValue();
		this.page.setAllRecorders(count);
		convertMessage(map);
		return null;
	}

	public String toPraise() {
		praiseService.praise(this.datLifeTime.getId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("praise", "success");
		convertMessage(map);
		return null;
	}

	public String toAddComment() {
		LifeComment lifeComment = new LifeComment();
		lifeComment.setLid(lid);
		lifeComment.setContent(content);
		commentService.addComment(lifeComment);
		return null;
	}

	public String toDetail() {

		try {
			if (this.datLifeTime.getId() == null)
				throw new Exception("参数错误");
			if (!this.k.equals("f56d2ea3d85aa26fdce5f0b33348d2bb")) {
				throw new Exception("参数错误");
			}
			DatLifeTime dataLifeTime = this.kdtApiService.getDatLifeTimeById(this.datLifeTime.getId());
			this.request.setAttribute("kdtShowDTO", dataLifeTime);
			Long count = praiseService.getPraiseCount(this.datLifeTime.getId());
			List<LifeComment> commentList = commentService
					.findAllCommentsByLid(this.datLifeTime.getId());
			this.request.setAttribute("count", count);
			this.request.setAttribute("commentList", commentList);
			this.request.setAttribute("id", this.datLifeTime.getId());
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("toMain err : " + e.getMessage());
		}
		return "error";
	}

	public String newDetail() {
		
		/*
		 * 
		 * String url = request.getRequestURL() + "?" + request.getQueryString();
		url = url.replace("detail.do", "newdetail.do");
		try {
			url = URLEncoder.encode(url, "utf-8");
			StringBuffer sb = new StringBuffer(
			"https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
			sb.append(APP_ID);
			sb.append("&redirect_uri=");
			sb.append(url);
			sb.append("&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
			this.response.sendRedirect(sb.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 * 
		 */
		try {
			if (this.datLifeTime.getId() == null)
				throw new Exception("参数错误");
			if (!this.k.equals("f56d2ea3d85aa26fdce5f0b33348d2bb")) {
				throw new Exception("参数错误");
			}

			/*
			 * 若恒
			 */
			
			// 获取code
			String code = request.getParameter("code");
			this.request.setAttribute("code", code);
			// 用户同意授权
			if(null != code) {
				// 用code换取access_token（同时会得到OpenID）
				
//				WeixinOauth2Token wot = AdvancedUtil.getOAuth2AceessToken(APP_ID, APP_SECRET, code);
//				this.request.setAttribute("code", code);
//				openid = wot.getOpenId();
//				this.request.setAttribute("openid", openid);
				// 获取用户基本信息
//				FromUser fromUser = AdvancedUtil.getSNSUserInfo(wot.getAccessToken(), openid);
//				//将用户信息存入本地数据库
//				fromUserService.validateUser(fromUser);
//				this.request.setAttribute("fromuser", fromUser);
			}
//			DatLifeTime dataLifeTime = this.kdtApiService.getDatLifeTimeById(this.datLifeTime.getId());
//			this.request.setAttribute("kdtShowDTO", dataLifeTime);
//			Long count = praiseService.getPraiseCount(this.datLifeTime.getId());
//			List<LifeComment> commentList = commentService
//					.findAllCommentsByLid(this.datLifeTime.getId());
//			this.request.setAttribute("count", count);
//			this.request.setAttribute("commentList", commentList);
//			this.request.setAttribute("id", this.datLifeTime.getId());
//			this.request.setAttribute("canPraise", praiseService.canPraise(
//					this.datLifeTime.getId(), openid));
			return "info";
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("toMain err : " + e.getMessage());
		}
		return "error";
	}

	public String toIndex() {
		if (this.page == null) {
			this.page = new PageUtil();
		}

		Map map = this.kdtApiService.getKdtShowRecord(this.card, Integer
				.valueOf(this.page.getLineSize()), Integer.valueOf(this.page
				.getStartRecord()));
		List list = (List) map.get("list");
		int count = Integer.valueOf(map.get("count").toString()).intValue();
		this.page.setAllRecorders(count);
		this.request.setAttribute("DatLifeTimeList", list);
		return "success";
	}

	public String toInsert() {
		return "toInsert";
	}

	public String insert() {
		try {
			Long uid = getLoginUser().getUid();
			this.log.debug(this.imageFileName);

			this.kdtApiService.insertDatLifeTime(uid, this.image,
					this.imageFileName, this.datLifeTime);

			this.dwz = new DwzUtil("200", "信息添加成功", "lifetime", true);
		} catch (Exception e) {
			String errMsg = e.getMessage();
			this.log.error("insert error " + errMsg);
			this.dwz = new DwzUtil("300", "信息添加失败", "lifetime", true);
		}
		return "outer";
	}

	public String toUpdate() {
		try {
			Long id = this.datLifeTime.getId();
			if ((id == null) || (id.longValue() <= 0L)) {
				throw new RuntimeException("编号不合法");
			}
			this.request.setAttribute("datLifeTime", this.kdtApiService
					.getDatLifeTimeById(id));
			return "toUpdate";
		} catch (RuntimeException e) {
			String errMsg = e.getMessage();
			this.log.error("toUpdate error " + errMsg);
			this.dwz = new DwzUtil("300", errMsg, "lifetime", true);
		}
		return "outer";
	}

	public String update() {
		try {
			Long id = this.datLifeTime.getId();
			if ((id == null) || (id.longValue() <= 0L)) {
				throw new RuntimeException("编号不合法");
			}
			Long uid = getLoginUser().getUid();

			this.kdtApiService.updateDatLifeTime(uid, this.image,
					this.imageFileName, this.datLifeTime);

			this.dwz = new DwzUtil("200", "信息修改成功", "lifetime", true);
		} catch (Exception e) {
			String errMsg = e.getMessage();
			this.log.error("update error " + errMsg);
			this.dwz = new DwzUtil("300", "信息修改失败", "lifetime", true);
		}
		return "outer";
	}

	public String delete() {
		try {
			Long id = this.datLifeTime.getId();
			if ((id == null) || (id.longValue() <= 0L))
				throw new RuntimeException("编号不合法");
			this.kdtApiService.delete(id);

			this.dwz = new DwzUtil("200", "信息删除成功", "lifetime", false);
		} catch (Exception e) {
			String errMsg = e.getMessage();
			this.log.error("delete error " + errMsg);
			this.dwz = new DwzUtil("300", errMsg, "lifetime", false);
		}
		return "outer";
	}

	public String toExport() {
		return "toExport";
	}

	public String export() {
		try {
			if (StringUtil.isEmpty(this.imageFileName)) {
				throw new RuntimeException("File 为空");
			}

			this.dwz = new DwzUtil("200", "导入成功", "lifetime", true);
		} catch (RuntimeException e) {
			String errMsg = e.getMessage();
			this.log.error("export error " + errMsg);
			this.dwz = new DwzUtil("300", "导入失败：" + errMsg, "lifetime", true);
		}
		return "outer";
	}

	public String toTest() {
		try {
			testKdt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public String testKdt() throws IOException {
		this.log.debug("IN METHOD");

		Map requestMap = WeixinMessageUtil.parseXml(this.request);

		String fromUserName = (String) requestMap.get("FromUserName");

		String toUserName = (String) requestMap.get("ToUserName");

		String msgType = (String) requestMap.get("MsgType");

		String content = (String) requestMap.get("Content");

		String event = (String) requestMap.get("Event");

		this.log.debug("fromUserName :" + fromUserName + " toUserName :"
				+ toUserName + " msgType :" + msgType + " event :" + event);
		System.out.println("fromUserName" + fromUserName);
		if (msgType.equals("event")) {
			String eventKey = (String) requestMap.get("EventKey");
			String kdtKey = "http://koudaitong.com/v1/x/15lbcabnn";
			if (eventKey.equals(kdtKey)) {
				TextMessage textMessage = new TextMessage();
				textMessage.setToUserName(fromUserName);
				textMessage.setFromUserName(toUserName);
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType("text");
				textMessage.setContent("ewr");

				String respMessage = WeixinMessageUtil
						.textMessageToXml(textMessage);

				this.response.setContentType("text/xml");
				PrintWriter out = this.response.getWriter();
				out.print(respMessage);
				out.close();
			}
		}
		return null;
	}

	public String getK() {
		return this.k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getCard() {
		return this.card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public File getImage() {
		return this.image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return this.imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return this.imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Object getModel() {
		if (this.datLifeTime == null)
			this.datLifeTime = new DatLifeTime();
		return this.datLifeTime;
	}

	public Long getLid() {
		return lid;
	}

	public void setLid(Long lid) {
		this.lid = lid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}