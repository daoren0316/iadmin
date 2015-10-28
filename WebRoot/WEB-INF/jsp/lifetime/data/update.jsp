<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">修改信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/life_update.do" class="pageForm required-validate"
          onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="97">

            <dl>
                <dt>买家昵称：</dt>
                <dd>
                    <input type="text" name="buyer_nick" size="30" class="required" maxlength="50"
                           value="${datLifeTime.buyer_nick}"/>
                </dd>
            </dl>

            <dl>
                <dt>被保人名称：</dt>
                <dd>
                    <input type="text" name="bbrName" size="30" class="required" maxlength="20"
                           value="${datLifeTime.bbrName}"/>
                </dd>
            </dl>
            <dl>
                <dt>被保人职位：</dt>
                <dd>
                    <input type="text" name="bbrOccupation" size="30" class="required" maxlength="20"
                           value="${datLifeTime.bbrOccupation}"/>
                </dd>
            </dl>
            <dl>
                <dt>亲密照：</dt>
                <dd>
                    <div class="divPanel">
                        <input type="file" name="image"></div>
                    <div class="divPanel">
                        <span class="info">最佳格式为 660px * 400px，大小200k以内的jpg，png类型的图片</span></div>
                    <div class="divPanel">
                        <c:if test="${!empty datLifeTime.bbrImg}">
                            <img src="${datLifeTime.bbrImg}" height="260"/></c:if></div>
                </dd>
            </dl>
            <dl>
                <dt>证件号：</dt>
                <dd>
                    <input type="text" name="bbrCard" size="30" class="required" maxlength="20"
                           value="${datLifeTime.bbrCard}"/>
                </dd>
            </dl>
            <dl>
                <dt>手机号：</dt>
                <dd>
                    <input type="text" name="bbrPhone" size="30" class="required" maxlength="15"
                           value="${datLifeTime.bbrPhone}"/>
                </dd>
            </dl>
            <dl>
                <dt>留言：</dt>
                <dd>
                    <textarea name="bbrMessage" cols="80" rows="6">${datLifeTime.bbrMessage}</textarea>
                </dd>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <input type="hidden" name="id" value="${datLifeTime.id}"/>
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