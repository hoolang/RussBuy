package com.hoolang.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "HL_PRODUCTS")
public class Products implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	"*Product Name",
//	"*Unique ID",
//	"*Parent Unique ID",
//	"*Description",
//	"*Tags", 
//	"*Price",
//	"MSRP",
//	"*Quantity",
//	"*Shipping",
//	"*Category",
//	"Color",
//	"Size",
//	"Weight",
//	"Other Platform Product Url", 
//	"*Main Image URL", 
//	"Extra Image URL", 
//	"Extra Image URL 1", 
//	"Extra Image URL 2",
//	"Extra Image URL 3",
//	"Extra Image URL 4"
	
	private long uid;
	private String product_name;
	private String unique_id;
	private String parent_id;
	private String description;
	private String tags;
	private float price;			//人民币
	private float price_usd;		//美金
	private float msrp;
	private String quantity;
	private String shipping;		//运费
	private String category;		//分类
	private String color;
	private String size;
	private String weight;
	private String other_platform_product_url;
	private String main_image_url;	//主图
	private String extra_image_urls;//附加图
	private String langType;			//语言种类
	private char status;			//状态
	private Date create_date;
	private Date update_date;
	
	public Products(){};
	
	public Products(long uid, String product_name, String unique_id, String parent_id, String description, String tags,
			float price, float price_usd, float msrp, String quantity, String shipping, String category, String color,
			String size, String weight, String other_platform_product_url, String main_image_url,
			String extra_image_urls, String langType, char status, Date create_date, Date update_date) {
		super();
		this.uid = uid;
		this.product_name = product_name;
		this.unique_id = unique_id;
		this.parent_id = parent_id;
		this.description = description;
		this.tags = tags;
		this.price = price;
		this.price_usd = price_usd;
		this.msrp = msrp;
		this.quantity = quantity;
		this.shipping = shipping;
		this.category = category;
		this.color = color;
		this.size = size;
		this.weight = weight;
		this.other_platform_product_url = other_platform_product_url;
		this.main_image_url = main_image_url;
		this.extra_image_urls = extra_image_urls;
		this.langType = langType;
		this.status = status;
		this.create_date = create_date;
		this.update_date = update_date;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}
	@Column(length=10000)
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
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
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getLangType() {
		return langType;
	}
	public void setLangType(String langType) {
		this.langType = langType;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
}
