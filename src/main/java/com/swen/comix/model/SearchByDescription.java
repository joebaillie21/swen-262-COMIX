package com.swen.comix.model;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.swen.comix.db.*;

/**
 * @Author Angela and Joe
 *         Returns arraylist of comicbooks from search results looking for
 *         specific Description
 */
public class SearchByDescription implements SearchStrategy {
    private PersonalCollection personalCollection;
    private Database db;

    public SearchByDescription(PersonalCollection personalCollection) {
        this.personalCollection = personalCollection;
    }

    public SearchByDescription(Database db) {
        this.db = db;
    }

    /**
     * @param toBeSearched String - contains the description to be searched
     * @param isSearchDb   boolean - contains if you are searching on db or on pc
     */
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
     * 
     * @param toBeSearched String - the description
     * @return ArrayList<ComicBook> - arraylist of comicbooks returned after finding
     *         matching description
     * @throws Exception
     */
    private ArrayList<ComicBook> searchOnDb(String toBeSearched) throws Exception {

        ResultSet res = db.getTable("SELECT * FROM comics WHERE description LIKE '%" + toBeSearched + "%'");

        return db.resToArrayList(res);
    }

    /**
     * Searches on PersonalCollection to return an arraylist of comic books based on
     * toBeSearched
     * 
     * @param toBeSearched String - containing the description to be searched
     * @return ArrayList <ComicBook> - contains the matching comic books with
     *         description
     */
    private ArrayList<ComicBook> searchOnPC(String toBeSearched) {
        ArrayList<ComicBook> comics = new ArrayList<>();
        ArrayList<ComicBook> pc = (ArrayList<ComicBook>) personalCollection.getPersonalCollection();

        for (int i = 0; i < pc.size(); i++) {
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if (comic.getDescription().equals(toBeSearched)) {
                comics.add(pc.get(i));

            }

        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics;
    }
}
