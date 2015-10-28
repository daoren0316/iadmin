<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form id="pagerForm" action="<%=basePath%>site/site_lookupSite.do" method="post">
    <input type="hidden" name="status" value="${param.status}">
    <input type="hidden" name="pageNum" value="${pageNum }"/>
    <input type="hidden" name="numPerPage" value="${numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
    <input type="hidden" name="shopName" value="${shopName }"/>
</form>

<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return dialogSearch(this);" action="<%=basePath%>site/site_lookupSite.do"
          method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        小站名称
                        <input name="siteName" type="text" value="${siteName }"/>
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
    <table class="table" width="100%" layoutH="110">
        <thead>
        <tr>
            <th width="150">名称</th>
            <th>地址</th>
            <th width="40">带回</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty siteList}">
            <c:forEach items="${siteList}" var="sl" varStatus="sort">
                <tr target="sid_user" rel="${sl.siteId }">
                    <td>${sl.siteName}</td>
                    <td title="${sl.siteAddress}">${sl.siteAddress}</td>
                    <td>
                        <a class="btnSelect"
                           href="javascript:$.bringBack({siteId:'${sl.siteId }', siteName:'${sl.siteName}'})"
                           title="查找带回">选择</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty siteList}">
            <tr>
                <td colspan="3" align="center">没有您需要的数据</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})">
                <option value="20" <c:if test="${numPerPage eq 20 }">selected="selected"</c:if>>20</option>
                <option value="30" <c:if test="${numPerPage eq 30 }">selected="selected"</c:if>>30</option>
                <option value="50" <c:if test="${numPerPage eq 50 }">selected="selected"</c:if>>50</option>
            </select>
            <span>条，共${page.allRecorders}条</span>
        </div>

        <div class="pagination" targetType="dialog" totalCount="${page.allRecorders }" numPerPage="${numPerPage }"
             currentPage="${pageNum }"></div>
    </div>
</div>

