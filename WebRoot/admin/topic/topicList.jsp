<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.tjitcast.com/jsp/tags" prefix="t" %>
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
	<div class="rpos">当前位置：帖子管理- 帖子管理 - 列表</div>
		
	<div class="clear"></div>
</div>
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
            <input type="submit" name="submit2" value="搜索"/>
            </td>
      </tr>
</table>
</form>

<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0" align="center">
<thead class="pn-lthead">
<tr>
	<th width="20px"><input type="checkbox" id="allCheck" value="checkbox" onclick="Pn.checkBox(&#39;ids&#39;,this.checked);"/></th>
	<th>ID</th>
	<th>帖子名称</th>
	<th>所属板块名称</th>
	<th>所属分区名称</th>
	<th>发布日期</th>
	<th>作者</th>
	<th>总回复数</th>
	<th>帖子IP</th>
	<th>人气</th>
	<th>状态</th>
	<th>类型</th>
	<th>操作</th>
</tr>
</thead>
<c:forEach items="${mapList}" var="topic">
<tbody class="pn-ltbody">
<tr align="center">
	<td><input type="checkbox" name="ids" value="1"/></td>
	<td>${topic.key.id }</td>
	<td>${topic.key.title }</td>
	<td>
	${topic.value.name }
	</td>
	
	<td>
	<c:forEach items="${categoryList}" var="category">
	<c:if test="${category.id==topic.value.categoryId}">
	${category.name }
	</c:if>
	</c:forEach>
	</td>
	
	<td>${topic.key.pubTime }</td>
	
	<td>
	<c:forEach items="${memberList}" var="member">
	<c:if test="${member.id==topic.key.memberId}">
	${member.loginName }
	</c:if>
	</c:forEach>
	</td>
	
	<td>${topic.key.replyCount }</td>
	<td>${topic.key.ip }</td>
	<td>${topic.key.visitCount }</td>
	<td>
         <c:if test="${topic.key.status==0}">
               普通
         </c:if>
         <c:if test="${topic.key.status==1}">
              普通
         </c:if>
         <c:if test="${topic.key.status==2}">
           移除   
         </c:if>
         <c:if test="${topic.key.status==3}">
            锁定  
         </c:if>
         <c:if test="${topic.key.status==4}">
             精华 
         </c:if>
    </td>
    <td>
         <c:if test="${topic.key.type==0}">
               普通
         </c:if>
         <c:if test="${topic.key.type==1}">
               置顶
         </c:if>
          <c:if test="${topic.key.type==2}">
               隐藏
         </c:if>
          <c:if test="${topic.key.type==3}">
               公告
         </c:if>
    </td>
	<td class="pn-lopt"><a href="<%=basePath %>user/topicServlet?method=toUpdate&id=${topic.key.id }" class="pn-loperator">修改</a>┆<a href="<%=basePath %>user/topicServlet?method=delete&id=${topic.key.id }" class="pn-loperator">删除</a></td>
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
		<t:pager pageSize="${pm.pageSize}" pageNo="${pm.pageNo}" url="${pageContext.request.contextPath}/user/topicServlet?method=topicList" recordCount="${pm.recordCount}"/>
	</div>
	<div class="clear"></div>
</div>

</div>

</body></html>