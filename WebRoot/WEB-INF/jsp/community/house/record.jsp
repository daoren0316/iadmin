<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>admin/house_toIndex.do"
          method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        户名称
                        <input type="text" name="houseName" value="${houseName}"/>
                    </td>
                    <td>
                        所属小区
                        <select class="combox" style="float: none" name="communityId" ref="HouseComboxBuilding_"
                                refUrl="<%=basePath%>site/charge_loadBuilding.do?communityId={value}">
                            <option value="0">请选择小区</option>
                            <c:forEach items="${communityList}" var="cl">
                                <option value="${cl.communityId}"
                                        <c:if test="${cl.communityId eq communityId}">selected="selected"</c:if> >${cl.communityName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        所属幢
                        <select class="combox" style="float: none" name="buildingId" id="HouseComboxBuilding_"
                                ref="HouseComboxUnit_" refUrl="<%=basePath%>site/charge_loadUnit.do?buildingId={value}">
                            <option value="0">请选择幢</option>
                            <c:forEach items="${buildingList}" var="bl">
                                <option value="${bl.buildingId}"
                                        <c:if test="${bl.buildingId eq buildingId}">selected="selected"</c:if> >${bl.buildingTitle}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        所属单元
                        <select class="combox" style="float: none" name="unitId" id="HouseComboxUnit_">
                            <option value="0">请选择单元</option>
                            <c:forEach items="${unitList}" var="ul">
                                <option value="${ul.unitId}"
                                        <c:if test="${ul.unitId eq unitId}">selected="selected"</c:if> >${ul.unitName}</option>
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

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
    <div class="panelBar">
        <ul class="toolBar">

        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="35">序号</th>
            <th>户号</th>
            <th width="200">详细地址</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty houseList}">
            <c:forEach items="${houseList}" var="ul" varStatus="sort">
                <tr target="sid_user" rel="${ul.houseId }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td>${ul.houseName }</td>
                    <td>${ul.communityName }&nbsp;${ul.buildingTitle }${ul.unitName }${ul.houseName }</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty houseList}">
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

