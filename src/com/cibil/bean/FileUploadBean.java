package com.cibil.bean;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadBean extends BaseBean {
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}
