// JavaScript 
/**
 * 获取数据
 * @param val
 */
var page = 1;
function loadMess(path, val, num, real) {
    var url = path + "lifetime/load.do";
    val = ( val == null || val == "") ? "" : val;
    $.post(url, {"card": val}, function (data) {
        var datList = data.list;
        var datCount = data.count;
        $("#reloadMess").html("");
        foreachMess(path, datList, num, real);

        if (data.count > 20) {
            var lineSize = 20;
            var pageSize = datCount % lineSize == 0 ? datCount / lineSize : datCount / lineSize + 1;
            page = pageSize > page ? page : pageSize;
            $("#next-page").bind("click", function () {
                page = page + 1;
                $.post(url, {"card": val, "pageNum": page}, function (data1) {
                    foreachMess(path, data1.list, page, real);
                    if (data1.count <= (page * lineSize)) {
                        $("#next-page").hide();
                    }
                }, "json");
            }).show();
        }
    }, "json");
}

function foreachMess(path, datList, num, real) {
    for (var i = 0; i < datList.length; i++) {
        var sign = i + "" + num;
        var bbrImg = (datList[i].bbrImg != null && datList[i].bbrImg != "") ? "<img src=\"" + datList[i].bbrImg + "\"/>" : "<img src=\"" + path + "images/lifetime/11.jpg\"/>";
        var bbrMessage = datList[i].bbrMessage.length > 25 ? datList[i].bbrMessage.substring(0, 25) + "..." : datList[i].bbrMessage;
        var pay_time = formatCSTDate(datList[i].pay_time, "yyyy-MM-dd");
        var bbrName = datList[i].bbrName.length > 3 ? datList[i].bbrName.substring(0, 3) + "..." : datList[i].bbrName;;
        var buyer_nick = datList[i].buyer_nick.length > 3 ? datList[i].buyer_nick.substring(0, 3) + ".." : datList[i].buyer_nick;
        if (!real) {
        	bbrName = datList[i].bbrName.length > 1 ? datList[i].bbrName.substring(0, 1) + "**" : datList[i].bbrName;
        	buyer_nick = datList[i].buyer_nick.length > 1 ? datList[i].buyer_nick.substring(0, 1) + "**" : datList[i].buyer_nick;
        }
        var id = datList[i].id;
        var htm = "<a href=\"" + path + "lifetime/detail.do?id=" + id + "&k=f56d2ea3d85aa26fdce5f0b33348d2bb\">";
        htm += "<div class=\"list-panel\">";
        htm += "<div class=\"left-panel\">";
        htm += "<p class=\"header\">" + buyer_nick + "<img src=\"" + path + "images/lifetime/xin.gif\" width=\"26\" height=\"22\" />" + bbrName + "</p>";
        htm += "<p>生效时间：" + pay_time + "</p>";
        htm += "<p class=\"time\"><span id=\"timer_day" + sign + "\">0</span>天<span id=\"timer_hour" + sign + "\">0</span>小时<span id=\"timer_minute" + sign + "\">0</span>分钟";
        htm += "<span id=\"timer_second" + sign + "\">0</span>秒</p>";
        htm += "<p>" + bbrMessage + "</p> </div>";
        htm += "<div class=\"right-panel\">" + bbrImg + "</div></div> </a>";
        $("#reloadMess").append(htm);

        count_down("" + sign + "", "" + formatCSTDate(datList[i].createdTime, "yyyy/MM/dd hh:mm:ss") + "");
    }
}

function search(path) {
    var card = $("#cardNum");
    var cardVal = card.val();
    if (cardVal == null || cardVal == "") {
        loadMess(path, null, 0);
    } else {
        // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (!reg.test(cardVal)) {
            card.css("border-color", "#ff0000");
        } else {
            card.css("border-color", "#9d4949");
            loadMess(path, cardVal, cardVal.substring(cardVal.length - 6, cardVal.length), true);
        }
    }
}

//格式化CST日期的字串
function formatCSTDate(strDate, format) {
    return formatDate(new Date(strDate), format);
}

//格式化日期,
function formatDate(date, format) {
    var paddNum = function (num) {
        num += "";
        return num.replace(/^(\d)$/, "0$1");
    }
    //指定格式字符
    var cfg = {
        yyyy: date.getFullYear() //年 : 4位
        , yy: date.getFullYear().toString().substring(2)//年 : 2位
        , M: date.getMonth() + 1  //月 : 如果1位的时候不补0
        , MM: paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
        , d: date.getDate()   //日 : 如果1位的时候不补0
        , dd: paddNum(date.getDate())//日 : 如果1位的时候补0
        , hh: paddNum(date.getHours())  //时
        , mm: paddNum(date.getMinutes()) //分
        , ss: paddNum(date.getSeconds()) //秒
    }
    format || (format = "yyyy-MM-dd hh:mm:ss");
    return format.replace(/([a-z])(\1)*/ig, function (m) {
        return cfg[m];
    });
}

//定义倒计时函数
function count_down(obj, time) {
    //根据天，时，分，秒的ID找到相对应的元素
    var time_day = $("#timer_day" + obj);
    var time_hour = $("#timer_hour" + obj);
    var time_minute = $("#timer_minute" + obj);
    var time_second = $("#timer_second" + obj);

    // 设定活动结束结束时间
    var time_end = new Date(time);
    time_end = time_end.getTime();
    // 获取当前时间
    var time_now = new Date();
    time_now = time_now.getTime();
   // var time_distance = time_end - time_now;  // 时间差：活动结束时间减去当前时间
   var time_distance =   time_now - time_end;
    var int_day, int_hour, int_minute, int_second;
    if (time_distance >= 0) {
        // 相减的差数换算成天数
        int_day = Math.floor(time_distance / 86400000);
        time_distance -= int_day * 86400000;
        // 相减的差数换算成小时
        int_hour = Math.floor(time_distance / 3600000);
        time_distance -= int_hour * 3600000;
        // 相减的差数换算成分钟
        int_minute = Math.floor(time_distance / 60000);
        time_distance -= int_minute * 60000;
        // 相减的差数换算成秒数
        int_second = Math.floor(time_distance / 1000);

        // 判断小时小于10时，前面加0进行占位
        if (int_hour < 10)
            int_hour = "0" + int_hour;
        // 判断分钟小于10时，前面加0进行占位
        if (int_minute < 10)
            int_minute = "0" + int_minute;
        // 判断秒数小于10时，前面加0进行占位
        if (int_second < 10)
            int_second = "0" + int_second;

        // 显示倒计时效果
        time_day.text(int_day);
        time_hour.text(int_hour);
        time_minute.text(int_minute);
        time_second.text(int_second);
        setTimeout("count_down('" + obj + "','" + time + "')", 1000);
    }

}
