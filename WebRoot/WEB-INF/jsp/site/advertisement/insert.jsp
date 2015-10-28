<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">添加广告信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/advertisement_insert.do" class="pageForm required-validate"
          onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="97">
            <c:if test="${adminUser.sysFlag}">
                <dl>
                    <dt>所属小区：</dt>
                    <dd>
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
                <dt>链接地址：</dt>
                <dd>
                    <input type="text" name="linkUrl" size="50" class="required" maxlength="200"/>
                </dd>
            </dl>

            <dl>
                <dt>广告图片：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="file" name="image" class="required"/></div>

                    <div class="divPanel1">
                        <span class="info">最佳格式为 684px * 280px，大小100k以内的jpg，png类型的图片</span></div>
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