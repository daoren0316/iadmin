/**
 * 增加附件选择框
 * @param rel
 * @private
 */
function _addFile(rel) {
    // 获取附件上传域
    var $panel = $("#" + rel + "AttachPanel");
    var iptNum = $("input", $panel).length;
    if (iptNum < 5) {
        var $htm = "<span><input type=\"file\" name=\"image\" /></span>";
        $panel.append($htm);
    } else
        alertMsg.warn("最多只能添加5个图片");
}

/**
 * 减少附件选择框
 * @param rel
 * @private
 */
function _delFile(rel) {
    // 获取附件上传域
    var $panel = $("#" + rel + "AttachPanel");
    var iptNum = $("input", $panel).length;
    if (iptNum > 1)
        $("span", $panel).last().remove();
}

