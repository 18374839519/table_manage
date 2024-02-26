package com.manage.field.service;

import com.manage.field.persistents.param.CreateTableParam;

public interface OpTableService {

    /**
     * 创建表
     *
     * @param param param
     */
    void createTable(CreateTableParam param);

    /**
     * 获取ddl建表语句
     *
     * @param tableName tableName
     * @return String
     */
    String getTableDdl(String tableName);

    /**
     * 获取dml数据增、删、改语句
     *
     * @param tableName tableName
     * @return String
     */
    String getTableDml(String tableName);
}
