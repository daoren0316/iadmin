<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form id="pagerForm" method="post" action="<%=basePath%>admin/guestbook_toIndex.do">
    <input type="hidden" name="status" value="${param.status}">
    <input type="hidden" name="pageNum" value="${pageNum }"/>
    <input type="hidden" name="startTime" value="${startTime }"/>
    <input type="hidden" name="endTime" value="${endTime }"/>
    <input type="hidden" name="numPerPage" value="${numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="<%=basePath%>admin/guestbook_toIndex.do" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        留言时间：
                        <input name="startTime" id="startTime" type="text" value="${startTime }" readonly="readonly"
                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"/>
                        -
                        <input name="endTime" id="endTime" type="text" value="${endTime }" readonly="readonly"
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
            <li></li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="80">序列号</th>
            <th width="120">留言人</th>
            <th width="150">留言时间</th>
            <th>留言内容</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty guestbookList}">
            <c:forEach items="${guestbookList}" var="gbl" varStatus="sort">
                <tr target="sid_user" rel="${gbl.gid }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td>${gbl.username}</td>
                    <td><fmt:formatDate value="${gbl.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td title="${gbl.content }">
                        <c:if test="${fn:length(gbl.content) > 35}">
                            ${fn:substring(gbl.content,0,35 )}...
                        </c:if>
                        <c:if test="${fn:length(gbl.content) <= 35}">
                                ${gbl.content }
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty guestbookList}">
            <tr>
                <td colspan="4" align="center">没有您需要的数据</td>
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

