<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>2015情人节最有逼格的礼物，你有资格买吗？</title>

<script src="<%=basePath%>js/mobile.min.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="css/wechat.css"> -->
<style>
html, body {
    margin: 0;
    color: #000;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    font-family: '微软雅黑';
}

#loading {
    text-align: center;
    font-size: 20px;
    line-height: 400px;
    display: none;
}

.loading #loading {
    display: block;
}

.loading .head,
.loading .main {
    display: none;
}

.head img {
    width: 100%;
}

.main {
    padding: 10px;
    padding-top: 0;
    margin-top: -10px;
    text-align: center;
}

.t-t {
    font-size: 22px;
    line-height: 2em;
    text-align: left;
}

.t-s {
    padding: 3px 10px;
    background-color: #eca702;
    max-width: 65%;
    min-width: 20%;
    height: auto;
    font-weight: bold;
    line-height: 32px;
    margin: 10px auto;
    text-align: center;
    position: relative;
    border-radius: 5px;
    color: #FFF;
    cursor: pointer;
    -webkit-transition: background-color 0.3s ease;
}

img {
    max-width: 100%;
}

.t-s img {
    max-width: 100%;
}

.t-s span {
    font-size: 1em;
    line-height: 1.5em;
}

.t-s.active {
    background-color: #27AE60;
}

.t-s.active span {
    color: #000;
}

.t-s:after {
    position: absolute;
    right: 1px;
    top: 1px;
    background: #27AE60;
    display: none;
    opacity: 0;
    -webkit-transition: opacity 0.3s ease;
}

.t-s.active:after {
    display: block;
    opacity: 1;
}

.t-c {
    line-height: 1.2em;
    padding: 10px;
    margin-bottom: 10px;
    font-size: 1em;
    text-align: left;
}

.t-s.h {
    max-width: 40%;
    display: inline-block;
    margin-left: 5px;
    margin-right: 5px;
}

.t-r {
    margin-bottom: 10px;
}

.t-r span {
    display: inline-block;
    vertical-align: middle;
    font-size: 50px;
    color: #27AE60;
    /*color: rgba(0,0,0,0);*/
    text-shadow: 3px 0 4px #16A085;
}

.t-img {
    margin-top: 10px;
    margin-bottom: 10px;
}

.t-img img {
    max-width: 60%;
}

.t-btn,
.t-btn:link,
.t-btn:visited {
    color: #fff;
    background-color: #eca702;
    padding-top: 12px;
    padding-bottom: 13px;
    padding-left: 15px;
    padding-right: 15px;
    display: inline-block;
    line-height: 1em;
    min-width: 60%;
    margin: 10px auto;
    border-radius: 7px;
    cursor: pointer;
    text-decoration: none;
}

.share {
    position: fixed;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.6);
    display: none;
    z-index: 1000;
}

.share img {
    /*position: absolute;*/
    top: 0;
    right: 0;
    /*width: 70%;*/
    /*max-width: 320px;*/
}

.livetip {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.7);
    z-index: 200;
}

.lt-w {
    position: fixed;
    top: 100px;
    left: 10px;
    right: 10px;
    z-index: 300;
}

.lt-w .lt-c {
    background-color: white;
    padding-top: 10px;
    padding-bottom: 20px;
}

.list {
    list-style: initial;
    text-align: left;
}

.list li {
    margin-top: 0.5em;
    margin-bottom: 0.5em;
}

a,
a:link,
a:visited {
    color: #607fa6;
}

.dialog {
    position: fixed;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
}

.dialog-bg {
    position: absolute;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.7);
}

.dialog-con {
    position: absolute;
    width: 280px;
    top: 100px;
    background-color: white;
    left: 50%;
    margin-left: -140px;
    padding-bottom: 10px;
}
</style>
</head>
<body class="loading">
<div id="loading">
    加载中
</div>
<div class="head">
    <img src="<%=basePath%>images/lifetime/640.jpg">
