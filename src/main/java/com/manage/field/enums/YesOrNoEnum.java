package com.manage.field.enums;

import lombok.Getter;

@Getter
public enum YesOrNoEnum {

    Y(1, "是"),

    N(2, "否");

    private final Integer code;

    private final String desc;

    YesOrNoEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
