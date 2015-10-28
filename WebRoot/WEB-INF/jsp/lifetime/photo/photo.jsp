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
    <title>给爱情下个套 - 幸福墙</title>
    <link rel="stylesheet" href="<%=basePath%>css/ui-photo-core.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="<%=basePath%>dwz/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jphoto.core.js"></script>
    <script type="text/javascript">
        $(function(){
            var time1 = "2018/10/04 00:00:00";
            var time2 = "2016/10/04 00:00:00";
            var time3 = "2019/10/04 00:00:00";
            var time4 = "2015/6/24 00:00:00";
            var time5 = "2017/10/04 08:33:00";
            var time6 = "2016/10/04 08:33:00";
            count_down(1,time1);
            count_down(2,time2);
            count_down(3,time3);
            count_down(4,time4);
            count_down(5,time5);
            count_down(6,time6);
            count_down(7,time1);
            count_down(8,time3);
        });
    </script>
</head>
<body>
<div class="ui-main">
    <div class="ui-top opacity">
        <div class="ui-top-body">
            <div class="back-tips">
                <a href="<%=basePath%>lifetime/main.do"><</a>
            </div>
            <div class="top-tips">时钟墙</div>
        </div>
    </div>
    <div class="ui-body-main">
        <!-- START -->
        <div class="ui-body-box border-radius">
            <div class="ui-body-context">
                <div class="person-tips font font-color">
                    <span class="sender">小A君</span>
                    <span class="arrows">--></span>
                    <span class="getter">小B妹</span>
                </div>
                <div class="time-tips font font-color">
                    生效时间：2014-12-25
                </div>
                <div class="count-down font font-color">
                    <span id="timer_day1">&nbsp;</span>天
                    <span id="timer_hour1">&nbsp;</span>小时
                    <span id="timer_minute1">&nbsp;</span>分钟
                    <span id="timer_second1">&nbsp;</span>秒
                </div>
                <div class="title-tips font font-color">
                    这是一段感人肺腑的留言，说出了他的心声更表达出了他对她的爱！
                </div>
            </div>
        </div>
        <!-- MIDDLE -->
        <div class="ui-body-box border-radius">
            <div class="ui-body-context">
                <div class="person-tips font font-color">
                    <span class="sender">小A君</span>
                    <span class="arrows">--></span>
                    <span class="getter">小B妹</span>
                </div>
                <div class="time-tips font font-color">
                    生效时间：2014-12-25
                </div>
                <div class="count-down font font-color">
                    <span id="timer_day2">&nbsp;</span>天
                    <span id="timer_hour2">&nbsp;</span>小时
                    <span id="timer_minute2">&nbsp;</span>分钟
                    <span id="timer_second2">&nbsp;</span>秒
                </div>
                <div class="title-tips font font-color">
                    这是一段感人肺腑的留言，说出了他的心声更表达出了他对她的爱！
                </div>
            </div>
        </div>
        <!-- MIDDLE -->
        <div class="ui-body-box border-radius">
            <div class="ui-body-context">
                <div class="person-tips font font-color">
                    <span class="sender">小A君</span>
                    <span class="arrows">--></span>
                    <span class="getter">小B妹</span>
                </div>
                <div class="time-tips font font-color">
                    生效时间：2014-12-25
                </div>
                <div class="count-down font font-color">
                    <span id="timer_day3">&nbsp;</span>天
                    <span id="timer_hour3">&nbsp;</span>小时
                    <span id="timer_minute3">&nbsp;</span>分钟
                    <span id="timer_second3">&nbsp;</span>秒
                </div>
                <div class="title-tips font font-color">
                    这是一段感人肺腑的留言，说出了他的心声更表达出了他对她的爱！
                </div>
            </div>
        </div>
        <!-- MIDDLE -->
        <div class="ui-body-box border-radius">
            <div class="ui-body-context">
                <div class="person-tips font font-color">
                    <span class="sender">小A君</span>
                    <span class="arrows">--></span>
                    <span class="getter">小B妹</span>
                </div>
                <div class="time-tips font font-color">
                    生效时间：2014-12-25
                </div>
                <div class="count-down font font-color">
                    <span id="timer_day4">&nbsp;</span>天
                    <span id="timer_hour4">&nbsp;</span>小时
                    <span id="timer_minute4">&nbsp;</span>分钟
                    <span id="timer_second4">&nbsp;</span>秒
                </div>
                <div class="title-tips font font-color">
                    这是一段感人肺腑的留言，说出了他的心声更表达出了他对她的爱！
                </div>
            </div>
        </div>
        <!-- MIDDLE -->
        <div class="ui-body-box border-radius">
            <div class="ui-body-context">
                <div class="person-tips font font-color">
                    <span class="sender">小A君</span>
                    <span class="arrows">--></span>
                    <span class="getter">小B妹</span>
                </div>
                <div class="time-tips font font-color">
                    生效时间：2014-12-25
                </div>
                <div class="count-down font font-color">
                    <span id="timer_day5">&nbsp;</span>天
                    <span id="timer_hour5">&nbsp;</span>小时
                    <span id="timer_minute5">&nbsp;</span>分钟
                    <span id="timer_second5">&nbsp;</span>秒
                </div>
                <div class="title-tips font font-color">
                    这是一段感人肺腑的留言，说出了他的心声更表达出了他对她的爱！
                </div>
            </div>
        </div>
        <!-- MIDDLE -->
        <div class="ui-body-box border-radius">
            <div class="ui-body-context">
                <div class="person-tips font font-color">
                    <span class="sender">小A君</span>
                    <span class="arrows">--></span>
                    <span class="getter">小B妹</span>
                </div>
                <div class="time-tips font font-color">
                    生效时间：2014-12-25
                </div>
                <div class="count-down font font-color">
                    <span id="timer_day6">&nbsp;</span>天
                    <span id="timer_hour6">&nbsp;</span>小时
                    <span id="timer_minute6">&nbsp;</span>分钟
                    <span id="timer_second6">&nbsp;</span>秒
                </div>
                <div class="title-tips font font-color">
                    这是一段感人肺腑的留言，说出了他的心声更表达出了他对她的爱！
                </div>
            </div>
        </div>
        <!-- MIDDLE -->
        <div class="ui-body-box border-radius">
            <div class="ui-body-context">
                <div class="person-tips font font-color">
                    <span class="sender">小A君</span>
                    <span class="arrows">--></span>
                    <span class="getter">小B妹</span>
                </div>
                <div class="time-tips font font-color">
                    生效时间：2014-12-25
                </div>
                <div class="count-down font font-color">
                    <span id="timer_day7">&nbsp;</span>天
                    <span id="timer_hour7">&nbsp;</span>小时
                    <span id="timer_minute7">&nbsp;</span>分钟
                    <span id="timer_second7">&nbsp;</span>秒
                </div>
                <div class="title-tips font font-color">
                    这是一段感人肺腑的留言，说出了他的心声更表达出了他对她的爱！
                </div>
            </div>
        </div>
        <!-- MIDDLE -->
        <div class="ui-body-box border-radius">
            <div class="ui-body-context">
                <div class="person-tips font font-color">
                    <span class="sender">小A君</span>
                    <span class="arrows">--></span>
                    <span class="getter">小B妹</span>
                </div>
                <div class="time-tips font font-color">
                    生效时间：2014-12-25
                </div>
                <div class="count-down font font-color">
                    <span id="timer_day8">&nbsp;</span>天
                    <span id="timer_hour8">&nbsp;</span>小时
                    <span id="timer_minute8">&nbsp;</span>分钟
                    <span id="timer_second8">&nbsp;</span>秒
                </div>
                <div class="title-tips font font-color">
                    这是一段感人肺腑的留言，说出了他的心声更表达出了他对她的爱！
                </div>
            </div>
        </div>
        <!-- END -->

        <!-- BOTTOM -->
        <div class="ui-bottom-panel">
            <div class="halving-line"></div>
            <div class="signature">
                Copyright &copy; 2014 LifeTime, All Rights Reserved
            </div>
        </div>
    </div>
</div>
</body>
</html>
