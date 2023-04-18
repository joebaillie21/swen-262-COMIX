package com.swen.comix.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonEncoding;
import com.swen.comix.db.credentials.*;
import com.swen.comix.model.*;

public class Database implements iDatabase {

    private Connection con;

    /**
     * @author Joe
     *         Sets state con to the relevant connection to the database using
     *         getConnection();
     *         Prepares a String update to create the comics table with relevant
     *         fields if it doesn't already exist at the connection
     *         Calls the create table method which cleanses the string and executes
     *         the update.
     * @throws Exception
     */

    public Database() throws Exception {
        con = this.getConnection();

        loadData("DROP TABLE IF EXISTS comics");
        String comicsSQL = "CREATE TABLE comics(id SERIAL PRIMARY KEY,  series_title TEXT , volume_number TEXT , issue_number TEXT , publication_date VARCHAR(40), author TEXT, publisher TEXT, principle_character TEXT,  description TEXT, value FLOAT, grade INT, slab BOOLEAN DEFAULT FALSE, signatures INT DEFAULT 0, authenticated BOOLEAN DEFAULT FALSE)";
        createTable(comicsSQL);
        loadData(FileType.CSV, "src\\data\\comics(1).csv");
    }

    public Database(boolean testLoad) {
        con = this.getConnection();
    }

    /**
     * Provides the base connection for the database
     * IF THIS METHOD FAILS, IT WILL RETURN AN ERROR MESSAGE
     * THE ERROR MEANS THAT THE CONNECTION HAS NOT BEEN SUCCESSFULLY COMPLETED
     * Returns the connection to the database
     * Is called upon instantiation
     * 
     * @return connection
     */
    public Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(credentials.getHost(), credentials.getUsername(),
                    credentials.getPassword());

