package com.tongu.vr.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tongu.vr.config.OssConfig;
import com.tongu.vr.exception.VrErrorCodeEnum;
import com.tongu.vr.exception.VrException;
import com.tongu.vr.model.entity.Attachment;
import com.tongu.vr.model.vo.FileVO;
import com.tongu.vr.repository.AttachmentRepository;
import com.tongu.vr.service.AttachmentService;
import com.tongu.vr.service.FileService;
import com.tongu.vr.util.IDUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private OssConfig config;
	
	@Override
	public FileVO save(MultipartFile file) {
		try {
			if(file.isEmpty()) {
				throw new VrException(VrErrorCodeEnum.FILE_EMPTY);
			}
			
			if(file.getSize() > config.getMaxSize()*1024*1024) {
				throw new VrException(VrErrorCodeEnum.FILE_OVER_SIZE, String.format("file size can't more than %dM", config.getMaxSize()));
			}
			
			// 1) 计算文件hash值
			String hash = DigestUtils.md5Hex(file.getBytes());
			
			// 2) 判断文件是否已经存在，若存在则直接返回二维码地址
			Attachment attachment = attachmentRepository.findByAttachmentSign(hash);
			if(attachment != null) {
				return new FileVO(config.getDomain(), attachment.getAttachmentName(), attachment.getQrCodePath());
			}
			
			// 3) 文件不存在，则上传文件
			FileVO fileVo = fileService.upload(file.getContentType(), file.getOriginalFilename(), file.getBytes());
			
			// 4) 生成二维码 
			//    此处生成二维码图片并把图片保存到oss，返回oss的二维码路径
			FileVO qrVo = fileService.createQr(fileVo);
			
			// 5) 把文件、二维码信息保存到数据库
			attachment = new Attachment();
			attachment.setId(IDUtil.nextId());
			attachment.setAttachmentName(fileVo.getFileName());
			attachment.setAttachmentPath(fileVo.getFilePath());
			attachment.setAttachmentSize((int)file.getSize());
			attachment.setAttachmentSign(hash);
			attachment.setQrCodePath(qrVo.getFilePath());
			attachment.setCreateDate(new Date());
			attachment.setLastUpdateDate(new Date());
			attachment.setVersion(0);
			attachment.setIsDelete("0");
			attachmentRepository.save(attachment);
			
			return new FileVO(config.getDomain(), attachment.getAttachmentName(), attachment.getQrCodePath());
		} catch (VrException e) {
			log.error("上传文件失败!", e);
			throw e;
		} catch (Exception e) {
			log.error("上传文件失败!", e);
			throw new VrException(VrErrorCodeEnum.FILE_UPLOAD_FAILURE);
		}
	}

	@Override
	public List<FileVO> queryList() {
		List<Attachment> attachmentList = attachmentRepository.findAll();
		return attachmentList.stream().map(a->{return new FileVO(config.getDomain(), a.getAttachmentName(), a.getQrCodePath());}).collect(Collectors.toList());
	}

}
