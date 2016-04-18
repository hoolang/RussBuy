package com.hoolang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.hoolang.entity.Products;

public class MobuyUtil {

	private static String ACCESS_TOKEN = "019d7ab13eab88c492e4ce92ec58b0d5";
	public static String create(Products product, String subProducts) throws Exception{
		//使用Post方式，组装参数
		HttpPost httpost = new HttpPost(Hoolang.MOBUY_CREATE_PRODUCT_URL);
		
		String time = System.currentTimeMillis() + "";
		int radomInt = new Random().nextInt(99);
		int radomInt2 = new Random().nextInt(99);
		String parent_id = time +"-"+ radomInt +"-"+ radomInt2;
		System.out.println("parent_id===>"+ parent_id);
		System.out.println("product.getParent_id===>"+product.getParent_id());
		product.setParent_id(parent_id);
		
		String params = "access_token=" + ACCESS_TOKEN + "&name="+ product.getName()
				+ "&parent_sku=" + product.getParent_id() + "&tags=" + product.getTags() 
				+ "&main_image=" + product.getMain_image_url() + "&extra_images=" + product.getExtra_image_urls()
				+ "&category=" + product.getCategory() + "&warehouse_address=" + 1 
				+ "&description=" + product.getDescription() + "&short_description=" +""
				+ "&other_platform_product_url=" + "" + "&products=" + subProducts;
		System.out.println("params====>"+params);
		StringEntity reqEntity = new StringEntity(params);
		
		reqEntity.setContentEncoding("UTF8");
		// 设置类型
	    reqEntity.setContentType("application/x-www-form-urlencoded");
		httpost.setEntity(reqEntity);  
		//创建httpclient链接，并执行
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    CloseableHttpResponse response = httpclient.execute(httpost);
	    
	    //对于返回实体进行解析
		HttpEntity entity = response.getEntity();
		InputStream returnStream = entity.getContent();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(returnStream, "UTF8"));
		StringBuilder result = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str).append("\n");
		}
		
		return result.toString();

	}
}
