/**
 * 发送手机验证码
 * @param ref
 * @param path
 * @param url
 * @param phoneNumber
 * @returns {boolean}
 */
function sendPhoneNumberCode(ref, path, url, phoneNumber) {
    if (!checkStr(phoneNumber)) {
        alertMsg.error("手机号码不能为空");
        return false;
    } else {
        var $SpanBtn_ = $("#" + ref + "SpanBtn_");
        $SpanBtn_.attr("disabled", "disabled");
        var count = 0;
        var sum = 60;
        var i = setInterval(function () {
            if (count > sum) {
                $SpanBtn_.val("发送验证码").removeAttr("disabled");
                clearInterval(i);
            } else {
                $SpanBtn_.val('剩余' + parseInt(sum - count) + '秒').attr("disabled", "disabled");
            }
            count++;
        }, 1000);

        var $url = path + "site/charge_sendCode.do";
        $.post($url, {phoneNumber: phoneNumber}, function (data) {
            if (!data) {
                alertMsg.error("验证码发送失败");
                clearInterval(i);
                $SpanBtn_.val("发送验证码").removeAttr("disabled");
            } else
                $("#" + ref + "Rank_").bind("blur", function () {
                    checkPhoneNumberCode(ref, path, url, phoneNumber);
                });
        }, "json");

    }
}

/**
 * 校验手机验证码
 * @param ref
 * @param path
 * @param url
 * @param phoneNumber
 * @returns {boolean}
 */
function checkPhoneNumberCode(ref, path, url, phoneNumber) {
    var $code_ = $("#" + ref + "Rank_").val();
    if (!checkStr($code_)) {
        alertMsg.error("验证码不能为空");
        return false;
    } else if ($code_.length != 6) {
        alertMsg.error("验证码为6位数字");
        return false;
    } else {
        var url = path + url;
        $.post(url, {phoneNumber: phoneNumber, code: $code_}, function (data) {
            if (data) {
                var $ba = $("#" + ref + "ButtonActive_");
                $ba.removeClass("buttonDisabled").addClass("buttonActive");
                $("#" + ref + "ConfirmBtn_", $ba).removeAttr("disabled");
            } else
                alertMsg.error("验证码校验失败");
        }, "json");
    }
}