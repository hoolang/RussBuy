package com.hoolang.action;

import java.io.File;
import java.util.List;

import org.json.JSONObject;

import com.hoolang.entity.Products;
import com.hoolang.service.ProductsService;
import com.hoolang.util.CSVUtil;
import com.hoolang.util.MobuyUtil;
import com.hoolang.util.spider.PPKOOSpider;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 527259201856721178L;

	private long uid;
	private String product_name;
	private String unique_id;
	private String description;
	private String tags;
	private float price;			//人民币
	private float price_usd;		//美金
	private float msrp;
	private String quantity;
	private String shipping;
	private String category;
	private String color;
	private String size;
	private float weight;
	private String other_platform_product_url;
	private String main_image_url;
	private String extra_image_urls;
	
    //文件导出目录  
    private String exportDir = "/export";
	// 接收到的文件
	private File file;
	// 需要采集的urls
	private String urls;
	private Products product;
	public ProductsService productsService;
	public List<Products> productList;

	public String save(){
		productsService.save(product);
		return SUCCESS;
	}
	// 导入CSV文档
	public String importCSV() throws Exception{
		if(file == null){
			return ERROR;
		}
		List<Products> list = CSVUtil.importToService(file);
		for(Products product : list){
			productsService.save(product);
		}
		return SUCCESS;
	}
	// 查询
	public String list(){
		productList = productsService.listProduct();
//		System.out.println("ssssssss====>"+productList.size());
//		Map<String,Object> request = (Map)ActionContext.getContext().get("request");
//		request.put("productList", productList);
		return SUCCESS;
	}
	
	// 爬虫
	public String spider(){
		
		if(urls == null || urls.length() == 0 ){
			System.out.println("urls == null");
			return ERROR;
		}else{
			String[] URLs = urls.split("\r\n");
			if(URLs.length == 0){
				System.out.println("URLs.length == 0");
				return ERROR;
			}else{
				PPKOOSpider.create().spider(productsService, URLs);
				return SUCCESS;
			}
		}
	}
	
	// 创建Mobuy产品
	public String createMobuyProduct(){
		try {
			String result = MobuyUtil.create(product);
			//转化为json对象，注：Json解析的jar包可选其它
			JSONObject resultJson = new JSONObject(result.toString());

			try {
				String code = resultJson.getString("code");
				if (Integer.getInteger(code) == 0) {
					System.out.println("上传产品成功:");
					System.out.println("产品ID:" + resultJson.getString("id"));

				}else{
					System.out.println("上传产品出错信息:" + resultJson.getString("message"));
				}
			} catch (Exception e) {}
			
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public List<Products> getProductList() {
		return productList;
	}
	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getUnique_id() {
		return unique_id;
	}
	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getPrice_usd() {
		return price_usd;
	}
	public void setPrice_usd(float price_usd) {
		this.price_usd = price_usd;
	}
	public float getMsrp() {
		return msrp;
	}
	public void setMsrp(float msrp) {
		this.msrp = msrp;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getShipping() {
		return shipping;
	}
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getOther_platform_product_url() {
		return other_platform_product_url;
	}
	public void setOther_platform_product_url(String other_platform_product_url) {
		this.other_platform_product_url = other_platform_product_url;
	}
	public String getMain_image_url() {
		return main_image_url;
	}
	public void setMain_image_url(String main_image_url) {
		this.main_image_url = main_image_url;
	}
	public String getExtra_image_urls() {
		return extra_image_urls;
	}
	public void setExtra_image_urls(String extra_image_urls) {
		this.extra_image_urls = extra_image_urls;
	}


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public ProductsService getProductsService() {
		return productsService;
	}

	public void setProductsService(ProductsService productsService) {
		this.productsService = productsService;
	}
	public String getUrls() {
		return urls;
	}
	public void setUrls(String urls) {
		this.urls = urls;
	}

	
	
}
