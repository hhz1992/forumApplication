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
	<div class="rpos">当前位置： 管理员 - 会员管理 - 列表</div>
		<input type="button" value="添加会员" onclick="window.location.href='<%=basePath %>/registe.jsp'"/>
	<div class="clear"></div>
</div>
<form id="tableForm" method="post" onsubmit="return _validateBatch();" action="<%=basePath %>user/memberManagerSerlet?method=serach">
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
	<th>昵称</th>
	<th>E-mail</th>
	<th>创建时间</th>
	<th>最后登录时间</th>
	<th>最后登录IP</th>
	<th>身份</th>
	<th>所属板块</th>
	<th>状态</th>
	<th>操作</th>
</tr>
</thead>
<c:forEach items="${pm.datas}" var="member">
<tbody class="pn-ltbody">
<tr align="center">
	<td><input type="checkbox" name="ids" value="1"/></td>
	<td>${member.id }</td>
	<td>${member.loginName }</td>
	<td>${member.nickName}</td>
	<td>${member.email }</td>
	<td>${member.regTime }</td>
	<td>${member.lastVisitTime }</td>
	<td>${member.lastIp }</td>
	<td>
	    <c:if test="${member.memberIdentity==1}">
	    会员
	    </c:if>
	    <c:if test="${member.memberIdentity==2}">
	    版主
	    </c:if>
	</td>
	<td>
	<c:if test="${member.memberIdentity==2}">
	   ${member.forumName } 
	</c:if>
	 <c:if test="${member.memberIdentity==1}">
	
	 </c:if>
	</td>
	
	<td>
		<c:if test="${member.status==1}">
			正常
		</c:if>
		<c:if test="${member.status==2}">
			锁定
		</c:if>
		<c:if test="${member.status==3}">
			屏蔽
		</c:if>
		<c:if test="${member.status==4}">
			禁言
		</c:if>
	</td>
	<td class="pn-lopt"><a href="<%=basePath %>user/memberManagerSerlet?method=toUpdate&id=${member.id }" class="pn-loperator">修改</a>┆<a href="<%=basePath %>user/memberManagerSerlet?method=setConfig&id=${member.id }" class="pn-loperator">设置</a>┆<a href="<%=basePath %>user/memberManagerSerlet?method=delete&id=${member.id }" class="pn-loperator">删除</a></td>
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
		<t:pager pageSize="${pm.pageSize}" pageNo="${pm.pageNo}" url="${pageContext.request.contextPath}/user/memberManagerSerlet?method=serach" recordCount="${pm.recordCount}"/>

	</div>
	<div class="clear"></div>
</div>

</div>

</body></html>