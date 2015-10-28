package cc.kokoko.server.ibutler.web.action.site;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Commodity;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import cc.kokoko.server.ibutler.service.CommodityService;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 团购管理
 */
public class CommodityAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private CommodityService commodityService;

    private Commodity commodity;

    private String starttime;
    private String endtime;
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
        if (page == null) {
            page = new PageUtil();
        }
        // 获取当前登录用户编号
        Long uid = this.getLoginUser().getUid();
        // 获取所在小区编号
        Long communityId = this.getLoginUser().getCommunityId();
        // 获取团购信息
        Map<String, Object> map = this.commodityService.getCommodityRecord(uid, communityId, starttime, endtime,
                page.getLineSize(), page.getStartRecord());
        List<Commodity> list = (List<Commodity>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("commodityList", list);
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
     * 添加团购信息
     *
     * @return
     */
    public String insert() {
        try {
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            // 获取所在小区编号
            Long communityId = this.getLoginUser().getCommunityId();
            // 添加团购信息
            this.commodityService.insert(uid, communityId, titleImage, titleImageFileName, image, imageFileName, commodity);
            //设置页面提示信息
            this.dwz = new DwzUtil("200", "团购发布成功！", "commodity", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil("300", "团购发布失败！", "commodity", "closeCurrent");
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
            if (commodity.getCommodityId() <= 0)
                throw new RuntimeException("编号不合法");
            // 根据编号获取团购详细信息
            request.setAttribute("commodity", this.commodityService.getCommodityById(commodity.getCommodityId()));
            return "toUpdate";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpdate error " + errMsg);
            this.dwz = new DwzUtil("300", errMsg, "commodity", "closeCurrent");
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 编辑团购信息
     *
     * @return
     */
    public String update() {
        try {
            if (commodity.getCommodityId() <= 0)
                throw new RuntimeException("编号不合法");
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            // 编辑团购信息
            this.commodityService.update(uid, commodity.getCommodityId(), titleImage, titleImageFileName, image, imageFileName, commodity);
            //设置页面提示信息
            this.dwz = new DwzUtil("200", "团购编辑成功！", "commodity", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil("300", "团购编辑失败！", "commodity", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 删除团购信息
     *
     * @return
     */
    public String delete() {
        try {
            if (commodity.getCommodityId() <= 0)
                throw new RuntimeException("编号不合法");
            // 删除团购信息
            this.commodityService.delCommodityById(commodity.getCommodityId());
            this.dwz = new DwzUtil("200", "删除成功", "commodity", "");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("delete error " + errMsg);
            this.dwz = new DwzUtil("300", "删除失败 " + errMsg, "commodity", "");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    public Object getModel() {
        if (commodity == null)
            commodity = new Commodity();
        return commodity;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public File getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(File titleImage) {
        this.titleImage = titleImage;
    }

    public String getTitleImageContentType() {
        return titleImageContentType;
    }

    public void setTitleImageContentType(String titleImageContentType) {
        this.titleImageContentType = titleImageContentType;
    }

    public String getTitleImageFileName() {
        return titleImageFileName;
    }

    public void setTitleImageFileName(String titleImageFileName) {
        this.titleImageFileName = titleImageFileName;
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
}
