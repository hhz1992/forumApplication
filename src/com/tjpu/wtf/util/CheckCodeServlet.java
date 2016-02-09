package com.tjpu.wtf.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//生成图片验证码
public class CheckCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//使用GUI编程的绘图功能
		CheckCodeService ccService = new CheckCodeService();
		String code = ccService.randomString(4);
		
		//把图片上的字符串保存起来，以供验证
		request.getSession().setAttribute("check_code", code);
		
		//输出到响应输出流
		response.setContentType("image/png");
		//通过发送响应头，来禁用浏览器的缓存
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
		
		ccService.renderImage(code, response.getOutputStream(), 80, 22);
	}

}
