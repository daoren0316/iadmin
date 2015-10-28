<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">修改角色信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/role_update.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>角色名称：</dt>
                <dd>
                    <input type="text" name="roleName" class="required" size="25"
                           remote="<%=basePath%>admin/role_check.do?roleId=${role.roleId}" value="${role.roleName}"/>
                </dd>
            </dl>
            <dl>
                <dt>角色状态：</dt>
                <dd>
                    <input type="radio" name="flag" value="0" <c:if test="${role.flag eq 0 }">checked="checked"</c:if>/>正常
                    <input type="radio" name="flag" value="1" <c:if test="${role.flag eq 1 }">checked="checked"</c:if>/>禁用
                </dd>
            </dl>
            <dl>
                <dt>拥有权限：</dt>
                <dd style="border: 1px solid #ccc; height: 285px;overflow:auto;">
                    ${tree}
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <input type="hidden" name="roleId" value="${role.roleId}">
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