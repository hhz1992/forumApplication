<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://localhost:8080/wtf_bbs/admin_Login.jsp"><head><title>404</title>
<meta content="text/html; charset=UTF-8" http-equiv="content-type"/>
<meta name="generator" content="mshtml 8.00.7600.16535"/>
<meta http-equiv="refresh" content="3;url=http://localhost:8080/wtf_bbs/admin_Login.jsp"/></head>
<body style="background-image: url(images/404-1.gif); margin: 0px">
<table border="0" cellspacing="0" cellpadding="0" width="563" align="center">
  <tbody><tr><td height="125">${msg}</td></tr>
  <tr>
    <td bgcolor="#ffffff" height="195" background="<%=basePath %>/images/1.gif">
      <table border="0" cellspacing="0" cellpadding="0" align="center">
        <tbody>
        <tr><td width="210"><a href="http://localhost:8080/wtf_bbs/admin_Login.jsp"><img border="0" alt="" src="<%=basePath %>/images/csdn_itcast.png"/></a></td>
          <td style="color: #333333; font-size: 14px; font-weight: bold">页面没有找到，3秒钟之后将带您进入首页！</td></tr></tbody></table></td></tr></tbody></table>
          </body></html>