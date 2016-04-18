package com.hoolang.service;

import java.util.List;

import com.hoolang.entity.Size;

public interface SizeService {
	/**
	 * 保存Size
	 * @param product
	 */
	public void save(Size size);
	/**
	 * 获取Size列表
	 * @return
	 */
	public List<Size> listSize();
	/**
	 * 获取一个Size
	 * @param id
	 * @return
	 */
	public Size oneSize(int id);
}
