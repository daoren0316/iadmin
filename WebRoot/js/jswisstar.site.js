/**
 * 调用说明
 * 1，注意页面中id的取值，要遵循JS中的规范。如手机号的命名（ref + PhoneNumber_）
 * 2, JS中支持的函数有：
 *      手机号（ref + "PhoneNumber_"）
 *      卡号  （ref + "CardNo_"）
 *      验证码 (ref + "Rank_")
 *      金额   (ref + "Amount_")
 *
 * 方法说明
 * 1，sendCode 发送手机验证码
 * 2，checkCode 校验手机验证码
 * 3，toLoadPhoneNumber_ 根据户号获取该户成员手机号
 * 4，toConfirmMsgBtn_  确认信息
 *
 * 其他说明
 * 1，ref 指页面配置的id前缀
 * 2，path 指项目的绝对路径
 *
 * @author HHang
 */

/**
 * 发送手机验证码
 * @param ref
 * @param path
 * @param url
 * @param suffix
 * @returns {boolean}
 */
function sendCode(ref, path, url, suffix) {
    var $phoneNumber_ = $("#" + ref + "PhoneNumber_").val(); // 手机号码
    if (jQuery.isEmptyObject($phoneNumber_))
        $phoneNumber_ = $("#" + ref + "PhoneNumber_  option:selected").val();
    if (!checkStr($phoneNumber_)) {
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
        $.post($url, {phoneNumber: $phoneNumber_}, function (data) {
            if (!data) {
                alertMsg.error("验证码发送失败");
                clearInterval(i);
                $SpanBtn_.val("发送验证码").removeAttr("disabled");
            } else
                $("#" + ref + "Rank_").bind("blur", function () {
                    checkCode(ref, path, url, suffix);
                });
        }, "json");

    }
}

/**
 * 校验手机验证码
 * @param ref
 * @param path
 * @param url
 * @param suffix
 * @returns {boolean}
 */
function checkCode(ref, path, url, suffix) {
    var $phoneNumber_ = $("#" + ref + "PhoneNumber_").val(); // 手机号码
    var $code_ = $("#" + ref + "Rank_").val();
    var $house_ = $("#" + ref + "ComboxHouse_  option:selected").val();//户号
    if (!checkStr($phoneNumber_)) {
        alertMsg.error("手机号码不能为空");
        return false;
    } else if (!checkStr($code_)) {
        alertMsg.error("验证码不能为空");
        return false;
    } else if ($code_.length != 6) {
        alertMsg.error("验证码为6位数字");
        return false;
    } else {
        var url = path + url;
        $.post(url, {phoneNumber: $phoneNumber_, code: $code_, houseId: $house_}, function (data) {
            if (data.code == 0) {
                if (data.result != null && data.result > 0) {
                    var $ba = $("#" + ref + "ButtonActive_");
                    $ba.removeClass("buttonDisabled").addClass("buttonActive");
                    $("#" + ref + "ConfirmBtn_", $ba).removeAttr("disabled");
                }
                $("#" + ref + suffix).val(data.result);
            } else
                alertMsg.error(data.result);
        }, "json");
    }
}

/**
 * 根据户号获取该户成员手机号
 * @param val
 * @param ref
 * @param path
 * @param isRead 是否只读
 * @url url
 * @private
 */
function toLoadPhoneNumber_(val, ref, path, isRead, other, actionUrl) {
    var $phoneNumberTip_ = $("#" + ref + "PhoneNumberTip_");
    var $id = ref + "PhoneNumber_";
    var $read = isRead ? "readonly='readonly'" : "";
    var $html = "<input type='text' name='phoneNumber' id='" + $id + "' class='required phone' size='25' " + $read + " />";
    if (val != '0') {
        var $url = path + actionUrl;
        $.post($url, {houseId: val}, function (data) {
            if (!jQuery.isEmptyObject(data)) {
                var $combox = "<select class='combox' name='phoneNumber' id='" + $id + "' style='width: 165px;'>";
                $.each(data, function (i, n) {
                    var $HMain = n['HMain'];
                    // 验证是否为户主
                    var $txt = $HMain ? "[主]" + n['phone'] : n['phone'];
                    $combox = $combox + "<option value='" + n['phone'] + "'>" + $txt + "</option>";
                });
                $combox = $combox + "</select>";
                // 验证是否需要其它号码的按钮
                if (other) {
                    $combox = $combox + "<p style='width: 500px;padding: 0;'>";
                    $combox = $combox + "<a class='button' style='padding:0px 3px;margin-top:2px; ' href=\"javascript:otherPhone('" + val + "','" + ref + "','" + path + "');\"><span>其它</span></a>";
                    $combox = $combox + "</p>";
                }
                $phoneNumberTip_.text("").append($combox);
            } else
                $phoneNumberTip_.text("").append($html);
        }, "json");
    } else
        $phoneNumberTip_.text("").append($html);
}

