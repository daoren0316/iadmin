package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.ibutler.domain.GuestBook;
import cc.kokoko.server.ibutler.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 用户留言数据统计
 *
 * @author HHang
 * @version V1.0.1
 */
public class GuestBookAction extends AdminBaseAction {

    @Autowired
    private GuestBookService guestBookService;

    public String startTime;
    public String endTime;

    /**
     * 查询留言记录
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取留言记录
        Map<String, Object> map = this.guestBookService.getGuestBookRecord(startTime, endTime, page.getLineSize(), page.getStartRecord());
        List<GuestBook> list = (List<GuestBook>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("guestbookList", list);
        return SUCCESS;
    }

}
