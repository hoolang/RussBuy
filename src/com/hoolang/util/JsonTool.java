package com.hoolang.util;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class JsonTool {

	/***
	 * 
	 * @param obj
	 * @param params 需要过滤的字段
	 * @throws IOException
	 */
	public static void fromObject(Object obj, String[] params) throws IOException{
		
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new HLJsonValueProcessor()); 
		
		jsonConfig.setIgnoreDefaultExcludes(false); //设置默认忽略 
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//设置循环策略为忽略    解决json最头疼的问题 死循环
        if(params.length > 0){
        	jsonConfig.setExcludes(params);//此处是亮点，只要将所需忽略字段加到数组中即可
        }
		
		//JSONArray jsonArray = JSONArray.fromObject(obj,jsonConfig);
        JSONObject jsonObject = JSONObject.fromObject(obj,jsonConfig);
		//JSONArray jsonArray = JSONArray.fromObject(obj);

		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html; charset=UTF-8");
	    //response.setCharacterEncoding("UTF-8");
	    response.getWriter().print(jsonObject);
	}
	
	public static void fromObject(Object obj) throws IOException{
		String[] params = {};
		JsonTool.fromObject(obj, params);
	}
	
	public static void defaultSuccess() throws IOException{
		HashMap map = new HashMap();
		map.put("code", "0");
		String[] params = {};
		JsonTool.fromObject(map, params);
	}
}