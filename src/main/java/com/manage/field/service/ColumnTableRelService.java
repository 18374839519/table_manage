package com.manage.field.service;

import com.manage.field.persistents.entity.ColumnTableRel;

import java.util.List;

public interface ColumnTableRelService {

    /**
     * 查询表字段映射
     *
     * @param param param
     * @return ColumnTableRel
     */
    List<ColumnTableRel> getColumnTableRelList(ColumnTableRel param);

    /**
     * 查询表字段映射
     *
     * @param tableName tableName
     * @return ColumnTableRel
     */
    List<ColumnTableRel> getColumnTableRelList(String tableName);

    /**
     * 添加
     *
     * @param entity entity
     */
    void addColumnTableRel(ColumnTableRel entity);

    /**
     * 批量保存字段表关系
     *
     * @param entityList entityList
     */
    void addColumnTableRelBatch(List<ColumnTableRel> entityList);

    /**
     * 根据id删除
     *
     * @param id id
     */
    void delColumnTableRel(Long id);

    /**
     * 删除
     *
     * @param tableName tableName
     * @param columnName columnName
     */
    void delColumnTableRel(String tableName, String columnName);
}
