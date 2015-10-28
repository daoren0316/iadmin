<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">发布活动</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/sysActivity_insert.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">

            <dl>
                <dt>返还金额上限：</dt>
                <dd>
                    <input type="text" name="money" class="required number" size="25"/>
                </dd>
            </dl>

            <dl>
                <dt>开始时间：</dt>
                <dd>
                    <input name="startTime" id="addSysActivityStartTime" type="text" readonly="readonly" size="25" class="required"
                           onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'addSysActivityEndTime\')||\'2020-10-01\'}'})"/>
                </dd>
            </dl>

            <dl>
                <dt>结束时间：</dt>
                <dd>
                    <input name="endTime" id="addSysActivityEndTime" type="text" readonly="readonly" size="25" class="required"
                           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'addSysActivityStartTime\')}',maxDate:'2020-10-01'})"/>
                </dd>
            </dl>

            <dl>
                <dt>备注：</dt>
                <dd>
                    <textarea name="note" cols="80" rows="15"></textarea>
                </dd>
            </dl>

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