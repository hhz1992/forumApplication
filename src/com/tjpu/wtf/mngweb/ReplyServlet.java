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

public class ReplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReplyServlet() {
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
		if(method==null||method.equals("")){
			request.setAttribute("msg", "获取方法失败，请重试");
			request.getRequestDispatcher("/boardCate/bbs_board_cate_topic.jsp").forward(request, response);
		}else if(method.equals("addReply")){
			addReply(request,response);
		}else if(method.equals("replyList")){
			replyList(request,response);
		}else if(method.equals("toReply")){
			toReply(request,response);
		}else if(method.equals("copy")){
			copy(request,response);
		}else if(method.equals("copy2")){
			copy2(request,response);
		}else if(method.equals("delete")){
			delete(request,response);
		}else if(method.equals("shield")){
			shield(request,response);
		}else if(method.equals("show")){
			show(request,response);
		}else if(method.equals("serach")){
			serachMemberReply(request,response);
		}else if(method.equals("serachReplyContent")){
			serachReplyContent(request,response);
		}else if(method.equals("nextReply")){
			nextReply(request,response);
		}else if(method.equals("serachNextMemberReply")){
			serachNextMemberReply(request,response);
		}
			
		
	}

	


private void serachNextMemberReply(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
	// TODO Auto-generated method stub//
	
	//获取页面上传来的数据
	String count=request.getParameter("count");
	String loginName=request.getParameter("loginName");
	//如果已经到了第一页，则点击上一页时，显示第一页的内容
	if(Integer.parseInt(count)==0){
		serachMemberReply(request,response);
	}else{
		//d减1代表当前页数
		int d=Integer.parseInt(count);
		int k=0;
		//获取所有的回复
		Reply reply=new Reply();
		ReplyDao replyDao=new ReplyDaoImpl();
		MemberDao memberDao=new MemberDaoImpl();
		TopicDao topicDao=new TopicDaoImpl();
		ForumDao forumDao=new ForumDaoImpl();
		CategoryDao categoryDao=new CategoryDaoImpl();
		List<Reply> replyList=new ArrayList<Reply>();
		replyList=replyDao.findByMemberLoginName(loginName);
		//判断翻页是否已经过分
		if((d-1)*10>=replyList.size()){
			request.setAttribute("msg", "已经查到尾页，已自动跳转到首页");
			serachMemberReply(request,response);
		}else{
			//判断是否已经到达回复条数的上限
			if(d*10<=replyList.size()){
				k=d*10;
			}else{
				k=replyList.size();
			}
			//设置回复和回复者的map（键，值）
			Map<Reply,Member> map=new LinkedHashMap<Reply,Member>();
			for(int i=(d-1)*10;i<k;i++){
				reply=replyList.get(i);
				Member member=memberDao.findByLoginName(reply.getMember());
				map.put(reply, member);
			}
			//获取所有的帖子信息
			List<Topic> topicList=new ArrayList<Topic>();
			topicList=topicDao.findAll();
			request.setAttribute("topicList", topicList);
			
			//获取所有的板块信息
			List<Forum> forumList=new ArrayList<Forum>();
			forumList=forumDao.findAll();
			request.setAttribute("forumList", forumList);
			
			//获取所有分区的信息
			List<Category> categoryList=new ArrayList<Category>();
			categoryList=categoryDao.findAll();
			request.setAttribute("categoryList", categoryList);
			
			//只显示前10条数据
			int replyCount=0;
			replyCount=replyList.size();
			request.setAttribute("replyCount", replyCount);
			int replyCount10=(int)replyCount/10+1;
			request.setAttribute("replyCount10", replyCount10);
			
			//显示当前页数
			request.setAttribute("now", count);
			request.setAttribute("replyList", map);
			request.setAttribute("loginName", loginName);
			request.getRequestDispatcher("/admin/reply/searchReplyNextList.jsp").forward(request, response);
		}
	}
	
	}

