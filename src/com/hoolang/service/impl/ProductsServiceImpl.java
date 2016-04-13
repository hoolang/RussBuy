package com.hoolang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoolang.dao.base.ProductsDao;
import com.hoolang.entity.Products;
import com.hoolang.service.ProductsService;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService{
	@Resource
	private ProductsDao productDao;
	@Override
	public void save(Products product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}

}
