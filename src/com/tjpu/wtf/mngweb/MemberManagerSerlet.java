package com.tjpu.wtf.mngweb;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjpu.wtf.dao.CategoryDao;
import com.tjpu.wtf.dao.ForumDao;
import com.tjpu.wtf.dao.MemberDao;
import com.tjpu.wtf.dao.impl.CategoryDaoImpl;
import com.tjpu.wtf.dao.impl.ForumDaoImpl;
import com.tjpu.wtf.dao.impl.MemberDaoImpl;
import com.tjpu.wtf.model.Category;
import com.tjpu.wtf.model.Forum;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;

public class MemberManagerSerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MemberManagerSerlet() {
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

		//获取method 方法
		String method=request.getParameter("method");
		//判断method方法
		if(method!=null&&!method.equals(""))
		{//成功
			if(method.equals("memberList"))
			{
				memberList(request,response);
			}else if(method.equals("toUpdate")){
				toUpdate(request,response);
			}else if(method.equals("update")){
				update(request,response);
			}else if(method.equals("delete")){
				delete(request,response);
			}else if(method.equals("setConfig")){
				setConfigInfo(request,response);
			}else if(method.equals("setIdentity")){
				setIdentity(request,response);
			}else if(method.equals("serach")){
				serach(request,response);
			}
		}else{//失败
			request.setAttribute("msg", "请求有误！请重新再试！");
			request.getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}

	private void serach(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		// TODO Auto-generated method stub
		String  loginName=request.getParameter("loginName");
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		MemberDao dao=new MemberDaoImpl();
		//获取用户的列表
		PageModel<Member> pm=dao.findAllByLoginName2(loginName,Integer.parseInt(pageNo),pageSize);
		List<Member> list=pm.getDatas();
		if(list.size()==0){
			request.setAttribute("msg", "您输入的登录名不存在，请确认后输入");
		}
		//设置共享变量
		request.setAttribute("pm",pm);
		request.setAttribute("memberList", list);
		//跳转
		request.getRequestDispatcher("/admin/member/memberSerachList.jsp").forward(request, response);
	}

	private void setIdentity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取板块id
		String id=request.getParameter("id");
		String forumId=request.getParameter("forumId");
		//获取身份
		String memberIdentity=request.getParameter("memberIdentity");
		//验证
		if(forumId==null||forumId.equals("")){
			request.setAttribute("msg", "板块id获取失败，请重试");
			request.getRequestDispatcher("/admin/member/setMemberConfig.jsp").forward(request, response);
		}else if(memberIdentity==null||memberIdentity.equals("")){
			request.setAttribute("msg", "版主信息获取失败，请重试");
			request.getRequestDispatcher("/admin/member/setMemberConfig.jsp").forward(request, response);
		}else if(id==null||id.equals("")){
			request.setAttribute("msg", "会员id获取失败，请重试");
			request.getRequestDispatcher("/admin/member/setMemberConfig.jsp").forward(request, response);
		}else{
			Forum forum=new Forum();
			ForumDao dao2=new ForumDaoImpl();
			//根据forumId找到forum的所有信息
			forum=dao2.findById(Integer.parseInt(forumId));
			//从forum中获取名字，为member中的forumName设置名字
			String name2=forum.getName();
			int identity=Integer.parseInt(memberIdentity);
			
			Member member=new Member();
			MemberDao dao=new MemberDaoImpl();
			//为member设置身份
			member.setMemberIdentity(identity);
			
			if(identity==2){//若是版主，则为member设置板块号和板块名字
				member.setForumId(Integer.parseInt(forumId));
				member.setForumName(name2);
			}else{//若是会员，则为member板块号为0和板块名字为空
				member.setForumId(0);	
				member.setForumName(null);
			}
			
			
			member.setId(Integer.parseInt(id));
			//更新版主,会员,板块名等信息
			boolean flag=dao.updateMemberIdentity(member);
			if(flag)
			{//更新成功，重新获取member列表
				memberList(request, response);
			}else{
				request.setAttribute("msg", "版主设置失败，请重试");
				request.getRequestDispatcher("/admin/member/setMemberConfig.jsp").forward(request, response);
			}
			
		}
	}

	private void setConfigInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取页面传来的id
		String id=request.getParameter("id");
		if(id==null||id.equals("")){
			request.setAttribute("msg", "id获取失败，请重试");
			memberList(request, response);
		}else{
			//查询出所有板块信息
			ForumDao forumDao=new ForumDaoImpl();
			//显示页面的方法
			//设置键值对
			Map<Category,List<Forum>> map=new LinkedHashMap<Category,List<Forum>>();
			
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
			request.setAttribute("mapList", map);
			//根据id查询会员信息
			MemberDao memberdao=new MemberDaoImpl();
			//根据id找到member
			Member member=memberdao.findById(Integer.parseInt(id));
			//再次设置共享变量，进行页面跳转
			request.setAttribute("member", member);
			request.getRequestDispatcher("/admin/member/setMemberConfig.jsp").forward(request, response);
			
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取id属性值
		String id=request.getParameter("id");
		//创建类接口实现对象
		MemberDao dao=new MemberDaoImpl();
		//根据id调用delete方法，删除某一id的数据
		boolean flag=dao.delete(Integer.parseInt(id));
		if(flag)
		{
			//设置session，获取总会员数
			int count7=0;
			count7=dao.findAllMember();
			request.getSession().setAttribute("count7", count7);
			//删除成功，显示新页面
			memberList(request,response);
		}else{//删除失败
			request.setAttribute("msg", "删除失败，请重试");
			request.getRequestDispatcher("/admin/member/memberList.jsp").forward(request, response);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取id和state值
		String id=request.getParameter("id");
		String status=request.getParameter("state");
		//验证信息
		if(id==null||id.equals("")){
			request.setAttribute("msg", "id获取有误，请重试");
			request.getRequestDispatcher("/admin/member/updateMemberState.jsp").forward(request, response);
		}else if(status==null||status.equals("")){
			request.setAttribute("msg", "状态获取有误，请重试");
			request.getRequestDispatcher("/admin/member/updateMemberState.jsp").forward(request, response);
		}else{
			//创建类的实现接口的方法
			MemberDao dao=new MemberDaoImpl();
			//更新用户的状态
			boolean flag=dao.updateMemberStatus(Integer.parseInt(status), Integer.parseInt(id));
			if(flag){
				//更新成功，显示界面
				memberList(request,response);
			}else{//更新失败
				request.setAttribute("msg", "修改失败，请重试");
				request.getRequestDispatcher("/admin/member/updateMemberState.jsp").forward(request, response);
			}
		}
		
	}

	private void toUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取用户的id
		String id=request.getParameter("id");
		//验证
		if(id==null||id.equals("")){
			request.setAttribute("msg", "id获取有误，请重试");
			request.getRequestDispatcher("/admin/member/memberList.jsp").forward(request, response);
		}else{
			//创建类的实现对象
			MemberDao dao=new MemberDaoImpl();
			//找到id所对应的member
			Member member=dao.findById(Integer.parseInt(id));
			//设置共享变量，在updateMemberState.jsp中调用
			request.setAttribute("member", member);
			request.getRequestDispatcher("/admin/member/updateMemberState.jsp").forward(request, response);
		}
		
		
	}

	private void memberList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		
		MemberDao dao=new MemberDaoImpl();
		//获取用户的列表
		PageModel<Member> pm=dao.findAll2(Integer.parseInt(pageNo),pageSize);
		//设置共享变量
		request.setAttribute("pm", pm);
		//跳转
		request.getRequestDispatcher("/admin/member/memberList.jsp").forward(request, response);
		
		
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
