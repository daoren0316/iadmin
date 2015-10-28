<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

</head>
<body>
<table width="100%">
	<tr style="vertical-align:baseline;">
	<td style="border: #cc0000 1px solid;BACKGROUND: #ffffcc;color:#cc0000;" height="30px"><ul style="margin-top:15px"></ul><s:if test="actionErrors.size>0 || actionMessages.size>0"><s:actionerror/><s:actionmessage/></s:if><s:else><s:text name="%{exception.message}"/></s:else></td>
	</tr>
	<tr>
	<td border="0" height="2px"></td>
	</tr>
	<tr> 
	<td style="border: #cc0000 1px solid;BACKGROUND: #ffffcc;" height="30px">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返回" onclick="javascript:window.history.go(-1);"/>    
	</td></tr>
</table>


<s:property value="%{exceptionStack}"/>
</body>
</html>


