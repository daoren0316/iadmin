<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">添加权限信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/power_insert.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>名称：</dt>
                <dd>
                    <input type="text" name="powerName" class="required" size="25" maxlength="20"/>
                </dd>
            </dl>

            <dl>
                <dt>级别：</dt>
                <dd>
                    <input type="radio" name="rank" value="0" checked="checked" onclick="showDL(this.value)"/>顶级
                    <input type="radio" name="rank" value="5" onclick="showDL(this.value)"/>子级
                </dd>
            </dl>

            <dl style="display:none!important;" id="selectedTop">
                <dt>上级名称：</dt>
                <dd>
                    <p style="width: 500px;padding: 0;">
                        <input type="text" name="parentName" id="parent" size="25" readonly="readonly"/>
                        <input type="hidden" name="parent" id="parentId" value="0"/>
                        <input type="hidden" name="powerLevel" id="parentRank" value="0"/>
                    </p>

                    <p style="width: 500px;padding: 0;">
                        <a class="btnLook" href="<%=basePath %>admin/power_load.do"
                           target="dialog" rel="power" width="260" height="500"
                           maxable="false" minable="false" title="选择上级名称">
                            <span>选择</span></a>
                        <span class="info"> 查找上级权限信息</span>
                    </p>
                </dd>
            </dl>

            <dl>
                <dt>TARGET：</dt>
                <dd>
                    <select name="target" class="combox" onchange="selected(this.value)">
                        <option value="navTab">navTab</option>
                        <option value="dialog">dialog</option>
                        <option value="ajaxTodo">ajaxTodo</option>
                        <option value="selectedTodo">selectedTodo</option>
                        <option value="dwzExport">dwzExport</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>访问URL：</dt>
                <dd>
                    <input type="text" name="powerUrl" id="powerUrl" size="50"/>
                </dd>
            </dl>

            <dl>
                <dt>REL：</dt>
                <dd>
                    <input type="text" name="rel" id="rel" size="25" maxlength="20"/>
                </dd>
            </dl>

            <dl>
                <dt>排序：</dt>
                <dd>
                    <input type="text" name="sort" class="required digits"  size="25" maxlength="10"/>
                </dd>
            </dl>

            <div id="ajaxTodoTip" style="display:none!important;">
                <dl>
                    <dt>提示：</dt>
                    <dd>
                        <input type="text" name="title" class="required" size="25" maxlength="20" disabled="disabled"/>
                    </dd>
                </dl>
            </div>

            <div id="dialogTip" style="display:none!important;">
                <dl>
                    <dt>宽度：</dt>
                    <dd>
                        <input type="text" name="width" class="required digits" size="25" value="750"/>
                    </dd>
                </dl>

                <dl>
                    <dt>高度：</dt>
                    <dd>
                        <input type="text" name="height" class="required digits" size="25" value="500"/>
                    </dd>
                </dl>

                <dl>
                    <dt>属性：</dt>
                    <dd>
                        <input type="checkbox" name="maxable" value="1" checked="checked" disabled="disabled"/>最大化
                        <input type="checkbox" name="minable" value="1" checked="checked" disabled="disabled"/>最小化
                        <input type="checkbox" name="mask" value="1" checked="checked"/>遮盖
                        <input type="checkbox" name="drawable" value="1" checked="checked"/>拖动
                        <input type="checkbox" name="resizable" value="1" checked="checked"/>可变大小
                    </dd>
                </dl>
            </div>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">提交</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>

<script type="text/javascript">
    function showDL(obj) {
        if (obj == 5) {
            $("#selectedTop").css("display", "");
            $("#parent").addClass("required");
            $("#rel").addClass("required");
        } else {
            $("#selectedTop").css("display", "none");
            $("#parent").removeClass("required");
            $("#rel").removeClass("required");
        }
    }

    function selected(val) {
        var $ajtd = $("#ajaxTodoTip");
        var $txttd = $(":text", $ajtd);
        var $diag = $("#dialogTip");
        if (val == "ajaxTodo" || val == "selectedTodo" || val == "dwzExport") {
            $ajtd.show();
            $diag.hide();
            $txttd.removeAttr("disabled");
        } else if (val == "dialog") {
            $ajtd.hide();
            $diag.show();
            $txttd.attr("disabled", "disabled");
        } else {
            $ajtd.hide();
            $diag.hide();
            $txttd.attr("disabled", "disabled");
        }
    }
</script>
