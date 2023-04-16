package com.swen.comix.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.swen.comix.db.credentials.*;
import com.swen.comix.model.*;

public class Database implements iDatabase {

    private Connection con;

    public Database() {
        con = this.getConnection();
    }

    public Connection getConnection() {

        Connection connection = null;
        String callReference = "I have been called";

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

    public void BuildSample() throws Exception {

        String clean = "DROP TABLE IF EXISTS comics";
        PreparedStatement del = con.prepareStatement(clean);
        del.executeUpdate();
        String comicsSQL = "CREATE TABLE comics(id SERIAL PRIMARY KEY,  series_title TEXT NOT NULL, volume_number TEXT NOT NULL, issue_number TEXT NOT NULL, publication_date DATE, author TEXT, publisher TEXT, principle_character TEXT,  description TEXT, value FLOAT, grade INT)";
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

    public void createTable(String sql) throws Exception {

        try {

            PreparedStatement create = con.prepareStatement(sql);
            create.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadData(String sql) throws Exception {

        try {
            PreparedStatement loadSample = con.prepareStatement(sql);
            loadSample.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ResultSet getTable() throws Exception {
        String query = "SELECT * FROM comics";
        return getTable(query);
    }

    @Override
    public ResultSet getTable(String sql) throws Exception {
        PreparedStatement query = con.prepareStatement(sql);
        query.executeQuery();
        ResultSet res = query.getResultSet();
        return res;
    }

    @Override
    public ArrayList<ComicBookComponent> resToArrayList(ResultSet res) throws Exception {

        ArrayList<ComicBookComponent> comics = new ArrayList<>();

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

            comics.add(comic);
        }
        return comics;
    }

}
