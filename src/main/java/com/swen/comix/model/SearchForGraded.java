package com.swen.comix.model;

import java.util.ArrayList;

public class SearchForGraded implements SearchStrategy{
    private PersonalCollection personalCollection;
    final int NUM_RESULTS = 10;

    public SearchForGraded(PersonalCollection personalCollection){
        this.personalCollection = personalCollection;
    }
    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) {
        ArrayList<ComicBook> comics = new ArrayList<>(); 
        
        if(isSearchDb == false){ // this wil almost always be false 
            comics = searchOnPC(); 
        }
        return comics; 
    }

    private ArrayList<ComicBook> searchOnPC(){
        ArrayList<ComicBook> pc = (ArrayList<ComicBook>) personalCollection.getPersonalCollection();

        ArrayList<ComicBook> comics = new ArrayList<>(); 
        int currCount = 0; 

        for(int i = 1; i < NUM_RESULTS; i++){
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if(comic.getGrade() != 0 && currCount != NUM_RESULTS){ // 
                comics.add(comic); 
                currCount++;
            }
            if(currCount == NUM_RESULTS){
                return comics; 
            }
        }

        return comics;
    }
    
}
