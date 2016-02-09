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
<script type="text/javascript"> 
	function change(img){
		img.src = '<%=basePath%>checkcode.png?d=' + new Date().getTime();
	}
	
	
	function check(){
	var loginName=document.getElementById("username").value;
	if(loginName==null||loginName==""){
		alert(" 请输入管理员登录名 !");
		document.form1.username.focus();
		return false;
	}
	var password=document.getElementById("password").value;
	if(password==null||password==""){
		alert(" 请输入管理员密码 !");
		document.form1.password.focus();
		return false;
	}
	var checkCode=document.getElementById("checkCode").value;
	if(checkCode==null||checkCode=="")
	{
	    alert("请输入验证码！");
	    document.form1.checkCode.focus();
	    return false;
	}

}

	</script> 
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
</div> <!--/header--><!--main--> 
    <div class="main-wrap"><br /> 
    <div id="main"> 
    <div class="t z"> 
    <table cellspacing="0" cellpadding="0" width="100%"> 
      <thead> 
        <tr> 
          <th class="h" colspan="2"> 
          <h2>BBS提示信息</h2> 
          </th> 
        </tr> 
      </thead> 
      <tbody> 
        <tr class="tr2"> 
          <td align="left"></td> 
        </tr> 
        <tr class="tr2"> 
          <td align="left"> 
          <form id="form1" name="form1" action="<%=basePath %>/user/memberLoginServlet?method=login" method="post" onsubmit="return check()"> 
            <table cellspacing="0" cellpadding="0" width="200" align="center" border="0"> 
              <tbody> 
                <tr> 
                  <th colspan="2">会员登录</th> 
                </tr> 
                <tr> 
                  <td align="right">用户名：</td> 
                  <td><input class="required input" id="username" title="用户名不能为空" size="15" name="username" type="text" /></td> 
                </tr> 
                <tr> 
                  <td align="right">密码：</td> 
                  <td><input class="required input" id="password" title="密码不能为空" type="password" size="15" name="password" /></td> 
                </tr> 
                <tr> 
                  <td align="right">验证码：</td> 
                  <td><input class="required input" id="checkCode" title="请填写验证码" size="15" name="checkCode" type="text" /><img src="<%=basePath%>checkcode.png" class="tooltip" alt="重换一张" title="重换一张" style="cursor: pointer;" onclick="change(this)"/></td> 
           
                </tr> 
                <tr> 
                
		   <td colspan="3">
		          <font style="color:red;font-size:20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${log }</font>
		   </td>
        </tr>
	
                <tr> 
                  <td align="center" colspan="2"><input type="hidden" name="redirectUrl" value="" /><input type="hidden" name="cookieType" value="4" /> 
				   <input class="bta" type="submit" style="vertical-align: middle" value="提交" /> &nbsp;尚未注册？<a href="<%=basePath %>/registe.jsp">点这里注册吧</a>.</td> 
                </tr> 
              </tbody> 
            </table> 
          </form> 
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