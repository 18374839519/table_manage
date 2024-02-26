package com.manage.field.controller;

import com.manage.field.persistents.entity.Columns;
import com.manage.field.persistents.param.ColumnsParam;
import com.manage.field.service.ColumnsService;
import com.manage.field.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/columns")
public class ColumnsController {

    @Autowired
    private ColumnsService columnsService;

    /**
     * 分页查询
     *
     * @param param param
     * @return PageResult
     */
    @PostMapping("/getColumnPage")
    public ResponseData getColumnPage(@RequestBody ColumnsParam param) {
        return ResponseData.success(columnsService.getColumnPage(param));
    }

    /**
     * 条件查询
     *
     * @param param param
     * @return TableManage
     */
    @PostMapping("/getColumnList")
    public ResponseData getColumnList(@RequestBody ColumnsParam param) {
        return ResponseData.success(columnsService.getColumnList(param));
    }

    /**
     * 根据字段名称查询
     *
     * @param columnName columnName
     * @return ColumnManage
     */
    @PostMapping("/getColumn")
    public ResponseData getColumn(@RequestParam String columnName) {
        return ResponseData.success(columnsService.getColumn(columnName));
    }

    /**
     * 添加
     *
     * @param entity entity
     */
    @PostMapping("/addColumn")
    public ResponseData addColumn(@RequestBody Columns entity) {
        columnsService.addColumn(entity);
        return ResponseData.success();
    }

    /**
     * 修改
     *
     * @param entity entity
     */
    @PostMapping("/updateColumn")
    public ResponseData updateColumn(@RequestBody Columns entity) {
        columnsService.updateColumn(entity);
        return ResponseData.success();
    }

    /**
     * 根据id删除
     *
     * @param id id
     */
    @GetMapping("/deleteColumnById/{id}")
    public ResponseData deleteColumnById(@PathVariable("id") Long id) {
        columnsService.deleteColumnById(id);
        return ResponseData.success();
    }
}
