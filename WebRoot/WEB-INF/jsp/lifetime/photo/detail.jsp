<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>保护宣言 - 给爱情下个套</title>
		<meta name="viewport"
			content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta name="format-detection" content="telephone=no" />
		<script>
		function fontAuto(){
			var winW=document.documentElement.clientWidth;
			if(winW>=720)
			{
				document.documentElement.style.fontSize="625%";
			}
			else
			{
				document.documentElement.style.fontSize=(winW/720*625)+"%";
			}
		}
		fontAuto();
		window.onresize=fontAuto;
	</script>
		<style type="text/css">
html {
	font-size: 625%;
}  /*#9d4949*/
body {
	max-width: 720px;
	min-width: 320px;
	height: auto;
	margin: 0px auto;
	background: #fff;
	font-family: '微软雅黑';
	font-size: 16px;
}

p,li,ul,h3 {
	padding: 0;
	margin: 0;
}

li {
	list-style: none;
}

a {
	color: #000000;
	text-decoration: none;
}

.top-panel {
	height: auto;
	width: 100%;
	margin: 0px auto;
}

.list-panel {
	width: 100%;
	overflow: hidden;
	height: auto;
	padding: 15px 0;
	margin: 20px 0 0;
	background-color: #ffffff;
}

.list-panel  .left-panel {
	width: 95%;
	height: auto;
	overflow: hidden;
	text-align: center;
	line-height: 24px;
	margin: 0 10px;
}

.list-panel  .left-panel .header {
	border-bottom: 1px #f4a69c solid;
	border-top: 1px #f4a69c solid;
	height: 30px;
	line-height: 30px;
	font-size: 14px;
	font-weight: bold;
}

.list-panel  .left-panel .header img {
	vertical-align: middle;
	padding: 0 5px;
}

.list-panel  .left-panel .time {
	border-radius: 20px;
	background-color: #f4a69c;
	color: #ffffff;
	padding: 3px;
}

.list-panel  .right-panel {
	width: 100%;
	height: auto;
	padding-right: 10px;
	text-align: center;
}

.list-panel  .right-panel img {
	height: 226px;
	margin-top: 5px;
	-webkit-box-shadow: 0px 1px 2px 0px #4a4a4a;
	-moz-box-shadow: 0px 1px 2px 0px #4a4a4a;
	-o-box-shadow: 0px 1px 2px 0px #4a4a4a;
	-ms-box-shadow: 0px 1px 2px 0px #4a4a4a;
	box-shadow: 0px 1px 2px 0px #4a4a4a;
}

.footer-panel {
	height: auto;
	width: 100%;
	overflow: hidden;
	margin: 20px 0 0px;
	text-align: center;
}

.page_w {
	width: 100%;
	min-width: 320px;
	background: #fff;
}

.list_time {
	min-width: 320px;
	height: 2.61rem;
	margin: 0 auto;
	background: url(<%=basePath%>images/add/time_bg.png) no-repeat 0 0.5rem #fff;
	background-size: 100% auto;
	padding-top:0.5rem;
}

.list_time_top {
	width: 100%;
	overflow: hidden;
}

.list_bx_name {
	float: left;
	width: 2.85rem;
	line-height: 1.12rem;
	text-align: right;
	color: #a92f46;
	font-size: 0.32rem;
}

.list_bx_day {
	float: left;
	width: 1.2rem;
	font-size: 0.32rem;
	height: 0.48rem;
	text-align: center;
	line-height: 0.48rem;
	margin: 0.47rem 0 0 0.14rem;
	color: #fff;
	font-weight: bold;
}

.list_bxTo_name {
	float: left;
	width: 2.85rem;
	line-height: 1.12rem;
	color: #a92f46;
	font-size: 0.32rem;
	margin-left: 0.15rem;
}

.list_bx_hms {
	text-align: center;
	line-height: 0.6rem;
	padding-top: 0.37rem;
	color: #fff;
	font-weight: bold;
	font-size: 0.6rem;
}

.list_bx_start {
	text-align: center;
	line-height: 0.36rem;
	color: #fff;
	font-size: 0.32rem;
}

.list_card_tit {
	width: 100%;
	height: 2rem;
	background: url(<%=basePath%>images/add/tit.png) no-repeat;
	background-size: 100% auto;
}

.list_card_info {
	width: 5.72rem;
	margin: 0 auto;
	color: #a1464b;
	line-height: 0.54rem;
}

.list_card_info p {
	text-indent: 2em;
	background: #fff;
	font-size: 0.32rem;
}

