<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">注销卡片</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/card_confirmCancel.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">

            <dl>
                <dt>业主户号：</dt>
                <dd>
                    <c:if test="${adminUser.sysFlag}">
                        <select class="combox" name="communityId" id="CancelComboxCommunityId_"
                                ref="CancelComboxBuildingId_"
                                refUrl="<%=basePath%>site/charge_loadBuilding.do?communityId={value}">
                            <option value="0">请选择小区</option>
                            <c:if test="${!empty communityList}">
                                <c:forEach items="${communityList}" var="bl">
                                    <option value="${bl.communityId}">${bl.communityName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </c:if>
                    <select class="combox" name="buildingId" id="CancelComboxBuildingId_" ref="CancelComboxUnit_"
                            refUrl="<%=basePath%>site/charge_loadUnit.do?buildingId={value}">
                        <option value="0">请选择幢</option>
                        <c:if test="${!empty buildingList}">
                            <c:forEach items="${buildingList}" var="bl">
                                <option value="${bl.buildingId}">${bl.buildingTitle}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <select class="combox" name="unitId" id="CancelComboxUnit_" ref="CancelComboxHouse_"
                            refUrl="<%=basePath%>site/charge_loadHouse.do?unitId={value}">
                        <option value="0">请选择单元</option>
                    </select>
                    <select class="combox" name="houseId" id="CancelComboxHouse_" ref="CancelPhoneNumber_"
                            onchange="toLoadPhone_(this.value,'Cancel','<%=basePath%>',true,false)">
                        <option value="0">请选择户号</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>手机号码：</dt>
                <dd>
                    <p style="width: 500px;padding: 0;" id="CancelPhoneNumberTip_">
                        <input type="text" name="phoneNumber" id="CancelPhoneNumber_" class="required phone" size="25"
                               readonly="readonly"/>
                    </p>

                    <p style="width: 500px;padding: 5px 0px;">
                        <span class="info">请输入/选择用户注册时填写的手机号</span></p>
                </dd>
            </dl>

            <dl>
                <dt>验证码：</dt>
                <dd>
                    <p style="width: 500px;padding: 0;">
                        <input type="text" name="rank" id="CancelRank_" class="required digits" size="12"
                               maxlength="6"/>
                    </p>

                    <p style="width: 500px;padding: 5px 0px;">
                        <input type="button" style="border: 1px #7f7f7f solid;cursor: pointer" id="CancelSpanBtn_"
                               value="发送验证码"
                               onclick="return sendCode('Cancel','<%=basePath%>','site/charge_loadCard.do','CardNo_')"/>
                    </p>
                </dd>
            </dl>

            <dl>
                <dt>卡号：</dt>
                <dd>
                    <p style="width: 500px;padding: 0;">
                        <input type="text" name="cardNo" id="CancelCardNo_" class="required" size="25" maxlength="18"
                               readonly="readonly"/>
                    </p>

                    <p style="width: 500px;padding: 5px 0px;">
                        <span class="info">验证码校验通过后系统将自动填充卡号</span></p>
                </dd>
            </dl>

            <div style="float:left;padding: 5px 0px;">
                <ul style="padding: 5px;">卡注销说明：
                    <li style="padding: 10px 5px 0px 20px;">1. 只能为
                        <c:if test="${adminUser.sysFlag}">本系统</c:if>
                        <c:if test="${!adminUser.sysFlag}">本站 (${communityName}) </c:if> 业主注销卡片
                    </li>
                    <li style="padding: 10px 5px 0px 20px;">2. 成功选择户号后，系统将列出该户已注册的所有成员手机号码</li>
                    <li style="padding: 10px 5px 0px 20px;">3. 手动点击发送验证码并成功校验，系统将自动填充卡号</li>
                </ul>
            </div>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonDisabled" id="CancelButtonActive_">
                        <div class="buttonContent">
                            <button type="button" id="CancelConfirmBtn_" disabled="disabled"
                                    onclick="return toConfirmMsgBtn_('Cancel','<%=basePath%>site/card_confirmCancel.do','确认卡片信息')">
                                注销
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



