<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>site/order_toIndex.do"
          method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        订单号
                        <input type="text" name="orderId" value="${orderId}"/>
                    </td>
                    <td>
                        下单时间
                        <input name="startTime" id="startTime" type="text" value="${startTime }" readonly="readonly"
                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"/>
                        -
                        <input name="endTime" id="endTime" type="text" value="${endTime }" readonly="readonly"
                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})"/>
                    </td>
                    <td>
                        交易状态
                        <select name="tradeStatus">
                            <option value="0" <c:if test="${tradeStatus eq 0}">selected="selected"</c:if>>未完成</option>
                            <option value="1" <c:if test="${tradeStatus eq 1}">selected="selected"</c:if>>完成</option>
                        </select>
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
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"/></th>
            <th width="150">订单号</th>
            <th width="150">下单时间</th>
            <th width="80">订单类型</th>
            <th width="80">订单金额</th>
            <th>买家地址</th>
            <th width="200">订单简述</th>
            <th width="80">订单状态</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty orderList}">
            <c:forEach items="${orderList}" var="ol" varStatus="sort">
                <tr target="sid_user" rel="${ol.orderId }">
                    <td><input name="ids" value="${ol.orderId }" type="checkbox"/></td>
                    <td>${ol.orderId }</td>
                    <td><fmt:formatDate value="${ol.createedTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${ol.orderType eq 1 ? '团购' : '团购'}</td>
                    <td><fmt:formatNumber value="${ol.amount }" pattern="0.00"/></td>
                    <td>${ol.publicAddress }</td>
                    <td title="${ol.commodityTitle }">
                        <c:if test="${fn:length(ol.commodityTitle) > 15}">
                            ${fn:substring(ol.commodityTitle,0,15 )}...
                        </c:if>
                        <c:if test="${fn:length(ol.commodityTitle) <= 15}">
                            ${ol.commodityTitle }
                        </c:if>
                    </td>
                    <td style="color: ${ol.tradeStatus?'green':'blue' }">${ol.tradeStatus?'完成':'未完成' }</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty orderList}">
            <tr>
                <td colspan="7" align="center">没有您需要的数据</td>
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

