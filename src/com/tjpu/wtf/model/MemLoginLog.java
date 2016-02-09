/**
 * 
 */
package com.tjpu.wtf.model;

import java.io.Serializable;

/**
 * @author ZM
 *
 */
public class MemLoginLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5416628994145532502L;
	private int id;
	private String loginName;
	private String nickName;
	private String loginIp;
	private String ipAddress;
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
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	 * @return the loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}
	/**
	 * @param loginIp the loginIp to set
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	
}
