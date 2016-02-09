<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK" %>
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
    <div id="breadCrumb"><img alt="" align="absMiddle" src="<%=basePath %>images/home.gif" /> <a href="<%=basePath %>/index.jsp">${category.name }</a> &raquo; <a href="<%=basePath %>webIndexServlet?method=toForumTopicList&id=${forum.id }">${forum.name }</a> &raquo; <a href="#">${topic.title }</a></div>
    <div class="c"></div>
    <div class="t3">
    <c:if test="${memlog!=null}">
    <span class="fr" style="margin-left: 0.5em"><a href="<%=basePath %>bbs/bbs_post.jsp"><img alt="" src="<%=basePath %>images/post.png" /></a></span> 
    </c:if>
    <c:if test="${memlog==null}">
    <span class="fr" style="margin-left: 0.5em"><a href="<%=basePath %>login.jsp"><img alt="" src="<%=basePath %>images/post.png" /></a></span> 
    </c:if>
    
    <c:if test="${memlog!=null}">
     <span class="fr"><a href="<%=basePath %>user/replyServlet?method=toReply&topicId2=${topic.id}"><img alt="" src="<%=basePath %>images/reply.png" /></a></span>
    </c:if>
    <c:if test="${memlog==null}">
    <span class="fr"><a href="<%=basePath %>login.jsp"><img alt="" src="<%=basePath %>images/reply.png" /></a></span> 
	</c:if>
	<span class="fl"><div class="pg-3">	
				<span class="current">1</span>
				<a href="http://bbs.jeecms.com/fzjy/51_2.htm">2</a>
				<a href="http://bbs.jeecms.com/fzjy/51_3.htm">3</a>
				<a href="http://bbs.jeecms.com/fzjy/51_4.htm">4</a>
				<a href="http://bbs.jeecms.com/fzjy/51_5.htm">5</a>
				<a href="http://bbs.jeecms.com/fzjy/51_6.htm">6</a>
				<a href="http://bbs.jeecms.com/fzjy/51_7.htm">7</a>
				<a href="http://bbs.jeecms.com/fzjy/51_8.htm">8</a>
	<a href="http://bbs.jeecms.com/fzjy/51_2.htm"> > </a>
	<a href="http://bbs.jeecms.com/fzjy/51_8.htm"> >| </a>
