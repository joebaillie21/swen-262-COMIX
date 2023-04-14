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

        Collections.sort(searchResults, byPublicationDate); 
        return searchResults; 
    }

    public Comparator<ComicBook> byPublicationDate = (book1, book2) -> book1.getPublicationDate().compareTo(book2.getPublicationDate());
}