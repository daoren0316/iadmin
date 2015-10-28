<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>admin/consultant_toIndex.do"
          method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        顾问名称：
                        <input type="text" name="consultantName" value="${consultantName}"/>
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
            <th width="80">序列号</th>
            <th width="120">顾问名称</th>
            <th width="150">顾问类型</th>
            <th width="150">联系电话</th>
            <th>地址</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty consultantList}">
            <c:forEach items="${consultantList}" var="cl" varStatus="sort">
                <tr target="sid_user" rel="${cl.consultantId }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td>${cl.consultantName}</td>
                    <td><c:if test="${cl.consultantType eq 1}">小区律师</c:if>
                        <c:if test="${cl.consultantType eq 2}">社区医生</c:if>
                        <c:if test="${cl.consultantType eq 3}">旅游向导</c:if>
                        <c:if test="${cl.consultantType eq 4}">理财专家</c:if>
                        <c:if test="${cl.consultantType eq 5}">汽车顾问</c:if></td>
                    <td>${cl.phoneNumber}</td>
                    <td>${cl.publicAddress}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty consultantList}">
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

