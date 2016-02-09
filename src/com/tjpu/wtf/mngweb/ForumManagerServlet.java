package com.tjpu.wtf.mngweb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjpu.wtf.dao.CategoryDao;
import com.tjpu.wtf.dao.ForumDao;
import com.tjpu.wtf.dao.ReplyDao;
import com.tjpu.wtf.dao.TopicDao;
import com.tjpu.wtf.dao.impl.CategoryDaoImpl;
import com.tjpu.wtf.dao.impl.ForumDaoImpl;
import com.tjpu.wtf.dao.impl.ReplyDaoImpl;
import com.tjpu.wtf.dao.impl.TopicDaoImpl;
import com.tjpu.wtf.model.Category;
import com.tjpu.wtf.model.Forum;
import com.tjpu.wtf.model.PageModel;
import com.tjpu.wtf.model.Reply;
import com.tjpu.wtf.model.Topic;

public class ForumManagerServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ForumManagerServlet() {
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
		if(method!=null&&!method.equals("")){
			if(method.equals("toAdd")){
				//到增加板块的页面
				toAdd(request,response);
			}else if(method.equals("addForum")){
				//增加板块信息
				addForum(request,response);
			}else if(method.equals("forumList")){
				//显示板块的信息
				forumList(request,response);
			}else if(method.equals("toUpdate")){
				//到更新板块内容的页面
				
				toUpdate(request,response);
			}else if(method.equals("update")){
				//更新板块内容
				update(request,response);
			}else if(method.equals("delete")){
				delete(request,response);
			}else if(method.equals("serach")){
				serach(request,response);
			}
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
		//获取所有的板块信息
		String forumName=request.getParameter("loginName");
		// TODO Auto-generated method stub
		ForumDao dao=new ForumDaoImpl();
		//获取所有的板块信息
		PageModel<Forum> pm=dao.findAllByName2(forumName,Integer.parseInt(pageNo),pageSize);
		List<Forum> list=pm.getDatas();
		if(list.size()==0){
			request.setAttribute("msg", "您所输入的板块名不存在，请确认后再输入");
		}
		//设置共享变量
		request.setAttribute("forumList", list);
		request.setAttribute("pm", pm);
		//跳转
		request.getRequestDispatcher("/admin/forum/forumSerachList.jsp").forward(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) 
	 throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取板块的id
		String forumId=request.getParameter("id");
		if(forumId==null||forumId.equals("")){
			request.setAttribute("msg", "请选择板块所属分区");
			request.getRequestDispatcher("/admin/forum/forumList.jsp").forward(request, response);
		}else{
			Topic topic=new Topic();
			Reply reply=new Reply();
			TopicDao topicDao=new TopicDaoImpl();
			ReplyDao replyDao=new ReplyDaoImpl();
			ForumDao forumDao=new ForumDaoImpl();
			List<Reply> replyList=new ArrayList<Reply>();
			List<Topic> topicList=new ArrayList<Topic>();
			//获取forumId下所有的topic
			topicList=topicDao.findByForumIdAll(Integer.parseInt(forumId));
			for(int i=0;i<topicList.size();i++){
				topic=topicList.get(i);
				//获取topic下所有的回复,并且删除回复
				replyList=replyDao.findByTopicId((int) topic.getId());
				for(int j=0;j<replyList.size();j++){
					reply=replyList.get(j);
					replyDao.deleteByReplyId(reply.getId());
				}
				//删除帖子
				topicDao.deleteById((int) topic.getId());
			}
			//删除板块
			forumDao.deleteByForumId(Integer.parseInt(forumId));
			
			forumList(request,response);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
	 throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取用户输入信息
		String id=request.getParameter("id");
		String categoryId=request.getParameter("categoryName");
		String name=request.getParameter("name");
		String sortNo=request.getParameter("sortNo");
		String keywords=request.getParameter("keywords");
		String description=request.getParameter("description");
		String rule=request.getParameter("rule");
		//后台验证
		if(categoryId==null||categoryId.equals(""))
		{
			request.setAttribute("msg", "请选择板块所属分区");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(name==null||name.equals("")){
			request.setAttribute("msg", "请输入板块名字");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(sortNo==null||sortNo.equals("")){
			request.setAttribute("msg", "请输入板块编号");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(keywords==null||keywords.equals("")){
			request.setAttribute("msg", "请输入板块关键字");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(description==null||description.equals("")){
			request.setAttribute("msg", "请输入板块描述");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(rule==null||rule.equals("")){
			request.setAttribute("msg", "请输入板块规则");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(id==null||id.equals("")){
			request.setAttribute("msg", "id获取有误，请重试");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else{//通过后台验证
			CategoryDao dao=new CategoryDaoImpl();
			//讲id转换成int类型
			int cateId=Integer.parseInt(categoryId);
			ForumDao forumDao=new ForumDaoImpl();
			Forum forum=new Forum();
			//取出原来数据库中板块的名字
			forum=forumDao.findById(Integer.parseInt(id));
			if(name.equals(forum.getName()))
			{
				forum.setId(Integer.parseInt(id));
				forum.setName(name);
				forum.setSortNo(Integer.parseInt(sortNo));
				forum.setKeywords(keywords);
				forum.setDescription(description);
				forum.setRule(rule);
				forum.setCategoryId(cateId);
				//调用次方法查到分区信息
				Category category=dao.findById(cateId);
				//为forum赋值
				forum.setCategoryName(category.getName());
				
				//调用此方法更新数据库中板块的信息
				boolean flag=forumDao.update(forum);
				if(flag){
					//调用此方法显示板块列表
					forumList(request,response);
				}else{//没有更新
					request.setAttribute("msg", "修改板块信息未能成功");
					request.getRequestDispatcher("/admin/forum/updateForum.jsp").forward(request, response);
				}
			}else{
				//查看板块名是否已经存在
				boolean flg=forumDao.findByName(name);
				if(flg){//板块已经存在
					request.setAttribute("msg", "板块名已存在，请重新输入");
					toUpdate(request,response);
					
				}else{//板块名不存在
					//为forum赋值
					forum.setId(Integer.parseInt(id));
					forum.setName(name);
					forum.setSortNo(Integer.parseInt(sortNo));
					forum.setKeywords(keywords);
					forum.setDescription(description);
					forum.setRule(rule);
					forum.setCategoryId(cateId);
					//调用次方法查到分区信息
					Category category=dao.findById(cateId);
					//为forum赋值
					forum.setCategoryName(category.getName());
					
					//调用此方法更新数据库中板块的信息
					boolean flag=forumDao.update(forum);
					if(flag){
						//调用此方法显示板块列表
						forumList(request,response);
					}else{//没有更新
						request.setAttribute("msg", "修改板块信息未能成功");
						request.getRequestDispatcher("/admin/forum/updateForum.jsp").forward(request, response);
					}
				}
				
			}
			
			
		}
		
		
	}

	private void toUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取用户id
		String id=request.getParameter("id");
		//后台验证id
		if(id==null||id.equals("")){
			request.setAttribute("msg", "id获取有误，请重试");
			request.getRequestDispatcher("/admin/forum/forumList.jsp").forward(request, response);
		}else{
			ForumDao dao=new ForumDaoImpl();
			//通过id找到具体的板块
			Forum forum=dao.findById(Integer.parseInt(id));
			CategoryDao dao2=new CategoryDaoImpl();
			//用于列表显示信息
			List<Category> list=dao2.findAll();
			request.setAttribute("categoryList", list);
			//设置共享变量
			request.setAttribute("forum",forum);
			request.getRequestDispatcher("/admin/forum/updateForum.jsp").forward(request, response);
		}
		
	}

	private void forumList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		ForumDao dao=new ForumDaoImpl();
		//获取所有的板块信息
		PageModel<Forum> pm=dao.findAll2(Integer.parseInt(pageNo),pageSize);
		
		//设置共享变量
		request.setAttribute("pm", pm);
		//跳转
		request.getRequestDispatcher("/admin/forum/forumList.jsp").forward(request, response);
		
	}

	private void addForum(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		/*
		 * 增加板块
		 */
		//获取用户输入的信息
		String categoryId=request.getParameter("categoryName");
		String name=request.getParameter("name");
		String sortNo=request.getParameter("sortNo");
		String keywords=request.getParameter("keywords");
		String description=request.getParameter("description");
		String rule=request.getParameter("rule");
		//后台验证
		if(categoryId==null||categoryId.equals(""))
		{
			request.setAttribute("msg", "请选择板块所属分区");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(name==null||name.equals("")){
			request.setAttribute("msg", "请输入板块名字");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(sortNo==null||sortNo.equals("")){
			request.setAttribute("msg", "请输入板块编号");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(keywords==null||keywords.equals("")){
			request.setAttribute("msg", "请输入板块关键字");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(description==null||description.equals("")){
			request.setAttribute("msg", "请输入板块描述");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else if(rule==null||rule.equals("")){
			request.setAttribute("msg", "请输入板块规则");
			request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
		}else{//后台验证通过
			CategoryDao dao=new CategoryDaoImpl();
			//将id转换成int类型
			int cateId=Integer.parseInt(categoryId);
			Forum forum=new Forum();
			ForumDao forumDao=new ForumDaoImpl();
			//调用此方法，判断输入的模块名是否存在
			boolean flg=forumDao.findByName(name);
			if(flg){//模块名已经存在
				request.setAttribute("msg", "板块名已存在，请重新输入");
				toAdd(request,response);
			}else{//模块名不存在
				forum.setName(name);
				forum.setSortNo(Integer.parseInt(sortNo));
				forum.setKeywords(keywords);
				forum.setDescription(description);
				forum.setRule(rule);
				forum.setCategoryId(cateId);
				//通过id查看分区名称
				Category category=dao.findById(cateId);
				//讲分区名传递给forum
				forum.setCategoryName(category.getName());
				//调用此方法增加模块
				boolean flag=forumDao.addForum(forum);
				if(flag){
					//跳转到模块列表
					forumList(request,response);
				}else{//添加模块失败
					request.setAttribute("msg", "添加模块信息未成功");
					request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
				}
			}
			
			
		}
		
	}

	private void toAdd(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		// TODO Auto-generated method stub
		//到增加板块内容的页面
		CategoryDao dao=new CategoryDaoImpl();
		//调用此方法获取所有的板块信息
		List<Category> list=dao.findAll();
		//设置共享变量
		request.setAttribute("categoryList", list);
		//跳转到增加板块的页面
		request.getRequestDispatcher("/admin/forum/addForum.jsp").forward(request, response);
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
