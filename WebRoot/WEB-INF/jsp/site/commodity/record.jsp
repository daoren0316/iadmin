<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>site/commodity_toIndex.do"
          method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        发布时间
                        <input name="starttime" id="startTime" type="text" value="${starttime }" readonly="readonly"
                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"/>
                        -
                        <input name="endtime" id="endTime" type="text" value="${endtime }" readonly="readonly"
                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})"/>
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
            <th width="80">序列号</th>
            <th>标题</th>
            <th width="150">发布时间</th>
            <th width="80">团购价</th>
            <th width="80">市场价</th>
            <th width="100">开始时间</th>
            <th width="100">结束时间</th>
            <th width="80">商品总数</th>
            <th width="80">最多购买数</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty commodityList}">
            <c:forEach items="${commodityList}" var="cl" varStatus="sort">
                <tr target="sid_user" rel="${cl.commodityId }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td title="${cl.commodityTitle}">
                        <c:if test="${fn:length(cl.commodityTitle)>=35}">
                            ${fn:substring(cl.commodityTitle,0,35)}...
                        </c:if>
                        <c:if test="${fn:length(cl.commodityTitle)<35}">
                            ${cl.commodityTitle}
                        </c:if>
                    </td>
                    <td><fmt:formatDate value="${cl.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${cl.price}</td>
                    <td>${cl.marketPrice}</td>
                    <td><fmt:formatDate value="${cl.startTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${cl.endTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${cl.totalAmount}</td>
                    <td>${cl.amountLimit}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty commodityList}">
            <tr>
                <td colspan="9" align="center">没有您需要的数据</td>
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

