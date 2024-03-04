package com.manage.field.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.field.persistents.entity.Columns;
import com.manage.field.persistents.param.ColumnsParam;
import com.manage.field.persistents.vo.ColumnsVo;
import com.manage.field.utils.PageResult;

import java.util.List;

public interface ColumnsService extends IService<Columns> {

    /**
     * 分页查询
     *
     * @param param param
     * @return PageResult
     */
    PageResult<ColumnsVo> getColumnPage(ColumnsParam param);

    /**
     * 条件查询
     *
     * @param param param
     * @return TableManage
     */
    List<Columns> getColumnList(ColumnsParam param);

    /**
     * 根据字段名称查询
     *
     * @param columnName columnName
     * @return ColumnManage
     */
    Columns getColumn(String columnName);

    /**
     * 添加
     *
     * @param entity entity
     */
    void addColumn(Columns entity);

    /**
     * 修改
     *
     * @param entity entity
     */
    void updateColumn(Columns entity);

    /**
     * 根据id删除
     *
     * @param id id
     */
    void deleteColumnById(Long id);
}
