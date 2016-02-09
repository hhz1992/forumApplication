package com.tjpu.wtf.model;

import java.io.Serializable;

public class Member implements Serializable{

	/**
	 * @author hhz.zm
	 * 会员
	 */
	private static final long serialVersionUID = 6625040786709555994L;
	private int id;//OID、自增长
	private String loginName;//登录名，唯一
	private String password;//密码，不为空
	private String email;//邮箱号
	private String nickName;//昵称
	private int gender;//性别
	private String avatar;//头像路径
	private int integral;//积分 –》 等级
	private String signature;//签名
	private String bithplace;//籍贯
	private String introducation;//简介
	private String qq;//QQ号
	private String msn;//MSN号
	private String mobile;//手机号
	private String address;//联系地址
	private String postalCode;//邮政编码
	private String regTime;//注册时间
	private String lastVisitTime;//最后访问时间
	private String lastIp;//最后登录时的IP
	private int topicCount;//发表的总主题数
	private int replyCount;//发表的总回复数
	private int status;//状态（正常，锁定，屏蔽，禁言）
	private String autoLoginKey;//自动登录的认证字符串
	private int memberIdentity;//会员身份(1.会员  2 版主)
	private int forumId;//板块ID
	private String forumName;//板块名
	private int best;
	/**
	 * @return the best
	 */
	public int getBest() {
		return best;
	}
	/**
	 * @param best the best to set
	 */
	public void setBest(int best) {
		this.best = best;
	}
	/**
	 * @return the forumName
	 */
	public String getForumName() {
		return forumName;
	}
	/**
	 * @param forumName the forumName to set
	 */
	public void setForumName(String forumName) {
		this.forumName = forumName;
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
	public Member()
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
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginname the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * @return the integral
	 */
	public int getIntegral() {
		return integral;
	}
	/**
	 * @param integral the integral to set
	 */
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	/**
	 * @return the bithplace
	 */
	public String getBithplace() {
		return bithplace;
	}
	/**
	 * @param bithplace the bithplace to set
	 */
	public void setBithplace(String bithplace) {
		this.bithplace = bithplace;
	}
	/**
	 * @return the introducation
	 */
	public String getIntroducation() {
		return introducation;
	}
	/**
	 * @param introducation the introducation to set
	 */
	public void setIntroducation(String introducation) {
		this.introducation = introducation;
	}
	/**
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * @param qq the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * @return the msn
	 */
	public String getMsn() {
		return msn;
	}
	/**
	 * @param msn the msn to set
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalcode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	/**
	 * @return the lastVisitTime
	 */
	public String getLastVisitTime() {
		return lastVisitTime;
	}
	/**
	 * @param lastVisitTime the lastVisitTime to set
	 */
	public void setLastVisitTime(String lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}
	/**
	 * @return the lastIp
	 */
	public String getLastIp() {
		return lastIp;
	}
	/**
	 * @param lastIp the lastIp to set
	 */
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
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
	 * @return the autoLoginKey
	 */
	public String getAutoLoginKey() {
		return autoLoginKey;
	}
	/**
	 * @param autoLoginKey the autoLoginKey to set
	 */
	public void setAutoLoginKey(String autoLoginKey) {
		this.autoLoginKey = autoLoginKey;
	}
	/**
	 * @return the memberIdentity
	 */
	public int getMemberIdentity() {
		return memberIdentity;
	}
	/**
	 * @param memberIdentity the memberIdentity to set
	 */
	public void setMemberIdentity(int memberIdentity) {
		this.memberIdentity = memberIdentity;
	}
	

}
