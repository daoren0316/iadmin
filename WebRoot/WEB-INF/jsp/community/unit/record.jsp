<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>admin/unit_toIndex.do"
          method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        单元名称
                        <input type="text" name="unitName" value="${unitName}"/>
                    </td>
                    <c:if test="${adminUser.sysFlag}">
                        <td>
                            所属小区
                            <select class="combox" style="float: none" name="communityId" ref="UnitComboxBuilding_"
                                    refUrl="<%=basePath%>site/charge_loadBuilding.do?communityId={value}">
                                <option value="0">请选择小区</option>
                                <c:forEach items="${communityList}" var="cl">
                                    <option value="${cl.communityId}"
                                            <c:if test="${cl.communityId eq communityId}">selected="selected"</c:if> >${cl.communityName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </c:if>
                    <td>
                        所在幢
                        <select class="combox" style="float: none" name="buildingId" id="UnitComboxBuilding_">
                            <option value="0">请选择幢</option>
                            <c:forEach items="${buildingList}" var="bl">
                                <option value="${bl.buildingId}"
                                        <c:if test="${bl.buildingId eq buildingId}">selected="selected"</c:if> >${bl.buildingTitle}</option>
                            </c:forEach>
                        </select>
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

