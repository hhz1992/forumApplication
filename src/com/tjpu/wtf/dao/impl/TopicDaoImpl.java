/**
 * 
 */
package com.tjpu.wtf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.tjpu.wtf.dao.TopicDao;
import com.tjpu.wtf.dbutils.ConnectionFactory;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;
import com.tjpu.wtf.model.Topic;

/**
 * @author hhz.zm
 *
 */
public class TopicDaoImpl implements TopicDao{

	private QueryRunner qr=new QueryRunner();
	public List<Topic> findAllByMemberId(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_topic_table where memberId=?";
		Object[] param={id};
		List<Topic> list=null;
		try {
			list=qr.query(conn, sql, new BeanListHandler<Topic>(Topic.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	public boolean addTopic(Topic topic) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="insert into javabean_topic_table(title,content,pubTime,forumId,memberId,ip,visitCount,replyCount,status,type) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] param={topic.getTitle(),topic.getContent(),topic.getPubTime(),topic.getForumId(),topic.getMemberId(),topic.getIp(),1,0,1,0};
		int i=0;
		try {
			i=qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		if(i!=0){
			return true;
		}else{
			return false;
		}
	}
	public int findTopicCountToday(int id,String date) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select count(*) from javabean_topic_table where forumId=? and pubTime like '"+date+"%'";
		Object[] param={id};
		int count=0;
	
		try {
			Object obj=qr.query(conn, sql, new ScalarHandler(), param);
			Long l=(Long)obj;
			count=l.intValue();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		
		return count;
	}
	
	public int findAllTopicCountToday(String date) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select count(*) from javabean_topic_table where pubTime like '"+date+"%'";
		
		int count=0;
	
		try {
			Object obj=qr.query(conn, sql, new ScalarHandler());
			Long l=(Long)obj;
			count=l.intValue();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		
		return count;
	}
	
	public Topic findById(int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_topic_table where id=?";
		Object[] param={topicId};
		Topic topic=new Topic();
		try {
			topic=qr.query(conn, sql,new BeanHandler<Topic>(Topic.class) ,param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return topic;
	}
	public List<Topic> findAllByForumId(int forumId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_topic_table where forumId=?";
		Object[] param={forumId};
		List<Topic> list=null;
		try {
			list=qr.query(conn, sql, new BeanListHandler<Topic>(Topic.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	
	public int memberTopicCount(int memberId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select count(*) from javabean_topic_table where memberId=?";
		Object[] param={memberId};
		int count=0;
		try {
			Object obj=qr.query(conn, sql, new ScalarHandler(),param);
			Long l=(Long)obj;
			count=l.intValue();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public int memberTopicBestCount(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select count(*) from javabean_topic_table where memberId=? and status=?";
		Object[] param={id,4};
		int count=0;
		try {
			Object obj=qr.query(conn, sql, new ScalarHandler(),param);
			Long l=(Long)obj;
			count=l.intValue();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Topic> findAll() {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_topic_table";
		List<Topic> list=new ArrayList<Topic>();
		try {
			list=qr.query(conn, sql, new BeanListHandler<Topic>(Topic.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	public boolean deleteById(int parseInt) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="delete from javabean_topic_table where id=?";
		Object[] param={parseInt};
		int i=0;
		try {
			i=qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		if(i!=0){
			return true;
		}else{
			return false;
		}
		
	}
	public boolean update(Topic topic) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_topic_table set status=?,type=? where id=?";
		Object[] param={topic.getStatus(),topic.getType(),topic.getId()};
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
	public void updateTopicVisitCount(long id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_topic_table set visitCount=visitCount+1 where id=?";
		Object[]param={id};
		int i=0;
		try {
			i=qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		
	}
	public List<Topic> findByForumIdAll(int forumId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_topic_table where forumId=?";
		Object[]param={forumId};
		List<Topic> list=new ArrayList<Topic>();
		try {
			list=qr.query(conn, sql, new BeanListHandler<Topic>(Topic.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	public boolean updateTopicReplyCountandLastReplyTime(String time,int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_topic_table set replyCount=replyCount+1,LastReplyTime=? where id=?";
		Object[]param={time,id};
		int i=0;
		try {
			i=qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		if(i!=0){
			return true;
		}else{
			return false;
		}
	
	}
	public boolean updateReplyId(int id, int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_topic_table set replyid=? where id=?";
		Object[]param={id,topicId};
		int i=0;
		try {
			i=qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		if(i!=0){
			return true;
		}else{
			return false;
		}
	}
	public void updatelastReplyMember(String loginName, int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_topic_table set lastReplyMember=? where id=?";
		Object[]param={loginName,topicId};
		
		try {
			qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		
	}
	public void updateReplyCountByTopicId(int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_topic_table set replyCount=replyCount-1 where id=?";
		Object[]param={topicId};
		
		try {
			qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
	}
	public int findAllTopicCount() {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select count(*) from javabean_topic_table";
		
		int count=0;
	
		try {
			Object obj=qr.query(conn, sql, new ScalarHandler());
			Long l=(Long)obj;
			count=l.intValue();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		
		return count;
	}
	public PageModel<Topic> findAll2(int pageNO, int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_topic_table";
		//查询数据
		String sql="select * from javabean_topic_table limit ?,?";
		Object[]param={(pageNO-1)*pageSize,pageSize};
		PageModel<Topic> pm=new PageModel<Topic>(pageNO,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler());
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Topic>(Topic.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}
	public PageModel<Topic> findByForumIdAll2(int forumId, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_topic_table where forumId=?";
		//查询数据
		String sql="select * from javabean_topic_table where forumId=? limit ?,? ";
		Object[]param2={forumId};
		Object[]param={forumId,(pageNo-1)*pageSize,pageSize};
		PageModel<Topic> pm=new PageModel<Topic>(pageNo,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler(),param2);
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Topic>(Topic.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}
	public PageModel<Topic> findAllByMemberId2(int memberId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_topic_table where memberId=?";
		//查询数据
		String sql="select * from javabean_topic_table where memberId=? limit ?,? ";
		Object[]param2={memberId};
		Object[]param={memberId,(pageNo-1)*pageSize,pageSize};
		PageModel<Topic> pm=new PageModel<Topic>(pageNo,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler(),param2);
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Topic>(Topic.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}
	public void updateAttachIdByTopicId(int attachmentId, int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_topic_table set attachId=? where id=?";
		Object[]param={attachmentId,topicId};
		try {
			qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
	}
	
	
	

}
