/**
 * 
 */
package com.tjpu.wtf.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;

import com.opensymphony.xwork2.ActionSupport;
import com.tjpu.wtf.dao.MemberDao;
import com.tjpu.wtf.dao.impl.MemberDaoImpl;
import com.tjpu.wtf.model.Member;

/**
 * @author think
 *
 */
public class MemberAvatarAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1414293178137373659L;
	public static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";
	protected static ObjectMapper mapper = new ObjectMapper();
	private File avatar; //头像
	private String avatarFileName; //文件名
	private String avatarContentType; //MIME类型
	
	private String srcName; //头像裁切的源图片名
	private int cutX;//裁切起点X坐标
	private int cutY; //裁切起点Y坐标
	private int cutW; //裁切宽度
	private int cutH; //裁切调度
	
	public String avatarfile(){
		Map<String, String> jsonMap = new HashMap<String, String>(); //存放json数据的map
		try{
			String fileName = IOHelper.getDateTimePatternFileName(avatarFileName);
			System.out.println("执行了吗"+fileName);
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("fileName", fileName);
			//获取临时存放目录File实例
			File parent = new File(getServletContext().getRealPath(Constants.UPLOAD_FILE_TEMP_PATH));
			if(IOHelper.createDir(parent)){
				File dest = new File(parent, fileName);
				BufferedImage srcImg =ImageIO.read(avatar); //把上传的文件内容读取一个BufferedImage实例
			
				int w = srcImg.getWidth();
				int h = srcImg.getHeight();
				if(w > Constants.IMAGE_MAX_WIDTH){ //图片超宽处理
					h = Long.valueOf(Math.round(h * Constants.IMAGE_MAX_WIDTH * 1.0 / w)).intValue();
					w= Constants.IMAGE_MAX_WIDTH;

				}else if(h > Constants.IMAGE_MAX_HEIGHT){
					w = Long.valueOf(Math.round(w * Constants.IMAGE_MAX_HEIGHT * 1.0 / h)).intValue();
					h = Constants.IMAGE_MAX_HEIGHT;
				}
				IOHelper.resizeToFile(srcImg, dest, w, h);
				
				jsonMap.put("width", String.valueOf(w));
				jsonMap.put("height", String.valueOf(h));
			}
			jsonMap.put("opt", "ok");
			jsonMap.put("fileName", fileName);
		}catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("opt", "error");
			jsonMap.put("msg", "头像上传失败，请重新上传！");
		}
		renderJson(jsonMap);
		return null;
	}
	
	public String cut() throws IOException{
	Map<String, String> jsonMap = new HashMap<String, String>(); //存放json数据的map
	HttpServletRequest request=ServletActionContext.getRequest();
	cutX=Integer.parseInt(request.getParameter("cutX"));
	cutY=Integer.parseInt(request.getParameter("cutY"));
	cutW=Integer.parseInt(request.getParameter("cutW"));
	cutH=Integer.parseInt(request.getParameter("cutH"));
	srcName=request.getParameter("srcName");
		//获取当前登录的会员
		//Member curr_member = (Member)getSession().getAttribute(Constants.SESSION_ATT_CURR_MEMBER);
		
		//获取源图片
		File src = new File(getServletContext().getRealPath(Constants.UPLOAD_FILE_TEMP_PATH + Constants.FILE_SEPARATOR + this.srcName));
		
		//创建头像目标文件：全路径形式“服务器真实基路径/upload/用名登录名/日期时间格式字符串.扩展名”
		File dest = new File(getServletContext().getRealPath(Constants.UPLOAD_AVATAR_PATH),
				IOHelper.getDateTimePatternFileName(this.srcName));
		System.out.println(dest.getAbsolutePath());
		BufferedImage srcImg = ImageIO.read(src);
		//裁切图片
		BufferedImage cutImg = srcImg.getSubimage(cutX, cutY, cutW, cutH);
		
		//从App全局字典数据中取出头像的宽和高
		//Map<String, DataDictionary> map = (Map<String, DataDictionary>)getServletContext().getAttribute(Constants.GLOBAL_DICT_DATA);
		int avatar_default_width = 100;
		int avatar_default_height= 100;
		try{
			//缩小至指定宽和高的图片并存放
			IOHelper.resizeToFile(cutImg, dest, avatar_default_width, avatar_default_height);
			
			//更新会员的头像
			Member member=(Member)ServletActionContext.getRequest().getSession().getAttribute("memlog");
			member.setAvatar(Constants.UPLOAD_AVATAR_PATH+"/"+dest.getName());
			MemberDao dao=new MemberDaoImpl();
			dao.updateSystemAvatar(member);
			
			dao.updateMemberIntegral(5, member.getId());
			//curr_member.setAvatar(dest.getName());
			//memberService.update(curr_member);
			src.delete(); //头像切割成功后，直接把源图片删除
			
			jsonMap.put("opt", "ok");
			jsonMap.put("fileName", dest.getName());
		}catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("opt", "error");
			jsonMap.put("msg", "头像裁切失败！");
		}
		renderJson(jsonMap);
		return null;
	}
	
	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 */
	public void renderJson(Object data) {
		HttpServletResponse response=getResponse();
		response.setContentType(JSON_CONTENT_TYPE);
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	/**
	 * 获取HttpServletResponse对象
	 * @return
	 */
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 获取ServletContext对象
	 * @return
	 */
	public ServletContext getServletContext(){
		return ServletActionContext.getServletContext();
	}
	public String getAvatarContentType() {
		return avatarContentType;
	}

	public void setAvatarContentType(String avatarContentType) {
		this.avatarContentType = avatarContentType;
	}

	/**
	 * @return the avatar
	 */
	public File getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the avatarFileName
	 */
	public String getAvatarFileName() {
		return avatarFileName;
	}

	/**
	 * @param avatarFileName the avatarFileName to set
	 */
	public void setAvatarFileName(String avatarFileName) {
		this.avatarFileName = avatarFileName;
	}

	/**
	 * @return the srcName
	 */
	public String getSrcName() {
		return srcName;
	}

	/**
	 * @param srcName the srcName to set
	 */
	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}

	/**
	 * @return the cutX
	 */
	public int getCutX() {
		return cutX;
	}

	/**
	 * @param cutX the cutX to set
	 */
	public void setCutX(int cutX) {
		this.cutX = cutX;
	}

	/**
	 * @return the cutY
	 */
	public int getCutY() {
		return cutY;
	}

	/**
	 * @param cutY the cutY to set
	 */
	public void setCutY(int cutY) {
		this.cutY = cutY;
	}

	/**
	 * @return the cutW
	 */
	public int getCutW() {
		return cutW;
	}

	/**
	 * @param cutW the cutW to set
	 */
	public void setCutW(int cutW) {
		this.cutW = cutW;
	}

	/**
	 * @return the cutH
	 */
	public int getCutH() {
		return cutH;
	}

	/**
	 * @param cutH the cutH to set
	 */
	public void setCutH(int cutH) {
		this.cutH = cutH;
	}
	
	
}
