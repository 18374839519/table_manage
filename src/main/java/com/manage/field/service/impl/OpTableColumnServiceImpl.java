package com.manage.field.service.impl;

import com.manage.field.enums.ColumnTypeEnum;
import com.manage.field.exception.BusinessException;
import com.manage.field.persistents.dto.DbConnectDto;
import com.manage.field.persistents.entity.ColumnTableRel;
import com.manage.field.persistents.param.ColumnsParam;
import com.manage.field.persistents.param.CreateTableColumnParam;
import com.manage.field.service.ColumnTableRelService;
import com.manage.field.service.OpTableColumnService;
import com.manage.field.service.DbAbstract;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OpTableColumnServiceImpl extends DbAbstract implements OpTableColumnService {

    @Autowired
    private ColumnTableRelService columnTableRelService;

    @Override
    public void createTableColumn(CreateTableColumnParam param) {
        DbConnectDto connectDto = createConnect();
        Statement statement = connectDto.getStatement();
        try {
            String tableName = param.getTableName();
            List<ColumnsParam> columnList = buildColumnList(param);
            // 构建添加表字段语句
            String sql = buildCreateTableColumnSql(tableName, columnList);
            if (sql == null) {
                return;
            }
            statement.execute(sql);
            saveTableRelInfo(tableName, columnList);
        } catch (Exception e) {
            log.error("建表异常：{}", e.getMessage(), e);
            throw new BusinessException(500, "建表异常：" + e.getMessage());
        } finally {
            closeConnect(connectDto);
        }
    }

    private List<ColumnsParam> buildColumnList(CreateTableColumnParam param) {
        String tableName = param.getTableName();
        List<ColumnTableRel> tableRelList = columnTableRelService.getColumnTableRelList(tableName);
        List<String> existColumnList = tableRelList.stream().map(ColumnTableRel::getColumnName).distinct().collect(Collectors.toList());
        List<ColumnsParam> columnList = param.getColumnList();
        if (CollectionUtils.isNotEmpty(existColumnList)) {
            columnList = columnList.stream().filter(x -> !existColumnList.contains(x.getColumnName())).collect(Collectors.toList());
        }
        if (CollectionUtils.isNotEmpty(columnList)) {
            columnList = columnList.stream().distinct().collect(Collectors.toList());
        }
        return columnList;
    }

    private String buildCreateTableColumnSql(String tableName, List<ColumnsParam> columnList) {
        if (CollectionUtils.isEmpty(columnList)) {
            return null;
        }
        List<String> columnStrList = new ArrayList<>();
        for (ColumnsParam column : columnList) {
            String columnStr = column.getColumnName() + " " + buildColumnType(column);
            columnStrList.add(columnStr);
        }
        return "alter table " + tableName + " add (" + StringUtils.join(columnStrList, ",") + "); ";
    }

    private String buildColumnType(ColumnsParam column) {
        return ColumnTypeEnum.getValue(column.getColumnType(), column.getPrimaryKey(), column.getColumnLength());
    }

    private void saveTableRelInfo(String tableName, List<ColumnsParam> columnList) {
        List<ColumnTableRel> tableRelList = buildColumnTableRel(tableName, columnList);
        columnTableRelService.addColumnTableRelBatch(tableRelList);
    }

    private List<ColumnTableRel> buildColumnTableRel(String tableName, List<ColumnsParam> columnList) {
        List<ColumnTableRel> tableRelList = new ArrayList<>();
        for (ColumnsParam columnsParam : columnList) {
            ColumnTableRel columnTableRel = new ColumnTableRel();
            columnTableRel.setTableName(tableName);
            columnTableRel.setColumnName(columnsParam.getColumnName());
            tableRelList.add(columnTableRel);
        }
        return tableRelList;
    }

    @Override
    public void delTableColumn(String tableName, String columnName) {
        DbConnectDto connectDto = createConnect();
        Statement statement = connectDto.getStatement();
        try {
            String sql = "alter table " + tableName + " drop column " + columnName + "; ";
            statement.execute(sql);
            //  删除表关系数据
            columnTableRelService.delColumnTableRel(tableName, columnName);
        } catch (Exception e) {
            log.error("删除表字段异常：{}", e.getMessage(), e);
            throw new BusinessException(500, "删除表字段异常：" + e.getMessage());
        } finally {
            closeConnect(connectDto);
        }
    }
}
