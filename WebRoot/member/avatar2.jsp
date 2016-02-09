<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html> 
  <head> 
    <title>csdnbbs-官方论坛|开源java bbs,jsp cms,jsp bbs</title> 
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" /> 
    <meta name="keywords" content="java,jsp,bbs,开源网站内容管理系统,网站群,站群,多站点,csdn-bbs" /> 
    <meta name="description" content="讨论jsp cms、jsp bbs相关资讯，struts,hibernate,spring,freemarker最新技术研究" /> 
		<script src="../jquery/jquery-1.4.2.min.js" type="text/javascript"></script> 
    <link rel="stylesheet" type="text/css" href="../css/front.css" /> 
    <link rel="stylesheet" type="text/css" href="../css/whole.css" /> 
    <link rel="stylesheet" type="text/css" href="../css/layout.css" />
    <link rel="stylesheet" type="text/css" href="../css/set.css"/>

    <script type="text/javascript">
	function g(o){
		return document.getElementById(o);
	}
	function HoverLi(m,n,counter){
		for(var i=1;i<=counter;i++){
			g('tb_'+m+i).className='';
			g('tbc_'+m+i).className='undis';
		}
		//alert('tbc_'+m+n);
		g('tb_'+m+n).className='current';
		g('tbc_'+m+n).className='dis';
	}
	
	function showimage(imgpath,value){
	    var path = imgpath + '/face/'+ value;
		document.images.useravatars.src = path;
		document.creator.proicon.value ="images/face/"+value;//images/face/
		return false;
	}
	</script>
	  </head> 
	<body> 
    <!--header--><div class="cc" id="header">
<div class="head-wrap cc">
<div id="topbar">
<div class="cc" id="nav-top">
<ul class="fr">
  <li><a href="http://www.jeecms.com" target="_blank">JEEBBS官方网站</a></li>
  <li><a href="javascript:void(0);" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage(location.href)" class="white">设为首页</a></li>
  <li>
  <a onclick="javascript:window.external.AddFavorite('http://bbs.jeecms.com','jeecms-国内专业、开源、免费的JAVA (JSP)论坛')" href="javascript:void(0);" class="white">加入收藏</a>
  </li>
</ul>
</div>
</div>
<div id="head"> 
<table width="100%" cellspacing="0" cellpadding="0"> 
  <tbody> 
    <tr> 
      <td><a href="/" class="fl"><img src="<%=basePath %>/images/csdn_itcast.png" alt="" /></a></td>
      <td>&nbsp;&nbsp;&nbsp;</td>  
      <td id="banner"><img src="<%=basePath %>/images/800X60.gif" alt="" /></td> 
    </tr> 
  </tbody> 
</table> 
</div> 
</div>
<div class="nav-wrap">
<div style="z-index: 999;" class="cc pr" id="nav">
<ul id="nav-global">
  <li class="current"><a href="/">首页</a></li>
  <li><a href="http://www.jeecms.com" target="_blank">官网</a></li>
  <li><a href="http://www.jeecms.com/buy/index.htm" target="_blank">购买</a></li>
  <li><a href="http://www.jeecms.com/service/index.htm" target="_blank">服务</a></li>
  <li><a href="/bbsmember/index.htm" target="_blank">用户中心</a></li>
