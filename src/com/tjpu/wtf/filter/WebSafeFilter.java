/**
 * 
 */
package com.tjpu.wtf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjpu.wtf.model.Member;

/**
 * @author ZM
 *前台过滤器，保护需要登录才能操作的功能
 */
public class WebSafeFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		 
		HttpSession session=request.getSession();
		Member member=(Member)session.getAttribute("memlog");
		if(member==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else{
			arg2.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
