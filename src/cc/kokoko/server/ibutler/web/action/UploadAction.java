package cc.kokoko.server.ibutler.web.action;

import cc.kokoko.server.ibutler.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class UploadAction extends AdminBaseAction {

    @Autowired
    private UploadService uploadService;

    // 封装上传文件域的属性
    private File filedata;
    // 封装上传文件类型的属性
    private String filedataContentType;
    // 封装上传文件名的属性
    private String filedataFileName;

    /**
     * 上传附件(适用于富文本编辑器的图片上传)
     */
    public void file() {
        // 获取当前登录用户的编号
        Long uid = this.getLoginUser().getUid();
        // 获取图片上传成功后返回的URL
        String url = this.uploadService.upload(uid, filedata, filedataFileName);
        // 封装返回结果
        Map<String, Object> msgMap = new HashMap<String, Object>();
        msgMap.put("url", url);
        msgMap.put("localname", filedataFileName);
        msgMap.put("id", "1");
        // 封装JSON返回数据
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("err", "");
        paraMap.put("msg", msgMap);
        this.convertMessage(paraMap);
    }

    public File getFiledata() {
        return filedata;
    }

    public void setFiledata(File filedata) {
        this.filedata = filedata;
    }

    public String getFiledataContentType() {
        return filedataContentType;
    }

    public void setFiledataContentType(String filedataContentType) {
        this.filedataContentType = filedataContentType;
    }

    public String getFiledataFileName() {
        return filedataFileName;
    }

    public void setFiledataFileName(String filedataFileName) {
        this.filedataFileName = filedataFileName;
    }
}