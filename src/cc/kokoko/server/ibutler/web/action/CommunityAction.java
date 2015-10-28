package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.common.util.ImportExeclUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.Community;
import cc.kokoko.server.ibutler.service.CommunityService;
import com.opensymphony.xwork2.ModelDriven;
import jxl.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 小区管理
 *
 * @author HHang
 * @version V1.0.1
 */
public class CommunityAction extends AdminBaseAction implements ModelDriven {

    @Autowired
    private CommunityService communityService;

    private Community community;

    // 封装上传文件域的属性
    private File file;
    // 封装上传文件类型的属性
    private String fileContentType;
    // 封装上传文件名的属性
    private String fileFileName;

    /**
     * 到达数据显示页面
     *
     * @return
     */
    public String toIndex() {
        // 获取项目路径
        String path = request.getContextPath();
        String tree = this.communityService.buildTree(path);
        request.setAttribute("tree", tree);
        return SUCCESS;
    }

    /**
     * 根据城市编号获取小区信息
     *
     * @return
     */
    public String community() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取小区信息
        Map<String, Object> map = this.communityService.getCommunityRecord(0, community.getCommunityName(),
                page.getLineSize(), page.getStartRecord());
        List<Community> list = (List<Community>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("communityList", list);
        // 将选中的城市编号放入request中
        request.setAttribute("cityId", community.getCityId());
        return "toCommunity";
    }

    /**
     * 根据城市编号获取小区信息
     *
     * @return
     */
    public String ctyTree() {
        if (page == null) {
            page = new PageUtil();
        }
        // 获取小区信息
        Map<String, Object> map = this.communityService.getCommunityRecord(community.getCityId(), community.getCommunityName(),
                page.getLineSize(), page.getStartRecord());
        List<Community> list = (List<Community>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("communityList", list);
        // 将选中的城市编号放入request中
        request.setAttribute("cityId", community.getCityId());
        return "toCtyTree";
    }

    /**
     * 到达添加城市信息页面
     *
     * @return
     */
    public String toInsert() {
        try {
            // 将城市数据放入request中
            request.setAttribute("cityList", this.communityService.getAllCity());
            return "toInsert";
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toInsert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.COMMUNITY, true);
            return AppConst.DwzCode.SUCCESS;
        }
    }

    /**
     * 添加小区数据
     *
     * @return
     */
    public String insert() {
        try {
            if (community == null)
                throw new RuntimeException("community 为空");
            if (community.getCityId() <= 0)
                throw new RuntimeException("cityId 参数错误");

            // 添加小区信息
            this.communityService.insert(community);
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "信息添加成功", ActionResult.Dwz.COMMUNITY, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("toInsert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "信息添加失败：" + errMsg, ActionResult.Dwz.COMMUNITY, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 检测小区名是否重复
     */
    public void check() throws UnsupportedEncodingException {
        Boolean bool = false;
        try {
            if (community.getCityId() < 0)
                throw new RuntimeException("cityId 参数错误");
            if (StringUtil.isEmpty(community.getCommunityName()))
                throw new RuntimeException("小区名为空");

            String name = community.getCommunityName();
            // 转换字符类型
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            bool = this.communityService.checkCommunity(community.getCityId(), name);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("check error " + errMsg);
        }
        this.convertMessage(bool);
    }

    /**
     * 到达导入Execl页面
     *
     * @return
     */
    public String toExport() {
        return "toExport";
    }

    /**
     * 导入Execl数据
     *
     * @return
     */
    public String export() {
        try {
            if (StringUtil.isEmpty(fileFileName))
                throw new RuntimeException("File 为空");
            // 获取第一个工作区
            Sheet sheet = ImportExeclUtil.readWorkbookFile(file);
            // 导入数据
            this.communityService.export(sheet);
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "导入成功", ActionResult.Dwz.COMMUNITY, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("export error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "导入失败：" + errMsg, ActionResult.Dwz.COMMUNITY, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public Object getModel() {
        if (community == null)
            community = new Community();
        return community;
    }

}
