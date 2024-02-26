package com.manage.field.service;

import com.google.common.base.CaseFormat;
import com.manage.field.persistents.param.CreateTableParam;
import org.apache.commons.lang3.StringUtils;

public abstract class CreateJavaFilesAbstract {

    /**
     * 创建Java文件
     *
     * @param param param
     */
    public abstract void createJavaFiles(CreateTableParam param);

    public String getProjectPath() {
        // 获取项目的根路径
        return System.getProperty("user.dir").replaceAll("\\\\", "/");
    }

    public String getEntityFileName(String tableName) {
        String str = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName);
        String prefix = StringUtils.substring(str, 0, 1);
        String suffix = StringUtils.substring(str, 1);
        return prefix.toUpperCase() + suffix;
    }

    public String getMapperFileName(String tableName) {
        String fileName = getEntityFileName(tableName);
        return fileName + "Mapper";
    }

    public String getServiceFileName(String tableName) {
        String fileName = getEntityFileName(tableName);
        return fileName + "Service";
    }

    public String getServiceImplFileName(String tableName) {
        String fileName = getEntityFileName(tableName);
        return fileName + "ServiceImpl";
    }
}
