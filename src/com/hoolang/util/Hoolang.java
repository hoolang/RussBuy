package com.hoolang.util;
/**  
 * Package: com.hoolang.util  
 *  
 * File: HoolangFinal.java   
 *  
 * Author: hoolang   Date: 2015年5月29日  
 *  
 * Copyright @ 2015 Corpration 深圳后浪时代科技有限公司
 *   
 */

public class Hoolang {
	
	/**
	 * 服务器地址
	 */
	public static final String SERVICE_URL = "http://23.88.177.154:8080/nvshen/";
//	public static final String SERVICE_URL = "http://192.168.168.100:8080/nvshen/";
	/**
	 * 数据名称
	 */
	public static final String DATABASE_NAME = "nvshen";
	/**
	 * 点赞表名
	 */
	public static final String HL_LIKE = DATABASE_NAME + "." + "HL_LIKE";
	/**
	 * 用户表
	 */
	public static final String HL_USER = DATABASE_NAME + "." + "HL_USER";
	/**
	 * show表
	 */
	public static final String HL_POST = DATABASE_NAME + "." + "HL_POST";
	/**
	 * 评论表
	 */
	public static final String HL_COMMENT = DATABASE_NAME + "." + "HL_COMMENT";
	/**
	 * 用户头像服务器地址
	 */
	public static final String USER_ICON_URL = "http://192.168.168.188:8008/nvshen/icons/";
	/**
	 * show的图片服务器地址
	 */
	public static final String SHOW_PHOTO_URL = "http://192.168.168.188:8008/nvshen/photos/";
	/**
	 * json返回的字段意义
	 */
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String CANCEL = "cancel";
	public static final String DONE = "done";
	public static final String WRONG_REQUEST = "wrong request!!";
	
	/**
	 *  表示需要获取旧的信息
	 */
	public static final String OLDER = "older";
	/**
	 * 表示需要获取新的信息
	 */
	public static final String LATEST = "latest";
	/**
	 * 语言类型
	 * @author hoolang
	 *
	 */
    public static enum LangType {
        ZH, EN, RU;
    }
	/**
	 * 创建产品的类型
	 * @author hoolang
	 *
	 */
    public static enum CreateType {
        MOBUY, WISH, ALIEXPRESS;
    }
    
    /**
     * Mobuy创建产品借口URL
     */
    public static final String MOBUY_CREATE_PRODUCT_URL = "http://vendor.mobuyapp.ru/devproduct/add";
    /**
     * 系统根路径
     */
    public static final String ROOT = System.getProperty("Hoolang.root");
    
    ////////////////////////JSON RESULT/////////////////////////////////
    /**
     * 获取一个产品信息
     */
    public static final String PRODUCT_ONE_URL = SERVICE_URL + "pjson/oneProduct.action";
    /**
     * 产品列表URL http://127.0.0.1:8080/nvshen/
     */
    public static final String PRODUCT_LIST_URL = SERVICE_URL + "pjson/productList.action";
    /**
     * 生成水印图片
     */
    public static final String PRODUCT_CREATE_MARK = SERVICE_URL + "pjson/productCreateMark.action";
    /**
     * wish爬虫
     */
    public static final String WISH_SPIDER_URL = SERVICE_URL + "pjson/wishSpider.action";
////////////////////////JSON RESULT END/////////////////////////////////
}
