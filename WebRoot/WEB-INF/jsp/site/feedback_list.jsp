<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/common_header.inc.jsp"%>
<title>订单列表</title>
<link rel="stylesheet" href="<s:url value="/images/admincp.css" />" />
<script language="javascript">
	function checkForm(s) {
		if (window.confirm("确定要将选中订单设置为已完成 吗？")) {
			return true;
		}
		return false;
	}
</script>
</head>
<body>
	<div class="container">
		<h3>

			<br /> <span>投诉报修</span> <br />
		</h3>



		<table width="90%" align="left" border="1">
			<s:form name="" method="post"
				action="/site/feedback_doChangeStatus.do"
				onsubmit="return checkForm(this)">
				<tr height="25">
					<td width="50"></td>
					<td>内容</td>
					<td width="160"><b>时间</b></td>


				</tr>
				<s:iterator value="feedbackList" id="feedback">
					<tr height="32">
						<td><s:if test='#feedback.picList != "" '>
								<span color="red">有图</span>
							</s:if></td>
						<td><a
							href="<s:url value="/site/feedback_detail.do" />?feedbackId=<s:property value="feedbackId" />"><s:property
									value="content" /></a></td>
						<td><s:date name="postTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</s:iterator>

				<tr>
					<td height="25"><s:if test='tradeStatus == 0 '>
							<input type="submit" name="submit" value="设置为已完成" />
						</s:if></td>
					<td align="center" colspan="3"></td>
				</tr>
			</s:form>
		</table>
		<br />

		<table width="90%" align="left" bfeedback="0">
			<tr height="39">
				<td><s:include value="/WEB-INF/jsp/include/page.jsp">
						<s:param name="action">
							<s:url value="/site/feedback_doList.do" />?tradeStatus=<s:property
								value="tradeStatus" />
							<s:property value="paramStr" escape="false" />
						</s:param>
					</s:include></td>
			</tr>
		</table>


	</div>

	<br />
</body>
</html>