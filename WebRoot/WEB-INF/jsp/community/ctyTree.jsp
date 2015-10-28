<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<form id="pagerForm" onsubmit="return divSearch(this,'communityBox');"
      action="<%=basePath%>admin/cty_ctyTree.do?cityId=${cityId}" method="post">
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
    <table class="table" width="100%" layoutH="76" ref="communityBox">
        <thead>
        <tr>
            <th width="35">序号</th>
            <th>小区名称</th>
            <th width="80">所在城市</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty communityList}">
            <c:forEach items="${communityList}" var="cl" varStatus="sort">
                <tr target="sid_user" rel="${ml.id }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td>${cl.communityName }</td>
                    <td>${cl.cityName }</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty communityList}">
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

