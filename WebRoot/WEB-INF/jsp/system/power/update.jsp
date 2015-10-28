<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">修改权限信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/power_update.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>名称：</dt>
                <dd>
                    <input type="text" name="powerName" class="required" size="25" maxlength="20"
                           value="${power.powerName}"/>
                </dd>
            </dl>

            <dl>
                <dt>级别：</dt>
                <dd>
                    <input type="radio" name="rank" value="0"
                           <c:if test="${power.parent eq 0 }">checked="checked"</c:if> onclick="showDL(this.value)"/>顶级
                    <input type="radio" name="rank" value="5"
                           <c:if test="${power.parent ne 0 }">checked="checked"</c:if> onclick="showDL(this.value)"/>子级
                </dd>
            </dl>

            <dl
                    <c:if test="${power.parent eq 0 }">style="display:none!important;"</c:if> id="selectedTop">
                <dt>上级名称：</dt>
                <dd>
                    <p style="width: 500px;padding: 0;">
                        <input type="text" name="parentName" id="parent" size="25" readonly="readonly"
                               value="${power.parentName}"/>
                        <input type="hidden" name="parent" id="parentId" value="${power.parent}"/>
                        <input type="hidden" name="powerLevel" id="parentRank" value="${power.powerLevel}"/>
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
                        <option value="navTab"
                                <c:if test="${power.target eq 'navTab'}">selected="selected"</c:if> >navTab
                        </option>
                        <option value="dialog"
                                <c:if test="${power.target eq 'dialog'}">selected="selected"</c:if> >dialog
                        </option>
                        <option value="ajaxTodo"
                                <c:if test="${power.target eq 'ajaxTodo'}">selected="selected"</c:if> >ajaxTodo
                        </option>
                        <option value="selectedTodo"
                                <c:if test="${power.target eq 'selectedTodo'}">selected="selected"</c:if> >selectedTodo
                        </option>
                        <option value="dwzExport"
                                <c:if test="${power.target eq 'dwzExport'}">selected="selected"</c:if> >dwzExport
                        </option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>访问URL：</dt>
                <dd>
                    <input type="text" name="powerUrl" id="powerUrl" size="50" value="${power.powerUrl}"/>
                </dd>
            </dl>

            <dl>
                <dt>REL：</dt>
                <dd>
                    <input type="text" name="rel" id="rel" size="25" maxlength="20" value="${power.rel}"/>
                </dd>
            </dl>

            <dl>
                <dt>排序：</dt>
                <dd>
                    <input type="text" name="sort" class="required digits" size="25" maxlength="10"
                           value="${power.sort}"/>
                </dd>
            </dl>

            <div id="ajaxTodoTip"
                 <c:if test="${power.target ne 'ajaxTodo'&& power.target ne 'selectedTodo' && power.target ne 'dwzExport'}">style="display:none!important;"</c:if> >
                <dl>
                    <dt>提示：</dt>
                    <dd>
                        <input type="text" name="title" class="required" size="25" value="${power.title}"
                               maxlength="20"/>
                    </dd>
                </dl>
            </div>

            <div id="dialogTip"
                 <c:if test="${power.target ne 'dialog'}">style="display:none!important;"</c:if> >
                <dl>
                    <dt>宽度：</dt>
                    <dd>
                        <input type="text" name="width" class="required digits" size="25" value="${power.width}"/>
                    </dd>
                </dl>

                <dl>
                    <dt>高度：</dt>
                    <dd>
                        <input type="text" name="height" class="required digits" size="25" value="${power.height}"/>
                    </dd>
                </dl>

                <dl>
                    <dt>属性：</dt>
                    <dd>
                        <input type="checkbox" name="maxable" value="1" checked="checked" disabled="disabled"/>最大化
                        <input type="checkbox" name="minable" value="1" checked="checked" disabled="disabled"/>最小化
                        <input type="checkbox" name="mask" value="1"
                               <c:if test="${mask eq 1}">checked="checked"</c:if> />遮盖
                        <input type="checkbox" name="drawable" value="1"
                               <c:if test="${drawable eq 1}">checked="checked"</c:if>/>拖动
                        <input type="checkbox" name="resizable" value="1"
                               <c:if test="${resizable eq 1}">checked="checked"</c:if> />可变大小
                    </dd>
                </dl>
            </div>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <input type="hidden" name="powerId" value="${power.powerId}"/>
                            <input type="hidden" name="oldParent" value="${power.parent}"/>
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
