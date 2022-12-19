package com.example.automobileservice.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.util.config.ConfigurationException;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class ManagedHikariDataSource extends HikariDataSource {

    @PostConstruct
    public void checkConnection() {
        try {
            log.info("Check db connection...");
            Connection connection = this.getConnection();
            connection.close();
        } catch (SQLException e) {
            log.error("Cannot connect to db: poolName = {}, url = {}, login = {}, password = {}",
                    this.getPoolName(), this.getJdbcUrl(), this.getUsername(), this.getPassword());
            throw new ConfigurationException("Fatal db connection error", e);
        }
    }
}