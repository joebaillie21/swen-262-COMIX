package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {

    public static void main(String[] args) {

        Connection connection = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(credentials.getHost(), credentials.getUsername(),
                    credentials.getPassword());

            if (connection != null) {
                System.out.println("Connection OK");
            }

            else {
                System.out.println("Connection FAILED");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}