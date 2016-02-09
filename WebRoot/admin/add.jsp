<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0059)http://localhost:8080/jeebbs/admin/bbs/category/Com_add.do? -->
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
	<div class="rpos">当前位置： 板块 - 分区管理 - 添加</div>
	<form class="ropt">
		<input type="submit" value="返回列表" onclick="this.form.action=&#39;Com_list.do&#39;;"/>
	</form>
	<div class="clear"></div>
</div>
<form method="post" action="http://localhost:8080/jeebbs/admin/bbs/category/Com_save.do" id="jvForm">
<input type="hidden" name="pageNo" value="1"/>
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tbody><tr>
<td width="20%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>分区名称：</td><td width="80%" class="pn-fcontent"><input type="text" maxlength="100" value="" name="bean.title" class="required" size="40"/></td></tr><tr><td width="20%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>访问路径：</td><td width="80%" class="pn-fcontent"><input type="text" maxlength="20" rname="path" value="" name="bean.path" class="required" vld="{path:true,remote:&#39;checkPath.do&#39;,messages:{remote:&#39;路径已存在&#39;}}"/></td></tr><tr><td width="20%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>下级板块横排：</td><td width="80%" class="pn-fcontent"><input type="text" value="1" name="bean.forumCols" class="required digits"/> <span class="pn-fhelp">每排显示版块个数，为1按正常方式排列</span></td></tr><tr><td width="20%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>排列顺序：</td><td width="80%" class="pn-fcontent"><input type="text" value="10" name="bean.priority" class="required digits"/> <span class="pn-fhelp">从小到大排列</span></td></tr><tr><td width="20%" class="pn-flabel pn-flabel-h">分区版主：</td><td width="80%" class="pn-fcontent"><input type="text" value="" name="moderators" size="40"/> <span class="pn-fhelp">多个版主用,分割</span></td></tr><tr><td colspan="2" class="pn-fbutton">
<input type="submit" value="提交"/> &nbsp; <input type="reset" value="重置"/></td>
</tr></tbody></table>
</form>
</div>

</body></html>