private void nextReply(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String count=request.getParameter("count");
		if(Integer.parseInt(count)==0){
			replyList(request,response);
		}else{
			int d=Integer.parseInt(count);
			int k=0;
			//获取所有的回复
			Reply reply=new Reply();
			ReplyDao replyDao=new ReplyDaoImpl();
			MemberDao memberDao=new MemberDaoImpl();
			TopicDao topicDao=new TopicDaoImpl();
			ForumDao forumDao=new ForumDaoImpl();
			CategoryDao categoryDao=new CategoryDaoImpl();
			List<Reply> replyList=new ArrayList<Reply>();
			replyList=replyDao.findAll();
			//判断翻页是否已经过分
			if((d-1)*10>=replyList.size()){
				request.setAttribute("msg", "已经查到尾页，已自动跳转到首页");
				replyList(request,response);
			}else{
				//判断是否已经到达回复条数的上限
				if(d*10<=replyList.size()){
					k=d*10;
				}else{
					k=replyList.size();
				}
				//设置回复和回复者的map（键，值）
				Map<Reply,Member> map=new LinkedHashMap<Reply,Member>();
				for(int i=(d-1)*10;i<k;i++){
					reply=replyList.get(i);
					Member member=memberDao.findByLoginName(reply.getMember());
					map.put(reply, member);
				}
				//获取所有的帖子信息
				List<Topic> topicList=new ArrayList<Topic>();
				topicList=topicDao.findAll();
				request.setAttribute("topicList", topicList);
				
				//获取所有的板块信息
				List<Forum> forumList=new ArrayList<Forum>();
				forumList=forumDao.findAll();
				request.setAttribute("forumList", forumList);
				
				//获取所有分区的信息
				List<Category> categoryList=new ArrayList<Category>();
				categoryList=categoryDao.findAll();
				request.setAttribute("categoryList", categoryList);
				
				//只显示前10条数据
				int replyCount=0;
				replyCount=replyList.size();
				request.setAttribute("replyCount", replyCount);
				int replyCount10=(int)replyCount/10+1;
				request.setAttribute("replyCount10", replyCount10);
				
				//显示当前页数
				request.setAttribute("now", count);
				request.setAttribute("replyList", map);
				request.getRequestDispatcher("/admin/reply/replyNextList.jsp").forward(request, response);
				
			}
		}
		
		
	}

private void serachReplyContent(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
	String pageNo=request.getParameter("pageNo");
	int pageSize=10;
	if(pageNo==null){
		pageNo="1";
	}
	//获取页面传来的topicId
	String topicId=request.getParameter("id");
	String floor=request.getParameter("floor");
	request.setAttribute("floor", floor);
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
		CategoryDao categoryDao=new CategoryDaoImpl();
		Category category=new Category();
		MemberDao memberDao=new MemberDaoImpl();
		Member member=new Member();
		ReplyDao replyDao=new ReplyDaoImpl();
		
		forum=forumDao.findById(topic.getForumId());
		request.setAttribute("forum", forum);
		
		//根据forum找到categoryId，从而找到对应的category
		
		category=categoryDao.findById(forum.getCategoryId());
		request.setAttribute("category", category);
		
		//根据topic获取memberId，从而找到对应的member
		
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
		request.getRequestDispatcher("/boardCate/bbs_board_cate_topic3.jsp").forward(request, response);
	}
	}

