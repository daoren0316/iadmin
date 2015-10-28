<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">添加顾问信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/consultant_insert.do" class="pageForm required-validate"
          onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>登录名：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="text" name="username" size="30" class="required alphanumeric" minlength="3"
                               maxlength="12" remote="<%=basePath%>admin/user_checkUser.do"/></div>

                    <div class="divPanel1">
                        <span class="info">登录名 由3-12位字母、数字、下划线组成</span></div>
                </dd>
            </dl>

            <dl>
                <dt>登录密码：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="password" name="password" size="30" class="required alphanumeric" minlength="6"
                               maxlength="20"/></div>

                    <div class="divPanel1">
                        <span class="info">密码 由6-20位字母、数字、下划线组成</span></div>
                </dd>
            </dl>

            <dl>
                <dt>姓名：</dt>
                <dd>
                    <input type="text" name="consultantName" size="30" class="required" maxlength="5"/>
                </dd>
            </dl>

            <dl>
                <dt>性别：</dt>
                <dd>
                    <input type="radio" name="gender" value="1" checked="checked"/>男
                    <input type="radio" name="gender" value="2"/>女
                </dd>
            </dl>

            <dl>
                <dt>类型：</dt>
                <dd>
                    <select name="consultantType" class="combox required">
                        <option value="">请选择类型</option>
                        <option value="1">小区律师</option>
                        <option value="2">社区医生</option>
                        <option value="3">旅游向导</option>
                        <option value="4">理财专家</option>
                        <option value="5">汽车顾问</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>距离：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="text" name="distance" class="required digits" size="10" maxlength="10"/></div>

                    <div class="divPanel1"><span class="info">单位：米(m)</span></div>
                </dd>
            </dl>

            <dl>
                <dt>职务：</dt>
                <dd>
                    <input type="text" name="duty" size="30" maxlength="15"/>
                </dd>
            </dl>

            <dl>
                <dt>联系电话：</dt>
                <dd>
                    <input type="text" name="phoneNumber" class="required phone" size="30"/>
                </dd>
            </dl>

            <dl>
                <dt>头像：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="file" name="image" class="required"/></div>

                    <div class="divPanel1">
                        <span class="info">最佳格式为 200px * 200px，大小100k以内的jpg，png类型的图片</span></div>
                </dd>
            </dl>

            <dl>
                <dt>地址：</dt>
                <dd>
                    <input type="text" name="publicAddress" size="40" maxlength="40"/>
                </dd>
            </dl>

            <dl>
                <dt>签名：</dt>
                <dd>
                    <textarea name="signature" cols="80" rows="3"></textarea>
                </dd>
            </dl>

            <dl>
                <dt>简介：</dt>
                <dd>
                    <textarea name="consultantDesc" cols="80" rows="15"></textarea>
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