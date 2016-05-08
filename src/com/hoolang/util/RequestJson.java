package com.hoolang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.hoolang.entity.Products;

public class RequestJson {
	/**
	 * 获取产品列表
	 * 
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public static List<Products> productList() throws IOException,
			JSONException {
		// 使用Post方式，组装参数
		HttpPost httpost = new HttpPost(Hoolang.PRODUCT_LIST_URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		// nvps.add(new BasicNameValuePair("q", q));
		// nvps.add(new BasicNameValuePair("from", from));
		// nvps.add(new BasicNameValuePair("to", to));
		// nvps.add(new BasicNameValuePair("appid", appId));
		// nvps.add(new BasicNameValuePair("salt", String.valueOf(salt)));
		// nvps.add(new BasicNameValuePair("sign", md5));
		//
		//
		// httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		// 构造最简单的字符串数据
		// q = java.net.URLEncoder.encode(q, "utf-8");
		// String params = "q=" + q + "&from="+ from +"&to=" + to + "&appid=" +
		// appId + "&salt=" + String.valueOf(salt) + "&sign=" + md5;

		// 创建httpclient链接，并执行
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(httpost);

		// 对于返回实体进行解析
		HttpEntity entity = response.getEntity();
		InputStream returnStream = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				returnStream, "UTF-8"));
		StringBuilder result = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str).append("\n");
		}

		// 转化为json对象，注：Json解析的jar包可选其它
		// JSONObject resultJson = new JSONObject(result.toString());

		JSONObject resultJson = JSONObject.fromObject(result.toString());

		// 开发者自行处理错误，本示例失败返回为null
		try {
			String error_code = resultJson.getString("error_code");
			if (error_code != null) {
				System.out.println("出错代码:" + error_code);
				System.out.println("出错信息:" + resultJson.getString("error_msg"));
				return null;
			}
		} catch (Exception e) {
		}
		// 获取返回翻译结果
		JSONArray array = (JSONArray) resultJson.get("content");
		List list = new ArrayList();
		for (int i = 0; i < array.size(); i++) {
			Products product = (Products) JSONObject.toBean(
					JSONObject.fromObject(array.get(i)), Products.class);
			list.add(product);
		}

		// JSONObject dst = (JSONObject) array.get(0);
		// String text = dst.getString("content");
		// text = URLDecoder.decode(text, "");

		return list;
	}

	/**
	 * 获取一个产品
	 * @param pid 要获取的产品的ID
	 * @return 返回一个产品信息
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static Products oneProduct(long pid) throws UnsupportedOperationException, IOException {
		// 使用Post方式，组装参数
		HttpPost httpost = new HttpPost(Hoolang.PRODUCT_ONE_URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("pid", pid+""));
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		// 创建httpclient链接，并执行
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(httpost);

		// 对于返回实体进行解析
		HttpEntity entity = response.getEntity();
		InputStream returnStream = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				returnStream, "UTF-8"));
		StringBuilder result = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str).append("\n");
		}

		// 转化为json对象，注：Json解析的jar包可选其它
		// JSONObject resultJson = new JSONObject(result.toString());

		JSONObject resultJson = JSONObject.fromObject(result.toString());

		// 开发者自行处理错误，本示例失败返回为null
		try {
			String error_code = resultJson.getString("error_code");
			if (error_code != null) {
				System.out.println("出错代码:" + error_code);
				System.out.println("出错信息:" + resultJson.getString("error_msg"));
				return null;
			}
		} catch (Exception e) {
		}

		Products product = (Products) JSONObject.toBean(
				(JSONObject) resultJson.get("content"), Products.class);

		return product;
	}
}
