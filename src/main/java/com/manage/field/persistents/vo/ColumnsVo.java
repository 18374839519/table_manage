package com.manage.field.persistents.vo;

import lombok.Data;

@Data
public class ColumnsVo {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段描述
     */
    private String columnDesc;

    /**
     * 状态 1：可用 2：禁用
     */
    private Integer status;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段长度 无长度为-1
     */
    private Integer columnLength;

    /**
     * 是否主键 1：是 2：否
     */
    private Integer primaryKey;

    /**
     * 类型 1：默认字段 2：自定义字段
     */
    private Integer type;
}
