package com.tjpu.wtf.mngweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjpu.wtf.dao.CategoryDao;
import com.tjpu.wtf.dao.ForumDao;
import com.tjpu.wtf.dao.ManagerDao;
import com.tjpu.wtf.dao.ReplyDao;
import com.tjpu.wtf.dao.TopicDao;
import com.tjpu.wtf.dao.impl.CategoryDaoImpl;
import com.tjpu.wtf.dao.impl.ForumDaoImpl;
import com.tjpu.wtf.dao.impl.ManagerDaoImpl;
import com.tjpu.wtf.dao.impl.ReplyDaoImpl;
import com.tjpu.wtf.dao.impl.TopicDaoImpl;
import com.tjpu.wtf.model.Category;
import com.tjpu.wtf.model.Forum;
import com.tjpu.wtf.model.Manager;
import com.tjpu.wtf.model.PageModel;
import com.tjpu.wtf.model.Reply;
import com.tjpu.wtf.model.Topic;

public class CategoryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CategoryServlet() {
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
		if(method!=null){
			if(method.equals("addCategory")){
				addCategory(request,response);
			}else if(method.equals("categoryList")){
				categoryList(request,response);
			}else if(method.equals("delete")){
				delete(request,response);
			}else if(method.equals("toUpdate")){
				toUpdate(request,response);
			}else if(method.equals("update")){
				update(request,response);
			}else if(method.equals("serach")){
				serach(request,response);
			}
		}else{
			request.setAttribute("msg", "获取方法有误");
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
		String categoryName=request.getParameter("loginName");
		//调用实现接口的方法
		CategoryDao dao=new CategoryDaoImpl();
		//创建一个List列表，此列表中的数据都为category类型（javaBean）
		PageModel<Category> pm=dao.findAllByCategoryName2(categoryName,Integer.parseInt(pageNo),pageSize);
		List<Category> list=pm.getDatas();
		if(list.size()==0){
			request.setAttribute("msg", "没有查询到您所输入的分区名，请确认后再试！");
		}
		//设置共享变量
		request.setAttribute("pm", pm);
		request.setAttribute("categoryList", list);
		//实施跳转
		request.getRequestDispatcher("/admin/category/categorySerachList.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
	 throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取用户信息
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String sortNo=request.getParameter("sortNo");
		String state=request.getParameter("state");
		//后台验证
		if(name==null||name.equals("")){
			request.setAttribute("msg", "获取name时出现异常，请稍后再试");
			request.getRequestDispatcher("/admin/category/updateCategory.jsp").forward(request, response);
		}else if(sortNo==null||sortNo.equals("")){
			request.setAttribute("msg", "获取sortNo时出现异常，请稍后再试");
			request.getRequestDispatcher("/admin/category/updateCategory.jsp").forward(request, response);
		}else{//通过后台验证
			CategoryDao dao=new CategoryDaoImpl();
			//通过id找到具体的分区
			Category category=dao.findById(Integer.parseInt(id));
			if(name.equals(category.getName())){
				//更新数据
				category.setSortNo(Integer.parseInt(sortNo));
				category.setName(name);
				category.setState(Integer.parseInt(state));
				//更新数据库
				boolean flag=dao.update(category);
				if(flag)
				{//更新成功
					categoryList(request,response);
				}else{//获取id失败
					request.setAttribute("msg", "获取id时出现异常，请稍后再试");
					request.getRequestDispatcher("/admin/category/updateCategory.jsp").forward(request, response);
				}
			}else{
				boolean flag2=dao.findByName(name);
				if(flag2){
					request.setAttribute("msg", "分区名已存在，请重试");
					toUpdate(request,response);
				}else{
					//更新数据
					category.setSortNo(Integer.parseInt(sortNo));
					category.setName(name);
					category.setState(Integer.parseInt(state));
					//更新数据库
					boolean flag=dao.update(category);
					if(flag)
					{//更新成功
						categoryList(request,response);
					}else{//获取id失败
						request.setAttribute("msg", "获取id时出现异常，请稍后再试");
						request.getRequestDispatcher("/admin/category/updateCategory.jsp").forward(request, response);
					}
				}
				
			}
			
		}
	}

	private void toUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
        String id=request.getParameter("id");
		
		if(id!=null&&!id.equals(""))
		{//获取id成功
		CategoryDao dao=new CategoryDaoImpl();
		//通过所给的id获取category信息
		Category category=dao.findById(Integer.parseInt(id));
		//设置共享变量
		request.setAttribute("category", category);
		//施行页面跳转
		request.getRequestDispatcher("/admin/category/updateCategory.jsp").forward(request, response);
		}else{//获取id失败
			request.setAttribute("msg", "获取id时出现异常，请稍后再试");
			request.getRequestDispatcher("/admin/category/categoryList.jsp").forward(request, response);
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		// TODO Auto-generated method stub
		String categoryId=request.getParameter("id");
		if(categoryId==null||categoryId.equals("")){
			request.setAttribute("msg", "id获取失败");
			request.getRequestDispatcher("/admin/category/categoryList.jsp").forward(request, response);
		}else{
			Reply reply=new Reply();
			List<Reply> replyList=new ArrayList<Reply>();
			ReplyDao replyDao=new ReplyDaoImpl();
			Topic topic=new Topic();
			List<Topic> topicList=new ArrayList<Topic>();
			TopicDao topicDao=new TopicDaoImpl();
			Forum forum=new Forum();
			List<Forum> forumList=new ArrayList<Forum>();
			ForumDao forumDao=new ForumDaoImpl();
			Category category=new Category();
			CategoryDao categoryDao=new CategoryDaoImpl();
			//获取该categoryId所对应的category
			category=categoryDao.findById(Integer.parseInt(categoryId));
			
			//获取该categoryId下的forumList
			forumList=forumDao.findAllById(Integer.parseInt(categoryId));
			for(int i=0;i<forumList.size();i++){
				forum=forumList.get(i);
				//获取该forum下所有的topic
				topicList=topicDao.findAllByForumId(forum.getId());
				for(int j=0;j<topicList.size();j++){
					topic=topicList.get(j);
					//获取该topic下的所有replyList
					replyList=replyDao.findByTopicId((int) topic.getId());
					for(int k=0;k<replyList.size();k++){
						reply=replyList.get(k);
						//删除该reply
						replyDao.deleteByReplyId(reply.getId());
					}
					//删除topic
					topicDao.deleteById((int) topic.getId());
				}
				//删除forum
				forumDao.deleteByForumId(forum.getId());
			}
			//删除category
			categoryDao.delete(category.getId());
			categoryList(request,response);
		}
		
	}

	private void categoryList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		//调用实现接口的方法
		CategoryDao dao=new CategoryDaoImpl();
		//创建一个List列表，此列表中的数据都为category类型（javaBean）
		PageModel<Category> pm=dao.findAll2(Integer.parseInt(pageNo),pageSize);
		//设置共享变量
		request.setAttribute("pm",pm);
		//实施跳转
		request.getRequestDispatcher("/admin/category/categoryList.jsp").forward(request, response);
		
	}

