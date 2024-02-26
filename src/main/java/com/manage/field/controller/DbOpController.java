package com.manage.field.controller;

import com.manage.field.persistents.param.CreateTableColumnParam;
import com.manage.field.persistents.param.CreateTableParam;
import com.manage.field.service.OpTableColumnService;
import com.manage.field.service.OpTableService;
import com.manage.field.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dbOp")
public class DbOpController {

    @Autowired
    private OpTableService opTableService;

    @Autowired
    private OpTableColumnService opTableColumnService;

    /**
     * 创建表
     *
     * @param param param
     * @return Boolean 建表结果
     */
    @PostMapping("/createTable")
    public ResponseData createTable(@RequestBody CreateTableParam param) {
        opTableService.createTable(param);
        return ResponseData.success();
    }

    /**
     * 创建表字段
     *
     * @param param param
     */
    @PostMapping("/createTableColumn")
    public ResponseData createTableColumn(@RequestBody CreateTableColumnParam param) {
        opTableColumnService.createTableColumn(param);
        return ResponseData.success();
    }

    /**
     * 删除表字段
     *
     * @param tableName tableName
     * @param columnName columnName
     */
    @GetMapping("/delTableColumn/{tableName}/{columnName}")
    public ResponseData delTableColumn(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName) {
        opTableColumnService.delTableColumn(tableName, columnName);
        return ResponseData.success();
    }

    /**
     * 获取ddl建表语句
     *
     * @param tableName tableName
     * @return String
     */
    @GetMapping("/getTableDdl/{tableName}")
    public ResponseData getTableDdl(@PathVariable("tableName") String tableName) {
        return ResponseData.success(opTableService.getTableDdl(tableName));
    }

    /**
     * 获取dml数据增、删、改语句
     *
     * @param tableName tableName
     * @return String
     */
    @GetMapping("/getTableDml/{tableName}")
    public ResponseData getTableDml(@PathVariable("tableName") String tableName) {
        return ResponseData.success(opTableService.getTableDml(tableName));
    }
}
