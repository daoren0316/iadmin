<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<h2 class="contentTitle">修改登陆密码</h2>

<div class="pageContent">
    <form method="post" action="admin/shop_changePwd.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>用户名：</dt>
                <dd>
                    <input type="text" name="userName" class="required" size="25"
                            value="${adminUser.username}" disabled="disabled"/>
                </dd>
            </dl>
            <dl>
                <dt>旧密码：</dt>
                <dd>
                    <input type="password" name="oldPassword" class="required alphanumeric" size="25"
                           minlength="6" maxlength="20" alt="字母、数字、下划线 6-20位" remote="<%=basePath%>admin/shop_checkPwd.do"/>
                </dd>
            </dl>

            <dl>
                <dt>新密码：</dt>
                <dd>
                    <input type="password" id="newPasswrod" class="required alphanumeric" size="25"
                           minlength="6" maxlength="20" alt="字母、数字、下划线 6-20位"/>
                </dd>
            </dl>

            <dl>
                <dt>确认新密码：</dt>
                <dd>
                    <input type="password" name="passwrod" class="required alphanumeric" equalto="#newPasswrod"
                           size="25"/>
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