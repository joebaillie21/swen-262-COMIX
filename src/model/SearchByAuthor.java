package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.*;

public class SearchByAuthor implements SearchStrategy {

    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, String searchOn) throws Exception {
        ArrayList<ComicBook> comics = new ArrayList<>();
        
        if(searchOn.equals("database")){
            comics = searchOnDb(toBeSearched);
        }else if(searchOn.equals("personalcollection")){
            // implement the private methods 
        }
        return comics;
    }

    private ArrayList<ComicBook> searchOnDb(String toBeSearched) throws Exception{
        ArrayList<ComicBook> comics = new ArrayList<>();
        Connection con = dbManager.getConnection();
        PreparedStatement getComics = con.prepareStatement("SELECT * FROM comics WHERE author = " + toBeSearched);
        ResultSet res = getComics.executeQuery();
        while (res.next()) {
            String publisher = Integer.toString(res.getInt("publisher_id")); // This will be changed to be a join method
                                                                                // once data has been loaded and
                                                                                // expectations clarified
            String author = res.getString("author");
            String seriesTitle = res.getString("series_title");
            int volNum = res.getInt("volume_number");
            int issueNum = res.getInt("issue_number");
            String publicationDate = res.getString("publication_date");
            String principleCharacters = res.getString("principle_character");
            String description = res.getString("description");
            // ComicBookComponent comic = new ComicBookComponent(publisher, seriesTitle,
            // volNum, issueNum, publicationDate, author, principleCharacters, description);
        }
        return comics; 
    }

    private ArrayList<ComicBook> searchOnPC(String toBeSearched){
        
    }
}
