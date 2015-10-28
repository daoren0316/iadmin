<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">缴纳停车费</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/charge_tcFee.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">

            <dl>
                <dt>业主户号：</dt>
                <dd>
                    <select class="combox" name="buildingId" ref="tcFeeComboxUnit_" id="tcFeeComboxBuilding_"
                            refUrl="<%=basePath%>site/charge_loadUnit.do?buildingId={value}">
                        <option value="0">请选择幢</option>
                        <c:if test="${!empty buildingList}">
                            <c:forEach items="${buildingList}" var="bl">
                                <option value="${bl.buildingId}">${bl.buildingTitle}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <select class="combox" name="unitId" id="tcFeeComboxUnit_" ref="tcFeeComboxHouse_"
                            refUrl="<%=basePath%>site/charge_loadHouse.do?unitId={value}">
                        <option value="0">请选择单元</option>
                    </select>
                    <select class="combox" name="houseId" id="tcFeeComboxHouse_">
                        <option value="0">请选择户号</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>开始时间：</dt>
                <dd>
                    <input name="beginDate" id="feeStartTime" type="text" readonly="readonly" class="required"
                           onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'feeEndTime\')||\'2020-10-01\'}'})"/>
                </dd>
            </dl>

            <dl>
                <dt>结束时间：</dt>
                <dd>
                    <input name="endDate" id="feeEndTime" type="text" readonly="readonly" class="required"
                           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'feeStartTime\')}',maxDate:'2020-10-01'})"/>
                </dd>
            </dl>

            <dl>
                <dt>车牌号：</dt>
                <dd>
                    <input type="text" name="note" class="required" size="20"/>
                </dd>
            </dl>

            <dl>
                <dt>金额：</dt>
                <dd>
                    <input type="text" name="amount" class="required number" size="10"/>
                </dd>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">缴费</button>
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



