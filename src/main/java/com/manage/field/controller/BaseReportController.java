package com.manage.field.controller;

import com.manage.field.service.BaseReportService;
import com.manage.field.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baseReport")
public class BaseReportController {

    @Autowired
    private BaseReportService baseReportService;

    /**
     * 统计表数量和字段数量
     *
     * @return Map
     */
    @GetMapping("/getTableColumnCount")
    public ResponseData getTableColumnCount() {
        return ResponseData.success(baseReportService.getTableColumnCount());
    }

    /**
     * 统计字段使用数前10
     *
     * @return ColumnReportVo
     */
    @GetMapping("/getColumnReportTop10")
    public ResponseData getColumnReportTop10() {
        return ResponseData.success(baseReportService.getColumnReportTop10());
    }
}
