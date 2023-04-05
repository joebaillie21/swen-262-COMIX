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

    public SearchByAuthor(PersonalCollection pc) {
        this.personalCollection = pc;
    }

    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) throws Exception {
        ArrayList<ComicBook> comics = new ArrayList<>();

        if (isSearchDb == true) {
            comics = searchOnDb(toBeSearched);
        } else if (isSearchDb == false) {
            comics = searchOnPC(toBeSearched);
        }
        return comics;
    }

    /**
     * @param toBeSearched
     * @return ArrayList of comics with the given author name
     * @throws Exception
     * @author Joe
     */

    private ArrayList<ComicBook> searchOnDb(String toBeSearched) throws Exception {

        iDatabase db = new Database();

        ArrayList<ComicBook> comics = new ArrayList<>();

        ResultSet res = db.getTable("SELECT * FROM comics WHERE author = '" + toBeSearched + "'");

        return db.resToArrayList(res);
    }

    private ArrayList<ComicBook> searchOnPC(String toBeSearched) {
        ArrayList<ComicBook> comics = new ArrayList<>();
        ArrayList<ComicBook> pc = personalCollection.getPersonalCollection();
        int currCount = 0;

        for (int i = 0; i < pc.size(); i++) {
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if (comic.getAuthor().equals(toBeSearched) && currCount != NUM_RESULTS) {
                comics.add(pc.get(i));
                currCount++;
            }

            if (currCount == NUM_RESULTS) {
                return comics;
            }

        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics;
    }
}
