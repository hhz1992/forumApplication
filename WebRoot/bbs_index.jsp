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
		<script src="<%=basePath %>jquery/jquery-1.4.2.min.js" type="text/javascript"></script> 
		<script src="<%=basePath %>js/checkcode.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(function(){
		
		    new CheckCode($('#checkcode'),'<%=basePath%>checkcode.png');
		}
		
		)
		</script>
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
    <div id="main"><!--notice--> 
    <div id="notice"> 
    <div id="notice0" style="width: 98%; height: 18px; line-height: 18px; overflow-y: hidden;"> 
    <ul> 
      <li>发布公告</li> 
     
    </ul> 
    </div> 
    <div style="height: 36px; overflow-y: hidden;"></div> 
    </div> 
    <!--/notice--> 
    <div class="t3 cc">
    <c:if test="${memlog!=null}">
    <div id="nav-user" class="cc">
			欢迎您：${memlog.loginName }
			&nbsp;&nbsp;<c:if test="${memlog.integral<501}">【级别：菜鸟】</c:if><c:if test="${memlog.integral>500&&memlog.integral<1501}">【级别：前哨】</c:if><c:if test="${memlog.integral>1500&&memlog.integral<2001}">【级别：尉官】</c:if><c:if test="${memlog.integral>2000&&memlog.integral<2501}">【级别：大校】</c:if><c:if test="${memlog.integral>2500}">【级别：将军】</c:if>
			&nbsp;&nbsp;【<a href="<%=basePath %>/member/main.jsp">用户中心</a>】
			&nbsp;&nbsp;【<a href="<%=basePath %>/user/memberRegisteServlet?method=exit">退出</a>】
		</div>
    </c:if>
    <div id="nav-user" class="cc"> 
    
	<script type="text/javascript"> 
				$(function() {
					//$("#loginForm").validate($.validator.AlertError);
					//new JCore.CheckCode($('#checkCode'),'/CheckCode.svl');
				});
				</script> 
	<c:if test="${memlog==null}">			
    <form id="loginForm" action="<%=basePath %>/user/memberLoginServlet?method=login2" method="post"> 
      <table cellspacing="0" cellpadding="0" border="0"> 
        <tbody> 
          <tr> 
            <td><input class="uname" title="请输入用户名" maxlength="16" name="username" type="text" /></td> 
            <td><input class="pwd" title="请输入密码" type="password" maxlength="18" name="password" /></td> 
            <td><input class="yan" id="checkcode" title="请输入验证码" maxlength="10" size="12" name="checkcode" type="text" /></td> 
            <td> 
			<select name="cookieType"> 
			  <option value="4">不保存</option> 
			  <option value="3">保存1天</option> 
			  <option value="2">一个月</option> 
			  <option value="1">1年</option> 
			</select> 
			</td> 
            <td><input type="hidden" name="redirectUrl" value="http://bbs.csdn-bbs.com/index.do" /> <input type="submit" name="submit" value="登录" /></td> 
            <td>&nbsp; <a href="<%=basePath %>/user/memberRegisteServlet?method=registe">注册</a> &nbsp; <a href="<%=basePath %>/user/memberLoginServlet?method=login">登录</a></td> 
            
          </tr> 
          
        </tbody> 
      </table> 
    </form> 
    </c:if>
    </div> 
    <div class="fr">
    
    今日:${topicCountToday}  <br /> 
    主题:${count6} | 会员:${count7} | 回复:${count8 }
    
    欢迎新会员:<span class="black">
    <c:if test="${memlog!=null}">${memlog.loginName }</c:if>
     <c:if test="${memlog==null}">游客</c:if>
    </span>
    </div> 
    </div> 
    <div id="content"> 
    <div class="contentwrap z"> 
    <c:forEach items="${mapList}" var="category">
    <c:if test="${category.key.state!=2}">
    <div class="t z"> 
    
    <table width="100%" cellspacing="0" cellpadding="0"> 
      <tbody> 
        <tr> 
          <th class="h" colspan="6"><a class="closeicon fr" href="#"><img alt="" src="<%=basePath %>images/cate_fold.gif" /></a> 
          <h2>&raquo; <a class="cfont" href="#">≡csdn-bbs${category.key.name }≡</a></h2> 
 
          </th> 
        </tr> 
        <tr class="tr2"> 
          <td width="*" colspan="2" class="tac">论坛</td> 
          <td class="tal y-style e">主题 / 文章</td> 
          <td class="tal y-style f">最后发表</td> 
          <td style="width: 120px;" class="tal">版主</td> 
        </tr> 
      </tbody> 
      <c:forEach items="${category.value}" var="forum">
      <tbody> 
        <tr class="tr3 f_one"> 
        <td width="76" class="icon tac"><div class="today">
        
        <c:forEach items="${topicCount}" var="forum2">
        <c:if test="${forum2.key.id==forum.key.id}">
        ${forum2.value}
        </c:if>
          
        </c:forEach>
        <p>今日</p>  
        </div> 
          </td> 
          <td width="749"> 
          <h3 class="b"><a href="<%=basePath%>webIndexServlet?method=toForumTopicList&id=${forum.key.id }">${forum.key.name }</a></h3> 
          <br /> 
          <span class="smalltxt gray" id="desc_40">${forum.key.description }</span></td> 
          <td class="tal y-style f10 e">${forum.key.topicCount } / <span class="gray2 f10">${forum.key.articleCount }</span></td> 
          <td><span class="gray2">${forum.key.lastTopic }</span>&nbsp;<br/><span class="gray2">BY${forum.key.lastMember } <span class="b"></span> <br /> 
          <span class="f9">${forum.key.regTime }</span></span></td> 
          <c:forEach items="${forum.value}" var="member">
          <td class="y-style black" style="border-right: medium none; width: 140px;"> ${member.nickName }			&nbsp;</td> 
          </c:forEach>
        </tr> 
      </tbody> 
      
      </c:forEach>
      <tr>
		   <td colspan="3">
		          <font style="color:red;font-size:20">${msg }</font>
		   </td>
        </tr>
      <!-- 
      <tbody> 
        <tr class="tr3 f_one"> 
          <td width="76" class="icon tac">          <div class="today">0
          <p>今日</p> 
          </div> 
          </td> 
          <td width="749"> 
          <h3 class="b"><a href="http://bbs.csdn-bbs.com/fzjy/index.htm">csdn-bbs发展建议</a></h3> 
          <br /> 
          <span class="smalltxt gray" id="desc_40">您对csdn-bbs有什么好的意见和建议，请在此提出。我们会在今后的管理与新版开发过程中逐步加以解决!</span></td> 
          <td class="tal y-style f10 e">43 / <span class="gray2 f10">196</span></td> 
          <td><a href="http://bbs.csdn-bbs.com/fzjy/896.htm">csdn-bbs后台用户管理中为什么没有查询功能呢？</a> &nbsp;<br/>BY<span class="gray2"> <span class="b">flyage</span> <br /> 
          <span class="f9">[2009-09-17 20:42]</span></span></td> 
          <td class="y-style black" style="border-right: medium none; width: 140px;"> zhjc  			&nbsp;</td> 
        </tr> 
      </tbody> 
      <tbody> 
        <tr class="tr3 f_one"> 
          <td width="76" class="icon tac">          <div class="today">0
          <p>今日</p> 
          </div> 
          </td> 
          <td width="749"> 
          <h3 class="b"><a href="http://bbs.csdn-bbs.com/sqfw/index.htm">授权用户服务区</a></h3> 
          <br /> 
          <span class="smalltxt gray" id="desc_40">专门为授权用户提供正常工作日24小时内的快速响应支持服务。</span></td> 
          <td class="tal y-style f10 e">57 / <span class="gray2 f10">179</span></td> 
          <td><a href="http://bbs.csdn-bbs.com/sqfw/876.htm#pid2755">文章时间问题</a> &nbsp;<br/>BY<span class="gray2"> <span class="b">korven</span> <br /> 
          <span class="f9">[2009-09-16 18:51]</span></span></td> 
          <td class="y-style black" style="border-right: medium none; width: 140px;"> pony  			&nbsp;</td> 
        </tr> 
      </tbody>  -->
    </table> 
    </div> 
    </c:if>
    </c:forEach>
    
    
    <!-- 
    <div class="t z"> 
    <table width="100%" cellspacing="0" cellpadding="0"> 
      <tbody> 
        <tr> 
          <th class="h" colspan="6"><a class="closeicon fr" href="#"><img alt="" src="images/cate_fold.gif" /></a> 
          <h2>&raquo; <a class="cfont" href="http://bbs.csdn-bbs.com/use.htm">≡csdn-bbs使用交流区≡</a></h2> 
 
          </th> 
        </tr> 
        <tr class="tr2"> 
          <td width="*" colspan="2" class="tac">论坛</td> 
          <td class="tal y-style e">主题 / 文章</td> 
          <td class="tal y-style f">最后发表</td> 
          <td style="width: 120px;" class="tal">版主</td> 
        </tr> 
      </tbody> 
      <tbody> 
        <tr class="tr3 f_one"> 
          <td width="76" class="icon tac"> 
          <div class="todaynew">3
          <p>今日</p> 
          </div> 
