package com.hoolang.action;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.hoolang.entity.User;
import com.hoolang.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class UserAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserService userService;
	private User user;
	private int u_id;
	private String name;
	private String icon;
	private char sex;
	private String province;
	private String city;
	private String text;
	private int verified_type;
	private Date registerTime;
	private Date updateTime;
	
	//代表上传文件的file对象     
    private File file;
    //上传文件名     
    private String fileFileName;
    //上传文件的MIME类型     
    private String fileContentType;
    //保存上传文件的目录  
    private String uploadDir = "/icons";
    //新文件名称  
    private String newFileName = null;
    //原文件名  
    private String fileString;
	

	public String save() throws Exception{

		//user.setU_register(new Date());
		//user.setU_update(user.getU_register());
		upload();
		Date date = new Date();
		System.out.print("-------"+ user.getCity() + user.getText());
		user.setRegisterTime(date);
		user.setUpdateTime(date);
		user.setIcon(this.getNewFileName());
		
		userService.save(user);
		return SUCCESS;
	}
	
	public void upload() throws IOException{
        // 得到当前时间自1970年1月1日0时0分0秒开始流逝的毫秒数，将这个毫秒数作为上传文件新的文件名。  
        long now = new Date().getTime();
        // 得到保存上传文件的目录的真实路径 
        String path = ServletActionContext.getServletContext().getRealPath(uploadDir);
        File dir = new File(path);
        // 如果这个目录不存在，则创建它。
        if (!dir.exists()) 
            dir.mkdir();  
        int index = fileFileName.lastIndexOf('.'); 
        // 判断上传文件名是否有扩展名
        if (index != -1)
            newFileName = now + fileFileName.substring(index);  
        else  
            newFileName = Long.toString(now);  
        BufferedOutputStream bos = null;  
        BufferedInputStream bis = null;  
        // 读取保存在临时目录下的上传文件，写入到新的文件中。  
        try {  
            FileInputStream fis = new FileInputStream(file);  
            bis = new BufferedInputStream(fis);  
            FileOutputStream fos = new FileOutputStream(new File(dir,newFileName));  
            bos = new BufferedOutputStream(fos);  
            byte[] buf = new byte[4096];  
            int len = -1;  
            while ((len = bis.read(buf)) != -1) {  
                bos.write(buf, 0, len);  
            }  
        } finally {  
            try {  
                if (null != bis)  
                    bis.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                if (null != bos)  
                    bos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    } 
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getFileString() {
		return fileString;
	}

	public void setFileString(String fileString) {
		this.fileString = fileString;
	}

	public int getVerified_type() {
		return verified_type;
	}

	public void setVerified_type(int verified_type) {
		this.verified_type = verified_type;
	}

	
}
