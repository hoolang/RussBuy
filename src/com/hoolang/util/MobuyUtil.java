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
	public static String create(Products product) throws Exception{
		//使用Post方式，组装参数
		HttpPost httpost = new HttpPost(Hoolang.MOBUY_CREATE_PRODUCT_URL);
		
		String time = System.currentTimeMillis() + "";
		int radomInt = new Random().nextInt(99);
		int radomInt2 = new Random().nextInt(99);
		String parent_id = time +"-"+ radomInt +"-"+ radomInt2;
		product.setParent_id(parent_id);
		
		// 添加json开始符号
		String products = "[";
		
		String[] colors = product.getColor().split(",");
		String[] sizes = product.getSize().split(",");
		
		int i = 0;
		
		if(colors.length > 0 && sizes.length > 0){// 有颜色也有尺寸
			System.out.println("有颜色也有尺寸");
			for(String color : colors){
				for(String size : sizes){
					i++;
					String subProduct = "{"
							+ "sku:\"" + parent_id + "-" + i +"-" + color + "-" + size +"\","
							+ "color:\"" + color + "\","
							+ "size:\"" + size + "\","
							+ "sell_price:\"" + product.getPrice() + "\","
							+ "weight:\"" + product.getWeight() + "\","
							+ "inventory:\"" + product.getQuantity() + "\","
							+ "},";
					products = products + subProduct;
				}
			}
		}else if(colors.length > 0 && sizes.length == 0){
			System.out.println("只有颜色");
			for(String color : colors){
				i++;
				String subProduct = "{"
						+ "sku:\"" + parent_id + "-" + i + "-" + color +"\","
						+ "color:\"" + color + "\","
						+ "size:\"\","
						+ "sell_price:\"" + product.getPrice() + "\","
						+ "weight:\"" + product.getWeight() + "\","
						+ "inventory:\"" + product.getQuantity() + "\","
						+ "},";
				products = products + subProduct;
			}
		}else if(colors.length == 0 && sizes.length > 0){
			System.out.println("只有尺寸");
			for(String size : sizes){
				i++;
				String subProduct = "{"
						+ "sku:\"" + parent_id + "-" + i + "-" + size +"\","
						+ "color:\"\","
						+ "size:\"" + size + "\","
						+ "sell_price:\"" + product.getPrice() + "\","
						+ "weight:\"" + product.getWeight() + "\","
						+ "inventory:\"" + product.getQuantity() + "\","
						+ "},";
				products = products + subProduct;
			}
		}else if(colors.length == 0 && sizes.length == 0){
			System.out.println("没颜色没尺寸");
			String subProduct = "{"
					+ "sku:\"" + parent_id + "-" +i+ "\","
					+ "color:\"\","
					+ "size:\"\","
					+ "sell_price:\"" + product.getPrice() + "\","
					+ "weight:\"" + product.getWeight() + "\","
					+ "inventory:\"" + product.getQuantity() + "\","
					+ "},";
			products = products + subProduct;
		}
		// 去掉最后一个逗号，添加json关闭符号
		products = products.substring(0,products.length()-1) + "]";

		
		String params = "access_token=" + ACCESS_TOKEN + "&name="+ product.getProduct_name()
				+ "&parent_sku=" + product.getParent_id() + "&tags=" + product.getTags() 
				+ "&main_image=" + product.getMain_image_url() + "&extra_images=" + product.getExtra_image_urls()
				+ "&category=" + product.getCategory() + "&warehouse_address=" + 1 
				+ "&description=" + product.getDescription() + "&short_description=" +""
				+ "&other_platform_product_url=" + "" + "&products=" + products;
		System.out.println(params);
		StringEntity reqEntity = new StringEntity(params);
		
		reqEntity.setContentEncoding("UTF_8");
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
