<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>tforum-left</title>
<link href="<%=basePath %>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>/admin/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>/admin/css/navbar.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath %>/admin/js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath %>/admin/js/pony.js" type="text/javascript"></script>
<script src="<%=basePath %>/admin/js/navbar.js" type="text/javascript"></script>
<script language="javascript">
var show = true;
var open = null;
var hide = false;
//修改菜单的上下箭头符号
function my_on(head,body){
	var tag_a;
	for(var i=0;i<head.childNodes.length;i++){
		if (head.childNodes[i].nodeName=="A"){
			tag_a=head.childNodes[i];
			break;
		}
	}
	if(tag_a){tag_a.className="on";}
}
function my_off(head,body){
	var tag_a;
	for(var i=0;i<head.childNodes.length;i++){
		if (head.childNodes[i].nodeName=="A"){
			tag_a=head.childNodes[i];
			break;
		}
	}
	if(tag_a){tag_a.className="off";}
}
window.onload=function(){
	new Menu("menu1",'menu1_child','dtu',40,false,my_on,my_off).init();
	new Menu("menu2",'menu2_child','dtu',40,false,my_on,my_off).init();
	new Menu("menu3",'menu3_child','dtu',40,false,my_on,my_off).init();
	new Menu("menu4",'menu4_child','dtu',40,false,my_on,my_off).init();
};
</script>
</head>
<body class="lbody">
<div id="menu">
	<div class="tit" id="menu1"><a href="javascript:void(0)" class="on" id="menu1_a">论坛分区模块设置</a></div>
	<div class="list" id="menu1_child">
		<ul><li><a target="rightFrame" href="<%=basePath %>admin/category/addCategory.jsp">添加分区</a></li>
			<li><a target="rightFrame" href="<%=basePath %>user/categoryServlet?method=categoryList">查看分区</a></li>
			<li><a target="rightFrame" href="<%=basePath %>user/forumManagerServlet?method=toAdd">添加板块</a></li>
			<li><a target="rightFrame" href="<%=basePath %>user/forumManagerServlet?method=forumList">查看板块</a></li>
		</ul>
	</div>
	<div class="tit" id="menu2"><a href="javascript:void(0)" target="rightFrame" class="on" id="menu1_a">会员管理</a></div>
	<div class="list" id="menu2_child">
	  <ul>
		<li><a target="rightFrame" href="<%=basePath %>/user/memberManagerSerlet?method=memberList">查看会员</a></li>
		<li><a target="rightFrame" href="<%=basePath %>/guest/addguest.jsp">客户验证</a></li>
	  </ul>
	</div>
	<div class="tit" id="menu3"><a href="javascript:void(0)" target="rightFrame" class="on" id="menu1_a">管理员功能</a></div>
	<div class="list" id="menu3_child">
	  <ul>
			<li><a target="rightFrame" href="<%=basePath %>/admin/manager/addManager.jsp">添加管理员</a></li>
			<li><a target="rightFrame" href="<%=basePath %>/admin/managerServlet?method=managerList">查询管理员</a></li>
		</ul>
	</div>
	
	<div class="tit" id="menu4"><a href="javascript:void(0)" target="rightFrame" class="on" id="menu1_a">帖子管理</a></div>
	<div class="list" id="menu4_child">
	  <ul>
		 <li><a href="<%=basePath %>user/topicServlet?method=topicList" target="rightFrame">帖子管理</a></li>
		 <li><a href="<%=basePath %>user/replyServlet?method=replyList" target="rightFrame">回复管理</a></li>
	  </ul>
	</div>
</div>
</body></html> 
