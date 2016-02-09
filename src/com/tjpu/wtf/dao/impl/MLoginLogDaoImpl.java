/**
 * 
 */
package com.tjpu.wtf.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.tjpu.wtf.dao.MLoginLogDao;
import com.tjpu.wtf.dbutils.ConnectionFactory;
import com.tjpu.wtf.model.MLoginLog;

/**
 * @author ZM
 *
 */
public class MLoginLogDaoImpl implements  MLoginLogDao{
	
	
	private QueryRunner qr=new QueryRunner();

	public boolean insertLog(MLoginLog log) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="insert into javabean_mloginlog_table(loginname,realName,loginIp,ipAddress) values(?,?,?,?)";
		Object[]params={log.getLoginName(),log.getRealName(),log.getLoginIp(),log.getIpAddress()};
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