</td> 
          <td width="749"> 
          <h3 class="b"><a href="http://bbs.csdn-bbs.com/fabu/index.htm">程序发布公告</a></h3> 
          <br /> 
          <span class="smalltxt gray" id="desc_40">csdn-bbs程序、补丁发布区，请随时关注新版本动态!
</span></td> 
          <td class="tal y-style f10 e">51 / <span class="gray2 f10">531</span></td> 
          <td><a href="http://bbs.csdn-bbs.com/fabu/898.htm">csdn-bbs交流群第十一群开放通告</a> &nbsp;<br/>BY<span class="gray2"> <span class="b">korven</span> <br /> 
          <span class="f9">[2009-09-18 09:59]</span></span></td> 
          <td class="y-style black" style="border-right: medium none; width: 140px;"> korven  			&nbsp;</td> 
        </tr> 
      </tbody> 
      <tbody> 
        <tr class="tr3 f_one"> 
          <td width="76" class="icon tac">          <div class="today">0
          <p>今日</p> 
          </div> 
          </td> 
          <td width="749"> 
          <h3 class="b"><a href="http://bbs.csdn-bbs.com/bug/index.htm">BUG反馈</a></h3> 
          <br /> 
          <span class="smalltxt gray" id="desc_40">专门收集网友反馈的系统bug信息，及时提供bug解决方案。</span></td> 
          <td class="tal y-style f10 e">95 / <span class="gray2 f10">240</span></td> 
          <td><a href="http://bbs.csdn-bbs.com/bug/725.htm#pid2739">图片属性设置没效果</a> &nbsp;<br/>BY<span class="gray2"> <span class="b">wuweido</span> <br /> 
          <span class="f9">[2009-09-16 15:25]</span></span></td> 
          <td class="y-style black" style="border-right: medium none; width: 140px;"> liqiang05  			&nbsp;</td> 
        </tr> 
      </tbody> 
      <tbody> 
        <tr class="tr3 f_one"> 
          <td width="76" class="icon tac">          <div class="today">0
          <p>今日</p> 
          </div> 
          </td> 
          <td width="749"> 
          <h3 class="b"><a href="http://bbs.csdn-bbs.com/azsy/index.htm">安装与使用</a></h3> 
          <br /> 
          <span class="smalltxt gray" id="desc_40">安装和使用csdn-bbs中遇到的问题大家可以在这里进行讨论</span></td> 
          <td class="tal y-style f10 e">494 / <span class="gray2 f10">1251</span></td> 
          <td><a href="http://bbs.csdn-bbs.com/azsy/895.htm">再次发帖请教单页模型中的问题</a> &nbsp;<br/>BY<span class="gray2"> <span class="b">andyzhaojianhui</span> <br /> 
          <span class="f9">[2009-09-17 16:11]</span></span></td> 
          <td class="y-style black" style="border-right: medium none; width: 140px;"> korven  			&nbsp;</td> 
        </tr> 
      </tbody> 
    </table> 
    </div> 
    
    <div class="t z"> 
    <table width="100%" cellspacing="0" cellpadding="0"> 
      <tbody> 
        <tr> 
          <th class="h" colspan="6"><a class="closeicon fr" href="#"><img alt="" src="<%=basePath %>images/cate_fold.gif" /></a> 
          <h2>&raquo; <a class="cfont" href="http://bbs.csdn-bbs.com/common.htm">≡综合技术交流区≡</a></h2> 
 
          </th> 
        </tr> 
        <tr class="tr2"> 
          <td width="*" colspan="2" class="tac">论坛</td> 
          <td class="tal y-style e">主题 / 文章</td> 
          <td class="tal y-style f">最后发表</td> 
          <td style="width: 120px;" class="tal">版主</td> 
        </tr> 
      </tbody> 
      <tbody> 
        <tr class="tr3 f_one"> 
          <td width="76" class="icon tac"> 
          <div class="todaynew">1
          <p>今日</p> 
          </div> 
