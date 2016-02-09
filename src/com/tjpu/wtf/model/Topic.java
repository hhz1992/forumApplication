package com.tjpu.wtf.model;

import java.io.Serializable;

public class Topic implements Serializable{

	/**
	 *  @author hhz.zm
	 * 主题
	 */
	private static final long serialVersionUID = -2671301817166656335L;
	private long id;//OID、自增长
	private String title;//标题
	private String content;//内容
	private String pubTime;//发表时间
	private int attachId;//主题所附带的附件列表
	private int forumId;//所属版块
	private String ip;//发表者所用的IP
	private int status;//状态（普通，移除，锁定，精华）
	private int type;//类型（置顶，隐藏，公告）
	private int visitCount;//人气
	private int replyCount;//回复数
	private String lastReplyTime;//最后回复时间
	private int memberId;//所属的会员
	private int replyid;//主题下的回复列表
	private String lastReplyMember;//最后回复该主题的人
	/**
	 * @return the lastReplyMember
	 */
	public String getLastReplyMember() {
		return lastReplyMember;
	}
	/**
	 * @param lastReplyMember the lastReplyMember to set
	 */
	public void setLastReplyMember(String lastReplyMember) {
		this.lastReplyMember = lastReplyMember;
	}
	public Topic()
	{}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the visitCount
	 */
	public int getVisitCount() {
		return visitCount;
	}
	/**
	 * @param visitCount the visitCount to set
	 */
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	/**
	 * @return the replyCount
	 */
	public int getReplyCount() {
		return replyCount;
	}
	/**
	 * @param replyCount the replyCount to set
	 */
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	/**
	 * @return the lastReplyTime
	 */
	public String getLastReplyTime() {
		return lastReplyTime;
	}
	/**
	 * @param lastReplyTime the lastReplyTime to set
	 */
	public void setLastReplyTime(String lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}
	
	/**
	 * @return the forumId
	 */
	public int getForumId() {
		return forumId;
	}
	/**
	 * @param forumId the forumId to set
	 */
	public void setForumId(int forumId) {
		this.forumId = forumId;
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
	 * @return the replyid
	 */
	public int getReplyid() {
		return replyid;
	}
	/**
	 * @param replyid the replyid to set
	 */
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}
	

}
