package com.hoolang.dao.base;

import java.util.List;

import com.hoolang.entity.Products;

public interface ProductsDao extends BaseDao<Products>{
	List<Products> listProducts(String status, int pageNo, int pageSize);
}
