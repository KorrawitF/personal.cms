package korrawit.cms.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Database {
    private static final Logger log = LoggerFactory.getLogger(Database.class);

    private final String host;
    private final String port;
    private final String username;
    private final String password;
    private final String database;
    private final String mode;
    private final String ssl;

    private Connection conn;

    public Database(@Value("${spring.datasource.host}") String host,
            @Value("${spring.datasource.port}") String port,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.database}") String database,
            @Value("${spring.datasource.mode}") String mode,
            @Value("${spring.datasource.ssl}") String ssl) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password == null ? "" : password;
        this.database = database;

        if (mode == null || mode.isEmpty()) {
            this.mode = "disable";
        } else {
            this.mode = mode;
        }

        if (ssl == null || ssl.isEmpty()) {
            this.ssl = "false";
        } else {
            this.ssl = ssl;
        }
    }

    @PostConstruct
    public Connection getConnection() {
        if (this.conn != null) {
            return this.conn;
        }

        String url = String.format("jdbc:postgresql://%s:%s/%s", this.host, this.port, this.database);
        Properties props = new Properties();
        props.setProperty("user", this.username);
        props.setProperty("password", this.password);
        props.setProperty("ssl", this.ssl);
        props.setProperty("sslmode", this.mode);

        log.info("Connecting to database: {}:{}/{}", host, port, database);

        try {
            this.conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            log.error("Failed to connect to database: {}:{}/{}", host, port, database, e);
            throw new RuntimeException(e);
        }

        return this.conn;
    }

    @PreDestroy
    public void close() {
        if (this.conn != null) {
            try {
                log.info("Closing database connection: {}:{}/{}", host, port, database);
                this.conn.close();
            } catch (SQLException e) {
                log.error("Failed to close database connection: {}:{}/{}", host, port, database, e);
                throw new RuntimeException(e);
            } finally {
                this.conn = null;
            }
        }
    }
}
