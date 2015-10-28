<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<form id="pagerForm" onsubmit="return divSearch(this,'communityBox');"
      action="<%=basePath%>admin/unit_unitTree.do?buildingId=${buildingId}" method="post">
    <input type="hidden" name="status" value="${param.status}">
    <input type="hidden" name="pageNum" value="${pageNum }"/>
    <input type="hidden" name="numPerPage" value="${numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
</form>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
    <div class="panelBar">
        <ul class="toolBar">
        </ul>
    </div>
    <table class="table" width="100%" layoutH="75" ref="communityBox">
        <thead>
        <tr>
            <th width="35">序号</th>
            <th>单元名</th>
            <th width="200">详细地址</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty unitList}">
            <c:forEach items="${unitList}" var="ul" varStatus="sort">
                <tr target="sid_user" rel="${ul.unitId }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td>${ul.unitName }</td>
                    <td>${ul.communityName }&nbsp;${ul.buildingTitle }${ul.unitName }</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty unitList}">
            <tr>
                <td colspan="3" align="center">没有您需要的数据</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'communityBox')">
                <option value="20" <c:if test="${numPerPage eq 20 }">selected="selected"</c:if>>20</option>
                <option value="30" <c:if test="${numPerPage eq 30 }">selected="selected"</c:if>>30</option>
                <option value="50" <c:if test="${numPerPage eq 50 }">selected="selected"</c:if>>50</option>
            </select>
            <span>条，共${page.allRecorders}条</span>
        </div>

        <div class="pagination" rel="communityBox" totalCount="${page.allRecorders }" numPerPage="${numPerPage }"
             currentPage="${pageNum }"></div>
    </div>
</div>

