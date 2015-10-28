<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">业主余额查询</h2>

<div class="pageContent">
    <form method="post" action="#" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>业主户号：</dt>
                <dd>
                    <c:if test="${adminUser.sysFlag}">
                        <select class="combox" name="communityId" id="QueryComboxCommunityId_"
                                ref="QueryComboxBuildingId_"
                                refUrl="<%=basePath%>site/charge_loadBuilding.do?communityId={value}">
                            <option value="0">请选择小区</option>
                            <c:if test="${!empty communityList}">
                                <c:forEach items="${communityList}" var="bl">
                                    <option value="${bl.communityId}">${bl.communityName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </c:if>
                    <select class="combox" name="buildingId" id="QueryComboxBuildingId_" ref="QueryComboxUnit_"
                            refUrl="<%=basePath%>site/charge_loadUnit.do?buildingId={value}">
                        <option value="0">请选择幢</option>
                        <c:if test="${!empty buildingList}">
                            <c:forEach items="${buildingList}" var="bl">
                                <option value="${bl.buildingId}">${bl.buildingTitle}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <select class="combox" name="unitId" id="QueryComboxUnit_" ref="QueryComboxHouse_"
                            refUrl="<%=basePath%>site/charge_loadHouse.do?unitId={value}">
                        <option value="0">请选择单元</option>
                    </select>
                    <select class="combox" name="houseId" id="QueryComboxHouse_"
                            onchange="queryAmount(this.value,'Query','<%=basePath%>')">
                        <option value="0">请选择户号</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <p style="float:left;padding: 5px;color:blue;font-size: 14px;font-weight:bold;">
                    业主当前余额为 <span id="QueryAmountPanel_">0</span> 元
                </p>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">关闭</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>