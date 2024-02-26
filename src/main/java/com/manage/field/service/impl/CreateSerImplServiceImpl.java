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
public class CreateSerImplServiceImpl extends CreateJavaFilesAbstract {

    @Override
    public void createJavaFiles(CreateTableParam param) {
        String path = getProjectPath();
        String tableName = param.getTableName();
        String fileName = getServiceImplFileName(tableName);
        String serviceImplPath = path + "/src/main/java/com/manage/field/service/impl/" + fileName + ".java";
        File file = new File(serviceImplPath);
        try {
            boolean result = file.createNewFile();
            if (!result) {
                log.error("Java文件创建失败");
                return;
            }
            FileWriter fileWriter = new FileWriter(file);
            buildServiceImplFile(fileWriter, tableName, fileName);
            fileWriter.close();
        } catch (Exception e) {
            log.error("Java文件创建异常：{}", e.getMessage(), e);
        }
    }

    private void buildServiceImplFile(FileWriter fileWriter, String tableName, String fileName) throws IOException {
        fileWriter.write("package com.manage.field.service.impl;\n\n");
        fileWriter.write("import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\n");
        String mapperFileName = getMapperFileName(tableName);
        fileWriter.write("import com.manage.field.mapper." + mapperFileName + ";\n");
        String entityFileName = getEntityFileName(tableName);
        fileWriter.write("import com.manage.field.persistents.entity." + entityFileName + ";\n");
        String serviceFileName = getServiceFileName(tableName);
        fileWriter.write("import com.manage.field.service." + serviceFileName + ";\n");
        fileWriter.write("import org.springframework.stereotype.Service;\n\n");
        fileWriter.write("@Service\n");
        fileWriter.write("public class " + fileName + " extends ServiceImpl<" + mapperFileName + ", " + entityFileName + "> ");
        fileWriter.write("implements " + serviceFileName + " {\n");
        fileWriter.write("}\n");
    }
}
