<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0056)http://localhost:8080/jeebbs/admin/core/user/Com_edit.do -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title></title>
<link href="<%=basePath %>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>/admin/css/theme.css" rel="stylesheet" type="text/css"/>

<script src="<%=basePath %>/admin/js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath %>/admin/js/pony.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	//$("#jvForm").validate();
});
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 核心功能 - 用户管理 - 修改</div>
	<form class="ropt" method="post">
		<input type="button" value="返回列表" onclick="history.back();"/>
	</form>
	<div class="clear"></div>
</div>
<form method="post" action="http://localhost:8080/jeebbs/admin/core/user/Com_update.do" id="jvForm">
<input type="hidden" name="pageNo" value="1"/>
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tbody><tr>
<td width="15%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>登录名：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="text" rname="username" rvalue="test2" value="test2" name="bean.loginName" vld="{required:true,rangelength:[2,20],username:true,remote:&#39;checkUsername.do&#39;,messages:{remote:&#39;用户名已存在！&#39;}}"/></td><td width="15%" class="pn-flabel pn-flabel-h">真实姓名：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="text" maxlength="50" value="" name="bean.realName"/></td></tr>
<tr><td width="15%" class="pn-flabel pn-flabel-h">密码：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="password" id="password" maxlength="32" name="bean.password"/>
</td><td width="15%" class="pn-flabel pn-flabel-h">确认密码：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="password" equalto="#password"/>
</td></tr>
<tr><td width="15%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>电子邮箱：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="text" maxlength="100" rname="email" rvalue="owyn211@163.com" value="owyn211@163.com" name="bean.email" vld="{required:true,email:true,remote:&#39;checkEmail.do&#39;,messages:{remote:&#39;email已经被使用！&#39;}}"/></td><td width="15%" class="pn-flabel pn-flabel-h">邮编：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="text" maxlength="20" value="" name="bean.zip"/></td></tr>
<tr><td width="15%" class="pn-flabel pn-flabel-h">地址：</td><td colspan="3" width="85%" class="pn-fcontent"><input type="text" maxlength="250" value="" name="bean.address" size="60"/></td></tr>
<tr><td width="15%" class="pn-flabel pn-flabel-h">电话：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="text" maxlength="250" value="" name="bean.tel"/></td><td width="15%" class="pn-flabel pn-flabel-h">手机：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="text" maxlength="250" value="" name="bean.mobile"/></td></tr>
<tr><td width="15%" class="pn-flabel pn-flabel-h">性别：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="radio" id="_bean.gender_0" value="true" name="bean.gender"/><label for="_bean.gender_0">男</label> <input type="radio" id="_bean.gender_1" value="false" name="bean.gender"/><label for="_bean.gender_1">女</label></td>
<td width="15%" class="pn-flabel pn-flabel-h">禁用：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="radio" id="_bean.disabled_0" value="true" name="bean.disabled"/><label for="_bean.disabled_0">是</label> <input type="radio" id="_bean.disabled_1" value="false" checked="checked" name="bean.disabled"/><label for="_bean.disabled_1">否</label></td>
</tr>
<tr><td width="15%" class="pn-flabel pn-flabel-h">创建时间：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="text" value="2009-08-13 17:44:29" name="bean.createTime" disabled="disabled"/></td><td width="15%" class="pn-flabel pn-flabel-h">登录次数：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="text" value="1" name="bean.loginCount" disabled="disabled"/></td></tr>
<tr><td width="15%" class="pn-flabel pn-flabel-h">最后登录时间：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="text" value="2009-08-13 17:44:29" name="bean.lastLoginTime" disabled="disabled"/></td><td width="15%" class="pn-flabel pn-flabel-h">最后登录IP：</td><td colspan="1" width="35%" class="pn-fcontent"><input type="text" value="127.0.0.1" name="bean.lastLoginIp" disabled="disabled"/></td></tr>
<tr><td colspan="4" class="pn-fbutton">
<input type="hidden" name="bean.id" value="4"/>
<input type="submit" value="提交"/> &nbsp; <input type="reset" value="重置"/></td>
</tr></tbody></table>
</form>
</div>

</body></html>