	private void addCategory(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		// TODO Auto-generated method stub
		//获取页面输入信息
		String name=request.getParameter("name");
		String  sortNo=request.getParameter("sortNo");
		//后台验证
		if(name==null||name.equals("")){
			request.setAttribute("msg", "请输入分区名称");
			request.getRequestDispatcher("/admin/category/addCategory.jsp").forward(request, response);
		}else if(sortNo==null||sortNo.equals("")){
			request.setAttribute("msg", "请输入分区编号");
			request.getRequestDispatcher("/admin/category/addCategory.jsp").forward(request, response);
		}else{
			CategoryDao dao=new CategoryDaoImpl();
			Category category=new Category();
			//检测分区名是否已经存在
			boolean flg=dao.findByName(name);
			if(flg){//分区名已经存在
				request.setAttribute("msg", "您输入的分区名已存在");
				request.getRequestDispatcher("/admin/category/addCategory.jsp").forward(request, response);
			}else{//分区名不存在，为category设置值
				category.setName(name);
				category.setSortNo(Integer.parseInt(sortNo));
				category.setState(1);
				//增加分区
				boolean flag=dao.addCategory(category);
				if(flag)
				{//增加分区成功，调用列表方法
					categoryList(request,response);
				}else{
					request.setAttribute("msg", "添加分区失败");
					request.getRequestDispatcher("/admin/category/addCategory.jsp").forward(request, response);
				}
			}
			
		}
		
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
