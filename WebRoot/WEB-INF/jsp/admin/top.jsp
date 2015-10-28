<%@ page pageEncoding="UTF-8" %>
<%@ include file="../include/common_header.inc.jsp"%>
<title><{$page_title}></title>
<link rel="stylesheet" href="<s:url value="/images/admincp.css" />" type="text/css" media="all" />
</head>
<body>
<div class="mainhd">
	<div class="logo"><a href="#" target="_blank"><img src="<s:url value="/images/admin_logo.gif" />" border="0" /></a></div>
	<div class="uinfo">
		<p>您好, <em><s:text name="%{loginUser.username}"/></em> [ <a href="<s:url value="/admin/admin_doLogout.do"></s:url>" target="_top" onclick="return window.confirm('确定退出么？');">退出</a> ]</p>
			</div>
</div>
</body>
</html>