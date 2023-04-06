package com.swen.comix.model;

import java.util.ArrayList;

public class SearchForSlabbed implements SearchStrategy{
    private PersonalCollection personalCollection;

    public SearchForSlabbed(PersonalCollection personalCollection){
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

        for(int i = 0; i < pc.size(); i++){
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if(comic.getSlabbed() == true){ // if they are slabbed
                comics.add(comic); 
            }
        }

        return comics;
    }
    
}
