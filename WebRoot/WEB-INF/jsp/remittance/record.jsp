<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form id="pagerForm" method="post" action="<%=basePath%>admin/rite_toIndex.do">
    <input type="hidden" name="status" value="${param.status}">
    <input type="hidden" name="pageNum" value="${pageNum }"/>
    <input type="hidden" name="numPerPage" value="${numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="<%=basePath%>admin/rite_toIndex.do" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        打款时间
                        <input name="startTime" id="startTime" type="text"
                               readonly="readonly" size="15"
                               value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd"/>"
                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"/>
                        -
                        <input name="endTime" id="endTime" type="text"
                               readonly="readonly" size="15"
                               value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd"/>"
                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})"/>
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
            <li><a class="add" href="<%=basePath %>admin/rite_toInsert.do" target="dialog" width="750" height="500"
                   rel="insertRite">
                <span>财务打款</span></a></li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="35">序号</th>
            <th width="150">打款时间</th>
            <th width="100">打款金额</th>
            <th width="150">收款商户</th>
            <th width="100">商户电话</th>
            <th width="200">时间段</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty riteList}">
            <c:forEach items="${riteList}" var="rl" varStatus="sort">
                <tr target="sid_user" rel="${rl.id }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td><fmt:formatDate value="${rl.initTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>&yen;&nbsp;<fmt:formatNumber value="${rl.money}" pattern="0.00#"/></td>
                    <td>${rl.shopName}</td>
                    <td>${rl.phoneNumber}</td>
                    <td><fmt:formatDate value="${rl.startTime}" pattern="yyyy-MM-dd"/>
                        -
                        <fmt:formatDate value="${rl.endTime}" pattern="yyyy-MM-dd"/></td>
                    <td title="${rl.note }">
                        <c:if test="${fn:length(rl.note) > 35}">
                            ${fn:substring(rl.note,0,35 )}...
                        </c:if>
                        <c:if test="${fn:length(rl.note) <= 35}">
                            ${rl.note }
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty riteList}">
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

