package com.hoolang.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.hoolang.entity.Products;
import com.hoolang.service.ProductsService;
import com.hoolang.util.BaiduTranslateUtil;
import com.hoolang.util.CSVUtil;
import com.hoolang.util.Hoolang.CreateType;
import com.hoolang.util.JsonTool;
import com.hoolang.util.MobuyUtil;
import com.hoolang.util.WordUtil;
import com.hoolang.util.spider.PPKOOSpider;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 527259201856721178L;

	private long pid;
	private String name;
	private String unique_id;
	private String description;
	private String tags;
	private float price; // 人民币
	private float price_usd; // 美金
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
	public String[] images; // 图片集合，用于jsp界面编辑
	private CreateType createType;
	private int type = 0;

	// 文件导出目录
	private String exportDir = "/export";
	// 接收到的文件
	private File file;
	// 需要采集的urls
	private String urls;
	public Products product;
	public ProductsService productsService;
	public List<Products> productList;

	private String[] skus;
	private String[] sizes;
	private String[] colors;
	private String[] market_price;
	private String[] sell_price;
	private String[] weights;
	private String[] inventory;

	public String save() {
		productsService.save(product);
		return SUCCESS;
	}

	/**
	 * 生成标签（默认通过name来创建）
	 * @return
	 * @throws IOException
	 */
	public String createTags() throws IOException{
		HashMap map = new HashMap();
		map.put("tags", WordUtil.split(name));
		String params[] = {};
		JsonTool.fromObject(map, params);
		return null;
	}
	/**
	 * 一键翻译
	 * @return
	 * @throws IOException
	 */
	public String translate() throws IOException {
		System.out.println("===>" + name);
		System.out.println("===>" + tags);
		System.out.println("===>" + description);
		HashMap map = BaiduTranslateUtil.translateProduct(name, tags, description);
		String params[] = {};
		JsonTool.fromObject(map, params);
		return null;
	}

	/**
	 * 导入CSV
	 * @return
	 * @throws Exception
	 */
	public String importCSV() throws Exception {
		if (file == null) {
			return ERROR;
		}
		List<Products> list = CSVUtil.importToService(file);
		for (Products product : list) {
			productsService.save(product);
		}
		return SUCCESS;
	}

	/**
	 * 获取一个产品
	 * @return
	 */
	public String getOneProduct() {
		product = productsService.oneProduct(pid);

		if (product.getExtra_image_urls() != null) {
			String imgs = product.getExtra_image_urls().replace("[", "").replace("]", "");
			product.setExtra_image_urls(imgs);
			images = imgs.split(",");
		}
		createType = CreateType.values()[type];
		switch (createType) {
		case MOBUY:
			return "mobuy";
		case WISH:
			return "wish";
		case ALIEXPRESS:
			return "aliexpress";
		default:
			return "mobuy";
		}
	}

	/**
	 * 产品列表
	 * @return
	 */
	public String list() {
		productList = productsService.listProduct();
		return SUCCESS;
	}

	/**
	 * 爬虫
	 * @return
	 */
	public String spider() {

		if (urls == null || urls.length() == 0) {
			System.out.println("urls == null");
			return ERROR;
		} else {
			String[] URLs = urls.split("\r\n");
			if (URLs.length == 0) {
				System.out.println("URLs.length == 0");
				return ERROR;
			} else {
				PPKOOSpider.create().spider(productsService, URLs);
				return SUCCESS;
			}
		}
	}

	/**
	 * 创建Mobuy产品
	 * @return
	 */
	public String createMobuyProduct() {
		String products = "[";
		if (skus == null) {
			System.out.println("skus is null");
		}
		for (int i = 0; i < skus.length; i++) {
			String subProduct = "{" + "\"sku\":\"" + skus[i] + "\"," + "\"color\":\"" + colors[i] + "\","
					+ "\"size\":\"" + sizes[i] + "\"," + "\"sell_price\":\"" + sell_price[i] + "\","
					+ "\"market_price\":\"" + market_price[i] + "\"," + "\"weight\":\"" + weights[i] + "\","
					+ "\"inventory\":\"" + inventory[i] + "\"" + "},";
			products = products + subProduct;
		}
		products = products.substring(0, products.length() - 1) + "]";
		product.setExtra_image_urls(product.getExtra_image_urls().replace(",", "|"));

		System.out.println("getExtra_image_urls====>" + product.getExtra_image_urls());
		System.out.println("getMain_image_url====>" + product.getMain_image_url());
		System.out.println("products===>" + products);
		try {
			System.out.println("try====>");
			String result = MobuyUtil.create(product, products);
			System.out.println("result====>" + result);
			// 转化为json对象，注：Json解析的jar包可选其它
			JSONObject resultJson = new JSONObject(result.toString());
			System.out.println("Code:" + resultJson.getString("code"));
			System.out.println("Meassge:" + resultJson.getString("message"));
			try {
				String code = resultJson.getString("code");
				if (Integer.getInteger(code) == 0) {
					System.out.println("上传产品成功:");
					System.out.println("产品ID:" + resultJson.getString("id"));
					return SUCCESS;
				} else {
					System.out.println("上传产品出错信息:" + resultJson.getString("message"));
					return ERROR;
				}
			} catch (Exception e) {
				System.out.println(e);
				return ERROR;
			}

		} catch (Exception e) {
			System.out.println(e);
			return ERROR;
		}
	}

	public List<Products> getProductList() {
		return productList;
	}

	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public CreateType getCreateType() {
		return createType;
	}

	public void setCreateType(CreateType createType) {
		this.createType = createType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getExportDir() {
		return exportDir;
	}

	public void setExportDir(String exportDir) {
		this.exportDir = exportDir;
	}

	public String[] getSkus() {
		return skus;
	}

	public void setSkus(String[] skus) {
		this.skus = skus;
	}

	public String[] getSizes() {
		return sizes;
	}

	public void setSizes(String[] sizes) {
		this.sizes = sizes;
	}

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	public String[] getMarket_price() {
		return market_price;
	}

	public void setMarket_price(String[] market_price) {
		this.market_price = market_price;
	}

	public String[] getSell_price() {
		return sell_price;
	}

	public void setSell_price(String[] sell_price) {
		this.sell_price = sell_price;
	}

	public String[] getWeights() {
		return weights;
	}

	public void setWeights(String[] weights) {
		this.weights = weights;
	}

	public String[] getInventory() {
		return inventory;
	}

	public void setInventory(String[] inventory) {
		this.inventory = inventory;
	}
}
