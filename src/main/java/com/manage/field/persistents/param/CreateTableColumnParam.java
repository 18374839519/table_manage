package com.manage.field.persistents.param;

import lombok.Data;

import java.util.List;

@Data
public class CreateTableColumnParam {

    private String tableName;

    private List<ColumnsParam> columnList;
}
