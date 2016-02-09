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
	<div class="rpos">当前位置： 管理员 - 会员管理 - 设置版主</div>
	
	<div class="clear"></div>
</div>
<form method="post" action="<%=basePath %>user/memberManagerSerlet?method=setIdentity" id="form1" name="from1" onsubmit="return check();">

<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tbody>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>会员登录名称：</td><td width="80%" class="pn-fcontent">
	<input type="text" maxlength="100"  name="name" id="name" disabled="disabled" class="required" size="40" value="${member.loginName }"/>
	</td>
</tr>

<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>会员登录昵称：</td><td width="80%" class="pn-fcontent">
	<input type="text" maxlength="100"  name="nickName" id="nickName" disabled="disabled" class="required" size="40" value="${member.nickName }"/>
	</td>
</tr>


<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>会员注册时间：</td><td width="80%" class="pn-fcontent">
	<input type="text" maxlength="100"  name="regTime" id="regTime" disabled="disabled" class="required" size="40" value="${member.regTime }"/>
	</td>
</tr>




<tr>
<td width="20%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>状态：</td>
<td width="80%" class="pn-fcontent">
<c:if test="${member.status==1}">
<input type="radio"  name="state"   value="1" checked="checked" disabled="disabled"/> 正常&nbsp;
<input type="radio"  name="state"   value="2" disabled="disabled"/> 锁定&nbsp;
<input type="radio"  name="state"   value="3" disabled="disabled"/> 屏蔽&nbsp;
<input type="radio"  name="state"   value="4" disabled="disabled"/> 禁言
</c:if>
<c:if test="${member.status==2}">
<input type="radio"  name="state"   value="1" disabled="disabled"/> 正常&nbsp;
<input type="radio"  name="state"   value="2" checked="checked" disabled="disabled"/> 锁定&nbsp;
<input type="radio"  name="state"   value="3" disabled="disabled"/> 屏蔽&nbsp;
<input type="radio"  name="state"   value="4" disabled="disabled"/> 禁言
</c:if>
<c:if test="${member.status==3}">
<input type="radio"  name="state"   value="1" disabled="disabled"/> 正常&nbsp;
<input type="radio"  name="state"   value="2" disabled="disabled"/> 锁定&nbsp;
<input type="radio"  name="state"   value="3" checked="checked" disabled="disabled"/> 屏蔽&nbsp;
<input type="radio"  name="state"   value="4" disabled="disabled"/> 禁言
</c:if>
<c:if test="${member.status==4}">
<input type="radio"  name="state"   value="1" disabled="disabled"/> 正常&nbsp;
<input type="radio"  name="state"   value="2" disabled="disabled"/> 锁定&nbsp;
<input type="radio"  name="state"   value="3" disabled="disabled"/> 屏蔽&nbsp;
<input type="radio"  name="state"   value="4" checked="checked" disabled="disabled"/> 禁言
</c:if>
<span class="pn-fhelp"></span>
</td>
</tr>
<tr>
<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>选择板块：</td><td width="80%" class="pn-fcontent">
<select name="forumId">
    <c:forEach items="${mapList}" var="category">
       <optgroup label="${category.key.name}"></optgroup>
       <c:forEach items="${category.value}" var="forum2">
       <option value="${forum2.id }">${forum2.name }</option>
       </c:forEach>
    </c:forEach>
</select>
</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>设置身份：</td><td width="80%" class="pn-fcontent">
	<c:if test="${member.memberIdentity==1}">
	<input type="radio" name="memberIdentity" value="1" checked="checked"/>会员&nbsp;
	<input type="radio" name="memberIdentity" value="2" />版主&nbsp;
	</c:if>
	<c:if test="${member.memberIdentity==2}">
	<input type="radio" name="memberIdentity" value="1" />会员&nbsp;
	<input type="radio" name="memberIdentity" value="2" checked="checked"/>版主&nbsp;
	</c:if>
	<c:if test="${member.memberIdentity==0}">
	<input type="radio" name="memberIdentity" value="1" checked="checked"/>会员&nbsp;
	<input type="radio" name="memberIdentity" value="2" />版主&nbsp;
	</c:if>
	</td>
</tr>

<tr>
		   <td align="center" colspan="3">
		          <font style="color:red;font-size:24px">${msg }</font>
		   </td>
        </tr>
<tr><td colspan="2" class="pn-fbutton">
<input type="submit" value="修改"/> &nbsp; <input type="reset" value="重置"/></td>
</tr></tbody></table>
<input type="hidden" name="id" value="${member.id }"/>
</form>
</div>

</body></html>