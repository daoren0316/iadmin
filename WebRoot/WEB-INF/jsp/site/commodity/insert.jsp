<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">添加团购信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/commodity_insert.do" class="pageForm required-validate"
          onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>商品标题：</dt>
                <dd>
                    <input type="text" name="commodityTitle" size="50" class="required" maxlength="50"/>
                </dd>
            </dl>

            <dl>
                <dt>商品标题图片：</dt>
                <dd>
                    <div class="divPanel">
                        <span><input type="file" name="titleImage"/></span></div>

                    <div class="divPanel">
                        <span class="info">最佳格式为 550px * 300px，大小100k以内的jpg，png类型的图片</span></div>
                </dd>
            </dl>

            <dl>
                <dt>商品介绍图片：</dt>
                <dd>
                    <div id="addCommodityAttachPanel" class="divPanel">
                        <input type="file" name="image"/></div>

                    <div class="divPanel">
                        <a href="javascript:;" onclick="_addFile('addCommodity')" title="添加">
                            <img src="<%=basePath%>images/file_add.png"/></a>
                        <a href="javascript:;" onclick="_delFile('addCommodity')" title="删除">
                            <img src="<%=basePath%>images/file_del.png"/></a></div>

                    <div class="divPanel">
                        <span class="info">最佳格式为 660px * 400px，大小100k以内的jpg，png类型的图片</span></div>
                </dd>
            </dl>

            <dl>
                <dt>市场价：</dt>
                <dd>
                    <input type="text" name="marketPrice" size="10" class="required number" maxlength="10"/>
                </dd>
            </dl>

            <dl>
                <dt>团购价：</dt>
                <dd>
                    <input type="text" name="price" size="10" class="required number" maxlength="10"/>
                </dd>
            </dl>

            <dl>
                <dt>开始时间：</dt>
                <dd>
                    <input name="startTime" id="startDate" type="text" readonly="readonly" class="required"
                           onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})"/>
                </dd>
            </dl>

            <dl>
                <dt>结束时间：</dt>
                <dd>
                    <input name="endTime" id="endDate" type="text" readonly="readonly" class="required"
                           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"/>
                </dd>
            </dl>

            <dl>
                <dt>总数量：</dt>
                <dd>
                    <input type="text" name="totalAmount" size="10" class="required digits" maxlength="10"/>
                </dd>
            </dl>

            <dl>
                <dt>最多购买件数：</dt>
                <dd>
                    <input type="text" name="amountLimit" size="10" class="required digits" maxlength="10"/>
                </dd>
            </dl>

            <dl>
                <dt>是否随时退款：</dt>
                <dd>
                    <input type="radio" name="isAnytimeDawback" value="true" checked="checked"/>支持
                    <input type="radio" name="isAnytimeDawback" value="false"/>不支持
                </dd>
            </dl>

            <dl>
                <dt>是否超时退款：</dt>
                <dd>
                    <input type="radio" name="isTimeoutDawback" value="true" checked="checked"/>支持
                    <input type="radio" name="isTimeoutDawback" value="false"/>不支持
                </dd>
            </dl>

            <dl>
                <dt>购买须知：</dt>
                <dd>
                    <textarea name="notice" cols="80" rows="5"></textarea>
                </dd>
            </dl>

            <dl>
                <dt>商品介绍：</dt>
                <dd>
                    <textarea name="commodityDesc" class="required" cols="80" rows="10"></textarea>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">提交</button>
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