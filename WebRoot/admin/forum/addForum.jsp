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
    var name=document.getElementById("name").value;
    if(name==null||name=="")
    {
        alert("请输入板块名");
        document.form1.name.focus();
        return false;
    }
    var sortNo=document.getElementById("sortNo").value;
    if(sortNo==null||sortNo=="")
    {
        alert("请输入板块编号");
        document.form1.sortNo.focus();
        return false;
    }
    var keywords=document.getElementById("keywords").value;
    if(keywords==null||keywords=="")
    {
        alert("请输入板块关键字");
        document.form1.keywords.focus();
        return false;
    }
    var description=document.getElementById("description").value;
    if(description==null||description=="")
    {
        alert("请输入板块描述");
        document.form1.description.focus();
        return false;
    }
    var rule=document.getElementById("rule").value;
    if(rule==null||rule=="")
    {
        alert("请输入板块规则");
        document.form1.rule.focus();
        return false;
    }
  
   

}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 论坛 -板块管理 - 添加板块</div>
	<form class="ropt" method="post" action="<%=basePath %>user/forumManagerServlet?method=forumList" name="forum2" id="forum2">
		<input type="submit" value="返回列表"  onclick="window.location.href='<%=basePath %>user/forumManagerServlet?method=forumList'" />
	</form>
	<div class="clear"></div>
</div>
<form method="post" action="<%=basePath %>user/forumManagerServlet?method=addForum" id="form1" name="form1" onsubmit="return check();">

<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tbody>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>请选择分区：</td><td width="80%" class="pn-fcontent">
	<select name="categoryName">
	<c:forEach items="${categoryList}" var="category">
	<option value="${category.id }">${category.name }</option>
	</c:forEach>
	</select> 
	</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>板块名称：</td>
	<td width="80%" class="pn-fcontent">
	<input type="text" maxlength="20" id="name" name="name" class="required" />
	</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>排序编号：</td>
	<td width="80%" class="pn-fcontent">
	<input type="text" maxlength="20" id="sortNo" name="sortNo" class="required" />
	</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>关键字(SEO)：</td>
	<td width="80%" class="pn-fcontent">
	<input type="text" maxlength="20" id="keywords" name="keywords" class="required" />
	</td>
</tr>
<tr>
    <td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span> 描述：</td>
    <td width="80%" class="pn-fcontent"><textarea class="input" rows="4" cols="50" name="description" id="description" value=""></textarea> </td>
            
</tr> 
<tr>
    <td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span> 板块规则：</td>
    <td width="80%" class="pn-fcontent"><textarea class="input" rows="4" cols="50" name="rule" id="rule"></textarea>  </td>
            
</tr>
<tr>
		   <td align="center" colspan="3">
		          <font style="color:red;font-size:24px">${msg }</font>
		   </td>
        </tr>
<tr><td colspan="2" class="pn-fbutton">
<input type="submit" value="添加"/> &nbsp; <input type="reset" value="重置"/></td>
</tr></tbody></table>
</form>
</div>

</body></html>