<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">添加商户信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>admin/shop_insert.do" class="pageForm required-validate"
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
                        <input type="password" name="passwrod" size="30" class="required alphanumeric" minlength="6"
                               maxlength="20"/></div>

                    <div class="divPanel1">
                        <span class="info">密码 由6-20位字母、数字、下划线组成</span></div>
                </dd>
            </dl>

            <dl>
                <dt>商家名称：</dt>
                <dd>
                    <input type="text" name="shopName" size="30" class="required" maxlength="30"/>
                </dd>
            </dl>

            <dl>
                <dt>商家类型：</dt>
                <dd>
                    <select name="shopType" class="combox required">
                        <option value="">请选择商家类型</option>
                        <option value="1">美食餐饮</option>
                        <option value="2">商品超市</option>
                        <option value="3">美容健康</option>
                        <option value="4">休闲娱乐</option>
                        <option value="5">旅游酒店</option>
                        <option value="6">其他</option>
                    </select>
                </dd>
            </dl>

            <dl>
                <dt>联系电话：</dt>
                <dd>
                    <input type="text" name="phoneNumber" class="required phone" size="30"/>
                </dd>
            </dl>

            <dl>
                <dt>折扣：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="text" name="discount" class="required digits" size="10" min="1" max="100" maxlength="3"/></div>

                    <div class="divPanel1">
                        <span class="info">折扣（百分比），1- 100之间的整数, 如90代表9折(90%)，85代表85折(85%)</span></div>
                </dd>
            </dl>

            <dl>
                <dt>距离：</dt>
                <dd>
                    <div class="divPanel1">
                        <input type="text" name="shopDistance" class="required digits" size="10" maxlength="6"/></div>

                    <div class="divPanel1"><span class="info">单位：米(m)</span></div>
                </dd>
            </dl>

            <dl>
                <dt>LOGO：</dt>
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
                    <input type="text" name="shopAddress" class="required" size="40" maxlength="40"/>
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
                    <textarea name="shopDesc" cols="80" rows="15"></textarea>
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