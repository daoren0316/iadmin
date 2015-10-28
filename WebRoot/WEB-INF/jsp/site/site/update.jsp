<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">修改小站信息</h2>

<div class="pageContent">
    <form method="post" id="pageForm" action="<%=basePath%>site/site_update.do" class="pageForm required-validate"
          onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>小站名称：</dt>
                <dd>
                    <input type="text" name="siteName" size="30" class="required" value="${site.siteName}"
                           disabled="disabled"/>
                </dd>
            </dl>

            <dl>
                <dt>联系电话：</dt>
                <dd>
                    <input type="text" name="phoneNumber" class="required phone" size="30" value="${phoneNumber}"/>
                </dd>
            </dl>

            <dl>
                <dt>LOGO：</dt>
                <dd>
                    <p style="width: 500px;padding: 5px 0;">
                        <input type="file" name="image"/></p>

                    <p style="width: 500px;padding: 5px 0;">
                        <span class="info">最佳格式为 200px * 200px，大小100k以内的jpg，png类型的图片</span></p>
                    <img src="${site.logoUrl}" width="120" height="90"/>
                </dd>
            </dl>

            <dl>
                <dt>地址：</dt>
                <dd>
                    <input type="text" name="siteAddress" class="required" size="40" value="${site.siteAddress}"/>
                </dd>
            </dl>

            <dl>
                <dt>签名：</dt>
                <dd>
                    <textarea name="signature" cols="80" rows="3">${site.signature}</textarea>
                </dd>
            </dl>

            <dl>
                <dt>简介：</dt>
                <dd>
                    <textarea name="siteDesc" cols="80" rows="15">${site.siteDesc}</textarea>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <input type="hidden" name="logoUrl" value="${site.logoUrl}"/>
                            <input type="hidden" name="siteId" value="${site.siteId}"/>
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