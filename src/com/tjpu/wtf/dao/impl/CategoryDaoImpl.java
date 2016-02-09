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


import com.tjpu.wtf.dao.CategoryDao;
import com.tjpu.wtf.dbutils.ConnectionFactory;
import com.tjpu.wtf.model.Category;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;

/**
 * @author hhz.zm
 *
 */
public class CategoryDaoImpl implements CategoryDao{

	private QueryRunner qr=new QueryRunner();
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="insert into javabean_category_table(name,sortNo,state) values(?,?,?)";
		Object[] param={category.getName(),category.getSortNo(),category.getState()};
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

	
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_category_table";
		List<Category> list=new ArrayList<Category>();
		try {
			list=qr.query(conn, sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}


	public boolean delete(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="delete from javabean_category_table where id=?";
		Object[] param={id};
		int i=0;
		try {
			i=qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i!=0){
			return true;
		}else{
			return false;
		}
	}


	public Category findById(int id) {
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_category_table where id=?";
		Object[] param={id};
		Category category=null;
		try {
			category=qr.query(conn, sql,new BeanHandler<Category>(Category.class) ,param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return category;
	}


	public boolean update(Category category) {
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_category_table set name=?,sortNo=?,state=? where id=?";
		Object[] param={category.getName(),category.getSortNo(),category.getState(),category.getId()};
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


	public boolean findByName(String name) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_category_table where name=?";
		Object[]param={name};
		Category category=null;
		try {
			category=qr.query(conn, sql, new BeanHandler<Category>(Category.class), param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		if(category!=null)
		{
			return true;
		}else{
			return false;
		}
	}


	public List<Category> findAllByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_category_table where name=?";
		List<Category> list=new ArrayList<Category>();
		Object[]param={categoryName};
		try {
			list=qr.query(conn, sql, new BeanListHandler<Category>(Category.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}


	public PageModel<Category> findAll2(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_category_table";
		//查询数据
		String sql="select * from javabean_category_table limit ?,?";
		Object[]param={(pageNo-1)*pageSize,pageSize};
		PageModel<Category> pm=new PageModel<Category>(pageNo,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler());
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Category>(Category.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}


	public PageModel<Category> findAllByCategoryName2(String categoryName,
			int parseInt, int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_category_table where name=?";
		//查询数据
		String sql="select * from javabean_category_table where name=? limit ?,?";
		Object[]param1={categoryName};
		Object[]param={categoryName,(parseInt-1)*pageSize,pageSize};
		PageModel<Category> pm=new PageModel<Category>(parseInt,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler(),param1);
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Category>(Category.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}
}
