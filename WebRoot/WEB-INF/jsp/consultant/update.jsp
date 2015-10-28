<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">修改个人信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/consultant_update.do" class="pageForm required-validate"
          onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>登录名：</dt>
                <dd>
                    <input type="text" name="username" size="30" class="required" value="${username}"
                           disabled="disabled"/>
                </dd>
            </dl>

            <dl>
                <dt>类型：</dt>
                <dd>
                    <c:if test="${consultant.consultantType eq 1}">
                        <c:set var="consultantType">小区律师</c:set>
                    </c:if>
                    <c:if test="${consultant.consultantType eq 2}">
                        <c:set var="consultantType">社区医生</c:set>
                    </c:if>
                    <c:if test="${consultant.consultantType eq 3}">
                        <c:set var="consultantType">旅游向导</c:set>
                    </c:if>
                    <c:if test="${consultant.consultantType eq 4}">
                        <c:set var="consultantType">理财专家</c:set>
                    </c:if>
                    <c:if test="${consultant.consultantType eq 5}">
                        <c:set var="consultantType">汽车顾问</c:set>
                    </c:if>
                    <input type="text" name="consultantType" size="30" class="required" value="${consultantType}"
                           disabled="disabled"/>
                </dd>
            </dl>

            <dl>
                <dt>姓名：</dt>
                <dd>
                    <input type="text" name="consultantName" size="30" class="required"
                           value="${consultant.consultantName}" maxlength="5"
                           disabled="disabled"/>
                </dd>
            </dl>

            <dl>
                <dt>职务：</dt>
                <dd>
                    <input type="text" name="duty" size="30" maxlength="15" value="${consultant.duty}"/>
                </dd>
            </dl>

            <dl>
                <dt>联系电话：</dt>
                <dd>
                    <input type="text" name="phoneNumber" class="required phone" size="30" value="${phoneNumber}"/>
                </dd>
            </dl>

            <dl>
                <dt>距离：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="text" name="distance" class="required digits" size="10" maxlength="10"
                               value="${consultant.distance}"/></div>

                    <div class="divPanel1"><span class="info">单位：米(m)</span></div>
                </dd>
            </dl>


            <dl>
                <dt>头像：</dt>
                <dd>
                    <p style="width: 500px;padding: 5px 0;">
                        <input type="file" name="image"/></p>

                    <p style="width: 500px;padding: 5px 0;">
                        <span class="info">最佳格式为 200px * 200px，大小100k以内的jpg，png类型的图片</span></p>

                    <img src="${consultant.avatarUrl}" width="120" height="90"/>
                </dd>
            </dl>

            <dl>
                <dt>地址：</dt>
                <dd>
                    <input type="text" name="publicAddress" size="40" maxlength="40"
                           value="${consultant.publicAddress}"/>
                </dd>
            </dl>

            <dl>
                <dt>签名：</dt>
                <dd>
                    <textarea name="signature" cols="80" rows="3">${consultant.signature}</textarea>
                </dd>
            </dl>

            <dl>
                <dt>简介：</dt>
                <dd>
                    <textarea name="consultantDesc" cols="80" rows="15">${consultant.consultantDesc}</textarea>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <input type="hidden" name="navTab" value="${navTab}">
                            <input type="hidden" name="avatarUrl" value="${consultant.avatarUrl}">
                            <input type="hidden" name="consultantId" value="${consultant.consultantId}">
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