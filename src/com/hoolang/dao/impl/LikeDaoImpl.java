package com.hoolang.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoolang.dao.base.LikeDao;
import com.hoolang.dao.impl.base.BaseDaoImpl;
import com.hoolang.entity.Like;
@Repository("likeDao")
public class LikeDaoImpl extends BaseDaoImpl<Like> implements LikeDao {

	@Override
	public List<Like> mostLikesPosts(String status, int index, int max) {
		String SQL = "select l.post, count(l.post) as like_count from Like l group by l.post order by like_count desc";
		return findByHQL(SQL, max);
	}

}
