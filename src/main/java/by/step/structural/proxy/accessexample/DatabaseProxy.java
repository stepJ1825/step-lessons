package by.step.structural.proxy.accessexample;

import java.util.Collections;
import java.util.List;

public class DatabaseProxy extends Database {
    private Database database;

    public DatabaseProxy(Database database) {
        this.database = database;
    }

    @Override
    public List<String> getDatabase() {
        if("ADMIN".equals(database.getUserRole())){
            return database.getDatabase();
        } else return Collections.emptyList();
    }
}
