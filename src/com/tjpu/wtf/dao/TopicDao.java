/**
 * 
 */
package com.tjpu.wtf.dao;

import java.util.List;

import com.tjpu.wtf.model.PageModel;
import com.tjpu.wtf.model.Topic;

/**
 * @author hhz.zm
 *
 */
public interface TopicDao {

	public List<Topic> findAllByMemberId(int id);

	public boolean addTopic(Topic topic);

	public int findTopicCountToday(int id,String date);

	public Topic findById(int topicId);

	public List<Topic> findAllByForumId(int forumId);

	public int memberTopicCount(int memberId);

	public List<Topic> findAll();

	public boolean deleteById(int parseInt);

	public boolean update(Topic topic);

	public void updateTopicVisitCount(long id);

	public List<Topic> findByForumIdAll(int forumId);


	public boolean updateTopicReplyCountandLastReplyTime(String time,int topicId);

	public boolean updateReplyId(int id, int topicId);

	public int memberTopicBestCount(int id);

	public void updatelastReplyMember(String loginName, int topicId);

	public void updateReplyCountByTopicId(int topicId);

	public int findAllTopicCountToday(String date);

	public int findAllTopicCount();

	public PageModel<Topic> findAll2(int pageNO, int pageSize);

	public PageModel<Topic> findByForumIdAll2(int forumId, int pageNo,
			int pageSize);

	public PageModel<Topic> findAllByMemberId2(int id, int pageNo,
			int pageSize);

	public void updateAttachIdByTopicId(int attachmentId, int topicId);



	

	



}
