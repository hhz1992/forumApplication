/**
 * 
 */
package com.tjpu.wtf.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.tjpu.wtf.dao.MemLoginLogDao;
import com.tjpu.wtf.dbutils.ConnectionFactory;
import com.tjpu.wtf.model.MemLoginLog;

/**
 * @author ZM
 *
 */
public class MemLoginLogDaoImpl implements MemLoginLogDao{

	private QueryRunner qr=new QueryRunner();
	public boolean insertLog(MemLoginLog log) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="insert into javabean_memloginlog_table(loginName,nickName,loginIp,ipAddress) values(?,?,?,?)";
		Object[]params={log.getLoginName(),log.getNickName(),log.getLoginIp(),log.getIpAddress()};
		int i=0;
		try {
			i=qr.update(conn, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		if(i!=0)
		{
			return true;
		}else{
			return false;
		}
	}
	

}
