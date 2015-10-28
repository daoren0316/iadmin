<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="<%=basePath%>admin/sysActivity_toIndex.do" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        发布时间
                        <input name="starttime" id="sysActivityStartTime" type="text" value="${starttime }" readonly="readonly"
                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'sysActivityEndTime\')||\'2020-10-01\'}'})"/>
                        -
                        <input name="endtime" id="sysActivityEndTime" type="text" value="${endtime }" readonly="readonly"
                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sysActivityStartTime\')}',maxDate:'2020-10-01'})"/>
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
            <th width="35">序号</th>
            <th width="150">开始时间</th>
            <th width="100">结束时间</th>
            <th width="150">返还金额上限</th>
            <th>备注</th>
            <th width="100">状态</th>
            <th width="150">发布时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty sysActivityList}">
            <c:forEach items="${sysActivityList}" var="rl" varStatus="sort">
                <tr target="sid_user" rel="${rl.id }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td><fmt:formatDate value="${rl.startTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${rl.endTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${rl.money}元</td>
                    <td title="${rl.note }">
                        <c:if test="${fn:length(rl.note) > 35}">
                            ${fn:substring(rl.note,0,35 )}...
                        </c:if>
                        <c:if test="${fn:length(rl.note) <= 35}">
                            ${rl.note }
                        </c:if>
                    </td>
                    <td style="color:
                    <c:if test="${rl.flag eq 0}">#808080</c:if>
                    <c:if test="${rl.flag eq 1}">green</c:if>
                    <c:if test="${rl.flag eq 2}">darkorange</c:if>
                    <c:if test="${rl.flag eq 3}">red</c:if>">
                        <c:if test="${rl.flag eq 0}">未启用</c:if>
                        <c:if test="${rl.flag eq 1}">启用</c:if>
                        <c:if test="${rl.flag eq 2}">过期</c:if>
                        <c:if test="${rl.flag eq 3}">关闭</c:if>
                    </td>
                    <td>
                        <fmt:formatDate value="${rl.createdTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty sysActivityList}">
            <tr>
                <td colspan="6" align="center">没有您需要的数据</td>
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

