package com.manage.field.persistents.param;

import com.manage.field.persistents.entity.Tables;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateTableParam extends Tables {

    private List<ColumnsParam> columnList;
}
