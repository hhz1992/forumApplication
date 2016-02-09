<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>登录</title>
<link href="<%=basePath %>admin/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>admin/css/theme.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>admin/js/jquery.js" type="text/javascript"/>
<script src="<%=basePath %>admin/js/pony.js" type="text/javascript"></script>
<script type="text/javascript">
var url = location.href;
var index = url.indexOf('jsessionid');
if(index != -1) {
	var sid = url.substr(index+11);
	document.cookie = "JSESSIONID="+sid+";path=/;";
}

function resizeFrame() {
	document.getElementById("mainFrame").style.height = document.body.offsetHeight-document.getElementById("top").offsetHeight-1+"px";
}
$(window).load(function() {
	resizeFrame();
});
$(window).resize(function() {
	resizeFrame();
});
</script>
<style type="text/css">
html{height:100%; overflow:hidden;}
body{height:100%;}
</style>
</head>
<body>
<!--页头Begin-->
<div id="top">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tbody><tr>
  	<td width="200" height="66" align="center" background="<%=basePath%>admin/images/title_bg.jpg"><span style="font-size:20px;font-weight:bolder;color:#FFF">论坛后台管理系统</span></td>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tbody><tr>
        <td height="66" align="right" background="<%=basePath%>admin/images/title_bg.jpg">
		<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
          <tbody><tr>
            <td width="17" align="center"><img src="<%=basePath%>admin/images/msg2.jpg" width="10" height="8" border="0"/></td>
            <td width="80" align="left"><span style="color:#FFF">您好,${manager_admin.loginName}</span></td>
            <td width="17" align="center"><img src="<%=basePath%>admin/images/ico5.jpg" border="0"/></td>
            <td width="60" align="left"><a href="<%=basePath %>mng/managerLoginServlet?method=mngExit" class="channel" onclick="return confirm(&#39;您确定退出吗？&#39;);">退 出</a></td>
          </tr>
        </tbody></table></td>
      </tr>
      
    </tbody></table></td>
  </tr>
</tbody></table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tbody><tr>
    <td background="<%=basePath%>admin/images/msg_bg.jpg"><!--<a href="javascript:void(0);">关闭左栏</a>-->&nbsp;</td>
    <td width="73" align="center" background="<%=basePath%>admin/images/msg_bg.jpg">
    【<a href="http://localhost:8080/jeebbs/" target="_blank">查看首页</a>】</td>
    <td width="28" background="<%=basePath %><%=basePath%>admin/images/msg_bg.jpg">
    <img src="<%=basePath%>admin/images/tleft.jpg" width="28" height="26" border="0"/></td>
    <td width="140" align="center" background="<%=basePath%>admin/images/tbg.jpg">
    <script language="JavaScript" type="text/javascript">
		<!--
				var enabled = 0; today = new Date();
				var day; var date;
				if(today.getDay()==0) day = " 星期日";
				if(today.getDay()==1) day = " 星期一";
				if(today.getDay()==2) day = " 星期二";
				if(today.getDay()==3) day = " 星期三";
				if(today.getDay()==4) day = " 星期四";
				if(today.getDay()==5) day = " 星期五";
				if(today.getDay()==6) day = " 星期六";
				date = (today.getFullYear()) + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日" + day +"";
				document.write(date);
		// -->
	  </script></td>
  </tr>
</tbody></table>
<div style="border-top:1px solid #1879B0;"></div>
</div>
<!--页头End-->
<!--主体框架Begin-->
<iframe id="mainFrame" name="mainFrame" src="<%=basePath %>admin/main.jsp" frameborder="0" scrolling="no" style="width: 100%; height: 669px; "></iframe>
<!--主体框架End-->
<div style="border-top:1px solid #1879B0;"></div>

</body></html>