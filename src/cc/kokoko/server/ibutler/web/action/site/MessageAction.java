package cc.kokoko.server.ibutler.web.action.site;


import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Message;
import cc.kokoko.server.ibutler.domain.User;
import cc.kokoko.server.ibutler.service.UserService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import cc.kokoko.server.ibutler.service.MessageService;

import java.util.List;
import java.util.Map;

/**
 * 留言信息管理
 *
 * @author HHang
 * @version V1.0.1
 */
public class MessageAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    private Message message;
    public String startTime;
    public String endTime;

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
        //获取当前登录的用户类型
        //Byte userType = this.getLoginUser().getUserType();
        // 如果用户类型为99则不根据用户编号查询
        //uid = ("99").equals(userType.toString()) ? null : uid;
        // 获取留言信息
        Map<String, Object> map = this.messageService.getMessageRecord(uid, startTime, endTime, page.getLineSize(), page.getStartRecord());
        List<Message> list = (List<Message>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("messageList", list);
        return SUCCESS;
    }

    /**
     * 到达信息回复页面
     *
     * @return
     */
    public String toReply() {
        try {
            // 获取留言接收人编号
            Long messageId = message.getMessageId();
            if (messageId == null || messageId <= 0)
                throw new RuntimeException("信息编号不合法");
            // 根据编号获取留言信息
            Message message1 = this.messageService.getMessageById(messageId);
            // 验证消息的接收人是否为自己
            if (message1.getFromUid().equals(this.getLoginUser().getUid()))
                throw new RuntimeException("不能自己回复自己");
            request.setAttribute("message", message1);
            return "toReply";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toReply error " + errMsg);
            this.dwz = new DwzUtil("300", errMsg, "message", "");
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 回复留言
     *
     * @return
     */
    public String reply() {
        try {
            // 获取当前登录用户的编号
            Long uid = this.getLoginUser().getUid();
            // 获取留言接收人编号
            Long fromUid = message.getFromUid();
            if (fromUid == null || fromUid <= 0)
                throw new RuntimeException("留言接收人编号不合法");
            message.setToUid(fromUid);
            // 根据当前登录用户编号获取该用户详细信息
            User user = this.userService.getUserByUid(uid);
            message.setFromUid(user.getUid());
            // 如果昵称为为空，则使用登录名
            String fromName = StringUtil.isEmpty(user.getNickname()) ? user.getUsername() : user.getNickname();
            message.setFromUsername(fromName);
            message.setFromUserAvatar(user.getProfileImage());
            message.setMessageType(AppConst.MessageType.P2P_MESSAGE);
            message.setCommunityId(user.getCommunityId());
            // 添加回复信息
            this.messageService.insert(message);
            if (fromUid == null || fromUid <= 0)
                throw new RuntimeException("信息编号不合法");
            this.dwz = new DwzUtil("200", "留言回复成功", "message", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toReply error " + errMsg);
            this.dwz = new DwzUtil("300", "留言回复失败", "message", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    public Object getModel() {
        if (message == null)
            message = new Message();
        return message;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
