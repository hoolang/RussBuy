package com.hoolang.service;

import java.util.HashMap;
import java.util.List;

import com.hoolang.entity.Like;

public interface LikeService {

	public HashMap save(Like like, long pid, long uid);
	
	/**
	 * 根据pid和uid删除点赞数据
	 * @param pid
	 * @param uid
	 */
	public void delete(long pid, long uid);
	
	public List find(long pid, long uid);
}
