<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>幸福墙 - 给爱情下个套</title>
    <meta name="viewport"
          content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <style type="text/css">
        body {width:100%; height:auto; margin:0px auto;background:#9d4949; font-size: 12px; font-family: '微软雅黑'; text-align: center;}
        p,span {padding: 0;margin: 0;}
        a {color:#000000; text-decoration: none;}
        .top-panel {height: auto; width: 100%; margin: 0px auto;}
        .list-panel {width: 100%; overflow: hidden; height: auto; padding: 10px 0; margin: 10px 0 0; background-color:#ffffff;}
        .list-panel  .left-panel {float: left; width: 50%; height: auto; overflow: hidden; text-align: center; line-height: 24px; margin-left: 5px; }
        .list-panel  .left-panel .header {border-bottom: 1px #f4a69c solid; border-top: 1px #f4a69c solid; height: 30px; line-height: 30px; font-size: 14px; font-weight: bold; }
        .list-panel  .left-panel .header img {vertical-align: middle; padding: 0 5px; }
        .list-panel  .left-panel .time {border-radius: 20px; background-color: #f4a69c;color: #ffffff; padding: 3px;}
        .list-panel  .right-panel {float: right; width: 45%;height: auto; padding-right: 10px;}
        .list-panel  .right-panel img {
            height: 128px;
        }
        .footer-panel {height: auto;  width: 100%; overflow: hidden; margin:20px 0 10px; text-align: center;}

        .search-panel { width: 100%; height: 100%; overflow: hidden; background-color: #f4a69c; }
        .search-panel .middle {width: 100%; height: auto; text-align: center; white-space: nowrap;}
        .search-panel  .middle img { padding-left: 0px; margin-left:-2px; vertical-align: middle;}
        .search-panel  .middle img.search {padding-left:3px; cursor:pointer;vertical-align: middle; margin-left:-3px;}
        .search-panel  .middle input {vertical-align: middle; width:142px; height: 20px; line-height: 20px; margin: 0px 0px 0 -3px; border-radius: 10px; padding: 2px 8px; border: #9d4949 1px solid;}
        .next-page {width: 100%; height: 30px; line-height: 30px; text-align: center; background-color: #f4a69c;}
        .next-page span {font-size: 13px; color: #9d4949; cursor: pointer;}
        .hide {
            display: none;
        }
    </style>
    <script type="text/javascript" src="<%=basePath%>dwz/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jphoto.core.js"></script>

</head>


<body>

<!-- TOP -->
<div class="top-panel">
    <img src="<%=basePath%>images/lifetime/header.jpg?r=3000" width="100%"/>
</div>
<!-- TOP  END-->


<div class="search-panel">
    <div class="middle">
        <img src="<%=basePath%>images/lifetime/tips.png" width="120" height="40"/>
        <input type="text" placeholder="请输入身份证号进行查询" name="card" id="cardNum" size="20" maxlength="18"/>
        <img class="search" src="<%=basePath%>images/lifetime/search.png" onclick="search('<%=basePath%>')" />
    </div>
</div>

<!-- LIST -->
<div id="reloadMess">
</div>
<div class="next-page hide" id="next-page">
    <span>点击显示更多</span>
</div>
<!-- LIST END -->

<!-- FOOTER -->
<div  class="footer-panel">
    <img src="<%=basePath%>images/lifetime/footer.jpg"/>
</div>
<!-- FOOTER END -->
</body>
</html>

<script type="text/javascript">
    $(function () {
        // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
//        var useragent = navigator.userAgent;
//        if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
//            // 这里警告框会阻塞当前页面继续加载
//            alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
//            // 以下代码是用javascript强行关闭当前页面
//            var opened = window.open('about:blank', '_self');
//            opened.opener = null;
//            opened.close();
//        }
        loadMess("<%=basePath%>",null,0,false);

    });

</script>

