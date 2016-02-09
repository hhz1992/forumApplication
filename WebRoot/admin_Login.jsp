<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>JEEBBS后台登录</title>
<link href="<%=basePath %>css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>css/theme.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>js/keyboard/jquery-ui-1.8.16.custom.css" rel="stylesheet"/>
    <link href="<%=basePath %>js/keyboard/keyboard.css" type="text/css"  rel="stylesheet" />
    <script type="text/javascript" src="<%=basePath %>js/keyboard/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/keyboard/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/keyboard/jquery.keyboard.extension-typing.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/keyboard/jquery.keyboard.js"></script>
	<script type="text/javascript">
	function change(img){
		img.src = '<%=basePath%>checkcode.png?d=' + new Date().getTime();
	}
	$(document).ready(function() {
        $('#password').keyboard({
            openOn: null,
            stayOpen: true,
            layout: 'qwerty'
        }).addTyping();
        
        $('#passwd').click(function() {
            $('#password').getkeyboard().reveal();
        });

        
        $(".logininput").blur(function() {
            if ($(this).val() == "") {
                $(this).css("border-color", "red");
            } else{
                $(this).css("border-color", "#D9D6C4");
            }
        });
	});
	</script>
<script type="text/javascript">

function check(){
	var loginName=document.getElementById("loginName").value;
	if(loginName==null||loginName==""){
		alert(" 请输入管理员登录名 !");
		document.form1.loginName.focus();
		return false;
	}
	var password=document.getElementById("password").value;
	if(password==null||password==""){
		alert(" 请输入管理员密码 !");
		document.form1.password.focus();
		return false;
	}
	var checkCode=document.getElementById("checkCode").value;
	if(checkCode==null||checkCode=="")
	{
	    alert("请输入验证码！");
	    document.form1.checkCode.focus();
	    return false;
	}

}

</script>
<style type="text/css">
body{background-color:#EEF5FA;}
.input{
	border: #cccccc 1px solid; 
	height:16px;
	width:150px;
}
</style>
</head><body>
<form id="form1" name="form1" action="<%=basePath %>mng/managerLoginServlet?method=mngLogin" method="post" onsubmit="return check();">
<table align="center" border="0" cellpadding="0" cellspacing="0" 
width="666">
  <tbody><tr>
    <td><img src="<%=basePath %>images/login_02.jpg" alt="" height="500" width="345"/></td>
    <td align="center" background="<%=basePath%>images/login_03.jpg" 
width="370"><table border="0" cellpadding="0" cellspacing="0" 
width="98%">
      <tbody><tr>
        <td align="right" height="85">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="right" height="30" width="25%">用户名：</td>
        <td align="left" width="75%">
		<input class="input required" size="20" name="loginName" id="loginName"
maxlength="20"  type="text"/>
		</td>
		<td>&nbsp;</td>
      </tr>
      <tr>
        <td align="right" height="30">密　码：</td>
        <td align="left"><input class="input required" size="20" 
name="password" id="password" maxlength="20"  type="password"/>
 
</td>
<td align="left"> <img id="passwd" class="tooltip" alt="点击打开软键盘" title="点击打开软键盘" src="<%=basePath %>js/keyboard/keyboard.png" /></td>
      </tr>
      <tr>
        <td align="right" height="30">验证码：</td>
        <td align="left"><input class="input required" size="20" 
name="checkCode" id="checkCode" maxlength="20" type="text"/>

</td>
<td>
	<img src="<%=basePath %>checkcode.png" class="tooltip" alt="重换一张" title="重换一张" style="cursor: pointer;" onclick="change(this)"/>
</td>
      </tr>
      <tr>
        <td colspan="2" align="center" height="60">
		<input src="<%=basePath%>/images/login.jpg" type="image"/>
	
		</td>
		<td></td>
		</tr>
		<tr>
		   <td colspan="3">
		          <font style="color:red;font-size:20">${msg }</font>
		   </td>
        </tr>
    </tbody></table>
	</td>
  </tr>
</tbody></table>
</form>
<script language="javascript">
$(function(){
});
</script></body></html>