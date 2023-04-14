package com.swen.comix.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author Angela
 * This class sorts the search results by issue number
 */

public class SortByIssueNumber implements SortStrategy {


    /**
     * This sorts the search results by issue number 
     */
    @Override
    public ArrayList<ComicBook> algorithm(ArrayList<ComicBook> searchResults) throws Exception {
        ArrayList<ComicBook> copy = new ArrayList<>();
        for(int i = 0; i < searchResults.size(); i++){
            copy.add(searchResults.get(i));
        }

        Collections.sort(copy, byIssueNum); 
        return copy; 
    }
    
    public static Comparator<ComicBook> byIssueNum = (book1, book2) -> book1.getIssueNumber().compareTo(book2.getIssueNumber());
}

