package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;

// Managing class for database actions

public class dbManager {

    // Connect to the database
    // return Connection connection

    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(credentials.getHost(), credentials.getUsername(),
                    credentials.getPassword());

            if (connection != null) {
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

    public static void execDelete(String delete) throws Exception {

        Connection con = null;
        PreparedStatement stmt = null;

        try {

            con = getConnection();
            stmt = con.prepareStatement(delete);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // Execute an SQL update
    // @param sql statement

    public static void execUpdate(String update) throws Exception {

        Connection con = null;
        PreparedStatement stmt = null;

        try {

            con = getConnection();

            // Cleanse for sql injection
            stmt = con.prepareStatement(update);
            stmt.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ResultSet execQuery(String query) throws Exception {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {

            con = getConnection();

            // Cleanse for sql injection
            stmt = con.prepareStatement(query);
            res = stmt.getResultSet();

        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    public static void createComixTable() throws Exception {
        String query = """
                DROP TABLE IF EXISTS comics;
                DROP TABLE IF EXISTS comix;

                CREATE TABLE comics(
                    id SERIAL PRIMARY KEY,
                    series_title TEXT NOT NULL,
                    volume_number INT NOT NULL,
                    issue_number INT NOT NULL,
                    publication_date DATE,
                    author TEXT,
                    publisher_id INT,
                    principle_character TEXT,
                    description TEXT,
                    value FLOAT,
                    grade INT
                    );
                """;
        execUpdate(query);
    }

}
