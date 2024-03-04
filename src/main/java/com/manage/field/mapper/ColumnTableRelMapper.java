package com.manage.field.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.field.persistents.entity.ColumnTableRel;
import com.manage.field.persistents.vo.ColumnReportVo;

import java.util.List;

public interface ColumnTableRelMapper extends BaseMapper<ColumnTableRel> {

    /**
     * 统计字段使用数前10
     *
     * @return ColumnReportVo
     */
    List<ColumnReportVo> getColumnReportTop10();
}
