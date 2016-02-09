<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0056)http://localhost:8080/jeebbs/admin/core/user/Com_list.do -->
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
	<div class="rpos">当前位置： 核心功能 - 用户管理 - 列表</div>
	<form class="ropt" method="post">
		<input type="submit" value="添加用户" onclick="this.form.action=&#39;Com_add.do&#39;;"/>
	</form>
	<div class="clear"></div>
</div>
<script language="javascript">
var Com_edit = {action:"Com_edit.do"};
var Com_delete = {action:"Com_delete.do",msg:"您确定删除吗？"};
function _gotoPage(pageNo) {
	try{
		var tableForm = document.getElementById('tableForm');
		tableForm.pageNo.value = pageNo;
		tableForm.action="Com_list.do";
		tableForm.onsubmit=null;
		tableForm.submit();
	} catch(e) {
		alert('gotoPage(pageNo)方法出错或不存在');
	}
}
function _operate(op,id) {
	if(op.msg && !confirm(op.msg)) {
		return;
	}
	var tableForm = document.getElementById('tableForm');
	tableForm.onsubmit=null;
	tableForm.action=op.action;
	tableForm.id.value = id;
	tableForm.submit();
}
function _validateBatch() {
	var batchChecks = document.getElementsByName('ids');
	var hasChecked = false;
	for(var i=0; i<batchChecks.length; i++) {
		if(batchChecks[i].checked) {
			hasChecked = true;
			break;
		}
	}
	if(!hasChecked) {alert('请选择要操作的数据！')};
	return hasChecked;
}
</script>
<form id="tableForm" method="post" onsubmit="return _validateBatch();">
<div class="pn-sp">
	<div class="pn-sp-left"><input type="submit" value="删除" onclick="if(confirm(&#39;您确定删除吗？&#39;)){this.form.action=&#39;Com_delete.do&#39;;return true;}else{return false;}"/>	
	</div>
	<div class="pn-sp-right">
		共3条 1/1 页&nbsp;<a href="#" disabled="disabled">首页</a>&nbsp;<a href="#">上一页</a>&nbsp;<a href="#">下一页</a>&nbsp;<a href="#">末页</a>
	</div>
	<div class="clear"></div>
</div>
<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead">
<tr>
	<th width="20px"><input type="checkbox" id="allCheck" value="checkbox" onclick="Pn.checkBox(&#39;ids&#39;,this.checked);"/></th>
	<th>ID</th>
	<th>登录名</th>
	<th>真实姓名</th>
	<th>登录次数</th>
	<th>创建时间</th>
	<th>最后登录时间</th>
	<th>最后登录IP</th>
	<th>禁用</th>
	<th>操作</th>
</tr>
</thead>
<tbody class="pn-ltbody">
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);" class="">
	<td><input type="checkbox" name="ids" value="4"/></td>
	<td>4</td>
	<td>test2</td>
	<td>
	<td>1</td>
	<td>2009-08-13</td>
	<td>2009-08-13 17:44:29</td>
	<td>127.0.0.1</td>
	<td>否</td>
	<td class="pn-lopt"/><a href="javascript:_operate(Com_edit,'4');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'4');" class="pn-loperator">删除</a></td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);" class="">
	<td><input type="checkbox" name="ids" value="3"/></td>
	<td>3</td>
	<td>test</td>
	<td>
	<td>5</td>
	<td>2009-08-13</td>
	<td>2009-08-13 17:49:41</td>
	<td>127.0.0.1</td>
	<td>否</td>
	<td class="pn-lopt"/><a href="javascript:_operate(Com_edit,'3');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'3');" class="pn-loperator">删除</a></td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);" class="">
	<td><input type="checkbox" name="ids" value="1"/></td>
	<td>1</td>
	<td>admin</td>
	<td>管理员</td>
	<td>11</td>
	<td>
	<td>2010-09-25 11:48:08</td>
	<td>127.0.0.1</td>
	<td>否</td>
	<td class="pn-lopt"/><a href="javascript:_operate(Com_edit,'1');" class="pn-loperator">修改</a>┆<a href="javascript:_operate(Com_delete,'1');" class="pn-loperator">删除</a></td>
</tr>
</tbody>
</table>
<input type="hidden" name="id"/>
<input type="hidden" name="pageNo" value="1"/>
<div class="pn-sp">
	<div class="pn-sp-left"><input type="submit" value="删除" onclick="if(confirm(&#39;您确定删除吗？&#39;)){this.form.action=&#39;Com_delete.do&#39;;return true;}else{return false;}"/>	
	</div>
	<div class="pn-sp-right">
		共3条 1/1 页&nbsp;<a href="#" disabled="disabled">首页</a>&nbsp;<a href="#">上一页</a>&nbsp;<a href="#">下一页</a>&nbsp;<a href="#">末页</a>
	</div>
	<div class="clear"></div>
</div>
</form>
<script language="javascript">
$(function(){
});
</script></div>

</body></html>