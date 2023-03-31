package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbManager {

    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(credentials.getHost(), credentials.getUsername(),
                    credentials.getPassword());

            if (connection != null) {
                return connection;
            } else {
                return null;

            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}