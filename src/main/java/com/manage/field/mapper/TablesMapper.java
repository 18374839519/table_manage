package com.manage.field.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.field.persistents.dto.TableDetailDto;
import com.manage.field.persistents.entity.Tables;

import java.util.List;

public interface TablesMapper extends BaseMapper<Tables> {

    List<TableDetailDto> getTableDetail(String tableName);
}
