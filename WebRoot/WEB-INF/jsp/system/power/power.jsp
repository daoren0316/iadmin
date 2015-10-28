<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<script type="text/javascript">
    function selectedEle(id, param, rank) {
        $("#parentId").val(id);
        $("#parent").val(param);
        $("#parentRank").val(rank);
        $.pdialog.close("power");
    }
</script>

<div id="treeCode" style="overflow:auto; width: 100%; height: 100%; border:solid 1px #CCC; background:#FFF;">
    ${tree }
</div>
