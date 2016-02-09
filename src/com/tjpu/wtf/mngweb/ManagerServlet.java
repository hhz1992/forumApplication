package com.tjpu.wtf.mngweb;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.tjpu.wtf.dao.ManagerDao;
import com.tjpu.wtf.dao.impl.ManagerDaoImpl;
import com.tjpu.wtf.model.Manager;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;
import com.tjpu.wtf.util.MD5;

public class ManagerServlet extends HttpServlet {
    //引入MD5加密算法包
	private MD5 md5=new MD5();
	/**
	 * Constructor of the object.s
	 */
	public ManagerServlet() {
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
        //doGet方法不安全，但是却默认是doGet方法，所以，在此调用doPost方法
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
		if(method!=null)
		{
			if(method.equals("addmanager"))
			{
				//调用管理员增加方法
				
				addmanager(request,response);
			}else if(method.equals("managerList"))
			{  //调用显示所有管理员列表
				managerList(request,response);
			}else if(method.equals("delete"))
			{  //删除某一管理员
				delete(request,response);
			}else if(method.equals("toUpdate"))
			{  //调用Update方法，去Update.jsp界面
				toUpdate(request,response);
			}else if(method.equals("update"))
			{   //调用update方法更新数据
				update(request,response);
			}else if(method.equals("serach")){
				serach(request,response);
			}
		}else{//提交的方法有误
			request.setAttribute("msg", "路径有误");
			request.getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}
	
	
	private void serach(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		
		String loginName=request.getParameter("loginName");
		if(loginName==null||loginName.equals("")){
			request.setAttribute("msg", "请输入登录用户名");
			managerList(request,response);
		}else{
			
			ManagerDao managerDao=new ManagerDaoImpl();
			PageModel<Manager> pm=managerDao.findAllByLoginName2(loginName,Integer.parseInt(pageNo),pageSize);
			List<Manager> list=pm.getDatas();
			if(list.size()==0){
				request.setAttribute("msg", "您所输入的管理员登录名有误，请确认后再试！");
			}
			   
				//设置共享变量
			request.setAttribute("pm", pm);
				request.setAttribute("managerList", list);
				//实施跳转
				request.getRequestDispatcher("/admin/manager/managerSerachList.jsp").forward(request, response);
			
			
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取管理员信息
		String id=request.getParameter("id");
		String loginName=request.getParameter("loginName");
		String password=request.getParameter("password");
		String realName=request.getParameter("realName");
		String mobile=request.getParameter("mobile");
		String state=request.getParameter("state");
		//后台验证是否没有输入，防止跳过前台的javaScript验证
		if(loginName==null||loginName.equals(""))
		{
			request.setAttribute("msg", "请输入登录用户名");
			try {
				request.getRequestDispatcher("/admin/manager/Update.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(password==null||password.equals(""))
		{
			request.setAttribute("msg", "请输入登录密码");
			try {
				request.getRequestDispatcher("/admin/manager/Update.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(mobile==null||mobile.equals(""))
		{
			request.setAttribute("msg", "请输入手机号");
			try {
				request.getRequestDispatcher("/admin/manager/Update.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(realName==null||realName.equals(""))
		{
			request.setAttribute("msg", "请输入真实姓名");
			try {
				request.getRequestDispatcher("/admin/manager/Update.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(state==null||state.equals(""))
		{
			request.setAttribute("msg", "请输入状态");
			try {
				request.getRequestDispatcher("/admin/manager/Update.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//创建一个Manager对象和一个ManagerDao对象
			ManagerDao dao=new ManagerDaoImpl();//通过调用实现的具体方法
			//通过所给的ID找到所在的manager信息，为javaBean，里面通过调用findById方法已经给manager复制
			Manager manager=dao.findById(Integer.parseInt(id));
			//重新给manager赋值
			manager.setLoginName(loginName);
			manager.setPassword(md5.getMD5ofStr(password));
			manager.setRealName(realName);
			manager.setMobile(mobile);
			manager.setState(Integer.parseInt(state));
			
			//创建一个格式化日期的对象
			SimpleDateFormat foramt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//manager调用setCreatTime方法为CreateTime赋值
			manager.setCreateTime(foramt.format(new Date()));//获取现在的时间
			//提交manager给dao的update方法
			boolean flag=dao.update(manager);
			if(flag)
			{//更新成功
				try {//显示用户列表，调用此方法
					managerList(request,response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{//更新失败
				request.setAttribute("manager", manager);
				try {
					request.getRequestDispatcher("/admin/manager/Update.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}	
		
	}

	private void toUpdate(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取ID
		String id=request.getParameter("id");
		
		if(id!=null&&!id.equals(""))
		{//获取id成功
		ManagerDao dao=new ManagerDaoImpl();
		//通过所给的id获取manager信息
		Manager manager=dao.findById(Integer.parseInt(id));
		//设置共享变量
		request.setAttribute("manager", manager);
		//施行页面跳转
		request.getRequestDispatcher("/admin/manager/Update.jsp").forward(request, response);
		}else{//获取id失败
			request.setAttribute("msg", "获取id时出现异常，请稍后再试");
			request.getRequestDispatcher("/admin/manager/managerList.jsp").forward(request, response);
		}
	}

	//删除某一数据
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//获取用户id
		String id=request.getParameter("id");
		ManagerDao dao=new ManagerDaoImpl();
		//调用delete方法
		boolean flag=dao.delete(Integer.parseInt(id));
		if(flag)
		{//删除成功
			try {//调用次方法，显示用户列表
				managerList(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{//删除失败
			try {
				request.getRequestDispatcher("/admin/manager/managerList.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
    //调用此方法，增加会员
	public void addmanager(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		//获取管理员信息
		String loginName=request.getParameter("loginName");
		String password=request.getParameter("password");
		String realName=request.getParameter("realName");
		String mobile=request.getParameter("mobile");
		//后台验证是否为空
		if(loginName==null||loginName.equals(""))
		{
			request.setAttribute("msg", "请输入登录用户名");
			request.getRequestDispatcher("/admin/manager/addManager.jsp").forward(request, response);
		}else if(password==null||password.equals(""))
		{
			request.setAttribute("msg", "请输入登录密码");
			request.getRequestDispatcher("/admin/manager/addManager.jsp").forward(request, response);
		}else if(mobile==null||mobile.equals(""))
		{
			request.setAttribute("msg", "请输入手机号");
			request.getRequestDispatcher("/admin/manager/addManager.jsp").forward(request, response);
		}else if(realName==null||realName.equals(""))
		{
			request.setAttribute("msg", "请输入真实姓名");
			request.getRequestDispatcher("/admin/manager/addManager.jsp").forward(request, response);
		}else{
			//创建一个Manager对象
			Manager manager=new Manager();
			//为manager对象赋值
			manager.setLoginName(loginName);
			manager.setPassword(md5.getMD5ofStr(password));
			manager.setRealName(realName);
			manager.setMobile(mobile);
			manager.setState(1);
			manager.setLoginNum(0);
			//创建一个格式化日期的对象
			SimpleDateFormat foramt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			manager.setCreateTime(foramt.format(new Date()));//调用次方法为CreateTime赋值——当前时间
			//调用实现接口的方法
			ManagerDao dao=new ManagerDaoImpl();
			boolean flag=dao.addManager(manager);
			if(flag){//成功
				managerList(request,response);
			}else{//失败
				request.setAttribute("msg", "添加管理员失败，请稍后再试");
				request.getRequestDispatcher("/admin/manager/addManager.jsp").forward(request, response);
			}
		}
		
	}
	
    //显示管理员列表
	private void managerList(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		//调用实现接口的方法
		ManagerDao dao=new ManagerDaoImpl();
		//创建一个List列表，此列表中的数据都为Manager类型（javaBean）
		PageModel<Manager> pm=dao.findAll2(Integer.parseInt(pageNo),pageSize);
		//设置共享变量
		request.setAttribute("pm", pm);
		//实施跳转
		request.getRequestDispatcher("/admin/manager/managerList.jsp").forward(request, response);
		
		
		// TODO Auto-generated method stub
		
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
