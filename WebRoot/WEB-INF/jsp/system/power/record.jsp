<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>admin/power_toIndex.do"
          method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        权限名称
                        <input type="text" name="powerName" value="${powerName}">
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
    <table class="table" width="100%" layoutH="118">
        <thead>
        <tr>
            <th width="240">权限名称</th>
            <th>访问URL</th>
            <th width="120">唯一REL</th>
            <th width="80">权限等级</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty powerList}">
            <c:forEach items="${powerList}" var="top" varStatus="sort">
                <c:if test="${!empty top.powerLevel && top.powerLevel eq 0 }">
                    <tr target="sid_user" rel="${top.powerId }">
                        <td>&nbsp;${top.powerName}</td>
                        <td>${top.powerUrl}</td>
                        <td>${top.rel}</td>
                        <td>${top.powerLevel}</td>
                    </tr>
                    <c:forEach items="${powerList}" var="two" varStatus="sort">
                        <c:if test="${two.parent eq top.powerId && two.powerLevel eq 1}">
                            <tr target="sid_user" rel="${two.powerId }">
                                <td>
                                    <c:forEach begin="0" end="${(two.powerLevel) * 2}">
                                        &nbsp;
                                    </c:forEach>
                                    &lfloor;${two.powerName}</td>
                                <td>${two.powerUrl}</td>
                                <td>${two.rel}</td>
                                <td>${two.powerLevel}</td>
                            </tr>
                            <c:forEach items="${powerList}" var="three" varStatus="sort">
                                <c:if test="${three.parent eq two.powerId && three.powerLevel eq 2}">
                                    <tr target="sid_user" rel="${three.powerId }">
                                        <td>
                                            <c:forEach begin="0" end="${(three.powerLevel) * 2}">
                                                &nbsp;
                                            </c:forEach>
                                            &lfloor;${three.powerName}</td>
                                        <td>${three.powerUrl}</td>
                                        <td>${three.rel}</td>
                                        <td>${three.powerLevel}</td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${empty powerList}">
            <tr>
                <td colspan="4" align="center">没有您需要的数据</td>
            </tr>
        </c:if>
        </tbody>
    </table>

</div>

