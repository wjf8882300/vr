package com.tongu.vr.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.ObjectMetadata;
import com.tongu.vr.config.OssConfig;
import com.tongu.vr.exception.VrErrorCodeEnum;
import com.tongu.vr.exception.VrException;
import com.tongu.vr.model.vo.FileVO;
import com.tongu.vr.service.FileService;
import com.tongu.vr.util.FileUtil;
import com.tongu.vr.util.QrUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private OssConfig config;

	@Override
	public FileVO upload(String contentType, String fileName, byte[] fileBytes) {	
		String dir = DateUtils.formatDate(new Date(), "yyyy/MM/dd") + "/"; 
		String filePath = dir + DateUtils.formatDate(new Date(), "HHmmss") + "_" + UUID.randomUUID() + "."
				+ FileUtil.getExt(fileName);
		
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		metadata.setContentLength(fileBytes.length);
		String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(fileBytes));
		metadata.setContentMD5(md5);

		OSSClient ossClient = new OSSClient(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
		ossClient.putObject(config.getBucket(), dir, new ByteArrayInputStream("".getBytes()));
		ossClient.putObject(config.getBucket(), filePath, new ByteArrayInputStream(fileBytes), metadata);
		ossClient.shutdown();
		
		return new FileVO(config.getDomain(), fileName, filePath);
	}

	@Override
	public FileVO createQr(FileVO fileVo) {
		try {
			// 创建二维码文件
			String qrName = UUID.randomUUID() + ".jpg";
			QrUtil.createQrCode(fileVo.getUrl(), config.getTmpPath(), qrName);
			
			// 文件上传至oss
			return upload(MediaType.IMAGE_JPEG_VALUE, qrName, FileCopyUtils.copyToByteArray(new File(config.getTmpPath() + File.separator + qrName)));
		} catch (Exception e) {
			log.error("生成二维码失败", e);
			throw new VrException(VrErrorCodeEnum.CREATE_QR_CODE_FAILURE);
		}
	}
}
