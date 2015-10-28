<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div layoutH="2" style="float:left; display:block; overflow:auto; width:230px; border:solid 1px #CCC; line-height:21px; background:#fff">
    ${tree}
</div>

<div id="communityBox" class="unitBox" style="margin-left:233px;">
    <!--#include virtual="list1.html" -->
</div>