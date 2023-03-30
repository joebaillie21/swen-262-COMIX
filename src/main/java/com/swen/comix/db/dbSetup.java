package com.swen.comix.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class dbSetup {

    public static void createTable() throws Exception {

        try {

            Connection con = dbConnection.getConnection();
            PreparedStatement create = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS comics(id SERIAL PRIMARY KEY,  series_title TEXT NOT NULL, volume_number INT NOT NULL, issue_number INT NOT NULL, publication_date DATE, author TEXT, publisher_id INT, principle_character TEXT,  description TEXT, value FLOAT, grade INT)");
            create.executeUpdate();
            PreparedStatement delete = con.prepareStatement("DROP TABLE IF EXISTS comix");
            delete.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Functions complete");
        }
    }

}
