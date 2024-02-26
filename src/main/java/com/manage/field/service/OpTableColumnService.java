package com.manage.field.service;

import com.manage.field.persistents.param.CreateTableColumnParam;

public interface OpTableColumnService {

    /**
     * 创建表字段
     *
     * @param param param
     */
    void createTableColumn(CreateTableColumnParam param);

    /**
     * 删除表字段
     *
     * @param tableName tableName
     * @param columnName columnName
     */
    void delTableColumn(String tableName, String columnName);
}
