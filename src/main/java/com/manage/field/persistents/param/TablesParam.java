package com.manage.field.persistents.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TablesParam extends BaseParam {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableDesc;

    /**
     * 表类型 1：系统表 2：业务表
     */
    private Integer tableType;

    /**
     * 状态 1：可用 2：禁用
     */
    private Integer status;
}
