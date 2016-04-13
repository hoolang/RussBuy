package com.hoolang.dao.base;

import java.util.List;

import com.hoolang.entity.Comments;
import com.hoolang.entity.Post;

public interface CommentsDao extends BaseDao<Comments> {

	/**
	 * 获取评论人数最多的数据
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 */
	List<Comments> mostCommentsPosts(String status, int index, int max);
}
