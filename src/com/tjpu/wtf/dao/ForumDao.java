/**
 * 
 */
package com.tjpu.wtf.dao;

import java.util.List;

import com.tjpu.wtf.model.Forum;
import com.tjpu.wtf.model.PageModel;

/**
 * @author hhz.zm
 *
 */
public interface ForumDao {

	public boolean addForum(Forum forum);

	public List<Forum> findAll();

	public Forum findById(int id);

	public boolean update(Forum forum);

	public boolean findByName(String name);

	public List<Forum> findAllById(int id);

	

	public boolean updateForumInfo(String lastMember, String regTime,
			String title, int forumId);

	public void deleteByForumId(int forumId);

	public void updateForumCount(int forumId);

	public List<Forum> findAllByName(String forumName);

	public PageModel<Forum> findAll2(int pageNo, int pageSize);

	public PageModel<Forum> findAllByName2(String forumName, int pageNo,
			int pageSize);
	
	

}
