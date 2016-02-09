<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
      <td><a href="/" class="fl"><img src="<%=basePath%>/images/csdn_itcast.png" alt="" /></a></td>
      <td>&nbsp;&nbsp;&nbsp;</td>  
      <td id="banner"><img src="<%=basePath%>/images/800X60.gif" alt="" /></td> 
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
    <div id="breadCrumb"><img alt="" align="absMiddle" style="cursor: pointer" src="<%=basePath%>/images/home.gif" /> <a href="<%=basePath %>/index.jsp">bbs首页</a>&raquo;用户中心</div>
    <div id="set-wrap">
    <div id="set-side">
    <div id="set-side-wrap">
    <h2 class="set-h2"><a class="color8" href="<%=basePath %>member/main.jsp">设置中心</a></h2>
    <ul id="set-menu">
      <li id="pro_modify"><a href="<%=basePath %>/user/memberRegisteServlet?method=editInfo">编辑资料</a></li>
      <li id="pro_modify"><a href="<%=basePath%>member/password.jsp">修改密码</a></li>
      <li id="pro_modify"><a href="<%=basePath%>member/avatar.jsp">修改头像</a></li>
      <li id="pro_modify"><a href="<%=basePath%>webIndexServlet?method=mytopic">我的主题</a></li>
      <li id="pro_modify"><a href="<%=basePath %>webIndexServlet?method=myReply">我的回复</a></li>
      <li id="pro_modify"><a href="<%=basePath %>/user/memberRegisteServlet?method=attachmentList">我的附件</a></li>
      <li id="pro_modify"><a href="<%=basePath %>/bbs_index.jsp">退 出</a></li>
    </ul>
    </div>
    </div>
    <div id="set-content">
    <div class="cc" id="set-content-wrap" style="border-right: #c7e1ef 1px solid; padding-right: 1em; border-top: #c7e1ef 1px solid; padding-left: 1em; padding-bottom: 1em; border-left: #c7e1ef 1px solid; padding-top: 1em; border-bottom: #c7e1ef 1px solid">
    <div style="float: left; width: 100%">
    <table cellspacing="0" cellpadding="0" width="100%" border="0">
      <tbody>
        <tr>
        <c:if test="${memlog.avatar==null}">
        <td class="tac" width="80" style="vertical-align: top"><img class="pic" alt="" border="0" src="<%=basePath %>/images/face/none.gif"/><br />
          <div style="padding-right: 0px; padding-left: 0px; padding-bottom: 0.3em; padding-top: 0.3em"><a class="gray" href="<%=basePath %>/member/avatar.jsp">修改头像</a></div>
          </td>
        </c:if>
        <c:if test="${memlog.avatar!=null}">
          <td class="tac" width="80" style="vertical-align: top"><img class="pic" alt="" border="0" src="<%=basePath %>${memlog.avatar}"/><br />
          <div style="padding-right: 0px; padding-left: 0px; padding-bottom: 0.3em; padding-top: 0.3em"><a class="gray" href="<%=basePath %>/member/avatar.jsp">修改头像</a></div>
          </td>
          </c:if>
          <td style="padding-left: 1.5em; vertical-align: top">
          <div class="bdbdash" style="padding-right: 0px; padding-left: 0px; padding-bottom: 1em; padding-top: 0px">
          <div><span style="font: bold 16px Verdana">${memlog.loginName }</span> <span class="gray">(数字ID:${memlog.id })</span> <a class="link5" href="http://localhost:8080/wtf_bbs//user/memberRegisteServlet?method=editInfo">编辑资料</a></div>
          <div><a class="link5" href="<%=basePath %>/member/main.jsp">我的首页</a>　<a class="link5" href="http://localhost:8080/wtf_bbs/webIndexServlet?method=mytopic">我的主题</a>　<a class="link5" href="http://localhost:8080/wtf_bbs/webIndexServlet?method=myReply">我的回复</a></div>
          <div class="bdbdash" style="padding-right: 0px; padding-left: 0px; padding-bottom: 1em; padding-top: 1em">
          <table class="set-table1" cellspacing="0" cellpadding="0" width="80%" border="0">
            <tbody>
              <tr>
                <td class="tdpr2" width="44%">精华：${count3 }<br />
                发帖：${count2 }<br />
                积分：${memlog.integral }<br />
                回复：${memlog.replyCount }<br />
                </td>
                <td class="gray" width="56%"><c:if test="${memlog.integral<501}">【会员头衔：菜鸟】</c:if><c:if test="${memlog.integral>500&&memlog.integral<1501}">【会员头衔：前哨】</c:if><c:if test="${memlog.integral>1500&&memlog.integral<2001}">【会员头衔：尉官】</c:if><c:if test="${memlog.integral>2000&&memlog.integral<2501}">【会员头衔：大校】</c:if><c:if test="${memlog.integral>2500}">【会员头衔：将军】</c:if><br />
                会员级别： <img alt="" src="<%=basePath%>/images/level/1.gif" /> <br />
                注册时间： ${memlog.regTime }<br />
                最后登录： ${memlog.lastVisitTime }</td>
              </tr>
            </tbody>
          </table>
          </div>
          <div class="bdbdash" style="padding-right: 0px; padding-left: 0px; padding-bottom: 1em; padding-top: 1em">
          <table class="set-table1" cellspacing="0" cellpadding="0" border="0">
            <tbody>
              <tr>
                <td></td>
              </tr>
            </tbody>
          </table>
          </div>
          </div>
          </td>
        </tr>
      </tbody>
    </table>
    </div>
    <div class="c"></div>
    <div style="padding-right: 0px; padding-left: 0px; padding-bottom: 1em; padding-top: 1em">
    <table class="set-table1" cellspacing="0" cellpadding="0" border="0">
      <tbody>
        <tr>
          <td class="tdpr2 gray"></td>
        </tr>
      </tbody>
    </table>
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
  <li><a target="_blank" href="http://www.jeecms.com/news/743.htm">关于我们</a></li>
  <li><a target="_blank" href="http://www.jeecms.com/yjdt/826.htm">联系我们</a></li>
  <li><a target="_blank" href="#">诚聘英才</a></li>
  <li><a target="_blank" href="#">友情链接</a></li>
  <li><a target="_blank" href="http://www.jeecms.com/download/index.htm">程序下载</a></li>
  <li><a target="_blank" href="#">合作服务</a></li>
  <li><a target="_blank" href="http://www.jeecms.com/yjdt/825.htm">许可协议</a></li>
  <li><a onclick="this.style.behavior='url(#default#homepage)';this.setHomePage(location.href)" href="javascript:void(0);">设为首页</a></li>
  <li><a onclick="window.external.addFavorite('http://www.jeecms.com','jeecms-国内专业、开源的JAVA (JSP) CMS')" href="javascript:void(0);">加入收藏</a></li>
</ul>
</div>
<div class="y-bg3"></div>
<div class="y-bg2"></div>
<div class="y-bg"></div>
</div>
<center><small>
<table cellspacing="0" cellpadding="0" width="620" border="0">
  <tbody>
    <tr>
      <td valign="middle" align="center" height="24"><span id="windspend">Processed in 0.107346 second(s)</span></td>
    </tr>
    <tr valign="middle">
      <td align="center" height="24"><small>Powered by <b style="color: rgb(255,153,0)">JEEBBS v1.0</b><strong> </strong>Copyright &copy; 2007-2009 </small>&nbsp;<script src="http://s41.cnzz.com/stat.php?id=1097297&web_id=1097297&show=pic1" language="JavaScript" charset="gb2312"></script></td>
    </tr>
  </tbody>
</table>
</small></center></div>
</div>
</div> <!--footer-->
  </body>
</html>
