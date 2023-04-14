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
        ArrayList<ComicBook> copy = new ArrayList<>();
        for(int i = 0; i < searchResults.size(); i++){
            copy.add(searchResults.get(i));
        }

        Collections.sort(copy, byVolumeNum); 
        return copy; 
    }

    public static Comparator<ComicBook> byVolumeNum = (book1, book2) -> book1.getVolNum().compareTo(book2.getVolNum());

    

    
}

