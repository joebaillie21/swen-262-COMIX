package com.swen.comix.model;

import java.util.ArrayList;

public class SearchForRuns implements SearchStrategy {

    private PersonalCollection personalCollection; 
    final int MIN_CONSEC_ISSUES = 12; 
    public SearchForRuns(PersonalCollection personalCollection){
        this.personalCollection = personalCollection; 
    }

    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) {
        ArrayList<ComicBook> comics = new ArrayList<>(); 

        if(isSearchDb == false){
            comics = searchByPC();
        }
        return comics; 
    }
    
    private ArrayList<ComicBook> searchByPC(){
        ArrayList<ComicBook> pc = (ArrayList<ComicBook>) personalCollection.getPersonalCollection();

    }
}
