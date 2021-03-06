<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>site/pos_toIndex.do" method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        POS机编号
                        <input name="posNo" type="text" value="${posNo }"/>
                    </td>
                    <c:if test="${adminUser.sysFlag}">
                        <td>
                            所属小区
                            <select name="communityId" class="combox" style="float: none;">
                                <option value="">请选择所属小区</option>
                                <c:forEach items="${communityList}" var="cl">
                                    <option value="${cl.communityId}"
                                            <c:if test="${cl.communityId eq communityId}">selected="selected"</c:if>>${cl.communityName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </c:if>
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
            <th width="150">POS机编号</th>
            <th width="150">POS商户编号</th>
            <th>绑定商户</th>
            <th width="150">绑定时间</th>
            <c:if test="${adminUser.sysFlag}">
                <th width="120">所属小区</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty posList}">
            <c:forEach items="${posList}" var="pl" varStatus="sort">
                <tr target="sid_user" rel="${pl.posNo }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td>${pl.posNo}</td>
                    <td>${pl.shopNo}</td>
                    <td title="${pl.shopName}">${pl.shopName}</td>
                    <td><fmt:formatDate value="${pl.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <c:if test="${adminUser.sysFlag}">
                        <td>${pl.communityName}</td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty posList}">
            <tr>
                <td colspan="5" align="center">没有您需要的数据</td>
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

