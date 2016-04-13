package com.hoolang.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoolang.dao.base.LikeDao;
import com.hoolang.entity.Like;
import com.hoolang.service.LikeService;
import com.hoolang.util.Hoolang;
@Service("likeService")
public class LikeServiceImpl implements LikeService {

	@Resource
	LikeDao likeDao;
	@Override
	public HashMap save(Like like, long pid, long uid) {
		HashMap hashMap = new HashMap();
		// 如果查询结果为空，就保存like信息
		if(find(pid, uid).isEmpty()){
			likeDao.save(like);
			hashMap.put("status", "done");
		}else{
			// 否则删除
			delete(pid, uid);
			hashMap.put("status", "cancel");
		}
		return hashMap;
	}
	@Override
	public void delete(long pid, long uid) {
		
		likeDao.deleteBySQL("delete from " + Hoolang.HL_LIKE + " where pid = " + pid + " and uid = " + uid);
	}

	@Override
	public List find(long pid, long uid) {
		String sql = "SELECT l.lid FROM " + Hoolang.HL_LIKE + " l where l.pid = " + pid + " and l.uid = " + uid;
		//System.out.println(sql);
		return likeDao.findBySQL(sql);
	}

}
