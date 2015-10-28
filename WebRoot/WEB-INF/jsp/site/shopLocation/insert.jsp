<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">接入商户</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/shopLocation_insert.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <c:if test="${adminUser.sysFlag}">
                <dl>
                    <dt>所属小区：</dt>
                    <dd style="width: 400px;">
                        <select name="communityId" class="combox required">
                            <option value="">请选择所属小区</option>
                            <c:forEach items="${communityList}" var="cl">
                                <option value="${cl.communityId}">${cl.communityName}</option>
                            </c:forEach>
                        </select>
                    </dd>
                </dl>
            </c:if>

            <dl>
                <dt>商户名称：</dt>
                <dd style="width: 400px;">
                    <div class="divPanel1">
                        <input name="shopId" type="hidden">
                        <input class="required" name="shopName" type="text" suggestFields="shopName"
                               suggestUrl="" size="25" lookupGroup="" readonly="readonly"/></div>

                    <div class="divPanel1">
                        <a class="btnLook" href="<%=basePath%>site/pos_lookupShop.do" maxable="false"
                           rel="add_lookupShop" width="750" height="500"
                           lookupGroup="">查找带回 - 接入商户</a>
                        <span class="info">查找带回 - 接入商户</span></div>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">提交</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>

</div>