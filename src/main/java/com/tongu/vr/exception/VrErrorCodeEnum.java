package com.tongu.vr.exception;

import lombok.Getter;

/**
 * 自定义异常编码
 * @author wangjf
 *
 */
public enum VrErrorCodeEnum{
    /** */
	FILE_EMPTY(1000001, "file can't be empty"),
	FILE_OVER_SIZE(1000002, "file size can't more than 20M"),
	FILE_UPLOAD_FAILURE(1000003, "upload file error"),
	FILE_DOWNLOAD_FAILURE(1000004, "dowload file error"),
	CREATE_QR_CODE_FAILURE(1000005, "create qr code failure"),
    ;
	
	@Getter
    private int code;
	@Getter
    private String msg;

    VrErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static VrErrorCodeEnum getEnum(int code) {
        for (VrErrorCodeEnum error : VrErrorCodeEnum.values()) {
            if (error.getCode() == code) {
                return error;
            }
        }
        return null;
    }
}

