package com.manage.field.persistents.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @TableField(
            fill = FieldFill.INSERT
    )
    @Excel(
            name = "创建时间",
            databaseFormat = "yyyy-MM-dd HH:mm:ss",
            format = "yyyy-MM-dd",
            width = 20.0
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;
    @TableField(
            fill = FieldFill.INSERT
    )
    private String createUser;
    @TableField(
            fill = FieldFill.UPDATE
    )
    @Excel(
            name = "更新时间",
            databaseFormat = "yyyy-MM-dd HH:mm:ss",
            format = "yyyy-MM-dd",
            width = 20.0
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updateTime;
    @TableField(
            fill = FieldFill.UPDATE
    )
    private String updateUser;

    public BaseEntity() {
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setCreateUser(final String createUser) {
        this.createUser = createUser;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateUser(final String updateUser) {
        this.updateUser = updateUser;
    }

    public String toString() {
        return "BaseEntity(createTime=" + this.getCreateTime() + ", createUser=" + this.getCreateUser() + ", updateTime="
                + this.getUpdateTime() + ", updateUser=" + this.getUpdateUser() + ")";
    }
}
