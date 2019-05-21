package com.tongu.vr.model.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class FileVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1445224461522331939L;

	private String domain;
	
	private String fileName;
	
	private String filePath;
	
	public FileVO() {
		
	}
	
	public FileVO(String domain, String fileName, String filePath) {
		this.domain = domain;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	public String getUrl() {
		return this.domain + "/" + this.filePath;
	}
}
