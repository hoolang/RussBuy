package com.hoolang.action;

import java.io.IOException;

import com.hoolang.util.Hoolang;
import com.hoolang.util.ImageMarkLogoUtil;
import com.hoolang.util.JsonTool;
import com.opensymphony.xwork2.ActionSupport;

public class ImageAction extends ActionSupport{

	private static final long serialVersionUID = -2997147898505119808L;
	
	public String image;

	public String productCreateMark() throws IOException{
		System.out.println("imaeg:====>" + image);
		String mark = Hoolang.ROOT +"images/" + image + ".mark.jpg";
		String source = Hoolang.ROOT +"images/" + image;
		String icon = Hoolang.ROOT +"images/new.png";
		
		ImageMarkLogoUtil.setImageMarkOptions(1, 10, 10, null, null);
		ImageMarkLogoUtil.markImageByIcon(icon, source, mark);
		JsonTool.success();
		return null;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
