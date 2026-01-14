package by.step.pool;

import lombok.Setter;

public class Driver {
    @Setter
    private ConnectionPool connectionPool;

    public Driver() {
    }

    public Driver(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


}
