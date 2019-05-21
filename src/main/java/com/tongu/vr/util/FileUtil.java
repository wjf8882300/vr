package com.tongu.vr.util;

/**
 * 文件工具类
 * @author wangjf
 *
 */
public class FileUtil {
	
	/**
	 * 获取文件后缀名（不含.）
	 * @param fileName 文件名称
	 * @return
	 */
	public static String getExt(String fileName) {
		String[] names = fileName.split("\\.");
		if(names == null || names.length != 2) {
			return fileName;
		}
		return names[1];
	}
}
