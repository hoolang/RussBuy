package com.hoolang.service;

import java.util.List;

import com.hoolang.entity.Products;

public interface ProductsService {
	public void save(Products product);
	public List<Products> listProduct();
}
