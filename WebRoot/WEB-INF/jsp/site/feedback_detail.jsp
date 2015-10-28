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
<h3><span><a href="javascript:history.back(-1)">返回</a></span></h3>
<br/>


<br/><br/>
	<table width="90%" align="left" border="1">
		<tr >

			<td height="32" width="50"><b>投诉人</b></td>
			
			<td >
			昵称：<s:property value="%{feedbackUser.nickname}"/><br/>
			手机：<s:property value="%{feedbackUser.phoneNumber}"/><br/>
			地址：<s:property value="%{feedbackUser.publicAddress}"/><br/>
			</td>
		</tr>
		<tr >

			<td height="32" width="50"><b>投诉时间</b></td>
			
			<td >
			<s:date name="postTime" format="yyyy-MM-dd HH:mm:ss" />
			</td>
		</tr>
		<tr >

			<td height="32" width="50"><b>内容</b></td>
			
			<td >
			<s:property value="%{feedback.content}"/>
			</td>
		</tr>
		
		<tr>

			<td height="32" ><b>图片</b></td>
			
			<td >
			<s:iterator value="picList" id="pic">
			<img src="<s:property value="fileUrl" />" />  <br/>
			</s:iterator>
			</td>
			
		</tr>
  </table>

   
</div>

<br/>
 

</body>
</html>