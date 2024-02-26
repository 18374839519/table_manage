package com.manage.field.exception;

import com.manage.field.enums.AbstractBaseExceptionEnum;

public class BusinessException extends RuntimeException {

    private Integer code;
    private String errorMessage;

    public BusinessException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public BusinessException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

    public Integer getCode() {
        return this.code;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
