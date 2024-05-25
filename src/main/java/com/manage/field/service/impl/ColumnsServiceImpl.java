package com.manage.field.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.field.exception.BusinessException;
import com.manage.field.mapper.ColumnsMapper;
import com.manage.field.persistents.entity.ColumnTableRel;
import com.manage.field.persistents.entity.Columns;
import com.manage.field.persistents.param.ColumnsParam;
import com.manage.field.persistents.vo.ColumnsVo;
import com.manage.field.service.ColumnTableRelService;
import com.manage.field.service.ColumnsService;
import com.manage.field.utils.PageResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColumnsServiceImpl
        extends ServiceImpl<ColumnsMapper, Columns>
        implements ColumnsService {

    @Resource
    private ColumnsMapper columnsMapper;

    @Autowired
    private ColumnTableRelService columnTableRelService;

    @Override
    public PageResult<ColumnsVo> getColumnPage(ColumnsParam param) {
        LambdaQueryWrapper<Columns> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(param.getColumnName()), Columns::getColumnName, param.getColumnName());
        queryWrapper.like(StringUtils.isNotBlank(param.getColumnDesc()), Columns::getColumnDesc, param.getColumnDesc());
        queryWrapper.orderByAsc(Columns::getId);
        IPage<Columns> iPage = new Page<>(param.getPageNo(), param.getPageSize());
        IPage<Columns> page = columnsMapper.selectPage(iPage, queryWrapper);
        List<ColumnsVo> pageList = buildPageVo(page.getRecords());
        PageResult<ColumnsVo> pageResult = new PageResult<>();
        pageResult.setRows(pageList);
        pageResult.setPageNo(param.getPageNo());
        pageResult.setPageSize(param.getPageSize());
        pageResult.setTotalPage((int) page.getPages());
        pageResult.setTotalRows((int) page.getTotal());
        return pageResult;
    }

    private List<ColumnsVo> buildPageVo(List<Columns> records) {
        List<ColumnsVo> voList = new ArrayList<>();
        if (CollectionUtils.isEmpty(records)) {
            return voList;
        }
        for (Columns record : records) {
            ColumnsVo vo = new ColumnsVo();
            BeanUtil.copyProperties(record, vo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<Columns> getColumnList(ColumnsParam param) {
        LambdaQueryWrapper<Columns> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(param.getColumnName()), Columns::getColumnName, param.getColumnName());
        queryWrapper.eq(StringUtils.isNotBlank(param.getColumnDesc()), Columns::getColumnDesc, param.getColumnDesc());
        return this.list(queryWrapper);
    }

    @Override
    public Columns getColumn(String columnName) {
        if (StringUtils.isBlank(columnName)) {
            throw new BusinessException(500, "字段名称不能为空");
        }
        LambdaQueryWrapper<Columns> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Columns::getColumnName, columnName);
        queryWrapper.last("limit 1");
        return this.getOne(queryWrapper);
    }

    @Override
    public void addColumn(Columns entity) {
        Columns columns = getColumn(entity.getColumnName());
        if (columns != null) {
            throw new BusinessException(500, "字段已存在");
        }
        this.save(entity);
    }

    @Override
    public void updateColumn(Columns entity) {
        Long id = entity.getId();
        if (id == null) {
            throw new BusinessException(500, "主键ID不能为空");
        }
        Columns columns = this.baseMapper.selectById(id);
        if (columns == null) {
            throw new BusinessException(500, "字段不存在");
        }
        Columns column = getColumn(entity.getColumnName());
        if (column != null && !id.equals(column.getId())) {
            throw new BusinessException(500, "字段已存在");
        }
        this.baseMapper.updateById(entity);
    }

    @Override
    public void deleteColumnById(Long id) {
        if (id == null) {
            throw new BusinessException(500, "主键ID不能为空");
        }
        Columns columns = this.baseMapper.selectById(id);
        if (columns == null) {
            throw new BusinessException(500, "字段不存在");
        }
        ColumnTableRel param = new ColumnTableRel();
        param.setColumnName(columns.getColumnName());
        List<ColumnTableRel> columnTableRelList = columnTableRelService.getColumnTableRelList(param);
        if (CollectionUtils.isNotEmpty(columnTableRelList)) {
            List<String> tableList = columnTableRelList.stream().map(ColumnTableRel::getTableName).distinct()
                    .collect(Collectors.toList());
            throw new BusinessException(500, "字段已被表【" + StringUtils.join(tableList, "，") + "】使用，不可删除");
        }
        this.removeById(id);
    }
}
