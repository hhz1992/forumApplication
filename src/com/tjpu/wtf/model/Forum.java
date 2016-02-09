package com.tjpu.wtf.model;

import java.io.Serializable;

public class Forum implements Serializable{

	/**
	 * @author hhz.zm
	 * 板块
	 */
	private static final long serialVersionUID = -301467946528971610L;
	private int id;//OID、自增长
	private String name;//版块名
	private int sortNo;//排序编号
	private String keywords;//关键字（SEO）
	private String description;//描述
	private String rule;//版块规则
	private int topicCount;//总主题数
	private int articleCount;//总文章数
	private int topicTodayCount;//今日主题数
	private String lastTopic;//最后一个主题
	private int lastTopicId;
	
	private String categoryName;//版主列表
	private int categoryId;//所属分区
	private String lastMember;
	private String regTime;
	/**
	 * @return the lastTopicId
	 */
	public int getLastTopicId() {
		return lastTopicId;
	}
	/**
	 * @param lastTopicId the lastTopicId to set
	 */
	public void setLastTopicId(int lastTopicId) {
		this.lastTopicId = lastTopicId;
	}
	/**
	 * @return the lastMember
	 */
	public String getLastMember() {
		return lastMember;
	}
	/**
	 * @param lastMember the lastMember to set
	 */
	public void setLastMember(String lastMember) {
		this.lastMember = lastMember;
	}
	/**
	 * @return the regTime
	 */
	public String getRegTime() {
		return regTime;
	}
	/**
	 * @param regTime the regTime to set
	 */
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public Forum()
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the sortNo
	 */
	public int getSortNo() {
		return sortNo;
	}
	/**
	 * @param sortNo the sortNo to set
	 */
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
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
	 * @return the rule
	 */
	public String getRule() {
		return rule;
	}
	/**
	 * @param rule the rule to set
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
	/**
	 * @return the topicCount
	 */
	public int getTopicCount() {
		return topicCount;
	}
	/**
	 * @param topicCount the topicCount to set
	 */
	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}
	/**
	 * @return the articleCount
	 */
	public int getArticleCount() {
		return articleCount;
	}
	/**
	 * @param articleCount the articleCount to set
	 */
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	/**
	 * @return the topicTodayCount
	 */
	public int getTopicTodayCount() {
		return topicTodayCount;
	}
	/**
	 * @param topicTodayCount the topicTodayCount to set
	 */
	public void setTopicTodayCount(int topicTodayCount) {
		this.topicTodayCount = topicTodayCount;
	}
	/**
	 * @return the lastTopic
	 */
	
	
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @return the lastTopic
	 */
	public String getLastTopic() {
		return lastTopic;
	}
	/**
	 * @param lastTopic the lastTopic to set
	 */
	public void setLastTopic(String lastTopic) {
		this.lastTopic = lastTopic;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	

}
