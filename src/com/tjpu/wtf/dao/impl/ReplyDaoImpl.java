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

import com.tjpu.wtf.dao.ReplyDao;
import com.tjpu.wtf.dbutils.ConnectionFactory;
import com.tjpu.wtf.model.Category;
import com.tjpu.wtf.model.PageModel;
import com.tjpu.wtf.model.Reply;
import com.tjpu.wtf.model.Topic;

/**
 * @author hhz.zm
 *
 */
public class ReplyDaoImpl implements ReplyDao{

	QueryRunner qr=new QueryRunner();
	public boolean addReply(Reply reply) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="insert into javabean_reply_table(content,pubTime,ip,floor,topicId,member,status) values(?,?,?,?,?,?,?)";
		Object[] param={reply.getContent(),reply.getPubTime(),reply.getIp(),reply.getFloor(),reply.getTopicId(),reply.getMember(),1};
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
	public List<Reply> findAll() {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_reply_table";
		List<Reply> list=new ArrayList<Reply>();
		try {
			list=qr.query(conn, sql,new  BeanListHandler<Reply>(Reply.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	public List<Reply> findByTopicId(int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_reply_table where topicId=?";
		Object[] param={topicId};
		List<Reply> list=new ArrayList<Reply>();
		try {
			list=qr.query(conn, sql, new BeanListHandler<Reply>(Reply.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	public Reply findMaxFloor(int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_reply_table where floor=(select max(floor) from javabean_reply_table where topicId=?)";
		Object[]param={topicId};
		Reply reply=null;
		try {
			reply=qr.query(conn, sql, new BeanHandler<Reply>(Reply.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return reply;
	}
	public Reply findMaxReplyId(int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_reply_table where id=(select max(id) from javabean_reply_table where topicId=?)";
		Reply reply=new Reply();
		Object[]param={topicId};
		try {
			reply=qr.query(conn, sql, new BeanHandler<Reply>(Reply.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.getConnection();
		}
		return reply;
	}
	public Reply findByReplyId(int replyId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_reply_table where id=?";
		Object[]param={replyId};
		Reply reply=new Reply();
		try {
			reply=qr.query(conn, sql, new BeanHandler<Reply>(Reply.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return reply;
	}
	public boolean deleteByTopicId(int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="delete from javabean_reply_table where topicId=?";
		Object[]param={topicId};
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
	public boolean deleteByReplyId(int replyId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="delete from javabean_reply_table where id=?";
		Object[]param={replyId};
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
	public List<Reply> findByMemberLoginName(String loginName) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_reply_table where member=?";
		Object[]param={loginName};
		List<Reply> list=null;
		try {
			list=qr.query(conn, sql, new BeanListHandler<Reply>(Reply.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	public boolean setStatus2(int replyId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_reply_table set status=? where id=?";
		Object[]param={2,replyId};
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
	public boolean setStatus1(int replyId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_reply_table set status=? where id=?";
		Object[]param={1,replyId};
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
	public int findAllReplyCount() {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select count(*) from javabean_reply_table";
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
	public PageModel<Reply> findByMemberLoginName2(String loginName,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_reply_table where member=?";
		//查询数据
		String sql="select * from javabean_reply_table where member=? limit ?,? ";
		Object[]param2={loginName};
		Object[]param={loginName,(pageNo-1)*pageSize,pageSize};
		PageModel<Reply> pm=new PageModel<Reply>(pageNo,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler(),param2);
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Reply>(Reply.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}
	public PageModel<Reply> findByTopicId2(int topicId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_reply_table where topicId=?";
		//查询数据
		String sql="select * from javabean_reply_table where topicId=? limit ?,?";
		Object[]param1={topicId};
		Object[]param={topicId,(pageNo-1)*pageSize,pageSize};
		PageModel<Reply> pm=new PageModel<Reply>(pageNo,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler(),param1);
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Reply>(Reply.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}

}
