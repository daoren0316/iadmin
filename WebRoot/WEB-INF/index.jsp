<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Cache" content="no-cache">
    <title>iButler后台管理系统</title>

    <link href="<%=basePath%>dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="<%=basePath%>dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="<%=basePath%>dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
    <link href="<%=basePath%>dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
    <!--[if IE]>
    <link href="<%=basePath%>dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <script src="<%=basePath%>dwz/js/speedup.js" type="text/javascript"></script>
    <![endif]-->

    <script src="<%=basePath%>dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/jquery.validate.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

    <!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
    <script src="<%=basePath%>highcharts/js/highcharts.js"></script>
    <script src="<%=basePath%>highcharts/js/modules/exporting.js"></script>

    <script src="<%=basePath%>dwz/js/dwz.core.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.util.date.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.validate.method.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.barDrag.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.drag.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.tree.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.accordion.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.ui.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.theme.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.tab.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.resize.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.dialog.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.cssTable.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.stable.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.taskBar.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.ajax.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.pagination.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.database.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.effects.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.panel.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.checkbox.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.history.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.combox.js" type="text/javascript"></script>
    <script src="<%=basePath%>dwz/js/dwz.print.js" type="text/javascript"></script>

    <!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换是下面dwz.regional.zh.js还需要引入)
    <script src="bin/dwz.min.js" type="text/javascript"></script>
    -->
    <script src="<%=basePath%>dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
    <script src="<%=basePath%>My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/jswisstar.site.min.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=basePath%>js/jswisstar.file.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=basePath%>js/jswisstar.common.min.js" type="text/javascript" charset="UTF-8"></script>

    <script type="text/javascript">
        $(function () {
            <c:if test="${!empty adminUser}">
            DWZ.init("<%=basePath%>dwz.frag.xml", {
                // loginUrl: "admin/admin_doLogout.do", loginTitle: "登录",	// 弹出登录对话框
                loginUrl: "<%=basePath%>admin/admin_doLogout.do",	// 跳到登录页面
                statusCode: {ok: 200, error: 300, timeout: 301}, //【可选】
                pageInfo: {pageNum: "pageNum", numPerPage: "numPerPage", orderField: "orderField", orderDirection: "orderDirection"}, //【可选】
                debug: false,	// 调试模式 【true|false】
                callback: function () {
                    initEnv();
                    $("#themeList").theme({themeBase: "themes"}); // themeBase 相对于index页面的主题base路径
                }
            });
            </c:if>

            <c:if test="${empty adminUser}">
            //alert("登录超时，请重新登录！");
            window.location.href = "<%=basePath%>admin/admin_doLogout.do";
            </c:if>
        });
    </script>
</head>

<body scroll="no">
<div id="layout">
    <div id="header">
        <div class="headerNav">
            <a  href="http://j-ui.com"></a>
            <ul class="nav">
                <li style="background: url('')"><a href="javascript:;">
                    ${adminUser.nickname}, 欢迎您使用iButler后台管理系统！</a>
                </li>
                <c:if test="${adminUser.userType eq 1 || adminUser.userType eq 2 ||adminUser.userType eq 3}">
                    <li><a href="<%=basePath %>admin/shop_toUpdate.do" target="dialog" maxable="false" mask="true"
                           width="750" height="500" rel="changeBMessage">修改基本信息</a></li>
                </c:if>
                <li><a href="<%=basePath %>admin/shop_toChangePwd.do" target="dialog" maxable="false" mask="true"
                       width="750" height="500" rel="changePwd">修改登陆密码</a></li>
                <li><a href="<%=basePath%>admin/admin_doLogout.do" onclick="return window.confirm('确定退出么？');">退出</a>
                </li>
            </ul>
            <ul class="themeList" id="themeList">
                <li theme="default">
                    <div class="selected">蓝色</div>
                </li>
            </ul>
        </div>

        <!-- navMenu -->
    </div>

    <div id="leftside">
        <div id="sidebar_s">
            <div class="collapse">
                <div class="toggleCollapse">
                    <div></div>
                </div>
            </div>
        </div>
        <div id="sidebar">
            <div class="toggleCollapse"><h2>主菜单</h2>

                <div>收缩</div>
            </div>

            <div class="accordion" fillSpace="sidebar">
                <div class="accordionHeader">
                    <h2><span>Folder</span>界面组件</h2>
                </div>
                <div class="accordionContent">
                    <c:if test="${!empty leftPower}">
                        <ul class="tree treeFolder">
                            <c:forEach items="${leftPower}" var="top">
                                <c:if test="${!empty top.powerLevel && top.powerLevel eq 0 }">
                                    <li><a href="javascript:void(0);">${top.powerName }</a>
                                        <ul>
                                            <c:forEach items="${leftPower}" var="two">
                                                <c:if test="${two.parent eq top.powerId && two.powerLevel eq 1}">
                                                    <li><a href="<%=basePath %>${two.powerUrl}" target="${two.target}"
                                                           rel="${two.rel}">${two.powerName }</a></li>
                                                </c:if>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <div id="container">
        <div id="navTab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                    <ul class="navTab-tab">
                        <li tabid="main" class="main"><a href="javascript:;"><span><span
                                class="home_icon">我的主页</span></span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft">left</div>
                <!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                <div class="tabsRight">right</div>
                <!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                <div class="tabsMore">more</div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">我的主页</a></li>
            </ul>
            <div class="navTab-panel tabsPageContent layoutBox">
                <div class="page unitBox">
                    <div class="accountInfo">
                        <p><span>欢迎您使用iButler后台管理系统！</span></p>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

<div id="footer">Copyright &copy; 2014 SWISSTAR团队 浙ICP备20140701号-10</div>

</body>
</html>