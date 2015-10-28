<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">确认发卡信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/card_grant.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">

            <dl>
                <dt>卡号：</dt>
                <dd style="width:260px;border: 1px solid #A2BAC0;">
                    ${empty cardNo ? '卡号将从系统随机获取' : cardNo}
                </dd>
            </dl>

            <dl>
                <dt>手机号码：</dt>
                <dd style="width:260px;border: 1px solid #A2BAC0;">
                    ${phoneNumber}
                </dd>
            </dl>

            <dl>
                <dt>昵称：</dt>
                <dd style="width:260px;border: 1px solid #A2BAC0;">
                    ${empty chargeUser.nickname ? '无' :chargeUser.nickname}
                </dd>
            </dl>

            <dl>
                <dt>业主地址：</dt>
                <dd style="width:260px;border: 1px solid #A2BAC0;">
                    ${address}
                </dd>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <input type="hidden" name="phoneNumber" value="${phoneNumber}"/>
                            <input type="hidden" name="cardNo" value="${cardNo}"/>
                            <input type="hidden" name="houseId" value="${houseId}"/>
                            <button type="submit" onclick="return window.confirm('确认发卡么？');">确认发卡</button>
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


