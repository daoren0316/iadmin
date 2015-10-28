<%@ page pageEncoding="UTF-8" %>
<%@ include file="../include/common_header.inc.jsp"%>
<title>管理员登陆</title>
<link rel="stylesheet" href="<s:url value="/images/admincp.css"/>" type="text/css" media="all" />
</head>
<body><div id="append"></div>


<div class="container">
	

	<s:form id="loginform" name="loginform" action="/admin/login.do" target="_top">
		<table class="mainbox">
			<tr>
				<td class="loginbox">
					
				</td>
				<td class="login">
					<p><div id="errormsg"><span color="red">${msg}</span></div></p>					
					<p id="usernamediv">用户名:<input type="text" name="adminUser.username" class="txt" tabindex="1" id="admin_username" value="" /></p>
					<p>密　码:<input type="password" name="adminUser.password" class="txt" tabindex="2" id="password" value="" /></p>
					<p>验证码:<input type="text" name="seccode" class="txt" tabindex="2" id="seccode" value="" style="margin-right:5px;width:85px;" /><img   src="<s:url  value="/common/rand.do"/>" /></p>
					<p class="loginbtn"><input type="submit" name="submit" value="登 录" class="btn" tabindex="3" /></p>
				</td>
			</tr>
		</table>
	</s:form>
</div>
</body>
</html>