            if (connection != null) {
                return connection;
            } else {
                System.out.println("CRITICAL ERROR, CONNECTION FAILED");
                return null;

            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * This method is exclusively to be used for testing
     * Loads sample data for testing.
     * THIS METHOD SHOULD NEVER BE CALLED IN MAIN
     * 
     * @throws Exception
     */
    public void BuildSample() throws Exception {

        String clean = "DROP TABLE IF EXISTS comics";
        PreparedStatement del = con.prepareStatement(clean);
        del.executeUpdate();
        String comicsSQL = "CREATE TABLE comics(id SERIAL PRIMARY KEY,  series_title TEXT NOT NULL, volume_number TEXT NOT NULL, issue_number TEXT NOT NULL, publication_date VARCHAR(40), author TEXT, publisher TEXT, principle_character TEXT,  description TEXT, value FLOAT, grade INT, slab BOOLEAN DEFAULT FALSE, signatures INT DEFAULT 0, authenticated BOOLEAN DEFAULT FALSE)";
        createTable(comicsSQL);
        String loadData = """
                        INSERT INTO comics (series_title, volume_number, issue_number, publication_date, author, publisher, principle_character)
                        VALUES
                        ('Joes Comic', '1', '24', TO_DATE('2003-01-02', 'YYYY/MM/DD'),'Joe Baillie', '262 Publishing', 'Joe'),
                        ('Ashes Comic', '3', '21', TO_DATE('2001-01-12', 'YYYY/MM/DD'),'Ashe R', '262 Publishing', 'Ashe'),
                        ('Zachs Comic', '4', '33', TO_DATE('2012-01-05', 'YYYY/MM/DD'),'Zach B', '344 Publishing', 'Zach'),
                        ('Angelas Comic', '2', '12', TO_DATE('2017-01-06', 'YYYY/MM/DD'),'Angela N', '256 Publishing', 'Angela'),
                        ('Peytons Comic', '5', '19', TO_DATE('2023-01-01', 'YYYY/MM/DD'),'Peyton W', '250 Publishing', 'Peyton')

                """;
        PreparedStatement load = con.prepareStatement(loadData);
        int status = load.executeUpdate();
        System.out.println(status);

    }

    /**
     * @author Joe
     *         Creates table based on sql statement provided
     *         Statement is cleaned by preparing the statement
     *         This is utilized by the build method for the db
     *         This does not populate the table with data
     * @param sql
     */
    public void createTable(String sql) throws Exception {

        try {

            PreparedStatement create = con.prepareStatement(sql);
            create.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @author Joe
     *         Loads data based on the sql string sent
     *         String should contain full insert statement
     *         For testing, this is utilized by loading the sample db
     *         For actual system running, the loaded data is the returned statement
     *         from ExportToSql
     * @param sql
     * @throws Exception
     */
    public void loadData(String sql) throws Exception {

        try {
            PreparedStatement loadSample = con.prepareStatement(sql);
            loadSample.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Loads data from a provided ArrayList of comics into the database
     * 
     * @param c
     * @throws Exception
     */
    public void loadData(ArrayList<ComicBookComponent> c) throws Exception {
        Converter conv = new Converter(FileType.SQL, FileType.JAVA);
        String sql = conv.convertJavaToFile(c);
        loadData(sql);
    }

    public void loadData(FileType fileType, String filePath) throws Exception {
        Converter conv = new Converter(FileType.SQL, fileType, this);
        String update = conv.convertFileToFile(filePath);
        loadData(update);
    }

    /**
     * @author Joe
     *         Retrieves all data from the comics table
     * @return ResultSet
     */
    @Override
    public ResultSet getTable() throws Exception {
        String query = "SELECT * FROM comics";
        return getTable(query);
    }

    /**
     * @author Joe
     *         Retrieves a query based on the passed sql string
     *         String is cleansed using PreparedStatement to protect from sql
     *         injection
     * @param String
     * @return ResultSet
     */
    @Override
    public ResultSet getTable(String sql) throws Exception {
        PreparedStatement query = con.prepareStatement(sql);
        query.executeQuery();
        ResultSet res = query.getResultSet();
        return res;
    }

    /**
     * @author Joe
     *         Turns a ResultSet into an ArrayList of comic book components
     * @param ResultSet
     * @return ArrayList<ComicBookComponent>
     */
    @Override
    public ArrayList<ComicBook> resToArrayList(ResultSet res) throws Exception {

        ArrayList<ComicBook> comics = new ArrayList<>();

        while (res.next()) {

            Publisher publisher = new Publisher(res.getString("publisher"));

            String authors = res.getString("author");

            ArrayList<Author> authorsObjectArrayList = new ArrayList<>();

            if (authors.contains(",")) {

                String[] authorsArray = authors.split(",");

                for (String a : authorsArray) {

                    Author author = new Author(a);

                    authorsObjectArrayList.add(author);

                }

            }

            else {

                authorsObjectArrayList.add(new Author(authors));

            }

            String seriesTitle = res.getString("series_title");

            String volNum = res.getString("volume_number");

            String issueNum = res.getString("issue_number");

            java.sql.Date publicationDateSQL = res.getDate("publication_date");
            String publicationDate = publicationDateSQL.toString();

            ArrayList<String> principleCharacters = new ArrayList<>();

            if (res.getString("principle_character").contains(",")) {

                String[] characters = res.getString("principle_character").split(",");

                for (String c : characters) {

                    principleCharacters.add(c);

                }

            }

            else {

                principleCharacters.add(res.getString("principle_character"));
            }

            String description = res.getString("description");

            ComicBookComponent comic = new ComicBookComponent(publisher, seriesTitle, volNum, issueNum, publicationDate,
                    authorsObjectArrayList, principleCharacters, description);

            comic.setSignatures(res.getInt("signatures"));
            comic.setAuthentication(res.getBoolean("authenticated"));
            comic.setGrade(res.getInt("grade"));
            comic.setSlabbed(res.getBoolean("slab"));
            comic.setValue(res.getFloat("value"));

            comics.add(comic);
        }
        return comics;
    }

}
