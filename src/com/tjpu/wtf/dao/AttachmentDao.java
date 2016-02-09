/**
 * 
 */
package com.tjpu.wtf.dao;

import java.util.List;

import com.tjpu.wtf.model.Attachment;
import com.tjpu.wtf.model.PageModel;

/**
 * @author zm
 *
 */
public interface AttachmentDao {

	public boolean addAttachment(Attachment att);

	public List<Attachment> findAllByMemberId(int id);

	public PageModel<Attachment> findAllByMemberId2(int id, int pageNo,
			int pageSize);

	public boolean deleteByTopicId(int topicId);

	public Attachment findByMaxId();

	public List<Attachment> findAllByTopicId(int topicId);

	public List<Attachment> findAll();

}
