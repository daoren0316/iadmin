<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>admin/life_toIndex.do"
          method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>证件号<input type="text" name="card" size="20" value="${card}"></td>
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
            <th width="35">序号</th>
            <th width="140">买家昵称</th>
            <th width="100">生效时间</th>
            <th width="100">到期时间</th>
            <th width="80">被保人名称</th>
            <th width="60">职位</th>
            <th width="160">证件号</th>
            <th width="100">手机号</th>
            <th>留言</th>
            <th width="130">添加时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty DatLifeTimeList}">
            <c:forEach items="${DatLifeTimeList}" var="ol" varStatus="sort">
                <tr target="sid_user" rel="${ol.id }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td>${ol.buyer_nick}</td>
                    <td><fmt:formatDate value="${ol.pay_time}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${ol.effectiveTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${ol.bbrName}</td>
                    <td>${ol.bbrOccupation}</td>
                    <td>${ol.bbrCard}</td>
                    <td>${ol.bbrPhone}</td>
                    <td title="${ol.bbrMessage}">
                        <c:if test="${fn:length(ol.bbrMessage)>20}">
                            ${fn:substring(ol.bbrMessage,0,20)}...
                        </c:if>
                        <c:if test="${fn:length(ol.bbrMessage)<=20}">
                            ${ol.bbrMessage}
                        </c:if>
                    </td>
                    <td><fmt:formatDate value="${ol.createdTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty DatLifeTimeList}">
            <tr>
                <td colspan="10" align="center">没有您需要的数据</td>
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

