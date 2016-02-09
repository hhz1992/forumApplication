/**
 * 
 */
package com.tjpu.wtf.model;

import java.io.Serializable;

/**
 * @author hhz.zm
 *附件
 */
public class Attachment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2175024008799626340L;

	private int id;//OID、自增长
	private String fileName;//文件名
	private int fileSize;//文件大小（字节）
	private String path;//文件存放路径
	private String description;//描述
	private String uploadTime;//上传时间
	private int downloadCount;//被下载次数
	private int topicId;//所属主题
	private int lowIntegral;//下载所需要的最低积分
	private int replyId;//所属的回复
	private int memberId;//发附件人的信息
	public Attachment()
	{}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileSize
	 */
	public int getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the uploadTime
	 */
	public String getUploadTime() {
		return uploadTime;
	}
	/**
	 * @param uploadTime the uploadTime to set
	 */
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	/**
	 * @return the downloadCount
	 */
	public int getDownloadCount() {
		return downloadCount;
	}
	/**
	 * @param downloadCount the downloadCount to set
	 */
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	/**
	 * @return the topicId
	 */
	public int getTopicId() {
		return topicId;
	}
	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	/**
	 * @return the lowIntegral
	 */
	public int getLowIntegral() {
		return lowIntegral;
	}
	/**
	 * @param lowIntegral the lowIntegral to set
	 */
	public void setLowIntegral(int lowIntegral) {
		this.lowIntegral = lowIntegral;
	}
	
	/**
	 * @return the replyId
	 */
	public int getReplyId() {
		return replyId;
	}
	/**
	 * @param replyId the replyId to set
	 */
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
}
