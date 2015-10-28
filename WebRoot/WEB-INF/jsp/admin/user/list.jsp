<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/common_header.inc.jsp"%>
<title>登陆成功</title>
<link rel="stylesheet" href="<s:url value="/images/admincp.css" />" />
<body>
	<div id="append"></div>


	<div class="container">
		<table width="98%">
			<tr>
				<th>ID</th>
				<th>用户名</th>
				<th>手机号</th>
				<th>注册时间</th>
			</tr>
			<s:iterator value="userList">
				<tr>
					<td><s:property value="uid" /></td>
					<td><s:property value="nickname" /></td>
					<td><s:property value="phoneNumber" /></td>
					<td><s:property value="regTime" /></td>
				</tr>
			</s:iterator>
		</table>

		<s:include value="/WEB-INF/jsp/include/page.jsp">
			<s:param name="action">/admin/user_doList.do<s:property
					value="paramStr" escape="false" />
			</s:param>
		</s:include>
	</div>
</body>
</html>