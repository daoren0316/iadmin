<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">添加幢信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/building_insert.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <c:if test="${adminUser.sysFlag}">
                <dl>
                    <dt>所属小区：</dt>
                    <dd style="width: 400px;">
                        <select name="communityId" class="combox required">
                            <option value="">请选择小区</option>
                            <c:forEach items="${communityList}" var="cl">
                                <option value="${cl.communityId}">${cl.communityName}</option>
                            </c:forEach>
                        </select>
                    </dd>
                </dl>
            </c:if>

            <dl>
                <dt>幢名称：</dt>
                <dd style="width: 400px;">
                    <input type="text" name="buildingTitle" class="required" size="25"/>
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