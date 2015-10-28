<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Cache" content="no-cache">
    <title>管理员登陆</title>
    <script language="javascript">
    if(window.top.location.href != window.location.href){  //防止在frame中被调用
		window.top.location.href = window.location.href;
	}
	</script>
    <link href="<%=basePath%>dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=basePath%>dwz/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jswisstar.login.js"></script>
</head>
<body>
<div id="login">
    <div id="login_header">
        <h1 class="login_logo">
            <a href="javascript:;"><img src="<%=basePath%>dwz/themes/default/images/login_logo.gif"/></a>
        </h1>

        <div class="login_headerContent">
            <div class="navList">

            </div>
            <h2 class="login_title"><img src="<%=basePath%>dwz/themes/default/images/login_title.png"/></h2>
        </div>
    </div>
    <div id="login_content">
        <div class="loginForm">
            <form action="<%=basePath%>admin/login.do" method="post">
                <p align="center" class="errMsg">&nbsp;</p>
                <p>
                    <label>用户名：</label>
                    <input type="text" name="username" id="username" size="20" class="login_input" value="admin"/>
                </p>
                <p>
                    <label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                    <input type="password" name="password" id="password" size="20" class="login_input" value="123qwe"/>
                </p>
                <p>
                    <label>验证码：</label>
                    <input class="code" type="text" name="seccode" id="seccode" size="5" maxlength="4"/>
                    <span><img src="<%=basePath%>common/rand.do" alt="" width="75" height="24"/></span>
                </p>
                <div class="login_bar">
                    <input class="sub" type="button" value=" "/>
                </div>
            </form>
        </div>
        <div class="login_banner"><img src="<%=basePath%>dwz/themes/default/images/login_banner.png"/></div>
        <div class="login_main">
            <ul class="helpList">
                <li><a href="javascript:;">下载微管家</a></li>
                <li><a href="javascript:;">忘记密码怎么办？</a></li>
                <li><a href="javascript:;">为什么登录失败？</a></li>
            </ul>
            <div class="login_inner">
                <p>您可以使用 微管家APP ，随时随地管理个人数据</p>
                <p>微管家APP中智能监控，对讲监视，安防报警等功能可以为您提供更好的智能家居体验</p>
                <p>微管家APP开辟有团购模块，可以方便您日常的生活</p>
            </div>
        </div>
    </div>
    <div id="login_footer">
        Copyright &copy; 2014 www.swisstar.com Inc. All Rights Reserved.
    </div>
</div>
</body>
</html>