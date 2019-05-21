package com.tongu.vr.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * HTTP工具类
 * @author wangjf
 *
 */
public class HttpUtil {

	/**
	 * 文件下载
	 * @param ips 文件流
	 * @param fileName 下载的文件名
	 * @throws IOException
	 */
	public static void download(InputStream ips, String fileName) throws IOException {
		HttpServletResponse response = getResponse();
		try(OutputStream ouputStream = response.getOutputStream()) {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition","inline;filename=" + URLEncoder.encode(fileName,"UTF-8"));
			IOUtils.copy(ips, ouputStream);
			response.flushBuffer();
		}
	}
	
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		return response;
	}
}
