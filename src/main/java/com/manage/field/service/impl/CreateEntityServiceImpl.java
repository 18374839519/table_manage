package com.manage.field.service.impl;

import com.google.common.base.CaseFormat;
import com.manage.field.contants.BaseContent;
import com.manage.field.enums.ColumnAttriTypeEnum;
import com.manage.field.enums.ColumnTypeEnum;
import com.manage.field.persistents.param.ColumnsParam;
import com.manage.field.persistents.param.CreateTableParam;
import com.manage.field.service.CreateJavaFilesAbstract;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class CreateEntityServiceImpl extends CreateJavaFilesAbstract {

    @Override
    public void createJavaFiles(CreateTableParam param) {
        String path = getProjectPath();
        String fileName = getEntityFileName(param.getTableName());
        String entityPath = path + "/src/main/java/com/manage/field/persistents/entity/" + fileName + ".java";
        File file = new File(entityPath);
        try {
            boolean result = file.createNewFile();
            if (!result) {
                log.error("Java文件创建失败");
                return;
            }
            FileWriter fileWriter = new FileWriter(file);
            buildEntityFile(fileWriter, param, fileName);
            fileWriter.close();
        } catch (Exception e) {
            log.error("Java文件创建异常：{}", e.getMessage(), e);
        }
    }

    private void buildEntityFile(FileWriter fileWriter, CreateTableParam param, String fileName) throws IOException {
        fileWriter.write("package com.manage.field.persistents.entity;\n\n");
        fileWriter.write("import com.baomidou.mybatisplus.annotation.IdType;\n");
        fileWriter.write("import com.baomidou.mybatisplus.annotation.TableField;\n");
        fileWriter.write("import com.baomidou.mybatisplus.annotation.TableId;\n");
        fileWriter.write("import com.baomidou.mybatisplus.annotation.TableName;\n");
        fileWriter.write("import lombok.Data;\n");
        fileWriter.write("import lombok.EqualsAndHashCode;\n\n");
        fileWriter.write("@EqualsAndHashCode(callSuper = true)\n");
        fileWriter.write("@Data\n");
        fileWriter.write("@TableName(\"" + param.getTableName() + "\")\n");
        fileWriter.write("public class " + fileName + " extends BaseEntity {\n\n");
        buildEntityBody(fileWriter, param.getColumnList());
        fileWriter.write("}");
    }

    private void buildEntityBody(FileWriter fileWriter, List<ColumnsParam> columnList) throws IOException {
        if (CollectionUtils.isEmpty(columnList)) {
            return;
        }
        for (ColumnsParam param : columnList) {
            if (BaseContent.ID.equals(param.getColumnName())) {
                buildPrimaryKey(fileWriter);
            } else {
                buildColumnDesc(fileWriter, param.getColumnDesc());
                fileWriter.write("\t@TableField(\"" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, param.getColumnName()) + "\")\n");
                String code = ColumnTypeEnum.getValue(param.getColumnType(), param.getPrimaryKey(), param.getColumnLength());
                String attriName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param.getColumnName());
                fileWriter.write("\tprivate " + ColumnAttriTypeEnum.getValue(code) + " " + attriName + ";\n\n");
            }
        }
    }

    private void buildPrimaryKey(FileWriter fileWriter) throws IOException {
        buildColumnDesc(fileWriter, "主键ID");
        fileWriter.write("\t@TableId(type = IdType.ASSIGN_ID)\n");
        fileWriter.write("\tprivate Long id;\n\n");
    }

    private void buildColumnDesc(FileWriter fileWriter, String columnDesc) throws IOException {
        fileWriter.write("\t/**\n");
        fileWriter.write("\t * " + columnDesc + "\n");
        fileWriter.write("\t */\n");
    }
}
