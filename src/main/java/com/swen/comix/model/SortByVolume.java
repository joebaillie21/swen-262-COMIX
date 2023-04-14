package com.swen.comix.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author Angela
 * This sorts the search results by volume number 
 */

public class SortByVolume implements SortStrategy {

    @Override
    public ArrayList<ComicBook> algorithm(ArrayList<ComicBook> searchResults) throws Exception {
        Collections.sort(searchResults, byVolumeNum); 
        return searchResults; 
    }

    public static Comparator<ComicBook> byVolumeNum = (book1, book2) -> book1.getVolNum().compareTo(book2.getVolNum());

    

    
}

