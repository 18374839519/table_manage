package com.manage.field.persistents.param;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class BaseParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer pageNo = 1;

    private Integer pageSize = 10;
}
