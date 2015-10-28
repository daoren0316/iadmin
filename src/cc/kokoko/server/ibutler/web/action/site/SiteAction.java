package cc.kokoko.server.ibutler.web.action.site;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Site;
import cc.kokoko.server.ibutler.service.SiteService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;

public class SiteAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private SiteService siteService;

    private Site site;
    // 封装上传文件域的属性
    private File image;
    // 封装上传文件类型的属性
    private String imageContentType;
    // 封装上传文件名的属性
    private String imageFileName;

    private String phoneNumber;

    /**
     * 到达信息修改页面
     *
     * @return
     */
    public String toUpdate() {
        return "toUpdate";
    }

    /**
     * 修改服务小站信息
     *
     * @return
     */
    public String update() {
        try {
            // 获取当前登录的用户编号
            Long uid = this.getLoginUser().getUid();
            if (site.getSiteId() <= 0)
                throw new RuntimeException("编号不合法");
            // 修改服务小站信息
            this.siteService.update(uid, image, imageFileName, site, phoneNumber);
            //设置页面提示信息
            this.dwz = new DwzUtil("200", "信息修改成功！", "closeCurrent");
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("update error " + errMsg);
            this.dwz = new DwzUtil("300", "信息修改失败！", "closeCurrent");
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 查找带回小站信息
     *
     * @return
     */
    public String lookupSite() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取所有商户信息
        Map<String, Object> map = this.siteService.getAllSiteRecord(site.getSiteName(), page.getLineSize(), page.getStartRecord());
        List<Site> list = (List<Site>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("siteList", list);
        return "lookupSite";
    }


    public Object getModel() {
        if (site == null)
            site = new Site();
        return site;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
