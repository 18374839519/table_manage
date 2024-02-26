package com.manage.field.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "op.db")
@PropertySource(value = "db.properties")
@Data
public class DbProperties {

    private String driver;

    private String url;

    private String user;

    private String password;

    private String type;
}
