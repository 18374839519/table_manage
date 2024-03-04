package com.manage.field.persistents.vo;

import lombok.Data;

@Data
public class ColumnReportVo {

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
     * 使用次数
     */
    private Long useCount;

    /**
     * 使用率
     */
    private String useRate;
}
