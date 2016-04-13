package com.hoolang.dao.impl;

import org.springframework.stereotype.Repository;

import com.hoolang.dao.base.ProductsDao;
import com.hoolang.dao.impl.base.BaseDaoImpl;
import com.hoolang.entity.Products;

@Repository("productsDao")
public class ProductsImpl extends BaseDaoImpl<Products> implements ProductsDao {


}