<span class="total">共8页</span>
</div> </span>
    <div class="c"></div>
    </div>
    <div class="c"></div>
    <div class="t" style="border-bottom-width: 0pt; margin-bottom: 0pt">
    <table cellspacing="0" cellpadding="0" width="100%" style="border-bottom: rgb(169,213,233) 1px solid">
      <tbody>
        <tr>
          <td class="tal h b">${topic.title }</td>
        </tr>
      </tbody>
    </table>
    </div>
      <a name="pid133"></a>
    <div class="t5" style="border-top-width: 0pt">
    <table cellspacing="0" cellpadding="0" width="100%" style="table-layout: fixed">
      <tbody>
        <tr class="tr1">
          <th class="r_two" rowspan="2" style="padding-right: 0pt; padding-left: 0pt; padding-bottom: 2em; width: 185px; padding-top: 0pt">
          <div style="padding-right: 5px; padding-left: 12px; padding-bottom: 0pt; line-height: 24px; padding-top: 5px"><b class="fl black"><a target="_blank" href="#">${member.loginName }</a></b>
          <div class="c"></div>
          </div>
          <div class="user-pic">
          <table style="border-top-width: 0pt; border-left-width: 0pt; border-bottom-width: 0pt; border-right-width: 0pt">
            <tbody>
              <tr>
              <c:if test="${member.avatar==null}">
                <td width="1"><img class="pic" alt="" border="0" src="<%=basePath %>/images/face/none.gif" /></td>
               </c:if> 
                <c:if test="${member.avatar!=null}">
                <td width="1"><img class="pic" alt="" border="0" src="<%=basePath %>${member.avatar}" /></td>
               </c:if> 
                <td style="vertical-align: top"></td>
              </tr>
            </tbody>
          </table>
          </div>
          <div style="padding-left: 12px; padding-bottom: 8px">
          <c:if test="${member.memberIdentity==2}">
                            级别: 版主
          </c:if>
          <c:if test="${member.memberIdentity==1}">
                            级别: 会员
          </c:if>
          <br />
          <img alt="" style="display: block; margin: 0.2em 0pt" src="<%=basePath %>images/level/21.gif" /> <span class="user-info2" id="showface_0"><span class="user-infoWrap2">
          <div class="c" style="width: 120px; height: 5px"></div>
          积分:${member.integral }<br />
          人气: <span class="f12">${topic.visitCount+1 }</span><br />
          精华:${member.best } <br />
          主题:<span class="s1 f12">${member.topicCount }</span><br />
          回复:<span class="s2 f12">${member.replyCount }</span><br />
          注册时间:<span class="gray">${member.regTime }</span><br />
          </span></span></div>
          </th>
          <th class="r_one" id="td_tpc" valign="top" height="100%" style="border-top-width: 0pt; padding-right: 0px; padding-left: 0px; border-left-width: 0pt; border-bottom-width: 0pt; padding-bottom: 0px; overflow: hidden; padding-top: 0px; border-right-width: 0pt">
          <div class="tiptop"><span class="fl"><span class="b">1#</span></span> <span class="fl gray" style="white-space: nowrap">&nbsp; 发表于:${topic.pubTime } &nbsp; IP:${topic.ip}
		  </span>
          <div class="fr black" style="overflow: hidden"><a href="<%=basePath %>webIndexServlet?method=toCateTopicAuthor&id=${topic.id }">只看该作者</a> </div>
          <div class="c"></div>
          </div>
          <div class="c"></div>
        ${topic.content }
        
        <div class="c"></div>
       
       <c:forEach items="${attList}" var="att">
       <c:if test="${att.topicId==topic.id}">
      <a href="<%=basePath %>/user/memberRegisteServlet?method=down&fileName=${att.fileName }"> <strong>下载附件：</strong> ${att.fileName }</a><br/>
       </c:if>
       </c:forEach>
       
		  </th>
        </tr>
        
        
        
        <tr class="tr1 r_one">
          <th style="border-top-width: 0pt; padding-right: 0px; padding-left: 0px; border-left-width: 0pt; border-bottom-width: 0pt; padding-bottom: 0px; vertical-align: bottom; padding-top: 30px; border-right-width: 0pt">
          <div class="c" id="w_tpc"></div>
          <div class="sigline"></div>
          <div class="signature" style="overflow: hidden; height: auto; max-height: 100px">
          <table width="100%">
            <tbody>
              <tr>
                <td>${member.signature }</td>
              </tr>
            </tbody>
          </table>
          </div>
          <div class="tipad black"><span class="fr gray"><a href="javascript:scroll(0,0)">顶端</a> </span>
          <c:if test="${memlog!=null}">
          <span class="fr gray"><a href="<%=basePath %>user/replyServlet?method=copy2&topicId=${topic.id}">引用</a> </span>
          </c:if>
          <div class="fl readbot">   </div>
          <div class="c"></div>
          </div>
          </th>
        </tr>
      </tbody>
    </table>
    </div>
    
    
    <c:forEach items="${replyList}" var="reply">
    <c:if test="${reply.key.status==1}">
  <a name="pid135"></a>
    <div class="t5" style="border-top-width: 0pt">
    <table cellspacing="0" cellpadding="0" width="100%" style="table-layout: fixed">
      <tbody>
        <tr class="tr1">
          <th class="r_two" rowspan="2" style="padding-right: 0pt; padding-left: 0pt; padding-bottom: 2em; width: 185px; padding-top: 0pt">
          <div style="padding-right: 5px; padding-left: 12px; padding-bottom: 0pt; line-height: 24px; padding-top: 5px"><b class="fl black"><a target="_blank" href="#">${reply.value.loginName }</a></b>
          <div class="c"></div>
          </div>
          <div class="user-pic">
          <table style="border-top-width: 0pt; border-left-width: 0pt; border-bottom-width: 0pt; border-right-width: 0pt">
            <tbody>
              <tr>
              <c:if test="${reply.value.avatar==null}">
                <td width="1"><img class="pic" alt="" border="0" src="<%=basePath %>/images/face/none.gif" /></td>
               </c:if> 
                <c:if test="${reply.value.avatar!=null}">
                <td width="1"><img class="pic" alt="" border="0" src="<%=basePath %>${reply.value.avatar}" /></td>
               </c:if> 
                <td style="vertical-align: top"></td>
              </tr>
            </tbody>
          </table>
          </div>
          <div style="padding-left: 12px; padding-bottom: 8px">
          <c:if test="${reply.value.memberIdentity==2}">
                            级别: 版主
          </c:if>
          <c:if test="${reply.value.memberIdentity==1}">
                            级别: 会员
          </c:if>
          <br />
          <img alt="" style="display: block; margin: 0.2em 0pt" src="<%=basePath %>images/level/21.gif" /> <span class="user-info2" id="showface_0"><span class="user-infoWrap2">
          <div class="c" style="width: 120px; height: 5px"></div>
          积分:${reply.value.integral }<br />
         
          精华:${reply.value.best } <br />
          主题:<span class="s1 f12">${reply.value.topicCount }</span><br />
          回复:<span class="s2 f12">${reply.value.replyCount }</span><br />
          注册时间:<span class="gray">${reply.value.regTime }</span><br />
          </span></span></div>
          </th>
          <th class="r_one" id="td_tpc" valign="top" height="100%" style="border-top-width: 0pt; padding-right: 0px; padding-left: 0px; border-left-width: 0pt; border-bottom-width: 0pt; padding-bottom: 0px; overflow: hidden; padding-top: 0px; border-right-width: 0pt">
          <div class="tiptop"><span class="fl"><span class="b">${reply.key.floor }#</span></span> <span class="fl gray" style="white-space: nowrap">&nbsp; 发表于:${reply.key.pubTime } &nbsp; IP:${reply.key.ip}
		  </span>
		  
          <div class="fr black" style="overflow: hidden">
          <c:if test="${memlog.forumId==topic.forumId}">
          <c:if test="${memlog.memberIdentity==2}">
          <a href="<%=basePath %>webIndexServlet?method=delete&topicId=${topic.id}&replyId=${reply.key.id}">删除回复</a>
          </c:if>
          </c:if>
           </div>
          
          <div class="c"></div>
          </div>
          <div class="c"></div>
        ${reply.key.content }
		  </th>
        </tr>
        <tr class="tr1 r_one">
          <th style="border-top-width: 0pt; padding-right: 0px; padding-left: 0px; border-left-width: 0pt; border-bottom-width: 0pt; padding-bottom: 0px; vertical-align: bottom; padding-top: 30px; border-right-width: 0pt">
          <div class="c" id="w_tpc"></div>
          <div class="sigline"></div>
          <div class="signature" style="overflow: hidden; height: auto; max-height: 100px">
          <table width="100%">
            <tbody>
              <tr>
                <td>${reply.value.signature }</td>
              </tr>
            </tbody>
          </table>
          </div>
          <div class="tipad black"></span><span class="fr gray"><a href="javascript:scroll(0,0)">顶端</a> </span>
          <c:if test="${memlog!=null}">
          <span class="fr gray"><a href="<%=basePath %>user/replyServlet?method=copy&replyId=${reply.key.id}&topicId=${topic.id}">引用</a> </span>
          </c:if>
          <div class="fl readbot">   </div>
          <div class="c"></div>
          </div>
          </th>
        </tr>
      </tbody>
    </table>
    </div>
    </c:if>
  </c:forEach>
    
    <div class="t3">
    <c:if test="${memlog!=null}">
    	<span class="fr" style="margin-left: 0.5em"><a href="<%=basePath %>bbs/bbs_post.jsp"><img alt="" src="<%=basePath %>images/post.png" /></a></span> 
    </c:if>	
    <c:if test="${memlog==null}">
        <span class="fr" style="margin-left: 0.5em"><a href="<%=basePath %>login.jsp"><img alt="" src="<%=basePath %>images/post.png" /></a></span> 
    </c:if>
    	
    	<c:if test="${memlog!=null}">
    	<span class="fr"><a href="<%=basePath %>user/replyServlet?method=toReply&topicId2=${topic.id}"><img alt="" src="<%=basePath %>images/reply.png" /></a></span> 
    	</c:if>
    	<c:if test="${memlog==null}">
    	<span class="fr"><a href="<%=basePath %>login.jsp"><img alt="" src="<%=basePath %>images/reply.png" /></a></span> 
    	</c:if>
    	<span class="fl">
    		<div class="pg-3">	
	
				<span class="current">1</span>
				<a href="http://bbs.jeecms.com/fzjy/51_2.htm">2</a>
				<a href="http://bbs.jeecms.com/fzjy/51_3.htm">3</a>
				<a href="http://bbs.jeecms.com/fzjy/51_4.htm">4</a>
				<a href="http://bbs.jeecms.com/fzjy/51_5.htm">5</a>
				<a href="http://bbs.jeecms.com/fzjy/51_6.htm">6</a>
				<a href="http://bbs.jeecms.com/fzjy/51_7.htm">7</a>
				<a href="http://bbs.jeecms.com/fzjy/51_8.htm">8</a>
	<a href="http://bbs.jeecms.com/fzjy/51_2.htm"> > </a>
	<a href="http://bbs.jeecms.com/fzjy/51_8.htm"> >| </a>
