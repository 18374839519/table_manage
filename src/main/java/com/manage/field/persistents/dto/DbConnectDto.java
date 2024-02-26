package com.manage.field.persistents.dto;

import lombok.Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Data
public class DbConnectDto {

    private Connection connection;

    private Statement statement;

    private ResultSet resultSet;

    public DbConnectDto(Connection connection, Statement statement, ResultSet resultSet) {
        this.connection = connection;
        this.statement = statement;
        this.resultSet = resultSet;
    }
}
