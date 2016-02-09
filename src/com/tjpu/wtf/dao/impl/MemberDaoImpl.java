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

import com.tjpu.wtf.dao.MemberDao;
import com.tjpu.wtf.dbutils.ConnectionFactory;
import com.tjpu.wtf.model.Manager;
import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;

/**
 * @author hhz.zm
 *
 */
public class MemberDaoImpl implements MemberDao{
	private QueryRunner qr=new QueryRunner();
	
	//通过所给的loginName和password调用此方法，检测是否是会员，是否允许登录成功
	public Member findUser(String loginName, String password) {
		// TODO Auto-generated method stub
		//连接数据库的方法
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_member_table where loginName=? and password=?";
		//创建一个Manager对象
		Member member=new Member();
		//把参数放进此数组中
		Object[]params={loginName,password};
		try {
			//调用此方法完成数据库的链接
			member=qr.query(conn, sql, new BeanHandler<Member>(Member.class), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return member;
	}
	
	//完成登录者的登录次数的更新
	public void updateLoginCount(String lastVisitTime,String ip,int id) {
		// TODO Auto-generated method stub
		//链接数据库
		Connection conn=ConnectionFactory.getConnection();
		//登录次数加1
		String sql="update javabean_member_table set lastVisitTime=?,lastIp=? where id=?";
		//把数据放入到数组中
		Object[]param={lastVisitTime,ip,id};
		
		try {
			qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		
		
	}
	
	
	public boolean addMember(Member member) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="insert into javabean_member_table(loginName,password,email,regTime,status,memberIdentity,integral,topicCount,replyCount,best) values(?,?,?,?,?,?,?,?,?,?)";
		Object[]param={member.getLoginName(),member.getPassword(),member.getEmail(),member.getRegTime(),member.getStatus(),member.getMemberIdentity(),member.getIntegral(),member.getTopicCount(),member.getReplyCount(),0};
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

	public boolean findByName(String loginName) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_member_table where loginname=?";
		Object[]param={loginName};
		Member member=null;
		try {
			member=qr.query(conn, sql, new BeanHandler<Member>(Member.class), param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		if(member==null)
		{
			return true;
		}else{
			return false;
		}
		
	}

	public Member findById(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_member_table where id=?";
		Object[] param={id};
		Member member=null;
		try {
			member=qr.query(conn, sql,new BeanHandler<Member>(Member.class) ,param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return member;
	}

	public boolean updateMemberInfo(Member member) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set nickName=?,gender=?,signature=?,bithplace=?,introducation=?,qq=?,msn=?,mobile=?,address=?,postalCode=?,email=?,integral=integral+20 where id=?";
		Object[] param={member.getNickName(),member.getGender(),member.getSignature(),member.getBithplace(),member.getIntroducation(),member.getQq(),member.getMsn(),member.getMobile(),member.getAddress(),member.getPostalCode(),member.getEmail(),member.getId()};
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

	public boolean updatePwd(Member member) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set password=? where id=?";
		Object[] param={member.getPassword(),member.getId()};
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

	public boolean updateSystemAvatar(Member member) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set avatar=? where id=?";
		Object[] param={member.getAvatar(),member.getId()};
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

	public List<Member> findAll() {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_member_table";
		List<Member> list=new ArrayList<Member>();
		try {
			list=qr.query(conn, sql, new BeanListHandler<Member>(Member.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}

	public boolean updateMemberStatus(int status, int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set status=? where id=?";
		Object[]param={status,id};
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

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="delete from javabean_member_table where id=?";
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

	public boolean updateMemberIdentity(Member member) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set forumId=?,forumName=?,memberIdentity=? where id=?";
		Object[] param={member.getForumId(),member.getForumName(),member.getMemberIdentity(),member.getId()};
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

	public List<Member> findForumByForumId(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_member_table where forumId=? and memberIdentity=?";
		List<Member> list=new ArrayList<Member>();
		Object[] param={id,2};
		try {
			list=qr.query(conn, sql, new BeanListHandler<Member>(Member.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}

	public boolean updateMemberIntegral(int inc,int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set integral=integral+? where Id=?";
		Object[]param={inc,id};
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

	public boolean updateMemberInfo1(Member member) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set gender=?,signature=?,bithplace=?,introducation=?,qq=?,msn=?,mobile=?,address=?,postalCode=?,email=? where id=?";
		Object[] param={member.getGender(),member.getSignature(),member.getBithplace(),member.getIntroducation(),member.getQq(),member.getMsn(),member.getMobile(),member.getAddress(),member.getPostalCode(),member.getEmail(),member.getId()};
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

	public Member findByName2(String memberNickName) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_member_table where loginName=?";
		Object[]param={memberNickName};
		Member member=new Member();
		try {
			member=qr.query(conn, sql, new BeanHandler<Member>(Member.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return member;
	}

	public void updateTopicCount(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set topicCount=topicCount+1 where id=?";
		Object[]param={id};
		
		try {
			qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
	}

	public boolean updateMemberReplyCount(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set replyCount=replyCount+1 where id=?";
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
		if(i!=0){
			return true;
		}else{
			return false;
		}
		
	}

	public Member findByLoginName(String loginName) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_member_table where loginName=?";
		Object[]param={loginName};
		Member member=new Member();
		try {
			member=qr.query(conn, sql, new BeanHandler<Member>(Member.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return member;
	}

	public boolean updateBest(int id) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set best=best+1 where id=?";
		Object[]param={id};
		int i=1;
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

	public int findAllMember() {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select count(*) from javabean_member_table";
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

	public void updateMemberReplyCount1(String loginName) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set replyCount=replyCount-1 where loginName=?";
		Object[]param={loginName};
		try {
			qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
	}

	public void updateMemberTopicCount(int memberId) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="update javabean_member_table set topicCount=topicCount-1 where id=?";
		Object[]param={memberId};
		try {
			qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
	}

	public List<Member> findAllByLoginName(String loginName) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from javabean_member_table where loginName=?";
		List<Member> list=new ArrayList<Member>();
		Object[]param={loginName};
		try {
			list=qr.query(conn, sql, new BeanListHandler<Member>(Member.class),param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return list;
	}

	public PageModel<Member> findAll2(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_member_table";
		//查询数据
		String sql="select * from javabean_member_table limit ?,?";
		Object[]param={(pageNo-1)*pageSize,pageSize};
		PageModel<Member> pm=new PageModel<Member>(pageNo,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler());
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Member>(Member.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}

	public PageModel<Member> findAllByLoginName2(String loginName, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		Connection conn=ConnectionFactory.getConnection();
		//先查询出总共的记录数
		String sql1="select count(*) from javabean_member_table where loginName=?";
		//查询数据
		String sql="select * from javabean_member_table where loginName=? limit ?,?";
		Object[]param2={loginName};
		Object[]param={loginName,(pageNo-1)*pageSize,pageSize};
		PageModel<Member> pm=new PageModel<Member>(pageNo,pageSize);
		int count=0;
		try {
			Object obj=qr.query(conn, sql1,new ScalarHandler(),param2);
			Long c=(Long)obj;
			count=c.intValue();
			pm.setRecordCount(count);
			pm.setDatas(qr.query(conn, sql, new BeanListHandler<Member>(Member.class),param));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn);
		}
		return pm;
	}


	

	
	


	
}
