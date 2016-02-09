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
	<div class="rpos">当前位置： 管理员 - 管理员管理 - 列表</div>
		<input type="button" value="添加管理员" onclick="window.location.href='<%=basePath %>admin/manager/addManager.jsp'"/>
	<div class="clear"></div>
</div>
<form id="tableForm" method="post" onsubmit="return _validateBatch();" action="<%=basePath %>admin/managerServlet?method=serach">
<div class="pn-sp">
	<table align="center">
		<tr align="center">
			<td>
				登录名称:
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
	<th>登录名</th>
	<th>真实姓名</th>
	<th>登录次数</th>
	<th>创建时间</th>
	<th>最后登录时间</th>
	<th>最后登录IP</th>
	<th>状态</th>
	<th>操作</th>
</tr>
</thead>
<c:forEach items="${managerList}" var="manager">
<tbody class="pn-ltbody">
<tr align="center">
	<td><input type="checkbox" name="ids" value="1"/></td>
	<td>${manager.id }</td>
	<td>${manager.loginName }</td>
	<td>${manager.realName}</td>
	<td>${manager.loginNum }</td>
	<td>${manager.createTime }</td>
	<td>${manager.lastLoginTime }</td>
	<td>${manager.ip }</td>
	<td>
		<c:if test="${manager.state==1}">
			正常
		</c:if>
		<c:if test="${manager.state==2}">
			禁用
		</c:if>
		<c:if test="${manager.state==3}">
			离职
		</c:if>
	</td>
	<td class="pn-lopt"><a href="<%=basePath %>admin/managerServlet?method=toUpdate&id=${manager.id }" class="pn-loperator">修改</a>┆<a href="<%=basePath %>admin/managerServlet?method=delete&id=${manager.id }" class="pn-loperator">删除</a></td>
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
	<t:pager pageSize="${pm.pageSize}" pageNo="${pm.pageNo}" url="${pageContext.request.contextPath}/admin/managerServlet?method=serach" recordCount="${pm.recordCount}"/>
	</div>
	<div class="clear"></div>
</div>

</div>

</body></html>