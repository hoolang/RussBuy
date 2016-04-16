package com.hoolang.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoolang.dao.base.ProductsDao;
import com.hoolang.dao.impl.base.BaseDaoImpl;
import com.hoolang.entity.Post;
import com.hoolang.entity.Products;
import com.hoolang.util.Hoolang;

@Repository("productsDao")
public class ProductsImpl extends BaseDaoImpl<Products> implements ProductsDao {

	@Override
	public List<Products> listProducts(String status, int pageNo, int pageSize) {
		String HQL = "from Products";
		return findByPage(HQL, pageNo, pageSize);
	}

}
