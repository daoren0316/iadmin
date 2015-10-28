<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
        共 <font style="color:#1B82FF;"><s:if test="pageInfo.totalCount==0">1</s:if><s:else><s:property value="pageInfo.totalPage" /></s:else> </font> 页 计 <font style="color:#1B82FF;"><s:property value="pageInfo.totalCount" /> </font>  条   当前第 <font style="color:#1B82FF;"><s:property value="pageInfo.toPage" /></font> 页
			
      <s:if test="pageInfo.toPage>1"> <%//--大于一页 --%>
        <a href="<%=request.getParameter("action")%>&pageInfo.toPage=1&pageInfo.pageSize=<s:property value="pageInfo.pageSize"/>">首页</a>
        <a href="<%=request.getParameter("action")%>&pageInfo.toPage=<s:property value="pageInfo.toPage-1"/>&pageInfo.pageSize=<s:property value="pageInfo.pageSize"/>">上页</a>
      </s:if> 	

     <s:iterator status="stat" value="{-3,-2,-1,0,1,2,3}" > 
         <s:if test="(pageInfo.toPage<4)">          
           <s:if test="#stat.index + 1 == pageInfo.toPage">
              &nbsp;<s:property value="#stat.index + 1" />
           </s:if>
           <s:elseif test="pageInfo.totalPage>=#stat.index + 1">
             &nbsp;<a href="<%=request.getParameter("action")%>&pageInfo.toPage=<s:property value="#stat.index + 1"/>&pageInfo.pageSize=<s:property value="pageInfo.pageSize"/>"><s:property value="#stat.index + 1" /></a>
           </s:elseif> 
         </s:if> 
         <s:elseif test="(pageInfo.toPage + top >0)&&(pageInfo.toPage + top <= pageInfo.totalPage)">
           <s:if test="pageInfo.toPage + top == pageInfo.toPage">
              &nbsp;<s:property value="pageInfo.toPage+top" />
           </s:if>
           <s:else>
            &nbsp;<a href="<%=request.getParameter("action")%>&pageInfo.toPage=<s:property value="pageInfo.toPage + top"/>&pageInfo.pageSize=<s:property value="pageInfo.pageSize"/>"><s:property value="pageInfo.toPage+top" /></a>                    
           </s:else>          
         </s:elseif>         
     </s:iterator>

     <s:if test="pageInfo.toPage<pageInfo.totalPage"> 
        &nbsp;<a href="<%=request.getParameter("action")%>&pageInfo.toPage=<s:property value="pageInfo.toPage+1"/>&pageInfo.pageSize=<s:property value="pageInfo.pageSize"/>">下页</a>
         &nbsp;<a href="<%=request.getParameter("action")%>&pageInfo.toPage=<s:property value="pageInfo.totalPage"/>&pageInfo.pageSize=<s:property value="pageInfo.pageSize"/>">尾页</a>
     </s:if>
     
