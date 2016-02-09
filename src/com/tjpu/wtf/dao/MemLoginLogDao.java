/**
 * 
 */
package com.tjpu.wtf.dao;


import com.tjpu.wtf.model.MemLoginLog;

/**
 * @author ZM
 *
 */
public interface MemLoginLogDao {
	//添加登录日志
	public boolean insertLog(MemLoginLog log);
}
