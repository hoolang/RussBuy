package com.hoolang.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.opensymphony.xwork2.ActionSupport;

public class OAuthAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7578336287931352669L;
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static String oauthWish() throws ClientProtocolException, IOException{
		//使用Post方式，组装参数
		HttpPost httpost = new HttpPost("https://sandbox.merchant.wish.com/api/v2/oauth/access_token");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("client_id", "571b48bd2b296c17406ff8e7"));
		nvps.add(new BasicNameValuePair("client_secret", "ef6ef7a6823f41beab714111b5764b2b"));
		nvps.add(new BasicNameValuePair("code", "f8aee88c0c954fddba1876debb5ab751"));
		nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));
		nvps.add(new BasicNameValuePair("redirect_uri", "https://127.0.0.1"));
		
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8)); 
		
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
			System.out.println(str);
		}
		
		return SUCCESS;
	}

}
