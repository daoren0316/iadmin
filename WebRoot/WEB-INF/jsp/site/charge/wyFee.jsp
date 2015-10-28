<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">缴纳 - 物业费/停车费</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/charge_wyFee.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <p style="float:left;padding: 5px;color:blue;font-size: 14px;font-weight:bold;">
                    业主当前余额为 <span id="wyFeeAmountPanel_">0</span> 元
                </p>
            </dl>

            <dl>
                <dt>业主户号：</dt>
                <dd>
                    <c:if test="${adminUser.sysFlag}">
                        <select class="combox" name="communityId" id="wyFeeComboxCommunityId_"
                                ref="wyFeeComboxBuildingId_"
                                refUrl="<%=basePath%>site/charge_loadBuilding.do?communityId={value}">
                            <option value="0">请选择小区</option>
                            <c:if test="${!empty communityList}">
                                <c:forEach items="${communityList}" var="bl">
                                    <option value="${bl.communityId}">${bl.communityName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </c:if>
                    <select class="combox" name="buildingId" ref="wyFeeComboxUnit_" id="wyFeeComboxBuildingId_"
                            refUrl="<%=basePath%>site/charge_loadUnit.do?buildingId={value}">
                        <option value="0">请选择幢</option>
                        <c:if test="${!empty buildingList}">
                            <c:forEach items="${buildingList}" var="bl">
                                <option value="${bl.buildingId}">${bl.buildingTitle}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <select class="combox" name="unitId" id="wyFeeComboxUnit_" ref="wyFeeComboxHouse_"
                            refUrl="<%=basePath%>site/charge_loadHouse.do?unitId={value}">
                        <option value="0">请选择单元</option>
                    </select>
                    <select class="combox" name="houseId" id="wyFeeComboxHouse_"
                            onchange="queryAmount(this.value,'wyFee','<%=basePath%>')">
                        <option value="0">请选择户号</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>缴费类型：</dt>
                <dd>
                    <input type="radio" name="feeType" value="1" checked="checked"/>物业费
                    <input type="radio" name="feeType" value="2"/>停车费
                </dd>
            </dl>

            <dl>
                <dt>开始时间：</dt>
                <dd>
                    <div class="divPanel1">
                        <input name="beginDate" id="wyFeeStartTime" type="text" readonly="readonly" class="required"
                               size="25" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'wyFeeEndTime\')||\'2020-10-01\'}'})"/>
                    </div>
                    <div class="divPanel1">
                        <span class="info">请选择缴费的开始时间</span></div>
                </dd>
            </dl>

            <dl>
                <dt>结束时间：</dt>
                <dd>
                    <div class="divPanel1">
                        <input name="endDate" id="wyFeeEndTime" type="text" readonly="readonly" class="required" size="25"
                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'wyFeeStartTime\')}',maxDate:'2020-10-01'})"/>
                    </div>

                    <div class="divPanel1">
                        <span class="info">请选择缴费的结束时间</span></div>
                </dd>
            </dl>

            <dl>
                <dt>金额：</dt>
                <dd>
                    <input type="text" name="amount" class="required number" size="25"/>
                </dd>
            </dl>

            <dl>
                <div style="float:left;padding: 5px 0px;">
                    <ul style="padding: 5px;">缴费说明：
                        <li style="padding: 10px 5px 0px 20px;">
                            1. 只能为
                            <c:if test="${adminUser.sysFlag}">本系统</c:if>
                            <c:if test="${!adminUser.sysFlag}">本站 (${communityName}) </c:if> 业主代缴物业费/停车费
                        </li>
                        <li style="padding: 10px 5px 0px 20px;">2. 缴费金额不能大于业主的当前账户余额</li>
                    </ul>
                </div>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit" onclick="return window.confirm('确定缴费么？');">缴费</button>
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



