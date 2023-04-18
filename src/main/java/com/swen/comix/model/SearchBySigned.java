package com.swen.comix.model;

import java.util.ArrayList;
/**
 * @author Angela Ngo 
 * This searches for any signed comics in the personal collection 
 */
public class SearchBySigned implements SearchStrategy {

    private PersonalCollection personalCollection; 
    public SearchBySigned(PersonalCollection personalCollection){
        this.personalCollection = personalCollection;
    }

    /**
     * @param toBeSearched - String this will be empty or null
     * @param isSearchDb - boolean this will always be false because this is only on the personal 
     * collection 
     */
    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) {
        ArrayList<ComicBook> comics = new ArrayList<>(); 
        if(isSearchDb == false){
            comics = searchOnPC();
        }
        return comics;
    }

    /**
     * This searches for any comics in the personal collection that are signed  
     * @return ArrayList<ComicBook> - this contains any comicbooks in the personal collection 
     * that are signed 
     */
    private ArrayList<ComicBook> searchOnPC(){
        ArrayList<ComicBook> comics = new ArrayList<>(); 
        ArrayList<ComicBookComponent> pc = (ArrayList<ComicBookComponent>) personalCollection.getPersonalCollection();
        for(int i = 0; i < pc.size(); i++ ){
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if(comic.getSignatures() != comic.DEFAULT_SIG_AMT){
                comics.add(comic); 
            }
        }
        return comics; 
    }
    
}