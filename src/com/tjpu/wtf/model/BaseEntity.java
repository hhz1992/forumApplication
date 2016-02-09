/**
 *BaseEntity.java
 *
 * 2012-1-26下午02:36:29
 */
package com.tjpu.wtf.model;

import java.io.Serializable;

/**
 * @author pkzhang
 *
 */
public class BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4263896347946136089L;
	private int PageSize;
	private int PageNo;
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
	public int getPageNo() {
		return PageNo;
	}
	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}

}
