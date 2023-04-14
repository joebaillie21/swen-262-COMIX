package com.swen.comix.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SortByDefault implements SortStrategy {

    @Override
    public ArrayList<ComicBook> algorithm(ArrayList<ComicBook> searchResults) throws Exception {
        ArrayList<ComicBook> copy = new ArrayList<>();
        for(int i = 0; i < searchResults.size(); i++){
            copy.add(searchResults.get(i));
        }

        Comparator<ComicBook> chain = SortBySeriesTitle.bySeriesTitle
            .thenComparing(SortByVolume.byVolumeNum)
            .thenComparing(SortByIssueNumber.byIssueNum);
            
        Collections.sort(copy, chain); 
        return copy;  
    }

    
    
}
