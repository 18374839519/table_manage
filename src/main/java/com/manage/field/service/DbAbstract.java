package com.manage.field.service;

import com.manage.field.config.DbProperties;
import com.manage.field.exception.BusinessException;
import com.manage.field.persistents.dto.DbConnectDto;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Slf4j
public abstract class DbAbstract {

    @Resource
    private DbProperties dbProperties;

    public DbConnectDto createConnect() {
        Connection connection;
        Statement statement;
        try {
            Class.forName(dbProperties.getDriver());
            connection = DriverManager.getConnection(dbProperties.getUrl(), dbProperties.getUser(), dbProperties.getPassword());
            statement = connection.createStatement();
        } catch (Exception e) {
            log.error("数据库连接异常：{}", e.getMessage(), e);
            throw new BusinessException("数据库连接异常：" + e.getMessage());
        }
        return new DbConnectDto(connection, statement, null);
    }

    public void closeConnect(DbConnectDto dto) {
        try {
            if (dto == null) {
                log.error("数据库连接为空");
                throw new BusinessException("数据库连接为空");
            }
            ResultSet resultSet = dto.getResultSet();
            if (resultSet != null) {
                resultSet.close();
            }
            Statement statement = dto.getStatement();
            if (statement != null) {
                statement.close();
            }
            Connection connection = dto.getConnection();
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            log.error("数据库连接关闭异常：{}", e.getMessage(), e);
            throw new BusinessException(500, "数据库连接关闭异常：" + e.getMessage());
        }
    }
}
