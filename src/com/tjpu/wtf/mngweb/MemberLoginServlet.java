package com.tjpu.wtf.mngweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjpu.wtf.dao.CategoryDao;
import com.tjpu.wtf.dao.ForumDao;
import com.tjpu.wtf.dao.MLoginLogDao;
import com.tjpu.wtf.dao.ManagerDao;
import com.tjpu.wtf.dao.MemLoginLogDao;
import com.tjpu.wtf.dao.MemberDao;
import com.tjpu.wtf.dao.ReplyDao;
import com.tjpu.wtf.dao.TopicDao;
import com.tjpu.wtf.dao.impl.CategoryDaoImpl;
import com.tjpu.wtf.dao.impl.ForumDaoImpl;
import com.tjpu.wtf.dao.impl.MLoginLogDaoImpl;
import com.tjpu.wtf.dao.impl.ManagerDaoImpl;
import com.tjpu.wtf.dao.impl.MemLoginLogDaoImpl;
import com.tjpu.wtf.dao.impl.MemberDaoImpl;
import com.tjpu.wtf.dao.impl.ReplyDaoImpl;
import com.tjpu.wtf.dao.impl.TopicDaoImpl;
import com.tjpu.wtf.ip.IPSeeker;
import com.tjpu.wtf.model.Category;
import com.tjpu.wtf.model.Forum;
import com.tjpu.wtf.model.MLoginLog;
import com.tjpu.wtf.model.Manager;
import com.tjpu.wtf.model.MemLoginLog;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.util.MD5;

public class MemberLoginServlet extends HttpServlet {

