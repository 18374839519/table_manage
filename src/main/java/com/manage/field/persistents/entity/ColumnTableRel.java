package com.manage.field.persistents.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@TableName("column_table_rel")
@Data
public class ColumnTableRel extends BaseEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字段名称
     */
    @TableField("column_name")
    private String columnName;

    /**
     * 表名称
     */
    @TableField("table_name")
    private String tableName;
}