</ul>
</div>
</div>
</div> <!--/header--><!--main-->
    <div class="main-wrap">
    <div id="main">
    <div id="breadCrumb"><img alt="" align="absMiddle" style="cursor: pointer" src="<%=basePath%>images/home.gif" /> <a href="http://bbs.jeecms.com">JEEBBS-国内开源java 论坛,jsp 论坛</a> &raquo; 用户中心</div>
    <div id="set-wrap">
    <div id="set-side">
    <div id="set-side-wrap">
    <h2 class="set-h2"><a class="color8" href="/bbsmember/index.do">设置中心</a></h2>
    <ul id="set-menu">
      <li id="pro_modify"><a href="<%=basePath %>/user/memberRegisteServlet?method=editInfo">编辑资料</a></li>
      <li id="pro_modify"><a href="<%=basePath%>member/password.jsp">修改密码</a></li>
      <li class="current" id="pro_modify"><a href="<%=basePath%>member/avatar.jsp">修改头像</a></li>
       <li id="pro_modify"><a href="<%=basePath%>/member/mytopics.jsp">我的主题</a></li>
      <li id="pro_modify"><a href="<%=basePath%>member/myreplies.jsp">我的回复</a></li>
      <li id="pro_modify"><a href="<%=basePath%>bbs_index.jsp">退 出</a></li>
    </ul>
    </div>
    </div>
    <div id="set-content">
    <div class="cc" id="set-content-wrap">
    <div>
    <div class="set-tab-table">
    <table cellspacing="0" cellpadding="0" border="0">
      <tbody>
        <tr class="tac" id="infolist">
          <td  id="tb_11"><a href="<%=basePath%>member/avatar.jsp" style="cursor: pointer"> 本地上传</a></td>
          <td class="current" id="tb_12"><a  href="<%=basePath%>member/avatar.jsp" style="cursor: pointer">自定义头像</a></td>
          
        </tr>
      </tbody>
    </table>
    </div>
    
      <div class="set-tab-box">
      <form id="creator" action="<%=basePath %>/user/memberRegisteServlet" method="post"  onsubmit="" name="creator">
      <div class="dis" id="tbc_12">
      <table class="set-table2" cellspacing="0" cellpadding="0" width="100%" border="0">
        <tbody>
          <tr>
            <td class="td1" width="25%"><img height="80" width="80" name="useravatars" alt="" src="<%=basePath %>${memlog.avatar}" /><div align="center" colspan="3" class="tac" style="padding-right: 1em; padding-left: 1em; padding-bottom: 1em; padding-top: 1em">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="btn" type="submit" name="submit" value="上传头像" /></div> </td>
            
            <td>
            <div id="openchang" style="margin-top: 1%; float: left; width: 100%">
            
            <div id="face1" style="margin-top: 1%; float: left; width: 100%">
            <div id="iconbox">
          
            </div>
            <input type="hidden" id="proicon" name="avatar" value="none.gif"/></div>
            </div>
            </td>
          </tr>
         
        </tbody>
        
        <tr>
		   <td align="center" colspan="3">
		          <font style="color:red;font-size:24px">${msg }</font>
		   </td>
        </tr>
        
      </table>
             
      </div>
     
    </form>
    </div>
    </div>
    </div>
    </div>
    </div>
    </div>
    <!--/main--><!--footer--><div class="footer-wrap"> 
    <div id="footer"> 
    <div class="mt" id="mode-footer"> 
    <div class="bottom tac"> 
    <div class="y-bg"></div> 
    <div class="y-bg2"></div> 
    <div class="y-bg3"></div> 
    <div class="y-bg4 black"> 
    <ul> 
      <li><a target="_blank" href="#">关于我们</a></li> 
      <li><a target="_blank" href="#">联系我们</a></li> 
      <li><a target="_blank" href="#">诚聘英才</a></li> 
      <li><a target="_blank" href="#">友情链接</a></li> 
      <li><a target="_blank" href="#">程序下载</a></li> 
      <li><a target="_blank" href="#">合作服务</a></li> 
      <li><a target="_blank" href="#">许可协议</a></li> 
      <li><a href="javascript:void(0);" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage(location.href);" class="white">设为首页</a></li> 
      <li><a onclick="window.external.AddFavorite('http://edu.csdn.net/java','乐知教育-国内专业的JAVA高端培训')" href="javascript:void(0);" class="white">加入收藏</a></li> 
    </ul> 
    </div> 
    <div class="y-bg3"></div> 
    <div class="y-bg2"></div> 
    <div class="y-bg"></div> 
    </div> 
    <center><small> 
    <table cellspacing="0" cellpadding="0" width="620" border="0"> 
      <tbody> 
        <tr><td valign="middle" align="center" height="24"><span id="windspend">Processed in 0.016692 second(s)</span></td></tr> 
        <tr valign="middle"><td align="center" height="24"><small>Powered by <b style="color: rgb(255,153,0)">++YBBS v1.2</b><strong> </strong>Copyright &copy; 2008-2010 </small>&nbsp;<script src="http://s41.cnzz.com/stat.php?id=1097296&web_id=1097296&show=pic1" language="JavaScript" charset="UTF-8"></script></td></tr> 
      </tbody> 
    </table> 

    </small></center></div> 
    </div> 
    </div> <!--footer-->
      </body> 
    </html>