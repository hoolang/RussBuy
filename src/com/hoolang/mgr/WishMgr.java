package com.hoolang.mgr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hoolang.entity.Products;

public class WishMgr {
	private static String ACCESS_TOKEN = "ec7c968622e24269b2c9badf62e376ee";
	/**
	 * 添加产品URL
	 */
	private static String ADD = "https://sandbox.merchant.wish.com/api/v2/product/add";
	/**
	 * 添加产品变种
	 */
	private static String ADD_VARIANT = "https://sandbox.merchant.wish.com/api/v2/variant/add";

	private static class LazyHolder {
		private static final WishMgr INSTANCE = new WishMgr();
	}

	private WishMgr() {
	}

	public static final WishMgr getInstance() {
		return LazyHolder.INSTANCE;
	}

	public static String createProduct(Products product) {
		HttpPost httpost = new HttpPost(ADD);
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("name", product.getName()));
		nvps.add(new BasicNameValuePair("sku", product.getParent_id()));
		nvps.add(new BasicNameValuePair("inventory", product.getQuantity()));
		nvps.add(new BasicNameValuePair("price", product.getPrice() + ""));
		nvps.add(new BasicNameValuePair("shipping", product.getShipping()));
		nvps.add(new BasicNameValuePair("description", product.getDescription()));
		nvps.add(new BasicNameValuePair("tags", product.getTags()));
		nvps.add(new BasicNameValuePair("main_image", product.getMain_image_url()));
		nvps.add(new BasicNameValuePair("extra_images", product.getExtra_image_urls()));
		nvps.add(new BasicNameValuePair("access_token", ACCESS_TOKEN));
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		
		System.out.println("product.getMain_image_url()===>" + product.getMain_image_url());
		System.out.println("product.getExtra_image_urls()===>" + product.getExtra_image_urls());

		httpost.addHeader("Authorization", "Bearer "+ACCESS_TOKEN);

		// 创建httpclient链接，并执行
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpost);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		// 对于返回实体进行解析
		HttpEntity entity = response.getEntity();
		InputStream returnStream = null;
		try {
			returnStream = entity.getContent();
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(returnStream,
					"utf8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder result = new StringBuilder();
		String str = null;
		try {
			while ((str = reader.readLine()) != null) {
				result.append(str).append("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result:======>"+result);
		return result.toString();
	}

	// 产品变种
	public static String createProductVariation(Products product) throws ClientProtocolException, IOException {
		// parent_sku = red-shoe
		// sku = red-shoe-12
		// inventory = 10
		// price = 10
		// shipping = 3
		// access_token = an_example_access_token
		// size = 12
		HttpPost httpost = new HttpPost(ADD_VARIANT);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("parent_sku", product.getParent_id()));
		nvps.add(new BasicNameValuePair("sku", product.getUnique_id()));
		nvps.add(new BasicNameValuePair("inventory", product.getQuantity()));
		nvps.add(new BasicNameValuePair("price", product.getPrice() + ""));
		//尺寸
		if(product.getSize() != null && product.getSize().length()>0){
			nvps.add(new BasicNameValuePair("size", product.getSize()));
		}
		// 颜色
		if(product.getColor() != null && product.getColor().length()>0){
			nvps.add(new BasicNameValuePair("color", product.getColor()));
		}
		
		System.out.println("product.getParent_id()===>" + product.getParent_id());
		System.out.println("sku===>" + product.getUnique_id());
		System.out.println("inventory===>" + product.getQuantity());
		System.out.println("price===>" + product.getPrice());
		System.out.println("size===>" + product.getSize());
		System.out.println("color===>" + product.getColor());
		
		nvps.add(new BasicNameValuePair("shipping", product.getShipping()));
		nvps.add(new BasicNameValuePair("access_token", ACCESS_TOKEN));
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		//创建httpclient链接，并执行
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    CloseableHttpResponse response = httpclient.execute(httpost);
	    
	    //对于返回实体进行解析
		HttpEntity entity = response.getEntity();
		InputStream returnStream = entity.getContent();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(returnStream, "utf8"));
		StringBuilder result = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str).append("\n");
		}
		System.out.println("sku===result===>"+ result);
		return null;
	}

}