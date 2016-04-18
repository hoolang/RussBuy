package com.hoolang.dao.base;

import java.util.List;

import com.hoolang.entity.Size;

public interface SizeDao extends BaseDao<Size>{
	// 尺寸列表
	List<Size> listSizes(String status, int pageNo, int pageSize);
}
