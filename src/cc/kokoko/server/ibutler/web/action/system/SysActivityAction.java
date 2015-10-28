package cc.kokoko.server.ibutler.web.action.system;

import cc.kokoko.server.common.model.PageUtil;
import cc.kokoko.server.common.util.ActionResult;
import cc.kokoko.server.common.util.DwzUtil;
import cc.kokoko.server.common.util.ImportExeclUtil;
import cc.kokoko.server.commons.util.StringUtil;
import cc.kokoko.server.ibutler.domain.AppConst;
import cc.kokoko.server.ibutler.domain.SysActivityConfig;
import cc.kokoko.server.ibutler.domain.SysActivityLog;
import cc.kokoko.server.ibutler.service.SysActivityService;
import cc.kokoko.server.ibutler.web.action.AdminBaseAction;
import com.opensymphony.xwork2.ModelDriven;
import jxl.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 系统活动管理
 */
public class SysActivityAction extends AdminBaseAction implements ModelDriven {
    @Autowired
    private SysActivityService sysActivityService;

    private SysActivityConfig sysActivityConfig;

    private String starttime;
    private String endtime;
    private Long houseId;
    private String publicAddress;

    // 封装上传文件域的属性
    private File file;
    // 封装上传文件类型的属性
    private String fileContentType;
    // 封装上传文件名的属性
    private String fileFileName;

    /**
     * 到达信息显示页面
     *
     * @return
     */
    public String toIndex() {
        if (page == null)
            page = new PageUtil();
        Map<String, Object> map = this.sysActivityService.getSysActivityRecord(starttime, endtime, page.getLineSize(), page.getStartRecord());
        List<SysActivityConfig> list = (List<SysActivityConfig>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("sysActivityList", list);
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
     * 发布活动
     *
     * @return
     */
    public String insert() {
        try {
            // 验证数据是否合法
            if (sysActivityConfig.getMoney() <= 0)
                throw new RuntimeException("金额不合法");

            // 添加数据
            this.sysActivityService.insert(sysActivityConfig);
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "发布成功", ActionResult.Dwz.SYS_ACTIVITY, true);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("insert error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.SYS_ACTIVITY, true);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 关闭活动
     *
     * @return
     */
    public String close() {
        try {
            // 验证数据是否合法
            if (sysActivityConfig.getId() == null || sysActivityConfig.getId() <= 0)
                throw new RuntimeException("参数不合法");
            // 根据编号获取活动配置详细
            SysActivityConfig sysActivityConfig1 = this.sysActivityService.getSysActivityConfigById(sysActivityConfig.getId());
            if (sysActivityConfig1.getFlag() == 2)
                throw new RuntimeException("已过期活动不能关闭");
            if (sysActivityConfig1.getFlag() == 3)
                throw new RuntimeException("该活动已处于关闭状态");

            // 将活动状态修改为关闭
            this.sysActivityService.updateSysActivityFlag(3l, sysActivityConfig.getId());
            // 设置页面提示信息
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "关闭成功", ActionResult.Dwz.SYS_ACTIVITY, false);
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.error("close error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, errMsg, ActionResult.Dwz.SYS_ACTIVITY, false);
        }
        return AppConst.DwzCode.SUCCESS;
    }

    /**
     * 到达获取活动返还记录显示页面
     *
     * @return
     */
    public String toActivityIndex() {
        if (page == null)
            page = new PageUtil();

        Map<String, Object> map = this.sysActivityService.getSysActivityLogRecord(starttime, endtime, houseId, page.getLineSize(), page.getStartRecord());
        List<SysActivityLog> list = (List<SysActivityLog>) map.get("list");
        int count = Integer.valueOf(map.get("count").toString());
        page.setAllRecorders(count);
        request.setAttribute("sysActivityLogList", list);
        request.setAttribute("ref", "LookupHouseActivity");
        return "toActivityIndex";
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
            this.sysActivityService.export(sheet);
            this.dwz = new DwzUtil(ActionResult.Dwz.SUCCESS, "导入成功", ActionResult.Dwz.SYS_ACTIVITY, true);
        } catch (RuntimeException e) {
            String errMsg = e.getMessage();
            log.error("export error " + errMsg);
            this.dwz = new DwzUtil(ActionResult.Dwz.FAILURE, "导入失败：" + errMsg, ActionResult.Dwz.SYS_ACTIVITY, true);
        }
        return AppConst.DwzCode.SUCCESS;
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

    public Object getModel() {
        if (sysActivityConfig == null)
            sysActivityConfig = new SysActivityConfig();
        return sysActivityConfig;
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
}
