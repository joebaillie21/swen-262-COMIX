package main.java.com.swen.comix.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {

    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(credentials.getHost(), credentials.getUsername(),
                    credentials.getPassword());

            if (connection != null) {
                System.out.println("Connection successful.");
                return connection;
            } else {

                System.out.println("Connection failed.");
                return null;

            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}