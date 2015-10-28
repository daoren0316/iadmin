package cc.kokoko.server.ibutler.web.action.site;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.ibutler.domain.Advertise;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.service.AdvertisementService;
import cc.kokoko.server.ibutler.service.CommunityService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 广告管理
 */
public class AdvertisementAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private CommunityService communityService;

    private Advertise advertise;
    // 封装上传文件域的属性
    private File image;
    // 封装上传文件类型的属性
    private String imageContentType;
    // 封装上传文件名的属性
    private String imageFileName;

    /**
     * 到达信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null) {
            page = new PageUtil();
        }
        // 如果为管理员，则获取小区信息用于查询
        if (this.getLoginUser().isSysFlag()) {
            request.setAttribute("communityList", this.communityService.getAllCommunity());
        } else
            advertise.setCommunityId(this.getLoginUser().getCommunityId());
        // 获取团购信息
        Map<String, Object> map = this.advertisementService.getAdvertiseRecord(advertise.getCommunityId(), advertise.getPicUrl(),
                page.getLineSize(), page.getStartRecord());
        List<Advertise> list = (List<Advertise>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("advertiseList", list);
        return SUCCESS;
    }

    /**
     * 到达添加页面
     *
     * @return
     */
    public String toInsert() {
        if (this.getLoginUser().isSysFlag())
            request.setAttribute("communityList", this.communityService.getAllCommunity());
        return "toInsert";
    }

    /**
     * 添加广告
     *
     * @return
     */
    public String insert() {
        try {
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            // 获取所在小区编号
            Long communityId = this.getLoginUser().getCommunityId();
            if (this.getLoginUser().isSysFlag())
                communityId = advertise.getCommunityId();

            // 添加团购信息
            this.advertisementService.insert(uid, communityId, image, imageFileName, advertise);
            //设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "广告添加成功", ActionResult.Dwz.ADVERTISEMENT, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "广告添加失败：" + errMsg, ActionResult.Dwz.ADVERTISEMENT, true);
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
            if (advertise.getAdvId() <= 0)
                throw new RuntimeException("编号不合法");
            if (this.getLoginUser().isSysFlag())
                request.setAttribute("communityList", this.communityService.getAllCommunity());

            // 根据编号获取团购详细信息
            request.setAttribute("advertise", this.advertisementService.getAdvertiseById(advertise.getAdvId()));
            return "toUpdate";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toUpdate error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.ADVERTISEMENT, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 修改数据
     *
     * @return
     */
    public String update() {
        try {
            if (advertise.getAdvId() <= 0)
                throw new RuntimeException("编号不合法");
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            // 获取所在小区编号
            Long communityId = this.getLoginUser().getCommunityId();
            if (this.getLoginUser().isSysFlag())
                communityId = advertise.getCommunityId();
            // 编辑团购信息
            this.advertisementService.update(uid, communityId, advertise.getAdvId(), image, imageFileName, advertise);
            //设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "广告编辑成功", ActionResult.Dwz.ADVERTISEMENT, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "广告编辑失败：" + errMsg, ActionResult.Dwz.ADVERTISEMENT, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 删除广告
     *
     * @return
     */
    public String delete() {
        try {
            if (advertise.getAdvId() <= 0)
                throw new RuntimeException("编号不合法");
            // 编辑团购信息
            this.advertisementService.delete(advertise.getAdvId());
            //设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "删除成功", ActionResult.Dwz.ADVERTISEMENT);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("delete error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "删除失败：" + errMsg, ActionResult.Dwz.ADVERTISEMENT);
        }
        return AppConst.DwzCode.SUCCESS;
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

    public Object getModel() {
        if (advertise == null)
            advertise = new Advertise();
        return advertise;
    }
}
