package com.tjpu.wtf.dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.tjpu.wtf.model.Member;
import com.tjpu.wtf.model.PageModel;

/**
 * @author hhz.zm
 *
 */
public interface MemberDao {

	boolean addMember(Member member);

	Member findUser(String loginName, String password);
	
	//更新登录者的最后登陆时间，ip,id
	public void updateLoginCount(String lastVisitTime,String ip,int id);
	//查询用户名是否存在
	public boolean findByName(String loginName);
	//通过id来查找用户
	public Member findById(int id);
	//更新用户信息
	public boolean updateMemberInfo(Member member);
	//更改用户密码
	public boolean updatePwd(Member member);
	//更改用户头像的路径
	public boolean updateSystemAvatar(Member member);
	//获取所有用户的信息
	public List<Member> findAll();
	//更新用户的状态
	public boolean updateMemberStatus(int status,int id);
	//根据id删除某一用户
	public boolean delete(int id);

	public boolean updateMemberIdentity(Member member);



	public List<Member> findForumByForumId(int id);

	public boolean updateMemberIntegral(int inc,int id);

	boolean updateMemberInfo1(Member memberInfo);

	public Member findByName2(String member);

	public void updateTopicCount(int id);

	public boolean updateMemberReplyCount(int id);

	public Member findByLoginName(String loginName);

	public boolean updateBest(int id);

	public int findAllMember();

	public void updateMemberReplyCount1(String loginName);

	public void updateMemberTopicCount(int memberId);

	List<Member> findAllByLoginName(String loginName);

	PageModel<Member> findAll2(int pageNo, int pageSize);

	PageModel<Member> findAllByLoginName2(String loginName, int sortNo,
			int pageSize);

	

	

}
