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

import com.tjpu.wtf.dao.ForumDao;
import com.tjpu.wtf.dbutils.ConnectionFactory;
import com.tjpu.wtf.model.Forum;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;

/**
 * @author hhz.zm
 *
 */
public class ForumDaoImpl implements ForumDao{

	private QueryRunner qr=new QueryRunner();
	public boolean addForum(Forum forum) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="insert into javabean_forum_table(name,sortNo,keywords,description,rule,categoryName,categoryId,topicCount,articleCount) values(?,?,?,?,?,?,?,?,?)";
		Object[] param={forum.getName(),forum.getSortNo(),forum.getKeywords(),forum.getDescription(),forum.getRule(),forum.getCategoryName(),forum.getCategoryId(),0,0};
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
	public List<Forum> findAll() {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_forum_table";
		List<Forum> list=new ArrayList<Forum>();
		try {
			list=qr.query(conn, sql, new BeanListHandler<Forum>(Forum.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	public Forum findById(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_forum_table where id=?";
		Object[] param={id};
		Forum forum=null;
		try {
			forum=qr.query(conn, sql, new BeanHandler<Forum>(Forum.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return forum;
	}
	public boolean update(Forum forum) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_forum_table set name=?,sortNo=?,keywords=?,description=?,rule=?,categoryName=?,categoryId=? where id=?";
		Object[] param={forum.getName(),forum.getSortNo(),forum.getKeywords(),forum.getDescription(),forum.getRule(),forum.getCategoryName(),forum.getCategoryId(),forum.getId()};
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
		String sql="select * from javabean_forum_table where name=?";
		Object[] param={name};
		Forum forum=null;
		try {
			forum=qr.query(conn, sql,new BeanHandler<Forum>(Forum.class) ,param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		if(forum!=null)
		{
			return true;
		}else{
			return false;
		}
		
	}
	public List<Forum> findAllById(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_forum_table where categoryId=?";
		Object[] param={id};
		List<Forum> list=new ArrayList<Forum>();
		try {
			list=qr.query(conn, sql, new BeanListHandler<Forum>(Forum.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	public boolean updateForumInfo(String lastMember, String regTime,
			String title, int forumId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_forum_table set topictodaycount=topictodaycount+1,topiccount=topiccount+1,articlecount=articlecount+1,lasttopic=?,lastMember=?,regTime=? where id=?";
		Object[] param={title,lastMember,regTime,forumId};
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
	public void deleteByForumId(int forumId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="delete from javabean_forum_table where id=?";
		Object[]param={forumId};
		try {
			qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
	}
	public void updateForumCount(int forumId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_forum_table set topicCount=topicCount-1,articleCount=articleCount-1 where id=?";
		Object[]param={forumId};
		try {
			qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
	}
	public List<Forum> findAllByName(String forumName) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_forum_table where name=?";
		Object[] param={forumName};
		List<Forum> list=new ArrayList<Forum>();
		try {
			list=qr.query(conn, sql, new BeanListHandler<Forum>(Forum.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	public PageModel<Forum> findAll2(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_forum_table";
		//查询数据
		String sql="select * from javabean_forum_table limit ?,?";
		Object[]param={(pageNo-1)*pageSize,pageSize};
		PageModel<Forum> pm=new PageModel<Forum>(pageNo,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler());
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Forum>(Forum.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}
	public PageModel<Forum> findAllByName2(String forumName, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_forum_table where name=?";
		//查询数据
		String sql="select * from javabean_forum_table where name=? limit ?,?";
		Object[]param1={forumName};
		Object[]param={forumName,(pageNo-1)*pageSize,pageSize};
		PageModel<Forum> pm=new PageModel<Forum>(pageNo,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler(),param1);
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Forum>(Forum.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}

}
