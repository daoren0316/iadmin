<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>error</title>
<link rel="stylesheet" href="<s:url value="/images/admincp.css"/>" type="text/css" media="all" />
<script language="javascript">
var no_frame = '<s:text name="%{noFrame}"/>';
if('true' == no_frame){
	if(window.top.location.href != window.location.href){
		//window.top.location.href = window.location.href;
	}
	
}
</script>
</head>
<body>

<div class="container" >
	
	<div align="center" style="margin-top:20px;">
		<s:if test='result ==0 '>
			<img src="<s:url value="/images/ok.gif"/>" width="28" height="28" />
		</s:if>
		<s:text name="%{msg}"/>
		<br>
		<a href="<s:url value="%{toUrl}" />">返回上一页</a>
	</div>
</div>
</body>
</html>



