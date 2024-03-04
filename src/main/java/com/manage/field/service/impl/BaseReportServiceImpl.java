package com.manage.field.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.manage.field.mapper.ColumnsMapper;
import com.manage.field.persistents.entity.Tables;
import com.manage.field.persistents.vo.ColumnReportVo;
import com.manage.field.service.BaseReportService;
import com.manage.field.service.ColumnsService;
import com.manage.field.service.TablesService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BaseReportServiceImpl implements BaseReportService {

    @Autowired
    private TablesService tablesService;

    @Autowired
    private ColumnsService columnsService;

    @Resource
    private ColumnsMapper columnsMapper;

    @Override
    public Map<String, Object> getTableColumnCount() {
        long tableCount = tablesService.count();
        long columnCount = columnsService.count();
        Map<String, Object> map = new HashMap<>();
        map.put("tableCount", tableCount);
        map.put("columnCount", columnCount);
        return map;
    }

    @Override
    public List<ColumnReportVo> getColumnReportTop10() {
        LambdaQueryWrapper<Tables> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(Tables::getTableType, 1);
        long tableCount = tablesService.count(queryWrapper);
        List<ColumnReportVo> columnReportTop10 = columnsMapper.getColumnReportTop10();
        buildColumnReport(tableCount, columnReportTop10);
        return columnReportTop10;
    }

    private void buildColumnReport(Long tableCount, List<ColumnReportVo> columnReportTop10) {
        if (CollectionUtils.isEmpty(columnReportTop10)) {
            return;
        }
        for (ColumnReportVo reportVo : columnReportTop10) {
            Long useCount = reportVo.getUseCount();
            if (ObjectUtils.isEmpty(useCount) || ObjectUtils.isEmpty(tableCount)) {
                reportVo.setUseRate("0.00%");
                continue;
            }
            double rate = (double) useCount / tableCount;
            NumberFormat format = NumberFormat.getPercentInstance();
            format.setMinimumFractionDigits(2);
            reportVo.setUseRate(format.format(rate));
        }
    }
}
