package com.tongu.vr.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 * @author wangjf
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class VrException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2182599219191895632L;
	
	private Integer code;
	
	private String msg;

	public VrException(VrErrorCodeEnum error) {
		super(new StringBuilder().append(error.getCode()).append(":").append(error.getMsg()).toString());
		this.code = error.getCode();
		this.msg = error.getMsg();
	}
	
	public VrException(VrErrorCodeEnum error, String msg) {
		super(new StringBuilder().append(error.getCode()).append(":").append(msg).toString());
		this.code = error.getCode();
		this.msg = msg;
	}
	
}
