package com.swen.comix.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author Angela
 * This class sorts the search results by publication date 
 */

public class SortByPublicationDate implements SortStrategy {


    /**
     * This sorts the search results by publication date 
     * 
     * */    
    @Override
    public ArrayList<ComicBook> algorithm(ArrayList<ComicBook> searchResults) throws Exception {
        ArrayList<ComicBook> copy = new ArrayList<>();
        for(int i = 0; i < searchResults.size(); i++){
            copy.add(searchResults.get(i));
        }

        Collections.sort(copy, byPublicationDate); 
        return copy; 
    }

    public Comparator<ComicBook> byPublicationDate = (book1, book2) -> book1.getPublicationDate().compareTo(book2.getPublicationDate());
}