/**
 *  ClassName: PageModel.java
 *  created on 2011-6-28 上午09:55:10
 *  Copyrights 2011-2012 tjitcast.com All rights reserved.
 *  site: http://www.tjitcast.com
 *  email: qjyong@gmail.com
 */

package com.tjpu.wtf.model;
import java.util.List;

/**
 * 存放分页数据的模型
 * @author csdn
 */
public class PageModel<T> {
	/** 当前页号 */
	private int pageNo = 1;
	/** 每页显示的记录条数 */
	private int pageSize = 10;
	/** 总记录数 */
	private int recordCount;
	/** 总页数 */
	private int pageCount;
	/** 存放分页数据的集合 */
	private List<T> datas;
	
	public PageModel() {
	}
	
	public PageModel(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		if(this.getRecordCount() <= 0){
			return 0;
		}else{
			pageCount = (recordCount + pageSize - 1) / pageSize;
			return pageCount;
		}
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
}