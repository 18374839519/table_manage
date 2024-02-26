package com.manage.field.enums;

import com.manage.field.contants.BaseContent;
import lombok.Getter;

@Getter
public enum ColumnAttriTypeEnum {

    INT("int", "Integer"),

    VARCHAR("varchar", "String"),

    TEXT("text", "String"),

    DATE("timestamp", "Date"),

    BLOB("Blob", "String");

    private final String code;

    private final String value;

    ColumnAttriTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValue(String code) {
        ColumnAttriTypeEnum[] typeEnums = ColumnAttriTypeEnum.values();
        for (ColumnAttriTypeEnum typeEnum : typeEnums) {
            if (code.equals(typeEnum.getCode())) {
                return typeEnum.getValue();
            }
            if (code.contains(BaseContent.VARCHAR)) {
                return ColumnAttriTypeEnum.VARCHAR.getValue();
            }
        }
        return null;
    }
}
