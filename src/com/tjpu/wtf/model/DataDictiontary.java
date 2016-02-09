/**
 * 
 */
package com.tjpu.wtf.model;

import java.io.Serializable;

/**
 * @author hhz.zm
 *系统数据字典信息
 */
public class DataDictiontary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8809924614087493470L;

	private int id;//OID、自增长
	private String key;//信息项
	private String value;//值
	private String description;//简要描述
	public DataDictiontary()
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
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
	
	
}