.list_card_ta {
	height: 0.87rem;
	background: url(<%=basePath%>images/add/ta_bg.png) no-repeat 0 0.18rem #fff;
	background-size: 100% auto;
	padding-top: 0.18rem;
	display:block;
}

.list_content {
	width: 6.22rem;
	margin: 0 auto;
	color: #7b7b7b;
	line-height: 0.36rem;
	font-size: 0.32rem;
	background: #fff;
}

.list_content p {
	text-indent: 2em;
	line-height: 0.38rem;
	font-size: 0.28rem;
	padding: 0.15rem 0.15rem;
}

.list_content img {
	padding: 4px;
	border: 1px solid #ccc;
	margin: 0 auto;
	display: block;
	margin-top: 0.1rem;
	max-width: 100%;
}

.list_talk {
	border-top: #c64348 3px solid;
	background: #f5f5f5 url(<%=basePath%>images/add/zan.gif) 0.51rem top
		no-repeat;
	background-size: auto 100%;
	border-bottom: #d4d4d4 2px solid;
	background: auto 100%;
	padding-left: 1.14rem;
	height: 1.15rem;
}

.list_talk_count {
	float: left;
	line-height: 1.12rem;
	font-size: 0.46rem;
	font-weight: bold;
	padding-right: 0.15rem;
	color: #c64144;
}

.list_talk_zan,.list_talk_speak {
	float: left;
	padding: 0 0.12rem;
	height: 0.46rem;
	line-height: 0.46rem;
	text-align: center;
	font-size: 0.32rem;
	color: #c83f47;
	border: #c83f47 2px solid;
	border-radius: 0.1rem;
	margin-top: 0.29rem;
}

.list_talk_speak {
	float: right;
	margin-right: 0.32rem;
}

.list_list_l li {
	height: auto;
	width: 100%;
	overflow: hidden;
}

.list_list_img {
	float: left;
	width: 0.84rem;
	height: 0.84rem;
	margin: 0.2rem 0 0 0.42rem;
}

.list_list_img img {
	width: 100%;
	height: 100%;
	border-radius: 50%;
}

.list_list_name {
	float: left;
	width: 1.8rem;
	line-height: 1.15rem;
	padding-left: 0.2rem;
	color: #8e8f91;
	font-size: 0.36rem;
}

.list_list_l p {
	float: left;
	width: 5.2rem;
	font-size: 0.36rem;
	padding-top: 0.48rem;
	line-height: 0.38rem;
	color: #8e8f91;
	padding-bottom: 0.15rem;
	padding-left: 0.2rem;
}

.list_toTalk {
	height: 1.13rem;
	background: #f8f6f9;
	padding-top: 0.16rem;
}

.list_toTalk_div {
	width: 6.60rem;
	margin: 0 auto;
	padding: 0.02rem;
	height: 0.72rem;
	background: #bf4145;
	border-radius: 0.1rem;
}

.list_toTalk_text {
	float: left;
	width: 5.28rem;
	height: 0.71rem;
	background: #fff;
	border-radius: 0.1rem;
	font-size:0.36rem;
	padding:0 0.05rem;
	
}

.list_toTalk_text,.list_toTalk_btn {
	border: 0 none;
	float: left;
}

