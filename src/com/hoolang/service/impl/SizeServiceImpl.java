package com.hoolang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoolang.dao.base.SizeDao;
import com.hoolang.entity.Size;
import com.hoolang.service.SizeService;

@Service("sizeService")
public class SizeServiceImpl implements SizeService {
	@Resource
	private SizeDao sizeDao;
	@Override
	public void save(Size size) {

		sizeDao.save(size);
	}

	@Override
	public List<Size> listSize() {
		return sizeDao.findByPage("from Size", 0, 10);
	}

	@Override
	public Size oneSize(int sid) {
		// TODO Auto-generated method stub
		return sizeDao.get(Size.class, sid);
	}

}
