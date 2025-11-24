package by.step;

import java.sql.*;

public class PersonJDBCDemo {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "";

        try {
            Connection connection =
                    DriverManager.getConnection(connectionString, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from person");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1)
                        + " "
                        + resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
