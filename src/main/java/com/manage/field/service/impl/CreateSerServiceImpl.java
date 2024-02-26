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
public class CreateSerServiceImpl extends CreateJavaFilesAbstract {

    @Override
    public void createJavaFiles(CreateTableParam param) {
        String path = getProjectPath();
        String tableName = param.getTableName();
        String fileName = getServiceFileName(tableName);
        String servicePath = path + "/src/main/java/com/manage/field/service/" + fileName + ".java";
        File file = new File(servicePath);
        try {
            boolean result = file.createNewFile();
            if (!result) {
                log.error("Java文件创建失败");
                return;
            }
            FileWriter fileWriter = new FileWriter(file);
            buildServiceFile(fileWriter, fileName);
            fileWriter.close();
        } catch (Exception e) {
            log.error("Java文件创建异常：{}", e.getMessage(), e);
        }
    }

    private void buildServiceFile(FileWriter fileWriter, String fileName) throws IOException {
        fileWriter.write("package com.manage.field.service;\n\n");
        fileWriter.write("public interface " + fileName + " {\n");
        fileWriter.write("}\n");
    }
}