private void serachMemberReply(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String loginName=request.getParameter("loginName");
		if(loginName==null||loginName.equals("")){
			request.setAttribute("msg","请输入发帖者名称");
			request.getRequestDispatcher("/admin/reply/replyList.jsp").forward(request, response);
		}else{
			//获取该用户的回复
			Reply reply=new Reply();
			ReplyDao replyDao=new ReplyDaoImpl();
			MemberDao memberDao=new MemberDaoImpl();
			List<Reply> replyList=new ArrayList<Reply>();
			TopicDao topicDao=new TopicDaoImpl();
			ForumDao forumDao=new ForumDaoImpl();
			CategoryDao categoryDao=new CategoryDaoImpl();
			replyList=replyDao.findByMemberLoginName(loginName);
			if(replyList.size()==0){
				request.setAttribute("msg", "您所输入的回复者名称不存在，请重新输入！");
			}
			//判断是否已经到达回复条数的上限
			int k=0;
			if(10<=replyList.size()){
				k=10;
			}else{
				k=replyList.size();
			}
			//设置回复和回复者的map（键，值）
			Map<Reply,Member> map=new LinkedHashMap<Reply,Member>();
			for(int i=0;i<k;i++){
				reply=replyList.get(i);
				Member member=memberDao.findByLoginName(reply.getMember());
				map.put(reply, member);
			}
			//获取所有的帖子信息
			
			List<Topic> topicList=new ArrayList<Topic>();
			topicList=topicDao.findAll();
			//设置共享变量，为后来的值进行传递
			request.setAttribute("loginName", loginName);
			request.setAttribute("topicList", topicList);
			
			//获取所有的板块信息
			
			List<Forum> forumList=new ArrayList<Forum>();
			forumList=forumDao.findAll();
			request.setAttribute("forumList", forumList);
			
			//获取所有分区的信息
			
			List<Category> categoryList=new ArrayList<Category>();
			categoryList=categoryDao.findAll();
			request.setAttribute("categoryList", categoryList);
			
			//只显示前10条数据
			int replyCount=0;
			replyCount=replyList.size();
			request.setAttribute("replyCount", replyCount);
			int replyCount10=(int)replyCount/10+1;
			request.setAttribute("replyCount10", replyCount10);
			
			request.setAttribute("replyList", map);
			request.getRequestDispatcher("/admin/reply/searchReplyList.jsp").forward(request, response);
		}
	}

