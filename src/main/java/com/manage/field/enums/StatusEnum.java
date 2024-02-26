package com.manage.field.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    ENABLE(1, "可用"),

    DISABLE(2, "禁用"),

    DELETE(3, "删除");

    private final Integer code;

    private final String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
