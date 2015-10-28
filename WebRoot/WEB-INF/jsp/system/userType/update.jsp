<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">修改类型信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/type_update.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>类型名称：</dt>
                <dd>
                    <input type="text" name="typeName" class="required" size="25"
                           remote="<%=basePath%>admin/type_check.do?typeId=${type.typeId}" value="${type.typeName}"/>
                </dd>
            </dl>

            <dl>
                <dt>类型标识：</dt>
                <dd>
                    <input type="text" name="typeFlag" class="required digits" max="99"
                           min="0" size="25" remote="<%=basePath%>admin/type_checkFlag.do?typeId=${type.typeId}"
                           value="${type.typeFlag}"/>
                </dd>
            </dl>

            <dl>
                <dt>拥有角色：</dt>
                <dd>
                    <c:forEach items="${roleList}" var="rl">
                        <input type="checkbox" name="role" id="role${rl.roleId }" value="${rl.roleId }"/>${rl.roleName }
                    </c:forEach>
                </dd>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <input type="hidden" name="typeId" value="${type.typeId}">
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
    $(function () {
        <c:forEach items="${hasRole}" var="hs">
        $("#role${hs.roleId }").attr("checked", "checked");
        </c:forEach>
    });
</script>