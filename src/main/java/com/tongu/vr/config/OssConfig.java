package com.tongu.vr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 阿里云OSS配置
 * @author wangjf
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "vr.oss")
public class OssConfig {

	private String endpoint;
	private String bucket;
	private String accessKeyId;
	private String accessKeySecret;
	private Long maxSize;
	private String domain;
	private String tmpPath;
}
