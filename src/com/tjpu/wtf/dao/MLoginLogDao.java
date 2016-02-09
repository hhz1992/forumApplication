/**
 * 
 */
package com.tjpu.wtf.dao;

import com.tjpu.wtf.model.MLoginLog;

/**
 * @author ZM
 *
 */
public interface MLoginLogDao {
   //添加登录日志
	public boolean insertLog(MLoginLog log);
}
