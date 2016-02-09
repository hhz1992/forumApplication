package com.tjpu.wtf.mngweb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tjpu.wtf.dao.AttachmentDao;
import com.tjpu.wtf.dao.ForumDao;
import com.tjpu.wtf.dao.MemberDao;
import com.tjpu.wtf.dao.TopicDao;
import com.tjpu.wtf.dao.impl.AttachmentDaoImpl;
import com.tjpu.wtf.dao.impl.ForumDaoImpl;
import com.tjpu.wtf.dao.impl.MemberDaoImpl;
import com.tjpu.wtf.dao.impl.TopicDaoImpl;
import com.tjpu.wtf.model.Attachment;
import com.tjpu.wtf.model.Forum;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;
import com.tjpu.wtf.model.Topic;
import com.tjpu.wtf.util.MD5;

public class MemberRegisteServlet extends HttpServlet {

	private MD5 md5=new MD5();
	/**
	 * Constructor of the object.
	 */
	public MemberRegisteServlet() {
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
		if(method!=null)
		{
			if(method.equals("registe"))
			{
				addMember(request,response);
			}else if(method.equals("exit"))
			{
				memberExit(request,response);
			}else if(method.equals("editInfo"))
			{
				memberEdit(request,response);
			}else if(method.equals("updateMemberInfo"))
			{
				updateMemberInfo(request,response);
			}else if(method.equals("updatePwd"))
			{
				updatePwd(request,response);
			}else if(method.equals("systemAvatar")){
				systemAvatar(request,response);
			}else if(method.equals("attachment")){
				attachment(request,response);
			}else if(method.equals("upFile")){
				upFile(request,response);
			}else if(method.equals("attachmentList")){
				attachmentList(request,response);
			}else if(method.equals("deleteAttachment")){
				deleteAttachment(request,response);
			}else if(method.equals("down")){
				down(request,response);
			}
		}else{
			request.setAttribute("mem", "路径有误");
			request.getRequestDispatcher("/registe.jsp").forward(request, response);
		}
	}
	
	