.list_toTalk_btn {
	float: left;
	height: 0.72rem;
	width: 1.14rem;
	text-align: center;
	line-height: 0.72rem;
	font-size: 0.3rem;
	color: #fff;
	cursor: pointer;
	background: none;
}
input{margin:0;padding:0;}
</style>
		<script type="text/javascript"
			src="<%=basePath%>dwz/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jphoto.core.js"></script>
		<script type="text/javascript">
    var nickName = "";
    var id = "${id}";
    var openid = "";
    var headimgurl = "<%=basePath%>images/add/1.jpg";
    function praise(){
     	$("#praise").remove();
     	var praiseCount = Number($("#praiseCount").html()) + 1;
     	$("#praiseCount").html(praiseCount);
     	var url = "<%=basePath%>" + "lifetime/praise.do";
	    $.post(url, {"id": id}, function (data) {
	    
	    }, "json");
    }
    
    function commentFocus()
    {
    	$('#commentText').focus();
    }
    
    function addComment()
    {
    	var content = $('#commentText').val();
    	if(content == "")
    	{
    		alert("评论内容不能为空");
    	}
    	else
    	{
    		$('#commentText').val("");
    		var comment = "<li><span class=\"list_list_img\"><img src=\"";
    		comment += headimgurl;
    		comment +=  "\" /></span>";
    		comment += "<span class=\"list_list_name\">";
    		comment += nickName;
    		comment += "</span><p>";
    		comment += content;
    		comment += "</p></li>";
			$("#comment_list").prepend(comment);
	    	var url = "<%=basePath%>" + "lifetime/addcomment.do";
		    $.post(url, {"lid": id, "content" : content}, function (data) {
		    	
		    }, "json");
    	}
    }
    </script>

	</head>
	<body
		style="width: 100%; height: auto; margin: 0px auto; background: #fff;">
		<!--  
		<div class="top-panel">
			<img src="<%=basePath%>images/lifetime/header.jpg?r=3000"
				width="100%" />
		</div>
		-->
		<!--时间-->
		<div class="list_time">
			<ul class="list_time_top">
				<li class="list_bx_name">
					<c:set
						value="${empty kdtShowDTO.buyer_nick ? '他' : kdtShowDTO.buyer_nick}"
						var="nick" />
					<c:if test="${fn:length(nick)>3}">
                    ${fn:substring(nick,0,3)}...
                </c:if>
					<c:if test="${fn:length(nick)<=3}">
                    ${nick}
                </c:if>
				</li>
				<li class="list_bx_day" id="timer_day1">
					0
				</li>
				<li class="list_bxTo_name">
					<c:set
						value="${empty kdtShowDTO.bbrName ? '她' : kdtShowDTO.bbrName }"
						var="bbrName" />
					<c:if test="${fn:length(bbrName)>1}">
                    ${fn:substring(bbrName,0,1)} **
                </c:if>
					<c:if test="${fn:length(bbrName)<=1}">
                    ${bbrName}
                </c:if>
				</li>
			</ul>
			<ul class="list_time_bottom">
				<li class="list_bx_hms">
					<span id="timer_hour1">0</span>:
					<span id="timer_minute1">0</span>:
					<span id="timer_second1">0</span>
				</li>
				<li class="list_bx_start">
					守护起始时间：
					<fmt:formatDate value="${kdtShowDTO.pay_time}" pattern="yyyy-MM-dd" />
				</li>
			</ul>
		</div>
		<!--时间end-->

		<!--爱情保卫证-->
		<div class="page_w">
			<div class="list_card">
				<h3 class="list_card_tit"></h3>
				<div class="list_card_info">
					<strong class="list_card_name">
						<c:set
								value="${empty kdtShowDTO.bbrName ? '女士' : kdtShowDTO.bbrName }"
								var="bbrName" /> <c:if test="${fn:length(bbrName)>1}">
                    ${fn:substring(bbrName,0,1)} **
                </c:if> <c:if test="${fn:length(bbrName)<=1}">
                    ${bbrName}
                </c:if>
					 </strong>
					<p class="list_card_toName">
						兹证明您是ALIFETIME平台上
						<strong><c:set
							value="${empty kdtShowDTO.buyer_nick ? '先生' : kdtShowDTO.buyer_nick}"
							var="nick" /> <c:if test="${fn:length(nick)>3}">
                    ${fn:substring(nick,0,3)}...
                </c:if> <c:if test="${fn:length(nick)<=3}">
                    ${nick}
                </c:if>   </strong> 这辈子唯一要保护的人。您的健康与平安将得到其精心定制的《爱情保护套餐》的悉心呵护。
					</p>
					<p class="list_card_toName">
						无论何时，其在ALIFETIME平台上所定制的所有服务，您将是唯一受益人。
					</p>
					<p class="list_card_toName">
						特此证明
					</p>
					<p class="list_card_toName" style="text-align: right;">
						Alifetime.一辈子
						<br />
						<fmt:formatDate value="${kdtShowDTO.pay_time}"
							pattern="yyyy-MM-dd" />
					</p>
				</div>
			</div>
		</div>
		<i class="list_card_ta"></i>
		<!--爱情保卫证 end-->

		<!--内容-->
		<div class="page_w">
			<div class="list_content">
				<p>
					${kdtShowDTO.bbrMessage}
					<c:if test="${empty kdtShowDTO.bbrImg}">
						<img src="<%=basePath%>images/lifetime/11.jpg" />
					</c:if>
					<c:if test="${!empty kdtShowDTO.bbrImg}">
						<img src="${kdtShowDTO.bbrImg}" />
					</c:if>
				</p>
			</div>
		</div>
		<!--内容end-->

		<!-- 评论 -->
		<div class="page_w">
			<div class="list_talk">
				<span class="list_talk_count" id="praiseCount">${count }</span>
				<a class="list_talk_zan" id="praise" href="javascript:praise()">点个赞</a>
				<a class="list_talk_speak" href="javascript:commentFocus()">想对TA说</a>
			</div>
			<ul class="list_list_l" id="comment_list">
				<c:forEach items="${commentList}" var="comment">
					<li>
						<span class="list_list_img"><img
								src="<%=basePath%>images/add/1.jpg" />
						</span>
						<span class="list_list_name" style="display:none">${comment.cdate }</span>
						<p>
							${comment.content }
						</p>
					</li>
				</c:forEach>
			</ul>
			<div class="list_toTalk">
				<div class="list_toTalk_div">
					<input class="list_toTalk_text" id="commentText" type="text"
						value="" />
					<input class="list_toTalk_btn" type="button" onclick="addComment()"
						value="留言" />
				</div>
			</div>
		</div>
		<!-- 评论 end-->


		<!--<div class="list-panel">-->
		<!--    <div class="left-panel">-->
		<!--        <p class="header">-->
		<!--            <c:set value="${empty kdtShowDTO.buyer_nick ? '他' : kdtShowDTO.buyer_nick}" var="nick"/>-->
		<!--            <c:if test="${fn:length(nick)>3}">-->
		<!--                ${fn:substring(nick,0,3)}...-->
		<!--            </c:if>-->
		<!--            <c:if test="${fn:length(nick)<=3}">-->
		<!--                ${nick}-->
		<!--            </c:if>-->
		<!--            <img src="<%=basePath%>images/lifetime/xin.gif" width="26" height="22" />-->
		<!--            <c:set value="${empty kdtShowDTO.bbrName ? '她' : kdtShowDTO.bbrName }" var="bbrName"/>-->
		<!--            <c:if test="${fn:length(bbrName)>1}">-->
		<!--                ${fn:substring(bbrName,0,1)} **-->
		<!--            </c:if>-->
		<!--            <c:if test="${fn:length(bbrName)<=1}">-->
		<!--                ${bbrName}-->
		<!--            </c:if>-->
		<!--        </p>-->
		<!--        <p>生效时间：<fmt:formatDate value="${kdtShowDTO.pay_time}" pattern="yyyy-MM-dd"/></p>-->
		<!--        <p class="time">-->
		<!--            <span id="timer_day1">0</span>天-->
		<!--            <span id="timer_hour1">0</span>小时-->
		<!--            <span id="timer_minute1">0</span>分钟-->
		<!--            <span id="timer_second1">0</span>秒-->
		<!--        </p>-->
		<!--        <p>-->
		<!--                ${kdtShowDTO.bbrMessage}-->
		<!--        </p>-->
		<!--    </div>-->
		<!--    <div class="right-panel">-->
		<!--        <c:if test="${empty kdtShowDTO.bbrImg}">-->
		<!--            <img src="<%=basePath%>images/lifetime/11.jpg"/>-->
		<!--        </c:if>-->
		<!--        <c:if test="${!empty kdtShowDTO.bbrImg}">-->
		<!--            <img src="${kdtShowDTO.bbrImg}"/>-->
		<!--        </c:if>-->
		<!--    </div>-->
		<!--</div>-->


		<!-- FOOTER -->
		<div class="footer-panel">
			<img style="width:100%" src="<%=basePath%>images/lifetime/footer2.gif" onclick="window.location='http://mp.weixin.qq.com/s?__biz=MzAwNzMzNjQyOQ==&mid=207051001&idx=1&sn=16bdcf566316749c9bc60467c6fe2d5e&scene=1&srcid=1013WuLPioPYYowIMKgm74Lv&key=2877d24f51fa5384d86a7d340007d8c4913c4c9a29a27b65b43d2a6e6f9be1ef8efd7036a5f5cfe9f90234691bfec233&ascene=1&uin=ODIxNDgyNDIw&devicetype=Windows+8&version=61050016&pass_ticket=mg7w%2BoRC5P5NgbX7IXeZ1YWUU1X4L8Aep8LGZ56MV%2FsBO%2BWajUdbPJWeeZvJSQyq'"/>
		</div>
		<!-- FOOTER END -->
	</body>
</html>

<script type="text/javascript" language="JavaScript">
    $(function () {
        count_down('1', '<fmt:formatDate value="${kdtShowDTO.createdTime}" pattern="yyyy/MM/dd HH:mm:ss"/>');
    });
</script>

