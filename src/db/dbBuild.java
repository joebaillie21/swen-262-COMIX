package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class dbBuild {

    public static void Build() throws Exception {

        createComicsTable();
        createPublisherTable();

    }

    /**
     * Build the Comics table
     * DOES NOT POPULATE - THE TABLE WILL BE EMPTY
     * 
     * @throws Exception
     * @author Joe Baillie
     */

    public static void createComicsTable() throws Exception {

        try {

            Connection con = dbConnection.getConnection();
            PreparedStatement create = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS comics(id SERIAL PRIMARY KEY,  series_title TEXT NOT NULL, volume_number INT NOT NULL, issue_number INT NOT NULL, publication_date DATE, author TEXT, publisher_id INT, principle_character TEXT,  description TEXT, value FLOAT, grade INT)");
            create.executeUpdate();
            PreparedStatement delete = con.prepareStatement("DROP TABLE IF EXISTS comix");
            delete.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Build the publisher table
     * doesn't populate
     * Only builds new table if table doesn't already exist
     * 
     * @throws Exception
     * @author Joe Baillie
     */

    public static void createPublisherTable() throws Exception {
        try {

            Connection con = dbConnection.getConnection();
            PreparedStatement create = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS publisher(id SERIAL PRIMARY KEY,  name VARCHAR(40) NOT NULL)");
            create.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