</div>
<div class="main" id="main" ms-controller="test">
    <!-- 测试题 -->
    <div ms-if="item.type=='hello'">
        <div class="t-c"
             ms-if="item.text">
            {{item.text}}
        </div>
        <div class="t-c"
             ms-if="item.text1">
            {{item.text1}}
        </div>
        <div class="t-s h"
             ms-repeat-elem="item.select"
             ms-class-active="elem.active"
             ms-on-click="elem.tap(elem, item.select)">
            <img ms-attr-src="{{elem.img}}">
            <span>{{elem.text}}</span>
        </div>

    </div>
    <div ms-if="item.type=='select'">
        <div class="t-c">
            <div ms-if="item.img" style="margin-bottom:0.5em;">
                <img ms-attr-src="{{item.img}}">
            </div>
            {{item.id}}. {{item.text}}
        </div>
        <div class="t-s"
             ms-repeat-elem="item.select"
             ms-class-active="elem.active"
             ms-on-click="elem.tap(elem, item.select)">
            <img
                    ms-if="!!elem.img"
                    ms-attr-src="{{elem.img}}">
            <span>{{elem.text}}</span>
        </div>
    </div>
    <div ms-if="item.type=='result'">
        <div class="t-r">
            <span>{{item.ss}}</span>{{item.sstext}}
        </div>
        <div class="t-img" ms-if="!!item.img">
            <img ms-attr-src="{{item.img}}">
        </div>
        <div class="t-c"
             ms-if="item.text">
            {{item.text}}
        </div>
        <div class="t-btn"
             ms-repeat-elem="endbtns"
             ms-on-click="elem.tap()">{{elem.text}}
        </div>
    </div>
    <div class="share"
         ms-if="showshare"
         ms-visible="showshare"
         ms-on-tap="hideshare()">
        <img src="<%=basePath%>images/lifetime/0.png">
    </div>
    <div class="livetip"
         ms-visible="livetip"
         ms-on-tap="hidelivetip()">
    </div>
    <div class="lt-w"
         ms-visible="livetip">
        <div class="lt-c">
            <div class="t-c">
                还没分享哦，是否把这个好玩的东西分享给朋友后再离开呢？
            </div>
            <div class="t-btn"
                 ms-on-tap="sharebox()">分享
            </div>
            <div class="t-btn white"
                 ms-on-tap="livenow()">一会再说，先去看好玩的
            </div>
        </div>
    </div>
    <div class="dialog" ms-if="xunzhang">
        <div class="dialog-bg" ms-on-tap="hidexz()"></div>
        <div class="dialog-con">
            <div class="t-btn white"
                 ms-on-tap="hideshare()">取消
            </div>
        </div>
    </div>
    <!-- <h3 style="text-align:left;padding-left:10px;font-size:1em;">其他更好玩的：</h3>
    <ol class="list" id="other_arc">

    </ol>  -->
</div>

