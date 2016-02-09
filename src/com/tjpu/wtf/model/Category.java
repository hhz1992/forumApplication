/**
 * 
 */
package com.tjpu.wtf.model;

import java.io.Serializable;

/**
 * @author hhz.zm
 *分区
 */
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -861927659919261757L;
	private int id;//OID、自增长
	private String name;//分区名
	private int sortNo;//排序编号
	private int State;//分区状态(1.正常  2.不可用)
	
	public Category()
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
	 * @return the state
	 */
	public int getState() {
		return State;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		State = state;
	}
	

}
