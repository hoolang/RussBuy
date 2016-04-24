package com.hoolang.spider.pipeline;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.hoolang.entity.Products;
import com.hoolang.service.ProductsService;

public class SimplePipeline implements Pipeline {

	private ProductsService productService;
	private String langType;

	public String getLangType() {
		return langType;
	}

	public SimplePipeline setLangType(String langType) {
		this.langType = langType;
		return this;
	}

	public ProductsService getProductService() {
		return productService;
	}

	public void setProductService(ProductsService productService) {
		this.productService = productService;
	}

	public SimplePipeline() {

	}

	public SimplePipeline(ProductsService productService) {
		this.productService = productService;
	}

	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		System.out.println("HOOLANG:============================>"
				+ SimplePipeline.class.getName());
		Products product = new Products();
		// 产品标题
		product.setName(resultItems.get("title").toString());
		// 产品价格
		product.setPrice(Float.valueOf(resultItems.get("price").toString()));
		// 产品描述
		product.setDescription(resultItems.get("description").toString());

		// 产品关键词
		String tags = "";
		if (resultItems.get("tags") != null) {
			tags = resultItems.get("tags").toString();
		}
		product.setTags(tags);
		// 产品颜色
		String colorStr = "";
		if (resultItems.get("color") != null) {
			colorStr = convertString(resultItems.get("color").toString().replace("[", "").replace("]", ""));
		}

		product.setColor(colorStr);
		// 产品尺寸
		String sizeStr = "";
		if (resultItems.get("size") != null) {
			sizeStr = convertString(resultItems.get("size").toString().replace("[", "").replace("]", ""));
		}
		product.setSize(sizeStr);

		// 产品图片集合
		product.setExtra_image_urls(resultItems.get("images").toString());
		// 时间初始化
		Date create_date = new Date();
		product.setCreate_date(create_date);
		product.setUpdate_date(create_date);
		// 产品id
		String time = System.currentTimeMillis() + "";
		int radomInt = new Random().nextInt(999);
		int radomInt2 = new Random().nextInt(999);
		product.setParent_id(time + "-" + radomInt + "-" + radomInt2);
		// 初始状态
		product.setStatus('0');
		// 语言
		product.setLangType(langType);
		// 采集的url
		product.setOther_platform_product_url(resultItems.get("url").toString());

		productService.save(product);
	}
	
	public String convertString(String string){
		String[] strings = string.split(",");
		Map map = new HashMap();
		for (String str : strings) {
			str = str.trim();
			map.put(str, str);
		}
		String strTmp = "";
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			strTmp += entry.getKey() + ",";
		}
		strTmp = strTmp.substring(0, strTmp.length() - 1);
		return strTmp;
	}
}
