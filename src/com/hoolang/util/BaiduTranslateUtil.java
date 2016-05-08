package com.hoolang.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class BaiduTranslateUtil {
private static final String UTF8 = "utf-8";
	
	//申请者开发者id，实际使用时请修改成开发者自己的appid
	private static final String appId = "20160406000017813";

	//申请成功后的证书token，实际使用时请修改成开发者自己的token
	private static final String token = "JvNXW2IQPhaAvj3Jx2zc";

	private static final String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";

	//随机数，用于生成md5值，开发者使用时请激活下边第四行代码
	private static final Random random = new Random();
	
	public static String translate(String q, String from, String to) throws Exception{
		//用于md5加密
		//int salt = random.nextInt(10000);
		//本演示使用指定的随机数为1435660288
		int salt = 1435660288;
		
		// 对appId+源文+随机数+token计算md5值
		StringBuilder md5String = new StringBuilder();
		md5String.append(appId).append(q).append(salt).append(token);
		String md5 = DigestUtils.md5Hex(md5String.toString());

		//使用Post方式，组装参数
		HttpPost httpost = new HttpPost(url);
//		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//		nvps.add(new BasicNameValuePair("q", q));
//	    nvps.add(new BasicNameValuePair("from", from));
//	    nvps.add(new BasicNameValuePair("to", to));
//	    nvps.add(new BasicNameValuePair("appid", appId));
//	    nvps.add(new BasicNameValuePair("salt", String.valueOf(salt)));
//	    nvps.add(new BasicNameValuePair("sign", md5));
//		 
//		
//		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));  

		// 构造最简单的字符串数据
		q = java.net.URLEncoder.encode(q, "utf-8"); 
		String params = "q=" + q + "&from="+ from +"&to=" + to + "&appid=" + appId + "&salt=" + String.valueOf(salt) + "&sign=" + md5;
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
				new InputStreamReader(returnStream, UTF8));
		StringBuilder result = new StringBuilder();
		String str = null;
		while ((str = reader.readLine()) != null) {
			result.append(str).append("\n");
		}
		
		//转化为json对象，注：Json解析的jar包可选其它
		JSONObject resultJson = new JSONObject(result.toString());

		//开发者自行处理错误，本示例失败返回为null
		try {
			String error_code = resultJson.getString("error_code");
			if (error_code != null) {
				System.out.println("出错代码:" + error_code);
				System.out.println("出错信息:" + resultJson.getString("error_msg"));
				return null;
			}
		} catch (Exception e) {}
		
		//获取返回翻译结果
		JSONArray array = (JSONArray) resultJson.get("trans_result");
		JSONObject dst = (JSONObject) array.get(0);
		String text = dst.getString("dst");
		text = URLDecoder.decode(text, UTF8);

		return text;
	}
	
	//实际抛出异常由开发者自己处理
	public static String translateToEn(String q) throws Exception{
		
		String result = null;
		try {
			result = translate(q, "zh", "en");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 中文翻译为俄语
	public static  String translateToRu(String q) throws Exception{
	
		String result = null;
		try {
			result = translate(q, "zh", "ru");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 俄文翻译为中文
	public static  String translateRuToZh(String q) throws Exception{
	
		String result = null;
		try {
			result = translate(q, "ru", "zh");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 英文翻译为中文
	public static  String translateEnToZh(String q) throws Exception{
	
		String result = null;
		try {
			result = translate(q, "en", "zh");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 英文翻译为俄文
	public static  String translateEnToRu(String q) throws Exception{
	
		String result = null;
		try {
			result = translate(q, "en", "ru");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	// 自动检测语言翻译为俄文
	public static  String translateAutoToRu(String q) throws Exception{
	
		String result = null;
		try {
			result = translate(q, "auto", "ru");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	// 自动检测语言翻译为中文
	public static String translateAutoToZh(String q) throws Exception{
	
		String result = null;
		try {
			result = translate(q, "auto", "zh");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	// 自动检测语言翻译为英文
	public static String translateAutoToEn(String q) throws Exception{
	
		String result = null;
		try {
			result = translate(q, "auto", "En");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static HashMap translateProduct(String name, String tags, String description){
		System.out.println(name);
		System.out.println(tags);
		System.out.println(description);
		String resultTitle = null;
		String resultTags = null;
		String resultDescription = null;
		try {
			resultTitle = translateAutoToRu(name);
			resultTags = translateAutoToRu(tags);
			resultDescription = translateAutoToRu(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap map = new HashMap();
		map.put("name", resultTitle);
		map.put("tags", resultTags);
		map.put("description", resultDescription);
		return map;
	}
}