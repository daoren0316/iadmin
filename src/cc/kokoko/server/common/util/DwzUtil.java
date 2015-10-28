package cc.kokoko.server.common.util;

/**
 * DWZ交互提示数据的工具类
 *
 * @author HHang
 * @version V1.0.1
 */
public class DwzUtil {
    /**
     * 提示框中图标的类型（200 ：成功　300 ：失败 301：超时）
     */
    private String statusCode;
    /**
     * 提示内容
     */
    private String message;
    /**
     * 提示后调用的左侧菜单栏的REL
     */
    private String navTabId;
    /**
     * 是否关闭当前页面
     */
    private String callbackType;
    /**
     * 重定向的路径
     */
    private String forwardUrl;

    public DwzUtil() {
    }

    public DwzUtil(String statusCode, String message, String callbackType) {
        this.statusCode = statusCode;
        this.message = message;
        this.callbackType = callbackType;
    }

    public DwzUtil(String statusCode, String message, boolean isCloseNav) {
        this.statusCode = statusCode;
        this.message = message;
        this.callbackType = isCloseNav ? "closeCurrent" : "";
    }

    /**
     *
     * @param statusCode
     * @param message
     * @param navTabId
     * @param isCloseNav true 关闭弹出框
     */
    public DwzUtil(String statusCode, String message, String navTabId, boolean isCloseNav) {
        this.statusCode = statusCode;
        this.message = message;
        this.navTabId = navTabId;
        this.callbackType = isCloseNav ? "closeCurrent" : "";
    }

    public DwzUtil(String statusCode, String message, String navTabId, String callbackType) {
        this.statusCode = statusCode;
        this.message = message;
        this.navTabId = navTabId;
        this.callbackType = callbackType;
    }

    /**
     * 功能：设置STRUT2返回给DWZ的提示信息
     *
     * @param statusCode   提示框中图标的类型（200 ：成功　300 ：失败 301：超时）
     * @param message      提示内容
     * @param navTabId     提示后调用的左侧菜单栏的REL
     * @param callbackType 是否关闭当前页面
     * @param forwardUrl   重定向的路径
     */
    public DwzUtil(String statusCode, String message, String navTabId, String callbackType, String forwardUrl) {
        this.statusCode = statusCode;
        this.message = message;
        this.navTabId = navTabId;
        this.callbackType = callbackType;
        this.forwardUrl = forwardUrl;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

}
