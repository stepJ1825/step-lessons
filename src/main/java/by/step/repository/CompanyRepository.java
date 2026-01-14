package by.step.repository;

import by.step.pool.ConnectionPool;

public class CompanyRepository {
    private final ConnectionPool connectionPool;

    public CompanyRepository(ConnectionPool connectionPool) {
        System.err.println("--- CompanyRepository constructor ---");
        this.connectionPool = connectionPool;
    }

    public static CompanyRepository of(ConnectionPool connectionPool) {
        System.err.println("--- CompanyRepository factory method ---");
        return new CompanyRepository(connectionPool);
    }
}
