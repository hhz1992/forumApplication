/**
 *  ClassName: Constants.java
 *  created on 2010-9-28
 *  Copyrights 2010-2012 tjitcast.com All rights reserved.
 *  site: http://www.tjitcast.com
 *  email: qjyong@gmail.com
 */
package com.tjpu.wtf.util;

/**
 * 本系统使用到的常量类
 * 
 * @author qiujy
 */
public class Constants {
	/** 上传头像时源图片的最大宽度（px） */
	public static final int IMAGE_MAX_WIDTH = 500;
	/** 上传头像时源图片的最大高度为（px） */
	public static final int IMAGE_MAX_HEIGHT = 500;

	/** 全局使用的字典数据 */
	public static final String GLOBAL_DICT_DATA = "global_dict_data";
	
	/** 邮件的编码方式 */
	public static final String EMAIL_CHARSET = "email_charset";
	/** 邮件发送服务器地址 */
	public static final String EMAIL_SMTP_HOST = "email_smtp_host"; 
	/** 登录时邮件服务器是否需要安全验证 */
	public static final String EMAIL_AUTH = "email_auth"; 
	/** 网站邮箱账号 */
	public static final String EMAIL_ACCOUNT = "email_account"; 
	/** 网站邮箱登录名 */
	public static final String EMAIL_USER_USERNAME = "email_user_username"; 
	/** 网站邮箱密码 */
	public static final String EMAIL_USER_PASSWORD = "email_user_password"; 

	/** 网站名 */
	public static final String SITE_NAME = "site_name"; 
	/** 网站的关键字 */
	public static final String KEYWORDS = "keywords"; 
	/** 网站的描述信息 */
	public static final String DESCRIPTION = "description"; 
	/** 系统Cookie识别码 */
	public static final String COOKIE_KEY = "cookie_key";
	/** 版权信息 */
	public static final String COPYRIGHT = "copyright"; 
	/** 网站统计的代码 */
	public static final String TOTAL_CODE = "total_code";
	/** 备案号 */
	public static final String RECORD_CODE = "record_code"; 
	/** 公司名 */
	public static final String COMPANY = "company"; 
	
	/** 每页要显示的记录数的默认值 */
	public static final String DEFAULT_PAGE_SIZE = "default_page_size";
	/** 头像默认宽度（PX） */
	public static final String AVATAR_DEFAULT_WIDTH = "avatar_default_width"; 
	/** 头像默认高度（PX） */
	public static final String AVATAR_DEFAULT_HEIGHT = "avatar_default_height";
	
	/** 本web应用在Web服务器上存放的真实路径 */
	public static final String SERVER_BASE_PATH = System.getProperty("webapp.root");
	
	/** 系统的文件分隔符 */
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	/** 数据库数据备份文件的存放目录路径(相对于本Web应用基路径) */
	public static final String DATABASE_BACK_PATH = "/WEB-INF/sql";
	/** Lucen索引文件存放目录路径(相对于本Web应用基路径) */
	public static final String LUCENE_INDEX_PATH = "/WEB-INF/lucene_index";
	
	/** 备份用户上传的文件的存放目录路径(相对于本Web应用基路径) */
	public static final String UPLOAD_FILE_BACK_PATH = "/WEB-INF/filebak";
	/** 客户端上传的文件存放基目录路径(相对于本Web应用基路径) */
	public static final String UPLOAD_FILE_BASE_PATH = "/upload";
	/** 客户端上传的头像文件存放基目录路径(相对于本Web应用基路径) */
	public static final String UPLOAD_AVATAR_PATH = "/upload/avatar";
	/** 客户端上传的文件临时存放目录路径(相对于本Web应用基路径) */
	public static final String UPLOAD_FILE_TEMP_PATH = "/tmp";
	
	/** 会话中存放当前登录的管理员的key */
	public static final String SESSION_ATT_CURR_MANAGER = "curr_manager";
	/** 会话中存放当前登录的会员的key */
	public static final String SESSION_ATT_CURR_MEMBER = "curr_member";
}
