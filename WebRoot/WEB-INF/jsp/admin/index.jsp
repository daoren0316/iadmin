<%@ page pageEncoding="UTF-8" %>
<%@ include file="../include/common_header.inc.jsp"%>
<title>iButler后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link rel="stylesheet" href="<s:url  value="/images/admincp.css"/>" type="text/css" media="all" />
</head>
<body scroll="no">
<table cellpadding="0" cellspacing="0" width="100%" height="100%">
	<tr>
		<td colspan="2" height="69"><iframe src="admin_doTop.do" name="header" width="100%" height="69" scrolling="no" frameborder="0"></iframe></td>
	</tr>
	<tr height="690">
		<td valign="top" width="160" align="center"><iframe src="admin_doLeft.do" name="menu" target="main" width="100%" height="100%" scrolling="no" frameborder="0"></iframe></td>
		<td valign="top" align="left"><iframe src="admin_doMain.do" name="main" width="100%" height="100%" frameborder="0" scrolling="yes" style="overflow:visible;"></iframe></td>
	</tr>
</table>
</body>
</html>