<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<h2 class="contentTitle">财务打款</h2>

<div class="pageContent">

    <form method="post" action="<%=basePath%>admin/rite_insert.do" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone)" id="rite_query">
        <div class="pageFormContent nowrap" layoutH="97">
            <fieldset>
                <legend>&nbsp;&nbsp;金额统计&nbsp;&nbsp;</legend>
                <dl>
                    <dt>收款商户：</dt>
                    <dd>
                        <p style="width: 500px;padding: 0;">
                            <input name="shopId" id="shopId" type="hidden">
                            <input class="required" name="shopName" id="shopName" type="text"
                                   suggestFields="shopName"
                                   suggestUrl="javascript:;" lookupGroup="" readonly="readonly"/></p>

                        <p style="width: 500px;padding: 0;">
                            <a class="btnLook" href="<%=basePath%>site/pos_lookupShop.do" maxable="false"
                               rel="add_lookupShop" width="750" height="500"
                               lookupGroup="">查找带回 - 收款商户</a>
                            <span class="info">查找带回 - 收款商户</span></p>
                    </dd>
                </dl>

                <dl>
                    <dt>统计开始时间：</dt>
                    <dd>
                        <input class="required" name="startTime" id="startTime" type="text"
                               readonly="readonly"
                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})"/>
                    </dd>
                </dl>

                <dl>
                    <dt>统计结束时间：</dt>
                    <dd>
                        <input class="required" name="endTime" id="endTime" type="text"
                               readonly="readonly"
                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})"/>
                    </dd>
                </dl>
            </fieldset>

            <fieldset>
                <legend>&nbsp;&nbsp;确认打款&nbsp;&nbsp;</legend>
                <dl>
                    <dt>需打款金额：</dt>
                    <dd>
                        <p style="width: 500px;padding: 0;">
                            <input type="text" class="required" name="money" id="money" readonly="readonly"/>
                        </p>

                        <p style="width: 500px;padding: 0;">
                            <a class="button" style="padding:0px 5px" href="javascript:;"
                               id="queryButton"><span>查询</span></a>
                            <span class="info"> 查询需打款金额</span>
                        </p>
                    </dd>
                </dl>
                <dl>
                    <dt>备注：</dt>
                    <dd>
                        <textarea name="note" cols="80" rows="3" maxlength="200"></textarea>
                    </dd>
                </dl>
            </fieldset>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonDisabled" id="buttonActive">
                        <div class="buttonContent">
                            <button type="submit" id="submitButton" disabled="disabled">确认打款</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(function () {
        $("#queryButton").click(function () {
            var $pgc = $(".pageFormContent");
            var shopId = $("#shopId", $pgc).val();
            var shopName = $("#shopName", $pgc).val();
            var startTime = $("#startTime", $pgc).val();
            var endTime = $("#endTime", $pgc).val();
            // 验证商户编号是否为空
            if (checkStr(shopId) && checkStr(shopName) && checkStr(startTime) && checkStr(endTime)) {
                $.post("admin/rite_query.do",
                        {shopId: shopId, shopName: shopName, startTime: startTime, endTime: endTime},
                        function (data) {
                            $("#money").val(data);
                            var $ba = $("#buttonActive");
                            if (data != "0" && data != null) {
                                $ba.removeClass("buttonDisabled").addClass("buttonActive");
                                $("#submitButton", $ba).removeAttr("disabled");
                            }
                        }, "json");
            } else
                $("#rite_query").submit();
        });
    });

    /**
     * 验证字符串是否为空
     * @param obj
     * @returns {boolean}
     */
    function checkStr(obj) {
        if (obj == null || obj == "")
            return false;
        else
            return true;
    }
</script>