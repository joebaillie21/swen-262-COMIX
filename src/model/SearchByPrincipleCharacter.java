package model;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.*;

/**
 * @Author Angela and Joe
 */
public class SearchByPrincipleCharacter implements SearchStrategy {
    private String toBeSearched;
    private PersonalCollection personalCollection;
    final int NUM_RESULTS = 10;

    public SearchByPrincipleCharacter(PersonalCollection personalCollection) {
        this.personalCollection = personalCollection;
    }

    /**
     * Assuming that the toBeSearched needs to be parsed but they need to be split
     * with commas
     * 
     * @throws Exception
     */
    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) throws Exception {
        ArrayList<ComicBook> comics = new ArrayList<>();
        String[] characters = toBeSearched.split(",");

        if (isSearchDb == true) {
            comics = searchOnDb(toBeSearched);
        } else if (isSearchDb == false) {
            comics = searchOnPC(characters);
        }
        return comics;
    }

    private ArrayList<ComicBook> searchOnPC(String[] toBeSearched) {

        ArrayList<ComicBook> comics = new ArrayList<>();
        ArrayList<ComicBook> pc = personalCollection.getPersonalCollection();
        int currCount = 0;

        boolean isMatch = false; // reset everytime new comic iterate
        for (int i = 0; i < pc.size(); i++) {
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            ArrayList<String> characters = comic.getPrincipleCharacter();

            for (int k = 0; k < toBeSearched.length; k++) {
                if (!characters.get(k).equals(toBeSearched[k])) {
                    isMatch = false;
                    break;
                }
            }

            if (isMatch == true && currCount != NUM_RESULTS) {
                currCount++;
                isMatch = false; // reset
                comics.add(comic);
            }

            if (currCount == NUM_RESULTS) { // when the max results reached
                return comics;
            }

        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics;
    }

    private ArrayList<ComicBook> searchOnDb(String toBeSearched) throws Exception {

        iDatabase db = new Database();

        ResultSet res = db.getTable("SELECT * FROM comics WHERE principle_character = '" + toBeSearched + "'");

        return db.resToArrayList(res);
    }

}
