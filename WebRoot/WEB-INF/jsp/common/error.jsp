<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>error</title>
<link rel="stylesheet" href="<s:url value="/images/admincp.css"/>" type="text/css" media="all" />
</head>
<body>
<s:text name="%{exception.message}"/>
<br/>
<s:property value="%{exceptionStack}"/>
</body>
</html>



