<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<link href="<%=basePath %>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>/admin/css/theme.css" rel="stylesheet" type="text/css"/>

<script src="<%=basePath %>/admin/js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath %>/admin/js/pony.js" type="text/javascript"></script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置：帖子管理- 回复管理 - 列表</div>
		
	<div class="clear"></div>
	<form name="forum2" id="forum2" action="<%=basePath %>user/replyServlet?method=serach" method="post">
<table>
      <tr align="center">
           
            <td>
            
                <input class="input" maxlength="14" name="loginName" id="loginName"  type="text" value="${loginName}"/>  
            
            </td>
            <td>
            <input type="submit" name="submit" value="搜索回复者"/>
            </td>
      </tr>
</table>
</form>
</div>
<!-- 
<form name="forum2" id="forum2" action="<%=basePath %>user/topicServlet?method=serach" method="post">
<table>
      <tr align="center">
            <td>
                  选择板块
            </td>
            <td>
            <select name="forumId">
                  <c:forEach items="${mapList2}" var="category">
                      <optgroup label="${category.key.name }"></optgroup>
                  <c:forEach items="${category.value}" var="forum">
                  <c:choose>
                  <c:when test="${forum.id==forumm.id}">
                  <option value="${forum.id }" selected="selected">${forum.name }</option>
                  </c:when>
                  <c:otherwise>
                  <option value="${forum.id }" >${forum.name }</option>
                  </c:otherwise>
                  </c:choose>
                  
                  
                  </c:forEach>
                  
                  </c:forEach>
            
            
            </select>
            
            </td>
            <td>
            <input type="submit" name="submit" value="搜索"/>
            </td>
      </tr>
</table>
</form>
 -->
<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0" align="center">
<thead class="pn-lthead">
<tr>
	<th width="20px"><input type="checkbox" id="allCheck" value="checkbox" onclick="Pn.checkBox(&#39;ids&#39;,this.checked);"/></th>
	<th>回复ID</th>
	<th>回复所属帖子名称</th>
	<th>楼层</th>
	<th>回复所属板块名称</th>
	<th>回复所属分区名称</th>
	<th>帖子发布日期</th>
    <th>回复发布日期</th>
	<th>回复作者</th>

	<th>回复IP</th>
	<th>状态</th>
	<th>操作</th>
</tr>
</thead>
<c:forEach items="${replyList}" var="reply">
<tbody class="pn-ltbody">
<tr align="center">
	<td><input type="checkbox" name="ids" value="1"/></td>
	<td>${reply.key.id }</td>
	<td>
	<c:forEach items="${topicList}" var="topic">
	<c:if test="${topic.id==reply.key.topicId}">
	${topic.title }
	</c:if>
	</c:forEach>
	</td>
	<td>${reply.key.floor }</td>
	<td>
	<c:forEach items="${topicList}" var="topic">
	<c:if test="${topic.id==reply.key.topicId}">
	
	<c:forEach items="${forumList}" var="forum">
	<c:if test="${topic.forumId==forum.id}">
	${forum.name }
	</c:if>
	</c:forEach>
	
	</c:if>
	</c:forEach>
	</td>
	<td>
	<c:forEach items="${topicList}" var="topic">
	<c:if test="${topic.id==reply.key.topicId}">
	
	<c:forEach items="${forumList}" var="forum">
	<c:if test="${topic.forumId==forum.id}">
	
    <c:forEach items="${categoryList}" var="category">
    <c:if test="${category.id==forum.categoryId}">
    ${category.name }
    </c:if>
    
    </c:forEach>


	</c:if>
	</c:forEach>
	
	</c:if>
	</c:forEach>
	</td>
	<td>
	<c:forEach items="${topicList}" var="topic">
	<c:if test="${topic.id==reply.key.topicId}">
	${topic.pubTime }
	</c:if>
	</c:forEach>
	</td>
	<td>${reply.key.pubTime }</td>
	<td>${reply.value.loginName }</td>
	<td>${reply.key.ip }</td>
	<td>
	<c:if test="${reply.key.status==1}">
	正常
	</c:if>
	<c:if test="${reply.key.status==2}">
	屏蔽
	</c:if>
	</td>
	

	<td class="pn-lopt">
	<c:forEach items="${topicList}" var="topic">
	<c:if test="${topic.id==reply.key.topicId}">
	<a href="<%=basePath %>user/replyServlet?method=serachReplyContent&id=${topic.id}&floor=${reply.key.floor}" class="pn-loperator">查看</a>┆
	</c:if>
	</c:forEach>
	<a href="<%=basePath %>user/replyServlet?method=delete&replyId=${reply.key.id }" class="pn-loperator">删除</a>┆
	<a href="<%=basePath %>user/replyServlet?method=shield&replyId=${reply.key.id }" class="pn-loperator">回复屏蔽</a>┆
	<a href="<%=basePath %>user/replyServlet?method=show&replyId=${reply.key.id }" class="pn-loperator">回复显示</a>
	</td>
</tr>
</tbody>
</c:forEach>
</table>
<tr>
		   <td align="center" colspan="3">
		          <font style="color:red;font-size:24px">${msg }</font>
		   </td>
        </tr>
<input type="hidden" name="id"/>
<input type="hidden" name="pageNo" value="1"/>
<div class="pn-sp">
	
	<div align="center">
		共${replyCount }条 1/${replyCount10 } 页&nbsp;<a href="<%=basePath %>/user/replyServlet?method=serach&loginName=${loginName}" >首页</a>&nbsp;<a href="<%=basePath %>/user/replyServlet?method=serachNextMemberReply&count=${replyCount10}&loginName=${loginName}">上一页</a>&nbsp;<a href="<%=basePath %>/user/replyServlet?method=serachNextMemberReply&count=2&loginName=${loginName}">下一页</a>&nbsp;<a href="<%=basePath %>/user/replyServlet?method=serachNextMemberReply&count=${replyCount10}&loginName=${loginName}">末页</a>
	</div>
	<div class="clear"></div>
</div>

</div>

</body></html>