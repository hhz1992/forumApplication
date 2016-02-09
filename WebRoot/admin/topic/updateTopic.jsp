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
   
    
    
}
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 管理员 - 帖子管理 - 修改帖子状态</div>
	
	<div class="clear"></div>
</div>
<form method="post" action="<%=basePath %>user/topicServlet?method=update" id="form1" name="from1" onsubmit="return check();">

<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tbody>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>帖子名称：</td><td width="80%" class="pn-fcontent">
	<input type="text" maxlength="100" disabled="disabled" name="title" id="title" class="required" size="40" value="${topic.title }"/>
	</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>帖子发布日期：</td><td width="80%" class="pn-fcontent">
	<input type="text" maxlength="100" disabled="disabled" name="pubTime" id="pubTime" class="required" size="40" value="${topic.pubTime }"/>
	</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>发帖人：</td><td width="80%" class="pn-fcontent">
	<input type="text" maxlength="100" disabled="disabled" name="member" id="member" class="required" size="40" value="${member.loginName}"/>
	</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>所属板块名：</td><td width="80%" class="pn-fcontent">
	<input type="text" maxlength="100" disabled="disabled" name="forumName" id="forumName" class="required" size="40" value="${forum.name }"/>
	</td>
</tr>
<tr>
	<td width="20%" class="pn-flabel pn-flabel-h">
	<span class="pn-frequired">*</span>所属分区名：</td><td width="80%" class="pn-fcontent">
	<input type="text" maxlength="100" disabled="disabled" name="categoryName" id="categoryName" class="required" size="40" value="${category.name }"/>
	</td>
</tr>

<tr>
<td width="20%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>状态：</td>
<td width="80%" class="pn-fcontent">
<c:if test="${topic.status==1}">
<input type="radio"  name="state"   value="1" checked="checked"/> 普通&nbsp;
<input type="radio"  name="state"   value="2"/> 移除&nbsp;
<input type="radio"  name="state"   value="3"/>锁定&nbsp;
<input type="radio"  name="state"   value="4"/>精华

</c:if>
<c:if test="${topic.status==2}">
<input type="radio"  name="state"   value="1" /> 普通&nbsp;
<input type="radio"  name="state"   value="2" checked="checked"/> 移除&nbsp;
<input type="radio"  name="state"   value="3"/>锁定&nbsp;
<input type="radio"  name="state"   value="4"/>精华

</c:if>


<c:if test="${topic.status==3}">
<input type="radio"  name="state"   value="1" /> 普通&nbsp;
<input type="radio"  name="state"   value="2" /> 移除&nbsp;
<input type="radio"  name="state"   value="3" checked="checked"/>锁定&nbsp;
<input type="radio"  name="state"   value="4"/>精华

</c:if>


<c:if test="${topic.status==4}">
<input type="radio"  name="state"   value="1" /> 普通&nbsp;
<input type="radio"  name="state"   value="2" /> 移除&nbsp;
<input type="radio"  name="state"   value="3"/>锁定&nbsp;
<input type="radio"  name="state"   value="4" checked="checked"/>精华

</c:if>
<c:if test="${topic.status==0}">
<input type="radio"  name="state"   value="1" checked="checked"/> 普通&nbsp;
<input type="radio"  name="state"   value="2" /> 移除&nbsp;
<input type="radio"  name="state"   value="3"/>锁定&nbsp;
<input type="radio"  name="state"   value="4" />精华

</c:if>
<span class="pn-fhelp"></span>
</td>
</tr>

<tr>
<td width="20%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>类型：</td>
<td width="80%" class="pn-fcontent">
<c:if test="${topic.type==1}">
<input type="radio"  name="type"   value="0" /> 普通&nbsp;
<input type="radio"  name="type"   value="1" checked="checked"/> 置顶&nbsp;
<input type="radio"  name="type"   value="2"/> 隐藏&nbsp;
<input type="radio"  name="type"   value="3"/>公告&nbsp;

</c:if>
<c:if test="${topic.type==2}">
<input type="radio"  name="type"   value="0" /> 普通&nbsp;
<input type="radio"  name="type"   value="1" /> 置顶&nbsp;
<input type="radio"  name="type"   value="2" checked="checked"/> 隐藏&nbsp;
<input type="radio"  name="type"   value="3"/>公告&nbsp;

</c:if>




<c:if test="${topic.type==3}">
<input type="radio"  name="type"   value="0" /> 普通&nbsp;
<input type="radio"  name="type"   value="1" /> 置顶&nbsp;
<input type="radio"  name="type"   value="2"/> 隐藏&nbsp;
<input type="radio"  name="type"   value="3" checked="checked"/>公告&nbsp;

</c:if>




<c:if test="${topic.type==0}">
<input type="radio"  name="type"   value="0" checked="checked"/> 普通&nbsp;
<input type="radio"  name="type"   value="1" /> 置顶&nbsp;
<input type="radio"  name="type"   value="2" /> 隐藏&nbsp;
<input type="radio"  name="type"   value="3"/>公告&nbsp;

</c:if>
<span class="pn-fhelp"></span>
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
<input type="hidden" name="id" value="${topic.id }"/>
</form>
</div>

</body></html>