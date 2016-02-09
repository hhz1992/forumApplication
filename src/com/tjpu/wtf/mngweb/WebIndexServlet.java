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

import com.tjpu.wtf.dao.AttachmentDao;
import com.tjpu.wtf.dao.CategoryDao;
import com.tjpu.wtf.dao.ForumDao;
import com.tjpu.wtf.dao.MemberDao;
import com.tjpu.wtf.dao.ReplyDao;
import com.tjpu.wtf.dao.TopicDao;
import com.tjpu.wtf.dao.impl.AttachmentDaoImpl;
import com.tjpu.wtf.dao.impl.CategoryDaoImpl;
import com.tjpu.wtf.dao.impl.ForumDaoImpl;
import com.tjpu.wtf.dao.impl.MemberDaoImpl;
import com.tjpu.wtf.dao.impl.ReplyDaoImpl;
import com.tjpu.wtf.dao.impl.TopicDaoImpl;
import com.tjpu.wtf.model.Attachment;
import com.tjpu.wtf.model.Category;
import com.tjpu.wtf.model.Forum;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;
import com.tjpu.wtf.model.Reply;
import com.tjpu.wtf.model.Topic;

public class WebIndexServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WebIndexServlet() {
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
			if(method.equals("index")){
				IndexMethod(request,response);
			}else if(method.equals("mytopic")){
				myTopicList(request,response);
			}else if(method.equals("addTopic")){
				addTopic(request,response);
			}else if(method.equals("toCateTopic"))
			{
				toCateTopic(request,response);
			}else if(method.equals("toForumTopicList")){
				toForumTopicList(request,response);
			}else if(method.equals("myReply")){
				myReply(request,response);
			}else if(method.equals("toCateTopicAuthor")){
				toCateTopicAuthor(request,response);
			}else if(method.equals("delete")){
				delete(request,response);
			}
		}else{
			request.setAttribute("msg", "method获取失败");
			request.getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}
	

	public void delete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		//获取页面传来的topicId
		String topicId=request.getParameter("topicId");
		String replyId=request.getParameter("replyId");
		//后台验证id是否传输成功
		if(topicId==null||topicId.equals("")){
			request.setAttribute("msg", "topicId获取失败，请重试");
			request.getRequestDispatcher("/boardCate/bbs_board_cate_topic5.jsp").forward(request, response);
		}else if(replyId==null||replyId.equals("")){
			request.setAttribute("msg", "replyId获取失败，请重试");
			request.getRequestDispatcher("/boardCate/bbs_board_cate_topic5.jsp").forward(request, response);
		}else{
			ReplyDao replyDao=new ReplyDaoImpl();
			TopicDao topicDao=new TopicDaoImpl();
			MemberDao memberDao=new MemberDaoImpl();
			Reply reply2=new Reply();
			//通过replyId找到对应的reply
			reply2=replyDao.findByReplyId(Integer.parseInt(replyId));
			boolean flag=replyDao.deleteByReplyId(Integer.parseInt(replyId));
			if(flag){
				//根据topicId获取所对应的attach
				List<Attachment> attList=new ArrayList<Attachment>();
				AttachmentDao attDao=new AttachmentDaoImpl();
				attList=attDao.findAll();
				request.setAttribute("attList", attList);
				
				//设置session，获取总的回复数
				int count8=0;
				count8=replyDao.findAllReplyCount();
				request.getSession().setAttribute("count8", count8);
				//更新topic的ReplyCount
				
				//更新member列表中的replyCount字段
				memberDao.updateMemberReplyCount1(reply2.getMember());
				//更新topic列表中的replyCount字段
				topicDao.updateReplyCountByTopicId(Integer.parseInt(topicId));
				//根据topicId获取对应的topic
				
				Topic topic=topicDao.findById(Integer.parseInt(topicId));
				request.setAttribute("topic", topic);
				
				
				//根据topic获取forumId，从而找到所对应的forum
				ForumDao forumDao=new ForumDaoImpl();
				Forum forum=new Forum();
				forum=forumDao.findById(topic.getForumId());
				request.setAttribute("forum", forum);
				
				//根据forum找到categoryId，从而找到对应的category
				CategoryDao categoryDao=new CategoryDaoImpl();
				Category category=new Category();
				category=categoryDao.findById(forum.getCategoryId());
				request.setAttribute("category", category);
				
				//根据topic获取memberId，从而找到对应的member
				
				Member member=new Member();
				member=memberDao.findById(topic.getMemberId());
				request.setAttribute("member",member);
				
				//获取MemberId获取该member一共发帖的数量
				int count=0;
				count=topicDao.memberTopicCount(topic.getMemberId());
				request.setAttribute("count", count);
				//显示回复
				
				
				PageModel<Reply> pm=replyDao.findByTopicId2(Integer.parseInt(topicId),Integer.parseInt(pageNo),pageSize);
				List<Reply> replyList=pm.getDatas();
				request.setAttribute("pm", pm);
				Map<Reply,Member> map=new LinkedHashMap<Reply,Member>();
				for(int i=0;i<replyList.size();i++){
					Reply reply=replyList.get(i);
					Member member2=memberDao.findByName2(reply.getMember());
					map.put(reply, member2);
					
				}
				request.setAttribute("replyList",map);
				
				
				//更新人气
				topicDao.updateTopicVisitCount(topic.getId());
				request.getRequestDispatcher("/boardCate/bbs_board_cate_topic4.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "删除回复失败，请重试");
				request.getRequestDispatcher("/boardCate/bbs_board_cate_topic5.jsp").forward(request, response);
			}
		}
	}

	public void toCateTopicAuthor(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		//获取页面传来的topicId
		String topicId=request.getParameter("id");
		//后台验证id是否传输成功
		if(topicId==null||topicId.equals("")){
			request.setAttribute("msg", "Id获取失败，请重试");
			request.getRequestDispatcher("/member/mytopics.jsp").forward(request, response);
		}else{
			//根据topicId获取所对应的attach
			List<Attachment> attList=new ArrayList<Attachment>();
			AttachmentDao attDao=new AttachmentDaoImpl();
			attList=attDao.findAll();
			request.setAttribute("attList", attList);
			//根据topicId获取对应的topic
			TopicDao topicDao=new TopicDaoImpl();
			Topic topic=topicDao.findById(Integer.parseInt(topicId));
			request.setAttribute("topic", topic);
			
			//根据topic获取forumId，从而找到所对应的forum
			ForumDao forumDao=new ForumDaoImpl();
			Forum forum=new Forum();
			forum=forumDao.findById(topic.getForumId());
			request.setAttribute("forum", forum);
			
			//根据forum找到categoryId，从而找到对应的category
			CategoryDao categoryDao=new CategoryDaoImpl();
			Category category=new Category();
			category=categoryDao.findById(forum.getCategoryId());
			request.setAttribute("category", category);
			
			//根据topic获取memberId，从而找到对应的member
			MemberDao memberDao=new MemberDaoImpl();
			Member member=new Member();
			member=memberDao.findById(topic.getMemberId());
			request.setAttribute("member",member);
			
			//获取MemberId获取该member一共发帖的数量
			int count=0;
			count=topicDao.memberTopicCount(topic.getMemberId());
			request.setAttribute("count", count);
			//显示回复
			ReplyDao replyDao=new ReplyDaoImpl();
			
			PageModel<Reply> pm=replyDao.findByTopicId2(Integer.parseInt(topicId),Integer.parseInt(pageNo),pageSize);
			List<Reply> replyList=pm.getDatas();
			request.setAttribute("pm", pm);
			
			Map<Reply,Member> map=new LinkedHashMap<Reply,Member>();
			for(int i=0;i<replyList.size();i++){
				Reply reply=replyList.get(i);
				Member member2=memberDao.findByName2(reply.getMember());
				map.put(reply, member2);
				
			}
			request.setAttribute("replyList",map);
			
			
			//更新人气
			topicDao.updateTopicVisitCount(topic.getId());
			request.getRequestDispatcher("/boardCate/bbs_board_cate_topic2.jsp").forward(request, response);
		}
	}

	public void toForumTopicList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=5;
		if(pageNo==null){
			pageNo="1";
		}
		//获取页面传来的forumId
		String forumId=request.getParameter("id");
		//后台验证
		if(forumId==null||forumId.equals("")){
			request.setAttribute("msg", "forumId获取失败，请重试");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
			//获取该板块下所有的topic
		Topic topic=new Topic();
		TopicDao topicDao=new TopicDaoImpl();
		PageModel<Topic> pm=topicDao.findByForumIdAll2(Integer.parseInt(forumId),Integer.parseInt(pageNo),pageSize);
		List<Topic> topicList=pm.getDatas();
		request.setAttribute("pm", pm);
		//设置共享变量
        request.setAttribute("topicList", topicList);
        
        //获取该forumId所对应的forum
        Forum forum=new Forum();
        ForumDao forumDao=new ForumDaoImpl();
        forum=forumDao.findById(Integer.parseInt(forumId));
        request.setAttribute("forum", forum);
        
        //获取该forumId所对应的category
        Category category=new Category();
        CategoryDao categoryDao=new CategoryDaoImpl();
        category=categoryDao.findById(forum.getCategoryId());
        request.setAttribute("category", category);
        
        //获取所有的memberList
        Member member=new Member();
        MemberDao memberDao=new MemberDaoImpl();
        List<Member> memberList=memberDao.findAll();
        request.setAttribute("memberList", memberList);
        
        
		
		//循环输出帖子名称，帖子时间，已经和发帖所在的forumId相同的帖子名
		request.getRequestDispatcher("/boardCate/bbs_board_cate.jsp").forward(request, response);
		/*
		 *定义一个map对象保存帖子和作者
		 *Map<Topic,Member> map=new LinkedHashMap<Topic,Member>();
		 *根据板块id查询该板块下所有的话题
		 *TopicDao topicDao=new TopicDaoImpl();
		 *List<Topic> topicList=topicDao.findByForumIdAll(Integer.parseInt(forumid));
		 *MemberDao memberDao=new MemberDaoImpl();
		 *循环topicList获取每个帖子会员的信息
		 *for(int i=0;i<topicList.size;i++){
		 *Topic topic=topicList.size();
		 *Member member=memberDao.findById(topic.getMemberId());
		 *map.put(topic,member);
		 *}
		 *request.setAttribute("topicList",map);
		 */
		}
	}

	public void toCateTopic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		//获取页面传来的topicId
		String topicId=request.getParameter("id");
		//后台验证id是否传输成功
		if(topicId==null||topicId.equals("")){
			request.setAttribute("msg", "Id获取失败，请重试");
			request.getRequestDispatcher("/member/mytopics.jsp").forward(request, response);
		}else{
			//根据topicId获取所对应的attach
			List<Attachment> attList=new ArrayList<Attachment>();
			AttachmentDao attDao=new AttachmentDaoImpl();
			attList=attDao.findAll();
			request.setAttribute("attList", attList);
			//根据topicId获取对应的topic
			TopicDao topicDao=new TopicDaoImpl();
			Topic topic=topicDao.findById(Integer.parseInt(topicId));
			request.setAttribute("topic", topic);
			
			//根据topic获取forumId，从而找到所对应的forum
			ForumDao forumDao=new ForumDaoImpl();
			Forum forum=new Forum();
			forum=forumDao.findById(topic.getForumId());
			request.setAttribute("forum", forum);
			
			//根据forum找到categoryId，从而找到对应的category
			CategoryDao categoryDao=new CategoryDaoImpl();
			Category category=new Category();
			category=categoryDao.findById(forum.getCategoryId());
			request.setAttribute("category", category);
			
			//根据topic获取memberId，从而找到对应的member
			MemberDao memberDao=new MemberDaoImpl();
			Member member=new Member();
			member=memberDao.findById(topic.getMemberId());
			request.setAttribute("member",member);
			
			//获取MemberId获取该member一共发帖的数量
			int count=0;
			count=topicDao.memberTopicCount(topic.getMemberId());
			request.setAttribute("count", count);
			//显示回复
			ReplyDao replyDao=new ReplyDaoImpl();
			
			PageModel<Reply> pm=replyDao.findByTopicId2(Integer.parseInt(topicId),Integer.parseInt(pageNo),pageSize);
			List<Reply> replyList=pm.getDatas();
			request.setAttribute("pm", pm);
			
			Map<Reply,Member> map=new LinkedHashMap<Reply,Member>();
			for(int i=0;i<replyList.size();i++){
				Reply reply=replyList.get(i);
				Member member2=memberDao.findByName2(reply.getMember());
				map.put(reply, member2);
				
			}
			request.setAttribute("replyList",map);
			
			
			//更新人气
			topicDao.updateTopicVisitCount(topic.getId());
			request.getRequestDispatcher("/boardCate/bbs_board_cate_topic5.jsp").forward(request, response);
		}
	}

	/*
	 * 增加帖子到数据库
	 */
	public void addTopic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub

		//获取页面内容
		String title=request.getParameter("title");
		String forumId=request.getParameter("forumId");
		String content=request.getParameter("content");
		//后台验证
		if(title==null||title.equals("")){
			request.setAttribute("msg", "请输入标题");
			request.getRequestDispatcher("/bbs/bbs_post.jsp").forward(request, response);
		}else{
			//为topic赋值
			ReplyDao replyDao=new ReplyDaoImpl();
			Topic topic=new Topic();
			
			topic.setTitle(title);
			topic.setForumId(Integer.parseInt(forumId));
			topic.setContent(content);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			topic.setPubTime(format.format(new Date()));
			topic.setIp(getIpAddr(request));
			Member member=(Member) request.getSession().getAttribute("memlog");
			
			topic.setMemberId(member.getId());
			topic.setLastReplyMember(member.getLoginName());
			TopicDao topicDao=new TopicDaoImpl();
			//更新板块列表信息
			ForumDao forumDao=new ForumDaoImpl();
			Forum forum=new Forum();
			forum.setLastMember(member.getNickName());
			forum.setRegTime(format.format(new Date()));
			//增加积分
			MemberDao memberDao=new MemberDaoImpl();
			int inc=5;
			boolean flag2=memberDao.updateMemberIntegral(inc,member.getId());
			if(flag2){
				//增加帖子
				boolean flag=topicDao.addTopic(topic);
				System.out.println("123123");
			    
				if(flag)
				{
					//获取今日的发帖数量，并且设置session
					//获取当前日期，并且进行格式化
					SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd");
					int count5=topicDao.findAllTopicCountToday(format2.format(new Date()));
					request.getSession().setAttribute("topicCountToday", count5);
					//设置session，获取总主题数
					int count6=0;
					count6=topicDao.findAllTopicCount();
					request.getSession().setAttribute("count6", count6);
					//获取MemberId获取该member一共发帖的数量
					
					int count2=0;
					count2=topicDao.memberTopicCount(member.getId());
					request.getSession().setAttribute("count2", count2);
					//获取总的回复数
					int count8=replyDao.findAllReplyCount();
					request.getSession().setAttribute("count8", count8);
					//写入最后回复者的信息到topic表中
					
					memberDao.updateTopicCount(member.getId());
					boolean flg=forumDao.updateForumInfo(forum.getLastMember(),forum.getRegTime(),topic.getTitle(),topic.getForumId());
					if(flg){
						myTopicList(request,response);
					}else{
						request.setAttribute("msg", "更新板块信息失败，请重试！");
						request.getRequestDispatcher("/bbs/bbs_post.jsp").forward(request, response);
					}
					
				}else{
					request.setAttribute("msg", "请输入标题");
					request.getRequestDispatcher("/bbs/bbs_post.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("msg", "积分增加失败，请重试");
				request.getRequestDispatcher("/bbs/bbs_post.jsp").forward(request, response);
			}
			
		}
		
	}

	public void myTopicList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		//获取用户id
		Member member=(Member) request.getSession().getAttribute("memlog");
		TopicDao topicDao=new TopicDaoImpl();
		ForumDao forumDao=new ForumDaoImpl();
		PageModel<Topic> pm=topicDao.findAllByMemberId2(member.getId(),Integer.parseInt(pageNo),pageSize);
		List<Topic> topicList=pm.getDatas();
		request.setAttribute("pm", pm);
		List<Forum> forumList=forumDao.findAll();
		request.setAttribute("forumList", forumList);
		request.setAttribute("topicList", topicList);
		
		//循环输出帖子名称，帖子时间，已经和发帖所在的forumId相同的帖子名
		request.getRequestDispatcher("/member/mytopics.jsp").forward(request, response);
		
	}

	public void myReply(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=10;
		if(pageNo==null){
			pageNo="1";
		}
		//获取用户id
		Member member=(Member)request.getSession().getAttribute("memlog");
		Reply reply=new Reply();
		ReplyDao replyDao=new ReplyDaoImpl();
		Topic topic=new Topic();
		TopicDao topicDao=new TopicDaoImpl();
		Forum forum=new Forum();
		ForumDao forumDao=new ForumDaoImpl();
		//定义map
		Map<Reply,Topic> map=new LinkedHashMap<Reply,Topic>();
		//获取该登录用户的所有回复列表
		PageModel<Reply> pm=replyDao.findByMemberLoginName2(member.getLoginName(),Integer.parseInt(pageNo),pageSize);
		List<Reply> replyList=pm.getDatas();
		request.setAttribute("pm", pm);
		//设置键值对
		for(int i=0;i<replyList.size();i++){
			reply=replyList.get(i);
			topic=topicDao.findById(reply.getTopicId());
			map.put(reply, topic);
		}
		request.setAttribute("replyList", map);
		//获取所有的板块
		List<Forum> forumList=forumDao.findAll();
		request.setAttribute("forumList", forumList);
		
		
		request.getRequestDispatcher("/member/myreplies.jsp").forward(request, response);
	}
	//论坛首页信息展示功能
	public void IndexMethod(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		//查询出所有的分区信息
		CategoryDao categoryDao=new CategoryDaoImpl();
		List<Category> cateList=categoryDao.findAll();
		ReplyDao replyDao=new ReplyDaoImpl();
		ForumDao forumDao=new ForumDaoImpl();
		MemberDao memberDao=new MemberDaoImpl();
		/*
		 * //提供一个顶级容器map
		Map<Category,List<Forum>> map=new LinkedHashMap<Category,List<Forum>>();
		//循环分区，根据分区id获取每个分区下面所有的板块
		for(int i=0;i<cateList.size();i++)
		{
			Category category=cateList.get(i);
			List<Forum> forumList=forumDao.findAllById(category.getId());
			map.put(category, forumList);
		}
		
		List<Forum> forumList=forumDao.findAll();
		MemberDao memberDao=new MemberDaoImpl();
		Map<Forum,List<Member>> map2=new LinkedHashMap<Forum,List<Member>>();
		for(int i=0;i<forumList.size();i++)
		{
			Forum forum=forumList.get(i);
			List<Member> memberList=memberDao.findAllById(forum.getId());
			map2.put(forum, memberList);
		}
		request.setAttribute("memberList", map2);
		
		 */
		
		
		Map<Category,Map<Forum,List<Member>>> topmap=new LinkedHashMap<Category,Map<Forum,List<Member>>>();
		
		for(int i=0;i<cateList.size();i++)
		{
			Category category=cateList.get(i);
			List<Forum> forumList=forumDao.findAllById(category.getId());
			Map<Forum,List<Member>> map=new LinkedHashMap<Forum,List<Member>>();
			for(int j=0;j<forumList.size();j++)
			{
				Forum forum=forumList.get(j);
				List<Member> mList=memberDao.findForumByForumId(forum.getId());
				map.put(forum, mList);
			}
			topmap.put(category, map);
		}
		request.setAttribute("mapList", topmap);
		//获取当前日期，并且进行格式化
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		TopicDao topicDao=new TopicDaoImpl();
		//定义一个map用来存放板块和今日发帖的数量
		Map<Forum,Integer> mapcount=new LinkedHashMap<Forum,Integer>();
		//查询出所有的板块，最后发帖的ID
		List<Forum> forum2List=forumDao.findAll();
		for(int j=0;j<forum2List.size();j++)
		{
			Forum forum2=forum2List.get(j);
			int count=topicDao.findTopicCountToday(forum2.getId(),format.format(new Date()));
			mapcount.put(forum2, count);
		}
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
		
		//获取今日的发帖数量，并且设置session
		//获取当前日期，并且进行格式化
		SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd");
		int count5=topicDao.findAllTopicCountToday(format2.format(new Date()));
		request.getSession().setAttribute("topicCountToday", count5);
        request.setAttribute("topicCount", mapcount);
		
		
		
		
		request.getRequestDispatcher("/bbs_index.jsp").forward(request, response);
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
