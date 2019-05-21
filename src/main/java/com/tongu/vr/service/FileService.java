package com.tongu.vr.service;

import com.tongu.vr.model.vo.FileVO;

public interface FileService {

	/**
	 * 上传文件
	 * 
	 * @param contentType 文件类型
	 * @param fileName 文件名称
	 * @param fileBytes 文件内容
	 * @return
	 */
	public FileVO upload(String contentType, String fileName, byte[] fileBytes);
	
	/**
	 * 创建二维码
	 * 
	 * @param fileVo
	 */
	public FileVO createQr(FileVO fileVo);
}
