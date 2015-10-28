/**
 * @author HHang
 * 校验用户输入的登录信息是否合法
 */
$(function () {
    $(".sub").click(function () {
        var $body = $("body");
        var username = $("#username", $body).val();
        var password = $("#password", $body).val();
        var code = $("#seccode", $body).val();
        var errMsg = $(".errMsg", $body);
        if (username == null || username == "") {
            errMsg.text("请输入您的用户名");
        } else if (password == null || password == "") {
            errMsg.text("请输入您的密码");
        } else if (code == null || code == "") {
            errMsg.text("请输入验证码");
        } else {
            var url = $("form").attr("action");
            $.post(url, {username: username, password: password, seccode: code},function(data){
                if(data.resultCode!=0){
                    errMsg.text(data.resultMsg);
                }else{
                    window.location.href="admin/admin_doIndex.do";
                }
            },"json");
        }
        return false;
    });

    //绑定Enter事件
    document.onkeydown = function(e){
        var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            $(".sub").click();
        }
    }
})