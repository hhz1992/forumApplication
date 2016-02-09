<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title></title>
<link href="<%=basePath %>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>/admin/css/theme.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>/admin/js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath %>/admin/js/pony.js" type="text/javascript"></script>
<script type="text/javascript">
function check()
{
    var loginName=document.getElementById("loginName").value;
    if(loginName==null||loginName=="")
    {
        alert("请输入用户名");
        document.form1.loginName.focus();
        return false;
    }
    var password=document.getElementById("password").value;
    if(password==null||password=="")
    {
        alert("请输入密码");
        document.form1.password.focus();
        return false;
    }
    var realName=document.getElementById("realName").value;
    if(realName==null||realName=="")
    {
        alert("请输入真实姓名");
        document.form1.realName.focus();
        return false;
    }
    var mobile=document.getElementById("mobile").value;
    if(mobile==null||mobile=="")
    {
        alert("请输入手机号码");
        document.form1.mobile.focus();
        return false;
    }

}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 管理员 - 管理员管理 - 修改管理员</div>
	<form class="ropt" action="http://localhost:8080/wtf_bbs//admin/managerServlet?method=managerList" method="post">
		<input type="submit" value="返回列表" />
	</form>
	<div class="clear"></div>
</div>
<form method="post" action="<%=basePath %>admin/managerServlet?method=update" id="form1" name="from1" onsubmit="return check();">

<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tbody>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>请输入登录名称：</td><td width="80%" class="pn-fcontent">
	<input type="text" maxlength="100"  name="loginName" id="loginName" class="required" size="40" value="${manager.loginName }"/>
	</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>登录密码：</td>
	<td width="80%" class="pn-fcontent">
	<input type="password" maxlength="20" id="password" name="password" class="required"  value="${manager.password }"/>
	</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>真实姓名：</td>
	<td width="80%" class="pn-fcontent">
	<input type="text" id="realName" name="realName" class="required digits"  value="${manager.realName }"/> 
	<span class="pn-fhelp"></span>
	</td>
</tr>
<tr>
<td width="20%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>手机号码：</td>
<td width="80%" class="pn-fcontent"><input type="text"  name="mobile" id="mobile" class="required digits"  value="${manager.mobile }"/> 
<span class="pn-fhelp"></span>
</td>
</tr>
<tr>
<td width="20%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>状态：</td>
<td width="80%" class="pn-fcontent">
<c:if test="${manager.state==1}">
<input type="radio"  name="state"   value="1" checked="checked"/> 正常&nbsp;
<input type="radio"  name="state"   value="2"/> 禁用&nbsp;
<input type="radio"  name="state"   value="3"/> 离职
</c:if>
<c:if test="${manager.state==2}">
<input type="radio"  name="state"   value="1" /> 正常&nbsp;
<input type="radio"  name="state"   value="2" checked="checked"/> 禁用&nbsp;
<input type="radio"  name="state"   value="3" /> 离职
</c:if>
<c:if test="${manager.state==3}">
<input type="radio"  name="state"   value="1" /> 正常&nbsp;
<input type="radio"  name="state"   value="2"/> 禁用&nbsp;
<input type="radio"  name="state"   value="3" checked="checked"/> 离职
</c:if>

<span class="pn-fhelp"></span>
</td>
</tr>
<tr><td colspan="2" class="pn-fbutton">
<input type="submit" value="修改"/> &nbsp; <input type="reset" value="重置"/></td>
</tr></tbody></table>
<input type="hidden" name="id" value="${manager.id }"/>
</form>
</div>

</body></html>