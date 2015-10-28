<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="<%=basePath%>site/feedback_toTSIndex.do"
          method="post">
        <input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="pageNum" value="${pageNum }"/>
        <input type="hidden" name="numPerPage" value="${numPerPage}"/>
        <input type="hidden" name="orderField" value="${param.orderField}"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        提交时间
                        <input name="startTime" id="startTime" type="text" value="${startTime }" readonly="readonly"
                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"/>
                        -
                        <input name="endTime" id="endTime" type="text" value="${endTime }" readonly="readonly"
                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})"/>
                    </td>
                    <td>
                        投诉类型
                        <select style="float: none"  class="combox" name="type">
                            <option value="">请选择类型</option>
                            <option value="1" <c:if test="${type eq 1}">selected="selected"</c:if>>投诉商家</option>
                            <option value="2" <c:if test="${type eq 2}">selected="selected"</c:if>>投诉物业</option>
                        </select>
                    </td>
                    <td>
                        <div style="float: left">
                            业主地址
                            <input type="hidden" id="${ref}HouseId_" name="houseId" value="${houseId}"/>
                            <input type="text" id="${ref}PublicAddress_" name="publicAddress"
                                   value="${publicAddress}" size="25" readonly="readonly"/>
                        </div>
                        <a class="btnLook" target="dialog" href="<%=basePath%>site/charge_LookupHouse.do?ref=${ref}"
                           maxable="false" rel="find_LookupHouse" mask="true" width="560" height="300">查找业主地址</a>
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
            <th width="100">投诉类型</th>
            <th width="150">提交时间</th>
            <th width="80">业主昵称</th>
            <th width="100">业主电话</th>
            <th width="200">业主地址</th>
            <th>内容</th>
            <th width="60">回访标识</th>
            <c:if test="${adminUser.sysFlag}">
                <th width="120">所属小区</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:if test="${!empty feedbackList}">
            <c:forEach items="${feedbackList}" var="al" varStatus="sort">
                <tr target="sid_user" rel="${al.feedbackId }">
                    <td>${((page.currentPage-1) * numPerPage)+sort.count }</td>
                    <td style="color: ${al.type eq 1 ? 'blue' : 'purple'}">${al.type eq 1 ? '投诉商家' : '投诉物业'}</td>
                    <td><fmt:formatDate value="${al.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${al.nickname}</td>
                    <td>${al.phoneNumber}</td>
                    <td>${al.publicAddress}</td>
                    <td title="${al.content}">
                        <c:if test="${fn:length(al.content) > 35}">
                            ${fn:substring(al.content,0,35 )}...
                        </c:if>
                        <c:if test="${fn:length(al.content) <= 35}">
                            ${al.content }
                        </c:if>
                    </td>
                    <td style="color:${al.flag eq 1 ? 'green':'red'} ">${al.flag eq 1?'已回访':'未回访'}</td>
                    <c:if test="${adminUser.sysFlag}">
                        <td>${al.communityName}</td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty feedbackList}">
            <tr>
                <td colspan="8" align="center">没有您需要的数据</td>
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

