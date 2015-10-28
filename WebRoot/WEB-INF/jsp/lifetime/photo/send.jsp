<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我被套的 - 给爱情下个套</title>
    <meta name="viewport"content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <style type="text/css">
        .top{height:auto; width:100%;  margin:0px auto; }
        .footer{height:auto; width:100%; overflow:hidden; margin:0px auto; text-align:center }
        .center{ width:94%; height:auto;margin:0px auto;  }
        .btn{width:100%; height:auto;margin:0px auto;}
        .btn ul{width:100%; height:auto;margin:0px; padding:0px;}
        .btn ul li{ list-style-type:none; width:30%; float:left; margin:5px;}
        .btn ul li img{ width:100%;}
        .list{width:100%; height:auto;margin:0px auto; padding:0px;}
        .list ul{width:100%; height:auto;margin:0px; padding:0px;}
        .list ul li{ width:100%; float:left;list-style-type:none; background:#FFF; margin-bottom:15px;}
        .list ul li p{ float:right; width:47%; overflow:hidden; margin:3px;}
        .list ul li p img{ height:145px; margin-top:10px;}
        .list ul li dl{float:left; width:47%; overflow:hidden; text-align:center;padding:5px;}
        .list ul li dl dt{ height:30px; line-height:30px; font-family:"微软雅黑"; margin:5px 0 5px 5px; border-bottom:1px #f4a69c solid;border-top:1px #f4a69c solid;}
        .list ul li dl dd{ line-height:20px; padding:0px; margin:0px;}
        .shijian{ font-size:10px;font-family:"微软雅黑";}
        .daojishi{ line-height:30px; height:25px;font-size:12px; padding:5px; background:#f4a69c;font-family:"微软雅黑"; color:#FFF;border-radius:20px;}
        .content{ font-size:12px; line-height:25px;font-family:"微软雅黑";}
        .none{ display:none}
    </style>
    <script type="text/javascript" src="<%=basePath%>dwz/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jphoto.core.js"></script>
    <script type="text/javascript">
        $(function(){
            var time1 = "2018/10/04 00:00:00";
            var time2 = "2016/10/04 00:00:00";
            count_down(1,time1);
            count_down(2,time2);
        });
    </script>

</head>
<body style="width:100%; height:auto; margin:0px auto;background:#9d4949">
<div class="top"><img src="<%=basePath%>images/lifetime/top.jpg" width="100%"/></div>
<div class="center">
    <div class="btn">
        <ul id="zxgsTab">
            <li class="curr">
                <a href="<%=basePath%>lifetime/main.do?k=lifttime"><img src="<%=basePath%>images/lifetime/btn1.png"/></a>
            </li>
            <li>
                <a href="<%=basePath%>lifetime/send.do?k=lifttime"><img src="<%=basePath%>images/lifetime/btn2.png"/></a>
            </li>
            <li>
                <a href="<%=basePath%>lifetime/my.do?k=lifttime"><img src="<%=basePath%>images/lifetime/btn3.png"/></a>
            </li>
        </ul>
    </div>
    <div style="clear:both"></div>
    <div style="width:100%; margin:0px auto; border-bottom:2px #FFF solid;"/>
</div>
<div style="clear:both"></div>
<div class="list" id="zxgsTab_Content0">
    <ul>
        <li>
            <dl>
                <dt>小E妹<img src="<%=basePath%>images/lifetime/xin.gif" width="26" height="22" align="absmiddle"/>小A君</dt>
                <dd class="shijian">生效时间：2014/12/25</dd>
                <dd class="daojishi">
                    <span id="timer_day1">&nbsp;</span>天
                    <span id="timer_hour1">&nbsp;</span>小时
                    <span id="timer_minute1">&nbsp;</span>分钟
                    <span id="timer_second1">&nbsp;</span>秒
                </dd>
                <dd class="content">这是一段感人肺腑的留言说出了他的心声更表达了他对她的爱</dd>
            </dl>
            <p><img src="<%=basePath%>images/lifetime/11.jpg"/></p>
        </li>
        <li>
            <dl>
                <dt>小F妹<img src="<%=basePath%>images/lifetime/xin.gif" width="26" height="22" align="absmiddle"/>小A君</dt>
                <dd class="shijian">生效时间：2014/08/25</dd>
                <dd class="daojishi">
                    <span id="timer_day2">&nbsp;</span>天
                    <span id="timer_hour2">&nbsp;</span>小时
                    <span id="timer_minute2">&nbsp;</span>分钟
                    <span id="timer_second2">&nbsp;</span>秒
                </dd>
                <dd class="content">这是一段感人肺腑的留言说出了他的心声更表达了他对她的爱</dd>
            </dl>
            <p><img src="<%=basePath%>images/lifetime/11.jpg"/></p>
        </li>
    </ul>
</div>

<div style="clear:both"></div>
</div>
<div style="clear:both"></div>
<div class="footer"><img src="<%=basePath%>images/lifetime/footer.jpg"/></div>

</body>
</html>

