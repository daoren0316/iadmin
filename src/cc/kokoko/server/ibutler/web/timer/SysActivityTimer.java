package cc.kokoko.server.ibutler.web.timer;

import cc.kokoko.server.ibutler.service.SysActivityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 系统活动定时器
 */
public class SysActivityTimer {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private SysActivityService sysActivityService;

    /**
     * 开始活动
     */
    public void startActivity() {
        // 定时扫描活动是否可以开始
        this.sysActivityService.scanActivityIStart();
    }

    /**
     * 结束活动
     */
    public void endActivity() {
        // 定时扫描活动是否可以结束
        this.sysActivityService.scanActivityIEnd();
    }

    /**
     * 每天向服务小站划款
     */
    public void allotmentToSite(){
        this.sysActivityService.allotmentToSite();
    }

}
