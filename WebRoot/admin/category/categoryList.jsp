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
	<div class="rpos">当前位置： 管理员 - 分区管理 - 列表</div>
		<input type="button" value="添加分区" onclick="window.location.href='<%=basePath %>/admin/category/addCategory.jsp'"/>
	<div class="clear"></div>
</div>
<form id="form1" method="post" onsubmit="return _validateBatch();" action="<%=basePath %>user/categoryServlet?method=serach">
<div class="pn-sp">
	<table align="center">
		<tr align="center">
			<td>
				分区名称:
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
	<th>分区名</th>
	<th>分区号</th>
	<th>状态</th>
	<th>操作</th>
</tr>
</thead>
<c:forEach items="${pm.datas}" var="category">
<tbody class="pn-ltbody">
<tr align="center">
	<td><input type="checkbox" name="ids" value="1"/></td>
	<td>${category.id }</td>
	<td>${category.name }</td>
	<td>${category.sortNo}</td>
	<td>
		<c:if test="${category.state==1}">
			正常
		</c:if>
		<c:if test="${category.state==2}">
			不可用
		</c:if>
		
	</td>
	<td class="pn-lopt"><a href="<%=basePath %>user/categoryServlet?method=toUpdate&id=${category.id }" class="pn-loperator">修改</a>┆<a href="<%=basePath %>user/categoryServlet?method=delete&id=${category.id }" class="pn-loperator">删除</a></td>
</tr>
</tbody>
</c:forEach>
<tr>
		   <td align="center" colspan="3">
		          <font style="color:red;font-size:24px">${msg }</font>
		   </td>
        </tr>
</table>

<input type="hidden" name="id"/>
<input type="hidden" name="pageNo" value="1"/>
<div class="pn-sp">
	
	<div align="center">
		<t:pager pageSize="${pm.pageSize}" pageNo="${pm.pageNo}" url="${pageContext.request.contextPath}/user/categoryServlet?method=categoryList" recordCount="${pm.recordCount}"/>
	</div>
	<div class="clear"></div>
</div>

</div>

</body></html>