	private void down(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获得文件名称
    	String fileName=request.getParameter("fileName");
    	fileName=new String(fileName.getBytes("iso8859-1"),"gb2312");
    	response.setContentType("application/x-msdownload");
    	response.setHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes("gb2312"),"iso8859-1"));
    	//下载路径
    	String path=this.getServletContext().getRealPath("upload")+"\\"+new String(fileName.getBytes("gb2312"),"iso8859-1");
    	System.out.println(this.getServletContext().getRealPath("upload")+"\\"+new String(fileName.getBytes("GBK"),"UTF-8"));
        File file=new File(path);
        if(!file.exists())
        {
        	request.setAttribute("msg", "文件已经不存在");
        	request.getRequestDispatcher("/boardCate/bbs_board_cate_topic.jsp").forward(request, response);
        }else
        {
        	FileInputStream fis=new FileInputStream(file);
        	BufferedInputStream bis=new BufferedInputStream(fis);
        	BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
          //读取文件
        	int i=bis.read();
        	while(i!=-1)
        	{
        		System.out.println((char)i);
        		bos.write(i);
        		i=bis.read();
        	}
        	bos.flush();
        	fis.close();
        	bos.close();
        
        }
	}

	private void deleteAttachment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String topicId=request.getParameter("topicId");
		if(topicId==null||topicId.equals("")){
			request.setAttribute("msg", "id获取失败，请重试");
			   request.getRequestDispatcher("/bbs/attachment.jsp").forward(request, response);
		}else{
			AttachmentDao attDao=new AttachmentDaoImpl();
			boolean flag=attDao.deleteByTopicId(Integer.parseInt(topicId));
			if(flag){
				attachmentList(request,response);
			}else{
				request.setAttribute("msg", "删除附件失败，请重试");
				   request.getRequestDispatcher("/bbs/attachment.jsp").forward(request, response);
			}
		}
		
	}

	private void attachmentList(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageNo=request.getParameter("pageNo");
		int pageSize=4;
		if(pageNo==null){
			pageNo="1";
		}
		Member member=(Member) request.getSession().getAttribute("memlog");
		TopicDao topicDao=new TopicDaoImpl();
		List<Topic> topicList=topicDao.findAllByMemberId(member.getId());
		request.setAttribute("topicList", topicList);
		AttachmentDao attDao=new AttachmentDaoImpl();
		PageModel<Attachment> pm=attDao.findAllByMemberId2(member.getId(),Integer.parseInt(pageNo),pageSize);
		List<Attachment> attachmentList=pm.getDatas();
		request.setAttribute("pm", pm);
		request.setAttribute("attachmentList", attachmentList);
		request.getRequestDispatcher("/member/attachment.jsp").forward(request, response);
		
	}

	@SuppressWarnings("deprecation")
	private void upFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		DiskFileItemFactory factory=new DiskFileItemFactory();
		String path=request.getRealPath("/upload");
		//设置临时目录
		factory.setRepository(new File(path));
		//设置缓存，如果为-1则不限制大小
		factory.setSizeThreshold(-1);
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setSizeMax(-1);//文件最大尺寸，设为-1表示不受限制
		OutputStream os=null;
		InputStream is=null;
		String fName="";
		try {  
		    @SuppressWarnings("unchecked")
			List<FileItem> list=upload.parseRequest(request);  
		   for(FileItem item:list)  
		    {  
		        if(item.isFormField())//如果不是文件类型  
		        {  
		           String name=item.getFieldName();  
		           String value=item.getString();  
		           request.setAttribute(name, value);  
		             
		        }  
		        else//如果是文件类型  
		        {  
		            String name=item.getFieldName();//得到字段名称  
		            String value=item.getName();//取得该字段的值  
		            int start=value.lastIndexOf("\\");  
		            //获取文件名
		            //fName=value.substring(value.lastIndexOf('/'),value.length());
		            String fileName=value.substring(start+1);
		            fName=fileName;
		           request.setAttribute(name, fileName);                     
		            os=new FileOutputStream(new File(path,fileName));  
		            is=item.getInputStream();  
		            byte[] buffer=new byte[400];  
		            int length=0;  
		            while((length=is.read(buffer))>0)  
		           {  
		                  
		                os.write(buffer,0,length);  
		           }  
		        }  
		          
		    }  
		   Member member=(Member) request.getSession().getAttribute("memlog");
		   //获取描述和主题id
		   String description=request.getAttribute("description").toString();
		   String topicId=request.getAttribute("topicId").toString();
		   System.out.println(description+topicId+fName);
		   Attachment att=new Attachment();
		   att.setMemberId(member.getId());
		   att.setFileName(fName);
		   att.setPath(path+"/"+fName);
		   att.setDescription(description);
		   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   att.setUploadTime(format.format(new Date()));
		   att.setTopicId(Integer.parseInt(topicId));
		   AttachmentDao attDao=new AttachmentDaoImpl();
		   boolean flag=attDao.addAttachment(att);
		   if(flag){
			   //找到刚刚上传的那个文件
			   Attachment attachment=attDao.findByMaxId();
			   //同时更新topicId所对应的attachId
			   TopicDao topicDao=new TopicDaoImpl();
			   topicDao.updateAttachIdByTopicId(attachment.getId(),attachment.getTopicId());
			   attachmentList(request,response);
		   }else{
			   request.setAttribute("msg", "上传文件失败，请重试");
			   request.getRequestDispatcher("/bbs/attachment.jsp").forward(request, response);
		   }
		   
		   
		} catch (FileUploadException e) {  
		    // TODO Auto-generated catch block  
		    e.printStackTrace();  
		}finally{
			if(os!=null){
				os.close();
			}
			if(is!=null){
				is.close();
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
	private void attachment(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
		String topicId=request.getParameter("topicId");
		TopicDao topicDao=new TopicDaoImpl();
		Topic topic=topicDao.findById(Integer.parseInt(topicId));
		request.setAttribute("topic", topic);
		request.getRequestDispatcher("/bbs/attachment.jsp").forward(request, response);
	}

	public void memberExit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		 //获取HTTPSession对象
		HttpSession session=request.getSession();
		//从session对象中移除manager_admin
		session.removeAttribute("memlog");
		//进行页面跳转
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		
	}

	private void addMember(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		//获取用户信息
		String userName=request.getParameter("loginName");
		String password=request.getParameter("password");
		String password2=request.getParameter("repassword");
		String email=request.getParameter("email");
		String checkCode=request.getParameter("checkCode");
		
		//后台验证是否为空
		if(userName==null||userName.equals(""))
		{
			request.setAttribute("mem", "请输入登录用户名");
			request.getRequestDispatcher("/registe.jsp").forward(request, response);
		}else if(password==null||password.equals(""))
		{
			request.setAttribute("mem", "请输入登录密码");
			request.getRequestDispatcher("/registe.jsp").forward(request, response);	
		}else if(email==null||email.equals(""))
		{
			request.setAttribute("mem", "请输入邮箱");
			request.getRequestDispatcher("/registe.jsp").forward(request, response);
		}else if(checkCode==null||checkCode.equals(""))
		{
			request.setAttribute("mem", "请输入验证码");
			request.getRequestDispatcher("/registe.jsp").forward(request, response);
		}else if(password2==null||password2.equals(""))
		{
			request.setAttribute("mem", "请输入确认密码");
			request.getRequestDispatcher("/registe.jsp").forward(request, response);
		}else if(!password.equals(password2))
		{
			request.setAttribute("mem", "2次密码输入有误，请重新输入");
			request.getRequestDispatcher("/registe.jsp").forward(request, response);
		}else{
			//获取系统所给的验证码
			
			String verCode=request.getSession().getAttribute("check_code").toString();//从CheckCodeServlet中设置check_code
			//判断用户输入的验证码是否与系统输出的验证是否相同
			if(checkCode.equalsIgnoreCase(verCode))//不区分大小写
			{
			MemberDao dao=new MemberDaoImpl();
			//验证当前用户名是否存在
			boolean flg=dao.findByName(userName);
			if(!flg){
					request.setAttribute("mem", "用户名已存在，请重新输入");
					request.getRequestDispatcher("/registe.jsp").forward(request, response);
			}else{
				//当前输入的用户名数据库中没有
			Member member=new Member();
			member.setLoginName(userName);
			member.setPassword(md5.getMD5ofStr(password));
			member.setEmail(email);
			member.setStatus(1);
			member.setReplyCount(0);
			member.setTopicCount(0);
			
			//创建一个格式化日期的对象，以获取电脑时间
			member.setMemberIdentity(1);
			
			SimpleDateFormat foramt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			member.setRegTime(foramt.format(new Date()));//调用次方法为CreateTime赋值——当前时间
			//调用实现接口的方法
			member.setIntegral(5);
			boolean flag=dao.addMember(member);
			if(flag){//成功
				//设置session，获取总会员数
				int count7=0;
				count7=dao.findAllMember();
				request.getSession().setAttribute("count7", count7);	
			request.getRequestDispatcher("/regSuccess.jsp").forward(request, response);
			}else{//失败
				request.setAttribute("mem", "注册用户失败，请稍后再试");	
				request.getRequestDispatcher("/registe.jsp").forward(request, response);
			}
			}
			}else {
			request.setAttribute("mem", "验证码错误，请重新输入");
			request.getRequestDispatcher("/registe.jsp").forward(request, response);
			}
		}
		}
	
	
	public void memberEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//获取session中的信息
		Member member=(Member) request.getSession().getAttribute("memlog");
		//创建实现接口类的对象
	    MemberDao dao=new MemberDaoImpl();
	    //调用findById方法
	    Member memberInfo=dao.findById(member.getId());
	    //设置设置共享变量
	    request.setAttribute("memberInfo", memberInfo);
	    //页面跳转
	    request.getRequestDispatcher("/member/edit_info.jsp").forward(request, response);
	    
		
	}
	
		

	public void updateMemberInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		
		//获取用户信息
		String nickName=request.getParameter("nickName");
		String gender=request.getParameter("gender");
		String signature=request.getParameter("signature");
		String bithplace=request.getParameter("bithplace");
		String introducation=request.getParameter("introduction");
		String qq=request.getParameter("qq");
		String msn=request.getParameter("msn");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		String postalCode=request.getParameter("postalCode");
		String email=request.getParameter("email");
		//获取session信息，并且进行强制转换
		Member member=(Member) request.getSession().getAttribute("memlog");
		//创建实现接口的类的方法
		MemberDao dao=new MemberDaoImpl();
		//调用findById方法
		Member memberInfo=dao.findById(member.getId());
		if(memberInfo.getNickName()==null){//原用户昵称没有填写，填写后，加积分
			//更新用户信息
			if(nickName==null||nickName.equals(""))
			{//检测现在是否填写了昵称
				//没有填写，返回null
				memberInfo.setNickName(null);
			}else{
				//填写了，则把页面上的昵称数据写入memberInfo
				memberInfo.setNickName(nickName);
			}
			//获取页面上的数据
			memberInfo.setGender(Integer.parseInt(gender));
			memberInfo.setSignature(signature);
			memberInfo.setBithplace(bithplace);
			memberInfo.setIntroducation(introducation);
			memberInfo.setQq(qq);
			memberInfo.setMsn(msn);
			memberInfo.setMobile(mobile);
			memberInfo.setAddress(address);
			memberInfo.setPostalCode(postalCode);
			memberInfo.setEmail(email);
			//调用此方法，增加积分
			boolean flag=dao.updateMemberInfo(memberInfo);
			if(flag)
			{
				request.setAttribute("msg", "保存成功");
				memberEdit(request,response);
			}else{
				request.setAttribute("msg", "保存失败");
				request.getRequestDispatcher("/member/edit_info.jsp").forward(request, response);
			}
		}else{//原数据库中有昵称，则不增加积分
			//更新用户信息
			//此时不把nickname写入数据库，因为，页面的nickName已经被锁定，获取的话只能得到空值（自己已经测定）
			memberInfo.setGender(Integer.parseInt(gender));
			memberInfo.setSignature(signature);
			memberInfo.setBithplace(bithplace);
			memberInfo.setIntroducation(introducation);
			memberInfo.setQq(qq);
			memberInfo.setMsn(msn);
			memberInfo.setMobile(mobile);
			memberInfo.setAddress(address);
			memberInfo.setPostalCode(postalCode);
			memberInfo.setEmail(email);
			//调用此方法不增加积分
			boolean flag=dao.updateMemberInfo1(memberInfo);
			if(flag)
			{
				request.setAttribute("msg", "保存成功");
				memberEdit(request,response);
			}else{
				request.setAttribute("msg", "保存失败");
				request.getRequestDispatcher("/member/edit_info.jsp").forward(request, response);
			}
		}
		
		
		
	}
	
	
	public void updatePwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取用户信息
		String oldPwd=request.getParameter("oldPwd");
		String newPwd=request.getParameter("newPwd");
		String newPwd2=request.getParameter("newPwd2");
		//后台验证
		if(oldPwd==null||oldPwd==""){
			request.setAttribute("msg", "请输入原密码");
			request.getRequestDispatcher("/member/password.jsp").forward(request, response);
		}
		if(newPwd==null||newPwd==""){
			request.setAttribute("msg", "请输入新密码");
			request.getRequestDispatcher("/member/password.jsp").forward(request, response);
		}
		if(newPwd2==null||newPwd2==""){
			request.setAttribute("msg", "请输入确认密码");
			request.getRequestDispatcher("/member/password.jsp").forward(request, response);
		}else{
			//判断输入的密码是否正确
			//获取session
			Member member=(Member) request.getSession().getAttribute("memlog");
			if(member.getPassword().equals(md5.getMD5ofStr(oldPwd))){//原密码和新输入的密码相同
				if(newPwd.equals(newPwd2)){//新密码2次输入相同
					member.setPassword(md5.getMD5ofStr(newPwd));
					MemberDao dao=new MemberDaoImpl();
					boolean flag=dao.updatePwd(member);
					if(flag){
						request.setAttribute("msg", "修改密码成功");
						request.getRequestDispatcher("/member/password.jsp").forward(request, response);
					}else{
						request.setAttribute("msg", "修改密码失败");
						request.getRequestDispatcher("/member/password.jsp").forward(request, response);
					}
					
				}else{//新密码2次输入不同
					request.setAttribute("msg", "两次密码输入错误，请重新输入");
					request.getRequestDispatcher("/member/password.jsp").forward(request, response);
				}
			
			}else{//原密码输入不同
				request.setAttribute("msg", "原密码输入错误");
				request.getRequestDispatcher("/member/password.jsp").forward(request, response);
			}
		}
		
	}
	

	public void systemAvatar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取图像路径
		String avatar=request.getParameter("avatar");
		//获取session，并且进行强制转换为member
		Member member=(Member) request.getSession().getAttribute("memlog");
		//为member设置属性
		member.setAvatar(avatar);
		//创建接口的实现方法
		MemberDao dao=new MemberDaoImpl();
		//调用更新图像路径的方法
		boolean flag=dao.updateSystemAvatar(member);
		if(flag){//成功
			int inc=5;
			boolean flag2=dao.updateMemberIntegral(inc, member.getId());
			request.setAttribute("msg", "修改成功");
			request.getSession().setAttribute("memlog", member);
			request.getRequestDispatcher("/member/avatar.jsp").forward(request, response);
		
		}else{//失败
			request.setAttribute("msg", "修改失败，请重试");
			request.getRequestDispatcher("/member/avatar.jsp").forward(request, response);
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
