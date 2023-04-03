package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.*;

/**
 * @Author Angela and Joe
 */
public class SearchByAuthor implements SearchStrategy {
    private PersonalCollection personalCollection; 
    final int NUM_RESULTS = 10;
    public SearchByAuthor(PersonalCollection pc){
        this.personalCollection = pc; 
    }
    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) throws Exception {
        ArrayList<ComicBook> comics = new ArrayList<>();
        
        if(isSearchDb == true){
            comics = searchOnDb(toBeSearched);
        }else if(isSearchDb == false){
            comics = searchOnPC(toBeSearched); 
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
        ArrayList<ComicBook> comics = new ArrayList<>(); 
        ArrayList<ComicBook> pc = personalCollection.getPersonalCollection(); 
        int currCount = 0; 
        
        for(int i = 0; i < pc.size(); i++){
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if(comic.getAuthor().equals(toBeSearched) && currCount != NUM_RESULTS){
                comics.add(pc.get(i)); 
                currCount++;
            }
            
            if(currCount == NUM_RESULTS){
                return comics; 
            }
        
        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics; 
    }
}
