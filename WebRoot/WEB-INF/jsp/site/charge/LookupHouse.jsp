<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<c:if test="${!adminUser.sysFlag}">
    <h2 class="contentTitle">${communityName}</h2>
</c:if>

<div class="pageContent">
    <div class="pageFormContent nowrap" layoutH="${adminUser.sysFlag ? 59:97}">

        <c:if test="${adminUser.sysFlag}">
            <dl>
                <dt>所属城市：</dt>
                <dd style="width: 300px;">
                    <select class="combox" name="cityId" id="${ref}ComboxCityId_" ref="${ref}ComboxCommunityId_"
                            refUrl="<%=basePath%>site/charge_LoadCommunity.do?cityId={value}">
                        <option value="0">请选择城市</option>
                        <c:if test="${!empty cityList}">
                            <c:forEach items="${cityList}" var="bl">
                                <option value="${bl.cityId}">${bl.cityName}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </dd>
            </dl>
            <dl>
                <dt>所在小区：</dt>
                <dd style="width: 300px;">
                    <select class="combox" name="communityId" id="${ref}ComboxCommunityId_" ref="${ref}ComboxbuildingId_"
                            refUrl="<%=basePath%>site/charge_loadBuilding.do?communityId={value}">
                        <option value="0">请选择小区</option>
                    </select>
                </dd>
            </dl>
        </c:if>

        <dl>
            <dt>业主户号：</dt>
            <dd style="width: 300px;">
                <select class="combox" name="buildingId" id="${ref}ComboxbuildingId_" ref="${ref}ComboxUnit_"
                        refUrl="<%=basePath%>site/charge_loadUnit.do?buildingId={value}">
                    <option value="0">请选择幢</option>
                    <c:if test="${!empty buildingList && !adminUser.sysFlag}">
                        <c:forEach items="${buildingList}" var="bl">
                            <option value="${bl.buildingId}">${bl.buildingTitle}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <select class="combox" name="unitId" id="${ref}ComboxUnit_" ref="${ref}ComboxHouse_"
                        refUrl="<%=basePath%>site/charge_loadHouse.do?unitId={value}">
                    <option value="0">请选择单元</option>
                </select>
                <select class="combox" name="houseId" id="${ref}ComboxHouse_"
                        onchange="showAddress(this.value,'${ref}','<%=basePath%>',${adminUser.sysFlag})">
                    <option value="0">请选择户号</option>
                </select>
            </dd>
        </dl>

        <dl id="${ref}TipPanel_">
            <input type="hidden" id="${ref}CommunityName_" value="${communityName}"/>
            <fieldset>
                <legend>您选择的地址为</legend>
                <span style="float:left;font-size: 16px;font-weight:bold;color: blue;height:32px;line-height: 32px;padding: 5px; ">
                    ${communityName}&nbsp;
                </span>
            </fieldset>
        </dl>
    </div>
    <div class="formBar">
        <ul>
            <li>
                <div class="buttonActive">
                    <div class="buttonContent">
                        <button type="button"
                                onclick="return affirmChoose('${ref}')">
                            确定
                        </button>
                    </div>
                </div>
            </li>
            <li>
                <div class="button">
                    <div class="buttonContent">
                        <button type="button" class="close"
                                onclick="return clearAffirm('${ref}')">
                            关闭清空
                        </button>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>