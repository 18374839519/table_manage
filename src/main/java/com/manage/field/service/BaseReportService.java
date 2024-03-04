package com.manage.field.service;

import com.manage.field.persistents.vo.ColumnReportVo;

import java.util.List;
import java.util.Map;

public interface BaseReportService {

    /**
     * 统计表数量和字段数量
     *
     * @return Map
     */
    Map<String, Object> getTableColumnCount();

    /**
     * 统计字段使用数前10
     *
     * @return ColumnReportVo
     */
    List<ColumnReportVo> getColumnReportTop10();
}
