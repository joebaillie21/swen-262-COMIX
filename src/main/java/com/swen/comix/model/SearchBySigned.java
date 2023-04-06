package com.swen.comix.model;

import java.util.ArrayList;

public class SearchBySigned implements SearchStrategy {

    private PersonalCollection personalCollection; 
    public SearchBySigned(PersonalCollection personalCollection){
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
        ArrayList<ComicBook> comics = new ArrayList<>(); 
        ArrayList<ComicBook> pc = (ArrayList<ComicBook>) personalCollection.getPersonalCollection();
        for(int i = 0; i < pc.size(); i++ ){
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if(comic.getSignatures() != 0){
                comics.add(comic); 
            }
        }
        return comics; 
    }
    
}
