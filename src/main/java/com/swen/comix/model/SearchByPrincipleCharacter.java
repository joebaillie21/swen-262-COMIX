package com.swen.comix.model;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.swen.comix.db.*;

/**
 * @Author Angela and Joe
 *         Returns arraylist of comicbooks from search results looking for
 *         specific principle characters
 */
public class SearchByPrincipleCharacter implements SearchStrategy {
    private Database db;
    private PersonalCollection personalCollection;

    public SearchByPrincipleCharacter(PersonalCollection personalCollection) {
        this.personalCollection = personalCollection;
    }

    public SearchByPrincipleCharacter(Database db) {
        this.db = db;
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

    /**
     * 
     * @param toBeSearched String [] : string of principle characters
     * @return Arraylist<ComicBook> of
     */
    private ArrayList<ComicBook> searchOnPC(String[] toBeSearched) {

        ArrayList<ComicBook> comics = new ArrayList<>();
        ArrayList<ComicBook> pc = (ArrayList<ComicBook>) personalCollection.getPersonalCollection();

        boolean isMatch = false; // reset everytime new comic iterate
        for (int i = 0; i < pc.size(); i++) {
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            ArrayList<String> characters = comic.getPrincipleCharacters();

            for (int k = 0; k < toBeSearched.length; k++) {
                if (!characters.get(k).equals(toBeSearched[k])) {
                    isMatch = false;
                    break;
                }
            }

            if (isMatch == true) {
                isMatch = false; // reset
                comics.add(comic);
            }

        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics;
    }

    /**
     * This searches on the db to get all the comic books that have the given
     * principle characters
     * 
     * @param toBeSearched
     * @return
     * @throws Exception
     */
    private ArrayList<ComicBook> searchOnDb(String toBeSearched) throws Exception {

        ResultSet res = db.getTable("SELECT * FROM comics WHERE principle_character LIKE '%" + toBeSearched + "%'");

        return db.resToArrayList(res);
    }

}
