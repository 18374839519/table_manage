package com.manage.field.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.field.exception.BusinessException;
import com.manage.field.mapper.TablesMapper;
import com.manage.field.persistents.dto.TableDetailDto;
import com.manage.field.persistents.entity.Tables;
import com.manage.field.persistents.param.TablesParam;
import com.manage.field.persistents.vo.ColumnsVo;
import com.manage.field.persistents.vo.TableDetailVo;
import com.manage.field.service.TablesService;
import com.manage.field.utils.PageResult;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TablesServiceImpl
        extends ServiceImpl<TablesMapper, Tables>
        implements TablesService {

    @Resource
    private TablesMapper tablesMapper;

    @Override
    public PageResult<Tables> getTablesPage(TablesParam param) {
        LambdaQueryWrapper<Tables> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(param.getTableName()), Tables::getTableName, param.getTableName());
        queryWrapper.like(StringUtils.isNotBlank(param.getTableDesc()), Tables::getTableDesc, param.getTableDesc());
        queryWrapper.eq(ObjectUtils.isNotEmpty(param.getTableType()), Tables::getTableType, param.getTableType());
        queryWrapper.eq(ObjectUtils.isNotEmpty(param.getStatus()), Tables::getStatus, param.getStatus());
        queryWrapper.orderByDesc(Tables::getId);
        IPage<Tables> iPage = new Page<>(param.getPageNo(), param.getPageSize());
        IPage<Tables> page = tablesMapper.selectPage(iPage, queryWrapper);
        PageResult<Tables> pageResult = new PageResult<>();
        pageResult.setRows(page.getRecords());
        pageResult.setPageNo(param.getPageNo());
        pageResult.setPageSize(param.getPageSize());
        pageResult.setTotalPage((int) page.getPages());
        pageResult.setTotalRows((int) page.getTotal());
        return pageResult;
    }

    @Override
    public Tables getTable(String tableName) {
        if (StringUtils.isBlank(tableName)) {
            throw new BusinessException(500, "表名不能为空");
        }
        LambdaQueryWrapper<Tables> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tables::getTableName, tableName);
        queryWrapper.last("limit 1");
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public void addTable(Tables entity) {
        Tables table = getTable(entity.getTableName());
        if (table != null) {
            throw new BusinessException(500, "表已存在");
        }
        this.save(entity);
    }

    @Override
    public void updateTable(Tables entity) {
        Long id = entity.getId();
        if (id == null) {
            throw new BusinessException(500, "主键ID不能为空");
        }
        Tables tables = this.getById(id);
        if (tables == null) {
            throw new BusinessException(500, "表不存在");
        }
        this.updateById(entity);
    }

    @Override
    public void delTableById(Long id) {
        if (id == null) {
            throw new BusinessException(500, "主键ID不能为空");
        }
        this.removeById(id);
    }

    @Override
    public TableDetailVo getTableDetail(String tableName) {
        TableDetailVo detailVo = new TableDetailVo();
        List<TableDetailDto> detailList = baseMapper.getTableDetail(tableName);
        buildTableDetail(detailVo, detailList);
        return detailVo;
    }

    private void buildTableDetail(TableDetailVo detailVo, List<TableDetailDto> detailList) {
        if (CollectionUtils.isEmpty(detailList)) {
            return;
        }
        TableDetailDto tableDetailDto = detailList.stream().findFirst().orElse(null);
        if (tableDetailDto == null) {
            return;
        }
        BeanUtil.copyProperties(tableDetailDto, detailVo);
        List<ColumnsVo> columnsVoList = new ArrayList<>();
        for (TableDetailDto detailDto : detailList) {
            ColumnsVo columnsVo = new ColumnsVo();
            BeanUtil.copyProperties(detailDto, columnsVo);
            columnsVoList.add(columnsVo);
        }
        detailVo.setColumnList(columnsVoList);
    }
}
