package korrawit.cms.config;

import org.springframework.stereotype.Component;

@Component
public class Configuration {
    private final Database database;

    public Configuration(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }
}
