package com.manage.field.persistents.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("columns")
public class Columns extends BaseEntity {

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
     * 字段描述
     */
    @TableField("column_desc")
    private String columnDesc;

    /**
     * 状态 1：可用 2：禁用
     */
    @TableField("status")
    private Integer status;

    /**
     * 字段类型
     */
    @TableField("column_type")
    private String columnType;

    /**
     * 字段长度 无长度为-1
     */
    @TableField("column_length")
    private Integer columnLength;

    /**
     * 是否主键 1：是 2：否
     */
    @TableField("primary_key")
    private Integer primaryKey;
}
