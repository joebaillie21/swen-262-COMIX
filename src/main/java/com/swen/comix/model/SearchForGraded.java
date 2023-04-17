package com.swen.comix.model;

import java.util.ArrayList;

/**
 * @Author Angela Ngo 
 * This searches for graded comics in the personal collections
 */
public class SearchForGraded implements SearchStrategy{
    private PersonalCollection personalCollection;


    public SearchForGraded(PersonalCollection personalCollection){
        this.personalCollection = personalCollection;
    }

    /**
     * returns an arraylist of comicbooks that are graded in the personal 
     * collection 
     * @param toBeSearched - String would be empty, not necessary for this method 
     * @param isSearchDb - boolean but would always be false since its on personal collection 
     */
    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) {
        ArrayList<ComicBook> comics = new ArrayList<>(); 
        
        if(isSearchDb == false){ // this wil almost always be false 
            comics = searchOnPC(); 
        }
        return comics; 
    }

    /**
     * Helper method for searching for graded comicbooks in personal collection 
     * @return
     */
    private ArrayList<ComicBook> searchOnPC(){
        ArrayList<ComicBook> pc = (ArrayList<ComicBook>) personalCollection.getPersonalCollection();

        ArrayList<ComicBook> comics = new ArrayList<>(); 
    
        for(int i = 0; i < pc.size(); i++){
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if(comic.getGrade() != comic.DEFAULT_GRADE){ // 
                comics.add(comic); 
            }
    
        }

        return comics;
    }
    
}