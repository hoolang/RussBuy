package com.hoolang.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoolang.dao.base.SizeDao;
import com.hoolang.dao.impl.base.BaseDaoImpl;
import com.hoolang.entity.Size;
@Repository("sizeDao")
public class SizeDaoImpl extends BaseDaoImpl<Size> implements SizeDao {

	@Override
	public List<Size> listSizes(String status, int pageNo, int pageSize) {
		String HQL = "from Size";
		return findByPage(HQL, pageNo, pageSize);
	}

}
