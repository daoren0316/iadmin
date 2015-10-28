<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">发布活动</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/activity_insert.do" class="pageForm required-validate"
          onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>活动标题：</dt>
                <dd>
                    <input type="text" name="activityTitle" size="30" class="required"/>
                </dd>
            </dl>

            <dl>
                <dt>标题图片：</dt>
                <dd>
                    <p style="width: 500px;padding: 5px 0;">
                        <input type="file" name="titleImage"/></p>

                    <p style="width: 500px;padding: 5px 0;">
                        <span class="info">最佳格式为 550px * 300px，大小100k以内的jpg，png类型的图片</span></p>
                </dd>
            </dl>

            <dl>
                <dt>活动图片：</dt>
                <dd>
                    <div id="addActivityAttachPanel" class="divPanel">
                        <span><input type="file" name="image"/></span></div>

                    <div class="divPanel">
                        <a href="javascript:;" onclick="_addFile('addActivity')" title="添加">
                            <img src="<%=basePath%>images/file_add.png"/></a>
                        <a href="javascript:;" onclick="_delFile('addActivity')" title="删除">
                            <img src="<%=basePath%>images/file_del.png"/></a></div>

                    <div class="divPanel">
                        <span class="info">最佳格式为 660px * 400px，大小200k以内的jpg，png类型的图片</span></div>
                </dd>
            </dl>

            <dl>
                <dt>活动描述：</dt>
                <dd>
                    <textarea name="activityDesc" cols="80" rows="15"></textarea>
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