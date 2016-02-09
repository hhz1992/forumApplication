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

import com.tjpu.wtf.dao.AttachmentDao;
import com.tjpu.wtf.dbutils.ConnectionFactory;
import com.tjpu.wtf.model.Attachment;
import com.tjpu.wtf.model.Category;
import com.tjpu.wtf.model.PageModel;

/**
 * @author hhz.zm
 *
 */
public class AttachmentDaoImpl implements  AttachmentDao{

	QueryRunner qr=new QueryRunner();
	public boolean addAttachment(Attachment att) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="insert into javabean_attachment_table(fileName,fileSize,path,description,uploadTime,topicId,memberId) values(?,?,?,?,?,?,?)";
		Object[]param={att.getFileName(),att.getFileSize(),att.getPath(),att.getDescription(),att.getUploadTime(),att.getTopicId(),att.getMemberId()};
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
	public List<Attachment> findAllByMemberId(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_attachment_table where memberId=?";
		Object[]param={id};
		List<Attachment> list=new ArrayList<Attachment>();
		try {
			list=qr.query(conn, sql, new BeanListHandler<Attachment>(Attachment.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}
	public PageModel<Attachment> findAllByMemberId2(int id, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_attachment_table where memberId=?";
		//查询数据
		String sql="select * from javabean_attachment_table where memberId=? limit ?,?";
		Object[]param1={id};
		Object[]param={id,(pageNo-1)*pageSize,pageSize};
		PageModel<Attachment> pm=new PageModel<Attachment>(pageNo,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler(),param1);
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Attachment>(Attachment.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}
	public boolean deleteByTopicId(int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="delete from javabean_attachment_table where topicId=?";
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
	public Attachment findByMaxId() {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_attachment_table where id=(select max(id) from javabean_attachment_table)";
		Attachment attachment=new Attachment();
		try {
			attachment=qr.query(conn, sql, new BeanHandler<Attachment>(Attachment.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return attachment;
	}
	public List<Attachment> findAllByTopicId(int topicId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_attachment_table where topicId=?";
		Object[]param={topicId};
		List<Attachment> attachList=new ArrayList<Attachment>();
		try {
			attachList=qr.query(conn, sql, new BeanListHandler<Attachment>(Attachment.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return attachList;
	}
	public List<Attachment> findAll() {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_attachment_table";
		
		List<Attachment> attachList=new ArrayList<Attachment>();
		try {
			attachList=qr.query(conn, sql, new BeanListHandler<Attachment>(Attachment.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return attachList;
	}

}
