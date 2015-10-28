<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<h2 class="contentTitle">编辑团购信息</h2>

<div class="pageContent">
    <form method="post" action="<%=basePath%>site/commodity_update.do" class="pageForm required-validate"
          onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>商品标题：</dt>
                <dd>
                    <input type="text" name="commodityTitle" size="50" class="required" maxlength="50"
                           value="${commodity.commodityTitle}"/>
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
                    <div id="uppCommodityAttachPanel" class="divPanel">
                        <span><input type="file" name="image"/></span></div>

                    <div class="divPanel">
                        <a href="javascript:;" onclick="_addFile('uppCommodity')" title="添加">
                            <img src="<%=basePath%>images/file_add.png"/></a>
                        <a href="javascript:;" onclick="_delFile('uppCommodity')" title="删除">
                            <img src="<%=basePath%>images/file_del.png"/></a></div>

                    <div class="divPanel">
                        <span class="info">最佳格式为 660px * 400px，大小100k以内的jpg，png类型的图片</span></div>
                </dd>
            </dl>

            <dl>
                <dt>市场价：</dt>
                <dd>
                    <input type="text" name="marketPrice" size="10" class="required number" maxlength="10"
                           value="${commodity.marketPrice}"/>
                </dd>
            </dl>

            <dl>
                <dt>团购价：</dt>
                <dd>
                    <input type="text" name="price" size="10" class="required number" maxlength="10"
                           value="${commodity.price}"/>
                </dd>
            </dl>

            <dl>
                <dt>开始时间：</dt>
                <dd>
                    <input name="startTime" id="startDate" type="text" readonly="readonly" class="required"
                           onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')||\'2020-10-01\'}'})"
                           value="<fmt:formatDate value="${commodity.startTime}" pattern="yyyy-MM-dd"/>"/>
                </dd>
            </dl>

            <dl>
                <dt>结束时间：</dt>
                <dd>
                    <input name="endTime" id="endDate" type="text" readonly="readonly" class="required"
                           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'2020-10-01'})"
                           value="<fmt:formatDate value="${commodity.endTime}" pattern="yyyy-MM-dd"/>"/>
                </dd>
            </dl>

            <dl>
                <dt>总数量：</dt>
                <dd>
                    <input type="text" name="totalAmount" size="10" class="required digits" maxlength="10"
                           value="${commodity.totalAmount}"/>
                </dd>
            </dl>

            <dl>
                <dt>最多购买件数：</dt>
                <dd>
                    <input type="text" name="amountLimit" size="10" class="required digits" maxlength="10"
                           value="${commodity.amountLimit}"/>
                </dd>
            </dl>

            <dl>
                <dt>是否随时退款：</dt>
                <dd>
                    <input type="radio" name="isAnytimeDawback" value="true"
                           <c:if test="${commodity.isAnytimeDawback}">checked="checked"</c:if>/>支持
                    <input type="radio" name="isAnytimeDawback" value="false"
                           <c:if test="${!commodity.isAnytimeDawback}">checked="checked"</c:if>/>不支持
                </dd>
            </dl>

            <dl>
                <dt>是否超时退款：</dt>
                <dd>
                    <input type="radio" name="isTimeoutDawback" value="true"
                           <c:if test="${commodity.isTimeoutDawback}">checked="checked"</c:if> />支持
                    <input type="radio" name="isTimeoutDawback" value="false"
                           <c:if test="${!commodity.isTimeoutDawback}">checked="checked"</c:if>/>不支持
                </dd>
            </dl>

            <dl>
                <dt>购买须知：</dt>
                <dd>
                    <textarea name="notice" cols="80" rows="5">${commodity.notice}</textarea>
                </dd>
            </dl>

            <dl>
                <dt>商品介绍：</dt>
                <dd>
                    <textarea name="commodityDesc" class="required" cols="80"
                              rows="10">${commodity.commodityDesc}</textarea>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <input type="hidden" name="commodityId" value="${commodity.commodityId}">
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