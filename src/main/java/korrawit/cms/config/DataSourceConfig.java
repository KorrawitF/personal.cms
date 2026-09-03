package korrawit.cms.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import korrawit.cms.error.DatabaseConnectionException;

@Configuration
public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean(destroyMethod = "close")
    DataSource dataSource(
            @Value("${spring.datasource.host}") String host,
            @Value("${spring.datasource.port}") String port,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.database}") String database,
            @Value("${spring.datasource.mode}") String mode,
            @Value("${spring.datasource.ssl}") String ssl) {

        String jdbcUrl = "jdbc:postgresql://%s:%s/%s".formatted(host, port, database);

        HikariConfig config = new HikariConfig();
        config.setPoolName("cms-pool");
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password == null ? "" : password);
        config.addDataSourceProperty("ssl", ssl == null || ssl.isEmpty() ? "false" : ssl);
        config.addDataSourceProperty("sslmode", mode == null || mode.isEmpty() ? "disable" : mode);

        log.info("Connecting to database: {}:{}/{}", host, port, database);

        try {
            return new HikariDataSource(config);
        } catch (Exception e) {
            log.error("Failed to connect to database: {}:{}/{} - {}", host, port, database, e.getMessage());
            log.debug("Connection failure detail", e);
            throw new DatabaseConnectionException(
                    "Could not connect to database %s:%s/%s - %s".formatted(host, port, database, e.getMessage()),
                    e);
        }
    }
}
