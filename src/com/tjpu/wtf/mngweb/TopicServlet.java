package com.tjpu.wtf.mngweb;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.tjpu.wtf.dao.MemberDao;
import com.tjpu.wtf.dao.ReplyDao;
import com.tjpu.wtf.dao.TopicDao;
import com.tjpu.wtf.dao.impl.CategoryDaoImpl;
import com.tjpu.wtf.dao.impl.ForumDaoImpl;
import com.tjpu.wtf.dao.impl.MemberDaoImpl;
import com.tjpu.wtf.dao.impl.ReplyDaoImpl;
import com.tjpu.wtf.dao.impl.TopicDaoImpl;
import com.tjpu.wtf.model.Category;
import com.tjpu.wtf.model.Forum;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;
import com.tjpu.wtf.model.Reply;
import com.tjpu.wtf.model.Topic;

public class TopicServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TopicServlet() {
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
			if(method.equals("topicList")){
				topicList(request,response);
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
		//获取页面传来的forumId
		String forumId=request.getParameter("forumId");
		//查询出所有forumId为页面传来的forumId的topicList
		TopicDao topicDao=new TopicDaoImpl();
		PageModel<Topic> pm=topicDao.findByForumIdAll2(Integer.parseInt(forumId),Integer.parseInt(pageNo),pageSize);
		List<Topic> topicList=pm.getDatas();
		request.setAttribute("pm", pm);
		ForumDao forumDao=new ForumDaoImpl();
		CategoryDao categoryDao=new CategoryDaoImpl();
		MemberDao memberDao=new MemberDaoImpl();
		//设置列表
		/*
		 * 设置一个map，用来到页面显示下拉列表，分区下的所有板块，且分区不能被选中
		 */
		//找出所有的分区
		List<Category> cateList=categoryDao.findAll();
		Map<Category,List<Forum>> map=new LinkedHashMap<Category,List<Forum>>();
		for(int i=0;i<cateList.size();i++){
			Category cate=cateList.get(i);
			List<Forum> forumList=forumDao.findAllById(cate.getId());
			map.put(cate, forumList);
		} 
		request.setAttribute("mapList2", map);
		//设置topic和forum的map
        Map<Topic,Forum> topmap=new LinkedHashMap<Topic,Forum>();
		
		for(int i=0;i<topicList.size();i++)
		{
			Topic topic=topicList.get(i);
			Forum forum=forumDao.findById(topic.getForumId());
			topmap.put(topic, forum);
		}
		request.setAttribute("mapList", topmap);
		
		//获取所有的member
		List<Member> memberList=new ArrayList<Member>();
		memberList=memberDao.findAll();
		request.setAttribute("memberList", memberList);
		
		//获取所有的category
		List<Category> categoryList=new ArrayList<Category>();
		categoryList=categoryDao.findAll();
		request.setAttribute("categoryList", categoryList);
		
		//获取forumId所对应的forum，用来显示页面中列表上被选中的板块名
		Forum forum=forumDao.findById(Integer.parseInt(forumId));
		request.setAttribute("forumm", forum);
		
		request.getRequestDispatcher("/admin/topic/topicSerachList.jsp").forward(request, response);
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取页面数据
		String topicId=request.getParameter("id");
		String  status=request.getParameter("state");
		String type=request.getParameter("type");
		int count3=0;
		TopicDao topicDao=new TopicDaoImpl();
		Topic topic=new Topic();
		MemberDao memberDao=new MemberDaoImpl();
		Topic topic2=new Topic();
		int inc=5;
		//设置页面上的数据
		topic.setStatus(Integer.parseInt(status));
		topic.setType(Integer.parseInt(type));
		topic.setId(Integer.parseInt(topicId));
		//设置增加积分，置顶或者精华，则加5分
		
		//通过topicId找到topic
		topic2=topicDao.findById(Integer.parseInt(topicId));
		//通过topic2获取memberId信息
		Member member=memberDao.findById(topic2.getMemberId());
		//如果是置顶或者精华，则积分加5分
		if(topic.getType()==1||topic.getStatus()==4){
			
			boolean flag=memberDao.updateMemberIntegral(inc,member.getId());
			if(flag){
				
			}else{
				request.setAttribute("msg", "积分更新失败");
				request.getRequestDispatcher("/admin/topic/updateTopic.jsp").forward(request, response);
			}
		}
		//更新发帖者的精华数量
		if(topic.getStatus()==4){
			boolean flag=memberDao.updateBest(member.getId());
			if(flag){
				
			}else{
				request.setAttribute("msg", "发帖者精华帖数更新失败");
				request.getRequestDispatcher("/admin/topic/updateTopic.jsp").forward(request, response);
			}
		
		}
		//更新topic
		boolean flag=topicDao.update(topic);
		if(flag){
			//获取MemberId，得到该member精华帖数目
			
			count3=topicDao.memberTopicBestCount(member.getId());
			System.out.println(count3);
			request.getSession().setAttribute("count3", count3);
			//显示topic页面
			topicList(request,response);
		}else{
			request.setAttribute("msg", "更新失败，请重试");
			request.getRequestDispatcher("/admin/topic/updateTopic.jsp").forward(request, response);
		}
		
	}

