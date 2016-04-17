package com.hoolang.service;

import java.util.List;

import com.hoolang.entity.Products;

public interface ProductsService {
	/**
	 * 保存产品
	 * @param product
	 */
	public void save(Products product);
	/**
	 * 获取产品列表
	 * @return
	 */
	public List<Products> listProduct();
	/**
	 * 获取一个产品
	 * @param pid
	 * @return
	 */
	public Products oneProduct(long pid);
}
