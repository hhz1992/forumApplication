<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    var name=document.getElementById("name").value;
    if(name==null||name=="")
    {
        alert("请输入用户名");
        document.form1.name.focus();
        return false;
    }
    var sortNo=document.getElementById("sortNo").value;
    if(sortNo==null||sortNo=="")
    {
        alert("请输入密码");
        document.form1.sortNo.focus();
        return false;
    }
   

}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 管理员 -分区管理 - 添加分区</div>
	<form class="ropt" method="post" action="<%=basePath %>user/categoryServlet?method=categoryList" name="forum2" id="forum2">
		<input type="submit" value="返回列表" onclick="window.location.href='<%=basePath %>user/categoryServlet?method=categoryList'"/>
	</form>
	<div class="clear"></div>
</div>
<form method="post" action="<%=basePath %>user/categoryServlet?method=addCategory" id="form1" name="from1" onsubmit="return check();">

<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tbody>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>请输入分区名称：</td><td width="80%" class="pn-fcontent">
	<input type="text" maxlength="100"  name="name" id="name" class="required" size="40"/>
	</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>排序号码：</td>
	<td width="80%" class="pn-fcontent">
	<input type="text" maxlength="20" id="sortNo" name="sortNo" class="required" />
	</td>
</tr>
<tr>
		   <td align="center" colspan="3">
		          <font style="color:red;font-size:24px">${msg }</font>
		   </td>
        </tr>
<tr><td colspan="2" class="pn-fbutton">
<input type="submit" value="提交"/> &nbsp; <input type="reset" value="重置"/></td>
</tr></tbody></table>
</form>
</div>

</body></html>