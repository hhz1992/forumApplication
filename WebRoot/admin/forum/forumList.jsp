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
	<div class="rpos">当前位置：板块- 板块管理 - 列表</div>
		<input type="button" value="添加板块" onclick="window.location.href='<%=basePath %>user/forumManagerServlet?method=toAdd'"/>
	<div class="clear"></div>
</div>
<form id="form1" method="post" onsubmit="return _validateBatch();" action="<%=basePath %>user/forumManagerServlet?method=serach">
<div class="pn-sp">
	<table align="center">
		<tr align="center">
			<td>
				板块名称:
			</td>
			<td>
				<input type="text" name="loginName" id="loginName" value=""/>
			</td>
			
			<td>
				<input type="submit" value="查询" />
			</td>
			<td>
				<input type="reset" value="重置" />
			</td>
		</tr>
		
	</table>
	
</div>
</form>

<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0" align="center">
<thead class="pn-lthead">
<tr>
	<th width="20px"><input type="checkbox" id="allCheck" value="checkbox" onclick="Pn.checkBox(&#39;ids&#39;,this.checked);"/></th>
	<th>ID</th>
	<th>板块名称</th>
	<th>所属分区名称</th>
	<th>排序编号</th>
	<th>总主题数</th>
	<th>总文章数</th>
	
	
	<th>操作</th>
</tr>
</thead>
<c:forEach items="${pm.datas}" var="forum">
<tbody class="pn-ltbody">
<tr align="center">
	<td><input type="checkbox" name="ids" value="1"/></td>
	<td>${forum.id }</td>
	<td>${forum.name }</td>
	<td>${forum.categoryName}</td>
	<td>${forum.sortNo }</td>
	<td>${forum.topicCount }</td>
	<td>${forum.articleCount }</td>
	
	
	<td class="pn-lopt"><a href="<%=basePath %>user/forumManagerServlet?method=toUpdate&id=${forum.id }" class="pn-loperator">修改</a>┆<a href="<%=basePath %>user/forumManagerServlet?method=delete&id=${forum.id }" class="pn-loperator">删除</a></td>
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
		<t:pager pageSize="${pm.pageSize}" pageNo="${pm.pageNo}" url="${pageContext.request.contextPath}/user/forumManagerServlet?method=forumList" recordCount="${pm.recordCount}"/>
	</div>
	<div class="clear"></div>
</div>

</div>

</body></html>