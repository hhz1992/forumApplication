package com.tjpu.wtf.mngweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjpu.wtf.dao.MLoginLogDao;
import com.tjpu.wtf.dao.ManagerDao;
import com.tjpu.wtf.dao.impl.MLoginLogDaoImpl;
import com.tjpu.wtf.dao.impl.ManagerDaoImpl;
import com.tjpu.wtf.ip.IPSeeker;
import com.tjpu.wtf.model.MLoginLog;
import com.tjpu.wtf.model.Manager;
import com.tjpu.wtf.util.MD5;

public class ManagerLoginServlet extends HttpServlet {

	private MD5 md5=new MD5();
	/**
	 * Constructor of the object.
	 */
	public ManagerLoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method=request.getParameter("method");
		if(method!=null&&!method.equals(""))
		{//成功
			if(method.equals("mngLogin"))
			{
				mngLogin(request,response);
			}else if(method.equals("mngExit"))
			{
				mngExit(request,response);
			}
		}else{//失败
			request.setAttribute("msg", "请求有误！请重新再试！");
			request.getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}
	
	
	public void mngLogin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		
		String loginName=request.getParameter("loginName");
		String password=request.getParameter("password");
		String verCode=request.getParameter("checkCode");
		if(loginName==null||loginName.equals(""))
		{
			request.setAttribute("msg", "请输入用户名");
			request.getRequestDispatcher("/admin_Login.jsp").forward(request, response);
			
		}else if(password==null||password.equals(""))
		{
			request.setAttribute("msg", "请输入密码");
			request.getRequestDispatcher("/admin_Login.jsp").forward(request, response);
		}else if(verCode==null||verCode==""){
			request.setAttribute("msg", "请输入验证码");
			request.getRequestDispatcher("/admin_Login.jsp").forward(request, response);
			
		}else{
			//获取系统所给的验证码
			String checkCode=request.getSession().getAttribute("check_code").toString();//从CheckCodeServlet中设置check_code
			//判断用户输入的验证码是否与系统输出的验证是否相同
			if(verCode.equalsIgnoreCase(checkCode))
			{
				//成功
				//创建管理员接口实现类
				ManagerDao managerDao=new ManagerDaoImpl();
				Manager manager=managerDao.findUser(loginName, md5.getMD5ofStr(password));
				if(manager!=null&&manager.getId()>0)//表明已经查询出
				{
					if(manager.getState()==1)//如果用户状态为1则允许登录，并把用户信息写入session，并且页面跳转后台主界面
					{//把管理员登录信息写入登录日志
						//创建一个MLogin对象
						MLoginLog log=new MLoginLog();
						//给对象中的属性赋值，通过调用set方法
						log.setLoginName(loginName);
						log.setRealName(manager.getRealName());
						//获取IP地址方法
						IPSeeker seeker=IPSeeker.getInstance();
						//获取IP
						String ip=getIpAddr(request);
						//获取IP归属地
						log.setLoginIp(ip);
						log.setIpAddress(seeker.getAddress(ip));
						//创建一个MLoginLogDao对象
						MLoginLogDao logDao=new MLoginLogDaoImpl();
						boolean flag=logDao.insertLog(log);
						if(flag){//表示日志添加成功
							//获取session，并且把用户信息写入session，并且设置共享变量
							request.getSession().setAttribute("manager_admin", manager);
							//创建格式化时间类
							SimpleDateFormat foramt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							//更新管理登录次数，调用更新时间的方法
							managerDao.updateLoginCount(foramt.format(new Date()),ip,manager.getId());
							
							//页面跳转
							request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
							
						}else{
							request.getSession().setAttribute("msg","添加登录日志出现异常，请稍后再试！！");
							request.getRequestDispatcher("/admin_Login.jsp").forward(request, response);
						}
						
					}else if(manager.getState()==2){//禁用状态
						request.setAttribute("msg", "您已经被禁用，请联系管理员");
						request.getRequestDispatcher("/admin_Login.jsp").forward(request, response);
					}else if(manager.getState()==3){//离职
						request.setAttribute("msg", "您已经离职，请勿访问");
						request.getRequestDispatcher("/admin_Login.jsp").forward(request, response);
					}
				}else{//提交的密码和用户名错误
					request.setAttribute("msg", "您输入的密码或用户名有误");
					request.getRequestDispatcher("/admin_Login.jsp").forward(request, response);
				}
			}else{//验证码输入错误
				request.setAttribute("msg", "请正确输入验证码");
				request.getRequestDispatcher("/admin_Login.jsp").forward(request, response);
			}
		}
	}
	
	//获取IP地址
    
public String getIpAddr(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
public void mngExit(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{   //获取HTTPSession对象
	HttpSession session=request.getSession();
	//从session对象中移除manager_admin
	session.removeAttribute("manager_admin");
	//进行页面跳转
	request.getRequestDispatcher("/admin_Login.jsp").forward(request, response);
}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