	private MD5 md5=new MD5();
	/**
	 * Constructor of the object.
	 */
	public MemberLoginServlet() {
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
			if(method.equals("login"))
			{
				memLogin(request,response);
			}else if(method.equals("login2")){
				memLogin2(request,response);
			}
		}else{//失败
			request.setAttribute("msg", "请求有误！请重新再试！");
			request.getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}
	
	
	private void memLogin2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String loginName=request.getParameter("username");
		String password=request.getParameter("password");
		String verCode=request.getParameter("checkcode");
		if(loginName==null||loginName.equals(""))
		{
			request.setAttribute("log", "请输入用户名");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}else if(password==null||password.equals(""))
		{
			request.setAttribute("log", "请输入密码");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else if(verCode==null||verCode==""){
			request.setAttribute("log", "请输入验证码");
			request.getRequestDispatcher("/log.jsp").forward(request, response);
			
		}else{
			//获取系统所给的验证码
			String checkCode=request.getSession().getAttribute("check_code").toString();//从CheckCodeServlet中设置check_code
			//判断用户输入的验证码是否与系统输出的验证是否相同
			if(verCode.equalsIgnoreCase(checkCode))
			{
				//成功
				//创建用户接口实现类
				MemberDao memberDao=new MemberDaoImpl();
				ReplyDao replyDao=new ReplyDaoImpl();
				Member member=memberDao.findUser(loginName, md5.getMD5ofStr(password));
				if(member!=null&&member.getId()>0)//表明已经查询出
				{
					if(member.getStatus()==1||member.getStatus()==4)//如果用户状态为1则允许登录，并把用户信息写入session，并且页面跳转后台主界面
					{//把用户登录信息写入登录日志
						//创建一个MemLogin对象
						MemLoginLog log=new MemLoginLog();
						//给对象中的属性赋值，通过调用set方法
						log.setLoginName(loginName);
						log.setNickName(member.getNickName());
						//获取IP地址方法
						IPSeeker seeker=IPSeeker.getInstance();
						//获取IP
						String ip=getIpAddr(request);
						//获取IP归属地
						log.setLoginIp(ip);
						log.setIpAddress(seeker.getAddress(ip));
						//创建一个MemLoginLogDao对象
						MemLoginLogDao logDao=new MemLoginLogDaoImpl();
						boolean flag=logDao.insertLog(log);
						if(flag){//表示日志添加成功
					
							//创建格式化时间类
							SimpleDateFormat foramt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String lastVisitTime=foramt.format(new Date());
							member.setLastVisitTime(lastVisitTime);
							//更新管理登录次数，调用更新时间的方法
							memberDao.updateLoginCount(lastVisitTime,ip,member.getId());
							
							ForumDao forumDao=new ForumDaoImpl();
							//显示页面的方法
							//设置键值对
							Map<Category,List<Forum>> map=new LinkedHashMap<Category,List<Forum>>();
							/*
							 * 设置键值对，在bbs_index中用于显示分区和板块列表
							 */
							CategoryDao categoryDao=new CategoryDaoImpl();
							//获取所有的分区信息
							List<Category> categoryList=categoryDao.findAll();
							//通过分区的id设置键值对
							for(int i=0;i<categoryList.size();i++)
							{
								//获取第i个分区的信息
								Category category=categoryList.get(i);
								//找出所有分区号为category.getId()的板块信息
								List<Forum> forumList2=forumDao.findAllById(category.getId());
								//设置键值对
								map.put(category, forumList2);
							}
							request.getSession().setAttribute("cateForumMap", map);
							
							//获取MemberId获取该member一共发帖的数量
							TopicDao topicDao=new TopicDaoImpl();
							int count2=0;
							count2=topicDao.memberTopicCount(member.getId());
							request.getSession().setAttribute("count2", count2);
							
							//获取今日的发帖数量，并且设置session
							//获取当前日期，并且进行格式化
							SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
							int count5=topicDao.findAllTopicCountToday(format.format(new Date()));
							request.getSession().setAttribute("topicCountToday", count5);
							
							//获取MemberId，得到该member精华帖数目
							int count3=0;
							count3=topicDao.memberTopicBestCount(member.getId());
							request.getSession().setAttribute("count3", count3);
							
							//设置session，获取总主题数
							int count6=0;
							count6=topicDao.findAllTopicCount();
							System.out.println(count6);
							request.getSession().setAttribute("count6", count6);
							
							//设置session，获取总会员数
							int count7=0;
							count7=memberDao.findAllMember();
							request.getSession().setAttribute("count7", count7);
							
							//设置session，获取总的回复数
							int count8=0;
							count8=replyDao.findAllReplyCount();
							request.getSession().setAttribute("count8", count8);
							
							//页面跳转，并且设置共享变量，施行跳转
							request.getSession().setAttribute("memlog", member);
							request.getRequestDispatcher("/index.jsp").forward(request, response);
							
						}else{
							request.getSession().setAttribute("msg","添加登录日志出现异常，请稍后再试！！");
							request.getRequestDispatcher("/login.jsp").forward(request, response);
						}
						
					}else if(member.getStatus()==2){//禁用状态
						request.setAttribute("log", "您已经被禁用，请联系管理员");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}else if(member.getStatus()==3){//屏蔽
						request.setAttribute("log", "您已经屏蔽，请勿访问");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}else{
						request.setAttribute("log", "您状态有误，请勿访问");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
				}else{//提交的密码和用户名错误
					request.setAttribute("log", "您输入的密码或用户名有误");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}else{//验证码输入错误
				request.setAttribute("log", "请正确输入验证码");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	}

	public void memLogin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		
		String loginName=request.getParameter("username");
		String password=request.getParameter("password");
		String verCode=request.getParameter("checkCode");
		if(loginName==null||loginName.equals(""))
		{
			request.setAttribute("log", "请输入用户名");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}else if(password==null||password.equals(""))
		{
			request.setAttribute("log", "请输入密码");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else if(verCode==null||verCode==""){
			request.setAttribute("log", "请输入验证码");
			request.getRequestDispatcher("/log.jsp").forward(request, response);
			
		}else{
			//获取系统所给的验证码
			String checkCode=request.getSession().getAttribute("check_code").toString();//从CheckCodeServlet中设置check_code
			//判断用户输入的验证码是否与系统输出的验证是否相同
			if(verCode.equalsIgnoreCase(checkCode))
			{
				//成功
				//创建用户接口实现类
				MemberDao memberDao=new MemberDaoImpl();
				ReplyDao replyDao=new ReplyDaoImpl();
				Member member=memberDao.findUser(loginName, md5.getMD5ofStr(password));
				if(member!=null&&member.getId()>0)//表明已经查询出
				{
					if(member.getStatus()==1||member.getStatus()==4)//如果用户状态为1则允许登录，并把用户信息写入session，并且页面跳转后台主界面
					{//把用户登录信息写入登录日志
						//创建一个MemLogin对象
						MemLoginLog log=new MemLoginLog();
						//给对象中的属性赋值，通过调用set方法
						log.setLoginName(loginName);
						log.setNickName(member.getNickName());
						//获取IP地址方法
						IPSeeker seeker=IPSeeker.getInstance();
						//获取IP
						String ip=getIpAddr(request);
						//获取IP归属地
						log.setLoginIp(ip);
						log.setIpAddress(seeker.getAddress(ip));
						//创建一个MemLoginLogDao对象
						MemLoginLogDao logDao=new MemLoginLogDaoImpl();
						boolean flag=logDao.insertLog(log);
						if(flag){//表示日志添加成功
					
							//创建格式化时间类
							SimpleDateFormat foramt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String lastVisitTime=foramt.format(new Date());
							member.setLastVisitTime(lastVisitTime);
							//更新管理登录次数，调用更新时间的方法
							memberDao.updateLoginCount(lastVisitTime,ip,member.getId());
							
							ForumDao forumDao=new ForumDaoImpl();
							//显示页面的方法
							//设置键值对
							Map<Category,List<Forum>> map=new LinkedHashMap<Category,List<Forum>>();
							/*
							 * 设置键值对，在bbs_index中用于显示分区和板块列表
							 */
							CategoryDao categoryDao=new CategoryDaoImpl();
							//获取所有的分区信息
							List<Category> categoryList=categoryDao.findAll();
							//通过分区的id设置键值对
							for(int i=0;i<categoryList.size();i++)
							{
								//获取第i个分区的信息
								Category category=categoryList.get(i);
								//找出所有分区号为category.getId()的板块信息
								List<Forum> forumList2=forumDao.findAllById(category.getId());
								//设置键值对
								map.put(category, forumList2);
							}
							request.getSession().setAttribute("cateForumMap", map);
							
							//获取MemberId获取该member一共发帖的数量
							TopicDao topicDao=new TopicDaoImpl();
							int count2=0;
							count2=topicDao.memberTopicCount(member.getId());
							request.getSession().setAttribute("count2", count2);
							
							//获取今日的发帖数量，并且设置session
							//获取当前日期，并且进行格式化
							SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
							int count5=topicDao.findAllTopicCountToday(format.format(new Date()));
							request.getSession().setAttribute("topicCountToday", count5);
							
							//获取MemberId，得到该member精华帖数目
							int count3=0;
							count3=topicDao.memberTopicBestCount(member.getId());
							request.getSession().setAttribute("count3", count3);
							
							//设置session，获取总主题数
							int count6=0;
							count6=topicDao.findAllTopicCount();
							System.out.println(count6);
							request.getSession().setAttribute("count6", count6);
							
							//设置session，获取总会员数
							int count7=0;
							count7=memberDao.findAllMember();
							request.getSession().setAttribute("count7", count7);
							
							//设置session，获取总的回复数
							int count8=0;
							count8=replyDao.findAllReplyCount();
							request.getSession().setAttribute("count8", count8);
							
							//页面跳转，并且设置共享变量，施行跳转
							request.getSession().setAttribute("memlog", member);
							request.getRequestDispatcher("/index.jsp").forward(request, response);
							
						}else{
							request.getSession().setAttribute("msg","添加登录日志出现异常，请稍后再试！！");
							request.getRequestDispatcher("/login.jsp").forward(request, response);
						}
						
					}else if(member.getStatus()==2){//禁用状态
						request.setAttribute("log", "您已经被禁用，请联系管理员");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}else if(member.getStatus()==3){//屏蔽
						request.setAttribute("log", "您已经屏蔽，请勿访问");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}else{
						request.setAttribute("log", "您状态有误，请勿访问");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
				}else{//提交的密码和用户名错误
					request.setAttribute("log", "您输入的密码或用户名有误");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}else{//验证码输入错误
				request.setAttribute("log", "请正确输入验证码");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
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
	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
