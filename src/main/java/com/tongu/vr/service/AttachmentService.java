package com.tongu.vr.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tongu.vr.model.vo.FileVO;

public interface AttachmentService {

	public FileVO save(MultipartFile file);
	
	public List<FileVO> queryList();
}
