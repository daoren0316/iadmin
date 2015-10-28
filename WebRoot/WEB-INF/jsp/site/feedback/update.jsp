<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<c:if test="${feedback.category eq 1}">
    <c:set var="name" value="保修"/>
    <c:set var="typeName" value="${feedback.type eq 1 ? '公司保修' : '个人保修'}"/>
</c:if>
<c:if test="${feedback.category eq 2}">
    <c:set var="name" value="咨询"/>
    <c:set var="typeName" value="信息咨询"/>
</c:if>
<c:if test="${feedback.category eq 3}">
    <c:set var="name" value="投诉"/>
    <c:set var="typeName" value="${feedback.type eq 1 ? '投诉商家' : '投诉物业'}"/>
</c:if>

<h2 class="contentTitle">${name}详细信息</h2>

<div class="pageContent">

    <form method="post" action="<%=basePath%>site/feedback_update.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)" id="rite_query">
        <div class="pageFormContent nowrap" layoutH="97">
            <fieldset>
                <legend>&nbsp;&nbsp;${name}信息&nbsp;&nbsp;</legend>
                <dl>
                    <dt>${name}类型：</dt>
                    <dd>
                        <input type="text" value="${typeName}" readonly="readonly"
                               size="30"/>

                        <c:if test="${!empty feedback.picList}">
                            <a class="button" style="padding:0px 5px" mask="true" target="dialog" rel="showImg"
                               width="485" height="398"
                               href="<%=basePath%>site/feedback_showImg.do?feedbackId=${feedback.feedbackId}"><span>查看图片</span></a>
                        </c:if>
                    </dd>
                </dl>

                <dl>
                    <dt>提交时间：</dt>
                    <dd>
                        <input type="text"
                               value="<fmt:formatDate value="${feedback.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                               readonly="readonly" size="30"/>

                    </dd>
                </dl>

                <dl>
                    <dt>业主地址：</dt>
                    <dd>
                        <input type="text" value="${feedback.publicAddress}" readonly="readonly"
                               size="30"/>
                    </dd>
                </dl>
                <dl>
                    <dt>联系电话：</dt>
                    <dd>
                        <input type="text" value="${feedback.phoneNumber}" readonly="readonly"
                               size="30"/>
                    </dd>
                </dl>
                <dl>
                    <dt>问题描述：</dt>
                    <dd>
                        <textarea cols="80" rows="5" readonly="readonly">${feedback.content}</textarea>
                    </dd>
                </dl>
            </fieldset>

            <fieldset>
                <legend>&nbsp;&nbsp;回访信息&nbsp;&nbsp;</legend>
                <c:if test="${feedback.flag eq 0}">
                    <dl>
                        <dt>回访记录：</dt>
                        <dd>
                            <textarea name="note" cols="80" rows="6" maxlength="200"></textarea>
                        </dd>
                    </dl>
                </c:if>
                <c:if test="${feedback.flag eq 1}">
                    <dl>
                        <dt>回访时间：</dt>
                        <dd>
                            <input type="text" name="updateTime"
                                   value="<fmt:formatDate value="${feedback.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                   readonly="readonly" size="30"/>
                        </dd>
                    </dl>
                    <dl>
                        <dt>操作员名称：</dt>
                        <dd>
                            <input type="text" name="operatorName" value="${feedback.operatorName}"
                                   readonly="readonly" size="30"/>
                        </dd>
                    </dl>
                    <dl>
                        <dt>回访记录：</dt>
                        <dd>
                            <textarea name="note" cols="80" rows="6" readonly="readonly">${feedback.note}</textarea>
                        </dd>
                    </dl>
                </c:if>
            </fieldset>
        </div>
        <div class="formBar">
            <ul>
                <c:if test="${feedback.flag eq 0}">
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <input type="hidden" name="feedbackId" value="${feedback.feedbackId}"/>
                            <button type="submit">提交</button>
                        </div>
                    </div>
                </c:if>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">关闭</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>
