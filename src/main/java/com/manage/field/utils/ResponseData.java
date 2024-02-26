package com.manage.field.utils;

import lombok.Data;

@Data
public class ResponseData {

    private static final Integer DEFAULT_SUCC_CODE = 200;

    private static final String DEFAULT_SUCC_MSG = "请求成功";

    private final Integer code;

    private final String msg;

    private final Object data;

    public ResponseData(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseData success(Object data) {
        return new ResponseData(DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, data);
    }

    public static ResponseData success() {
        return new ResponseData(DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, null);
    }
}
