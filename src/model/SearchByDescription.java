package model;

import java.sql.ResultSet;
import java.util.ArrayList;

import db.Database;
import db.iDatabase;

/**
 * @Author Angela and Joe
 */
public class SearchByDescription implements SearchStrategy {
    private PersonalCollection personalCollection;


    public SearchByDescription(PersonalCollection personalCollection) {
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

        ResultSet res = db.getTable("SELECT * FROM comics WHERE description = '" + toBeSearched + "'");

        return db.resToArrayList(res);
    }

    private ArrayList<ComicBook> searchOnPC(String toBeSearched) {
        ArrayList<ComicBook> comics = new ArrayList<>();
        ArrayList<ComicBook> pc = personalCollection.getPersonalCollection();

        for (int i = 0; i < pc.size(); i++) {
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if (comic.getDescription().equals(toBeSearched) ) {
                comics.add(pc.get(i));
        
            }
            

        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics;
    }
}
