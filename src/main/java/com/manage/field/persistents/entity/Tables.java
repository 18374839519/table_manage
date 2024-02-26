package com.manage.field.persistents.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@TableName("tables")
@Data
public class Tables extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 表名称
     */
    @TableField("table_name")
    private String tableName;

    /**
     * 表描述
     */
    @TableField("table_desc")
    private String tableDesc;

    /**
     * 表类型 1：系统表 2：业务表
     */
    @TableField("table_type")
    private Integer tableType;

    /**
     * 状态 1：可用 2：禁用
     */
    @TableField("status")
    private Integer status;
}
