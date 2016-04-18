package com.hoolang.action;

import java.util.List;

import com.hoolang.entity.Size;
import com.hoolang.service.SizeService;
import com.hoolang.util.Hoolang.CreateType;
import com.opensymphony.xwork2.ActionSupport;

public class SizeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4362841298397556507L;

	private int id; // size id
	private String parent_id; // 父级id
	private String name; // size名
	private String name_ru; // size俄语名称
	private String name_en; // size英语名称

	public Size size;
	public SizeService sizeService;
	public List<Size> sizeList;

	public String save() {
		sizeService.save(size);
		return SUCCESS;
	}

	/**
	 * 获取一个产品
	 * 
	 * @return
	 */
	public String getOneSize() {
		size = sizeService.oneSize(id);

		return SUCCESS;
	}

	/**
	 * size列表
	 * 
	 * @return
	 */
	public String list() {
		sizeList = sizeService.listSize();
		return SUCCESS;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public SizeService getSizeService() {
		return sizeService;
	}

	public void setSizeService(SizeService sizeService) {
		this.sizeService = sizeService;
	}

	public List<Size> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<Size> sizeList) {
		this.sizeList = sizeList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_ru() {
		return name_ru;
	}

	public void setName_ru(String name_ru) {
		this.name_ru = name_ru;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
}
