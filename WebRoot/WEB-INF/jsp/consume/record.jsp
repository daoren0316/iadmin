<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>admin/consume_toIndex.do"
          method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        交易时间
                        <input name="startTime" id="ShopReceiptStartTime" type="text" value="${startTime }" readonly="readonly"
                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'ShopReceiptEndTime\')||\'2020-10-01\'}'})"/>
                        -
                        <input name="endTime" id="ShopReceiptEndTime" type="text" value="${endTime }" readonly="readonly"
                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ShopReceiptStartTime\')}',maxDate:'2020-10-01'})"/>
                    </td>
                    <td>
                        标识
                        <select name="flag">
                            <option value="1" <c:if test="${flag eq 1}"> selected="selected"</c:if>>未打款</option>
                            <option value="0" <c:if test="${flag eq 0}"> selected="selected"</c:if>>已打款</option>
                        </select>
                    </td>
                    <td>
                        <div style="float: left">
                            商户名称
                            <input name="shopId" value="${shopId}" type="hidden">
                            <input name="shopName" type="text" suggestFields="shopName" value="${shopName}"
                                   suggestUrl="javascript:;" lookupGroup="" readonly="readonly"/>
                        </div>
                        <a class="btnLook" href="<%=basePath%>site/pos_lookupShop.do" maxable="false"
                           rel="find_lookupShop" width="750" height="500" lookupGroup="">查找带回 - 商户名称</a>
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
            <th width="140">交易编号</th>
            <th width="150">交易时间</th>
            <th width="80">交易金额</th>
            <th width="200">业主地址</th>
            <th width="180">商户名称</th>
            <th>商户地址</th>
            <th width="60">标识</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty receiptAccountList}">
            <c:forEach items="${receiptAccountList}" var="ral" varStatus="sort">
                <tr target="sid_user" rel="${ral.id }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td>${ral.tradeId}</td>
                    <td><fmt:formatDate value="${ral.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>&yen;&nbsp;<fmt:formatNumber value=" ${ral.money}" pattern="0.00#"/></td>
                    <td>${ral.publicAddress }</td>
                    <td>${ral.shopName }</td>
                    <td>${ral.shopAddress }</td>
                    <td style="color: ${ral.flag eq 0 ? 'green': 'blue' }">${ral.flag eq 0 ? '已打款': '未打款' }</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty receiptAccountList}">
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

