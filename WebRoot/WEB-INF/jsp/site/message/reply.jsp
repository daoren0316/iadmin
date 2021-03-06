<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">回复留言</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/message_reply.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>接收人：</dt>
                <dd>
                    <input type="text" name="fromUsername" class="required" size="25" disabled="disabled"
                           value="${message.fromUsername}"/>
                </dd>
            </dl>

            <dl>
                <dt>留言内容：</dt>
                <dd>
                    <textarea name="content" cols="80" rows="20"></textarea>
                </dd>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <input type="hidden" name="fromUid" value="${message.fromUid}">
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