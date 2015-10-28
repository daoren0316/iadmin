<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>admin/consume_toChargeIndex.do"
          method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        交易时间：
                        <input name="startTime" id="consumeStartTime" type="text" value="${startTime }"
                               readonly="readonly"
                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'consumeEndTime\')||\'2020-10-01\'}'})"/>
                        -
                        <input name="endTime" id="consumeEndTime" type="text" value="${endTime }" readonly="readonly"
                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'consumeStartTime\')}',maxDate:'2020-10-01'})"/>
                    </td>
                    <td>
                        手机号码
                        <input type="text" name="phoneNumber" size="20" value="${phoneNumber}"/>
                    </td>
                    <td>
                        交易类型
                        <select class="combox" name="tradeType" style="float: none;">
                            <option value="">请选择交易类型</option>
                            <option value="1" <c:if test="${tradeType eq 1}">selected="selected"</c:if>>线下充值</option>
                            <option value="8" <c:if test="${tradeType eq 8}">selected="selected"</c:if>>在线充值</option>
                            <option value="4" <c:if test="${tradeType eq 4}">selected="selected"</c:if>>转账-转入</option>
                        </select>
                    </td>
                    <td>
                        <div style="float: left">
                            业主地址
                            <input type="hidden" id="${ref}HouseId_" name="houseId" value="${houseId}"/>
                            <input type="text" id="${ref}PublicAddress_" name="publicAddress"
                                   value="${publicAddress}" size="25" readonly="readonly"/>
                        </div>
                        <a class="btnLook" target="dialog" href="<%=basePath%>site/charge_LookupHouse.do?ref=${ref}"
                           maxable="false" rel="find_LookupHouse" mask="true" width="560" height="300">查找业主地址</a>
                    </td>
                </tr>
            </table>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">检索</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li></li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="35">序号</th>
            <th width="140">订单号</th>
            <th width="100">交易类型</th>
            <th width="80">交易金额</th>
            <th width="200">业主地址</th>
            <th width="80">业主昵称</th>
            <th width="120">电话号码</th>
            <th width="150">交易时间</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty ConsumeLogDTOList}">
            <c:forEach items="${ConsumeLogDTOList}" var="ral" varStatus="sort">
                <tr target="sid_user" rel="${ral.orderId }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td>${ral.orderId}</td>
                    <td>
                        <c:if test="${ral.tradeType eq 1}">线下充值</c:if>
                        <c:if test="${ral.tradeType eq 8}">在线充值</c:if>
                        <c:if test="${ral.tradeType eq 2}">团购</c:if>
                        <c:if test="${ral.tradeType eq 5}">POS机刷卡</c:if>
                        <c:if test="${ral.tradeType eq 6}">缴费物业费</c:if>
                        <c:if test="${ral.tradeType eq 7}">缴费停车费</c:if>
                        <c:if test="${ral.tradeType eq 3}">转账-转出</c:if>
                        <c:if test="${ral.tradeType eq 4}">转账-转入</c:if>
                        <c:if test="${ral.tradeType eq 9}">退款</c:if>
                    </td>
                    <td>
                        &yen;&nbsp;<fmt:formatNumber value=" ${ral.amount}" pattern="0.00#"/>
                    </td>
                    <td>${ral.publicAddress }</td>
                    <td>${ral.nickname }</td>
                    <td>${ral.phoneNumber }</td>
                    <td><fmt:formatDate value="${ral.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td title="${ral.note }">
                        <c:if test="${fn:length(ral.note) > 25}">
                            ${fn:substring(ral.note,0,25 )}...
                        </c:if>
                        <c:if test="${fn:length(ral.note) <= 25}">
                            ${ral.note }
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty ConsumeLogDTOList}">
            <tr>
                <td colspan="8" align="center">没有您需要的数据</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="20" <c:if test="${numPerPage eq 20 }">selected="selected"</c:if>>20</option>
                <option value="30" <c:if test="${numPerPage eq 30 }">selected="selected"</c:if>>30</option>
                <option value="50" <c:if test="${numPerPage eq 50 }">selected="selected"</c:if>>50</option>
            </select>
            <span>条，共${page.allRecorders}条</span>
        </div>

        <div class="pagination" targetType="navTab" totalCount="${page.allRecorders }" numPerPage="${numPerPage }"
             currentPage="${pageNum }"></div>
    </div>
</div>