<span class="total">共8页</span><tr>
		   <td colspan="3">
		          <font style="color:red;font-size:20">${msg }</font>
		   </td>
        </tr>
</div></span>
    <div class="c"></div>
    </div>
    <c:if test="${memlog!=null}">
    <c:if test="${floor!=null}">
    <c:if test="${topic.status==1||topic.status==2||topic.status==4}">
    <form id="form2" action="<%=basePath %>user/replyServlet?method=addReply" method="post">
      <div class="t" style="margin-top: 5px">
      <div><b class="h">快速回复</b></div>
      <FCK:editor instanceName="content" toolbarSet="site" height="400px" value="引用${floor}楼的回复：${content }<br>"></FCK:editor>
      <div align="center">
      <input type="submit" name="submit" value="回复"/></div>
      
      </div>
      <input type="hidden" name="topicId" value="${topic.id }"/>
      
    </form>
    </c:if>
      
    <c:if test="${topic.status==3}">
    <form id="form2" action="" method="post">
      <div class="t" style="margin-top: 5px">
      <div><b class="h">快速回复</b></div>
      <FCK:editor instanceName="content" toolbarSet="site" height="400px" value="帖子已被锁定，不可发帖"></FCK:editor>
      <div align="center">
      <input type="submit" name="submit" value="回复"/></div>
      
      </div>
      <input type="hidden" name="topicId" value="${topic.id }"/>
      
    </form>
    </c:if>
    
    </c:if>
    <c:if test="${floor==null}">
    <c:if test="${topic.status==1||topic.status==2||topic.status==4||topic.status==0}">
    <form id="form2" action="<%=basePath %>/user/replyServlet?method=addReply" method="post">
      <div class="t" style="margin-top: 5px">
      <div><b class="h">快速回复</b></div>
      <FCK:editor instanceName="content" toolbarSet="site" height="400px" ></FCK:editor>
      <div align="center">
      <input type="submit" name="submit" value="回复"/></div>
      
      </div>
      <input type="hidden" name="topicId" value="${topic.id }"/>
      
    </form>
    
    </c:if>
   
    <c:if test="${topic.status==3}">
    <form id="form2" action="" method="post">
      <div class="t" style="margin-top: 5px">
      <div><b class="h">快速回复</b></div>
      <FCK:editor instanceName="content" toolbarSet="site" height="400px" value="帖子已被锁定，不可回复"></FCK:editor>
      <div align="center">
      <input type="submit" name="submit" value="回复"/></div>
      
      </div>
      <input type="hidden" name="topicId" value="${topic.id }"/>
      
    </form>
    
    </c:if>

    </c:if>
    </c:if>
    
    
    <c:if test="${memlog==null}">
    <c:if test="${floor!=null}">
    
      <form id="form2" action="<%=basePath %>/login.jsp" method="post">
      <div class="t" style="margin-top: 5px">
      <div><b class="h">快速回复</b></div>
      <FCK:editor instanceName="content" toolbarSet="site" height="400px"></FCK:editor>
      <div align="center">
      <input type="submit" name="submit" value="回复"/></div>
      
      </div>
      <input type="hidden" name="topicId" value="${topic.id }"/>
      
    </form>
    </c:if>
    <c:if test="${floor==null}">
    <form id="form2" action="<%=basePath %>/login.jsp" method="post">
      <div class="t" style="margin-top: 5px">
      <div><b class="h">快速回复</b></div>
      <FCK:editor instanceName="content" toolbarSet="site" height="400px" ></FCK:editor>
      <div align="center">
      <input type="submit" name="submit" value="回复"/></div>
      
      </div>
      <input type="hidden" name="topicId" value="${topic.id }"/>
      
    </form>
    </c:if>
    </c:if>
    
    
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