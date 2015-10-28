<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">绑定POS机信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/pos_insert.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>POS机编号：</dt>
                <dd>
                    <input type="text" name="posNo" class="required digits" size="25"
                           remote="<%=basePath%>site/pos_checkPosNo.do"/>
                </dd>
            </dl>

            <dl>
                <dt>绑定商户：</dt>
                <dd>
                    <div class="divPanel1">
                        <input name="shopId" type="hidden">
                        <input class="required" name="shopName" type="text" suggestFields="shopName"
                               suggestUrl="" size="25" lookupGroup="" readonly="readonly"/></div>

                    <div class="divPanel1">
                        <a class="btnLook" href="<%=basePath%>site/pos_lookupShop.do" maxable="false"
                           rel="add_lookupShop" width="750" height="500"
                           lookupGroup="">查找带回 - 绑定商户</a>
                        <span class="info">查找带回 - 绑定商户</span></div>
                </dd>
            </dl>

            <dl>
                <dt>商户编号：</dt>
                <dd>
                    <input type="text" name="shopNo" class="required digits" size="25"/>
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