private void show(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException{
		// TODO Auto-generated method stub
	String replyId=request.getParameter("replyId");
	ReplyDao replyDao=new ReplyDaoImpl();
	//设置回复为显示
	boolean flag=replyDao.setStatus1(Integer.parseInt(replyId));
	if(flag){
		replyList(request,response);
	}else{
		request.setAttribute("msg","显示该回复失败，请重试");
		request.getRequestDispatcher("/admin/reply/replyList.jsp").forward(request, response);
	}
	}

public void shield(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException{
		// TODO Auto-generated method stub
		String replyId=request.getParameter("replyId");
		ReplyDao replyDao=new ReplyDaoImpl();
		//设置回复为隐藏
		boolean flag=replyDao.setStatus2(Integer.parseInt(replyId));
		if(flag){
			replyList(request,response);
		}else{
			request.setAttribute("msg","屏蔽该回复失败，请重试");
			request.getRequestDispatcher("/admin/reply/replyList.jsp").forward(request, response);
		}
		
	}

private void delete(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException{
		// TODO Auto-generated method stub
	String replyId=request.getParameter("replyId");
	if(replyId==null||replyId.equals("")){
		request.setAttribute("msg","回复id获取有误，请稍后再试");
		request.getRequestDispatcher("/admin/reply/replyList.jsp").forward(request, response);
	}else{
		ReplyDao replyDao=new ReplyDaoImpl();
		TopicDao topicDao=new TopicDaoImpl();
		MemberDao memberDao=new MemberDaoImpl();
		//获取reply所对应的topicId
		
		Reply reply=replyDao.findByReplyId(Integer.parseInt(replyId));
		
		
		
		
		boolean flag=replyDao.deleteByReplyId(Integer.parseInt(replyId));
		
		if(flag){
			//设置session，获取总的回复数
			int count8=0;
			count8=replyDao.findAllReplyCount();
			request.getSession().setAttribute("count8", count8);
			//更新member列表中的replyCount字段
			memberDao.updateMemberReplyCount1(reply.getMember());
			//更新topic列表中replyCount的值
			topicDao.updateReplyCountByTopicId(reply.getTopicId());
			replyList(request,response);
		}else{
			request.setAttribute("msg","删除失败");
			request.getRequestDispatcher("/admin/reply/replyList.jsp").forward(request, response);
		}
	}
	
		
	}

private void copy2(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException{
		// TODO Auto-generated method stub
	String pageNo=request.getParameter("pageNo");
	int pageSize=10;
	if(pageNo==null){
		pageNo="1";
	}
	//获取页面传来的topicId
	String topicId=request.getParameter("topicId");
	Topic topic2=new Topic();
	TopicDao topicDao=new TopicDaoImpl();
	//获取该topicId所对应的topic
	topic2=topicDao.findById(Integer.parseInt(topicId));
	//获取该topic所设置的楼层默认为1，内容
	String content=topic2.getContent();
	request.setAttribute("content", content);
	request.setAttribute("floor", 1);
	
	//后台验证id是否传输成功
	if(topicId==null||topicId.equals("")){
		request.setAttribute("msg", "Id获取失败，请重试");
		request.getRequestDispatcher("/member/mytopics.jsp").forward(request, response);
	}else{
		ForumDao forumDao=new ForumDaoImpl();
		Forum forum=new Forum();
		CategoryDao categoryDao=new CategoryDaoImpl();
		Category category=new Category();
		MemberDao memberDao=new MemberDaoImpl();
		Member member=new Member();
		ReplyDao replyDao=new ReplyDaoImpl();
		int count=0;
		//根据topicId获取所对应的attach
		List<Attachment> attList=new ArrayList<Attachment>();
		AttachmentDao attDao=new AttachmentDaoImpl();
		attList=attDao.findAll();
		request.setAttribute("attList", attList);
		//根据topicId获取对应的topic
		Topic topic=topicDao.findById(Integer.parseInt(topicId));
		request.setAttribute("topic", topic);
		
		//根据topic获取forumId，从而找到所对应的forum
		forum=forumDao.findById(topic.getForumId());
		request.setAttribute("forum", forum);
		
		//根据forum找到categoryId，从而找到对应的category
		category=categoryDao.findById(forum.getCategoryId());
		request.setAttribute("category", category);
		
		//根据topic获取memberId，从而找到对应的member
		member=memberDao.findById(topic.getMemberId());
		request.setAttribute("member",member);
		
		//获取MemberId获取该member一共发帖的数量
		
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
		request.getRequestDispatcher("/boardCate/bbs_board_cate_topic5.jsp").forward(request, response);
	}
		
	}

private void copy(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException{
		// TODO Auto-generated method stub
	String pageNo=request.getParameter("pageNo");
	int pageSize=10;
	if(pageNo==null){
		pageNo="1";
	}
	//获取页面传来的replyId和topicId
	String replyId=request.getParameter("replyId");
	String topicId=request.getParameter("topicId");
	//自己调试用的
	if(topicId==null||topicId.equals("")){
		request.setAttribute("msg", "Id获取失败，请重试");
		System.out.println(topicId);
		request.getRequestDispatcher("/boardCate/bbs_board_cate_topic5.jsp").forward(request, response);
	}
	//获取replyId所对应的reply
	Reply reply2=new Reply();
	ReplyDao replyDao=new ReplyDaoImpl();
	reply2=replyDao.findByReplyId(Integer.parseInt(replyId));
	//获取其楼层和内容
	String content=reply2.getContent();
	int floor=reply2.getFloor();
	request.setAttribute("content", content);
	request.setAttribute("floor", floor);
	
	//根据topicId获取所对应的attach
	List<Attachment> attList=new ArrayList<Attachment>();
	AttachmentDao attDao=new AttachmentDaoImpl();
	attList=attDao.findAll();
	request.setAttribute("attList", attList);
	//后台验证id是否传输成功
	if(topicId==null||topicId.equals("")){
		request.setAttribute("msg", "Id获取失败，请重试");
		request.getRequestDispatcher("/member/mytopics.jsp").forward(request, response);
	}else{
		//根据topicId获取对应的topic
		TopicDao topicDao=new TopicDaoImpl();
		ForumDao forumDao=new ForumDaoImpl();
		Forum forum=new Forum();
		CategoryDao categoryDao=new CategoryDaoImpl();
		Category category=new Category();
		MemberDao memberDao=new MemberDaoImpl();
		Member member=new Member();
		int count=0;
		Topic topic=topicDao.findById(Integer.parseInt(topicId));
		request.setAttribute("topic", topic);
		
		//根据topic获取forumId，从而找到所对应的forum
		forum=forumDao.findById(topic.getForumId());
		request.setAttribute("forum", forum);
		
		//根据forum找到categoryId，从而找到对应的category
		category=categoryDao.findById(forum.getCategoryId());
		request.setAttribute("category", category);
		
		//根据topic获取memberId，从而找到对应的member
		member=memberDao.findById(topic.getMemberId());
		request.setAttribute("member",member);
		
		//获取MemberId获取该member一共发帖的数量
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
		request.getRequestDispatcher("/boardCate/bbs_board_cate_topic5.jsp").forward(request, response);
	}
	
		
	}





private void toReply(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
	 //获取帖子id
	String topicId=request.getParameter("topicId2");
	request.setAttribute("topicId",topicId);
	TopicDao topicDao=new TopicDaoImpl();
	MemberDao memberDao=new MemberDaoImpl();
	ForumDao forumDao=new ForumDaoImpl();
	CategoryDao categoryDao=new CategoryDaoImpl();
	//再次根据topicId查询会员信息和主题数
	Topic topic=topicDao.findById(Integer.parseInt(topicId));
	Member member3=memberDao.findById(topic.getMemberId());
	request.setAttribute("topic", topic);
	//根据topic中的forumId找到forum
	Forum forum=forumDao.findById(topic.getForumId());
	request.setAttribute("forum", forum);
	
	//根据forum中的categoryId找到category
	Category category=categoryDao.findById(forum.getCategoryId());
	request.setAttribute("category", category);
	request.getRequestDispatcher("/boardCate/bbs_board_cate_reply.jsp").forward(request, response);
	}

private void addReply(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	String pageNo=request.getParameter("pageNo");
	int pageSize=10;
	if(pageNo==null){
		pageNo="1";
	}
	
	
	    //获取帖子id
		String topicId=request.getParameter("topicId");
		//获取session
		Member member=(Member) request.getSession().getAttribute("memlog");
		//获取回复内容
		String content=request.getParameter("content");
		//获取系统当前时间
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取回复ip地址
		String ip=getIpAddr(request);
		
			//根据帖子id值，查询当前回复表中是否存在回复信息，如果没有，则直接设置本次回复楼层值为2，
			//否则就得到当前回复信息中，最大的楼层值再加1
			ReplyDao replyDao=new ReplyDaoImpl();
			List<Reply> replyList2=replyDao.findByTopicId(Integer.parseInt(topicId));
			Reply replyNew=new Reply();
			
			if(replyList2.size()>0){
				System.out.println("a");
				//查询当前楼层最大值
				Reply reply=replyDao.findMaxFloor(Integer.parseInt(topicId));
				replyNew.setFloor(reply.getFloor()+1);
			}else{//直接设置楼层为2
				replyNew.setFloor(2);
			}
			replyNew.setContent(content);
			replyNew.setPubTime(format.format(new Date()));
			replyNew.setIp(ip);
			replyNew.setTopicId(Integer.parseInt(topicId));
			replyNew.setMember(member.getLoginName());
			//添加回复
			boolean flag=replyDao.addReply(replyNew);
			if(flag){//添加成功，则回到帖子页面，并显示所有的回复信息
				//设置session，获取总的回复数
				System.out.println("b");
				int count8=0;
				TopicDao topicDao=new TopicDaoImpl();
				MemberDao memberDao=new MemberDaoImpl();
				ForumDao forumDao=new ForumDaoImpl();
				CategoryDao categoryDao=new CategoryDaoImpl();
				count8=replyDao.findAllReplyCount();
				request.getSession().setAttribute("count8", count8);
				//更新topic中的lastMember
				topicDao.updatelastReplyMember(member.getLoginName(),Integer.parseInt(topicId));
				//增加积分5分
				int inc=5;
				boolean flag3=memberDao.updateMemberIntegral(inc,member.getId());
				//更新member的回复次数
				boolean flag2=memberDao.updateMemberReplyCount(member.getId());
				//更新topic的replyCount，lastReplyTime
				boolean flag4=topicDao.updateTopicReplyCountandLastReplyTime(format.format(new Date()),Integer.parseInt(topicId));
				//获取最大的replyId
				Reply reply2=replyDao.findMaxReplyId(Integer.parseInt(topicId));
				boolean flag5=topicDao.updateReplyId(reply2.getId(),Integer.parseInt(topicId));
				if(flag2){
					System.out.println("c");
					//根据帖子id查询出属于该话题的所有回复内容和所属会员信息
					PageModel<Reply> pm=replyDao.findByTopicId2(Integer.parseInt(topicId),Integer.parseInt(pageNo),pageSize);
					List<Reply> replyList=pm.getDatas();
					request.setAttribute("pm", pm);
					Map<Reply,Member> map=new LinkedHashMap<Reply,Member>();
					for(int i=0;i<replyList.size();i++){
						Reply reply=replyList.get(i);
						Member member2=memberDao.findByName2(reply.getMember());
						map.put(reply, member2);
					}
					System.out.println("d");
					request.setAttribute("replyList",map);
					//再次根据topicId查询会员信息和主题数
					Topic topic=topicDao.findById(Integer.parseInt(topicId));
					Member member3=memberDao.findById(topic.getMemberId());
					request.setAttribute("topic", topic);
					request.setAttribute("member", member3);
					//根据topic中的forumId找到forum
					Forum forum=forumDao.findById(topic.getForumId());
					request.setAttribute("forum", forum);
					
					//根据forum中的categoryId找到category
					Category category=categoryDao.findById(forum.getCategoryId());
					request.setAttribute("category", category);
					
					//获取MemberId获取该member一共发帖的数量
					
					int count2=0;
					count2=topicDao.memberTopicCount(member.getId());
					request.getSession().setAttribute("count2", count2);
					
					//根据topicId获取所对应的attach
					List<Attachment> attList=new ArrayList<Attachment>();
					AttachmentDao attDao=new AttachmentDaoImpl();
					attList=attDao.findAll();
					request.setAttribute("attList", attList);
					//定义一个关于回复者member与其发帖数的count
					request.getRequestDispatcher("/boardCate/bbs_board_cate_topic5.jsp").forward(request, response);
				
					System.out.println("e");
				}else{
					request.setAttribute("msg", "回复次数增加失败，请重试");
					request.getRequestDispatcher("/boardCate/bbs_board_cate_topic5.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("msg", "回复失败，请重试");
				request.getRequestDispatcher("/boardCate/bbs_board_cate_topic5.jsp").forward(request, response);
			}
		
		
		
	}

private void replyList(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException{
	// TODO Auto-generated method stub
	//获取所有的回复
	Reply reply=new Reply();
	ReplyDao replyDao=new ReplyDaoImpl();
	MemberDao memberDao=new MemberDaoImpl();
	TopicDao topicDao=new TopicDaoImpl();
	ForumDao forumDao=new ForumDaoImpl();
	CategoryDao categoryDao=new CategoryDaoImpl();
	List<Reply> replyList=new ArrayList<Reply>();
	replyList=replyDao.findAll();
	//判断是否已经到达回复条数的上限
	int k=0;
	if(10<=replyList.size()){
		k=10;
	}else{
		k=replyList.size();
	}
	//设置回复和回复者的map（键，值）
	Map<Reply,Member> map=new LinkedHashMap<Reply,Member>();
	for(int i=0;i<k;i++){
		reply=replyList.get(i);
		Member member=memberDao.findByLoginName(reply.getMember());
		map.put(reply, member);
	}
	//获取所有的帖子信息
	List<Topic> topicList=new ArrayList<Topic>();
	topicList=topicDao.findAll();
	request.setAttribute("topicList", topicList);
	
	//获取所有的板块信息
	List<Forum> forumList=new ArrayList<Forum>();
	forumList=forumDao.findAll();
	request.setAttribute("forumList", forumList);
	
	//获取所有分区的信息
	List<Category> categoryList=new ArrayList<Category>();
	categoryList=categoryDao.findAll();
	request.setAttribute("categoryList", categoryList);
	
	//只显示前10条数据
	int replyCount=0;
	replyCount=replyList.size();
	request.setAttribute("replyCount", replyCount);
	int replyCount10=(int)replyCount/10+1;
	request.setAttribute("replyCount10", replyCount10);
	
	request.setAttribute("replyList", map);
	request.getRequestDispatcher("/admin/reply/replyList.jsp").forward(request, response);
	
}

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
