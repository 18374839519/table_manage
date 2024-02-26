package com.manage.field.persistents.dto;

import com.manage.field.persistents.vo.ColumnsVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TableDetailDto extends ColumnsVo {

    private String tableName;

    private String tableDesc;

    private Integer tableType;

    private Integer status;
}
