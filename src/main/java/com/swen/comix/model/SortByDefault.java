package com.swen.comix.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SortByDefault implements SortStrategy {

    @Override
    public ArrayList<ComicBook> algorithm(ArrayList<ComicBook> searchResults) throws Exception {

        Comparator<ComicBook> chain = SortBySeriesTitle.bySeriesTitle
            .thenComparing(SortByVolume.byVolumeNum)
            .thenComparing(SortByIssueNumber.byIssueNum);
            
        Collections.sort(searchResults, chain); 
        return searchResults;  
    }

    
    
}
