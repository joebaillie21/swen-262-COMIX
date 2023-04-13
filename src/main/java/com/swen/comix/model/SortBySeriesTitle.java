package com.swen.comix.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author Angela 
 * This sorts the search results by series title 
 */

public class SortBySeriesTitle implements SortStrategy {

    /**
     * Sorts the personal collection or database by Series title
     * @param isSortDb - to differentiate if sorting the personal collection or the database
     */
    @Override
    public ArrayList<ComicBook> algorithm(ArrayList<ComicBook> searchResults) throws Exception {
        ArrayList<ComicBook> copy = new ArrayList<>();
        for(int i = 0; i < searchResults.size(); i++){
            copy.add(searchResults.get(i));
        }

        Collections.sort(copy, bySeriesTitle); 
        return copy; 
    }

    public static Comparator<ComicBook> bySeriesTitle = (book1, book2) -> book1.getSeriesTitle().compareTo(book2.getSeriesTitle());

}

