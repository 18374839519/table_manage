package com.manage.field.service.impl;

import com.manage.field.persistents.param.CreateTableParam;
import com.manage.field.service.CreateJavaFilesAbstract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
@Service
public class CreateMapperServiceImpl extends CreateJavaFilesAbstract {

    @Override
    public void createJavaFiles(CreateTableParam param) {
        String path = getProjectPath();
        String tableName = param.getTableName();
        String fileName = getMapperFileName(tableName);
        String mapperPath = path + "/src/main/java/com/manage/field/mapper/" + fileName + ".java";
        File file = new File(mapperPath);
        try {
            boolean result = file.createNewFile();
            if (!result) {
                log.error("Java文件创建失败");
                return;
            }
            FileWriter fileWriter = new FileWriter(file);
            buildMapperFile(fileWriter, tableName, fileName);
            fileWriter.close();
        } catch (Exception e) {
            log.error("Java文件创建异常：{}", e.getMessage(), e);
        }
    }

    private void buildMapperFile(FileWriter fileWriter, String tableName, String fileName) throws IOException {
        fileWriter.write("package com.manage.field.mapper;\n\n");
        fileWriter.write("import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n");
        String entityFileName = getEntityFileName(tableName);
        fileWriter.write("import com.manage.field.persistents.entity." + entityFileName + ";\n\n");
        fileWriter.write("public interface " + fileName + " extends BaseMapper<" + entityFileName + "> {\n");
        fileWriter.write("}\n");
    }
}
