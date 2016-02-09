/**
 * 
 */
package com.tjpu.wtf.dao;

import java.util.List;

import com.tjpu.wtf.model.PageModel;
import com.tjpu.wtf.model.Reply;

/**
 * @author hhz.zm
 *
 */
public interface ReplyDao {

public	boolean addReply(Reply reply);

public List<Reply> findAll();

public List<Reply> findByTopicId(int parseInt);

public Reply findMaxFloor(int topicId);

public Reply findMaxReplyId(int topicId);

public Reply findByReplyId(int replyId);

public boolean deleteByTopicId(int topicId);

public boolean deleteByReplyId(int replyId);

public List<Reply> findByMemberLoginName(String loginName);

public boolean setStatus2(int replyId);

public boolean setStatus1(int replyId);

public int findAllReplyCount();

public PageModel<Reply> findByMemberLoginName2(String loginName, int pageNo,
		int pageSize);

public PageModel<Reply> findByTopicId2(int topicId, int pageNo, int pageSize);

}
