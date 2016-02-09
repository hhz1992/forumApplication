<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html> 
  <head> 
    <title>登录页面</title> 
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
    <script src="<%=basePath %>jquery/jquery-1.4.2.min.js" type="text/javascript"></script> 
    <link href="<%=basePath %>css/front.css" type="text/css" rel="stylesheet" /> 
    <link href="<%=basePath %>css/whole.css" type="text/css" rel="stylesheet" /> 
    <link href="<%=basePath %>css/layout.css" type="text/css" rel="stylesheet" />

  </head> 
  <body> 
    <!--header--><div class="cc" id="header"> 
<div class="head-wrap cc"> 
<div id="topbar"> 
<div class="cc" id="nav-top"> 
<ul class="fr"> 
  <li><a href="#" target="_blank">官方网站</a></li>
  <li><a href="javascript:void(0);" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage(location.href);" class="white">设为首页</a></li> 
  <li><a onclick="window.external.AddFavorite('http://edu.csdn.net/java','乐知教育-国内专业的JAVA高端培训')" href="javascript:void(0);" class="white">加入收藏</a></li> 
</ul> 
</div> 
</div> 
<div id="head"> 
<table width="100%" cellspacing="0" cellpadding="0"> 
  <tbody> 
    <tr> 
      <td><a href="/" class="fl"><img src="<%=basePath%>images/csdn_itcast.png" alt="" /></a></td>
      <td>&nbsp;&nbsp;&nbsp;</td>  
      <td id="banner"><img src="<%=basePath%>images/800X60.gif" alt="" /></td> 
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
</div> <!--/header--><!--main--> 
    <div class="main-wrap"><br /> 
    <div id="main"> 
    <div class="t z"> 
    <table cellspacing="0" cellpadding="0" width="100%"> 
      <thead> 
        <tr> 
          <th class="h" colspan="2"> 
          <h2>BBS注册提示信息</h2> 
          </th> 
        </tr> 
      </thead> 
      <tbody> 
        <tr class="tr2"> 
          <td align="left"></td> 
        </tr> 
        <tr class="tr2"> 
          <td align="left"> 
            <table cellspacing="0" cellpadding="0" width="200" align="center" border="0"> 
              <tbody> 
                <tr> 
                  <th colspan="2" align="right"></th> 
                </tr> 
                <tr> 
                  <td align="center"  style="color:red;font-size:24px;" ><a href="<%=basePath %>/login.jsp">亲爱的会员，恭喜您注册成功~!</td> 
                  <td></td> 
                </tr> 
                <tr> 
                  <td align="right" style="color:red;font-size:16px;"><a href="<%=basePath %>/login.jsp">现在就去登录完善资料，让给多人认识你~~~</a></td> 
                  <td></td> 
                </tr> 
                <tr> 
                  <td align="right"></td> 
                  <td></td> 
                </tr> 
              
              </tbody> 
            </table> 
          </td> 
        </tr> 
      </tbody> 
    </table> 
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