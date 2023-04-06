package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.*;

import model.Author;

public class Database implements iDatabase {

    private Connection con;

    public Database() {
        con = getConnection();
    }

    public Connection getConnection() {

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

    public void Build() throws Exception {

        String comicsSQL = "CREATE TABLE IF NOT EXISTS comics(id SERIAL PRIMARY KEY,  series_title TEXT NOT NULL, volume_number INT NOT NULL, issue_number INT NOT NULL, publication_date DATE, author TEXT, publisher TEXT, principle_character TEXT,  description TEXT, value FLOAT, grade INT)";
        createTable(comicsSQL);
        String loadData = """
                        INSERT INTO comics (series_title, volume_number, issue_number, publication_date, author, publisher, principle_character)
                        VALUES
                        ('Joes Comic', 1, 24, TO_DATE('2003-01-02', 'YYYY/MM/DD'),'Joe Baillie', '262 Publishing', 'Joe'),
                        ('Ashes Comic', 3, 21, TO_DATE('2001-01-12', 'YYYY/MM/DD'),'Ashe R', '262 Publishing', 'Ashe'),
                        ('Zachs Comic', 4, 33, TO_DATE('2012-01-05', 'YYYY/MM/DD'),'Zach B', '344 Publishing', 'Zach'),
                        ('Angelas Comic', 2, 12, TO_DATE('2017-01-06', 'YYYY/MM/DD'),'Angela N', '256 Publishing', 'Angela'),
                        ('Peytons Comic', 5, 19, TO_DATE('2023-01-01', 'YYYY/MM/DD'),'Peyton W', '250 Publishing', 'Peyton')

                """; // This should be swapped out with a getData method from adapter pattern

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
    public ArrayList<ComicBook> resToArrayList(ResultSet res) throws Exception {

        ArrayList<ComicBook> comics = new ArrayList<>();

        while (res.next()) {

            Publisher publisher = new Publisher(res.getString("publisher"));

            String authors = res.getString("author");
            String[] authorsArray = authors.split(",");
            ArrayList<Author> authorsObjectArrayList = new ArrayList<>();
            for (String a : authorsArray) {
                Author author = new Author(a);
                authorsObjectArrayList.add(author);
            }

            Author author = new Author(res.getString("author"));

            String seriesTitle = res.getString("series_title");

            int volNum = res.getInt("volume_number");

            int issueNum = res.getInt("issue_number");

            String publicationDate = res.getString("publication_date");

            ArrayList<String> principleCharacters = new ArrayList<>();

            principleCharacters.add(res.getString("principle_character"));

            String description = res.getString("description");

            ComicBookComponent comic = new ComicBookComponent(publisher, seriesTitle, volNum, issueNum, publicationDate,
                    authorsObjectArrayList, principleCharacters, description);

            comics.add(comic);
        }
        return comics;
    }

}
