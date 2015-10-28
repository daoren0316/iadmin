<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">财务划款</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/money_insert.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">

            <dl>
                <dt>收款小站：</dt>
                <dd>
                    <div class="divPanel1">
                        <input name="siteId" type="hidden">
                        <input class="required" name="siteName" type="text" suggestFields="siteName"
                               suggestUrl="" size="25" lookupGroup="" readonly="readonly"/></div>

                    <div class="divPanel1">
                        <a class="btnLook" href="<%=basePath%>site/site_lookupSite.do" maxable="false"
                           rel="add_lookupShop" width="750" height="500"
                           lookupGroup="">查找带回 - 绑定小站</a>
                        <span class="info">查找带回 - 绑定小站</span></div>
                </dd>
            </dl>

            <dl>
                <dt>金额：</dt>
                <dd>
                    <input type="text" name="money" class="required number" size="25"/>
                </dd>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">确认划款</button>
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