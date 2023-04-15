package com.swen.comix.model;

import java.util.ArrayList;

import com.swen.comix.db.*;

import java.sql.ResultSet;

/**
 * @Author Angela and Joe
 *         Returns arraylist of comicbooks from search results looking for
 *         specific Series title
 */
public class SearchBySeriesTitle implements SearchStrategy {
    private Database db;
    private PersonalCollection personalCollection;

    public SearchBySeriesTitle(PersonalCollection personalCollection, Database db){
        this.db = db; 
        this.personalCollection = personalCollection;
    }
    /* These constructors for testing purposes  */
    public SearchBySeriesTitle(PersonalCollection personalCollection) {
        this.personalCollection = personalCollection;
    }

    public SearchBySeriesTitle(Database db) {
        this.db = db;
    }

    /**
     * @param toBeSearched String - contains the series title to be searched
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
     * @param toBeSearched String - the series title
     * @return ArrayList<ComicBook> - arraylist of comicbooks returned after finding
     *         matching series title
     * @throws Exception
     */
    private ArrayList<ComicBook> searchOnDb(String toBeSearched) throws Exception {

        ResultSet res = db.getTable("SELECT * FROM comics WHERE series_title LIKE '%" + toBeSearched + "%'");

        return db.resToArrayList(res);
    }

    /**
     * Searches on PersonalCollection to return an arraylist of comic books based on
     * toBeSearched
     * 
     * @param toBeSearched String - containing the series title to be searched
     * @return ArrayList <ComicBook> - contains the matching comic books with series
     *         title
     */
    private ArrayList<ComicBook> searchOnPC(String toBeSearched) {
        ArrayList<ComicBook> comics = new ArrayList<>();
        ArrayList<ComicBook> pc = (ArrayList<ComicBook>) personalCollection.getPersonalCollection();

        for (int i = 0; i < pc.size(); i++) {
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if (comic.getSeriesTitle().equals(toBeSearched)) {
                comics.add(pc.get(i));

            }

        }
        // return the ones even if it doesnt reach the max NUM_COUNT
        return comics;
    }

}
