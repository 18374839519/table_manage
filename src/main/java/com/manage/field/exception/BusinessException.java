package com.manage.field.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 全局异常处理
 *
 * @author dengdxu
 * @Datetime 2024/05/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    /**
     * 编码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    private static final Integer DEFAULT_ERROR_CODE = 500;

    private static final String DEFAULT_ERROR_MSG = "系统异常，请联系管理员";

    public BusinessException() {
        this.code = DEFAULT_ERROR_CODE;
        this.msg = DEFAULT_ERROR_MSG;
    }

    public BusinessException(String msg) {
        this.code = DEFAULT_ERROR_CODE;
        this.msg = msg;
    }

    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
