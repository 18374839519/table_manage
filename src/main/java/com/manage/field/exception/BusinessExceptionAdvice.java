package com.manage.field.exception;

import com.manage.field.utils.ResponseData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author dengdxu
 * @Datetime 2024/05/21
 */
@RestControllerAdvice
public class BusinessExceptionAdvice {

    @ExceptionHandler({BusinessException.class})
    public ResponseData handleGlobalException(BusinessException e) {
        return ResponseData.error(e.getMsg());
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseData handleException(Exception e) {
        return ResponseData.error(e.getMessage());
    }
}
