package com.manage.field.enums;

import lombok.Getter;

@Getter
public enum ColumnTypeEnum {

    BIG_INT("Number", 1, -1, "bigint primary key"),

    INT("Number", 2, -1, "int"),

    VARCHAR("String", 2, 0, "varchar"),

    TEXT("String", 2, -1, "text"),

    DATE("Date", 2, -1, "timestamp"),

    BLOB("Blob", 2, -1, "Blob")

    ;

    private final String code;

    private final Integer primaryKey;

    private final Integer columnLength;

    private final String value;

    ColumnTypeEnum(String code, Integer primaryKey, Integer columnLength, String value) {
        this.code = code;
        this.primaryKey = primaryKey;
        this.columnLength = columnLength;
        this.value = value;
    }

    public static String getValue(String code, Integer primaryKey, Integer columnLength) {
        int length = columnLength.equals(-1) ? -1 : 0;
        ColumnTypeEnum[] values = ColumnTypeEnum.values();
        for (ColumnTypeEnum typeEnum : values) {
            if (typeEnum.getCode().equals(code) && typeEnum.getPrimaryKey().equals(primaryKey)
                    && typeEnum.getColumnLength().equals(length)) {
                String typeValue = typeEnum.getValue();
                if (length == 0) {
                    typeValue = typeValue +  "(" + columnLength + ")";
                }
                return typeValue;
            }
        }
        return null;
    }
}
