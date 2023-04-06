package com.swen.comix.model;

import java.util.ArrayList;

public class SearchForGaps implements SearchStrategy{
    private PersonalCollection personalCollection;
    public SearchForGaps(PersonalCollection personalCollection){
        this.personalCollection = personalCollection;
    }
    
    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'algorithm'");
    } 
    
}
