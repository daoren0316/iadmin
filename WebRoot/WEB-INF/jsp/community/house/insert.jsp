<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">添加户信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/house_insert.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">

            <dl>
                <dt>所属单元：</dt>
                <dd style="width: 400px;">
                    <select class="combox" name="communityId" ref="InsertHouseComboxBuilding_"
                            refUrl="<%=basePath%>site/charge_loadBuilding.do?communityId={value}">
                        <option value="0">请选择小区</option>
                        <c:forEach items="${communityList}" var="cl">
                            <option value="${cl.communityId}">${cl.communityName}</option>
                        </c:forEach>
                    </select>
                    <select class="combox" style="float: none" name="buildingId" id="InsertHouseComboxBuilding_"
                            ref="InsertHouseComboxUnit_" refUrl="<%=basePath%>site/charge_loadUnit.do?buildingId={value}">
                        <option value="0">请选择幢</option>
                    </select>
                    <select class="combox" style="float: none" name="unitId" id="InsertHouseComboxUnit_">
                        <option value="0">请选择单元</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>户号：</dt>
                <dd style="width: 400px;">
                    <input type="text" name="houseName" class="required" size="25"/>
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