<script>
var ID = getQueryString("sid") || Date.now() + "" + Math.floor(Math.random() * 1000);
// addView();
var fen = 0;
function tap(elemvm, select, cb) {
    var a = false;
    for (var i = 0; i < select.length; i++) {

        a = a || select[i].active;
    }
    ;
    if (!a) {
        elemvm.active = true;
        setTimeout(function () {
            cb && cb()
        }, 500)
    }
}
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = location.search.substr(1).match(reg);
    if (r != null) return unescape(decodeURI(r[2]));
    return null;
}
var model = avalon.define("test", function (vm) {

    vm.arr = [
        {
            type: "hello",
            text: "这份礼物之前你肯定没有送过，TA肯定也没有收过，且一定能让TA感动一辈子！但由于这份礼物的独特性，85%的人都不能购买！测测你有这个资格吗？",
            text1: "点击开始测试咯~",
            select: [
                // {
                //     img: "http://mmbiz.qpic.cn/mmbiz/mhNj6OVxBQHOIxaibNiaCDE3veWkCTua8T9a4cEGDoCdfsD8aKWwtozrbhjXXHac1F7rJtJoT8WXrPsELN8ibOOqQ/0",
                //     text: "男生",
                //     active: false,
                //     tap: function (ele, select) {
                //         tap(ele, select, function () {
                //             model.item = model.arr[1];
                //             model.result.sex = 0;
                //         });
                //     }
                // },
                {
                    text: " 开始测试 ",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[1];
                            model.result.sex = 1;
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 1,
            text: "你与现任男/女朋友交往了多久?",
            select: [
                {
                    text: "还在追求",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[2];
                        });
                    }
                },
                {
                    text: "一年以下",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[2];
                        });
                    }
                },
                {
                    text: "一年至三年",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[2];
                        });
                    }
                },
                {
                    text: "三年以上",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[2];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 2,
            text: "给TA表白的时候，用的是什么方式?",
            select: [
                {
                    text: "强攻式：当众表白，我就是想全世界都知道我中意TA",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[4];
                        });
                    }
                },
                {
                    text: "迂回式：通过短信/电话/私信…吐露心声",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[3];
                        });
                    }
                },
                {
                    text: "默契式：没什么表不表白，相互都懂的，就在一起啦~",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[5];
                        });
                    }
                },
                {
                    text: "人家是被表白的，此题无效",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[3];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 3,
            text: "假如你误闯一间黑店，老板端出五杯饮料，告诉你当中只有一杯没毒，你直觉哪一杯不会被下毒？（请根据直觉选择答案）",
            select: [
                {
                    text: "新鲜牛奶",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[4];
                        });
                    }
                },
                {
                    text: "热烫乌龙茶",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[6];
                        });
                    }
                },
                {
                    text: "美式热咖啡",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[5];
                        });
                    }
                },
                {
                    text: "珍珠奶茶",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[4];
                        });
                    }
                },
                {
                    text: "纯净白开水",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[7];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 4,
            text: "你认为爱情的最高境界是什么？",
            select: [
                {
                    text: "长相厮守",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[5];
                        });
                    }
                },
                {
                    text: "曾经拥有",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[6];
                        });
                    }
                },
                {
                    text: "只要TA快乐",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[7];
                        });
                    }
                },
                {
                    text: "无法用语言形容",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[8];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 5,
            text: "当TA温柔的看着你，你会说：",
            select: [
                {
                    text: "你的眼睛大又圆",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[6];
                        });
                    }
                },
                {
                    text: "你真好",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[7];
                        });
                    }
                },
                {
                    text: "你踩到我的脚了！",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[6];
                        });
                    }
                },
                {
                    text: "你想什么呢？",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[8];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 6,
            text: "觉得感情如何保鲜？",
            select: [
                {
                    text: "各种纪念日都庆祝",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[7];
                        });
                    }
                },
                {
                    text: "双方互相包容",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[9];
                        });
                    }
                },
                {
                    text: "有问题及时沟通",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[8];
                        });
                    }
                },
                {
                    text: "感情只有新鲜感，不能保鲜",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[7];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 7,
            text: "当你在公园里散步时，看见一名长相不错的异性，坐在长椅上沉思，你会联想什么呢？",
            select: [
                {
                    text: "你会彷佛没有看见一般，从身边走过",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[9];
                        });
                    }
                },
                {
                    text: "你会想：他是一个人吗?是否在等人",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[8];
                        });
                    }
                },
                {
                    text: "你会想：你是否有烦恼?看起来好可怜",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[9];
                        });
                    }
                },
                {
                    text: "你会想：他一定是不受女人欢迎的大男人",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[8];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 8,
            text: "爱情、婚姻与工作的重要性排列（由重要至不重要）",
            select: [
                {
                    text: "爱情、婚姻、工作",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[9];
                        });
                    }
                },
                {
                    text: "爱情、工作、婚姻",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[9];
                        });
                    }
                },
                {
                    text: "婚姻、爱情、工作",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[9];
                        });
                    }
                },
                {
                    text: "婚姻、工作、爱情",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[9];
                        });
                    }
                },
                {
                    text: "工作、爱情、婚姻",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[9];
                        });
                    }
                },
                {
                    text: "工作、婚姻、爱情",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[9];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 9,
            text: "向恋爱对象表述感情时,您最常或最期望采用的方式是？",
            select: [
                {
                    text: "语言或文字表达",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[11];
                        });
                    }
                },
                {
                    text: "没有特定",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[10];
                        });
                    }
                },
                {
                    text: "身体接触",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[11];
                        });
                    }
                },
                {
                    text: "赠送礼物",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[10];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 10,
            text: "你给现任女/男朋友送过玫瑰吗",
            select: [
                {
                    text: "没送过，短时间内不准备送",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[14];
                        });
                    }
                },
                {
                    text: "送过了",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[11];
                        });
                    }
                },
                {
                    text: "准备情人节期间送",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[11];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 11,
            text: "你跟你现任女/男朋友上过床么",
            select: [
                {
                    text: "没有，机会还不成熟",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[12];
                        });
                    }
                },
                {
                    text: "上过",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[12];
                        });
                    }
                },
                {
                    text: "准备情人节那天发生关系",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[14];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 12,
            text: "你对现任女/男朋友什么态度",
            select: [
                {
                    text: "希望能牵手一辈子",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[13];
                        });
                    }
                },
                {
                    text: "交往一段时间看看，合则来，不合则去",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[14];
                        });
                    }
                },
                {
                    text: "玩玩就好，别太认真",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[14];
                        });
                    }
                }
            ]
        },
        {
            type: "select",
            id: 13,
            text: "如果分手了，你希望",
            select: [
                {
                    text: "还能做朋友，还能关心TA",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[15];
                        });
                    }
                },
                {
                    text: "不再往来，TA过得好不好跟我无关",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[14];
                        });
                    }
                },
                {
                    text: "可能会恨TA，知道TA过得不好，我也就安心了",
                    active: false,
                    tap: function (ele, select) {
                        tap(ele, select, function () {
                            model.item = model.arr[14];
                        });
                    }
                }
            ]
        },
        {
            type: "result",
            id: 14,
            text: "对不起，根据你们俩目前所处的情况以及你对爱情的态度，我们认为你还不是真爱，还没有资格买《给爱情下个套》这份礼物，你可以关注该微信公众号了解，但是不建议你购买，因为你有可能会误了TA一生。"
        },
        {
            type: "result",
            id: 15,
            text: "你和你的TA是真爱哦，赶紧关注《给爱情下个套》微信公众号，奉上真爱大礼，执子之手许下爱的承诺，一定能让TA感动一辈子"
        }

    ];
    vm.item = vm.arr[0];
    vm.endbtns = [
        {
            text: "分享",
            tap: function () {
                model.showshare = true;
            }
        },
        {
            text: "查看最逼格的礼物",
            tap: function () {
                location.href = "http://kdt.im/xlmYCLRYX";
            }
        },
        {
            text: "再测一次",
            tap: function () {
                model.result.sex = 0;
                for (var i = 0; i < model.arr.length; i++) {
                    var tmp = model.arr[i];
                    if (tmp.select) {
                        for (var ii = 0; ii < tmp.select.length; ii++) {
                            tmp.select[ii].active = false;
                        }
                    }
                }
                model.item = model.arr[0];
            }
        }
    ]
    vm.$otherurl = "http://game.choose1.net/?f=c_nhz";
    vm.result = {
        sex: 0
    }
    vm.showshare = false;
    vm.hideshare = function () {
        model.showshare = false;
    }
    vm.shared = false;
    vm.sharebox = function () {
        model.showshare = true;
    }
    vm.livetip = false;
    vm.hidelivetip = function () {
        model.livetip = false;
    }
    vm.livenow = function () {
        location.href = model.$otherurl;
    }
    vm.xunzhang = false;
    vm.hidexz = function () {
        model.xunzhang = false;
    }
    vm.showxz = function () {
        model.xunzhang = true;
    }
    vm.$watch("item", function (newval) {
        if (newval.type == 'result') {
            testEnd(newval.id);
        }
    })
});
document.body.className = "";

