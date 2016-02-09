<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.tjitcast.com/jsp/tags" prefix="t" %>
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
		<script src="<%=basePath %>/jquery/jquery-1.4.2.min.js" type="text/javascript"></script> 
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/front.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/whole.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/layout.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/set.css"/>
  </head> 
  <body> 
    <!--header--> <div class="cc" id="header"> 
<div class="head-wrap cc"> 
<div id="topbar"> 
<div class="cc" id="nav-top"> 
<ul class="fr"> 
  <li><a href="http://www.csdn-bbs.com" target="_blank">CSDN-BBS官方网站</a></li> 
  <li><a href="javascript:void(0);" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage(location.href)" class="white">设为首页</a></li> 
  <li> 
  <a onclick="javascript:window.external.AddFavorite('http://bbs.csdn-bbs.com','csdn-bbs-国内专业、开源、免费的JAVA (JSP)论坛')" href="javascript:void(0);" class="white">加入收藏</a> 
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
  <li><a href="/">首页</a></li> 
  <li class="current"><a href="http://edu.csdn.net/java" target="_blank">论坛</a></li> 
  <li><a href="http://edu.csdn.net/java" target="_blank">服务</a></li> 
  <li><a href="http://edu.csdn.net/java" target="_blank">帮助</a></li> 
  <li><a href="about.html" target="_blank">关于我们</a></li> 
</ul>
</div> 
</div> 
</div> <!--/header--> 
<!--main-->
    <div class="main-wrap">
    <div id="main">
    <div id="breadCrumb"><img alt="" align="absMiddle" style="cursor: pointer" src="<%=basePath %>/images/home.gif" /> <a href="<%=basePath %>/index.jsp">bbs首页</a>&raquo;<a href="<%=basePath %>/member/main.jsp">用户中心</a> </div>
    <div id="set-wrap">
    <div id="set-side">
    <div id="set-side-wrap">
    <h2 class="set-h2"><a class="color8" href="<%=basePath %>member/main.jsp">设置中心</a></h2>
    <ul id="set-menu">
      <li id="pro_modify"><a href="<%=basePath %>/user/memberRegisteServlet?method=editInfo">编辑资料</a></li>
      <li id="pro_modify"><a href="<%=basePath%>member/password.jsp">修改密码</a></li>
      <li id="pro_modify"><a href="<%=basePath%>member/avatar.jsp">修改头像</a></li>
      <li id="pro_modify"><a href="<%=basePath%>webIndexServlet?method=mytopic">我的主题</a></li>
      <li class="current" id="pro_modify"><a href="<%=basePath %>webIndexServlet?method=myReply">我的回复</a></li>
      <li id="pro_modify"><a href="<%=basePath %>/user/memberRegisteServlet?method=attachmentList">我的附件</a></li>
      <li id="pro_modify"><a href="<%=basePath %>/user/memberRegisteServlet?method=exit">退 出</a></li>
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
          <td class="current"><a href="#" style="cursor: pointer">我发表的回复</a></td>
        </tr>
      </tbody>
    </table>
    </div>
     <div class="set-tab-box">
      <table class="set-table2" cellspacing="0" cellpadding="0" width="100%" border="0" style="margin-top:10px;">
        <tbody>
        
          <tr>
            <td width="48%"><strong>标 题</strong></td>
            <td width="25%"><strong> 版块</strong></td>
            <td width="27%"><strong>回复时间</strong></td>
          </tr>
          <c:forEach items="${replyList}" var="reply">
          <tr>
            <td><a href="<%=basePath %>webIndexServlet?method=toCateTopic&id=${reply.value.id }"  target="_blank">${reply.value.title }</a></td>
            <td>
            <c:forEach items="${forumList}" var="forum">
            <c:if test="${forum.id==reply.value.forumId}">${forum.name}</c:if>
            </c:forEach>
             </td>
            <td> ${reply.key.pubTime }</td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
	  <div class="page">
			
              <ul>
           <t:pager pageSize="${pm.pageSize}" pageNo="${pm.pageNo}" url="${pageContext.request.contextPath}/webIndexServlet?method=myReply" recordCount="${pm.recordCount}"/>   
              </ul>
            
		</div>
    </div>
    </div>
    </div>
    </div>
    </div>
    </div>
    <!--/main-->
<!--footer--><div class="footer-wrap"> 
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