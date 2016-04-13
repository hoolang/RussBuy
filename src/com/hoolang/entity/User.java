package com.hoolang.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HL_USER")
public class User implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1111L;
/**
* 用户ID
用户昵称
头像URL
性别
所在地
简介 
注册时间
修改时间
*/
	private int uid;
	private String name;
	private String icon;
	private char sex;
	private String province;
	private String city;
	private String text;
	private int verified_type;
	private Date registerTime;
	private Date updateTime;
	
	public User(){}


	public User(int uid, String name, String icon, char sex, String province,
			String city, String text, int verified_type, Date registerTime,
			Date updateTime) {
		super();
		this.uid = uid;
		this.name = name;
		this.icon = icon;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.text = text;
		this.verified_type = verified_type;
		this.registerTime = registerTime;
		this.updateTime = updateTime;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public char getSex() {
		return sex;
	}


	public void setSex(char sex) {
		this.sex = sex;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public int getVerified_type() {
		return verified_type;
	}


	public void setVerified_type(int verified_type) {
		this.verified_type = verified_type;
	}


	public Date getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
