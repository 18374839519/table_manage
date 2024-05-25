package com.manage.field.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.field.exception.BusinessException;
import com.manage.field.mapper.ColumnTableRelMapper;
import com.manage.field.persistents.entity.ColumnTableRel;
import com.manage.field.service.ColumnTableRelService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColumnTableRelServiceImpl
        extends ServiceImpl<ColumnTableRelMapper, ColumnTableRel>
        implements ColumnTableRelService {

    @Override
    public List<ColumnTableRel> getColumnTableRelList(ColumnTableRel param) {
        LambdaQueryWrapper<ColumnTableRel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(param.getColumnName()), ColumnTableRel::getColumnName, param.getColumnName());
        queryWrapper.eq(StringUtils.isNotBlank(param.getTableName()), ColumnTableRel::getTableName, param.getTableName());
        return this.list(queryWrapper);
    }

    @Override
    public List<ColumnTableRel> getColumnTableRelList(String tableName) {
        if (StringUtils.isBlank(tableName)) {
            throw new BusinessException(500, "表名称不能为空");
        }
        LambdaQueryWrapper<ColumnTableRel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ColumnTableRel::getTableName, tableName);
        return this.list(queryWrapper);
    }

    @Override
    public void addColumnTableRel(ColumnTableRel entity) {
        List<ColumnTableRel> columnTableRelList = getColumnTableRelList(entity);
        if (CollectionUtils.isNotEmpty(columnTableRelList)) {
            return;
        }
        this.save(entity);
    }

    @Override
    public void addColumnTableRelBatch(List<ColumnTableRel> entityList) {
        List<String> columnList = entityList.stream().map(ColumnTableRel::getColumnName).distinct().collect(Collectors.toList());
        List<String> tableList = entityList.stream().map(ColumnTableRel::getTableName).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(columnList) || CollectionUtils.isEmpty(tableList)) {
            return;
        }
        LambdaQueryWrapper<ColumnTableRel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ColumnTableRel::getColumnName, columnList);
        queryWrapper.in(ColumnTableRel::getTableName, tableList);
        List<ColumnTableRel> tableRelList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(tableRelList)) {
            throw new BusinessException(500, "字段与表数据关系已存在");
        }
        this.saveBatch(entityList);
    }

    @Override
    public void delColumnTableRel(Long id) {
        if (id == null) {
            throw new BusinessException(500, "主键ID不能为空");
        }
        this.removeById(id);
    }

    @Override
    public void delColumnTableRel(String tableName, String columnName) {
        if (StringUtils.isBlank(tableName) || StringUtils.isBlank(columnName)) {
            throw new BusinessException(500, "表名称或字段名称不能为空");
        }
        ColumnTableRel param = new ColumnTableRel();
        param.setTableName(tableName);
        param.setColumnName(columnName);
        List<ColumnTableRel> columnTableRelList = getColumnTableRelList(param);
        if (CollectionUtils.isNotEmpty(columnTableRelList)) {
            List<Long> idList = columnTableRelList.stream().map(ColumnTableRel::getId).distinct().collect(Collectors.toList());
            this.removeBatchByIds(idList);
        }
    }
}
