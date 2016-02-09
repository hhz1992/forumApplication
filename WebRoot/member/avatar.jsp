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
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/front.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/whole.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/layout.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/set.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>jquery/imgareaselect-css/imgareaselect-animated.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>jquery/uploadify/uploadify.css" />
	<script language="javascript" src="<%=basePath %>jquery/jquery-1.4.3.min.js"></script> 
	<script language="javascript" src="<%=basePath %>jquery/uploadify/swfobject.js"></script>
	<script language="javascript" src="<%=basePath %>jquery/uploadify/jquery.uploadify.v2.1.0.min.js"></script>
	<script language="javascript" src="<%=basePath %>jquery/jquery.imgareaselect.pack.js"></script>
	<script src="<%=basePath %>jquery/jquery.blockUI.js" type="text/javascript"></script>
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
		document.creator.proicon.value = "images/face/"+value;//images/face/01.gif
		return false;
	}
	</script>
	<script language="javascript">
	$(document).ready(function() {
		$(document).ajaxStop($.unblockUI); //Ajax请求结束后，关闭等待提示
		//文件上传
		$("#avatar").uploadify({
			'uploader'       : '<%=basePath %>jquery/uploadify/uploadify.swf', //flash用于打开选取本地文件的按钮 
			'script'         : '<%=basePath %>avatar/test_avatarfile.action', //处理上传的路径
			'cancelImg'      : '<%=basePath %>jquery/uploadify/cancel.png', //取消上传文件的按钮图片
			'fileDataName'   : 'avatar', //和input的name属性值保持一致
			'folder'         : '<%=basePath %>tmp/', //服务器保存文件的路径
			'fileDesc'       : 'images', //文件描述
			'fileExt'        : '*.jpg;*.jpeg;*.png;*.gif;*.bmp', //文件扩展名列表
			'scriptData'     : {'id':'2'}, //请求参数
			'displayData'    : 'speed', //有speed和percentage两种，一个显示速度，一个显示完成百分比
			'buttonText'     : 'Browse Image', //出现在Flash上的文字，暂时还不支持中文 
			'auto'           : true, //是否选取文件后自动上传 
			'multi'          : false, //是否支持多文件上传 
			'sizeLimit'      : 1048576, //文件最大上传大小：1M
			'simUploadLimit' : 1, //每次最大上传文件数
			'onComplete'     : function(e, queueId, fileObj, response){ //文件上传完成后触发
				var json = $.parseJSON(response);
				if(json != null && json.opt == "ok"){
					var fileName = "<%=basePath %>tmp/" + json.fileName;
					$("#srcImg").attr("src", fileName); //设置要切割的源图片
					$("#minImg").attr("src", fileName); //设置预览图片
					$("#srcName").val(json.fileName); //设置隐藏表单源图片名
					$("#oimgW").html(json.width); //设置源图片宽
					$("#oimgH").html(json.height); //设置源图片高
					$("#cutTbl").show(); //显示裁切图片的表格
					window.setTimeout(function(){imgSelect(json.width, json.height);}, 100);
				}else{
					alert(json.msg);
				}
			} 
			
		});
		
		//提交最终的表单
		$("#cutForm").submit(function(){
			var $cf = $(this);
			$.post($cf.attr("action") + "?d=" + new Date().getTime(), $cf.serialize(), function(data){
				$.blockUI({ css: {border: 'none',padding: '5px',backgroundColor: '#000','-webkit-border-radius': '10px','-moz-border-radius': '10px',opacity: 0.6,color: '#fff'},
					message: '<h1><img src="<%=basePath %>images/loading.gif" align="absmiddle"/>&nbsp;&nbsp;操作正在进行中，请稍等．．．</h1>'});
				if(data != null && data.opt=="ok"){
					$("#myAvatar").attr("src", "<%=basePath %>upload/avatar/" + data.fileName);
					window.setTimeout(function(){alert("头像修改成功!");}, 300);
				}else{
					alert("操作失败!");
				}
			},"json");
			return false;
		});
	});
	var minWidth = 100;
	var minHeight = 100;
	var scale = 1; //放大倍数
	function imgSelect(srcW, srcH){ //图片裁切选择
		if(srcW<minWidth||srcH<minHeight) {
			alert("源图尺寸小于缩略图！");
			return;
		}
		if(srcW==minWidth&&srcW==minHeight) {
			alert("源图和缩略图尺寸一致！");
			return;
		}
		$('#srcImg').imgAreaSelect({
			minWidth         : minWidth, //最小选择宽度
			minHeight        : minHeight,//最小选择高度
			aspectRatio      : minWidth+':'+minHeight,
			x1               : 0,
			y1               : 0,
			x2               : minWidth,
			y2               : minHeight,
			onSelectChange   : function(img, selection){ //选择区域时的回调函数
				var scaleX = minWidth / selection.width;
				var scaleY = minHeight / selection.height;
				$('#minImg').css({ width: Math.round(scaleX * srcW * scale) + 'px', height: Math.round(scaleY * srcH * scale) + 'px', marginLeft: '-' + Math.round(scaleX * selection.x1) + 'px', marginTop: '-' + Math.round(scaleY * selection.y1) + 'px' });
				$('#cutX').val(selection.x1);
				$('#cutY').val(selection.y1);
				$('#cutW').val(selection.width);
				$('#cutH').val(selection.height);
			}
		});
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
      <td><a href="/" class="fl"><img src="../images/csdn_itcast.png" alt="" /></a></td>
      <td>&nbsp;&nbsp;&nbsp;</td>  
      <td id="banner"><img src="../images/800X60.gif" alt="" /></td> 
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
    <div id="breadCrumb"><img alt="" align="absMiddle" style="cursor: pointer" src="<%=basePath %>images/home.gif" /> <a href="<%=basePath %>/index.jsp">bbs首页</a>&raquo;<a href="<%=basePath %>/member/main.jsp">用户中心</a></div>
    <div id="set-wrap">
    <div id="set-side">
    <div id="set-side-wrap">
    <h2 class="set-h2"><a class="color8" href="<%=basePath %>/member/main.jsp">设置中心</a></h2>
    <ul id="set-menu">
       <li id="pro_modify"><a href="<%=basePath %>/user/memberRegisteServlet?method=editInfo">编辑资料</a></li>
      <li id="pro_modify"><a href="<%=basePath%>member/password.jsp">修改密码</a></li>
      <li class="current" id="pro_modify"><a href="<%=basePath%>member/avatar.jsp">修改头像</a></li>
      <li id="pro_modify"><a href="<%=basePath%>webIndexServlet?method=mytopic">我的主题</a></li>
      <li id="pro_modify"><a href="<%=basePath %>webIndexServlet?method=myReply">我的回复</a></li>
      <li id="pro_modify"><a href="<%=basePath %>/user/memberRegisteServlet?method=attachmentList">我的附件</a></li>
      <li id="pro_modify"><a href="<%=basePath %>/bbs_index.jsp">退 出</a></li>
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
          <td class="current" id="tb_11"><a onclick="HoverLi(1,1,2);" style="cursor: pointer">系统头像</a></td>
          <td id="tb_12"><a onclick="HoverLi(1,2,2);" style="cursor: pointer">自定义头像</a></td>
        </tr>
      </tbody>
    </table>
    </div>
      <div class="set-tab-box">
       <form id="creator" action="<%=basePath %>user/memberRegisteServlet?method=systemAvatar" method="post" onsubmit="return procheck(this)" name="creator">
      <div class="dis" id="tbc_11">
       <table class="set-table2" cellspacing="0" cellpadding="0" width="100%" border="0">
        <tbody>
          <tr>
          <c:if test="${memlog.avatar==null}">
            <td class="td1" width="25%"><img height="80" width="80" name="useravatars" alt="" src="<%=basePath %>/images/face/none.gif" /></td>
            </c:if>
             <c:if test="${memlog.avatar!=null}">
            <td class="td1" width="25%"><img height="80" width="80" name="useravatars" alt="" src="<%=basePath%>${memlog.avatar}" /></td>
            </c:if>
            <td>
            <div id="openchang" style="margin-top: 1%; float: left; width: 100%">
            <div style="float: left; margin-right: 1%">点击选择系统头像</div>
            <div id="face1" style="margin-top: 1%; float: left; width: 100%">
            <div id="iconbox">
            <div style="width: 272px"><span class="fl face" onclick="showimage('<%=basePath %>images','01.gif')"><img height="50" width="50" alt="" src="<%=basePath%>images/face/01.gif" /></span> <span class="fl face" onclick="showimage('<%=basePath %>images','02.gif')"><img height="50" width="50" alt="" src="<%=basePath%>images/face/02.gif" /></span> <span class="fl face" onclick="showimage('<%=basePath %>images','03.gif')"><img height="50" width="50" alt="" src="<%=basePath%>images/face/03.gif" /></span> <span class="fl face" onclick="showimage('<%=basePath %>images','04.gif')"><img height="50" width="50" alt="" src="<%=basePath%>images/face/04.gif" /></span> <span class="fl face" onclick="showimage('<%=basePath %>images','05.gif')"><img height="50" width="50" alt="" src="<%=basePath%>images/face/05.gif" /></span> <span class="fl face" onclick="showimage('<%=basePath %>images','06.gif')"><img height="50" width="50" alt="" src="<%=basePath%>images/face/06.gif" /></span> <span class="fl face" onclick="showimage('<%=basePath %>images','07.gif')"><img height="50" width="50" alt="" src="<%=basePath%>images/face/07.gif" /></span> <span class="fl face" onclick="showimage('<%=basePath %>images','08.gif')"><img height="50" width="50" alt="" src="<%=basePath%>images/face/08.gif" /></span> <span class="fl face" onclick="showimage('<%=basePath %>images','09.gif')"><img height="50" width="50" alt="" src="<%=basePath%>images/face/09.gif" /></span> <span class="fl face" onclick="showimage('<%=basePath %>images','10.gif')"><img height="50" width="50" alt="" src="<%=basePath%>images/face/10.gif" /></span></div>
            </div>
            <input type="hidden" id="proicon" name="avatar" value="none.gif"/></div>
            </div>
            </td>
          </tr>
        </tbody>
      </table>
       <div class="tac" style="padding-right: 1em; padding-left: 1em; padding-bottom: 1em; padding-top: 1em"><input class="btn" type="submit" name="submit" value="上传头像" /><font style="color:red;font-size:16px">${msg }</font></div> 
      </div>
    
      </form>
      <div class="undis" id="tbc_12">
       <table class="set-table2" cellspacing="0" cellpadding="0" width="100%" border="0">
        <tr>
            <td class="td1" style="width:120px">我的头像</td>
            <c:if test="${memlog.avatar!=null}">
            <td><img id="myAvatar" src="<%=basePath %>${memlog.avatar}" alt="我的头像" title="我的头像" /> </td>
            </c:if>
            <c:if test="${memlog.avatar==null}">
            <td><img id="myAvatar" src="<%=basePath %>/images/face/none.gif" alt="我的头像" title="我的头像" /> </td>
            </c:if>
          </tr>
          <tr>
            <td class="td1" style="width:120px">选择新头像文件</td>
            <td><input id="avatar" name="avatar" type="file" />	</td>
          </tr>
	      </table>
	      <form id="cutForm" action="<%=basePath %>avatar/test_cut.action" method="post">
 				<input type="hidden" id="srcName" name="srcName" value="none.gif"/>
				<input type="hidden" id="cutX" name="cutX" value="0"/>
				<input type="hidden" id="cutY" name="cutY" value="0"/>
				<input type="hidden" id="cutW" name="cutW" value="100"/>
				<input type="hidden" id="cutH" name="cutH" value="100"/>
	      <table id="cutTbl" class="set-table2" style="display: none" cellspacing="0" cellpadding="0" width="100%" border="0">
          <tr>
            <td class="td1" style="width:80px;text-align: right">切割头像</td>
            <td class="td1" style="width:500px;text-align: left">原图宽：<span id="oimgW"></span>&nbsp;高：<span id="oimgH"></span></td>
            <td class="td1" style="text-align: left">缩略图宽：100&nbsp;高：100</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><div style="width:500px;border:1px solid #333"><img id="srcImg" src="<%=basePath %>upload/avatar/none.gif" alt="要切割的源图片" title="要切割的源图片"/></div></td>
            <td><div id="imgDiv" style="overflow:hidden;width:100px;height:100px;border:1px solid #333;">
            	<img id="minImg" src="<%=basePath %>upload/avatar/none.gif" alt="预览图片" title="预览图片"/></div></td>
          </tr>
	      </table>
	      
	      <div style="text-align:center;margin-top:7px;"><input class="btn" type="submit" value="确认剪裁" /></div>
	      </form>
      </div>
     
      </div>
    
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