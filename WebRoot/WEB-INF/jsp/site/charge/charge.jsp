<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">线下充值</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/charge_confirm.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">

            <dl>
                <c:if test="${adminUser.sysFlag}">
                    <p id="ChargeAccountPanel_" style="float:left;padding: 5px;color:blue;font-size: 14px;font-weight:bold;display: none;"></p>
                </c:if>
                <c:if test="${!adminUser.sysFlag}">
                    <p id="ChargeAccountPanel_" style="float:left;padding: 5px;color:blue;font-size: 14px;font-weight:bold;">
                        本站当前余额为 <fmt:formatNumber pattern="0.00" value="${money}"/> 元
                    </p>
                </c:if>
            </dl>

            <dl>
                <dt>业主户号：</dt>
                <dd>
                    <c:if test="${adminUser.sysFlag}">
                        <select class="combox" name="communityId" id="ChargeComboxCommunityId_"
                                ref="ChargeComboxBuildingId_"
                                refUrl="<%=basePath%>site/charge_loadBuilding.do?communityId={value}"
                                onchange="loadSiteAccount(this.value,'Charge','<%=basePath%>')">
                            <option value="0">请选择小区</option>
                            <c:if test="${!empty communityList}">
                                <c:forEach items="${communityList}" var="bl">
                                    <option value="${bl.communityId}">${bl.communityName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </c:if>
                    <select class="combox" name="buildingId" id="ChargeComboxBuildingId_" ref="ChargeComboxUnit_"
                            refUrl="<%=basePath%>site/charge_loadUnit.do?buildingId={value}">
                        <option value="0">请选择幢</option>
                        <c:if test="${!empty buildingList}">
                            <c:forEach items="${buildingList}" var="bl">
                                <option value="${bl.buildingId}">${bl.buildingTitle}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <select class="combox" name="unitId" id="ChargeComboxUnit_" ref="ChargeComboxHouse_"
                            refUrl="<%=basePath%>site/charge_loadHouse.do?unitId={value}">
                        <option value="0">请选择单元</option>
                    </select>
                    <select class="combox" name="houseId" id="ChargeComboxHouse_"
                            onchange="toLoadPhone_(this.value,'Charge','<%=basePath%>',true,false)">
                        <option value="0">请选择户号</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>手机号码：</dt>
                <dd>
                    <div class="divPanel1" id="ChargePhoneNumberTip_">
                        <input type="text" name="phoneNumber" id="ChargePhoneNumber_" class="required phone" size="25"
                               readonly="readonly"/></div>

                    <div class="divPanel1">
                        <span class="info">请输入用户注册时填写的手机号</span></div>
                </dd>
            </dl>

            <dl>
                <dt>金额：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="text" name="amount" id="ChargeAmount_" class="required number" size="25"/></div>

                    <div class="divPanel1">
                        <span class="info">充值金额不能大于小站余额</span></div>
                </dd>
            </dl>

            <dl>
                <div style="float:left;padding: 5px 0px;">
                    <ul style="padding: 5px;">充值说明：
                        <li style="padding: 10px 5px 0px 20px;">
                            1. 只能为
                            <c:if test="${adminUser.sysFlag}">本系统</c:if>
                            <c:if test="${!adminUser.sysFlag}">本站 (${communityName}) </c:if> 业主进行充值
                        </li>
                        <li style="padding: 10px 5px 0px 20px;">2. 充值金额不能大于所属小站的总账户余额</li>
                    </ul>
                </div>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="button"
                                    onclick="return toConfirmMsgBtn_('Charge','<%=basePath%>site/charge_confirm.do','确认充值信息')">
                                充值
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