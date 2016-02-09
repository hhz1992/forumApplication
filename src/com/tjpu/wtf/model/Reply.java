package com.tjpu.wtf.model;

import java.io.Serializable;


public class Reply implements Serializable{

	/**
	 *@author hhz.zm
	 * 回复
	 */
	private static final long serialVersionUID = -1703586323502716802L;
	private int id;//OID、自增长
	private String content;//回复内容
	private String pubTime;//回复时间
	private String ip;//回复时所用的IP
	private int floor;//所在楼层
	private int topicId;//所属主题
	private String member;//作者 
	private int attachId;//回复所附带的附件列表
	private int status;//回复状态，1时代码正常，2 代表屏蔽
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	public Reply()
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the pubTime
	 */
	public String getPubTime() {
		return pubTime;
	}
	/**
	 * @param pubTime the pubTime to set
	 */
	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
	}
	/**
	 * @param floor the floor to set
	 */
	public void setFloor(int floor) {
		this.floor = floor;
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
	 * @return the member
	 */
	public String getMember() {
		return member;
	}
	/**
	 * @param member the member to set
	 */
	public void setMember(String member) {
		this.member = member;
	}
	/**
	 * @return the attachId
	 */
	public int getAttachId() {
		return attachId;
	}
	/**
	 * @param attachId the attachId to set
	 */
	public void setAttachId(int attachId) {
		this.attachId = attachId;
	}
	
	

}
