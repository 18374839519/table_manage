package com.manage.field.controller;

import com.manage.field.persistents.entity.Tables;
import com.manage.field.persistents.param.TablesParam;
import com.manage.field.service.TablesService;
import com.manage.field.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tables")
public class TablesController {

    @Autowired
    private TablesService tablesService;

    /**
     * 分页查询
     *
     * @param param param
     * @return Tables
     */
    @PostMapping("/getTablesPage")
    public ResponseData getTablesPage(@RequestBody TablesParam param) {
        return ResponseData.success(tablesService.getTablesPage(param));
    }

    /**
     * 修改表
     *
     * @param entity entity
     */
    @PostMapping("/updateTable")
    public ResponseData updateTable(@RequestBody Tables entity) {
        tablesService.updateTable(entity);
        return ResponseData.success();
    }

    /**
     * 根据ID删除
     *
     * @param id id
     */
    @GetMapping("/delTableById/{id}")
    public ResponseData delTableById(@PathVariable("id") Long id) {
        tablesService.delTableById(id);
        return ResponseData.success();
    }

    /**
     * 查询表明细
     *
     * @param tableName tableName
     * @return TableDetailVo
     */
    @GetMapping("/getTableDetail/{tableName}")
    public ResponseData getTableDetail(@PathVariable("tableName") String tableName) {
        return ResponseData.success(tablesService.getTableDetail(tableName));
    }
}
