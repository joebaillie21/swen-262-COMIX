package model;

import java.util.ArrayList;

import db.*;

import java.sql.ResultSet;

/**
 * @Author Angela and Joe
 */
public class SearchBySeriesTitle implements SearchStrategy {
    private PersonalCollection personalCollection;

    public SearchBySeriesTitle(PersonalCollection personalCollection) {
        this.personalCollection = personalCollection;
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

    private ArrayList<ComicBook> searchOnDb(String toBeSearched) throws Exception {

        iDatabase db = new Database();

        ArrayList<ComicBook> comics = new ArrayList<>();

        ResultSet res = db.getTable("SELECT * FROM comics WHERE series_title = '" + toBeSearched + "'");

        return db.resToArrayList(res);
    }

    private ArrayList<ComicBook> searchOnPC(String toBeSearched) {
        ArrayList<ComicBook> comics = new ArrayList<>();
        ArrayList<ComicBook> pc = (ArrayList<ComicBook>)personalCollection.getPersonalCollection();


        for (int i = 0; i < pc.size(); i++) {
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if (comic.getSeriesTitle().equals(toBeSearched)) {
                comics.add(pc.get(i));
                
            }
        
        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics;
    }

}
