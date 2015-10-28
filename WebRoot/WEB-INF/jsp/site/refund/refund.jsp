<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">退款</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/charge_confirm.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">

            <dl>
                <dt>业主户号：</dt>
                <dd>
                    <c:if test="${adminUser.sysFlag}">
                        <select class="combox" name="communityId" id="RefundComboxCommunityId_"
                                ref="RefundComboxBuildingId_"
                                refUrl="<%=basePath%>site/charge_loadBuilding.do?communityId={value}">
                            <option value="0">请选择小区</option>
                            <c:if test="${!empty communityList}">
                                <c:forEach items="${communityList}" var="bl">
                                    <option value="${bl.communityId}">${bl.communityName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </c:if>
                    <select class="combox" name="buildingId" id="RefundComboxBuildingId_" ref="RefundComboxUnit_"
                            refUrl="<%=basePath%>site/charge_loadUnit.do?buildingId={value}">
                        <option value="0">请选择幢</option>
                        <c:if test="${!empty buildingList}">
                            <c:forEach items="${buildingList}" var="bl">
                                <option value="${bl.buildingId}">${bl.buildingTitle}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <select class="combox" name="unitId" id="RefundComboxUnit_" ref="RefundComboxHouse_"
                            refUrl="<%=basePath%>site/charge_loadHouse.do?unitId={value}">
                        <option value="0">请选择单元</option>
                    </select>
                    <select class="combox" name="houseId" id="RefundComboxHouse_"
                            onchange="toLoadPhoneNumber_(this.value,'Refund','<%=basePath%>',true,false,'site/charge_loadMainPhone.do')">
                        <option value="0">请选择户号</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>手机号码：</dt>
                <dd>
                    <div class="divPanel1" id="RefundPhoneNumberTip_">
                        <input type="text" name="phoneNumber" id="RefundPhoneNumber_" class="required phone" size="25"
                               readonly="readonly"/></div>

                    <div class="divPanel1">
                        <span class="info">请输入用户注册时填写的手机号</span></div>
                </dd>
            </dl>

            <dl>
                <dt>验证码：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="text" name="rank" id="RefundRank_" class="required digits" size="12"
                               maxlength="6"/>
                    </div>

                    <div class="divPanel1">
                        <input type="button" style="border: 1px #7f7f7f solid;cursor: pointer" id="RefundSpanBtn_"
                               value="发送验证码"
                               onclick="return sendCode('Refund','<%=basePath%>','site/charge_loadAccount.do','Amount_')"/>
                    </div>
                </dd>
            </dl>

            <dl>
                <dt>钱包余额：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="text" name="amount" id="RefundAmount_" class="required number" size="25"
                               readonly="readonly"/></div>
                </dd>
            </dl>

            <dl>
                <div style="float:left;padding: 5px 0px;">
                    <ul style="padding: 5px;">退款说明：
                        <li style="padding: 10px 5px 0px 20px;">
                            1. 只能为
                            <c:if test="${adminUser.sysFlag}">本系统</c:if>
                            <c:if test="${!adminUser.sysFlag}">本站 (${communityName}) </c:if> 业主进行退款
                        </li>
                        <li style="padding: 10px 5px 0px 20px;">2. 只有家主才能进行退款</li>
                        <li style="padding: 10px 5px 0px 20px;">3. 手动点击发送验证码并成功校验，系统将自动填充钱包余额</li>
                    </ul>
                </div>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonDisabled" id="RefundButtonActive_">
                        <div class="buttonContent">
                            <button type="button" id="RefundConfirmBtn_" disabled="disabled"
                                    onclick="return toConfirmMsgBtn_('Refund','<%=basePath%>site/charge_confirmRefund.do','确认退款信息')">
                                确认退款
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