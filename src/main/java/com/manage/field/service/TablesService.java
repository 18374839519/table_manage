package com.manage.field.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.field.persistents.entity.Tables;
import com.manage.field.persistents.param.TablesParam;
import com.manage.field.persistents.vo.TableDetailVo;
import com.manage.field.utils.PageResult;

public interface TablesService extends IService<Tables> {

    /**
     * 分页查询
     *
     * @param param param
     * @return Tables
     */
    PageResult<Tables> getTablesPage(TablesParam param);

    /**
     * 根据表名称查询
     *
     * @param tableName tableName
     * @return Tables
     */
    Tables getTable(String tableName);

    /**
     * 添加表
     *
     * @param entity entity
     */
    void addTable(Tables entity);

    /**
     * 修改表
     *
     * @param entity entity
     */
    void updateTable(Tables entity);

    /**
     * 根据ID删除
     *
     * @param id id
     */
    void delTableById(Long id);

    /**
     * 查询表明细
     *
     * @param tableName tableName
     * @return TableDetailVo
     */
    TableDetailVo getTableDetail(String tableName);
}
