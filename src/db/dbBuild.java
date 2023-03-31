package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class dbBuild {

    public static void main(String[] args) throws Exception {
        Build();
    }

    public static void Build() throws Exception {

        createComicsTable();
        createPublisherTable();
        loadData();

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

            Connection con = dbManager.getConnection();
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

            Connection con = dbManager.getConnection();
            PreparedStatement create = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS publisher(id SERIAL PRIMARY KEY,  name VARCHAR(40) NOT NULL)");
            create.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void loadData() throws Exception {

        try {
            Connection con = dbManager.getConnection();
            PreparedStatement loadSample = con.prepareStatement(
                    """
                            INSERT INTO comics (series_title, volume_number, issue_number, publication_date, author, publisher_id, principle_character)
                            VALUES
                            ('Joes Comic', 1, 24, TO_DATE('2003-01-02', 'YYYY/MM/DD'),'Joe Baillie', 1, 'Joe'),
                            ('Ashes Comic', 3, 21, TO_DATE('2001-01-12', 'YYYY/MM/DD'),'Ashe R', 1, 'Ashe'),
                            ('Zachs Comic', 4, 33, TO_DATE('2012-01-05', 'YYYY/MM/DD'),'Zach B', 1, 'Zach'),
                            ('Angelas Comic', 2, 12, TO_DATE('2017-01-06', 'YYYY/MM/DD'),'Angela N', 1, 'Angela'),
                            ('Peytons Comic', 5, 19, TO_DATE('2023-01-01', 'YYYY/MM/DD'),'Peyton W', 2, 'Peyton')

                                """);
            loadSample.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
