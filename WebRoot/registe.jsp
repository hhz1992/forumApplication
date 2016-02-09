<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html> 
  <head> 
    <title>会员注册</title> 
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
    <script src="<%=basePath %>jquery/jquery-1.4.2.min.js" type="text/javascript"></script> 
    <link href="<%=basePath %>css/front.css" type="text/css" rel="stylesheet" /> 
    <link href="<%=basePath %>css/whole.css" type="text/css" rel="stylesheet" /> 
    <link href="<%=basePath %>css/layout.css" type="text/css" rel="stylesheet" />
<script type="text/javascript"> 
	function change(img){
		img.src = '<%=basePath %>checkcode.png?d=' + new Date().getTime();
	}
	
</script> 
<script type="text/javascript"> 	
	function check(){
	var loginName=document.getElementById("loginName").value;
	if(loginName==null||loginName==""){
		alert(" 请输入登录名 !");
		document.form1.loginName.focus();
	
		return false;
	}

	var password=document.getElementById("password").value;
	if(password==null||password==""){
		alert(" 请输入密码 !");
		document.form1.password.focus();
		return false;
	}
	
	var repassword=document.getElementById("repassword").value;
	if(repassword==null||repassword==""){
		alert(" 请输入确认密码 !");
		document.form1.repassword.focus();
		return false;
	}
	
	var email=document.getElementById("email").value;
		if(email==null||email==""){
		alert(" 请输入邮箱");
		document.form1.email.focus();
		return false;
		}
	
	var checkCode=document.getElementById("checkCode").value;
		if(checkCode==null||checkCode==""){
		alert(" 请输入验证码 !");
		
		document.form1.checkCode.focus();
		return false;
		}
		
	var repassword=document.getElementById("repassword").value;
	if(password!=repassword){
		alert(" 两次密码输入不相同");
		document.form1.password.value="";
		document.form1.repassword.value="";
		document.form1.password.focus();
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
      <td><a href="/" class="fl"><img src="<%=basePath %>images/csdn_itcast.png" alt="" /></a></td>
      <td>&nbsp;&nbsp;&nbsp;</td>  
      <td id="banner"><img src="<%=basePath %>images/800X60.gif" alt="" /></td> 
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
    <div class="main-wrap"><br /> 
    <div id="main"> 
    <div class="t z"> 
     <form id="form1" name="form1" action="<%=basePath %>/user/memberRegisteServlet?method=registe" method="post" onsubmit="return check();"> 
      <table width="100%" cellspacing="0" cellpadding="0"> 
        <thead> 
          <tr> 
            <th colspan="2" class="h"> 
            <h2>用 户 注 册</h2> 
            </th> 
          </tr> 
        </thead> 
        <tbody> 
          <tr class="tr2"> 
            <td width="30%" align="right">用户名：</td> 
            <td width="70%">&nbsp;<input type="text" id="loginName" name="loginName" vld="{required:true,rangelength:[4,20],username:true,remote:'/jeecms/core/ajax/checkUserName.do',messages:{remote:'用户名已存在！'}}" class="input" /></td> 
          </tr> 
          <tr class="tr2"> 
            <td align="right">密　码：</td> 
            <td>&nbsp;<input type="password" id="password" name="password" id="password" class="input required" /></td> 
          </tr> 
          <tr class="tr2"> 
            <td align="right">确认密码：</td> 
            <td>&nbsp;<input type="password" id="repassword" name="repassword" class="input" /></td> 
          </tr> 
          <tr class="tr2"> 
            <td align="right">E-mail：</td> 
            <td>&nbsp;<input type="text" id="email" name="email"  class="input required email" /></td> 
          </tr> 
                 <tr class="tr2"> 
        <td align="right" >验证码：</td>
        <td><input class="input required" size="20" 
		name="checkCode" id="checkCode" maxlength="20" type="text"/>
        
	<img src="<%=basePath%>checkcode.png" class="tooltip" alt="重换一张" title="重换一张" style="cursor: pointer;" onclick="change(this)"/>
	
		</td>
      </tr>
      <tr>
		   <td colspan="3">
		          <font style="color:red;font-size:20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${mem }</font>
		   </td>
        </tr>
          <tr class="tr2"> 
            <td align="center" colspan="2"><input type="submit" value="同意以下协议并提交" name="submit" /></td> 
          </tr> 
          <tr class="tr2"> 
            <td align="center" colspan="2"><textarea cols="180" rows="10" readonly="readonly">当您申请用户时，表示您已经同意遵守本规章。 
 
欢迎您加入本站点参加交流和讨论，本站点为公共论坛，为维护网上公共秩序和社会稳定，请您自觉遵守以下条款： 
 
一、不得利用本站危害国家安全、泄露国家秘密，不得侵犯国家社会集体的和公民的合法权益，不得利用本站制作、复制和传播下列信息：
　（一）煽动抗拒、破坏宪法和法律、行政法规实施的；
　（二）煽动颠覆国家政权，推翻社会主义制度的；
　（三）煽动分裂国家、破坏国家统一的；
　（四）煽动民族仇恨、民族歧视，破坏民族团结的；
　（五）捏造或者歪曲事实，散布谣言，扰乱社会秩序的；
　（六）宣扬封建迷信、淫秽、色情、赌博、暴力、凶杀、恐怖、教唆犯罪的；
　（七）公然侮辱他人或者捏造事实诽谤他人的，或者进行其他恶意攻击的；
　（八）损害国家机关信誉的；
　（九）其他违反宪法和法律行政法规的；
　（十）进行商业广告行为的。
 
二、互相尊重，对自己的言论和行为负责。
三、禁止在申请用户时使用相关本站的词汇，或是带有侮辱、毁谤、造谣类的或是有其含义的各种语言进行注册用户，否则我们会将其删除。
四、禁止以任何方式对本站进行各种破坏行为。
五、如果您有违反国家相关法律法规的行为，本站概不负责，您的登录论坛信息均被记录无疑，必要时，我们会向相关的国家管理部门提供此类信息。 </textarea></td> 
          </tr> 
        </tbody> 
      </table> 
    </form> 
    </div> 
    </div> 
    </div> 
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