<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<h2 class="contentTitle">${activity.activityTitle}</h2>
<link href="<%=basePath%>css/lunbo.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>css/look.css" rel="stylesheet" type="text/css" media="screen"/>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/activity_look.do" class="pageForm required-validate"
          onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="97">

            <div class="textAreaPanel">
                <pre>${activity.activityDesc}</pre>
            </div>

            <div class="wrapper">
                <c:if test="${!empty attachList}">
                    <div id="focus">
                        <ul>
                            <c:forEach items="${attachList}" var="al">
                                <li><img src="${al.fileUrl}" width="500" height="280"/></li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="formBar">
            <ul>
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

<script src="<%=basePath%>js/jswisstar.lunbo.js" type="text/javascript"></script>
