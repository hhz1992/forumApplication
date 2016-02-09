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
    <div id="notice0" style="overflow-y: hidden; width: 98%; line-height: 18px; height: 18px">
    <ul>
      <li><a href="/fabu/25.htm"><font color="#e10900">JEECMS v2.3.2 正式版发布公告</font></a> <span class="f9 gray">09-04-06</span></li>
      <li><a href="http://www.jeecms.com/demo/index.htm"><font color="#990000">JEECMS系统站群演示</font></a> <span class="f9 gray">09-05-31</span></li>
      <li><a href="http://www.jeecms.com/tutorial/index.htm">JEECMS系统使用教程</a> <span class="f9 gray">09-06-01</span></li>
      <li><a href="http://www.jeecms.com/download/index.htm">JEECMS系统下载中心</a> <span class="f9 gray">07-10-17</span></li>
    </ul>
    </div>
    <div style="overflow-y: hidden; height: 36px"></div>
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
    <div class="cc" id="nav-user">
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
    <div class="c"></div>
    <div class="cc" id="breadCrumb"><img align="absmiddle" style="cursor: pointer;" src="<%=basePath %>images/home.gif" alt="" /> 
	<a href="<%=basePath %>/index.jsp">${category.name }</a> &gt; <a href="<%=basePath %>webIndexServlet?method=toForumTopicList&id=${forum.id }">${forum.name }</a></div>
    <div class="c"></div>
    <div class="t">
    <table width="100%" cellspacing="0" cellpadding="0">
      <tbody>
        <tr class="tr2">
          <td><strong>板块介绍：</strong><br />
        ${forum.description }</td>
        </tr>
        <tr class="tr3">
          <td><strong>本版规则：</strong><br />
        ${forum.rule }</td>
        </tr>
      </tbody>
    </table>
    </div>
    <div id="c" class="c"></div>
    <div class="t3">
    <c:if test="${memlog!=null}">
    <span class="fr"> 	<a href="<%=basePath %>bbs/bbs_post.jsp"><img id="td_post" src="<%=basePath %>images/post.png" alt="" /></a></span>
    </c:if>
    <c:if test="${memlog==null}">
    <span class="fr"> 	<a href="<%=basePath %>login.jsp"><img id="td_post" src="<%=basePath %>images/post.png" alt="" /></a></span>
    </c:if>
      	
    <span class="fl">       <div class="pg-3">	
	
				
