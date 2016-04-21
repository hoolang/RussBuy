package com.hoolang.service.impl;

import java.util.List;

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
	@Override
	public List<Products> listProduct() {
		return productDao.findByPage("from Products order by create_date desc", 0, 10);
	}
	@Override
	public Products oneProduct(long pid) {
		return productDao.get(Products.class, pid);
	}

}
