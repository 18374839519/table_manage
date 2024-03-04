package com.manage.field.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.manage.field.contants.BaseContent;
import com.manage.field.enums.ColumnTypeEnum;
import com.manage.field.exception.BusinessException;
import com.manage.field.persistents.dto.DbConnectDto;
import com.manage.field.persistents.entity.ColumnTableRel;
import com.manage.field.persistents.entity.Tables;
import com.manage.field.persistents.param.ColumnsParam;
import com.manage.field.persistents.param.CreateTableParam;
import com.manage.field.persistents.vo.ColumnsVo;
import com.manage.field.persistents.vo.TableDetailVo;
import com.manage.field.service.ColumnTableRelService;
import com.manage.field.service.DbAbstract;
import com.manage.field.service.OpTableService;
import com.manage.field.service.TablesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OpTableServiceImpl extends DbAbstract implements OpTableService {

    @Autowired
    private TablesService tablesService;

    @Autowired
    private ColumnTableRelService columnTableRelService;

    @Autowired
    private CreateEntityServiceImpl createEntityService;

    @Autowired
    private CreateMapperServiceImpl createMapperService;

    @Autowired
    private CreateSerServiceImpl createSerService;

    @Autowired
    private CreateSerImplServiceImpl createSerImplService;

    @Override
    public void createTable(CreateTableParam param) {
        DbConnectDto connectDto = createConnect();
        Statement statement = connectDto.getStatement();
        try {
            // 构建建表语句
            String sql = buildCreateTableSql(param);
            statement.execute(sql);
            // 保存表记录
            saveTableRelInfo(param);
            if (param.getCreateJavaFiles()) {
                // 生成表对应的实体类、mapper、service、serviceImpl文件
                createEntityService.createJavaFiles(param);
                createMapperService.createJavaFiles(param);
                createSerService.createJavaFiles(param);
                createSerImplService.createJavaFiles(param);
            }
        } catch (Exception e) {
            log.error("建表异常：{}", e.getMessage(), e);
            throw new BusinessException(500, "建表异常：" + e.getMessage());
        } finally {
            closeConnect(connectDto);
        }
    }

    private String buildCreateTableSql(CreateTableParam param) {
        List<ColumnsParam> columnList = param.getColumnList();
        List<String> columnStrList = new ArrayList<>();
        for (ColumnsParam column : columnList) {
            String columnStr = column.getColumnName() + " " + buildColumnType(column);
            columnStrList.add(columnStr);
        }
        return "create table if not exists " + param.getTableName() + "(" + StringUtils.join(columnStrList, ",") + "); ";
    }

    private String buildColumnType(ColumnsParam column) {
        return ColumnTypeEnum.getValue(column.getColumnType(), column.getPrimaryKey(), column.getColumnLength());
    }

    private void saveTableRelInfo(CreateTableParam param) {
        Tables tables = new Tables();
        BeanUtil.copyProperties(param, tables);
        tablesService.addTable(tables);
        List<ColumnTableRel> tableRelList = buildColumnTableRel(param);
        columnTableRelService.addColumnTableRelBatch(tableRelList);
    }

    private List<ColumnTableRel> buildColumnTableRel(CreateTableParam param) {
        List<ColumnTableRel> tableRelList = new ArrayList<>();
        List<ColumnsParam> columnList = param.getColumnList();
        for (ColumnsParam columnsParam : columnList) {
            ColumnTableRel columnTableRel = new ColumnTableRel();
            columnTableRel.setTableName(param.getTableName());
            columnTableRel.setColumnName(columnsParam.getColumnName());
            tableRelList.add(columnTableRel);
        }
        return tableRelList;
    }

    @Override
    public String getTableDdl(String tableName) {
        TableDetailVo tableDetail = tablesService.getTableDetail(tableName);
        List<ColumnsVo> columnList = tableDetail.getColumnList();
        List<String> columnStrList = new ArrayList<>();
        for (ColumnsVo column : columnList) {
            ColumnsParam param = new ColumnsParam();
            BeanUtil.copyProperties(column, param);
            String columnStr = column.getColumnName() + " " + buildColumnType(param);
            columnStrList.add(columnStr);
        }
        return "create table if not exists " + tableName + " (\n" + StringUtils.join(columnStrList, ",\n") + "\n); ";
    }

    @Override
    public String getTableDml(String tableName) {
        TableDetailVo tableDetail = tablesService.getTableDetail(tableName);
        List<ColumnsVo> columnList = tableDetail.getColumnList();
        List<String> columnStrList = new ArrayList<>();
        List<String> valueStrList = new ArrayList<>();
        List<String> updateValStrList = new ArrayList<>();
        for (ColumnsVo column : columnList) {
            columnStrList.add(column.getColumnName());
            valueStrList.add("?");
            if (!BaseContent.ID.equals(column.getColumnName())) {
                String setStr = column.getColumnName() + " = ?";
                updateValStrList.add(setStr);
            }
        }
        List<String> dmlSqlList = new ArrayList<>();
        String insertSql = "insert into " + tableName + "\n(" + StringUtils.join(columnStrList, ",") + ")"
                + " \nvalues (" + StringUtils.join(valueStrList, ",") + ");";
        dmlSqlList.add(insertSql);
        String updateSql = "update " + tableName + " \nset " + StringUtils.join(updateValStrList, ",") + " \nwhere id = ?";
        dmlSqlList.add(updateSql);
        String delSql = "delete from " + tableName + " where id = ?";
        dmlSqlList.add(delSql);
        return StringUtils.join(dmlSqlList, "\n\n");
    }
}
