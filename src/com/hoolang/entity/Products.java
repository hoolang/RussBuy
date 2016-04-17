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
public class Products implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// "*Product Name",
	// "*Unique ID",
	// "*Parent Unique ID",
	// "*Description",
	// "*Tags",
	// "*Price",
	// "MSRP",
	// "*Quantity",
	// "*Shipping",
	// "*Category",
	// "Color",
	// "Size",
	// "Weight",
	// "Other Platform Product Url",
	// "*Main Image URL",
	// "Extra Image URL",
	// "Extra Image URL 1",
	// "Extra Image URL 2",
	// "Extra Image URL 3",
	// "Extra Image URL 4"

	private long pid;
	private String name;
	private String name_en;// 英语标题
	private String name_ru;// 俄语标题
	private String unique_id;
	private String parent_id;
	private String description;
	private String description_en;// 英语描述
	private String description_ru;// 俄语描述
	private String tags;
	private String tags_en;// 英语标签
	private String tags_ru;// 俄语标签
	private float price; // 人民币
	private float price_usd; // 美金
	private float msrp;
	private String quantity;
	private String shipping; // 运费
	private String category; // 分类
	private String color;
	private String size;
	private String weight;
	private String other_platform_product_url;
	private String main_image_url; // 主图
	private String extra_image_urls;// 附加图
	private String langType; // 语言种类（采集源语种）
	private char status; // 状态
	private Date create_date;
	private Date update_date;

	public Products() {	};
	

	public Products(long pid, String name, String name_en, String name_ru,
			String unique_id, String parent_id, String description,
			String description_en, String description_ru, String tags,
			String tags_en, String tags_ru, float price, float price_usd,
			float msrp, String quantity, String shipping, String category,
			String color, String size, String weight,
			String other_platform_product_url, String main_image_url,
			String extra_image_urls, String langType, char status,
			Date create_date, Date update_date) {
		super();
		this.pid = pid;
		this.name = name;
		this.name_en = name_en;
		this.name_ru = name_ru;
		this.unique_id = unique_id;
		this.parent_id = parent_id;
		this.description = description;
		this.description_en = description_en;
		this.description_ru = description_ru;
		this.tags = tags;
		this.tags_en = tags_en;
		this.tags_ru = tags_ru;
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

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}

	@Column(columnDefinition = "text")
	// 字段为text类型
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(length = 500)
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

	@Column(length = 10000)
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

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_ru() {
		return name_ru;
	}

	public void setName_ru(String name_ru) {
		this.name_ru = name_ru;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public String getDescription_ru() {
		return description_ru;
	}

	public void setDescription_ru(String description_ru) {
		this.description_ru = description_ru;
	}

	public String getTags_en() {
		return tags_en;
	}

	public void setTags_en(String tags_en) {
		this.tags_en = tags_en;
	}

	public String getTags_ru() {
		return tags_ru;
	}

	public void setTags_ru(String tags_ru) {
		this.tags_ru = tags_ru;
	}

}
