<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>jeecms-right</title>
<link href="<%=basePath %>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>/admin/css/theme.css" rel="stylesheet" type="text/css"/>

<script src="<%=basePath %>/admin/js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath %>/admin/js/pony.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	//$("#jvForm").validate();
});
</script></head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 首页 - 欢迎页</div>
	<div class="clear"></div>
</div>


<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="border:#c8c8e7 1px solid; border-top:0; margin-top:5px;">
  <tbody><tr>
    <td height="26" colspan="2" align="left" background="images/msg_bg.jpg">
	&nbsp;&nbsp;<img src="<%=basePath %>/admin/images/ico1.gif" border="0" align="absmiddle"/> <strong>系统属性</strong> </td>
  </tr>
  <tr>
    <td width="18%" height="25" align="right" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">JEECMS程序版本：</td>
    <td width="82%" align="left" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">JEEBBS V1.0 Beta版 【<a href="http://bbs.jeecms.com/" target="_blank">查看最新版本</a>】</td>
  </tr>
  <tr style="background-color:#F7F8FA">
    <td height="25" align="right" style="border-bottom:#cccccc 1px dashed;">操作系统版本：</td>
    <td align="left" style="border-bottom:#cccccc 1px dashed;">
    	Windows XP 5.1
    </td>
  </tr>
  <tr>
    <td height="25" align="right" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">操作系统类型：</td>
    <td align="left" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">
		x86 32位
	</td>
  </tr>
  <tr>
    <td height="25" align="right" bgcolor="#F7F8FA" style="border-bottom:#cccccc 1px dashed;">用户,目录,临时目录：</td>
    <td align="left" bgcolor="#F7F8FA" style="border-bottom:#cccccc 1px dashed;">
		Administrator, D:\apache-tomcat-6.0.29\bin, D:\apache-tomcat-6.0.29\temp
	</td>
  </tr>
  <tr>
    <td height="25" align="right" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">JAVA运行环境：</td>
    <td align="left" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">
		Java(TM) SE Runtime Environment 1.6.0_14-b08
	</td>
  </tr>
  <tr>
    <td height="25" align="right" bgcolor="#F7F8FA" style="border-bottom:#cccccc 1px dashed;">JAVA虚拟机：</td>
    <td align="left" bgcolor="#F7F8FA" style="border-bottom:#cccccc 1px dashed;">
		Java HotSpot(TM) Client VM 14.0-b16
	</td>
  </tr>
  <tr>
    <td height="25" align="right" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">剩余内存/总内存：</td>
    <td align="left" bgcolor="#FFFFFF" style="border-bottom:#cccccc 1px dashed;">8.14/24.07MB</td>
  </tr>
</tbody></table>
</div>

</body></html> 