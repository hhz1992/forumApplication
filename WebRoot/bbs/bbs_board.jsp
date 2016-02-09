<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<script src="<%=basePath %>jquery/jquery-1.4.2.min.js" type="text/javascript"></script> 
    <link href="<%=basePath %>css/front.css" rel="stylesheet" type="text/css" /> 
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/whole.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/layout.css" /> 
  </head> 
  <body> 
    <!--header--> <div class="cc" id="header"> 
<div class="head-wrap cc"> 
<div id="topbar"> 
<div class="cc" id="nav-top"> 
<ul class="fr"> 
  <li><a href="http://www.csdn-bbs.com" target="_blank">CSDN-BBS官方网站</a></li> 
  <li><a href="javascript:void(0);" onClick="this.style.behavior='url(#default#homepage)';this.setHomePage(location.href)" class="white">设为首页</a></li> 
  <li> 
  <a onClick="javascript:window.external.AddFavorite('http://bbs.csdn-bbs.com','csdn-bbs-国内专业、开源、免费的JAVA (JSP)论坛')" href="javascript:void(0);" class="white">加入收藏</a> 
  </li> 
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
 
<!--main-->
<div class="main-wrap">
    <div id="main">
	
	<!--notice-->
      <div id="notice">
    <div id="notice0" style="width:98%;height:18px;line-height:18px;overflow-y:hidden;">
    <ul>
      <li><a href="#"><font color="#E10900">5&middot;12汶川地震周年祭，留下你的祝福！</font></a> <span class="f9 gray">09-05-09</span></li>
      <li><a href="#"><font color="#990000">节约论坛存储空间，会员须知！</font></a> <span class="f9 gray">08-10-31</span></li>
      <li><a href="#">关于杜绝回复广告的措施，新注册的会员必看！</a> <span class="f9 gray">08-07-11</span></li>
      <li><a href="#">JEEBBS论坛免责申明,会员必看!</a> <span class="f9 gray">07-10-17</span></li>
      <li><a href="#">关于&quot;光学资源&quot;和&quot;机械资源&quot;限制的说明!</a> <span class="f9 gray">07-09-27</span></li>
      <li><a href="#">请会员多注意站内短消息!</a> <span class="f9 gray">07-08-28</span></li>
      <li><a href="#">新注册会员必看!</a> <span class="f9 gray">07-05-30</span></li>
      <li><a href="#">请会员珍惜自己的ID,严格遵守论坛规定!</a> <span class="f9 gray">06-08-16</span></li>
    </ul>
    </div>
    <div style="height: 36px;overflow-y: hidden;"></div>
    </div>
    <!--/notice-->
    <div class="t3 cc">
      <div id="nav-user" class="cc"> 
 
        <script type="text/javascript">
				$(function() {
					$("#loginForm").validate($.validator.AlertError);
					new JCore.CheckCode($('#checkCode'),'/CheckCode.svl');
				});
				</script>
                    <form id="loginForm" action="/jeebbs/LoginAloneSubmit.jspx" method="post">
                      <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td><input name="username" type="text" class="uname" title="请输入用户名" maxlength="16"/></td>
                          <td><input name="password" type="password" class="pwd" title="请输入密码" maxlength="18"/></td>
                          <td><input name="checkCode" type="text" class="yan" id="checkCode" title="请输入验证码" size="12" maxlength="10"/></td>
                          <td><input type="hidden" name="redirectUrl" value="http://bbs.jeecms.com/service.htm"/>
                              <input name="submit" type="submit" value="登录"/></td>
                          <td>&nbsp; <a href="/jeebbs/RegisterInput.jspx">注册</a> &nbsp; <a href="/jeebbs/LoginAloneInput.jspx">登录</a></td>
                        </tr>
                      </table>
                    </form>
		</div>
      <div class="fr"> 今日:6 | 昨日: 13 | 最高日:101 于 2009-08-12 <br/>
        主题:897 | 帖子:2771 | 会员:820 | 欢迎新会员:<span class="black">cnzma</span> </div>
    </div>
	
    <div class="c"></div>
  
    <div id="breadCrumb" class="cc">  
	<img src="http://bbs.jeecms.com/res_base/jeecms_com_bbs/blue/bbs_forum/img/home.gif" align="absmiddle" style="cursor:pointer"/> <a href="http://bbs.jeecms.com">JEEBBS-国内开源java 论坛,jsp 论坛</a> > ≡JEECMS产品服务区≡</div>
	
	<div class="c"></div>
	
	
    <div id="content">
    <div class="contentwrap z">
	
    <div class="t z">	
    <table cellspacing="0" cellpadding="0" width="100%">
      <tbody>
        <tr>
          <th class="h" colspan="6"><a class="closeicon fr" href="#"><img alt="" src="http://bbs.jeecms.com/res_base/jeecms_com_bbs/blue/bbs_forum/img/cate_fold.gif" /></a>
          <h2>&raquo; <a href="http://bbs.jeecms.com/service.htm">≡JEECMS产品服务区≡</a></h2>
          </th>
        </tr>
		<tr class="tr2">
          <td colspan="2" class="tac">论坛</td>
          <td width="132" class="tal y-style e">主题 / 文章</td>
          <td width="264" class="tal y-style f">最后发表</td>
        </tr>
      </tbody>
      <tbody>        
        <tr class="tr3 f_one">
          <td class="icon tac" width="76">
          <div class="today">0
          <p>今日</p>
          </div>
          </td>
          <td width="749">
          <h3 class="b"><a href="http://bbs.jeecms.com/sqzx/index.htm">系统售前咨询</a></h3>
          <br />
          <span class="smalltxt gray" id="desc_40">提供JEECMS商业授权相关信息咨询</span></td>
          <td class="tal y-style f10 e">8 / <span class="gray2 f10">20</span></td>
          <td><span class="gray2"> <span class="b">korven</span> <br />
          <span class="f9">[2009-09-13 16-00]</span></span>
		  </td>
        </tr>
      </tbody>
      <tbody>        
        <tr class="tr3 f_one">
          <td class="icon tac" width="76">
          <div class="today">0
          <p>今日</p>
          </div>
          </td>
          <td width="749">
          <h3 class="b"><a href="http://bbs.jeecms.com/fzjy/index.htm">JEECMS发展建议</a></h3>
          <br />
          <span class="smalltxt gray" id="desc_40">您对JEECMS有什么好的意见和建议，请在此提出。我们会在今后的管理与新版开发过程中逐步加以解决!</span></td>
          <td class="tal y-style f10 e">43 / <span class="gray2 f10">196</span></td>
          <td><span class="gray2"> <span class="b">flyage</span> <br />
          <span class="f9">[2009-09-17 20-42]</span></span>
		  </td>
        </tr>
      </tbody>
      <tbody>        
        <tr class="tr3 f_one">
          <td class="icon tac" width="76">
          <div class="today">0
          <p>今日</p>
          </div>
          </td>
          <td width="749">
          <h3 class="b"><a href="http://bbs.jeecms.com/sqfw/index.htm">授权用户服务区</a></h3>
          <br />
          <span class="smalltxt gray" id="desc_40">专门为授权用户提供正常工作日24小时内的快速响应支持服务。</span></td>
          <td class="tal y-style f10 e">57 / <span class="gray2 f10">179</span></td>
          <td><span class="gray2"> <span class="b">korven</span> <br />
          <span class="f9">[2009-09-16 18-51]</span></span>
		  </td>
        </tr>
      </tbody>
    </table>	
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