/**
 * 却换输入框
 * @param ref
 */
function otherPhone(val, ref, path) {
    var $phoneNumberTip_ = $("#" + ref + "PhoneNumberTip_");
    var $txt = "<input type='text' name='phoneNumber' id='" + ref + "PhoneNumber_' class='required phone' size='25'/>";
    $txt = $txt + "<img src='" + path + "images/reload.png' width='22' height='22' style='padding: 0 3px;vertical-align:middle;cursor:pointer;' title='返回' ";
    $txt = $txt + "onclick=\"toLoadPhone_('" + val + "','" + ref + "','" + path + "','" + false + "','" + true + "')\"/>";
    $phoneNumberTip_.text("").append($txt);
}

/**
 * 根据户号获取该户成员手机号
 * @param val
 * @param ref
 * @param path
 * @param isRead
 * @private
 */
function toLoadPhone_(val, ref, path, isRead, other) {
    toLoadPhoneNumber_(val, ref, path, isRead, other, "site/charge_loadPhone.do");
}


/**
 * 确认信息
 * @param ref
 * @param url
 * @param title
 * @returns {boolean}
 * @private
 */
function toConfirmMsgBtn_(ref, url, title) {
    var $OPhoneNumber_ = $("#" + ref + "PhoneNumber_"); // 手机号码
    var $ORank_ = $("#" + ref + "Rank_"); //手机验证码
    var $OCardNo_ = $("#" + ref + "CardNo_"); //卡号.
    var $OAmount_ = $("#" + ref + "Amount_");//金额
    var $OHouse_ = $("#" + ref + "ComboxHouse_  option:selected");//户号
    var $VPhoneNumber_ = "";
    var $VRank_ = "";
    var $VCardNo_ = "";
    var $VAmount_ = "";
    var $VHouse_ = "";
    if ($OHouse_[0]) {
        $VHouse_ = $OHouse_.val();
        if ($VHouse_ == "0") {
            alertMsg.error("请选择户号");
            return false;
        }
    }

    if ($OPhoneNumber_[0]) {
        $VPhoneNumber_ = $OPhoneNumber_.val();
        if (jQuery.isEmptyObject($VPhoneNumber_))
            $VPhoneNumber_ = $("#" + ref + "PhoneNumber_  option:selected").val();
        if (!checkStr($VPhoneNumber_)) {
            alertMsg.error("手机号码不能为空");
            return false;
        }
    }
    //
    if ($ORank_[0]) {
        $VRank_ = $ORank_.val();
        if (!checkStr($VRank_)) {
            alertMsg.error("验证码不能为空");
            return false;
        } else if ($VRank_.length != 6) {
            alertMsg.error("验证码为6位数字");
            return false;
        }
    }
    //
    if ($OAmount_[0]) {
        $VAmount_ = $OAmount_.val();
        if (!checkStr($VAmount_)) {
            alertMsg.error("金额不能为空");
            return false;
        }
    }
    //
    if ($OCardNo_[0]) {
        $VCardNo_ = $OCardNo_.val();
        if (!checkStr($VCardNo_)) {
            alertMsg.error("卡号不能为空");
            return false;
        }
    }
    //
    title = checkStr(title) ? "确认信息" : title;
    var $cardUrl_ = url + "?cardNo=" + $VCardNo_ + "&phoneNumber=" + $VPhoneNumber_ + "&code=" + $VRank_
        + "&amount=" + $VAmount_ + "&houseId=" + $VHouse_;
    $.pdialog._HOpen($cardUrl_, "toConfirm" + ref + "Card_", title, {
        mask: true,
        maxable: true,
        minable: true,
        resizable: false,
        width: 500,
        height: 300
    });

}

