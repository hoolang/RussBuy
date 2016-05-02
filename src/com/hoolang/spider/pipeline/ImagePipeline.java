package com.hoolang.spider.pipeline;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.hoolang.entity.Products;
import com.hoolang.service.ProductsService;
import com.hoolang.util.ImageUtil;

public class ImagePipeline implements Pipeline {

	private ProductsService productService;
	private String langType;

	public String getLangType() {
		return langType;
	}

	public ImagePipeline setLangType(String langType) {
		this.langType = langType;
		return this;
	}

	public ProductsService getProductService() {
		return productService;
	}

	public void setProductService(ProductsService productService) {
		this.productService = productService;
	}

	public ImagePipeline() {

	}

	public ImagePipeline(ProductsService productService) {
		this.productService = productService;
	}

	@Override
	public void process(ResultItems resultItems, Task task) {

		System.out.println("HOOLANG:============================>"
				+ ImagePipeline.class.getName());
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
			colorStr = convertString(resultItems.get("color").toString()
					.replace("[", "").replace("]", ""));
		}
		product.setColor(colorStr);
		// 产品尺寸
		String sizeStr = "";
		if (resultItems.get("size") != null) {
			sizeStr = convertString(resultItems.get("size").toString()
					.replace("[", "").replace("]", ""));
		}
		product.setSize(sizeStr);
		
		// 产品图片集合
		//product.setExtra_image_urls(resultItems.get("images").toString());
		//System.out.println("images.toString()===> "
		//		+ resultItems.get("images").toString());

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

		List list = resultItems.get("imgList");
		System.out.println("list.size===>"+list.size());
		
        Calendar a =  Calendar.getInstance();
        String path = String.valueOf(a.get(Calendar.YEAR))+ "/"+
        		String.valueOf(a.get(Calendar.MONTH)+1) + "/"+
                String.valueOf(a.get(Calendar.DAY_OF_MONTH)+ "/"+
                String.valueOf(a.get(Calendar.HOUR)));
        
        String newImages = "";
		for (int i = 0; i < list.size(); i++) {
			String url = list.get(i).toString();
			String fileName = url.substring(url.lastIndexOf("/")).replace("/", "");
			newImages += path +"/" + fileName + "|";
			MyThread thread = new MyThread(url, path,fileName);
			thread.start();
		}
		product.setExtra_image_urls(newImages.substring(0, newImages.length()-1));
		productService.save(product);
	}

	public String convertString(String string) {
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



class MyThread extends Thread{  
    
    private String url;
    private String path;
    private String fileName;
    public MyThread(String url, String path, String fileName){  
        this.url =url;
        this.path = path;
        this.fileName = fileName;
    }  
      
    public void run(){  
        try {
        	System.out.println("this.url===>"+this.url);
			ImageUtil.write(this.url, path, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }  
}  
