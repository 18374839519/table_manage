package com.manage.field.utils;

import lombok.Data;

@Data
public class ResponseData {

    private static final Integer DEFAULT_SUCCESS_CODE = 200;

    private static final String DEFAULT_SUCCESS_MSG = "请求成功";

    private static final Integer DEFAULT_ERROR_CODE = 500;

    private static final String DEFAULT_ERROR_MSG = "系统异常";

    private final Integer code;

    private final String msg;

    private final Object data;

    public ResponseData(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseData success(Object data) {
        return new ResponseData(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG, data);
    }

    public static ResponseData success() {
        return new ResponseData(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG, null);
    }

    public static ResponseData error() {
        return new ResponseData(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG, null);
    }

    public static ResponseData error(String msg) {
        return new ResponseData(DEFAULT_ERROR_CODE, msg, null);
    }

    public static ResponseData error(int code, String msg) {
        return new ResponseData(code, msg, null);
    }
}
