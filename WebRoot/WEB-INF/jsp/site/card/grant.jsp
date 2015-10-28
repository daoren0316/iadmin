<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<h2 class="contentTitle">发放卡片</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/card_confirm.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>业主户号：</dt>
                <dd>
                    <c:if test="${adminUser.sysFlag}">
                        <select class="combox" name="communityId" id="GrantComboxCommunityId_"
                                ref="GrantComboxBuildingId_"
                                refUrl="<%=basePath%>site/charge_loadBuilding.do?communityId={value}">
                            <option value="0">请选择小区</option>
                            <c:if test="${!empty communityList}">
                                <c:forEach items="${communityList}" var="bl">
                                    <option value="${bl.communityId}">${bl.communityName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </c:if>
                    <select class="combox" name="buildingId" id="GrantComboxBuildingId_" ref="GrantComboxUnit_"
                            refUrl="<%=basePath%>site/charge_loadUnit.do?buildingId={value}">
                        <option value="0">请选择幢</option>
                        <c:if test="${!empty buildingList}">
                            <c:forEach items="${buildingList}" var="bl">
                                <option value="${bl.buildingId}">${bl.buildingTitle}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <select class="combox" name="unitId" id="GrantComboxUnit_" ref="GrantComboxHouse_"
                            refUrl="<%=basePath%>site/charge_loadHouse.do?unitId={value}">
                        <option value="0">请选择单元</option>
                    </select>
                    <select class="combox" name="houseId" id="GrantComboxHouse_"
                            onchange="toLoadPhone_(this.value,'Grant','<%=basePath%>',false,true)">
                        <option value="0">请选择户号</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>手机号码：</dt>
                <dd>
                    <div class="divPanel1" id="GrantPhoneNumberTip_">
                        <input type="text" name="phoneNumber" id="GrantPhoneNumber_" class="required phone" size="25"/>
                    </div>

                    <div class="divPanel1">
                        <span class="info">请输入/选择用户注册时填写的手机号</span></div>
                </dd>
            </dl>

            <dl>
                <dt>卡号：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="text" name="cardNo" id="GrantCardNo_" size="25" maxlength="18"/></div>

                    <div class="divPanel1">
                        <span class="info">如果不输入卡号，将从系统随机获取一个卡号</span></div>
                </dd>
            </dl>

            <div style="float:left;padding: 5px 0px;">
                <ul style="padding: 5px;">发卡说明：
                    <li style="padding: 10px 5px 0px 20px;">
                        1. 只能为
                        <c:if test="${adminUser.sysFlag}">本系统</c:if>
                        <c:if test="${!adminUser.sysFlag}">本站 (${communityName}) </c:if>业主发放卡片
                    </li>
                    <li style="padding: 10px 5px 0px 20px;">
                        2. 如果不输入卡号，将从系统随机获取一个卡号
                    </li>
                </ul>
            </div>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="button"
                                    onclick="return toConfirmMsgBtn_('Grant','<%=basePath%>site/card_confirm.do','确认发卡信息')">
                                发卡
                            </button>
                        </div>
                    </div>
                </li>
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