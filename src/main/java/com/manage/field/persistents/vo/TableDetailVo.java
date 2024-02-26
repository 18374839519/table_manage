package com.manage.field.persistents.vo;

import lombok.Data;

import java.util.List;

@Data
public class TableDetailVo {

    private String tableName;

    private String tableDesc;

    private Integer tableType;

    private Integer status;

    private List<ColumnsVo> columnList;
}
