package com.tongu.vr.model.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseVO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5140713244415575899L;

	private Integer code;
	
	private String msg;
	
	T data;
	
	public BaseVO() {
		
	}
	
	public BaseVO(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
}
