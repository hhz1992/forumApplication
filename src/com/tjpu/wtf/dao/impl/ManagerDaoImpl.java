/**
 * 
 */
package com.tjpu.wtf.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.tjpu.wtf.dao.ManagerDao;
import com.tjpu.wtf.dbutils.ConnectionFactory;
import com.tjpu.wtf.model.Manager;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;

/**
 * @author ZM
 *
 */
public class ManagerDaoImpl implements ManagerDao{
	//创建一个全局的QueryRunner对象  QueryRunner类提供了执行CRUDSQL语句的方法
	private QueryRunner qr=new QueryRunner();
	
    //通过所给的loginName和password调用此方法，检测是否是会员，是否允许登录成功
	public Manager findUser(String loginName, String password) {
		// TODO Auto-generated method stub
		//连接数据库的方法
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_manager_table where loginName=? and password=?";
		//创建一个Manager对象
		Manager manager=new Manager();
		//把参数放进此数组中
		Object[]params={loginName,password};
		try {
			//调用此方法完成数据库的链接
			manager=qr.query(conn, sql, new BeanHandler<Manager>(Manager.class), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return manager;
	}
    
	
	//完成登录者的登录次数的更新
	public void updateLoginCount(String lastLoginTime,String ip,int id) {
		// TODO Auto-generated method stub
		//链接数据库
		Connection conn=ConnectionFactory.getConnection();
		//登录次数加1
		String sql="update javabean_manager_table set loginNum=loginNum+1,lastLoginTime=?,ip=? where id=?";
		//把数据放入到数组中
		Object[]param={lastLoginTime,ip,id};
		
		try {
			qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		
		
	}

	//添加管理员
	public boolean addManager(Manager manager) {
		// TODO Auto-generated method stub
		//链接数据库
		Connection conn=ConnectionFactory.getConnection();
		String sql="insert into javabean_manager_table(loginName,password,realName,mobile,state,loginNum,createTime,lastLoginTime,ip) values(?,?,?,?,?,?,?,?,?)";
		Object[]param={manager.getLoginName(),manager.getPassword(),manager.getRealName(),manager.getMobile(),manager.getState(),manager.getLoginNum(),manager.getCreateTime(),manager.getLastLoginTime(),manager.getIp()};
		int i=0;
		try {
			i=qr.update(conn, sql, param);
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

	//查看所有的管理员信息，放入到一个List列表中，此列表中是Manager类型
	public List<Manager> findAll() {
		// TODO Auto-generated method stub
		//链接数据库
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_manager_table";
		//List<Manager>是一个接口，调用其具体的实现方法产生新的对象
		List<Manager> list=new ArrayList<Manager>();
		try {
			//数据库数据处理，返回一个list对象
			list=qr.query(conn, sql, new BeanListHandler<Manager>(Manager.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}

	//通过所传给的id删除一个管理员
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		//数据库连接
		Connection conn=ConnectionFactory.getConnection();
		String sql="delete from javabean_manager_table where id=?";
		//把数据放入到数组中
		Object[]param={id};
		int i=0;
		try {
			//进行数据处理
			i=qr.update(conn, sql, param);
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

	//通过所给的id发现此id所对应的管理员信息，并用Manager返回
	public Manager findById(int id) {
		// TODO Auto-generated method stub
		//数据库连接
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_manager_table where id=?";
		Object[]param={id};
		Manager manager=null;
		try {
			//数据处理，并且返回一条manager信息
			manager=qr.query(conn, sql, new BeanHandler<Manager>(Manager.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		
		return manager;
	}

	//更新数据库中的内容，如果成功，则返回true
	public boolean update(Manager manager) {
		// TODO Auto-generated method stub
		//数据库连接
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_manager_table set loginName=?,password=?,realName=?,mobile=?,state=?,createTime=? where id=?";
		Object[]param={manager.getLoginName(),manager.getPassword(),manager.getRealName(),manager.getMobile(),manager.getState(),manager.getCreateTime(),manager.getId()};
		int i=0;
		try {
			//数据处理
			i=qr.update(conn, sql, param);
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


	public List<Manager> findAllByLoginName(String loginName) {
		// TODO Auto-generated method stub
		//链接数据库
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_manager_table where loginName=?";
		//List<Manager>是一个接口，调用其具体的实现方法产生新的对象
		Object[]param={loginName};
		List<Manager> list=new ArrayList<Manager>();
		try {
			//数据库数据处理，返回一个list对象
			list=qr.query(conn, sql, new BeanListHandler<Manager>(Manager.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}


	public PageModel<Manager> findAll2(int pageNo, int pageSize) {
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_manager_table";
		//查询数据
		String sql="select * from javabean_manager_table limit ?,?";
		Object[]param={(pageNo-1)*pageSize,pageSize};
		PageModel<Manager> pm=new PageModel<Manager>(pageNo,pageSize);
		int count=0;
		
		try {
			//数据库数据处理，返回一个list对象
			Object obj=qr.query(conn, sql1,new ScalarHandler());
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Manager>(Manager.class),param));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}


	public PageModel<Manager> findAllByLoginName2(String loginName, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_manager_table where loginName=?";
		//查询数据
		String sql="select * from javabean_manager_table where loginName=? limit ?,?";
		Object[]param1={loginName};
		Object[]param={loginName,(pageNo-1)*pageSize,pageSize};
		PageModel<Manager> pm=new PageModel<Manager>(pageNo,pageSize);
		int count=0;
		
		try {
			//数据库数据处理，返回一个list对象
			Object obj=qr.query(conn, sql1,new ScalarHandler(),param1);
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Manager>(Manager.class),param));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}

}