// 增加访问量
function addView() {
    var shtime = getQueryString("shtime");
    shtime = shtime ? "&shtime=" + shtime : "";
    var tag = getQueryString("tag");
    tag = tag ? "&tag=" + tag : "";
    var view = document.createElement("script");
    view.src = "http://wxcdn1.aliapp.com/logs/log.php?action=view&id=8118" + shtime + tag;
    document.body.appendChild(view);
    _hmt.push(['_trackEvent', '730.女汉子[访问]', '访问', '', 1]);
}
// 增加使用
function addUse() {
    var tag = getQueryString("tag");
    tag = tag ? "&tag=" + tag : "";
    var view = document.createElement("script");
    view.src = "http://wxcdn1.aliapp.com/logs/log.php?action=use&id=8118" + tag;
    document.body.appendChild(view);
    _hmt.push(['_trackEvent', '730.女汉子[测完]', '测完', '', 1]);
}
// 增加分享
function addShare() {
    var tag = getQueryString("tag");
    tag = tag ? "&tag=" + tag : "";
    var view = document.createElement("script");
    var fromid = getQueryString("fromid");
    fromid = fromid === null ? "" : "&fromid=" + fromid;
    view.src = "http://wxcdn1.aliapp.com/logs/log.php?action=share&id=8118" + tag;
    document.body.appendChild(view);
    _hmt.push(['_trackEvent', '730.女汉子[分享]', '分享', '', 1]);
}

function shareToFre(desc) {
    // alert(desc);
    if (!desc) {
        desc = "据说95%的女人都是女汉子，鉴定前她们都不信…";
    } else {
        desc = desc;
    }
    // 朋友圈

    window.shareData.title = desc;
    // share to frends
    var data = data;
    var callbacks = {};
    callbacks.ok = callbacks.confirm = function () {
        model.shared = true;
        // addShare();
    };
}
window.shareData = {
    "img_url": "<%=basePath%>images/lifetime/640.jpg",
    "link": "<%=basePath%>lifetime/test.do",
    "desc": "测测2015情人节最有逼格的礼物，你有资格买吗？",
    "title": "2015情人节最有逼格的礼物，你有资格买吗？"
};
var mebtnopenurl1 = 'http://game.choose1.net/?g=ceshis_nhz&f=shareTimelineComplete';
var mebtnopenurl2 = 'http://game.choose1.net/?g=ceshis_nhz&f=shareFriendComplete';

function WeiXinShareBtn() {
    WeixinJSBridge.on('menu:share:timeline', function (argv) {
        WeixinJSBridge.invoke('shareTimeline', {
            "title": "36氪",
            "link": "http://www.36kr.com",
            "desc": " 关注互联网创业 ",
            "img_url": "http://www.36kr.com/assets/images/apple-touch-icon.png"
        }, function (res) {
            document.location.href = "http://www.baidu.com";
        });
    });
}
</script>
</body>
</html>