</td> 
          <td width="749"> 
          <h3 class="b"><a href="http://bbs.csdn-bbs.com/freemarker/index.htm">freemarker技术专栏</a></h3> 
          <br /> 
          <span class="smalltxt gray" id="desc_40">专门讨论freemarker的相关技术问题</span></td> 
          <td class="tal y-style f10 e">121 / <span class="gray2 f10">281</span></td> 
          <td><a href="http://bbs.csdn-bbs.com/freemarker/897.htm">端口号</a> &nbsp;<br/>BY<span class="gray2"> <span class="b">lulifang216</span> <br /> 
          <span class="f9">[2009-09-18 09:12]</span></span></td> 
          <td class="y-style black" style="border-right: medium none; width: 140px;"> 			&nbsp;</td> 
        </tr> 
      </tbody> 
      <tbody> 
        <tr class="tr3 f_one"> 
          <td width="76" class="icon tac">          <div class="today">0
          <p>今日</p> 
          </div> 
          </td> 
          <td width="749"> 
          <h3 class="b"><a href="http://bbs.csdn-bbs.com/struts/index.htm">struts技术专栏</a></h3> 
          <br /> 
          <span class="smalltxt gray" id="desc_40">本板块专门讨论hibernate相关技术问题</span></td> 
          <td class="tal y-style f10 e">16 / <span class="gray2 f10">51</span></td> 
          <td><a href="http://bbs.csdn-bbs.com/struts/752.htm#pid2655">在新添加的文章内容后面显示“new”</a> &nbsp;<br/>BY<span class="gray2"> <span class="b">zhangjun460</span> <br /> 
          <span class="f9">[2009-09-14 12:53]</span></span></td> 
          <td class="y-style black" style="border-right: medium none; width: 140px;"> 			&nbsp;</td> 
        </tr> 
      </tbody> 
      <tbody> 
        <tr class="tr3 f_one"> 
          <td width="76" class="icon tac">          <div class="today">0
          <p>今日</p> 
          </div> 
          </td> 
          <td width="749"> 
          <h3 class="b"><a href="http://bbs.csdn-bbs.com/hibernate/index.htm">hibernate技术专栏</a></h3> 
          <br /> 
          <span class="smalltxt gray" id="desc_40">本板块专门讨论hibernate相关技术问题</span></td> 
          <td class="tal y-style f10 e">11 / <span class="gray2 f10">20</span></td> 
          <td><a href="http://bbs.csdn-bbs.com/hibernate/888.htm">hibernate和spring整合事务怎么控制</a> &nbsp;<br/>BY<span class="gray2"> <span class="b">axhack</span> <br /> 
          <span class="f9">[2009-09-16 22:42]</span></span></td> 
          <td class="y-style black" style="border-right: medium none; width: 140px;"> pony  			&nbsp;</td> 
        </tr> 
      </tbody> 
    </table> 
    </div> 
    
    <div class="t z"> 
    <table width="100%" cellspacing="0" cellpadding="0"> 
      <tbody> 
        <tr> 
          <th class="h" colspan="6"><a class="closeicon fr" href="#"><img alt="" src="<%=basePath %>images/cate_fold.gif" /></a> 
          <h2>&raquo; <a class="cfont" href="http://bbs.csdn-bbs.com/manage.htm">≡csdn-bbs站务管理区≡</a></h2> 
          </th> 
        </tr> 
        <tr class="tr2"> 
          <td width="*" colspan="2" class="tac">论坛</td> 
          <td class="tal y-style e">主题 / 文章</td> 
          <td class="tal y-style f">最后发表</td> 
          <td style="width: 120px;" class="tal">版主</td> 
        </tr> 
      </tbody> 
      <tbody> 
        <tr class="tr3 f_one"> 
          <td width="76" class="icon tac"><div class="today">0<p>今日</p></div></td> 
          <td width="749"> 
          <h3 class="b"><a href="http://bbs.csdn-bbs.com/station/index.htm">站务管理</a></h3> 
          <br /> 
          <span class="smalltxt gray" id="desc_40">本论坛一些重要的公告、通告和通知，任命版主，或者解除版主的职务，禁止某用户的发言，都在这里公开发布，请广大用户监督。</span></td> 
          <td class="tal y-style f10 e">0 / <span class="gray2 f10">0</span></td> 
          <td> 从未</td> 
          <td class="y-style black" style="border-right: medium none; width: 140px;"> 			&nbsp;</td> 
        </tr> 
      </tbody> 
    </table> 
    </div>  -->
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