</div>       </span>
    <div class="c"></div>
    </div>
    <div class="c"></div>
    <div style="display: none;" class="menu menu-post cc f14 tac" id="menu_post">
    <div style="width: 75px;" class="menu-b"><a href="post-htm-fid-40.html">新 帖</a></div>
    </div>
    <div style="margin: auto;" class="t z">
	<form method="post" action="" id="forum1">
    <table width="100%" cellspacing="0" cellpadding="0">
	  <tr>
	  <th class="h" colspan="5">
	  【 <a href="http://bbs.jeecms.com/fzjy/index-1.htm">精华主题</a>】	  </th></tr>
	  <tbody style="table-layout:fixed;">
        <tr class="tr2">
          <td style="width:2.8em" class="tac y-style">&nbsp;</td>
          <td class="tac">标 题</td>
          <td style="width:10em" class="y-style">作 者</td>
          <td style="width:9em" class="tal y-style">回 复 / 人 气</td>
          <td style="width:12em" class="y-style">最后发表</td>	  
        </tr>
	      
	  <c:forEach items="${topicList}" var="topic">
	  <c:if test="${topic.status==4||topic.type==1}">
	    <c:if test="${topic.type==0}">
	        <tr align="center" class="tr3 t_one">
            <td> <c:if test="${topic.status==4}">
			   <img align="absmiddle" src="<%=basePath %>images/topic.gif" alt="" /> 
			   </c:if>
			   <c:if test="${topic.type==1}">
               <img align="absmiddle" src="<%=basePath %>images/hot.gif" alt="" />	</c:if>		</td>
			
            <td  style="text-align: left; line-height: 23px;">
				<strong>
				
				<a id="link25" href="<%=basePath %>webIndexServlet?method=toCateTopic&id=${topic.id }" style="color:#008000;">${topic.title }</a>
				
				</strong>
						</td>			
			<c:forEach items="${memberList}" var="member">
			<c:if test="${member.id==topic.memberId}">
            <td class="tal y-style">${member.loginName }<div class="f10 gray2">${topic.pubTime }</div></td>
            </c:if>
            </c:forEach>
            
            
            <td class="tal y-style f10"><span class="s8">${topic.replyCount }</span> / ${topic.visitCount }</td>
            <td class="tal y-style">${topic.lastReplyTime }<br />
            
          	<span class="b">by: ${topic.lastReplyMember }</span>
          	
            </td>
          </tr>
	     </c:if>
          <c:if test="${topic.type==1}">
	        <tr align="center" class="tr3 t_one">
            <td> <c:if test="${topic.status==4}">
			   <img align="absmiddle" src="<%=basePath %>images/topic.gif" alt="" /> 
			   </c:if>
			   <c:if test="${topic.type==1}">
               <img align="absmiddle" src="<%=basePath %>images/hot.gif" alt="" />	</c:if>		</td>
			
            <td  style="text-align: left; line-height: 23px;">
				<strong>
				
				<a id="link25" href="<%=basePath %>webIndexServlet?method=toCateTopic&id=${topic.id }" style="color:#008000;">${topic.title }</a>
				
				</strong>
						</td>			
			<c:forEach items="${memberList}" var="member">
			<c:if test="${member.id==topic.memberId}">
            <td class="tal y-style">${member.loginName }<div class="f10 gray2">${topic.pubTime }</div></td>
            </c:if>
            </c:forEach>
            
            
            <td class="tal y-style f10"><span class="s8">${topic.replyCount }</span> / ${topic.visitCount }</td>
            <td class="tal y-style">${topic.lastReplyTime }<br />
           
          	<span class="b">by: ${topic.lastReplyMember }</span>
          	
            </td>
          </tr>
	    </c:if>
	    <c:if test="${topic.type==2}">
	        
	    </c:if>
	    <c:if test="${topic.type==3}">
	        <tr align="center" class="tr3 t_one">
            <td> <c:if test="${topic.status==4}">
			   <img align="absmiddle" src="<%=basePath %>images/topic.gif" alt="" /> 
			   </c:if>
			   <c:if test="${topic.type==1}">
               <img align="absmiddle" src="<%=basePath %>images/hot.gif" alt="" />	</c:if>		</td>
			
            <td  style="text-align: left; line-height: 23px;">
				<strong>
				
				<a id="link25" href="<%=basePath %>webIndexServlet?method=toCateTopic&id=${topic.id }" style="color:#008000;">${topic.title }</a>
				
				</strong>
					</td>			
			<c:forEach items="${memberList}" var="member">
			<c:if test="${member.id==topic.memberId}">
            <td class="tal y-style">${member.loginName }<div class="f10 gray2">${topic.pubTime }</div></td>
            </c:if>
            </c:forEach>
            
            
            <td class="tal y-style f10"><span class="s8">${topic.replyCount }</span> / ${topic.visitCount }</td>
            <td class="tal y-style">${topic.lastReplyTime }<br />
            
          	<span class="b">by: ${topic.lastReplyMember }</span>
          	
            </td>
          </tr>
	    </c:if>
	    </c:if>
          </c:forEach>
          
          
          
          
          
          
		<tr class="tr2"><td colspan="6" class="tac" style="border-top:0">普通主题</td></tr>
		
		<c:forEach items="${topicList}" var="topic">
	    <c:if test="${topic.type!=1&&topic.status!=4}">
	    <c:if test="${topic.type==0}">
	        <tr align="center" class="tr3 t_one">
            <td> 
			   <img align="absmiddle" src="<%=basePath %>images/rtopic.jpg" alt="" /> 
			   
			   	</td>
			
            <td  style="text-align: left; line-height: 23px;">
				<strong>
				
				<a id="link25" href="<%=basePath %>webIndexServlet?method=toCateTopic&id=${topic.id }" style="color:#008000;">${topic.title }</a>
				
				</strong>
						</td>			
			<c:forEach items="${memberList}" var="member">
			<c:if test="${member.id==topic.memberId}">
            <td class="tal y-style">${member.loginName }<div class="f10 gray2">${topic.pubTime }</div></td>
            </c:if>
            </c:forEach>
            
            
            <td class="tal y-style f10"><span class="s8">${topic.replyCount }</span> / ${topic.visitCount }</td>
            <td class="tal y-style">${topic.lastReplyTime }<br />
            
          	<span class="b">by: ${topic.lastReplyMember }</span>
          	
            </td>
          </tr>
	     </c:if>
          <c:if test="${topic.type==1}">
	        <tr align="center" class="tr3 t_one">
            <td> 
			   <img align="absmiddle" src="<%=basePath %>images/rtopic.jpg" alt="" /> 
			   
			   
               		</td>
			
            <td  style="text-align: left; line-height: 23px;">
				<strong>
				
				<a id="link25" href="<%=basePath %>webIndexServlet?method=toCateTopic&id=${topic.id }" style="color:#008000;">${topic.title }</a>
				
				</strong>
						</td>			
			<c:forEach items="${memberList}" var="member">
			<c:if test="${member.id==topic.memberId}">
            <td class="tal y-style">${member.loginName }<div class="f10 gray2">${topic.pubTime }</div></td>
            </c:if>
            </c:forEach>
            
            
            <td class="tal y-style f10"><span class="s8">${topic.replyCount }</span> / ${topic.visitCount }</td>
            <td class="tal y-style">${topic.lastReplyTime }<br />
           
          	<span class="b">by: ${topic.lastReplyMember }</span>
          	
            </td>
          </tr>
	    </c:if>
	    <c:if test="${topic.type==2}">
	        
	    </c:if>
	    <c:if test="${topic.type==3}">
	        <tr align="center" class="tr3 t_one">
            <td> 
			   <img align="absmiddle" src="<%=basePath %>images/rtopic.jpg" alt="" /> 
			   
			   	</td>
			
            <td  style="text-align: left; line-height: 23px;">
				<strong>
				
				<a id="link25" href="<%=basePath %>webIndexServlet?method=toCateTopic&id=${topic.id }" style="color:#008000;">${topic.title }</a>
				
				</strong>
						</td>			
			<c:forEach items="${memberList}" var="member">
			<c:if test="${member.id==topic.memberId}">
            <td class="tal y-style">${member.loginName }<div class="f10 gray2">${topic.pubTime }</div></td>
            </c:if>
            </c:forEach>
            
            
            <td class="tal y-style f10"><span class="s8">${topic.replyCount }</span> / ${topic.visitCount }</td>
            <td class="tal y-style">${topic.lastReplyTime }<br />
            
          	<span class="b">by: ${topic.lastReplyMember }</span>
          	
            </td>
          </tr>
	    </c:if>
	    </c:if>
          </c:forEach>
        
        
        
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
        

        <tr>
          <td style="height: 8px;" class="f_one" colspan="6"></td>
        </tr>
      </tbody>
    </table>
	<input type="hidden" value="2" name="forumId" />
    </form>
    </div>
    <div class="t3">
    <table width="100%" cellspacing="0" cellpadding="0">
      <tbody>
        <tr>
          <td>
          <form method="post" action="#">
			<select name="search">
            <option value="1">1天内的主题</option>
            <option value="digest">本版精华区</option>
            <option value="1">1天内的主题</option>
            <option value="2">2天内的主题</option>
            <option value="7">1星期内的主题</option>
            <option value="30">1个月内的主题</option>
            <option value="60">2个月内的主题</option>
            <option value="90">3个月内的主题</option>
            <option value="180">6个月内的主题</option>
            <option value="365">1年内的主题</option>
            </select>
			<input type="button" onclick="this.form.submit();" value="提 交" class="btn" />
          </form>
          </td>
          <td width="40%" align="right">
          <form method="post" name="jump">
            <a href="javascript:scroll(0,0)">版块权限查看</a>
          </form>
          </td>
        </tr>
      </tbody>
    </table>
    </div>
    <div class="t3">
	<c:if test="${memlog!=null}">
    <span class="fr"> 	<a href="<%=basePath %>bbs/bbs_post.jsp"><img id="td_post" src="<%=basePath %>images/post.png" alt="" /></a></span>
    </c:if>
    <c:if test="${memlog==null}">
    <span class="fr"> 	<a href="<%=basePath %>login.jsp"><img id="td_post" src="<%=basePath %>images/post.png" alt="" /></a></span>
    </c:if>  	     <span class="fl"><div class="pg-3">	
	
				<t:pager pageSize="${pm.pageSize}" pageNo="${pm.pageNo}" url="${pageContext.request.contextPath}/webIndexServlet?method=toForumTopicList" recordCount="${pm.recordCount}"/>
</div></span>
    <div class="c"></div>
    </div>
    <div class="c"></div>
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