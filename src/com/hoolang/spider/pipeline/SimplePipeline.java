package com.hoolang.spider.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.hoolang.entity.Products;
import com.hoolang.service.ProductsService;

public class SimplePipeline implements Pipeline {

	private ProductsService productService;
	public ProductsService getProductService() {
		return productService;
	}
	public void setProductService(ProductsService productService) {
		this.productService = productService;
	}
	
	public SimplePipeline(){

	}
	
	public SimplePipeline(ProductsService productService){
		this.productService = productService;
	}
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		System.out.println("===sss==");
		Products product = new Products();
		System.out.println(resultItems.get("title").toString());
		System.out.println(resultItems.get("price").toString());
		System.out.println(resultItems.get("description").toString());
		System.out.println(resultItems.get("images").toString());
		System.out.println(productService);
		
		product.setProduct_name(resultItems.get("title").toString());
		product.setPrice(Float.valueOf(resultItems.get("price").toString()));
		product.setDescription(resultItems.get("description").toString());
		product.setExtra_image_urls(resultItems.get("images").toString());
		productService.save(product);
	}

}
