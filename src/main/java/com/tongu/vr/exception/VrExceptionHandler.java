package com.tongu.vr.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tongu.vr.model.vo.BaseVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常处理
 * @author wangjf
 *
 */
@RestControllerAdvice
@Slf4j(topic = "error")
public class VrExceptionHandler {

	@ExceptionHandler(value = VrException.class)
	public BaseVO<String> processMatchException(VrException ex) {
		log.warn("processMatchException {}", ex);
		return new BaseVO<String>(ex.getCode(), ex.getMessage(), null);
	}
	
	@ExceptionHandler(value = Exception.class)
	public BaseVO<String> processMatchException(Exception ex) {
		log.warn("processMatchException {}", ex);
		return new BaseVO<String>(500, ex.getMessage(), null);
	}
}