/**
 * 获取业主余额
 * @param path
 * @param val
 * @param ref
 */
function queryAmount(val, ref, path) {
    $.post(path + "site/charge_queryAmount.do", {houseId: val}, function (data) {
        if (data == null || data == '')
            data = 0;
        $("#" + ref + "AmountPanel_").html(data);
    }, "json");
}

/**
 * 显示业主地址
 * @param val
 * @param ref
 * @param path
 * @returns {boolean}
 */
function showAddress(val, ref, path, isAdmin) {
    var $tipPanel = $("#" + ref + "TipPanel_");
    var $spanTipPanel = $("span", $tipPanel);
    var $spanText = "";
    if (isAdmin) {
        var $CityVal = $("#" + ref + "ComboxCityId_  option:selected").val();
        var $CommunityVal = $("#" + ref + "ComboxCommunityId_  option:selected").val();

        var $City = $("#" + ref + "ComboxCityId_  option:selected").text();
        var $Community = $("#" + ref + "ComboxCommunityId_  option:selected").text();

        if ($CityVal != '0') {
            $spanText = $City;
            $spanTipPanel.text("").append($City);
        }

        if ($CommunityVal != '0') {
            $spanText = $City + $Community;
            $spanTipPanel.text("").append($City + $Community);
        }

    } else {
        $spanText = $("#" + ref + "CommunityName_").val();
    }

    var $buildingVal = $("#" + ref + "ComboxbuildingId_  option:selected").val();
    var $unitVal = $("#" + ref + "ComboxUnit_  option:selected").val();
    var $houseVal = $("#" + ref + "ComboxHouse_  option:selected").val();

    var $building = $("#" + ref + "ComboxbuildingId_  option:selected").text();
    var $unit = $("#" + ref + "ComboxUnit_  option:selected").text();
    var $house = $("#" + ref + "ComboxHouse_  option:selected").text();

    if ($buildingVal != '0') {
        $spanTipPanel.text("").append($spanText + " " + $building);
    }
    if ($unitVal != '0') {
        $spanTipPanel.text("").append($spanText + " " + $building + $unit);
    }
    if ($houseVal != '0') {
        $spanTipPanel.text("").append($spanText + " " + $building + $unit + $house);
    }
}

/**
 * 确认选择的业主地址
 * @param ref
 * @returns {boolean}
 */
function affirmChoose(ref) {
    var $houseVal = $("#" + ref + "ComboxHouse_  option:selected").val();
    if ($houseVal == '0') {
        alertMsg.error("请选择户信息");
        return false;
    }

    var $tipPanel = $("#" + ref + "TipPanel_");
    var $spanTipPanel = $("span", $tipPanel);
    $("#" + ref + "HouseId_").val($houseVal);
    $("#" + ref + "PublicAddress_").val($spanTipPanel.text().trim());
    $.pdialog.close("find_LookupHouse");
}

/**
 * 清空选择的内容
 * @param ref
 */
function clearAffirm(ref) {
    $("#" + ref + "HouseId_").val("");
    $("#" + ref + "PublicAddress_").val("");
}

/**
 * 获取所选小站的余额
 * @param val
 * @param ref
 * @param path
 */
function loadSiteAccount(val, ref, path) {
    var $AccountPanel_ = $("#" + ref + "AccountPanel_");
    if ($AccountPanel_[0]) {
        var url = path + "site/charge_loadSiteAccount.do";
        if (val != '0') {
            $.post(url, {communityId: val}, function (data) {
                var $selectText = $("#" + ref + "ComboxCommunityId_  option:selected").text();
                var text = $selectText + "服务小站当前余额为 " + data + " 元";
                $AccountPanel_.css("display", "").text("").append(text);
            }, "json");
        } else {
            $AccountPanel_.css("display", "none").text("");
        }
    }
}