	private void toUpdate(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取id
		String id=request.getParameter("id");
		//后台验证
		if(id==null||id.equals("")){
			request.setAttribute("msg", "id获取有误，请重试");
			request.getRequestDispatcher("/admin/topic/topicList.jsp").forward(request, response);
		}else{
			CategoryDao categoryDao=new CategoryDaoImpl();
			ForumDao forumDao=new ForumDaoImpl();
			MemberDao memberDao=new MemberDaoImpl();
			TopicDao topicDao=new TopicDaoImpl();
			
			//通过topicId找到所对应的topic
			Topic topic=topicDao.findById(Integer.parseInt(id));
			//设置共享变量
			request.setAttribute("topic", topic);
			
			//通过topic可以获得MemberID,从而找到指定的member
			Member member=memberDao.findById(topic.getMemberId());
			request.setAttribute("member", member);
			
			//通过topic找到指定的ForumId,从而找到指定的forum
			Forum forum=forumDao.findById(topic.getForumId());
			request.setAttribute("forum", forum);
			
			//通过forum找到categoryId,从而找到指定的category
			Category category=categoryDao.findById(forum.getCategoryId());
			request.setAttribute("category", category);
			//施行页面跳转
			request.getRequestDispatcher("/admin/topic/updateTopic.jsp").forward(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		if(id==null||id.equals(""))
		{
			request.setAttribute("msg", "id获取有误，请重试");
			request.getRequestDispatcher("/admin/topic/topicList.jsp").forward(request, response);
		}else{
			//更新forum数据库中的值
			TopicDao dao=new TopicDaoImpl();
			ForumDao forumDao=new ForumDaoImpl();
			MemberDao memberDao=new MemberDaoImpl();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			ReplyDao replyDao=new ReplyDaoImpl();
			//根据topicId获取topic
			Topic topic=dao.findById(Integer.parseInt(id));
			boolean flag=dao.deleteById(Integer.parseInt(id));
			if(flag){
				//获取今日的发帖数量，并且设置session
				//获取当前日期，并且进行格式化
				int count5=dao.findAllTopicCountToday(format.format(new Date()));
				request.getSession().setAttribute("topicCountToday", count5);
				
				//获取MemberId获取该member一共发帖的数量
				int count2=0;
				count2=dao.memberTopicCount(topic.getMemberId());
				request.getSession().setAttribute("count2", count2);
				
				//设置session，获取总主题数
				int count6=0;
				count6=dao.findAllTopicCount();
				request.getSession().setAttribute("count6", count6);
				//更新forum表中的主题和文章数
				forumDao.updateForumCount(topic.getForumId());
				//更新topic所对应的发帖者所对应的topicCount
				memberDao.updateMemberTopicCount(topic.getMemberId());
				//同时从数据库中删除帖子下的所有回复
				boolean flag2=replyDao.deleteByTopicId(Integer.parseInt(id));
				topicList(request,response);
			}else{
				request.setAttribute("msg", "删除失败，请重试");
				request.getRequestDispatcher("/admin/topic/topicList.jsp").forward(request, response);
			}
		}
		
	}

	private void topicList(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		// TODO Auto-generated method stub
		//查询出所有分区和板块
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		//找到所有的topic
		TopicDao topicDao=new TopicDaoImpl();
		
		PageModel<Topic> pm=topicDao.findAll2(Integer.parseInt(pageNo),pageSize);
		List<Topic> topicList=pm.getDatas();
		request.setAttribute("pm", pm);
		
		ForumDao forumDao=new ForumDaoImpl();
		CategoryDao categoryDao=new CategoryDaoImpl();
		MemberDao memberDao=new MemberDaoImpl();
		
		//找到所有的category
		List<Category> cateList=categoryDao.findAll();
		//定义一个category和forum的map
		Map<Category,List<Forum>> map=new LinkedHashMap<Category,List<Forum>>();
		for(int i=0;i<cateList.size();i++){
			Category cate=cateList.get(i);
			List<Forum> forumList=forumDao.findAllById(cate.getId());
			map.put(cate, forumList);
		} 
		request.setAttribute("mapList2", map);
		
		//设置topic和forum的map
        Map<Topic,Forum> topmap=new LinkedHashMap<Topic,Forum>();
		for(int i=0;i<topicList.size();i++)
		{
			Topic topic=topicList.get(i);
			Forum forum=forumDao.findById(topic.getForumId());
			topmap.put(topic, forum);
		}
		request.setAttribute("mapList", topmap);
		
		//找到所有的member
		List<Member> memberList=new ArrayList<Member>();
		memberList=memberDao.findAll();
		request.setAttribute("memberList", memberList);
		
		//找到所有的category
		List<Category> categoryList=new ArrayList<Category>();
		categoryList=categoryDao.findAll();
		request.setAttribute("categoryList", categoryList);
		
		request.getRequestDispatcher("/admin/topic/topicList.jsp").forward(request, response);
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
