package com.hoolang.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HL_SIZE")
public class Size implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6040770299180834648L;
	private int id; 			// size id
	private int parent_id; 	// 父级id
	private String name;		//size名
	private String name_ru;		//size俄语名称
	private String name_en;		// size英语名称
	
	public Size(){};

	public Size(int id, int parent_id, String name, String name_ru, String name_en) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.name = name;
		this.name_ru = name_ru;
		this.name_en = name_en;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id = id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
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
